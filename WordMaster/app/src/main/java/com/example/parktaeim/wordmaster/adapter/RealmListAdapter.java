package com.example.parktaeim.wordmaster.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import io.realm.RealmBaseAdapter;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by parktaeim on 2017. 8. 24..
 */

public class RealmListAdapter<T extends RealmObject> extends RealmBaseAdapter {


    public RealmListAdapter(Context context, RealmResults<T> realmResults, boolean automaticUpdate){
        super(context,realmResults,automaticUpdate);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
