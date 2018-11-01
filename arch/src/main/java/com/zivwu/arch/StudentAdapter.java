package com.zivwu.arch;

import android.support.annotation.Nullable;

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
    }
}
