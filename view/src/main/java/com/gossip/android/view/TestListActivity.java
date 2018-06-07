package com.gossip.android.view;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.gossip.android.view.gossip.pk.PkActivity;
import com.gossip.android.view.path.PathActivity;
import com.gossip.android.view.widget.paint.shader.ShaderActivity;
import com.gossip.android.view.widget.wave.WaveActivity;

import java.util.ArrayList;
import java.util.List;

public class TestListActivity extends ListActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<Class> adapter = new ClassListAdapter(this,
                android.R.layout.simple_expandable_list_item_1, getStringList());
        getListView().setAdapter(adapter);
    }


    private List<Class> getStringList() {
        List<Class> list = new ArrayList<>();
        list.add(WaveActivity.class);
        list.add(ShaderActivity.class);
        list.add(PathActivity.class);
        list.add(PkActivity.class);
        return list;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Object item = l.getAdapter().getItem(position);
        if (item instanceof Class) {
            Class c = (Class) item;
            startActivity(new Intent(this, c));
        }
        super.onListItemClick(l, v, position, id);
    }

    private class ClassListAdapter extends ArrayAdapter<Class> {

        private final int mResource;

        public ClassListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Class> objects) {
            super(context, resource, objects);
            mResource = resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = LayoutInflater.from(TestListActivity.this)
                    .inflate(mResource, parent, false);
            TextView text = (TextView) view.findViewById(android.R.id.text1);
            Class item = getItem(position);
            String name = item.getName();
            String substring = name.substring(name.lastIndexOf(".") + 1, name.length());
            text.setText(substring);
            return view;
        }
    }
}
