package com.example.parktaeim.wordmaster.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.parktaeim.wordmaster.R;
import com.example.parktaeim.wordmaster.adapter.WordBookRealmAdapter;
import com.example.parktaeim.wordmaster.model.WordBook;

import io.realm.Realm;
import butterknife.ButterKnife;
import io.realm.RealmResults;
import io.realm.Sort;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private String title;
    private String describe;

    Dialog bookAddDialog;
    private Realm realm;
    WordBookRealmAdapter wordBookRealmAdapter;
    RealmResults<WordBook> wordBooksItems;
    RecyclerView realmRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Realm.init(this);
        realm = Realm.getDefaultInstance();

        realmRecyclerView = (RecyclerView) findViewById(R.id.WordBookRecyclerView);

        registerForContextMenu(realmRecyclerView);
        setUpRecyclerView();

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
                Intent intent = new Intent(MainActivity.this, WordsActivity.class);
                startActivity(intent);
            }
        });


    }


    private void setUpRecyclerView() {
        wordBookRealmAdapter = new WordBookRealmAdapter(realm.where(WordBook.class).findAllSorted("list_id", Sort.DESCENDING));
        realmRecyclerView.setAdapter(wordBookRealmAdapter);
        realmRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        realmRecyclerView.setHasFixedSize(true);
        wordBookRealmAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void setToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("");  //툴바 제목 설정 (제거)
    }


    //단어장 추가 다이얼로그
    private void ShowDialog() {
        final LayoutInflater dialog = LayoutInflater.from(this);
        final View dialogLayout = dialog.inflate(R.layout.word_book_dialog, null);
        bookAddDialog = new Dialog(this);

        bookAddDialog.setContentView(dialogLayout);
        bookAddDialog.show();
        bookAddDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btn_add_wordBook = (Button) dialogLayout.findViewById(R.id.btn_add_wordBook);
        Button btn_cancel = (Button) dialogLayout.findViewById(R.id.btn_cancel);
        final EditText titleEditText = (EditText) dialogLayout.findViewById(R.id.titleEditText);
        final EditText descEditText = (EditText) dialogLayout.findViewById(R.id.descEditText);


        //추가 버튼 클릭했을 때
        btn_add_wordBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addWordBookItem(titleEditText.getText().toString(), descEditText.getText().toString());

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


    private void addWordBookItem(String title, String describe) {
        if (title == null || title.length() == 0 || describe == null || describe.length() == 0) {
            Toast.makeText(MainActivity.this, "다시 작성해주세요!", Toast.LENGTH_SHORT).show();
            return;
        }

        wordBooksItems = realm.where(WordBook.class).findAll();

        System.out.println(wordBooksItems.size());

        realm.beginTransaction();
        WordBook wordBook = realm.createObject(WordBook.class, System.currentTimeMillis());
        wordBook.setTitle(title);
        wordBook.setDesc(describe);
        realm.commitTransaction();
        bookAddDialog.dismiss();

        System.out.println(wordBooksItems.size());

    }
}
