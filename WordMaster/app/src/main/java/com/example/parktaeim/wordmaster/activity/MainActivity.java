package com.example.parktaeim.wordmaster.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.parktaeim.wordmaster.R;
import com.example.parktaeim.wordmaster.adapter.WordBookRealmAdapter;
import com.example.parktaeim.wordmaster.model.WordBook;
import com.getkeepsafe.relinker.ReLinker;

import java.util.Date;
import io.realm.Realm;
import butterknife.ButterKnife;
import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import io.realm.RealmResults;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private String title;
    private String describe;

    private Realm realm;

    RealmRecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Realm.init(getApplicationContext());
        realm = Realm.getDefaultInstance();

        //realm = Realm.getInstance(this);

//        RealmConfiguration config = new RealmConfiguration.Builder(getApplicationContext()).build();
//        realm = Realm.getInstance(config);

//        Realm.init(getApplicationContext());
//        RealmConfiguration realmConfig = new RealmConfiguration.Builder().name("realm").build();
//        realm = Realm.getInstance(realmConfig);

//        ReLinker.loadLibrary(getApplicationContext(),"mylibrary","1.0");

//        Realm.init(getApplicationContext());
//        realm = Realm.getDefaultInstance();
//

        RealmResults<WordBook> wordBooksItems = realm
                .where(WordBook.class)
                .findAllSorted("list_id");

        WordBookRealmAdapter wordBookRealmAdapter = new WordBookRealmAdapter(this,wordBooksItems,true,true);
        RealmRecyclerView realmRecyclerView = (RealmRecyclerView) findViewById(R.id.WordBookRecyclerView);
        realmRecyclerView.setAdapter(wordBookRealmAdapter);


        ButterKnife.bind(this);

        setToolbar();  //툴바 세팅



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


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void setToolbar() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("");  //툴바 제목 설정 (제거)
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
        final EditText titleEditText = (EditText) findViewById(R.id.titleEditText);
        final EditText descEditText = (EditText) findViewById(R.id.descEditText);


        //추가 버튼 클릭했을 때
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( titleEditText == null || descEditText == null||titleEditText.length() == 0 || descEditText.length() == 0 ){   //제목이나 요약 입력값이 없으면 다시 작성해달라는 토스트
                    Toast.makeText(MainActivity.this, "다시 작성해주세요!",Toast.LENGTH_SHORT).show();
                }else {
                    addWordBookItem(titleEditText.getText().toString(),descEditText.getText().toString());
                }
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


    private void addWordBookItem(String title, String describe){
        if (title == null || title.length() ==0 ||describe == null || describe.length() ==0 ){
            Toast.makeText(MainActivity.this, "다시 작성해주세요!",Toast.LENGTH_SHORT).show();
            return;
        }

        long now = System.currentTimeMillis();
        Date date = new Date(now);


        realm.beginTransaction();
        WordBook wordBook = realm.createObject(WordBook.class);
        wordBook.setList_id(System.currentTimeMillis());
        wordBook.setTitle(title);
        wordBook.setDesc(describe);
        realm.commitTransaction();
    }


}
