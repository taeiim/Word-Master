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
import java.util.List;

import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Realm mRealm = null;
    private BookDataAdapter mAdapter;
    private List<com.example.parktaeim.wordmaster.Realm.List> mDataList = new ArrayList<>();
    private LinearLayoutManager mLayoutManager;
    private RealmResults<com.example.parktaeim.wordmaster.Realm.List> BookModelDb;
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

        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(config);


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

    @Override
    protected void onResume() {
        super.onResume();
        BookModelDb = mRealm.where(com.example.parktaeim.wordmaster.Realm.List.class).findAll();
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

        mAdapter.ubahData((view,position)->{
            dapatkanData(view,BookModelDb.get(position));
        });

        mAdapter.hapusData((view,position)->{
            menghapusData(position);

            mAdapter.notifyDataSetChanged();

            Toast.makeText(MainActivity.this,"Data berhasil dihapus", Toast.LENGTH_SHORT).show();
        });
    }

    private String dapatkanData(View view, BookModelDb bookModelDb ){
        String dateTime = "";
        Calendar c = Calendar.getInstance();
        System.out.println("Current Time = &gt; "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateTime = df.format(c.getTime());

        return dateTime;
    }

    private void menghapusData(int position){
        mRealm.beginTransaction();

        BookModelDb.remove(position);
        mRealm.commitTransaction();
    }

    private void ubahData(String id, String title, String nope){
        mRealm.beginTransaction();

        com.example.parktaeim.wordmaster.Realm.List list = mRealm.where(com.example.parktaeim.wordmaster.Realm.List.class).equalTo("")
    }
    private void tampilkanDialogTambah(){
        String titleBtn = "Simpan";
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);

        View itemView = inflater.inflate(R.layout.word_book_dialog,null);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setView(itemView);

        final EditText titleInput = (EditText) itemView.findViewById(R.id.titleEditText);
        final EditText descInput = (EditText) itemView.findViewById(R.id.descEditText);

        builder.setCancelable(false).setPositiveButton(titleBtn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(titleInput.length() == 0 || descInput.length() == 0){
                    Toast.makeText(MainActivity.this, "다시 작성해주세요 ", Toast.LENGTH_SHORT).show();
                }else{
                    title = titleInput.getText().toString();
                    describe = descInput.getText().toString();


                }
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
