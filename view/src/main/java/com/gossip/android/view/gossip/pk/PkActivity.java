package com.gossip.android.view.gossip.pk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.gossip.android.view.R;
import com.gossip.android.view.gossip.game.ScanAnimView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        scanView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                scanView.setNum(9,false);
                scanView.setNum(9,3);
            }
        });
    }


    @OnClick(R.id.fabtn_pk)
    public void onViewClicked() {
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
