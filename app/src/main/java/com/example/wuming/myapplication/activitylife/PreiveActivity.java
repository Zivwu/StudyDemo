package com.example.wuming.myapplication.activitylife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.wuming.myapplication.R;

public class PreiveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preive);
    }

    public void finish(View view) {
        onBackPressed();
    }
}
