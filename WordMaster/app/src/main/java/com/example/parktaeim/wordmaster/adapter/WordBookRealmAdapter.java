package com.example.parktaeim.wordmaster.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.parktaeim.wordmaster.R;
import com.example.parktaeim.wordmaster.model.WordBook;

import java.util.ArrayList;
import java.util.List;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmConfiguration;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;

/**
 * Created by parktaeim on 2017. 9. 1..
 */

public class WordBookRealmAdapter extends RealmRecyclerViewAdapter<WordBook,WordBookRealmAdapter.ViewHolder> {

    Context context;
    Realm realm;
    protected OrderedRealmCollection<WordBook> items;
    private RecyclerView mAttechedRecyclerView;

    public class ViewHolder extends RealmViewHolder {

        public CardView cardView;
        public TextView titleTextView;
        public TextView descTextView;

        public ViewHolder(CardView container) {
            super(container);
            this.cardView = (CardView) container.findViewById(R.id.wordBookCardView);
            this.titleTextView = (TextView) container.findViewById(R.id.titleTextView);
            this.descTextView = (TextView) container.findViewById(R.id.describeTextView);
        }
    }


    public WordBookRealmAdapter(OrderedRealmCollection<WordBook> data){
        super(data,true);
        this.items = data;
        setHasStableIds(true);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mAttechedRecyclerView = recyclerView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.wordsbookliist_custom,mAttechedRecyclerView,false);
        ViewHolder vh = new ViewHolder((CardView) v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final WordBook wordBookItem = items.get(position);
        holder.titleTextView.setText(wordBookItem.getTitle());
        holder.descTextView.setText(wordBookItem.getDesc());
    }


    @Override
    public long getItemId(int index) {
        return super.getItemId(index);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
