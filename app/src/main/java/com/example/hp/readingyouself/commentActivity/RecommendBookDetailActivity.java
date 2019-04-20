package com.example.hp.readingyouself.commentActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.readingyouself.BaseActivity;
import com.example.hp.readingyouself.R;
import com.example.hp.readingyouself.commentActivity.commentBean.BookCommentReviewBean;
import com.example.hp.readingyouself.commentActivity.commentBean.BookRecommendBean;
import com.example.hp.readingyouself.readActivity.BookIntroductionActivity;
import com.example.hp.readingyouself.readingDataSupport.DataConnector;

import java.util.HashMap;

public class RecommendBookDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_book_detail);
        initWidget();
    }

    public final static String HELP_ID = "review_id";


    private ImageView portrait;
    private TextView nickName;
    private TextView content;

    private void initWidget(){
        portrait = findViewById(R.id.help_detail_portrait);
        nickName = findViewById(R.id.help_detail_author_name);
        content = findViewById(R.id.help_detail_content);
    };

    private String helpId;
    private DataConnector dataConnector;
    private BookRecommendBean bookRecommendBean;

    @Override
    protected void initBeforeBindServiceOnCreated() {
        helpId = getIntent().getStringExtra(HELP_ID);
    }

    @Override
    protected void onHandleMessage(Message msg) {
        if(msg.obj instanceof BookRecommendBean){
            bookRecommendBean = (BookRecommendBean) msg.obj;
            nickName.setText(bookRecommendBean.getHelp().getAuthor().getNickname());
            content.setText(bookRecommendBean.getHelp().getContent());
            String[] avatar = new String[1];
            avatar[0] = bookRecommendBean.getHelp().getAuthor().getAvatar();
            dataConnector.sendAuthorPortrait(avatar);
        }
        if(msg.obj instanceof HashMap){
            portrait.setImageBitmap(((HashMap<String,Bitmap>)msg.obj).get(bookRecommendBean.getHelp().getAuthor().getAvatar()));
        }
    }

    @Override
    protected void onDataServiceConnect() {
        dataConnector = getDataConnector();
        dataConnector.sendBookRecommendBean(helpId);
    }

    @Override
    protected void onDataServiceDisconnect() {
        dataConnector = null;
    }

}
