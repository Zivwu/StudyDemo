package com.zivwu.arch;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

public class MyMigration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

        // DynamicRealm exposes an editable schema
        RealmSchema schema = realm.getSchema();

        // Migrate to version 1: Add a new class.
        // Example:
        // public Person extends RealmObject {
        //     private String name;
        //     private int age;
        //     // getters and setters left out for brevity
        // }
        if (oldVersion == 0) {
            schema.create("Person")
                    .addField("name", String.class)
                    .addField("address", String.class)
                    .addField("age", int.class);
            oldVersion++;
        }




        if (oldVersion == 1) {
            schema.get("Student")
                    .addRealmObjectField("person", schema.get("Person"));
            oldVersion++;
        }
    }
}
