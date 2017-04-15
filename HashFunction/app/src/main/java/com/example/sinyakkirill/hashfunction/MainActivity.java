package com.example.sinyakkirill.hashfunction;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private LocationListener mLocationListener;
    private Double currentLatitude = 0.0d;
    private Double currentLongitude = 0.0d;
    private boolean pointSending = false;

    Button startSendingButton;
    Button stopSendingButton;
    public static final int MIN_SEC = 2;
    public static final int MIN_DISTANCE = 5;
    private LocationManager mLocationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startSendingButton = (Button) findViewById(R.id.startSendingButton);
        stopSendingButton = (Button) findViewById(R.id.stopSendingButton);


        mLocationListener = new LocationListener() {

            @Override

            public void onLocationChanged(Location location) {

                if (location != null) {

                    currentLatitude = location.getLatitude();

                    currentLongitude = location.getLongitude();

                    Log.d("HashFunction", "Latitude: " + currentLatitude + " Longitude: " + currentLongitude);

                    if(pointSending){
                        //производим отправку полученных координат
                        sendCoordinatesOnServer(currentLatitude, currentLongitude);
                        Log.d("HashFunction", "Latitude: " + currentLatitude + " Longitude: " + currentLongitude);
                    }

                } else {

                    Toast.makeText(getApplicationContext(), "Object location not found", Toast.LENGTH_SHORT).show();

                }
            }
            @Override

            public void onStatusChanged(String provider, int status, Bundle extras) { }


            @Override

            public void onProviderEnabled(String provider) {
                choiceMode();
            }


            @Override

            public void onProviderDisabled(String provider) {
                choiceMode();
            }

        };
    }

    private void choiceMode() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        dialogBuilder.setTitle("Mode")

                .setMessage("Choice Mode")

                .setNegativeButton("GPS", new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialog, int which) {

                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));

                        if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000 * MIN_SEC, MIN_DISTANCE, (android.location.LocationListener) mLocationListener);

                            return;

                        } else {

                            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000 * MIN_SEC, MIN_DISTANCE, (LocationListener) mLocationListener);

                        }

                    }

                })

                .setPositiveButton("Internet", new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialog, int which) {

                        startActivity(new Intent(Settings.ACTION_SETTINGS));

                        if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000 * MIN_SEC, MIN_DISTANCE, mLocationListener);

                            return;

                        } else {

                            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000 * MIN_SEC, MIN_DISTANCE, mLocationListener);

                        }

                    }

                });

        Dialog dialog = dialogBuilder.create();

        dialog.show();

    }

    private void init()
    {
        mLocationListener = new LocationListener() {

            @Override

            public void onLocationChanged(Location location) {

                if (location != null) {

                    currentLatitude = location.getLatitude();

                    currentLongitude = location.getLongitude();

                    Log.d("HashFunction", "Latitude: " + currentLatitude + " Longitude: " + currentLongitude);

                    if(pointSending){
                        //производим тправку полученных координат
                        sendCoordinatesOnServer(currentLatitude, currentLongitude);
                    }

                    /*currentCoordinates = String.format("Coordinates (lat; lon): %1$.4f, %2$.4f", currentLatitude, currentLongitude);

                    currentTime = String.format("Time: %1$tF, %1$tT", new Date(location.getTime()));

                    currentAltitude = new String("Altitude: " + String.valueOf(location.getAltitude()));

                    currentStreet = new String("Street: " + getStreet(location.getLatitude(), location.getLongitude()));



                    mTextViewCoordinates.setText(currentCoordinates);

                    mTextViewTime.setText(currentTime);

                    mTextViewAltitude.setText(currentAltitude);

                    mTextViewStreet.setText(currentStreet);
                    if (isStart) {

                        LatLng point = new LatLng(currentLatitude, currentLongitude);

                        line.add(point);

                        line.color(Color.BLUE);

                        mPolyline = mMap.addPolyline(line);

                    }*/

                } else {

                    Toast.makeText(getApplicationContext(), "Object location not found", Toast.LENGTH_SHORT).show();

                }

            }



            @Override

            public void onStatusChanged(String provider, int status, Bundle extras) { }


            @Override

            public void onProviderEnabled(String provider) {
                //choiceMode();
            }


            @Override

            public void onProviderDisabled(String provider) {
                //choiceMode();
            }

        };
    }

    private void startSending()
    {
        pointSending = true;
    }

    private void stopSending()
    {
        pointSending = false;
    }

    private void sendCoordinatesOnServer(Double latitude, Double longitude)
    {

    }

}
