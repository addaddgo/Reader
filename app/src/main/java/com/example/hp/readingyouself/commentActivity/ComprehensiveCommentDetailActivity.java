package com.example.hp.readingyouself.commentActivity;

import android.graphics.Bitmap;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.readingyouself.BaseActivity;
import com.example.hp.readingyouself.R;
import com.example.hp.readingyouself.commentActivity.commentBean.ComprehensiveCommentBean;

import java.util.HashMap;

public class ComprehensiveCommentDetailActivity extends BaseActivity {


    public final static String POST_ID = "id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initBeforeBindServiceOnCreated() {
        setContentView(R.layout.activity_comrehensive_comment_detail);
        postId = getIntent().getStringExtra(POST_ID);
        portrait = findViewById(R.id.comprehensive_book_comment_detail_portrait);
        nickName = findViewById(R.id.comprehensive_book_comment_detail_author_name);
        content = findViewById(R.id.comprehensive_book_comment_detail_content);
    }

    private String postId;
    private ImageView portrait;
    private TextView nickName;
    private TextView content;
    private ComprehensiveCommentBean comprehensiveCommentBean;

    @Override
    protected void onHandleMessage(Message msg) {
        if(msg.obj instanceof ComprehensiveCommentBean){
            comprehensiveCommentBean = (ComprehensiveCommentBean)msg.obj;
            nickName.setText(comprehensiveCommentBean.getPost().getAuthor().getNickname());
            content.setText(comprehensiveCommentBean.getPost().getContent());
            String[] avatar = new String[1];
            avatar[0] = comprehensiveCommentBean.getPost().getAuthor().getAvatar();
            getDataConnector().sendAuthorPortrait(avatar);
        }
        if(msg.obj instanceof HashMap){
            portrait.setImageBitmap(((HashMap<String,Bitmap>)msg.obj).get(comprehensiveCommentBean.getPost().getAuthor().getAvatar()));
        }
    }

    @Override
    protected void onDataServiceConnect() {
        getDataConnector().sendComprehensiveCommentBean(postId);
    }

    @Override
    protected void onDataServiceDisconnect() {
    }
}
