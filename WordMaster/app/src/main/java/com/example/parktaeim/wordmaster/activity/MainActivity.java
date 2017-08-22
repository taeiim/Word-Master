package com.example.parktaeim.wordmaster.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.parktaeim.wordmaster.R;
import com.example.parktaeim.wordmaster.Realm.List;
import com.example.parktaeim.wordmaster.adapter.WordBookListAdapter;
import com.example.parktaeim.wordmaster.adapter.WordListAdapter;


import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Realm realm;
    private ListView listView;
    private com.example.parktaeim.wordmaster.adapter.WordBookListAdapter wordBookListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init(); //데이터 초기화

        //툴바
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("");  //툴바 제목 설정 (제거)

        listView = (ListView)findViewById(R.id.WordBookListView);

        wordBookListAdapter = new WordBookListAdapter(this);
        listView.setAdapter(wordBookListAdapter);

        Button button = (Button) findViewById(R.id.intentBtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,WordsActivity.class);
                startActivity(intent);
            }
        });



    }

//    //데이터 초기화
//    private void init(){
////        Context context ;
//        //RealmConfiguration config = new RealmConfiguration.Builder(context).build();
//        ///realm = Realm.getInstance(config);
//
//        //realm = Realm.getInstance(this);
//
//        RealmResults<List> wordBookList = getUserList();
//       Log.i(TAG, ">>>>> wordBookList.size : " + wordBookList.size());
//
//        insertWordBookData();
//        Log.i(TAG, ">>>>> wordBookList.size : " + wordBookList.size());
//
//    }
//
//    //단어장 정보 데이터 리스트 반환
//    private RealmResults<List> getUserList(){
//        return realm.where(List.class).findAll();
//    }
//
//    private void insertWordBookData(){
//
//        realm.beginTransaction();
//        List user = realm.createObject(List.class);
//        user.setTitle("왕초보 단어장");
//        user.setDescribe("완전열심히 해서 오늘안에 끝내기");
//        realm.commitTransaction();
//    }
}
