package com.example.hp.readingyouself.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.readingyouself.MaiActivityInterface;
import com.example.hp.readingyouself.R;
import com.example.hp.readingyouself.readingDataSupport.dataForm.CategoryBean;

import java.util.List;


public class CategoryFragment extends Fragment {

    private OnMaiActivityInteractionListener mListener;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        initRecyclerView(view);
        mListener.getAllCategory();
        return view;
    }

    private RecyclerView maleRecycler;
    private RecyclerView femaleRecycler;
    private RecyclerView pressRecycler;
    private MajorRecyclerAdapter maleAdapter;
    private MajorRecyclerAdapter femaleAdapter;
    private MajorRecyclerAdapter pressAdapter;


    public void resetCategory(CategoryBean categoryBean){
        maleAdapter.reSet(categoryBean.getMale());
        femaleAdapter.reSet(categoryBean.getFemale());
        pressAdapter.reSet(categoryBean.getPress());
    }

    //分类列表初始化
    private void initRecyclerView(View view){
        maleRecycler = view.findViewById(R.id.category_major_male_recycler);
        femaleRecycler = view.findViewById(R.id.category_major_female_recycler);
        pressRecycler = view.findViewById(R.id.category_major_press_recycler);
        RecyclerView.LayoutManager maleLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager femaleLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager pressLayoutManager = new LinearLayoutManager(getContext());
        maleAdapter = new MajorRecyclerAdapter(null);
        femaleAdapter = new MajorRecyclerAdapter(null);
        pressAdapter = new MajorRecyclerAdapter(null);
        maleRecycler.setAdapter(maleAdapter);
        femaleRecycler.setAdapter(femaleAdapter);
        pressRecycler.setAdapter(pressAdapter);
        maleRecycler.setLayoutManager(maleLayoutManager);
        femaleRecycler.setLayoutManager(femaleLayoutManager);
        pressRecycler.setLayoutManager(pressLayoutManager);
    }


    //男生 女生，出版的分类
    class MajorRecyclerAdapter extends RecyclerView.Adapter<MajorRecyclerAdapter.ViewHolder>{

        private List<CategoryBean.FoundationBean> foundationBeans;

        public MajorRecyclerAdapter(List<CategoryBean.FoundationBean> foundationBeans) {
            this.foundationBeans = foundationBeans;
        }

        @Override
        public int getItemCount() {
            if(foundationBeans == null)return 0;
            else return foundationBeans.size();
        }

        @Override
        public void onBindViewHolder(@NonNull MajorRecyclerAdapter.ViewHolder holder, int position) {
            String major = foundationBeans.get(position).getMajor();
            holder.textView.setText(major);
            holder.adapter.resetMins(foundationBeans.get(position).getMins(),major);
        }

        @NonNull
        @Override
        public MajorRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.category_major_item,parent,false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        public void reSet(List<CategoryBean.FoundationBean> foundationBeans){
            this.foundationBeans = foundationBeans;
            notifyDataSetChanged();
        }

        class  ViewHolder extends RecyclerView.ViewHolder{
            RecyclerView recyclerView;
            TextView textView;
            MinsCategoryRecyclerAdapter adapter;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                this.recyclerView = itemView.findViewById(R.id.category_mins_recycler);
                this.textView = itemView.findViewById(R.id.category_major_text);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                this.adapter = new MinsCategoryRecyclerAdapter(null,null);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);
            }
        }

        class MinsCategoryRecyclerAdapter extends RecyclerView.Adapter<MajorRecyclerAdapter.MinsCategoryRecyclerAdapter.ViewHolder>{

            List<String> strings;
            String major;

            MinsCategoryRecyclerAdapter(List<String> strings,String major) {
                this.strings = strings;
                this.major = major;
            }

            @Override
            public int getItemCount() {
                if(strings == null) return 0;else return strings.size();
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.category_mins_item,parent,false);
                ViewHolder viewHolder = new ViewHolder(view);
                return  viewHolder;
            }

            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                holder.textView.setText(strings.get(position));
            }

            class ViewHolder extends RecyclerView.ViewHolder{
                TextView textView;
                ViewHolder(@NonNull View itemView) {
                    super(itemView);
                    this.textView = itemView.findViewById(R.id.category_mins_text);
                    this.textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mListener.startListInCategoryActivity(major,textView.getText().toString());
                        }
                    });
                }
            }
            void resetMins(List<String> strings,String major){
                this.strings = strings;
                this.major = major;
                notifyDataSetChanged();
            }
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMaiActivityInteractionListener) {
            mListener = (OnMaiActivityInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnMaiActivityInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnMaiActivityInteractionListener extends MaiActivityInterface {
        void getAllCategory();
    }
}
