package com.example.hp.readingyouself.findActivity;

import android.content.Intent;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.readingyouself.BaseActivity;
import com.example.hp.readingyouself.R;
import com.example.hp.readingyouself.readingDataSupport.DataConnector;
import com.example.hp.readingyouself.readingDataSupport.dataForm.CategoryBean;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class CategoryActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        viewPager = findViewById(R.id.find_category_viewpager);
        initViewPager();
    }

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private View maleView;
    private View femaleView;
    private View pressView;
    private View pictureView;

    private void initViewPager(){
        LayoutInflater layoutInflater = getLayoutInflater();
        maleView = layoutInflater.inflate(R.layout.find_category_base_page,viewPager,false);
        femaleView = layoutInflater.inflate(R.layout.find_category_base_page,viewPager,false);
        pressView = layoutInflater.inflate(R.layout.find_category_base_page,viewPager,false);
        pictureView = layoutInflater.inflate(R.layout.find_category_base_page,viewPager,false);
        ArrayList<View> viewList = new ArrayList<>();
        viewList.add(maleView);
        viewList.add(femaleView);
        viewList.add(pressView);
        viewList.add(pictureView);
        viewPagerAdapter = new ViewPagerAdapter(viewList);
        viewPager.setAdapter(viewPagerAdapter);
        initPage();
    }

    private MajorRecyclerAdapter maleAdapter;
    private MajorRecyclerAdapter femaleAdapter;
    private MajorRecyclerAdapter pressAdapter;
    private MajorRecyclerAdapter pictureAdapter;

    private void initPage(){
        LinearLayoutManager maleLinearLayoutManager = new LinearLayoutManager(this);
        LinearLayoutManager femaleLinearLayoutManager = new LinearLayoutManager(this);
        LinearLayoutManager pressLinearLayoutManager = new LinearLayoutManager(this);
        LinearLayoutManager pictureLinearLayoutManager = new LinearLayoutManager(this);
        RecyclerView maleRecyclerView = maleView.findViewById(R.id.find_category_base_page_recycler);
        RecyclerView femaleRecyclerView = femaleView.findViewById(R.id.find_category_base_page_recycler);
        RecyclerView pressRecyclerView = pressView.findViewById(R.id.find_category_base_page_recycler);
        RecyclerView pictureRecyclerView = pictureView.findViewById(R.id.find_category_base_page_recycler);
        maleRecyclerView.setLayoutManager(maleLinearLayoutManager);
        femaleRecyclerView.setLayoutManager(femaleLinearLayoutManager);
        pressRecyclerView.setLayoutManager(pressLinearLayoutManager);
        pictureRecyclerView.setLayoutManager(pictureLinearLayoutManager);
        maleAdapter = new MajorRecyclerAdapter(null);
        femaleAdapter = new MajorRecyclerAdapter(null);
        pressAdapter = new MajorRecyclerAdapter(null);
        pictureAdapter = new MajorRecyclerAdapter(null);
        maleRecyclerView.setAdapter(maleAdapter);
        femaleRecyclerView.setAdapter(femaleAdapter);
        pressRecyclerView.setAdapter(pressAdapter);
        pictureRecyclerView.setAdapter(pictureAdapter);
        TextView maleText = maleView.findViewById(R.id.find_category_base_page_text);
        TextView femaleText = femaleView.findViewById(R.id.find_category_base_page_text);
        TextView pressText = pressView.findViewById(R.id.find_category_base_page_text);
        TextView pictureText = pictureView.findViewById(R.id.find_category_base_page_text);
        maleText.setText(getString(R.string.male));
        femaleText.setText(getString(R.string.female));
        pressText.setText(getString(R.string.press));
        pictureText.setText(getString(R.string.picture));
    }

    class ViewPagerAdapter extends PagerAdapter{

        ArrayList<View> viewList;

        ViewPagerAdapter(ArrayList<View> viewList) {
            super();
            this.viewList = viewList;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(viewList.get(position));
        }

        @Override
        public int getCount() {
            if(viewList == null) return 0 ;else return viewList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }


    }


    class MajorRecyclerAdapter extends RecyclerView.Adapter<MajorRecyclerAdapter.ViewHolder>{

        private List<CategoryBean.FoundationBean> foundationBeans;

        MajorRecyclerAdapter(List<CategoryBean.FoundationBean> foundationBeans) {
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
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.category_major_item,parent,false);
             return new ViewHolder(view);
        }

        void reSet(List<CategoryBean.FoundationBean> foundationBeans){
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
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                this.adapter = new MinsCategoryRecyclerAdapter(null,null);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getBaseContext(),CategoryListActivity.class);
                        intent.putExtra(CategoryListActivity.MAJOR_TYPE,textView.getText());
                        startActivity(intent);
                    }
                });
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
                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.category_mins_item,parent,false);
                return new ViewHolder(view);
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
                            Intent intent = new Intent(getBaseContext(),CategoryListActivity.class);
                            intent.putExtra(CategoryListActivity.MAJOR_TYPE,major);
                            intent.putExtra(CategoryListActivity.MINOR_TYPE,textView.getText().toString());
                            startActivity(intent);
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


    private DataConnector dataConnector;
    private CategoryBean categoryBean;

    @Override
    protected void onHandleMessage(Message msg) {
        if(msg.obj instanceof CategoryBean){
            if(categoryBean == null){
                categoryBean = (CategoryBean) msg.obj;
                maleAdapter.reSet(categoryBean.getMale());
                femaleAdapter.reSet(categoryBean.getFemale());
                pressAdapter.reSet(categoryBean.getPress());
                pictureAdapter.reSet(categoryBean.getPicture());
            }
        }
    }

    @Override
    protected void onDataServiceConnect() {
        dataConnector = getDataConnector();
        dataConnector.sentCategory();
    }

    @Override
    protected void onDataServiceDisconnect() {
        dataConnector  = null;
    }
}
