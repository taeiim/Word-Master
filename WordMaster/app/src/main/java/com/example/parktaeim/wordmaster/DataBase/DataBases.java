package com.example.parktaeim.wordmaster.DataBase;

import android.provider.BaseColumns;

/**
 * Created by parktaeim on 2017. 8. 22..
 */

public class DataBases {
    public static final class CreateDB implements BaseColumns{
        public static final String LIST_TITLE = "list title";
        public static final String LIST_DESCRIBE = "list describe";


        public static final String WORDCOUNT = "word count";
        public static final String WORD_ENGLISH = "english word";
        public static final String WORD_KOREAN = "korean word";

    }
}
