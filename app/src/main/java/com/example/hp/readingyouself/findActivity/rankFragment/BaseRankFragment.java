package com.example.hp.readingyouself.findActivity.rankFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.readingyouself.R;
import com.example.hp.readingyouself.findActivity.RankListActivity;
import com.example.hp.readingyouself.findActivity.RankingActivity;
import com.example.hp.readingyouself.readingDataSupport.dataForm.AllRankingBean;

import java.util.List;


public class BaseRankFragment extends Fragment {


    public BaseRankFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rank_base, container, false);
        initView(view);
        return view;
    }

    protected void initView(View view){
        //
        textView = view.findViewById(R.id.find_rank_base_text);
        textView.setText(title);
        RecyclerView recyclerView = view.findViewById(R.id.find_rank_base_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        if(baseBeans == null){
            baseBeans = listener.getBaseBean(title);
        }
        if(baseAdapter == null){
            baseAdapter = new BaseAdapter();
        }
        recyclerView.setAdapter(baseAdapter);
    }

    private TextView textView;
    private BaseAdapter baseAdapter;


    class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.ViewHolder>{


        @NonNull
        @Override
        public BaseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.base_rank_item,parent,false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull BaseAdapter.ViewHolder holder, int position) {
            holder.textView.setText(baseBeans.get(position).getTitle());
            holder.id = baseBeans.get(position).get_id();
            holder.title = baseBeans.get(position).getTitle();
        }

        @Override
        public int getItemCount() {
            if(baseBeans == null) return 0;else return baseBeans.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            TextView textView;
            String id;
            String title;

            ViewHolder(@NonNull View itemView) {
                super(itemView);
                this.textView = itemView.findViewById(R.id.find_base_rank_item_text);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(),RankListActivity.class);
                        intent.putExtra(RankListActivity.RANK_ID,id);
                        intent.putExtra(RankListActivity.RANK_NAME,title);
                        startActivity(intent);
                    }
                });
            }
        }

    }


    private List<AllRankingBean.BaseBean> baseBeans;
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    private Listener listener;

    public void reSet(List<AllRankingBean.BaseBean> baseBeans){
        this.baseBeans = baseBeans;
        if(baseAdapter != null)baseAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Listener){
            listener = (Listener)context;
        }else{
            throw new RuntimeException("must implement BaseRankFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


    public interface Listener{
        List<AllRankingBean.BaseBean> getBaseBean(String fragmentType);
    }
}
