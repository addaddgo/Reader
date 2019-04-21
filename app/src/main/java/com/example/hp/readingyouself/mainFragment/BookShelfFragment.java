package com.example.hp.readingyouself.mainFragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.hp.readingyouself.MaiActivityInterface;
import com.example.hp.readingyouself.R;
import com.example.hp.readingyouself.readActivity.BookIntroductionActivity;
import com.example.hp.readingyouself.readingDataSupport.dataForm.BookShelfBook;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;


public class BookShelfFragment extends Fragment {


    private OnMaiActivityInteractionListener mListener;

    public BookShelfFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book_shelf, container, false);
        recyclerView = view.findViewById(R.id.book_shelf_recycler);
        return view;
    }



    private RecyclerView recyclerView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        bitmaps = new ArrayList<>();
        recyclerView.setLayoutManager(linearLayoutManager);
        bookShelfRecyclerAdapter = new BookShelfRecyclerAdapter();
        recyclerView.setAdapter(bookShelfRecyclerAdapter);
    }

    private BookShelfRecyclerAdapter bookShelfRecyclerAdapter;

    @Override
    public void onStart() {
        super.onStart();
        reStart();
    }

    //TODO introduction活动需要传入读到的页数
    private List<BookShelfBook> bookShelfBooks;
    private ArrayList<Bitmap> bitmaps;

    class BookShelfRecyclerAdapter extends RecyclerView.Adapter<BookShelfRecyclerAdapter.ViewHolder>{


        @Override
        public int getItemCount() {
            if(bookShelfBooks == null)return 0;else return bookShelfBooks.size();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.book_shelf_book_item,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                holder.imageView.setImageBitmap(bitmaps.get(position));
                StringBuilder builder = new StringBuilder(bookShelfBooks.get(position).getBookName());
                builder = builder.append("\n");
                if(bookShelfBooks.get(position).isCollect()){
                    builder = builder.append(getString(R.string.book_shelf_already_collect));
                    builder = builder.append("  ");
                    if(bookShelfBooks.get(position).isDownload()){
                        builder = builder.append(getString(R.string.book_shelf_already_download));
                    }
                }
                holder.textView.setText(builder);
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            int position;
            ImageView imageView;
            TextView textView;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                this.imageView = itemView.findViewById(R.id.book_shelf_book_cover);
                this.textView = itemView.findViewById(R.id.book_shelf_book_text);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(),BookIntroductionActivity.class);
                        intent.putExtra(BookIntroductionActivity.BOOK_ID,bookShelfBooks.get(position).getbookIdentify());
                        intent.putExtra(BookIntroductionActivity.CHAPTER_POSITION,bookShelfBooks.get(position).getCurrentChapterPosition());
                        startActivity(intent);
                    }
                });
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

    public void reStart(){
        bookShelfBooks = mListener.getBookShelf();
        if(bookShelfBooks != null){
            for (BookShelfBook bookShelfBook:bookShelfBooks) {
                byte[] bitmapByte = bookShelfBook.getCoverBitmap();
                Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapByte,0,bitmapByte.length);
                bitmaps.add(bitmap);
            }
            bookShelfRecyclerAdapter.notifyDataSetChanged();
        }
    }

    public interface OnMaiActivityInteractionListener extends MaiActivityInterface {

        List<BookShelfBook> getBookShelf();

    }
}
