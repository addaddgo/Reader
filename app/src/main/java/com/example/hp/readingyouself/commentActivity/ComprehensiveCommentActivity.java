package com.example.hp.readingyouself.commentActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
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
import com.example.hp.readingyouself.commentActivity.commentBean.ComprehensiveAndOriginalCommentBean;
import com.example.hp.readingyouself.readingDataSupport.DataConnector;
import com.example.hp.readingyouself.readingDataSupport.netData.NetConstantParameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ComprehensiveCommentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprehensive_comment);
        RecyclerView recyclerView = findViewById(R.id.community_comprehensive_activity_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);
    }

    protected RecyclerAdapter recyclerAdapter;
    protected ComprehensiveAndOriginalCommentBean bean;
    protected List<ComprehensiveAndOriginalCommentBean.PostsBean>  postList;
    protected DataConnector dataConnector;
    protected HashMap<String,Bitmap> stringBitmapHashMap;

    @Override
    protected void onHandleMessage(Message msg) {
        if(msg.obj instanceof  ComprehensiveAndOriginalCommentBean){
            bean = (ComprehensiveAndOriginalCommentBean)msg.obj;
            postList = bean.getPosts();
            String[] avatars = new String[postList.size()];
            for (int i = 0; i < avatars.length; i++) {
                avatars[i] = postList.get(i).getAuthor().getAvatar();
            }
            dataConnector.sendAuthorPortrait(avatars);
        }
        if(msg.obj instanceof HashMap){
            stringBitmapHashMap = (HashMap<String,Bitmap>)msg.obj;
            recyclerAdapter.resetPosts();
        }
    }

    @Override
    protected void onDataServiceConnect() {
      dataConnector = getDataConnector();
      dataConnector.sentComprehensiveCommentList(NetConstantParameter.COMPREHENSIVE_COMMENT_SORT_MOST,0,20,false);
      currentDistillate = false;
      currentSort = NetConstantParameter.COMPREHENSIVE_COMMENT_SORT_MOST;
    }

    @Override
    protected void onDataServiceDisconnect() {
        dataConnector = null;
    }


    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

        @Override
        public int getItemCount() {
            if(postList == null) return 0;
            else return  postList.size();
        }


        @NonNull
        @Override
        public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.activity_comprehensive_comment_item,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
            holder.author.setText(postList.get(position).getAuthor().getNickname());
            holder.topic.setText(postList.get(position).getTitle());
            holder.topicId = postList.get(position).get_id();
            holder.imageView.setImageBitmap(stringBitmapHashMap.get(postList.get(position).getAuthor().getAvatar()));
        }
        void resetPosts(){
            if(postList != null && stringBitmapHashMap != null && stringBitmapHashMap.size() != 0)notifyDataSetChanged();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            ImageView imageView;
            TextView author;
            TextView topic;
            String topicId;
            ViewHolder(View itemView) {
                super(itemView);
                this.imageView = itemView.findViewById(R.id.community_comment_author_portrait);
                this.author = itemView.findViewById(R.id.community_comment_author_name);
                this.topic = itemView.findViewById(R.id.community_comment_topic);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getBaseContext(),ComprehensiveCommentDetailActivity.class);
                        intent.putExtra(ComprehensiveCommentDetailActivity.POST_ID,topicId);
                        startActivity(intent);
                    }
                });
            }
        }

    }

    protected boolean currentDistillate;
    protected String currentSort;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.usua_comment,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.community_usual_menu_all:
                if(currentDistillate){
                    dataConnector.sentComprehensiveCommentList(currentSort,0,20,false);
                    currentDistillate = false;
                }
                break;
            case R.id.community_usual_menu_distillate:
                if(!currentDistillate){
                    dataConnector.sentComprehensiveCommentList(currentSort,0,20,true);
                    currentDistillate = true;
                }
                break;
            case R.id.community_usual_menu_update:
                if(!currentSort.equals(NetConstantParameter.COMPREHENSIVE_COMMENT_SORT_UPDATED)){
                    dataConnector.sentComprehensiveCommentList(NetConstantParameter.COMPREHENSIVE_COMMENT_SORT_UPDATED,0,20,currentDistillate);
                    currentSort = NetConstantParameter.COMPREHENSIVE_COMMENT_SORT_UPDATED;
                }
                break;
            case R.id.community_usual_menu_most:
                if(!currentSort.equals(NetConstantParameter.COMPREHENSIVE_COMMENT_SORT_MOST)){
                    dataConnector.sentComprehensiveCommentList(NetConstantParameter.COMPREHENSIVE_COMMENT_SORT_MOST,0,20,currentDistillate);
                    currentSort = NetConstantParameter.COMPREHENSIVE_COMMENT_SORT_MOST;
                }
                break;
            case R.id.community_usual_menu_created:
                if(!currentSort.equals(NetConstantParameter.COMPREHENSIVE_COMMENT_SORT_CREATED)){
                    dataConnector.sentComprehensiveCommentList(NetConstantParameter.COMPREHENSIVE_COMMENT_SORT_UPDATED,0,20,currentDistillate);
                    currentSort = NetConstantParameter.COMPREHENSIVE_COMMENT_SORT_CREATED;
                }
                break;
        }
        return true;
    }
}
