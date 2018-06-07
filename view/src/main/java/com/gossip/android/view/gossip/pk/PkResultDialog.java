package com.gossip.android.view.gossip.pk;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;

import com.gossip.android.view.R;

import butterknife.ButterKnife;

public class PkResultDialog extends Dialog {



    public PkResultDialog(@NonNull Context context) {
        super(context, R.style.MyDialog);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_pk_dialog_pk_result);
        ButterKnife.bind(this);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setCanceledOnTouchOutside(true);
    }


}

