package com.example.parktaeim.wordmaster.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.parktaeim.wordmaster.R;
/**
 * Created by parktaeim on 2017. 8. 22..
 */

public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //툴바
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("");  //툴바 제목 설정 (제거)

        ImageView back_icon = (ImageView) findViewById(R.id.back_icon);
        //뒤로가기 버튼 누르면
        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();  //현재 화면 꺼지면서 전화면으로 돌아감
            }
        });

    }
}
