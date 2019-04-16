package com.example.hp.readingyouself.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hp.readingyouself.R;
import com.example.hp.readingyouself.readingDataSupport.dataForm.Rank;

import  com.example.hp.readingyouself.readingDataSupport.DataConnector;
import com.example.hp.readingyouself.MaiActivityInterface;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnMaiActivityInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class RankingFragment extends Fragment implements View.OnClickListener {

    private OnMaiActivityInteractionListener mListener;

    public RankingFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_ranking, container, false);
        initWidget(view);
        return view;
    }


    //all the widget which need changing
    private TextView maleWeekRanking;
    private TextView maleMonthRanking;
    private TextView maleTotalRanking;
    private TextView femaleWeekRanking;
    private TextView femaleMonthRanking;
    private TextView femaleTotalRanking;
    private RecyclerView recyclerView;
    private RecyclerRankingAdapter recyclerRankingAdapter;
    private EditText editText;

    private Rank rank;



    private void initWidget(View view){
        maleWeekRanking = view.findViewById(R.id.male_week_ranking);
        maleMonthRanking = view.findViewById(R.id.male_month_ranking);
        maleTotalRanking = view.findViewById(R.id.male_total_ranking);
        femaleWeekRanking = view.findViewById(R.id.female_week_ranking);
        femaleMonthRanking = view.findViewById(R.id.female_month_ranking);
        femaleTotalRanking = view.findViewById(R.id.female_total_ranking);
        recyclerView = view.findViewById(R.id.ranking_recycler);
        editText = view.findViewById(R.id.search_book_ranking);

        //add listener
        maleWeekRanking.setOnClickListener(this);
        maleMonthRanking.setOnClickListener(this);
        maleTotalRanking.setOnClickListener(this);
        femaleWeekRanking.setOnClickListener(this);
        femaleMonthRanking.setOnClickListener(this);
        femaleTotalRanking.setOnClickListener(this);

        //recycler
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mListener.getRank(DataConnector.MALE_HOTTEST_RANKING_WEEK);
        currentRanking = DataConnector.MALE_HOTTEST_RANKING_WEEK;
        recyclerRankingAdapter = new RecyclerRankingAdapter(rank);
        recyclerView.setAdapter(recyclerRankingAdapter);

        //EditText
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(KeyEvent.KEYCODE_ENTER == keyCode){
                    mListener.getRankBySearch(editText.getText().toString());
                    currentRanking = -1;
                }
                return false;
            }
        });
    }

    private int currentRanking;
    @Override
    public void onClick(View v) {
        Rank rank;
        switch (v.getId()){
            case R.id.male_week_ranking :
                if(currentRanking != DataConnector.MALE_HOTTEST_RANKING_WEEK) {
                    currentRanking = DataConnector.MALE_HOTTEST_RANKING_WEEK;
                    mListener.getRank(DataConnector.MALE_HOTTEST_RANKING_WEEK);
                }
                break;
            case R.id.male_month_ranking :
                if(currentRanking != DataConnector.MALE_HOTTEST_RANKING_MONTH) {
                    currentRanking = DataConnector.MALE_HOTTEST_RANKING_MONTH;
                    mListener.getRank(DataConnector.MALE_HOTTEST_RANKING_MONTH);
                }
                break;
            case R.id.male_total_ranking :
                if(currentRanking != DataConnector.MALE_HOTTEST_RANKING_TOTAL) {
                    currentRanking = DataConnector.MALE_HOTTEST_RANKING_TOTAL;
                    mListener.getRank(DataConnector.MALE_HOTTEST_RANKING_TOTAL);
                }
                break;
            case R.id.female_week_ranking :
                if(currentRanking != DataConnector.FEMALE_HOTTEST_RANKING_WEEK) {
                    currentRanking = DataConnector.FEMALE_HOTTEST_RANKING_WEEK;
                    mListener.getRank(DataConnector.FEMALE_HOTTEST_RANKING_WEEK);
                }
                break;
            case R.id.female_month_ranking :
                if(currentRanking != DataConnector.FEMALE_HOTTEST_RANKING_MONTH) {
                    currentRanking = DataConnector.FEMALE_HOTTEST_RANKING_MONTH;
                    mListener.getRank(DataConnector.FEMALE_HOTTEST_RANKING_MONTH);
                }
                break;
            case R.id.female_total_ranking :
                if(currentRanking != DataConnector.FEMALE_HOTTEST_RANKING_TOTAL) {
                    currentRanking = DataConnector.FEMALE_HOTTEST_RANKING_TOTAL;
                    mListener.getRank(DataConnector.FEMALE_HOTTEST_RANKING_TOTAL);
                }
                break;
        }
    }

    private class RecyclerRankingAdapter extends RecyclerView.Adapter<RecyclerRankingAdapter.HolderView>{
        private Rank rank;

        public RecyclerRankingAdapter(Rank rank) {
            super();
            this.rank = rank;
        }


        @Override
        public void onBindViewHolder(@NonNull HolderView holder, int position) {
            String shortIntro = getString(R.string.short_introduction_ranking) + rank.getBooks()[position].getShortIntroduction();
            holder.shortIntroduction.setText(shortIntro);
            String author = getString(R.string.author_ranking)+ rank.getBooks()[position].getAuthor();
            holder.author.setText(author);
            String title = getString(R.string.title_ranking) + rank.getBooks()[position].getTitle();
            holder.title.setText(title);
            holder.position = position;
        }

        @NonNull
        @Override
        public HolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_in_ranking_item,parent,false);
          final HolderView holderView = new HolderView(view);
          view.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if(rank != null){
                      mListener.startReadActivity(rank.getBooks()[holderView.position].getID());
                  }

              }
          });
          return holderView;
        }


        @Override
        public int getItemCount() {
            if(rank == null){
                return 0;
            }else{
                return rank.getBooks().length;
            }
        }

        public void reSetRank(Rank rank){
            if(rank != null){
                this.rank = rank;
                this.notifyDataSetChanged();
            }
        }

        class HolderView extends RecyclerView.ViewHolder{

            int position;
            TextView title;
            TextView author;
            TextView shortIntroduction;

            public HolderView(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.title_item_ranking);
                author = itemView.findViewById(R.id.author_item_ranking);
                shortIntroduction = itemView.findViewById(R.id.short_introduction_item_ranking);
            }
        }

    }


    public void reSetRank(Rank rank){
        this.recyclerRankingAdapter.reSetRank(rank);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnMaiActivityInteractionListener extends MaiActivityInterface {
        void getRank(int rankType);
        void getRankBySearch(String string);
    }
}
