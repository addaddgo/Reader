package com.example.hp.readingyouself;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bifan.txtreaderlib.bean.TxtMsg;
import com.bifan.txtreaderlib.interfaces.ILoadListener;
import com.bifan.txtreaderlib.interfaces.IPageEdgeListener;
import com.bifan.txtreaderlib.main.TxtReaderView;
import com.example.hp.readingyouself.readingDataSupport.DataConnector;
import com.example.hp.readingyouself.readingDataSupport.dataForm.BookChapter;
import com.example.hp.readingyouself.readingDataSupport.DataGiveService;

import java.util.ArrayList;

public class ChapterBodyActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_body);
        txtReaderView = findViewById(R.id.chapter_body_txt_reader);
        handler = new MyHandler(getMainLooper());
    }

    private TxtReaderView txtReaderView;
    private DataConnector dataConnector;

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this,DataGiveService.class);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(serviceConnection);
    }

    public final static String CHAPTER_LINK = "chapter_url";
    public final static String CHAPTER_POSITION = "chapter_position";

    private ArrayList<BookChapter> currentChapters;
    private int currentChapterPosition;

    //切换章节
    private void setPageListener(){
        txtReaderView.setOnPageEdgeListener(new IPageEdgeListener() {
            @Override
            public void onCurrentFirstPage() {
                //dataConnector.sentChapterBody(currentChapters.get(currentChapterPosition++).getLink());
            }

            @Override
            public void onCurrentLastPage() {
                //dataConnector.sentChapterBody(currentChapters.get(currentChapterPosition++).getLink());
            }
        });
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DataGiveService.DataBinder dataBinder = (DataGiveService.DataBinder)service;
            dataConnector = dataBinder.getDataConnector();
            dataConnector.setWorkHandler(handler);
            Intent intent = getIntent();
            String url = intent.getStringExtra(CHAPTER_LINK);
            currentChapterPosition = intent.getIntExtra(CHAPTER_POSITION,0);
            dataConnector.sentChapterBody(url);
            currentChapters = dataConnector.getCurrentBookChapters();
            setPageListener();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            dataConnector.setWorkHandler(null);
            dataConnector = null;
        }
    };

    public final static int CHAPTER_BODY = 1;
    private MyHandler handler;

    class MyHandler extends Handler{
        public MyHandler(Looper looper) {
            super(looper);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(CHAPTER_BODY == msg.what){
                String string = (String)msg.obj;
                txtReaderView.loadText(string, new ILoadListener() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onFail(TxtMsg txtMsg) {

                    }

                    @Override
                    public void onMessage(String s) {

                    }
                });
            }
        }
    }
}
