package com.gossip.android.view.gossip.pk;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.NumberPicker;

import com.gossip.android.view.R;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PkActivity extends AppCompatActivity {

    @BindView(R.id.fabtn_pk)
    ImageButton fabtnPk;
    @BindView(R.id.numpicker)
    NumberPicker numpicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_pk_activity);
        ButterKnife.bind(this);
        numpicker.setMaxValue(8);
        numpicker.setMinValue(1);
        numpicker.setValue(5);
        numpicker.setWrapSelectorWheel(false);
        showNumPicker();
    }

    private void showNumPicker() {
        final NumberPicker picker = new NumberPicker(this);
        picker.setMaxValue(8);
        picker.setMinValue(1);
        picker.setValue(5);
        picker.setWrapSelectorWheel(false);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择座位号")
                .setView(picker)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();


    }

    @OnClick(R.id.fabtn_pk)
    public void onViewClicked() {
        Random random = new Random();
        int i = random.nextInt(3);
        Dialog dialog;
        switch (i) {
            case 1:
                dialog = new PkResultDialog(this);
                dialog.show();
                break;
            case 0:
                dialog = new PkScoreDialog(this);
                dialog.show();
                break;
            case 2:
                dialog = new PkVoteDialog(this);
                dialog.show();
                break;
        }
    }
}
