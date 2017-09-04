package com.example.parktaeim.wordmaster.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.parktaeim.wordmaster.R;
import com.example.parktaeim.wordmaster.model.WordBook;

import io.realm.Realm;
import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;

/**
 * Created by parktaeim on 2017. 9. 1..
 */

public class WordBookRealmAdapter extends RealmBasedRecyclerViewAdapter<WordBook,WordBookRealmAdapter.ViewHolder> {

    public class ViewHolder extends RealmViewHolder {

        public TextView titleTextView;
        public TextView descTextView;

        public ViewHolder(CardView container) {
            super(container);
            this.titleTextView = (TextView) container.findViewById(R.id.titleTextView);
            this.descTextView = (TextView) container.findViewById(R.id.describeTextView);
        }
    }

    public WordBookRealmAdapter(
            Context context,
            RealmResults<WordBook> realmResults,
            boolean automaticUpdate,
            boolean animateResults) {
        super(context, realmResults, automaticUpdate, animateResults);
    }

    @Override
    public ViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int viewType) {
        View v = inflater.inflate(R.layout.wordsbookliist_custom, viewGroup, false);
        ViewHolder vh = new ViewHolder((CardView) v);
        return vh;
    }

    @Override
    public void onBindRealmViewHolder(ViewHolder viewHolder, int position) {
        final WordBook wordBookItem = realmResults.get(position);
        viewHolder.titleTextView.setText(wordBookItem.getTitle());
        viewHolder.descTextView.setText(wordBookItem.getDesc());
    }
}
