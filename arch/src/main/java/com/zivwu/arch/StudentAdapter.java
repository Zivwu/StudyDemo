package com.zivwu.arch;

import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class StudentAdapter extends BaseQuickAdapter<Student, BaseViewHolder> {
    public StudentAdapter(@Nullable List<Student> data) {
        super(R.layout.item_demo, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Student item) {
        helper.setText(R.id.tv_id, item.getPerson().getAddress());
        helper.setText(R.id.tv_name, item.getName());
        ViewCompat.setTransitionName(helper.getView(R.id.tv_id),
                String.valueOf(getData().indexOf(item)) + "_one");

    }
}
