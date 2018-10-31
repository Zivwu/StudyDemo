package com.zivwu.arch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

public class MainActivity extends AppCompatActivity {

    private StudentAdapter adapter;
    private Realm realm;
    private RecyclerView recyclerView;
    private RealmResults<Student> allAsync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRvList();

        initRealm();
    }

    private void initRealm() {
        realm = Realm.getDefaultInstance();
        allAsync = realm.where(Student.class)

                .findAllAsync()
                .sort("time",Sort.ASCENDING);
        allAsync.addChangeListener(new RealmChangeListener<RealmResults<Student>>() {
            @Override
            public void onChange(RealmResults<Student> students) {
                adapter.setNewData(students);
//                recyclerView.scrollToPosition(adapter.getData().size()-1);
            }
        });
        if (allAsync.isLoaded()){
            adapter.setNewData(allAsync);
        }
    }

    private void initRvList() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_list);
//设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//设置adapter
        adapter = new StudentAdapter(null);
        recyclerView.setAdapter(adapter);
//设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL));


        RecyclerView.Adapter adapters;


    }


    private int i = 0;

    public void delete(View view) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (allAsync.isLoaded()){
                    allAsync.deleteFromRealm(0);
                }
            }
        });
    }

    public void change(View view) {



    }

    public void add(View view) {
       final Student object = new Student();
        object.setId(String.valueOf(i));
        object.setName("李斯" + i);
        object.setTime(i);
        i++;
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                realm.copyToRealmOrUpdate(object);
            }
        });
    }
}
