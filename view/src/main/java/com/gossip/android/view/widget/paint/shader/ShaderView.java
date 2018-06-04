package com.gossip.android.view.widget.paint.shader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class ShaderView extends View {
    public ShaderView(Context context) {
        super(context);
    }

    public ShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }







    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint mPaint =new Paint();
        int w=this.getWidth();
        int h=this.getHeight();
        float[] stops = new float[]{0f,0.4f,0.6f,1f};
        RadialGradient mShader = new RadialGradient(w/2,
                h/2,
                w/2,
                new int[]{Color.WHITE,
                        Color.WHITE,
                        Color.BLACK,
                        Color.TRANSPARENT}
                ,stops,
                Shader.TileMode.CLAMP);
        mPaint.setShader(mShader);
        canvas.drawCircle(w/2,
                h/2,
                w/2,mPaint);
    }
}
