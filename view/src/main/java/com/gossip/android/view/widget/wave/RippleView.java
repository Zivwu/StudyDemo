package com.gossip.android.view.widget.wave;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.gossip.android.view.utils.Logger;

public class RippleView extends View {

    public String TAG = getClass().getName();


    public RippleView(Context context) {
        super(context);
    }

    public RippleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RippleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //处理
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int measureWidth = handleSize(widthSize, 200, widthMode);
        int measureHeight = handleSize(heightSize, 200, heightMode);
        setMeasuredDimension(measureWidth, measureHeight);

    }

    private int handleSize(int size, int defSize, int mode) {
        int measuredSize = 0;
        if (mode == MeasureSpec.EXACTLY) {
            //指定了具体的大小,match_parent也会是这种模式
            measuredSize = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            //相当于wrap_content
            measuredSize = size > defSize ? defSize : size;
        } else if (mode == MeasureSpec.UNSPECIFIED) {
            //不限制view的大小
            measuredSize = size;
        }

        printMeasureInfo(size, defSize, mode);

        return measuredSize;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        if (this.getHeight() != 0 && this.getWidth() != 0) {
            float[] stops = new float[]{0f, 0.7f, 1f};
            RadialGradient mRadialGradient = new RadialGradient(this.getWidth() / 2,
                    this.getHeight() / 2, getWidth() / 2,
                    new int[]{Color.parseColor("#000000"), Color.WHITE, Color.parseColor("#00FFFFFF")},
                    stops, Shader.TileMode.CLAMP);
            mPaint.setShader(mRadialGradient);
        }

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, mPaint);

    }

    private void printMeasureInfo(int size, int defSize, int mode) {
        String modeName = null;
        if (mode == MeasureSpec.EXACTLY) {
            //指定了具体的大小
            modeName = "EXACTLY";
        } else if (mode == MeasureSpec.AT_MOST) {
            //相当于wrap_content
            modeName = "AT_MOST";
        } else if (mode == MeasureSpec.UNSPECIFIED) {
            modeName = "UNSPECIFIED";
        }

        Logger.printD(TAG, "size" + size + "\ndefSiz" + defSize + "\nmode" + modeName);
    }
}
