package com.gossip.android.view.path;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gossip.android.view.R;

public class PathActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.path_activity_path);
    }

    public void onclick(View view) {
      PathView view1 =  this.findViewById(R.id.pathView);
        view1.start();
    }
}
