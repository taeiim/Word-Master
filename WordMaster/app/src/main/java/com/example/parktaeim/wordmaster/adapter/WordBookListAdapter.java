package com.example.parktaeim.wordmaster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.parktaeim.wordmaster.R;

import com.example.parktaeim.wordmaster.activity.MainActivity;
import com.example.parktaeim.wordmaster.model.WordListItem;
import com.example.parktaeim.wordmaster.model.WordsItem;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by parktaeim on 2017. 8. 17..
 */

public class WordBookListAdapter extends BaseAdapter{
    private ArrayList<WordListItem> wordBookItems = new ArrayList<>();

    private Context mContext;

    public WordBookListAdapter() {

    }

    @Override
    public int getCount() {
        return wordBookItems.size();
    }

    @Override
    public Object getItem(int position) {
        return wordBookItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.wordsbookliist_custom,parent,false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.titleTextView);
        TextView describe = (TextView) convertView.findViewById(R.id.describeTextView);
        TextView wordCount = (TextView) convertView.findViewById(R.id.wordCountTextView);

        WordListItem wordListItem = wordBookItems.get(position);

        title.setText(wordListItem.getTitle());
        describe.setText(wordListItem.getDescribe());
        wordCount.setText(wordListItem.getWordCount());
        return convertView;

    }

    public void addItem(String title, String desc, int wordsCount){

        WordListItem item = new WordListItem();

        item.setTitle(title);
        item.setDescribe(desc);
        item.setWordCount(wordsCount);

        wordBookItems.add(item);
    }
}
