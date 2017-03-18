package com.example.sinyakkirill.lab_1;

import android.app.Application;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Camera camera;
    SurfaceView cameraSurfaceView;
    Button takePicture;
    Button startRecord;
    Button stopRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        takePicture = (Button) findViewById(R.id.btnTakePicture);
        startRecord = (Button) findViewById(R.id.btnStartRecord);
        stopRecord = (Button) findViewById(R.id.btnStopRecord);
        cameraSurfaceView = (SurfaceView) findViewById(R.id.cameraSurfaceView);

        SurfaceHolder holder = cameraSurfaceView.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
              try {
                  camera.setPreviewDisplay(holder);
                  camera.startPreview();
              } catch (Exception e) {
                e.printStackTrace();
                }
        }
           @Override
           public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

          @Override
          public void surfaceDestroyed(SurfaceHolder holder) {}
         });

        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camera.takePicture(null, null, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] bytes, Camera camera) {
                        camera.autoFocus(new Camera.AutoFocusCallback() {
                            @Override
                            public void onAutoFocus(boolean b, Camera camera) {
                                if(b){
                                    Toast.makeText(getApplicationContext(), "Фото ОК!", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Фото NO!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        camera = Camera.open();
    }
}
