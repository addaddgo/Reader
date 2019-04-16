package com.example.hp.readingyouself.commentActivity;

import android.content.Intent;
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
import com.example.hp.readingyouself.commentActivity.commentBean.RecommendBookListBean;
import com.example.hp.readingyouself.readingDataSupport.DataConnector;
import com.example.hp.readingyouself.readingDataSupport.netData.NetConstantParameter;

import java.util.List;

public class RecommendBookActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_book);
        RecyclerView recyclerView = findViewById(R.id.community_recommend_book_comment_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerAdapter(null);
        recyclerView.setAdapter(adapter);
    }

    private RecyclerAdapter adapter;
    private DataConnector dataConnector;

    @Override
    protected void onHandleMessage(Message msg) {
        if(msg.obj instanceof RecommendBookListBean){
            adapter.resetBean(((RecommendBookListBean) msg.obj).getHelps());
        }
    }

    @Override
    protected void onDataServiceConnect() {
        dataConnector = getDataConnector();
        dataConnector.sendRecommendBookListBean(NetConstantParameter.COMPREHENSIVE_COMMENT_SORT_UPDATED,0,20,false);
        currentDistillate = false;
        currentSort = NetConstantParameter.BOOK_COMMENT_SORT_UPDATED;
    }

    @Override
    protected void onDataServiceDisconnect() {
        dataConnector = null;
    }

    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

        private List<RecommendBookListBean.HelpsBean> helpBeans;

        public RecyclerAdapter(List<RecommendBookListBean.HelpsBean> helpBeans) {
            this.helpBeans = helpBeans;
        }

        @Override
        public int getItemCount() {
            if(helpBeans == null)return 0;else return helpBeans.size();
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.topic.setText(helpBeans.get(position).getTitle());
            holder.author.setText(helpBeans.get(position).getTitle());
            holder.helpID = helpBeans.get(position).get_id();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_book_comment_item,parent,false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            ImageView imageView;
            TextView author;
            TextView topic;
            String helpID;
            ViewHolder(@NonNull View itemView) {
                super(itemView);
                this.imageView = itemView.findViewById(R.id.community_comment_book_image);
                this.author = itemView.findViewById(R.id.community_comment_book_name);
                this.topic = itemView.findViewById(R.id.community_comment_book_topic);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getBaseContext(),BookCommentDetailActivity.class);
                        intent.putExtra(BookCommentDetailActivity.REVIEW_ID,helpID);
                        startActivity(intent);
                    }
                });
            }
        }

        void resetBean(List<RecommendBookListBean.HelpsBean> helpBeans){
            this.helpBeans = helpBeans;
            notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.usua_comment,menu);
        return true;
    }


    private boolean currentDistillate;
    private String currentSort;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(dataConnector == null){
            return super.onOptionsItemSelected(item);
        }
        switch (item.getItemId()){
            case R.id.community_usual_menu_all:
                if(currentDistillate){
                    dataConnector.sendRecommendBookListBean(currentSort,0,20,false);
                    currentDistillate = false;
                }
                break;
            case R.id.community_usual_menu_distillate:
                if(!currentDistillate){
                    dataConnector.sendRecommendBookListBean(currentSort,0,20,true);
                    currentDistillate = true;
                }
                break;
            case R.id.community_usual_menu_update:
                if(!currentSort.equals(NetConstantParameter.COMPREHENSIVE_COMMENT_SORT_UPDATED)){
                    dataConnector.sendRecommendBookListBean(NetConstantParameter.COMPREHENSIVE_COMMENT_SORT_UPDATED,0,20,currentDistillate);
                    currentSort = NetConstantParameter.COMPREHENSIVE_COMMENT_SORT_UPDATED;
                }
                break;
            case R.id.community_usual_menu_most:
                if(!currentSort.equals(NetConstantParameter.COMPREHENSIVE_COMMENT_SORT_MOST)){
                    dataConnector.sendRecommendBookListBean(NetConstantParameter.COMPREHENSIVE_COMMENT_SORT_MOST,0,20,currentDistillate);
                    currentSort = NetConstantParameter.COMPREHENSIVE_COMMENT_SORT_MOST;
                }
                break;
            case R.id.community_usual_menu_created:
                if(!currentSort.equals(NetConstantParameter.COMPREHENSIVE_COMMENT_SORT_CREATED)){
                    dataConnector.sendRecommendBookListBean(NetConstantParameter.COMPREHENSIVE_COMMENT_SORT_UPDATED,0,20,currentDistillate);
                    currentSort = NetConstantParameter.COMPREHENSIVE_COMMENT_SORT_CREATED;
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
