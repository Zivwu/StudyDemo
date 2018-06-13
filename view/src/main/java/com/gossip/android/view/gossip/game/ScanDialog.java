package com.gossip.android.view.gossip.game;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.gossip.android.view.R;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ScanDialog extends Dialog {
    @BindView(R.id.sv_one)
    ScanAnimView svOne;
    @BindView(R.id.sv_two)
    ScanAnimView svTwo;
    @BindView(R.id.sv_three)
    ScanAnimView svThree;
    @BindView(R.id.btn_go)
    Button btnGo;
    @BindView(R.id.btn_scan)
    Button btnScan;
    private List<Integer> nums;
    private Disposable subscribe;

    public ScanDialog(@NonNull Context context,List<Integer> nums) {
        super(context, R.style.MyDialog);
        this.nums=nums;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_game_scan_dialog);
        ButterKnife.bind(this);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setCanceledOnTouchOutside(true);
        setExistNum(nums);

    }


    private void setExistNum(List<Integer> list){
        if (list.size()==3){
            svOne.setNum(list.get(0));
            svTwo.setNum(list.get(1));
            svThree.setNum(list.get(2));
        }

    }


    private void onScan(int one, final int two){
        svOne.setNum(one,true);
        subscribe = Observable.timer(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        svTwo.setNum(two,true);
                    }
                });

    }


    @OnClick({R.id.sv_three, R.id.btn_go, R.id.btn_scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sv_three:
                svThree.setNum(2,true);
                break;
            case R.id.btn_go:
                this.dismiss();
                break;
            case R.id.btn_scan:
                onScan(2,3);
                break;
        }
    }


    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (subscribe!=null&&!subscribe.isDisposed())
            subscribe.dispose();
    }
}
