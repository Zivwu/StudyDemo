package com.zivwu.arch;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class Person
        extends RealmObject
{

    private String address;
    private int age;
    private String name;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
