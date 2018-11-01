package com.zivwu.arch;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApp extends Application {

    public static RealmConfiguration otherConfig;

    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
    }


    private void initRealm() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("arch.realm")
//                .schemaVersion()
                .deleteRealmIfMigrationNeeded()
//                .migration(new MyMigration())
//                .inMemory()
                .build();
        Realm.setDefaultConfiguration(config);

    }


}
