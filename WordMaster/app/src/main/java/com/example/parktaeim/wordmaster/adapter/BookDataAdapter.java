package com.example.parktaeim.wordmaster.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.parktaeim.wordmaster.Realm.List;
import com.example.parktaeim.wordmaster.R;

import butterknife.ButterKnife;

/**
 * Created by parktaeim on 2017. 8. 24..
 */

public class BookDataAdapter extends RealmRecyclerViewAdapter<List> {

    private Context mContext;
    private int lastPosition = -1;
    private UbahDataInterface mUbahDataInterface;
    private HapusDataInterface mHapusDataInterface;

    public BookDataAdapter(Context context){
        this.mContext = context;
    }

    public void ubahData(UbahDataInterface ubahDataInterface){
        this.mUbahDataInterface = ubahDataInterface;
    }

    public void hapusData(HapusDataInterface ubahDataInterface){
        this.mHapusDataInterface = ubahDataInterface;
    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout. ,parent,false);

        return new DataHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        DataHolder holder = (DataHolder) viewHolder;
        List listItem = getItem(position);

        holder.title.setText(listItem.getTitle());
        holder.describe.setText(listItem.getDescribe());
        //holder.wordCount.setText(listItem.get());

        setAnimation(holder.cardView,position);
    }

    @Override
    public int getItemCount() {
        if(getRealmAdapter() != null){
            return getRealmAdapter().getCount();
        }
        return 0;
    }

    public int UbahDataInterface{
        void ubahData(View view, int position);
    }

    public int HapusDataInterface{
        void hapusData(View view, int position);

    }

    class DataHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.titleTextView) TextView title;
        @Bind(R.id.describeTextView) TextView describe;
        @Bind(R.id.wordCountTextView) TextView wordCount;
        @Bind(R.id.) CardView cardView;


        public DataHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    private void setAnimation(View viewToAnimate, int position){
        if(position > lastPosition){
            Animation animation = AnimationUtils.loadAnimation(mContext,R.anim.slide_up_in);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

}
