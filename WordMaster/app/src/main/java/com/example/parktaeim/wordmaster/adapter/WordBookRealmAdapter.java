package com.example.parktaeim.wordmaster.adapter;

import android.content.ClipData;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parktaeim.wordmaster.R;
import com.example.parktaeim.wordmaster.activity.MainActivity;
import com.example.parktaeim.wordmaster.model.Word;
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

import static android.media.CamcorderProfile.get;

/**
 * Created by parktaeim on 2017. 9. 1..
 */

public class WordBookRealmAdapter extends RealmRecyclerViewAdapter<WordBook, WordBookRealmAdapter.ViewHolder> {

    Context context;
    Realm realm;
    protected OrderedRealmCollection<WordBook> items;
    private RecyclerView mAttechedRecyclerView;
    long ID;

    public class ViewHolder extends RealmViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        public CardView cardView;
        public TextView titleTextView;
        public TextView descTextView;

        int position;

        public ViewHolder(CardView container) {
            super(container);
            this.cardView = (CardView) container.findViewById(R.id.wordBookCardView);
            this.titleTextView = (TextView) container.findViewById(R.id.titleTextView);
            this.descTextView = (TextView) container.findViewById(R.id.describeTextView);
            container.setOnCreateContextMenuListener(this);

            cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    position = getLayoutPosition();
                    return false;
                }
            });
        }


        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            MenuItem menuItem = contextMenu.add("삭제");
            menuItem.setOnMenuItemClickListener(this);

        }

        public void delete() {
            final WordBook wordBookItem = items.get(position);
            ID = wordBookItem.getList_id();
            realm = Realm.getDefaultInstance();
            final RealmResults<WordBook> results = realm.where(WordBook.class).equalTo("list_id", ID).findAll();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    results.deleteAllFromRealm();
                }
            });
            notifyDataSetChanged();
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            delete();
            return true;
        }

    }

    public WordBookRealmAdapter(OrderedRealmCollection<WordBook> data) {
        super(data, true);
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.wordsbookliist_custom, mAttechedRecyclerView, false);
        ViewHolder vh = new ViewHolder((CardView) v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final WordBook wordBookItem = items.get(position);
        holder.titleTextView.setText(wordBookItem.getTitle());
        holder.descTextView.setText(wordBookItem.getDesc());
        holder.cardView.setLongClickable(true);

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
