package com.example.hp.readingyouself.readingDataSupport;

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

import com.example.hp.readingyouself.ChapterActivity;
import com.example.hp.readingyouself.R;
import com.example.hp.readingyouself.readingDataSupport.dataForm.BookInCategoryBean;
import com.example.hp.readingyouself.readingDataSupport.netData.NetDataGiver;

public class ListInCategoryActivity extends AppCompatActivity {


    public final static String BOOK_CATEGORY_MAJOR = "major";
    public final static String BOOK_CATEGORY_MINOR = "minor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_in_category);
        handler = new MyHandler(getMainLooper());
        Intent intent = getIntent();
        major = intent.getStringExtra(BOOK_CATEGORY_MAJOR);
        minor = intent.getStringExtra(BOOK_CATEGORY_MINOR);
        recyclerView = findViewById(R.id.list_in_category_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerCategoryAdapter(null);
        recyclerView.setAdapter(adapter);
    }

    private RecyclerView recyclerView;
    private RecyclerCategoryAdapter adapter;

    private class RecyclerCategoryAdapter extends RecyclerView.Adapter<ListInCategoryActivity.RecyclerCategoryAdapter.HolderView>{
        private BookInCategoryBean bookInCategoryBean;

        public RecyclerCategoryAdapter(BookInCategoryBean bookInCategoryBean) {
            super();
            this.bookInCategoryBean = bookInCategoryBean;
        }

        @Override
        public void onBindViewHolder(@NonNull ListInCategoryActivity.RecyclerCategoryAdapter.HolderView holder, int position) {
            String shortIntro = getString(R.string.short_introduction) + bookInCategoryBean.getBooks().get(position).getShortIntro();
            holder.shortIntroduction.setText(shortIntro);
            String author = getString(R.string.author)+ bookInCategoryBean.getBooks().get(position).getAuthor();
            holder.author.setText(author);
            String title = getString(R.string.book_name) + bookInCategoryBean.getBooks().get(position).getTitle();
            holder.title.setText(title);
            holder.bookID = bookInCategoryBean.getBooks().get(position).get_id();
        }

        @NonNull
        @Override
        public ListInCategoryActivity.RecyclerCategoryAdapter.HolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.find_rank_list_item,parent,false);
            final ListInCategoryActivity.RecyclerCategoryAdapter.HolderView holderView = new ListInCategoryActivity.RecyclerCategoryAdapter.HolderView(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getBaseContext(),ChapterActivity.class);
                    intent.putExtra(ChapterActivity.BOOK_ID,holderView.bookID);
                    startActivity(intent);
                }
            });
            return holderView;
        }


        @Override
        public int getItemCount() {
            if(bookInCategoryBean == null){
                return 0;
            }else{
                return bookInCategoryBean.getBooks().size();
            }
        }

        public void reSetRank(BookInCategoryBean bookInCategoryBean){
            if(bookInCategoryBean != null){
                this.bookInCategoryBean = bookInCategoryBean;
                this.notifyDataSetChanged();
            }
        }

        class HolderView extends RecyclerView.ViewHolder{
            String bookID;
            TextView title;
            TextView author;
            TextView shortIntroduction;

            public HolderView(@NonNull View itemView) {
                super(itemView);
            }
        }

    }


    private String major;
    private String minor;

    private DataConnector dataConnector;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DataGiveService.DataBinder dataBinder = (DataGiveService.DataBinder)service;
            dataConnector = dataBinder.getDataConnector();
            dataConnector.setWorkHandler(handler);
            dataConnector.sentCategoryList(NetDataGiver.CATEGORY_TYPE_REPUTATION,major,minor,"0","10");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            dataConnector.setWorkHandler(null);
            dataConnector = null;
        }
    };

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

    private MyHandler handler;

    class MyHandler extends Handler {
        MyHandler(Looper looper) {
            super(looper);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            adapter.reSetRank((BookInCategoryBean)msg.obj);
        }
    }
}
