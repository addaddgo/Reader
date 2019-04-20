package com.example.hp.readingyouself.commentActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Message;
import android.support.annotation.NonNull;
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
import com.example.hp.readingyouself.commentActivity.commentBean.BookCommentListBean;
import com.example.hp.readingyouself.readingDataSupport.DataConnector;
import com.example.hp.readingyouself.readingDataSupport.netData.NetConstantParameter;

import java.util.HashMap;
import java.util.List;

public class BookCommentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_comment);
        RecyclerView recyclerView = findViewById(R.id.community_book_comment_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
    }

    private RecyclerAdapter adapter;
    private DataConnector dataConnector;

    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

        @Override
        public int getItemCount() {
            if(reviewsBeans == null)return 0;else return reviewsBeans.size();
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.topic.setText(reviewsBeans.get(position).getTitle());
            holder.name.setText(reviewsBeans.get(position).getBook().getTitle());
            holder.reviewID = reviewsBeans.get(position).get_id();
            holder.imageView.setImageBitmap(stringBitmapHashMap.get(reviewsBeans.get(position).getBook().getCover()));
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_book_comment_item, parent, false);
          return   new ViewHolder(view);
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            ImageView imageView;
            TextView name;
            TextView topic;
            String reviewID;
            ViewHolder(@NonNull View itemView) {
                super(itemView);
                this.imageView = itemView.findViewById(R.id.community_comment_book_image);
                this.name = itemView.findViewById(R.id.community_comment_book_name);
                this.topic = itemView.findViewById(R.id.community_comment_book_topic);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getBaseContext(),BookCommentDetailActivity.class);
                        intent.putExtra(BookCommentDetailActivity.REVIEW_ID,reviewID);
                        startActivity(intent);
                    }
                });
            }
        }

        void reset(){
            if(stringBitmapHashMap != null && reviewsBeans != null && stringBitmapHashMap.size() != 0)notifyDataSetChanged();
        }
    }

    private List<BookCommentListBean.ReviewsBean> reviewsBeans;
    private BookCommentListBean bookCommentListBean;
    private HashMap<String,Bitmap> stringBitmapHashMap;

    @Override
    protected void onHandleMessage(Message msg) {
         if(msg.obj instanceof BookCommentListBean){
             bookCommentListBean = (BookCommentListBean)msg.obj;
             reviewsBeans = bookCommentListBean.getReviews();
             String[] agents = new String[reviewsBeans.size()];
             for (int i = 0; i <agents.length;i++) {
                 agents[i] = reviewsBeans.get(i).getBook().getCover();
             }
             dataConnector.sendCovers(agents);
         }
         if(msg.obj instanceof HashMap){
             stringBitmapHashMap = (HashMap<String,Bitmap>)msg.obj;
             adapter.reset();
         }
    }

    @Override
    protected void onDataServiceConnect() {
        dataConnector = getDataConnector();
        dataConnector.sendBookComment(NetConstantParameter.BOOK_COMMENT_SORT_UPDATED,NetConstantParameter.BOOK_COMMENT_TYPE_ALL,0,20,false);
        currentDistillate = false;
        currentType = NetConstantParameter.BOOK_COMMENT_TYPE_ALL;
        currentSort = NetConstantParameter.COMPREHENSIVE_COMMENT_SORT_UPDATED;
    }

    @Override
    protected void onDataServiceDisconnect() {
        dataConnector = null;
    }

    private boolean currentDistillate;
    private String currentType;
    private String currentSort;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.book_comment,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(dataConnector == null){
            return super.onOptionsItemSelected(item);
        }
        switch (item.getItemId()){
            //主分类
            case R.id.community_book_comment_all:
                if(currentDistillate){
                    dataConnector.sendBookComment(currentSort,currentType,0,20,false);
                    currentDistillate = false;
                }
                break;
            case R.id.community_book_comment_distillate:
                if(!currentDistillate){
                    dataConnector.sendBookComment(currentSort,currentType,0,20,true);
                    currentDistillate = true;
                }
                break;
            //类型分类
            case R.id.community_book_comment_type_all:
                if(!currentType.equals(NetConstantParameter.BOOK_COMMENT_TYPE_ALL)){
                    dataConnector.sendBookComment(currentSort,NetConstantParameter.BOOK_COMMENT_TYPE_ALL,0,20,currentDistillate);
                    currentType = NetConstantParameter.BOOK_COMMENT_TYPE_ALL;
                }
                break;
            case R.id.community_book_comment_type_xhqh:
                if(!currentType.equals(NetConstantParameter.BOOK_COMMENT_TYPE_XHQH)){
                    dataConnector.sendBookComment(currentSort,NetConstantParameter.BOOK_COMMENT_TYPE_XHQH,0,20,currentDistillate);
                    currentType = NetConstantParameter.BOOK_COMMENT_TYPE_XHQH;
                }
                break;
            case R.id.community_book_comment_type_wxxx:
                if(!currentType.equals(NetConstantParameter.BOOK_COMMENT_TYPE_WXXX)){
                    dataConnector.sendBookComment(currentSort,NetConstantParameter.BOOK_COMMENT_TYPE_WXXX,0,20,currentDistillate);
                    currentType = NetConstantParameter.BOOK_COMMENT_TYPE_WXXX;
                }
                break;
            case R.id.community_book_comment_type_dsyn:
                if(!currentType.equals(NetConstantParameter.BOOK_COMMENT_TYPE_DSYN)){
                    dataConnector.sendBookComment(currentSort,NetConstantParameter.BOOK_COMMENT_TYPE_DSYN,0,20,currentDistillate);
                    currentType = NetConstantParameter.BOOK_COMMENT_TYPE_DSYN;
                }
                break;
            case R.id.community_book_comment_type_lsjs:
                if(!currentType.equals(NetConstantParameter.BOOK_COMMENT_TYPE_LSJS)){
                    dataConnector.sendBookComment(currentSort,NetConstantParameter.BOOK_COMMENT_TYPE_LSJS,0,20,currentDistillate);
                    currentType = NetConstantParameter.BOOK_COMMENT_TYPE_LSJS;
                }
                break;
            case R.id.community_book_comment_type_yxjj:
                if(!currentType.equals(NetConstantParameter.BOOK_COMMENT_TYPE_YXJJ)){
                    dataConnector.sendBookComment(currentSort,NetConstantParameter.BOOK_COMMENT_TYPE_YXJJ,0,20,currentDistillate);
                    currentType = NetConstantParameter.BOOK_COMMENT_TYPE_YXJJ;
                }
                break;
            case R.id.community_book_comment_type_khly:
                if(!currentType.equals(NetConstantParameter.BOOK_COMMENT_TYPE_KHLY)){
                    dataConnector.sendBookComment(currentSort,NetConstantParameter.BOOK_COMMENT_TYPE_KHLY,0,20,currentDistillate);
                    currentType = NetConstantParameter.BOOK_COMMENT_TYPE_KHLY;
                }
                break;
            case R.id.community_book_comment_type_cyjk:
                if(!currentType.equals(NetConstantParameter.BOOK_COMMENT_TYPE_CYJK)){
                    dataConnector.sendBookComment(currentSort,NetConstantParameter.BOOK_COMMENT_TYPE_CYJK,0,20,currentDistillate);
                    currentType = NetConstantParameter.BOOK_COMMENT_TYPE_CYJK;
                }
                break;
            case R.id.community_book_comment_type_hmzc:
                if(!currentType.equals(NetConstantParameter.BOOK_COMMENT_TYPE_HMZC)){
                    dataConnector.sendBookComment(currentSort,NetConstantParameter.BOOK_COMMENT_TYPE_HMZC,0,20,currentDistillate);
                    currentType = NetConstantParameter.BOOK_COMMENT_TYPE_HMZC;
                }
                break;
            case R.id.community_book_comment_type_xdyq:
                if(!currentType.equals(NetConstantParameter.BOOK_COMMENT_TYPE_XDYQ)){
                    dataConnector.sendBookComment(currentSort,NetConstantParameter.BOOK_COMMENT_TYPE_XDYQ,0,20,currentDistillate);
                    currentType = NetConstantParameter.BOOK_COMMENT_TYPE_XDYQ;
                }
                break;
            case R.id.community_book_comment_type_gdyq:
                if(!currentType.equals(NetConstantParameter.BOOK_COMMENT_TYPE_GDYQ)){
                    dataConnector.sendBookComment(currentSort,NetConstantParameter.BOOK_COMMENT_TYPE_GDYQ,0,20,currentDistillate);
                    currentType = NetConstantParameter.BOOK_COMMENT_TYPE_GDYQ;
                }
                break;
            case R.id.community_book_comment_type_hxyq:
                if(!currentType.equals(NetConstantParameter.BOOK_COMMENT_TYPE_HXYQ)){
                    dataConnector.sendBookComment(currentSort,NetConstantParameter.BOOK_COMMENT_TYPE_HXYQ,0,20,currentDistillate);
                    currentType = NetConstantParameter.BOOK_COMMENT_TYPE_HXYQ;
                }
                break;
            case R.id.community_book_comment_type_dmtr:
                if(!currentType.equals(NetConstantParameter.BOOK_COMMENT_TYPE_DMTR)){
                    dataConnector.sendBookComment(currentSort,NetConstantParameter.BOOK_COMMENT_TYPE_DMTR,0,20,currentDistillate);
                    currentType = NetConstantParameter.BOOK_COMMENT_TYPE_DMTR;
                }
                break;
            //排序分类
            case R.id.community_book_comment_sort_update:
                if(!currentSort.equals(NetConstantParameter.BOOK_COMMENT_SORT_UPDATED)){
                    dataConnector.sendBookComment(NetConstantParameter.BOOK_COMMENT_SORT_UPDATED,currentType,0,20,currentDistillate);
                    currentSort = NetConstantParameter.BOOK_COMMENT_SORT_UPDATED;
                }
                break;
            case R.id.community_book_comment_sort_created:
                if(!currentSort.equals(NetConstantParameter.BOOK_COMMENT_SORT_CREATED)){
                    dataConnector.sendBookComment(NetConstantParameter.BOOK_COMMENT_SORT_CREATED,currentType,0,20,currentDistillate);
                    currentSort = NetConstantParameter.BOOK_COMMENT_SORT_CREATED;
                }
                break;
            case R.id.community_book_comment_sort_most:
                if(!currentSort.equals(NetConstantParameter.BOOK_COMMENT_SORT_MOST)){
                    dataConnector.sendBookComment(NetConstantParameter.BOOK_COMMENT_SORT_MOST,currentType,0,20,currentDistillate);
                    currentSort = NetConstantParameter.BOOK_COMMENT_SORT_MOST;
                }
                break;
            case R.id.community_book_comment_sort_most_helpful:
                if(!currentSort.equals(NetConstantParameter.BOOK_COMMENT_SORT_HELPFUL_MOST)){
                    dataConnector.sendBookComment(NetConstantParameter.BOOK_COMMENT_SORT_HELPFUL_MOST,currentType,0,20,currentDistillate);
                    currentSort = NetConstantParameter.BOOK_COMMENT_SORT_HELPFUL_MOST;
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
