package com.example.hp.readingyouself;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.readingyouself.readingDataSupport.DataConnector;
import com.example.hp.readingyouself.readingDataSupport.dataForm.BookChapter;
import com.example.hp.readingyouself.readingDataSupport.DataGiveService;

import java.util.ArrayList;

public class ChapterActivity extends AppCompatActivity {

    public final static String BOOK_ID = "bookID";
    public final static int CHAPTER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);
        Intent intent = getIntent();
        bookID = intent.getStringExtra(BOOK_ID);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        chapterAdapter = new ChapterAdapter(chapters);
        recyclerView = findViewById(R.id.recycler_reading_activity);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(chapterAdapter);
        handler = new MyHandler(getMainLooper());
    }


    private RecyclerView recyclerView;
    private ChapterAdapter chapterAdapter;

    //数据处理
    private String bookID;
    private ArrayList<BookChapter> chapters;
    private DataConnector dataConnector;
    private MyHandler handler;

    class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ViewHolder>{

        private ArrayList<BookChapter> chapters;

        ChapterAdapter(ArrayList<BookChapter> chapters) {
            this.chapters = chapters;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.textView.setText(chapters.get(position).getTitle());
            holder.position = position;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.chapers_list_item,parent,false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(chapters != null){
                        Intent intent = new Intent(getBaseContext(),ChapterBodyActivity.class);
                        intent.putExtra(ChapterBodyActivity.CHAPTER_LINK,chapters.get(holder.position).getLink());
                        intent.putExtra(ChapterBodyActivity.CHAPTER_POSITION,holder.position);
                        startActivity(intent);
                    }
                }
            });
            return holder;
        }


        @Override
        public int getItemCount() {
            if(chapters != null){
                return chapters.size();
            }else{
                return 0;
            }
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            TextView textView;
            int position;

            ViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.chapter_name);
            }
        }

        void setChapters(ArrayList<BookChapter> chapters) {
            this.chapters = chapters;
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this,DataGiveService.class);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this,DataGiveService.class);
        unbindService(serviceConnection);
    }


    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DataGiveService.DataBinder dataBinder = (DataGiveService.DataBinder)service;
            dataConnector = dataBinder.getDataConnector();
            dataConnector.setWorkHandler(handler);
            dataConnector.sentChapterList(bookID);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            dataConnector = null;
            dataConnector.setWorkHandler(null);
        }
    };

    class MyHandler extends Handler{

        public MyHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == CHAPTER) {
                chapterAdapter.setChapters((ArrayList<BookChapter>) msg.obj);
                chapterAdapter.notifyDataSetChanged();
            }
        }
    }

}
