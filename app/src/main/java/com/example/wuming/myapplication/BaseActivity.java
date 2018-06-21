package com.example.wuming.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class BaseActivity extends AppCompatActivity {
    private String TAG =this.getClass().getName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        print("onCreate");
    }


    @Override
    protected void onStart() {
        super.onStart();
        print("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        print("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        print("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        print("onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        print("onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        print("onDestroy");
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        print("onAttachedToWindow");
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        print("onDetachedFromWindow");
    }

    private void print(String msg){
        Log.d(TAG,msg);
    }
}
