package com.example.parktaeim.wordmaster.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.parktaeim.wordmaster.adapter.BookDataAdapter;
import com.example.parktaeim.wordmaster.adapter.RealmBookDataAdapter;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Realm mRealm = null;
    private BookDataAdapter mAdapter;
    private List<com.example.parktaeim.wordmaster.Realm.BookList> mDataList = new ArrayList<>();
    private LinearLayoutManager mLayoutManager;
    private RealmResults<com.example.parktaeim.wordmaster.Realm.BookList> BookModelDb;
    private RealmBookDataAdapter realmBookDataAdapter;

    private String title;
    private String describe;

    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mRealm = Realm.getDefaultInstance();
        setToolbar();  //툴바 세팅
        setupRecyclerView();


        mRecyclerView = (RecyclerView) findViewById(R.id.WordBookRecyclerView);

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
                if(titleEditText.length() == 0 || descEditText.length() == 0){   //제목이나 요약 입력값이 없으면 다시 작성해달라는 토스트
                    Toast.makeText(MainActivity.this, "다시 작성해주세요!",Toast.LENGTH_SHORT).show();
                }else {
                    title = titleEditText.getText().toString();      // 제목 저장
                    describe = descEditText.getText().toString();    // 요약 저장
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

//    private void tampilkanDialogTambah(){
//        String titleBtn = "Simpan";
//        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
//
//        View itemView = inflater.inflate(R.layout.word_book_dialog,null);
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//
//        builder.setView(itemView);
//
//        final EditText titleInput = (EditText) itemView.findViewById(R.id.titleEditText);
//        final EditText descInput = (EditText) itemView.findViewById(R.id.descEditText);
//
//        builder.setCancelable(false).setPositiveButton(titleBtn, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                if(titleInput.length() == 0 || descInput.length() == 0){
//                    Toast.makeText(MainActivity.this, "다시 작성해주세요 ", Toast.LENGTH_SHORT).show();
//                }else{
//                    title = titleInput.getText().toString();
//                    describe = descInput.getText().toString();
//
//
//                }
//            }
//        });
//
//    }

    @Override
    protected void onResume() {
        super.onResume();
        BookModelDb = mRealm.where(com.example.parktaeim.wordmaster.Realm.BookList.class).findAll();
        realmBookDataAdapter = new RealmBookDataAdapter(MainActivity.this,BookModelDb,true);

        mAdapter.setRealmBaseAdapter(realmBookDataAdapter);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView(){
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(MainActivity.this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new BookDataAdapter(MainActivity.this);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.ubahData(new BookDataAdapter.UbahDataInterface(){
            @Override
            public void ubahData(View view, int position) {
                dapatkanData(view, BookModelDb.get(position));
            }
        });
        mAdapter.hapusData(new BookDataAdapter.HapusDataInterface(){
            @Override
            public void hapusData(View view, int position) {
                menghapusData(position);
                mAdapter.notifyDataSetChanged();

            }
        });


    }


    //데이터 가져오기
    private void dapatkanData(View view, com.example.parktaeim.wordmaster.Realm.BookList list){
        String id = list.getList_id();
        title = list.getTitle();
        describe = list.getDescribe();
    }

    //id 시간으로
    private String dapatkanId(){
        String dateTime = "";
        Calendar c = Calendar.getInstance();
        System.out.println("Current Time = &gt; "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateTime = df.format(c.getTime());

        return dateTime;
    }


    //삭제
    private void menghapusData(int position){
        mRealm.beginTransaction();

        BookModelDb.remove(position);
        mRealm.commitTransaction();
    }

    private void ubahData(String id, String title, String describe, String date){
        mRealm.beginTransaction();


        com.example.parktaeim.wordmaster.Realm.BookList dataModel = mRealm.where(com.example.parktaeim.wordmaster.Realm.BookList.class).equalTo("id",id).findFirst();

        dataModel.setTitle(title);
        dataModel.setDescribe(describe);
        dataModel.setCreatedAt(date);

        mRealm.commitTransaction();
    }


    //추가
    private void tambahData(){
        mRealm.beginTransaction();

        com.example.parktaeim.wordmaster.Realm.BookList list = mRealm.createObject(com.example.parktaeim.wordmaster.Realm.BookList.class);
        list.setList_id(dapatkanId());
        list.setTitle(title);
        list.setDescribe(describe);
        String date = list.getList_id().toString();
        list.setCreatedAt(date);

        mRealm.commitTransaction();
    }




}
