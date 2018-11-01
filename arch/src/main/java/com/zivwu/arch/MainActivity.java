package com.zivwu.arch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

public class MainActivity extends AppCompatActivity {

    private StudentAdapter adapter;
    private Realm realm;
    private RecyclerView recyclerView;
    private RealmResults<Student> allAsync;
    private RealmResults<Student> totle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRvList();

        initRealm();
    }

    private void initRealm() {
        realm = Realm.getDefaultInstance();
        totle = realm.where(Student.class)
                .sort("time", Sort.ASCENDING)
                .findAllAsync();
        allAsync = realm.where(Student.class)
                .sort("time", Sort.DESCENDING)
                .limit(20)
                .findAllAsync()
                .sort("time", Sort.ASCENDING);

        totle.addChangeListener(new RealmChangeListener<RealmResults<Student>>() {
            @Override
            public void onChange(RealmResults<Student> students) {
//                int i=10;
//                while (i<students.size()){
//                    students.deleteFirstFromRealm();
//                }
            }
        });

        allAsync.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<Student>>() {
            @Override
            public void onChange(RealmResults<Student> students,
                                 OrderedCollectionChangeSet changeSet) {
                if (changeSet==null) {
                    adapter.notifyDataSetChanged();
                    return;
                }
//                // For deletions, the adapter has to be notified in reverse order.
                boolean bottom = isBottom(recyclerView);
                OrderedCollectionChangeSet.Range[] deletions = changeSet.getDeletionRanges();
                for (int i = deletions.length - 1; i >= 0; i--) {
                    OrderedCollectionChangeSet.Range range = deletions[i];
                    adapter.notifyItemRangeRemoved(range.startIndex, range.length);
                }

                OrderedCollectionChangeSet.Range[] insertions = changeSet.getInsertionRanges();
                for (OrderedCollectionChangeSet.Range range : insertions) {
                    adapter.notifyItemRangeInserted(range.startIndex, range.length);
                }

                OrderedCollectionChangeSet.Range[] modifications = changeSet.getChangeRanges();
                for (OrderedCollectionChangeSet.Range range : modifications) {
                    adapter.notifyItemRangeChanged(range.startIndex, range.length);
                }

                if (bottom)
                    recyclerView.scrollToPosition(adapter.getData().size()-1);
            }
        });
        adapter.setNewData(allAsync);

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
                if (allAsync.isLoaded()) {
                    allAsync.deleteFromRealm(0);
                }
            }
        });
    }

    public void change(View view) {


    }


    public boolean isBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }

    public void add(View view) {
        final Student object = new Student();
        object.setId(String.valueOf(i));
        object.setName("李斯" + i);
        object.setTime(i);
        Person person = new Person();
        person.setAddress("地址在家里" + i);
        object.setPerson(person);
        i++;
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                realm.copyToRealmOrUpdate(object);
            }
        });
    }
}
