package com.pek.cameraapi;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;

import java.io.File;

import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;


@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class MainActivity extends AppCompatActivity {
    private SurfaceView mSurfaceView;
    public static long sTime;
    public static int sCounter = 1;
    public static CounterClass sTimer;
    private Camera mCamera;
    private MediaRecorder mMediaRecorder;
    private File mVideoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSurfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        SurfaceHolder holder = mSurfaceView.getHolder();

        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    mCamera.setPreviewDisplay(holder);
                    mCamera.startPreview();
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
        mCamera = Camera.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseMediaRecorder();
        if (mCamera != null) {
            mCamera.release();
        }
        mCamera = null;
    }

    public void onClickStartRecord(View view) {
        if (prepareVideoRecorder()) {
            mMediaRecorder.start();
        } else {
            releaseMediaRecorder();
        }
        sTimer = new CounterClass(60000, 1000);
        sTimer.start();
    }

    public void onClickStopRecord(View view) {
        if (mMediaRecorder != null) {
            mMediaRecorder.stop();
            releaseMediaRecorder();
        }
        sTimer.onFinish();
    }

    private boolean prepareVideoRecorder() {
        mCamera.unlock();
        mMediaRecorder = new MediaRecorder();
        File pictures = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        sCounter++;
        mVideoFile = new File(pictures,String.valueOf(sCounter).toString() +"myvideo.3gp");
        mMediaRecorder.setCamera(mCamera);
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        mMediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH));
        mMediaRecorder.setOutputFile(mVideoFile.getAbsolutePath());
        mMediaRecorder.setPreviewDisplay(mSurfaceView.getHolder().getSurface());

        try {
            mMediaRecorder.prepare();
        } catch (Exception e) {
            e.printStackTrace();
            releaseMediaRecorder();
            return false;
        }
        return true;
    }

    private void releaseMediaRecorder() {
        if (mMediaRecorder != null) {
            mMediaRecorder.reset();
            mMediaRecorder.release();
            mMediaRecorder = null;
            mCamera.lock();
        }
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer {
        public CounterClass(long millisinFuture,long CountDownInterval) {
            super(millisinFuture,CountDownInterval);
        }

        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @SuppressLint("NewApi")
        @Override
        public void onTick(long millisUntilFinished) {
            sTime = millisUntilFinished;
        }

        @Override
        public void onFinish() {
            if (mMediaRecorder != null) {
                mMediaRecorder.stop();
                releaseMediaRecorder();
            }
            if(sTime < 59000) {
                Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Successful! Limit of record is ONE minute", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
