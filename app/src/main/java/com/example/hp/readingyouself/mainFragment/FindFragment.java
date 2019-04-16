package com.example.hp.readingyouself.mainFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.hp.readingyouself.R;
import com.example.hp.readingyouself.findActivity.CategoryActivity;
import com.example.hp.readingyouself.findActivity.RankingActivity;

public class FindFragment extends Fragment implements View.OnClickListener {


    public FindFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_find,container,false);
        LinearLayout category = view.findViewById(R.id.find_category);
        LinearLayout ranking = view.findViewById(R.id.find_ranking);
        category.setOnClickListener(this);
        ranking.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.find_category:
                    intent = new Intent(getContext(),CategoryActivity.class);
                    startActivity(intent);
                break;
            case R.id.find_ranking:
                intent = new Intent(getContext(),RankingActivity.class);
                startActivity(intent);
                break;
        }
    }
}
