package com.gossip.android.view.widget.wave;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BaseInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.SeekBar;

import com.gossip.android.view.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

import static java.security.AccessController.getContext;

public class WaveActivity extends AppCompatActivity {

    @BindView(R.id.wave_progress)
    WaveProgress waveProgress;
    @BindView(R.id.seek_bar)
    AppCompatSeekBar seekBar;
    @BindView(R.id.aperture)
    WaveView aperture;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_activity_wave);
        ButterKnife.bind(this);
//        initA();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                waveProgress.setProgress(progress / 100f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        Observable.interval(150, TimeUnit.MICROSECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        aperture.start();
                    }
                });
    }


    private void initA() {
        aperture.setVisibility(View.GONE);
        aperture.setDuration(2000);
//        aperture.setStrokeWidth(200);

//        aperture.setInterpolator(new Interpolator() {
//            @Override
//            public float getInterpolation(float input) {
//                float result = test_num_0;
//                if (input < test_num_0.75)
//                    return result;
//                result = (1f - test_num_0.75f) * 4;
//                return result;
//            }
//        });
        aperture.setColor(Color.parseColor("#e416e7"));
        aperture.setInitialRadius(0);
    }


}
