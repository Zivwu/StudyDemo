package com.gossip.android.view.gossip.pk;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.gossip.android.view.R;
import com.gossip.android.view.gossip.game.ScanAnimView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PkActivity extends AppCompatActivity {

    @BindView(R.id.fabtn_pk)
    ImageButton fabtnPk;
    @BindView(R.id.scanView)
    ScanAnimView scanView;

    @BindView(R.id.iv_image)
    ImageView imgView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_pk_activity);
        ButterKnife.bind(this);
        testRxjava();

    }

    private void testRxjava() {
        Observable.just(123)
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        return getOb();
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String o) {
                        Toast.makeText(getApplicationContext(), o,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    Observable<String> getOb() {
        return Observable.just("123","124")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    @OnClick(R.id.fabtn_pk)
    public void onViewClicked() {
        startTarot2();

    }

    private void startTarot2() {
        TarotView viewById = findViewById(R.id.tarot);
        viewById.startAnim();

    }


    private void startTarot(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(imgView, "rotationY", 0, 90)
                .setDuration(2000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                imgView.setImageResource(R.mipmap.room_game_tarot_21);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imgView, "rotationY", 90, 180)
                .setDuration(2000);
        animator.setInterpolator(new LinearInterpolator());

        AnimatorSet set =new AnimatorSet();
        set.playSequentially(animator,animator2);
        set.start();
    }
}
