package com.example.hp.readingyouself.commentActivity;

import android.content.Intent;
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

import java.util.List;

public class ComprehensiveCommentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprehensive_comment);
        RecyclerView recyclerView = findViewById(R.id.community_comprehensive_activity_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerAdapter = new RecyclerAdapter(null);
        recyclerView.setAdapter(recyclerAdapter);
    }

    protected RecyclerAdapter recyclerAdapter;
    protected ComprehensiveAndOriginalCommentBean bean;
    protected DataConnector dataConnector;


    @Override
    protected void onHandleMessage(Message msg) {
        if(msg.obj instanceof  ComprehensiveAndOriginalCommentBean){
            bean = (ComprehensiveAndOriginalCommentBean)msg.obj;
            recyclerAdapter.resetPosts(bean.getPosts());
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

        private List<ComprehensiveAndOriginalCommentBean.PostsBean> posts;

        RecyclerAdapter(List<ComprehensiveAndOriginalCommentBean.PostsBean> posts) {
            this.posts = posts;
        }

        @Override
        public int getItemCount() {
            if(posts == null) return 0;
            else return  posts.size();
        }


        @NonNull
        @Override
        public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.activity_comprehensive_comment_item,parent,false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
            holder.author.setText(posts.get(position).getAuthor().getNickname());
            holder.topic.setText(posts.get(position).getTitle());
            holder.topicId = posts.get(position).get_id();
        }
        void resetPosts(List<ComprehensiveAndOriginalCommentBean.PostsBean> posts){
            this.posts = posts;
            notifyDataSetChanged();
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
                        intent.putExtra(ComprehensiveCommentDetailActivity.DISCUSSION_IS,topicId);
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
