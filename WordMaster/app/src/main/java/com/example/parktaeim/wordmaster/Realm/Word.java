package com.example.parktaeim.wordmaster.Realm;

import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;

/**
 * Created by parktaeim on 2017. 8. 22..
 */

public class Word extends RealmObject {
    public List list;

    private String list_id;

    private String english;

    private String korean;

    @PrimaryKey
    String PK = list_id.toString()+","+english.toString();


    public String getList_id() {
        return list_id;
    }

    public void setList_id(String list_id) {
        this.list_id = list_id;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getKorean() {
        return korean;
    }

    public void setKorean(String korean) {
        this.korean = korean;
    }

    public String getPK() {
        return PK;
    }

    public void setPK(String PK) {
        this.PK = PK;
    }

 //RealmResults<Word> word = realm.where(Word.class).distinct("list_id");

}
