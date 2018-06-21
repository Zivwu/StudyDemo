package com.gossip.android.view.gossip.pk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.gossip.android.view.R;
import com.gossip.android.view.gossip.game.ScanAnimView;
import com.gossip.android.view.gossip.game.ScanDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PkActivity extends AppCompatActivity {

    @BindView(R.id.fabtn_pk)
    ImageButton fabtnPk;
    @BindView(R.id.scanView)
    ScanAnimView scanView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_pk_activity);
        ButterKnife.bind(this);
//        scanView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Random random = new Random();
////                scanView.setNum(9,false);
//                List<Integer> list =new ArrayList<>();
//                list.add(2);
//                list.add(3);
//                list.add(-1);
//
//                ScanDialog dialog =new ScanDialog(PkActivity.this,list);
//                dialog.show();
//            }
//        });\

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
        testRxjava();
//        Random random = new Random();
//        int i = random.nextInt(3);
//        Dialog dialog;
//        switch (i) {
//            case 1:
//                dialog = new PkResultDialog(this);
//                dialog.show();
//                break;
//            case 0:
//                dialog = new PkScoreDialog(this);
//                dialog.show();
//                break;
//            case 2:
//                dialog = new PkVoteDialog(this);
//                dialog.show();
//                break;
//        }
    }
}
