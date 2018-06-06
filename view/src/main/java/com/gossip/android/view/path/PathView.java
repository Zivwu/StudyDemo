package com.gossip.android.view.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class PathView extends View {

    private Paint paint;
    private Path path;
    private Wave wave;
    private int progress;

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
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        paint.setColor(Color.RED);
        path = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset();
        path.moveTo(0, 100);
        for (int i = 1; i < getWidth() / wave.width / 2 + 2; i++) {
            path.quadTo(wave.width / 2 * i, 80, 100, 100);
            path.quadTo(150, 120, 200, 100);
        }
        canvas.drawPath(path, paint);

    }


    private class Wave {
        /**
         * 一个波纹的高度的偏移量
         */
        private int height = 20;
        /**
         * 一个波纹的宽度
         */
        private int width = 100;

        public Wave(int height, int width) {
            this.height = height;
            this.width = width;
        }
    }
}
