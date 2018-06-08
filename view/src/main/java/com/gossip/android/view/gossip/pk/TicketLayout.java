package com.gossip.android.view.gossip.pk;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Random;

public class TicketLayout extends FrameLayout {
    private int width;
    private int height;

    public TicketLayout(@NonNull Context context) {
        super(context);
    }

    public TicketLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TicketLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    public void addView() {
        TextView view = new TextView(this.getContext());
        view.setText("+1");
        view.setTextColor(Color.RED);
//        view.setTextSize();
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        this.addView(view, params);
        AnimatorSet set = getAnimator(view);
        set.addListener(new AnimEndListener(view));
        set.start();
    }

    private AnimatorSet getAnimator(final View target) {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(target, View.ALPHA, 0.2f, 1f, 0.2f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(target, View.SCALE_X, 0.2f, 1.5f, 0.5f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(target, View.SCALE_Y, 0.2f, 1.5f, 0.5f);
        ObjectAnimator y = ObjectAnimator.ofFloat(target, View.Y, height, 0);
        AnimatorSet anim = new AnimatorSet();
        anim.setDuration(2000);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.playTogether(alpha, y, scaleX, scaleY);
        anim.setTarget(target);
        return anim;
    }


    private class AnimEndListener extends AnimatorListenerAdapter {
        private View target;

        public AnimEndListener(View target) {
            this.target = target;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            //因为不停的add 导致子view数量只增不减,所以在view动画结束后remove掉
            removeView((target));
        }
    }


}
