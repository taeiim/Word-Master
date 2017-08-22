package com.example.parktaeim.wordmaster.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.parktaeim.wordmaster.R;
import com.example.parktaeim.wordmaster.adapter.WordBookListAdapter;
import com.example.parktaeim.wordmaster.adapter.WordListAdapter;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private com.example.parktaeim.wordmaster.adapter.WordBookListAdapter wordBookListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
