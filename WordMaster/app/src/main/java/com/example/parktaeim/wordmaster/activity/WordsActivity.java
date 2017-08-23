package com.example.parktaeim.wordmaster.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.parktaeim.wordmaster.R;
/**
 * Created by parktaeim on 2017. 8. 22..
 */

public class WordsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

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


        ImageView plus_icon = (ImageView) findViewById(R.id.plus_icon);

        //+ 버튼 클릭시 단어장추가 다이얼로그
        plus_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialog();
            }
        });
    }

    //단어장 추가 다이얼로그
    private void ShowDialog(){
        LayoutInflater dialog = LayoutInflater.from(this);
        final View dialogLayout = dialog.inflate(R.layout.words_dialog,null);
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

}
