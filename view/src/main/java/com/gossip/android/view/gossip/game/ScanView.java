package com.gossip.android.view.gossip.game;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.gossip.android.view.R;

public class ScanView extends FrameLayout {
    public static final int SCAN_VIEW_TYPE_LOOK = 1;
    public static final int SCAN_VIEW_TYPE_UN_LOOK = 2;
    public static final int SCAN_VIEW_TYPE_GONE = 3;

    protected int[] numRes = {R.mipmap.room_game_scan_num_0, R.mipmap.room_game_scan_num_1,
            R.mipmap.room_game_scan_num_2, R.mipmap.room_game_scan_num_3,
            R.mipmap.room_game_scan_num_4, R.mipmap.room_game_scan_num_5,
            R.mipmap.room_game_scan_num_6, R.mipmap.room_game_scan_num_7,
            R.mipmap.room_game_scan_num_8, R.mipmap.room_game_scan_num_9};
    protected int[] bgRes = {R.mipmap.room_game_scan_bg_blue, R.mipmap.room_game_scan_bg_red,
            R.mipmap.room_game_scan_bg_gray};
    protected ImageView numView;
    private ImageView bg;

    public ScanView(@NonNull Context context) {
        super(context);
    }

    public ScanView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScanView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    protected void init() {
        LayoutInflater.from(getContext()).inflate(getLayoutRes(), this);
        numView = findViewById(R.id.iv_num);
        bg = findViewById(R.id.iv_bg);
    }

    protected int getLayoutRes() {
        return R.layout.room_game_widget_scan_view_small;
    }

    public void setNum(int num, int type) {
        setNum(num);
        setBg(type);
    }

    public void setNum(int num) {
        int res;
        if (num >= 0 && num < numRes.length) {
            res = numRes[num];
            onOpenNum();
        } else {
            res = R.mipmap.room_game_scan_unknow;
            onCloseNum();
        }
        numView.setImageResource(res);
    }

    protected void  onOpenNum(){

    }

    protected void onCloseNum(){

    }


    public void setBg(int type) {
        int res;
        if (type > 3 || type < 1) {
            res = 0;
        } else {
            res = bgRes[type - 1];
        }
        bg.setImageResource(res);
    }
}
