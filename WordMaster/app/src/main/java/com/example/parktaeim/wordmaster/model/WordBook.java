package com.example.parktaeim.wordmaster.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by parktaeim on 2017. 9. 1..
 */

public class WordBook extends RealmObject {

    private String title;
    private String desc;

    @PrimaryKey
    private long list_id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getList_id() {
        return list_id;
    }

    public void setList_id(long list_id) {
        this.list_id = list_id;
    }
}
