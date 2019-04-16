package com.example.hp.readingyouself.commentActivity;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hp.readingyouself.BaseActivity;
import com.example.hp.readingyouself.R;

public class BookCommentDetailActivity extends AppCompatActivity {

    public final static String REVIEW_ID = "review_id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_comment_detail);
    }


}
