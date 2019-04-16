package com.example.hp.readingyouself.mainFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.hp.readingyouself.commentActivity.BookCommentActivity;
import com.example.hp.readingyouself.commentActivity.ComprehensiveCommentActivity;
import com.example.hp.readingyouself.R;
import com.example.hp.readingyouself.commentActivity.OriginalCommentActivity;
import com.example.hp.readingyouself.commentActivity.RecommendBookActivity;


public class CommunityFragment extends Fragment implements View.OnClickListener {

    public CommunityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community, container, false);
        setListener(view);
        return view;
    }


    private void setListener(View view){
        LinearLayout comprehensiveComment = view.findViewById(R.id.community_comprehensive_comment);
        LinearLayout bookComment = view.findViewById(R.id.community_book_comment);
        LinearLayout recommendBookComment = view.findViewById(R.id.community_recommend_book_comment);
        LinearLayout originalComment = view.findViewById(R.id.community_original_comment);
        comprehensiveComment.setOnClickListener(this);
        bookComment.setOnClickListener(this);
        recommendBookComment.setOnClickListener(this);
        originalComment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.community_comprehensive_comment:
                intent = new Intent(getContext(),ComprehensiveCommentActivity.class);
                startActivity(intent);
                break;
            case R.id.community_book_comment:
                intent = new Intent(getContext(),BookCommentActivity.class);
                startActivity(intent);
                break;
            case R.id.community_recommend_book_comment:
                intent = new Intent(getContext(),RecommendBookActivity.class);
                startActivity(intent);
                break;
            case R.id.community_original_comment:
                intent = new Intent(getContext(),OriginalCommentActivity.class);
                startActivity(intent);
                break;
        }
    }

}
