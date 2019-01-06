package com.example.wuming.myapplication.activitylife;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.ImageView;

import com.example.wuming.myapplication.BaseActivity;
import com.example.wuming.myapplication.R;

public class SecondActivity extends BaseActivity {

    private ImageView view;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        view = findViewById(R.id.iv);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(SecondActivity.this, view, "head").toBundle();
                Intent intent = new Intent(SecondActivity.this, PreiveActivity.class);
                startActivity(intent, bundle);
//            startActivity(intent);
            }
        });

    }
}
