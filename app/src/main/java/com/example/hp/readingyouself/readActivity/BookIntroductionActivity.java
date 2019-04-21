package com.example.hp.readingyouself.readActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.readingyouself.BaseActivity;
import com.example.hp.readingyouself.R;
import com.example.hp.readingyouself.commentActivity.commentBean.BookCommentListBean;
import com.example.hp.readingyouself.readingDataSupport.DataConnector;
import com.example.hp.readingyouself.readingDataSupport.dataForm.BookInformation;
import com.example.hp.readingyouself.readingDataSupport.dataForm.BookInformationBean;
import com.example.hp.readingyouself.readingDataSupport.dataForm.BookShelfBook;
import com.example.hp.readingyouself.readingDataSupport.dataForm.TheBookCommentListBean;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;

public class BookIntroductionActivity extends BaseActivity {

    public static final String BOOK_ID = "book_id";
    public static final String CHAPTER_POSITION = "chapter_position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void initBeforeBindServiceOnCreated() {
        Intent intent = getIntent();
        bookId = intent.getStringExtra(BOOK_ID);
        chapterPosition = intent.getIntExtra(CHAPTER_POSITION,0);
        setContentView(R.layout.activity_book_introduction);
        initWidget();
    }



    @Override
    protected void onHandleMessage(Message msg) {
        if(msg.obj instanceof  BookInformationBean){
            bookInformationBean = (BookInformationBean) msg.obj;
            String[] agent = new String[]{bookInformationBean.getCover()};
            digestTextView.setText(buildDigest(bookInformationBean));
            followerNumberTextView.setText(String.valueOf(bookInformationBean.getLatelyFollower()));
            saverPercentTextView.setText(String.valueOf(bookInformationBean.getRetentionRatio()));
            wordPerDayTextView.setText(String.valueOf(bookInformationBean.getRetentionRatio()));
            introductionTextView.setText(bookInformationBean.getLongIntro());
            dataConnector.sendCovers(agent);
            dataConnector.sendTheCommentListBeanByBookId(bookId,0,20);
        }
        if(msg.obj instanceof HashMap){
            if(msg.what == DataConnector.MESSAGE_WHAT_COVER){
                coverBitmap = ((HashMap<String,Bitmap>)msg.obj).get(bookInformationBean.getCover());
                if(coverBitmap != null)coverImageView.setImageBitmap(coverBitmap);
            }
            if(msg.what == DataConnector.MESSAGE_WHAT_PORTRAIT){
                commentAuthorPortrait = ((HashMap<String,Bitmap>)msg.obj);
                adapter.notifyDataSetChanged();
            }

        }
        if(msg.obj instanceof TheBookCommentListBean){
            theBookCommentListBean = (TheBookCommentListBean)msg.obj;
            String[] avatars = new String[theBookCommentListBean.getReviews().size()];
            for (int i = 0;i < avatars.length ;i++) {
                avatars[i] = theBookCommentListBean.getReviews().get(i).getAuthor().getAvatar();
            }
            dataConnector.sendAuthorPortrait(avatars);
        }
        if(msg.obj instanceof Boolean){
            if((Boolean)msg.obj){
                Toast.makeText(this,R.string.download_successful,Toast.LENGTH_SHORT).show();
                this.isDownload = true;
                this.downloadButton.setText(R.string.book_shelf_already_download);
            }else{
                Toast.makeText(this,R.string.download_unsuccessful,Toast.LENGTH_SHORT).show();
                this.downloadButton.setClickable(true);
                this.downloadButton.setText(R.string.download);
            }
        }
    }

    @Override
    protected void onDataServiceConnect() {
        dataConnector = getDataConnector();
        dataConnector.sendBookInformationBean(bookId);
        theBookIsCollectAndIsDownload();
    }

    @Override
    protected void onDataServiceDisconnect() {
        dataConnector = null;
    }

    //数据处理
    private String digest;
    private String bookId;
    private int chapterPosition;
    private DataConnector dataConnector;
    private BookInformationBean bookInformationBean;
    private HashMap<String,Bitmap> commentAuthorPortrait;
    private TheBookCommentListBean theBookCommentListBean;

    //控件列表
    private Bitmap coverBitmap;
    private ImageView coverImageView;
    private TextView digestTextView;
    private TextView followerNumberTextView;
    private TextView saverPercentTextView;
    private TextView wordPerDayTextView;
    private TextView introductionTextView;
    private CommentAdapter adapter;

    private String buildDigest(BookInformationBean bookInformationBean){
        StringBuilder builder = new StringBuilder(bookInformationBean.getTitle() + "\n" + bookInformationBean.getAuthor() + " | " );
        if(bookInformationBean.getTags() != null && bookInformationBean.getTags().size() != 0){
            for (String tag:bookInformationBean.getTags()) {
                builder = builder.append(tag);
                builder = builder.append(" ");
            }
            builder = builder.append("\n");
            builder = builder.append(bookInformationBean.getLastChapter());
            builder = builder.append("\n");
            builder = builder.append(bookInformationBean.getMajorCate());
            builder = builder.append(bookInformationBean.getMinorCate());
            return builder.toString();
        }
        return null;
    }

    private Button collectButton;
    private Button downloadButton;

    //控件初始化
    private void initWidget() {
        coverImageView = findViewById(R.id.book_introduction_cover);
        digestTextView = findViewById(R.id.book_introduction_digest);
        followerNumberTextView = findViewById(R.id.book_introduction_follower_number);
        saverPercentTextView = findViewById(R.id.book_introduction_saver_percent_number);
        wordPerDayTextView = findViewById(R.id.book_introduction_word_per_number);
        introductionTextView = findViewById(R.id.book_introduction_long);
        RecyclerView commentRecyclerView = findViewById(R.id.book_introduction_hot_comment_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        commentRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CommentAdapter();
        commentRecyclerView.setAdapter(adapter);
        final Button startReading = findViewById(R.id.book_introduction_start_reading);
        collectButton = findViewById(R.id.book_introduction_collect);
        downloadButton = findViewById(R.id.book_introduction_download);

        startReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果上传有阅读的章节，就传入
                Intent intent = new Intent(getBaseContext(), ReadingViewActivity.class);
                intent.putExtra(ReadingViewActivity.BOOK_ID, bookId);
                intent.putExtra(ReadingViewActivity.CHAPTER_POSITION, chapterPosition);
                startActivity(intent);
            }
        });
        collectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCollected){
                    dataConnector.removeShelfBook(bookId);
                    collectButton.setText(getString(R.string.collect));
                }else{
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    coverBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    bookShelfBook = new BookShelfBook(bookInformationBean.getTitle(),bookInformationBean.get_id()
                            ,stream.toByteArray(),chapterPosition,false,true);
                    bookShelfBook.save();
                    collectButton.setText(R.string.collect);
                    isCollected = true;
                }
            }
        });

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dataConnector.downloadBook(bookId);
               downloadButton.setText(getString(R.string.downloading));
               downloadButton.setClickable(false);
            }
        });
    }

    BookShelfBook bookShelfBook;
    private boolean isCollected;
    private boolean isDownload;

    private void theBookIsCollectAndIsDownload(){
        List<BookShelfBook> bookShelfBooks = dataConnector.getBookShelfBooks();
        for (BookShelfBook shelfBook:bookShelfBooks) {
            if(shelfBook.getbookIdentify().equals(bookId)){
                isCollected = shelfBook.isCollect();
                isDownload = shelfBook.isDownload();
                this.bookShelfBook = shelfBook;
            }
        }
        if(isCollected){
            collectButton.setText(getString(R.string.book_shelf_already_collect));
        }
        if(isDownload){
            downloadButton.setText(getString(R.string.book_shelf_already_download));
            downloadButton.setClickable(false);
        }
    }

    //评论
    class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder>{


        @Override
        public void onBindViewHolder(@NonNull CommentAdapter.CommentHolder holder, int position) {
            TheBookCommentListBean.ReviewsBean reviewsBean = theBookCommentListBean.getReviews().get(position);
            holder.authorNameText.setText(reviewsBean.getAuthor().getNickname());
            holder.topicText.setText(reviewsBean.getContent());
            if(commentAuthorPortrait != null)holder.portraitImage.setImageBitmap(commentAuthorPortrait.get(reviewsBean.getAuthor().getAvatar()));
        }

        @NonNull
        @Override
        public CommentAdapter.CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.the_book_comment_item,parent,false);
            return new CommentHolder(view);
        }

        @Override
        public int getItemCount() {
            return theBookCommentListBean == null? 0:theBookCommentListBean.getReviews().size();
        }

        class CommentHolder extends RecyclerView.ViewHolder{
            ImageView portraitImage;
            TextView authorNameText;
            TextView topicText;
            CommentHolder(View view){
                super(view);
                portraitImage = view.findViewById(R.id.the_book_comment_author_portrait);
                authorNameText = view.findViewById(R.id.the_book_comment_author_name);
                topicText = view.findViewById(R.id.the_book_comment_topic);
            }
        }
    }

}
