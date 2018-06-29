package com.gossip.android.view.rx;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.view.View;

import com.gossip.android.view.R;
import com.gossip.android.view.utils.Logger;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxActivity extends AppCompatActivity {
    private String TAG =this.getClass().getName();

    private Disposable show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        testShow();
    }

    private void testShow() {
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = RxActivity.this.getCacheDir();
                showUsableSpace(file);
                file = Environment.getExternalStorageDirectory();
                showUsableSpace(file);
            }
        });
    }

    private void showUsableSpace(File file) {
        long usableSpace = file.getUsableSpace();
        String acceseSpace = Formatter.formatFileSize(getApplicationContext(), usableSpace);
        Logger.printD(TAG,file.getAbsolutePath()+":"+acceseSpace);
    }

    private void onClick(View view){

        Observable.just(view)
                .subscribeOn(Schedulers.computation())
                .map(new Function<View, String>() {
                    @Override
                    public String apply(View view) throws Exception {
                        String s="1";
                        Logger.printD(TAG,s);
                        return s;
                    }
                })
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        return getTwo();
                    }
                })
                .compose(RxHelper.<String>enableViews())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        Logger.printD(TAG,"3");
                        return "3";
                    }
                })
                .compose(RxHelper.<String>io_main())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Logger.printD(TAG,"doOnSubscribe");
                        showProgress();
                    }
                })
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        Logger.printD(TAG,"doOnTerminate");
                        dis();
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public Observable<String> getTwo(){
        return Observable.just("2")
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Logger.printD(TAG,"doOnSubscribe   2");
                    }
                })
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        Logger.printD(TAG,"doOnTerminate   2");
                    }
                }).observeOn(AndroidSchedulers.mainThread());
    }



    private void showProgress(){
        if (show!=null)
            show.dispose();

        show = Observable.timer(2000, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Logger.printD(TAG,"showProgress");
                    }
                });
    }


    private void dis(){
        if (show!=null&&!show.isDisposed()){
            show.dispose();
        }
    }



}
