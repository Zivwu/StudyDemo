package com.gossip.android.view.gossip.pk;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gossip.android.view.R;
import com.gossip.android.view.widget.wave.WaveProgress;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PkVoteDialog extends Dialog {


    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.vp_progress)
    WaveProgress vpProgress;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.btn_vote)
    Button btnVote;
    @BindView(R.id.periscope)
    PeriscopeLayout periscope;

    public PkVoteDialog(@NonNull Context context) {
        super(context, R.style.MyDialog);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_pk_dialog_pk_vote);
        ButterKnife.bind(this);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setCanceledOnTouchOutside(true);
    }


    @OnClick({R.id.vp_progress, R.id.btn_vote})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.vp_progress:
                periscope.addHeart();
                vpProgress.setProgress(vpProgress.getProgress() + 0.1f);
                break;
            case R.id.btn_vote:
                break;
        }
    }
}

