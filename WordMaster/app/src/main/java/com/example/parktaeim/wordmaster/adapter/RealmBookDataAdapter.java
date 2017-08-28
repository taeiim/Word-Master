package com.example.parktaeim.wordmaster.adapter;

import android.content.Context;

import com.example.parktaeim.wordmaster.Realm.List;

import io.realm.RealmResults;

/**
 * Created by parktaeim on 2017. 8. 24..
 */

public class RealmBookDataAdapter extends RealmListAdapter<List> {
    public RealmBookDataAdapter(Context context, RealmResults<List> realmResults,boolean automaticUpdate){
        super(context,realmResults,automaticUpdate);
    }
}
