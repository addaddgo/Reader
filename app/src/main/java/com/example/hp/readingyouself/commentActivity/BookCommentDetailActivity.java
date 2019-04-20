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
import com.example.hp.readingyouself.readActivity.BookIntroductionActivity;
import com.example.hp.readingyouself.readingDataSupport.DataConnector;

import java.util.HashMap;

public class BookCommentDetailActivity extends BaseActivity {

    public final static String REVIEW_ID = "review_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_comment_detail);
        initWidget();
    }

    private ImageView portrait;
    private TextView nickName;
    private TextView content;
    private TextView startReading;

    private void initWidget(){
        portrait = findViewById(R.id.book_comment_detail_portrait);
        nickName = findViewById(R.id.book_comment_detail_author_name);
        content = findViewById(R.id.book_comment_detail_content);
        startReading = findViewById(R.id.book_comment_detail_start_reading);
        startReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bookCommentReviewBean != null) {
                    Intent intent = new Intent(getBaseContext(), BookIntroductionActivity.class);
                    intent.putExtra(BookIntroductionActivity.BOOK_ID,bookCommentReviewBean.getReview().getBook().getId());
                    startActivity(intent);
                }
            }
        });
    };

    private String reviewId;
    private DataConnector dataConnector;
    private BookCommentReviewBean bookCommentReviewBean;

    @Override
    protected void initBeforeBindServiceOnCreated() {
        reviewId = getIntent().getStringExtra(REVIEW_ID);
    }

    @Override
    protected void onHandleMessage(Message msg) {
        if(msg.obj instanceof BookCommentReviewBean){
            bookCommentReviewBean = (BookCommentReviewBean)msg.obj;
            nickName.setText(bookCommentReviewBean.getReview().getAuthor().getNickname());
            content.setText(bookCommentReviewBean.getReview().getContent());
            String startReadingString = getString(R.string.start_reading) + bookCommentReviewBean.getReview().getBook().getTitle();
            startReading.setText(startReadingString);
            String[] avatar = new String[1];
            avatar[0] = bookCommentReviewBean.getReview().getAuthor().getAvatar();
            dataConnector.sendAuthorPortrait(avatar);
        }
        if(msg.obj instanceof HashMap){
            portrait.setImageBitmap(((HashMap<String,Bitmap>)msg.obj).get(bookCommentReviewBean.getReview().getAuthor().getAvatar()));
        }
    }

    @Override
    protected void onDataServiceConnect() {
        dataConnector = getDataConnector();
        dataConnector.sendBookCommentReviewBean(reviewId);
    }

    @Override
    protected void onDataServiceDisconnect() {
        dataConnector = null;
    }

}
