package com.example.sinyakkirill.lab_1;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Camera camera;
    SurfaceView cameraSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        camera = Camera.open();
    }
}
