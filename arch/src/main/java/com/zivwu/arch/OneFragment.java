package com.zivwu.arch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.AutoTransition;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class OneFragment extends Fragment {
    private StudentAdapter adapter;
    private Realm realm;
    private RecyclerView recyclerView;
    private RealmResults<Student> allAsync;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main,container,false);
    }
    private void initRealm() {
        realm = Realm.getDefaultInstance();

        allAsync = realm.where(Student.class)
                .sort("time", Sort.DESCENDING)
                .limit(20)
                .findAllAsync()
                .sort("time", Sort.ASCENDING);


        allAsync.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<Student>>() {
            @Override
            public void onChange(RealmResults<Student> students,
                                 OrderedCollectionChangeSet changeSet) {
                if (changeSet==null) {
                    adapter.notifyDataSetChanged();
                    return;
                }
//                // For deletions, the adapter has to be notified in reverse order.

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

                     }
        });
        adapter.setNewData(allAsync);

    }

    @Override
    public void onStart() {
        super.onStart();
        initRvList();
        initRealm();
    }

    private void initRvList() {
        recyclerView = (RecyclerView)getView(). findViewById(R.id.rv_list);
//设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//设置adapter
        adapter = new StudentAdapter(null);
        recyclerView.setAdapter(adapter);
//设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.VERTICAL));
        adapter.bindToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                View viewById=   adapter.getViewByPosition(position,R.id.tv_id);
                View viewById2 = view.findViewById(R.id.tv_id);
                BlankFragment blankFragment = new BlankFragment();
                blankFragment.setEnterTransition(new AutoTransition());
                getFragmentManager().beginTransaction()
                        .addSharedElement(viewById2,"one")
                        .replace(R.id.fl_fragment, blankFragment)
                        .commit();
//                Bundle shareBundle = getShareBundle(getActivity(), viewById2);
//
//                startActivity(new Intent(getContext(),CommunityActivity.class),shareBundle);
            }
        });


    }

    @SuppressWarnings("unchecked")
    public static Bundle getShareBundle(Activity activity, View... views) {
        Pair<View, String>[] pairs = getPairs(views);
        return ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairs).toBundle();
    }


    public static Pair<View, String>[] getPairs(View... views) {
        Pair<View, String>[] pairs = null;
        if (views != null) {
            pairs = new Pair[views.length];
            for (int i = 0; i < views.length; i++) {
                View view = views[i];
                pairs[i] = new Pair<View, String>(view, ViewCompat.getTransitionName(view));
            }
        }
        return pairs;
    }

    private int i=0;
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
