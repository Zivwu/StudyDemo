package com.gossip.android.view.path;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.gossip.android.view.utils.Logger;

public class PathView extends View {

    private Paint paint;
    private Path path;
    private Wave wave;
    private int progress=50;
    private int dx;

    public PathView(Context context) {
        super(context);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        //抗锯齿
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(4);
        paint.setColor(Color.RED);
        path = new Path();
        wave=new Wave(20,200);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset();
        int currentY =this.getHeight()*(100-progress)/100;
        path.moveTo(0+dx, currentY);
//        for (int i = 1; i < getWidth() / wave.width / 2 + 2; i++) {
//            int firstX =wave.width*i;
//            int secondX=firstX+wave.width;
//            path.quadTo(firstX-wave.width/2, currentY-wave.height,
//                    firstX, currentY);
//            path.quadTo(firstX+wave.width/2, currentY+wave.height,
//                    secondX, currentY);
//            Logger.printD("PATH","i:"+i+"\ncontrol1:"+wave.width / 2*i+"\ncontrol2:"+wave.width / 2*(i+2));
//        }

        for (int i=0;i<getWidth()/wave.width+1;i++){
            int firstX=wave.width*i+wave.width/2+dx;
            int secondX =firstX +wave.width/2;
            int controlX = firstX -wave.width/4;
            int controlX2 = secondX -wave.width/4;
            int i1 = wave.width*i+wave.width >> 1;
            Logger.printD("PATH","i:"+i
                    +"\nfirstX:"+firstX
                    +"\ni1:"+i1
                    +"\nsecondX:"+secondX
                    +"\ncontrolX:"+controlX
                    +"\ncontrolX2:"+controlX2);
            path.quadTo(controlX,currentY-20,firstX,currentY);
            path.quadTo(controlX2,currentY+20,secondX,currentY);

        }
        canvas.drawPath(path, paint);

        path.reset();
        currentY+=100;
        path.moveTo(0, currentY);
        path.quadTo(50,currentY-20,100,currentY);
        path.quadTo(150,currentY+20,200,currentY);

        path.quadTo(250,currentY-20,300,currentY);
        path.quadTo(350,currentY+20,400,currentY);

        path.close();
        canvas.drawPath(path, paint);

    }


    public void start(){
        ValueAnimator animator = ValueAnimator.ofInt(0,wave.width);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx= (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {


            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                dx = 0;
            }
        });

        //无限重复
        animator.setRepeatCount(ValueAnimator.INFINITE);

        animator.start();
    }



    private class Wave {
        /**
         * 一个波纹的高度的偏移量
         */
        private int height = 20;
        /**
         * 一个波纹的长度
         */
        private int width = 100;

        public Wave(int height, int width) {
            this.height = height;
            this.width = width;
        }
    }
}
