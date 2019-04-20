package com.example.hp.readingyouself.mainFragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.readingyouself.R;
import com.example.hp.readingyouself.readActivity.BookIntroductionActivity;
import com.example.hp.readingyouself.readingDataSupport.dataForm.SearchBookBean;
import com.example.hp.readingyouself.readingDataSupport.dataForm.SearchLog;
import com.example.hp.readingyouself.searchActivity.SearchActivity;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;

public class SearchFragment extends Fragment {


    private SearchFragmentInter mListener;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_search, container, false);
        initWidget(view);
        return view;
    }

    private void initWidget(View view){
        Button button = view.findViewById(R.id.search_frag_search_button);
        editText = view.findViewById(R.id.search_frag_edit);
        RecyclerView recyclerView = view.findViewById(R.id.search_log_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerRankingAdapter();
        recyclerView.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newLog = editText.getText().toString();
                if(log != null){
                    boolean flag = true;
                    for (SearchLog s: log) {
                        if(s.getSearch().equals(newLog)) flag = false;
                    }
                    if(flag){
                        SearchLog searchLog = new SearchLog(newLog,System.currentTimeMillis());
                        searchLog.save();//为什么将此方法放到onStop中不能保持
                        log.add(searchLog);
                    }
                }
                searchBook(newLog);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if(log == null){
            log = mListener.giveSearchLog();
        }
        adapter.notifyDataSetChanged();
    }

    private void searchBook(String book){
        Intent intent = new Intent(getContext(),SearchActivity.class);
        intent.putExtra(SearchActivity.BOOK_NAME,book);
        startActivity(intent);
    }


    private RecyclerRankingAdapter adapter;
    private EditText editText;
    private List<SearchLog> log;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SearchFragmentInter ) {
            mListener = (SearchFragmentInter) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface SearchFragmentInter{
       List<SearchLog> giveSearchLog();
    }


    private class RecyclerRankingAdapter extends RecyclerView.Adapter<RecyclerRankingAdapter.HolderView>{

        @Override
        public void onBindViewHolder(@NonNull HolderView holder, int position) {
            holder.log.setText(log.get(position).getSearch());
        }

        @NonNull
        @Override
        public HolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_log_item,parent,false);
            return  new HolderView(view);
        }

        @Override
        public int getItemCount() {
            if(log == null){
                return 0;
            }else{
                return log.size();
            }
        }

        class HolderView extends RecyclerView.ViewHolder{
            TextView log;

            HolderView(@NonNull View itemView) {
                super(itemView);
                log = itemView.findViewById(R.id.search_log_item_text);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        searchBook(log.getText().toString());
                    }
                });
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
