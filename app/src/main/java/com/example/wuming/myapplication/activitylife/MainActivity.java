package com.example.wuming.myapplication.activitylife;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.wuming.myapplication.BaseActivity;
import com.example.wuming.myapplication.R;

import me.leolin.shortcutbadger.ShortcutBadger;

public class MainActivity extends BaseActivity
        implements OneFragment.OnFragmentInteractionListener, BlankFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.ed).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // 获取系统剪贴板
//                ClipboardManager clipboard = (ClipboardManager) MainActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
//
//                // 获取剪贴板的剪贴数据集
//                ClipData clipData = clipboard.getPrimaryClip();
//
//                if (clipData != null && clipData.getItemCount() > 0) {
//                    // 从数据集中获取（粘贴）第一条文本数据
//                    CharSequence text = clipData.getItemAt(0).getText();
//                    System.out.println("text: " + text);
//                }

                return false;
            }
        });

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_fragment, new OneFragment())
                .commit();
    }

    public void onSecondActivity(View view) {
//        Intent intent =new Intent(this,SecondActivity.class);
//        startActivity(intent);


    }

    int i = 0;

    public void delete(View view) {
        // 获取系统剪贴板
        ClipboardManager clipboard = (ClipboardManager) MainActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);

// 创建一个剪贴数据集，包含一个普通文本数据条目（需要复制的数据）
        ClipData clipData = ClipData.newPlainText(null, "需要复制的文本数据");

// 把数据集设置（复制）到剪贴板
        clipboard.setPrimaryClip(clipData);
    }

    public void add(View view) {
//        i++;
//        Toast.makeText(this, String.valueOf(i), Toast.LENGTH_SHORT).show();
//        ShortcutBadger.applyCount(this, i);



    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
