package com.gossip.android.view.gossip.game;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.gossip.android.view.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ScanAnimView extends ScanView {

    private AnimatorSet set;
    private ImageView roundView;
    private ImageView startOne;
    private ImageView startTwo;
    private ImageView startThree;

    private AnimatorSet numSet;

    private Disposable subscribe;
    private ImageView ivHand;
    private FrameLayout flNotifyClick;
    private ObjectAnimator handAnim;

    public ScanAnimView(@NonNull Context context) {
        super(context);
    }

    public ScanAnimView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScanAnimView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.room_game_widget_scan_view_big;
    }

    @Override
    protected void init() {
        super.init();
        roundView = findViewById(R.id.iv_round);
        startOne = findViewById(R.id.iv_start_one);
        startTwo = findViewById(R.id.iv_start_two);
        startThree = findViewById(R.id.iv_start_three);
        ivHand = findViewById(R.id.iv_hand);
        flNotifyClick = findViewById(R.id.fl_notify_click);
        initBgAndRoundAnim();
        initNumAnim();
    }

    private ObjectAnimator getHandAnim() {
        ObjectAnimator handAnim = ObjectAnimator.ofFloat(ivHand, "translationY", 0, 90);
        handAnim.setDuration(500);
        handAnim.setRepeatCount(ValueAnimator.INFINITE);//重复数量
        handAnim.setRepeatMode(ValueAnimator.REVERSE);//重复模式
        return handAnim;
    }

    private void initBgAndRoundAnim() {
        set = new AnimatorSet();
        //round
        ObjectAnimator rotation = ObjectAnimator.ofFloat(roundView, "rotation", 0, 3600);
        rotation.setDuration(20000);
        rotation.setInterpolator(new LinearInterpolator());
        rotation.setRepeatCount(ValueAnimator.INFINITE);//重复数量
        rotation.setRepeatMode(ValueAnimator.RESTART);//重复模式

        ObjectAnimator starAnimOne = getStarAnim(startOne, 500);
        ObjectAnimator starAnimTwo = getStarAnim(startTwo, 1000);
        ObjectAnimator starAnimThree = getStarAnim(startThree, 1500);
        set.playTogether(rotation, starAnimOne, starAnimTwo, starAnimThree);
    }

    private void initNumAnim() {
        //num
        numSet = new AnimatorSet();
        ObjectAnimator alpha = ObjectAnimator.ofFloat(numView, View.ALPHA, 0.0f, 1f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(numView, View.SCALE_X, 0.0f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(numView, View.SCALE_Y, 0.0f, 1f);
        numSet.setDuration(1000);
        numSet.playTogether(alpha, scaleX, scaleY);
        numSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (set != null) {
                    if (set.isRunning()) {
                        setRoundVisibility(View.GONE);
                        set.cancel();
                    }
                }
            }
        });
    }

    private void setRoundVisibility(int visibility) {
        roundView.setVisibility(visibility);
        startOne.setVisibility(visibility);
        startTwo.setVisibility(visibility);
        startThree.setVisibility(visibility);
    }


    public void setNum(final int num, boolean playAnim) {
        if (playAnim) {
            setRoundVisibility(View.VISIBLE);
            if (set != null)
                set.start();
            numView.setImageResource(0);
            if (num >= 0 && num < numRes.length)
                onOpenNum();

            if (subscribe != null) {
                subscribe.dispose();
            }

            subscribe = Observable.timer(2000, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            setNum(num);
                            if (numSet != null)
                                numSet.start();
                        }
                    });
        } else {
            setNum(num);
        }

    }

    @Override
    protected void onOpenNum() {
        super.onOpenNum();
        flNotifyClick.setVisibility(GONE);
        if (handAnim!=null)
            handAnim.cancel();
    }

    @Override
    protected void onCloseNum() {
        super.onCloseNum();
        if (handAnim==null)
            handAnim=getHandAnim();
        flNotifyClick.setVisibility(VISIBLE);
        handAnim.start();
    }

    private ObjectAnimator getStarAnim(View view, int time) {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, View.ALPHA, 0.2f, 1f);
        alpha.setDuration(time);
        alpha.setRepeatCount(ValueAnimator.INFINITE);//重复数量
        alpha.setRepeatMode(ValueAnimator.REVERSE);//重复模式
        return alpha;
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (subscribe != null) {
            subscribe.dispose();
        }
        if (set != null)
            set.cancel();
        if (numSet != null)
            numSet.cancel();
        if (handAnim!=null)
            handAnim.cancel();
    }
}
