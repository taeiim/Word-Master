package com.example.parktaeim.wordmaster.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.parktaeim.wordmaster.R;
import com.example.parktaeim.wordmaster.adapter.WordBookListAdapter;


import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Realm realm;

    private ListView listView;
    private WordBookListAdapter wordBookListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //checkRealm();

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.deleteRealm(realmConfiguration);
        Realm.setDefaultConfiguration(realmConfiguration);
        //init(); //데이터 초기화

         //툴바
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("");  //툴바 제목 설정 (제거)

        //wordBookListAdapter = new WordBookListAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,);
        listView = (ListView)findViewById(R.id.WordBookListView);

        //final WordBookListAdapter wordBookListAdapter = new WordBookListAdapter();


        Button button = (Button) findViewById(R.id.intentBtn);
        ImageView plus_icon = (ImageView) findViewById(R.id.plus_icon);

        //+ 버튼 클릭시 단어장추가 다이얼로그
        plus_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialog();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,WordsActivity.class);
                startActivity(intent);
            }
        });

        //wordBookListAdapter.addItem("제목","ㅇ니ㅏㅇㄹㄴㅣㄹㅇ라ㅣ",98);


        listView.setAdapter(wordBookListAdapter);

    }


    //단어장 추가 다이얼로그
    private void ShowDialog(){
        LayoutInflater dialog = LayoutInflater.from(this);
        final View dialogLayout = dialog.inflate(R.layout.word_book_dialog,null);
        final Dialog bookAddDialog = new Dialog(this);

        bookAddDialog.setContentView(dialogLayout);
        bookAddDialog.show();
        bookAddDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btn_add = (Button) dialogLayout.findViewById(R.id.btn_add);
        Button btn_cancel = (Button) dialogLayout.findViewById(R.id.btn_cancel);

        //추가 버튼 클릭했을 때
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //취소 버튼 클릭했을 때 다이얼로그 꺼짐
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookAddDialog.cancel();
            }
        });


    }

    //김나연
//    private Realm mRealm;
//
//    public void checkRealm(){
//        mRealm = Realm.getDefaultInstance();
//
//        RealmResults<WordListItem>  listItems = mRealm.where(WordListItem.class).findAll();
//
//
//        Log.i(MainActivity.class.getSimpleName(), " >>>> Before Insert Table Size : " + num);
//
//
//        mRealm.beginTransaction();
//        WordListItem item = mRealm.createObject(WordListItem.class);
//        item.setID(01);
//        item.setTitle("parktaeimbabo");
//        item.setDescribe("taeim is babo yeah!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//        item.setWordCount(100);
//        mRealm.cancelTransaction();
//        mRealm.commitTransaction();
//
//        num = mRealm.where(WordListItem.class).findAll().size();
//
//        Log.i(MainActivity.class.getSimpleName(), " >>>> After Insert Table Size : " + num);
//
//
//    }





    //나
//    //데이터 초기화
//    private void init(){
//        Context context ;
//        RealmConfiguration config = new RealmConfiguration.Builder(context).build();
//        realm = Realm.getInstance(config);
//
//        realm = Realm.getInstance(this);
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
