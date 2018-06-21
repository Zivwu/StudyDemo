package com.example.wuming.myapplication.activitylife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.wuming.myapplication.BaseActivity;
import com.example.wuming.myapplication.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSecondActivity(View view) {
        Intent intent =new Intent(this,SecondActivity.class);
        startActivity(intent);
    }
}
