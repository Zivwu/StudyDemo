package com.gossip.android.view.gossip.pk;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.gossip.android.view.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class PkScoreDialog extends Dialog {


    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.btn_vote)
    Button btnVote;
    Context context;

    public PkScoreDialog(@NonNull Context context) {
        super(context, R.style.MyDialog);
        this.context=context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_pk_dialog_pk_score);
        ButterKnife.bind(this);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setCanceledOnTouchOutside(true);
    }

    private void showNumPicker() {
        final NumberPicker picker = new NumberPicker(context);
        picker.setMaxValue(10);
        picker.setMinValue(0);
        picker.setValue(5);
        picker.setWrapSelectorWheel(false);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("请选择评分")
                .setView(picker)
                .setPositiveButton("确定", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvNum.setText(String.valueOf(picker.getValue()));
                    }
                })
                .setNegativeButton("取消", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();


    }


    @OnClick({R.id.ll_num_picker, R.id.btn_vote})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_num_picker:
                showNumPicker();
                break;
            case R.id.btn_vote:
                String score = tvNum.getText().toString();
                Observable.just(score)
                        .subscribeOn(Schedulers.io())
                        .map(new Function<String, Integer>() {
                            @Override
                            public Integer apply(String s) throws Exception {
                                return Integer.valueOf(s);
                            }
                        })
                        .filter(new Predicate<Integer>() {
                            @Override
                            public boolean test(Integer integer) throws Exception {
                                return integer>=0&&integer<=10;
                            }
                        });
                break;
        }
    }
}

