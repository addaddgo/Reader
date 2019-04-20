package com.example.hp.readingyouself.findActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.readingyouself.BaseActivity;
import com.example.hp.readingyouself.R;
import com.example.hp.readingyouself.readActivity.BookIntroductionActivity;
import com.example.hp.readingyouself.readingDataSupport.DataConnector;
import com.example.hp.readingyouself.readingDataSupport.dataForm.BookInCategoryListBean;
import com.example.hp.readingyouself.readingDataSupport.netData.NetDataGiver;

import java.util.HashMap;
import java.util.List;

public class CategoryListActivity extends BaseActivity {

    public final static String MAJOR_TYPE = "major";
    public final static String MINOR_TYPE = "minor";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        initView();
    }

    private void initView(){
        TextView textView = findViewById(R.id.find_category_book_type_text);
        Intent intent = getIntent();
        major = intent.getStringExtra(MAJOR_TYPE);
        minor = intent.getStringExtra(MINOR_TYPE);
        if(minor == null) textView.setText(minor);
        else{
            String string = major + minor;
            textView.setText(string);
        }
        RecyclerView recyclerView = findViewById(R.id.find_category_book_list_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new Adapter();
        recyclerView.setAdapter(adapter);
    }

    private String major;
    private String minor;
    private BookInCategoryListBean bookInCategoryListBean;
    private Adapter adapter;
    private HashMap<String,Bitmap> stringBitmapHashMap;
    List<BookInCategoryListBean.BooksBean> booksBeanList;

    private class Adapter extends RecyclerView.Adapter<Adapter.HolderView>{

        @Override
        public void onBindViewHolder(@NonNull HolderView holder, int position) {
            BookInCategoryListBean.BooksBean bean = booksBeanList.get(position);
            String author = getString(R.string.author) + bean.getAuthor();
            holder.author.setText(author);
            String bookName = getString(R.string.book_name) + bean.getTitle();
            holder.bookName.setText(bookName);
            String lastChapter = getString(R.string.last_chapter) + bean.getLastChapter();
            holder.lastChapter.setText(lastChapter);
            String shortIntro = getString(R.string.short_introduction) + bean.getShortIntro();
            holder.shortIntroduction.setText(shortIntro);
            holder.bookId = bean.get_id();
            holder.imageView.setImageBitmap(stringBitmapHashMap.get(bean.getCover()));
        }

        @NonNull
        @Override
        public HolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.find_category_book_list_item,parent,false);
            return  new HolderView(view);
        }

        @Override
        public int getItemCount() {
            return booksBeanList == null ? 0 : booksBeanList.size();
        }

        void reSetRank(){
            if(bookInCategoryListBean != null && stringBitmapHashMap !=null)notifyDataSetChanged();
        }

        class HolderView extends RecyclerView.ViewHolder{
            String bookId;
            ImageView imageView;
            TextView bookName;
            TextView author;
            TextView lastChapter;
            TextView shortIntroduction;

            HolderView(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.find_category_book_item_image);
                bookName = itemView.findViewById(R.id.find_category_book_item_book_name);
                author = itemView.findViewById(R.id.find_category_book_item_author);
                shortIntroduction = itemView.findViewById(R.id.find_category_book_item_shortIntro);
                lastChapter = itemView.findViewById(R.id.find_category_book_item_lastChapter);
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
    }

    @Override
    protected void onHandleMessage(Message msg) {
        if(msg.obj instanceof BookInCategoryListBean){
            bookInCategoryListBean = (BookInCategoryListBean)msg.obj;
            booksBeanList = bookInCategoryListBean.getBooks();
            String[] avatars = new String[bookInCategoryListBean.getBooks().size()];
            for (int i = 0;i < avatars.length ; i++){
                avatars[i] = bookInCategoryListBean.getBooks().get(i).getCover();
            }
            dataConnector.sendCovers(avatars);
        }
        if(msg.obj instanceof HashMap){
            stringBitmapHashMap = (HashMap<String,Bitmap>)msg.obj;
            adapter.reSetRank();
        }
    }

    private DataConnector dataConnector;

    @Override
    protected void onDataServiceConnect() {
        dataConnector = getDataConnector();
        dataConnector.sentCategoryList(NetDataGiver.CATEGORY_TYPE_HOT,major,minor,0,20);
        currentType = NetDataGiver.CATEGORY_TYPE_HOT;
    }

    @Override
    protected void onDataServiceDisconnect() {
        dataConnector = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.category_boo_list_type,menu);
        return true;
    }


    private String currentType;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.category_book_type_hot:
                if(dataConnector != null && !currentType.equals(NetDataGiver.CATEGORY_TYPE_HOT)){
                    dataConnector.sentCategoryList(NetDataGiver.CATEGORY_TYPE_HOT,major,minor,0,20);
                }
                return true;
            case R.id.category_book_type_new:
                if(dataConnector != null && !currentType.equals(NetDataGiver.CATEGORY_TYPE_NEW)){
                    dataConnector.sentCategoryList(NetDataGiver.CATEGORY_TYPE_NEW,major,minor,0,20);
                }
                return true;
            case R.id.category_book_type_reputation:
                if(dataConnector != null && !currentType.equals(NetDataGiver.CATEGORY_TYPE_REPUTATION)){
                    dataConnector.sentCategoryList(NetDataGiver.CATEGORY_TYPE_REPUTATION,major,minor,0,20);
                }
                return true;
            case R.id.category_book_type_over:
                if(dataConnector != null && !currentType.equals(NetDataGiver.CATEGORY_TYPE_OVER)){
                    dataConnector.sentCategoryList(NetDataGiver.CATEGORY_TYPE_OVER,major,minor,0,20);
                }
                return true;
        }
        return false;
    }
}
