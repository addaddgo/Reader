package com.example.hp.readingyouself.searchActivity;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.readingyouself.BaseActivity;
import com.example.hp.readingyouself.R;
import com.example.hp.readingyouself.readActivity.BookIntroductionActivity;
import com.example.hp.readingyouself.readingDataSupport.dataForm.SearchBookBean;

import java.util.HashMap;

public class SearchActivity extends BaseActivity {

    public static final String BOOK_NAME = "book_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initWidget();
    }

    private void initWidget(){
        RecyclerView recyclerView = findViewById(R.id.search_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerRankingAdapter();
        recyclerView.setAdapter(adapter);
    }

    private String bookName;


    private RecyclerRankingAdapter adapter;
    private EditText editText;
    private HashMap<String,Bitmap> stringBitmapHashMap;
    private SearchBookBean searchBookBean;

    @Override
    protected void initBeforeBindServiceOnCreated() {
        bookName = getIntent().getStringExtra(BOOK_NAME);
    }

    @Override
    protected void onHandleMessage(Message msg) {
        if(msg.obj instanceof SearchBookBean){
            searchBookBean = (SearchBookBean)msg.obj;
            if(searchBookBean.getBooks().size() != 0){
                String[] covers = new String[searchBookBean.getBooks().size()];
                for (int i = 0; i < covers.length; i++) {
                    covers[i] = searchBookBean.getBooks().get(i).getCover();
                }
                getDataConnector().sendCovers(covers);
            }else{
                Toast.makeText(this,getString(R.string.search_null),Toast.LENGTH_LONG).show();
            }

        }
        if(msg.obj instanceof HashMap){
            stringBitmapHashMap = (HashMap<String,Bitmap>)msg.obj;
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDataServiceConnect() {
        getDataConnector().sendSearchBookBean(bookName);
    }

    @Override
    protected void onDataServiceDisconnect() {

    }

    private class RecyclerRankingAdapter extends RecyclerView.Adapter<RecyclerRankingAdapter.HolderView>{

        @Override
        public void onBindViewHolder(@NonNull HolderView holder, int position) {
            SearchBookBean.BooksBean bean = searchBookBean.getBooks().get(position);
            holder.imageView.setImageBitmap(stringBitmapHashMap.get(bean.getCover()));
            String author = getString(R.string.author) + bean.getAuthor();
            holder.author.setText(author);
            String bookName = getString(R.string.book_name) + bean.getTitle();
            holder.bookName.setText(bookName);
            String shortIntro = getString(R.string.short_introduction) + bean.getShortIntro();
            holder.shortIntroduction.setText(shortIntro);
            holder.bookId = bean.get_id();
        }

        @NonNull
        @Override
        public HolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.find_rank_list_item,parent,false);
            return  new HolderView(view);
        }

        @Override
        public int getItemCount() {
            if(searchBookBean == null){
                return 0;
            }else{
                return searchBookBean.getBooks().size();
            }
        }

        class HolderView extends RecyclerView.ViewHolder{
            String bookId;
            ImageView imageView;
            TextView bookName;
            TextView author;
            TextView tag;
            TextView shortIntroduction;

            HolderView(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.find_ranking_item_image);
                bookName = itemView.findViewById(R.id.find_ranking_item_book_name);
                author = itemView.findViewById(R.id.find_ranking_item_author);
                shortIntroduction = itemView.findViewById(R.id.find_ranking_item_shortIntro);
                tag = itemView.findViewById(R.id.find_ranking_item_tag);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(bookId != null){
                            Intent intent = new Intent(getBaseContext(),BookIntroductionActivity.class);
                            intent.putExtra(BookIntroductionActivity.BOOK_ID,bookId);
                            startActivity(intent);
                        }
                    }
                });
            }
        }
        void  reset(){
            if(searchBookBean != null && stringBitmapHashMap != null &&stringBitmapHashMap.size() != 0)notifyDataSetChanged();
        }
    }
}
