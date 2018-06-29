package com.gossip.android.view.gossip.pk;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.gossip.android.view.R;

public class TarotView extends FrameLayout {
    private ImageView frontView;
    private ImageView backView;
    private AnimatorSet rotationYAnim;


    public TarotView(@NonNull Context context) {
        super(context);
        init();
    }

    public TarotView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TarotView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.room_game_widget_tarot, this);
        frontView = findViewById(R.id.iv_front);
        backView = findViewById(R.id.iv_back);
        rotationYAnim = new AnimatorSet();
        AnimatorSet  backAnim = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.room_game_tarot_back);
        AnimatorSet frontAnim = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.room_game_tarot_front);
        backAnim.setTarget(backView);
        frontAnim.setTarget(frontView);
        rotationYAnim.playTogether(backAnim,frontAnim);
        setCameraDistance();
    }

    private void setCameraDistance() {
        int distance = 16000;
        float scale = getResources().getDisplayMetrics().density * distance;
        frontView.setCameraDistance(scale);
        backView.setCameraDistance(scale);
    }



    public void setFontImageRes(int resId) {
        frontView.setImageResource(resId);
    }


    public void setBackImageRes(int resId) {
        backView.setImageResource(resId);
    }

    public void startAnim() {
        rotationYAnim.start();
    }


}
