package com.example.parktaeim.wordmaster.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.parktaeim.wordmaster.activity.MainActivity;

import java.util.ArrayList;

/**
 * Created by parktaeim on 2017. 8. 17..
 */

public class WordBookListAdapter extends BaseAdapter{

    private Context mContext;

    public WordBookListAdapter(Context mContect) {
        super();
        this.mContext= mContext;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
