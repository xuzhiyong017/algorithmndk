package com.sky.algorithmndk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startActivity(new Intent(this,MainActivity.class));
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                slowInfo();
//            }
//        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("MyApplication","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
        Log.d("MyApplication","onPause");
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d("MyApplication","onAttachedToWindow");
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d("MyApplication","onDetachedFromWindow");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MyApplication","onDestroy");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        finish();
    }

    private void slowInfo() {
        long start = System.currentTimeMillis();
        for (long i = 0; i < 100000; i++) {
//            Log.d("MyApplication","日志");
        }

//        try {
//            Thread.sleep(1200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        long cur = System.currentTimeMillis();
        Log.d("MyApplication","slowInfo Activity ="+(cur - start) + "  cur="+cur);
    }
}