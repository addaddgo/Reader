package com.example.hp.readingyouself.findActivity;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.readingyouself.BaseActivity;
import com.example.hp.readingyouself.R;
import com.example.hp.readingyouself.commentActivity.commentBean.BookCommentListBean;
import com.example.hp.readingyouself.readingDataSupport.DataConnector;
import com.example.hp.readingyouself.readingDataSupport.dataForm.RankBean;

import java.util.List;

public class RankListActivity extends BaseActivity {

    public final static String RANK_ID = "rank_id";
    public final static String RANK_NAME = "rank_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_list);
        TextView textView = findViewById(R.id.find_rank_list_text);
        textView.setText(name);
        RecyclerView recyclerView = findViewById(R.id.find_rank_list_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerRankingAdapter = new RecyclerRankingAdapter();
        recyclerView.setAdapter(recyclerRankingAdapter);
    }

    private RankBean rankBean;

    private class RecyclerRankingAdapter extends RecyclerView.Adapter<RecyclerRankingAdapter.HolderView>{

        private List<RankBean.RankingBean.BooksBean> booksBeanList;


        @Override
        public void onBindViewHolder(@NonNull HolderView holder, int position) {
            RankBean.RankingBean.BooksBean bean = booksBeanList.get(position);
            String author = getString(R.string.author) + bean.getAuthor();
            holder.author.setText(author);
            String bookName = getString(R.string.book_name) + bean.getTitle();
            holder.bookName.setText(bookName);
            String tag = getString(R.string.tag) + bean.getMajorCate() + "\t" + bean.getMinorCate();
            holder.tag.setText(tag);
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
            if(booksBeanList == null){
                return 0;
            }else{
                return booksBeanList.size();
            }
        }

        void reSetRank(List<RankBean.RankingBean.BooksBean> booksBeanList){
            if(booksBeanList != null){
                this.booksBeanList = booksBeanList;
                this.notifyDataSetChanged();
            }
        }

        class HolderView extends RecyclerView.ViewHolder{
            String bookId;
            TextView bookName;
            TextView author;
            TextView tag;
            TextView shortIntroduction;

            HolderView(@NonNull View itemView) {
                super(itemView);
                bookName = itemView.findViewById(R.id.find_ranking_item_book_name);
                author = itemView.findViewById(R.id.find_ranking_item_author);
                shortIntroduction = itemView.findViewById(R.id.find_ranking_item_shortIntro);
                tag = itemView.findViewById(R.id.find_ranking_item_tag);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(bookId != null){
                            //TODO 跳转到章节页面
                        }
                    }
                });
            }
        }
    }

    private DataConnector dataConnector;
    private RecyclerRankingAdapter recyclerRankingAdapter;

    @Override
    protected void onHandleMessage(Message msg) {
        if(msg.obj instanceof RankBean){
            rankBean = (RankBean)msg.obj;
            recyclerRankingAdapter.reSetRank(rankBean.getRanking().getBooks());
        }
    }


    private String id;
    private String name;

    @Override
    protected void onDataServiceConnect() {
        dataConnector = getDataConnector();
        dataConnector.sendRanking(id);
    }

    @Override
    protected void onDataServiceDisconnect() {
        dataConnector = null;
    }

    @Override
    protected void initBeforeBindServiceOnCreated() {
        id = getIntent().getStringExtra(RANK_ID);
        name = getIntent().getStringExtra(RANK_NAME);
    }
}
