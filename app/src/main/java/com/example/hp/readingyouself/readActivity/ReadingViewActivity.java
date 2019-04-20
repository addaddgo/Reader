package com.example.hp.readingyouself.readActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.solver.widgets.Rectangle;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.readingyouself.BaseActivity;
import com.example.hp.readingyouself.R;
import com.example.hp.readingyouself.readingDataSupport.DataConnector;
import com.example.hp.readingyouself.readingDataSupport.dataForm.ChapterBodyBean;
import com.example.hp.readingyouself.readingDataSupport.dataForm.ChapterListBean;

import java.util.ArrayList;
import java.util.List;

public class ReadingViewActivity extends BaseActivity implements View.OnClickListener {

    public static final String BOOK_ID = "book_id";
    public static final String CHAPTER_POSITION = "chapter_position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_view);
        pager = findViewById(R.id.reading_view_viewpager);
        initViewPager();
        iniPopWindow();
    }


    //数据获得部分
    private String chapterName;
    private TextView title;
    private String currentChapterUrl;
    private String bookID;
    private int currentChapterPosition;

    @Override
    protected void initBeforeBindServiceOnCreated() {
        Intent intent = getIntent();
        bookID = intent.getStringExtra(BOOK_ID);
        currentChapterPosition = intent.getIntExtra(CHAPTER_POSITION,0);
    }

    //viewpager部分
    private void initViewPager(){
        readingView = getLayoutInflater().inflate(R.layout.reading_view,pager,false);
        View tagView = getLayoutInflater().inflate(R.layout.reading_tag_view,pager,false);
        View chapterView = getLayoutInflater().inflate(R.layout.reading_chapter_list_view,pager,false);
        RecyclerView recyclerView = chapterView.findViewById(R.id.reading_chapter_list_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        chapterAdapter = new ChapterAdapter(null);
        recyclerView.setAdapter(chapterAdapter);
        ArrayList<View> views = new ArrayList<>();
        views.add(chapterView);
        views.add(readingView);
        views.add(tagView);
        pager.setAdapter(new PagerAdapter(views));
        pager.setCurrentItem(1);
        pageView = readingView.findViewById(R.id.reading_view_page);
        title = readingView.findViewById(R.id.reading_view_tittle);
        initTagView(tagView);
        initReadingView(readingView);
        initTagInputPopup();
    }

    class PagerAdapter extends android.support.v4.view.PagerAdapter{

        ArrayList<View> views;

        PagerAdapter(ArrayList<View> views) {
            super();
            this.views = views;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public int getCount() {
            if(views == null)return 0;else return views.size();
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(views.get(position));
        }
    }

    private ChapterBodyBean chapterBodyBean;
    private ViewPager pager;
    private DataConnector dataConnector;
    private ChapterListBean chapterListBean;
    private PageView pageView;
    private View readingView;
    private ChapterAdapter chapterAdapter;
    private ArrayList<PageView.Tag> tags;

    //数据传递
    @Override
    protected void onHandleMessage(Message msg) {
        if(msg.obj instanceof ChapterBodyBean){
                this.chapterBodyBean = (ChapterBodyBean) msg.obj;
                this.title.setText(chapterName);//返回文件并没有tittle,应该在chapterList中获得
                this.pageView.setText(chapterBodyBean.getChapter().getBody());
                this.pageView.setOriginalString(pageView.getText());
                pageView.resetTag(tags);
        }
        if(msg.obj instanceof ChapterListBean){
          this.chapterListBean = (ChapterListBean)msg.obj;
          this.chapterAdapter.setChapters(chapterListBean.getMixToc().getChapters());
          this.chapterAdapter.notifyDataSetChanged();
          currentChapterUrl = chapterListBean.getMixToc().getChapters().get(currentChapterPosition).getLink();
          dataConnector.sendChapterBodyBean(currentChapterUrl);
            chapterName = chapterListBean.getMixToc().getChapters().get(currentChapterPosition).getTitle();
            tags = dataConnector.getTagListByChapter(bookID,chapterName);
          tagAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDataServiceConnect() {
        dataConnector = getDataConnector();
        //tags = dataConnector.getTagListByChapter(BOOK_ID,chapterName);
        //dataConnector.sendChapterBodyBean(currentChapterUrl);
        dataConnector.sendChapterListByBean(bookID);
        //tagAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDataServiceDisconnect() {
        dataConnector = null;
    }

    class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ViewHolder>{

        private List<ChapterListBean.MixTocBean.ChaptersBean> chapters;

        ChapterAdapter(ArrayList<ChapterListBean.MixTocBean.ChaptersBean> chapters) {
            this.chapters = chapters;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.textView.setText(chapters.get(position).getTitle());
            holder.position = position;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.chapers_list_item,parent,false);
            return new ViewHolder(view);
        }


        @Override
        public int getItemCount() {
            if(chapters != null){
                return chapters.size();
            }else{
                return 0;
            }
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            TextView textView;
            int position;

            ViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.chapter_name);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        currentChapterUrl = chapterListBean.getMixToc().getChapters().get(0).getLink();
                        currentChapterPosition = position;
                        chapterName = chapterListBean.getMixToc().getChapters().get(position).getTitle();
                        tags = dataConnector.getTagListByChapter(bookID,chapterName);
                        dataConnector.sendChapterBodyBean(currentChapterUrl);
                        pager.setCurrentItem(1);
                    }
                });
            }
        }
        void setChapters(List<ChapterListBean.MixTocBean.ChaptersBean> chapters) {
            this.chapters = chapters;
        }
    }

    private void initReadingView(View view){
        Button next = view.findViewById(R.id.reading_view_next_chapter_button);
        Button pre = view.findViewById(R.id.reading_view_pre_chapter_button);
        next.setOnClickListener(this);
        pre.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(this.chapterListBean == null || this.chapterListBean.getMixToc() == null){
            return;
        }
        List<ChapterListBean.MixTocBean.ChaptersBean> chapters = this.chapterListBean.getMixToc().getChapters();
        switch (v.getId()){
            case R.id.reading_view_next_chapter_button:
                if(currentChapterPosition + 1 <= chapters.size()){
                    dataConnector.sendChapterBodyBean(chapters.get(++currentChapterPosition).getLink());
                    chapterName = chapters.get(currentChapterPosition).getTitle();
                    title.setText(chapterName);
                }else{
                    Toast.makeText(this,getString(R.string.end_chapter),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.reading_view_pre_chapter_button:
                if(currentChapterPosition - 1 >= 0){
                    dataConnector.sendChapterBodyBean(chapters.get(--currentChapterPosition).getLink());
                    chapterName = chapters.get(currentChapterPosition).getTitle();
                    title.setText(chapterName);
                }else{
                    Toast.makeText(this,getString(R.string.start_chapter),Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    //tag_view
    private TagAdapter tagAdapter;

    private void initTagView(View view){
        tagAdapter = new TagAdapter();
        RecyclerView recyclerView = view.findViewById(R.id.reading_tag_view_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(tagAdapter);
    }



    //search功能
    class TagAdapter extends RecyclerView.Adapter<TagAdapter.ViewHolder>{

            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                holder.tagText.setText(tags.get(position).string);
                holder.myWordText.setText(tags.get(position).myWord);
                holder.position = position;
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.reading_view_tag_item,parent,false);
                return new ViewHolder(view);
            }


            @Override
            public int getItemCount() {
                if(tags != null){
                    return tags.size();
                }else{
                    return 0;
                }
            }
            class ViewHolder extends RecyclerView.ViewHolder{
                TextView tagText;
                TextView forgotText;
                TextView myWordText;
                int position;

                ViewHolder(@NonNull View itemView) {
                    super(itemView);
                    tagText = itemView.findViewById(R.id.reading_tag_text);
                    forgotText = itemView.findViewById(R.id.reading_tag_view_forgot);
                    myWordText = itemView.findViewById(R.id.reading_tag_my_word_text);
                    forgotText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tags.remove(position);
                            pageView.resetTag(tags);
                            notifyDataSetChanged();
                        }
                    });
                }
            }
        }

        //当前的搜索内容
    private  ArrayList<Integer> searchPositionStarts = new ArrayList<>();
    private  String currentSearch;
    private  int currentPosition;


    private void iniPopWindow(){
        callBack = new PageViewCall();
        pageView.setCallBack(callBack);
        final View contentView = LayoutInflater.from(this).inflate(R.layout.reading_search_popup,null,false);
        nextSearch = contentView.findViewById(R.id.reading_search_next);
        preSearch = contentView.findViewById(R.id.reading_search_pre);
        searchEdit = contentView.findViewById(R.id.reading_search_edit);
        popupWindowSearch = new PopupWindow(contentView,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindowSearch.setOutsideTouchable(true);
        popupWindowSearch.setTouchable(true);
        popupWindowSearch.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindowSearch.setFocusable(true);
        searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                currentSearch = s.toString();
                searchString(currentSearch);
                pageView.setSearchColorBound(searchPositionStarts,s.length());
                currentPosition = 0;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        preSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPosition - 1 >= 0){
                    currentPosition--;
                    jumpToPageView(searchPositionStarts.get(currentPosition));
                }
            }
        });
        nextSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPosition + 1 < searchPositionStarts.size()){
                    currentPosition++;
                    jumpToPageView(searchPositionStarts.get(currentPosition));
                }
            }
        });
        popupWindowSearch.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                pageView.wipeSearchColorBound();
            }
        });
    }

    //搜索字符串，返回其位置
    private void searchString(String string){
        searchPositionStarts.clear();
        if(string.equals("\n") || string.equals(" ") || string.equals("\t") || string.equals(""))return;
        String stringOriginal = pageView.getOriginalString().toString();
        int  currentPosition = stringOriginal.indexOf(string);
        if(currentPosition == -1)return;
        do {
            currentPosition = stringOriginal.indexOf(string,currentPosition + string.length());
            if(currentPosition == -1)break;
            else {
                searchPositionStarts.add(currentPosition);
            }
        }while (true);
    }

    //pageView实现跳转功能
    private void jumpToPageView(int position){
            Layout layout = pageView.getLayout();
            int line = layout.getLineForOffset(position);
            Rect bound = new Rect();
            layout.getLineBounds(line,bound);
            pageView.scrollTo(bound.left,bound.top);
    }

    private PopupWindow popupWindowSearch;
    private EditText searchEdit;
    private Button nextSearch;
    private Button preSearch;

    private void showPopWindow(){
        searchEdit.setText(selectedString);
        searchString(selectedString);
        pageView.setSearchColorBound(searchPositionStarts,selectedString.length());
        popupWindowSearch.showAtLocation(readingView,Gravity.TOP,0,0);
    }

    private String selectedString;

    private PageViewCall callBack;
    //pageView的回调
     class PageViewCall implements PageView.CallBack{

         private String tag;
         private int start;
         private int end;

        public String getTag() {
            return tag;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        @Override
        public void onMakeNewTag(String tag, int start, int end) {
            this.tag = tag;
            this.start = start;
            this.end = end;
            showMakeTagPopup();
        }

        @Override
        public void onMakeNewTagSuccessful() {
            tagAdapter.notifyDataSetChanged();
        }

        @Override
        public void onSearch() {
            showPopWindow();
        }

        @Override
        public void onSelected(String string) {
            selectedString = string;
        }
    }
    //返回的数据

    //Tag输入框
    private EditText tagEdit;
    private PopupWindow tagPopup;

    private void initTagInputPopup(){
        final View contentView = LayoutInflater.from(this).inflate(R.layout.make_tag_poppup,null,false);
        tagEdit = contentView.findViewById(R.id.make_tag_popup_edit);
        tagPopup = new PopupWindow(contentView,WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        tagPopup.setOutsideTouchable(true);
        tagPopup.setFocusable(true);
        tagPopup.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        Button ensureMakeTag = contentView.findViewById(R.id.make_tag_popup_ensure);
        ensureMakeTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editString = tagEdit.getText().toString();
                if(callBack != null && pageView != null){
                    pageView.addNewTag(new PageView.Tag(callBack.getTag(),editString,callBack.getStart(),callBack.getEnd()));
                }
                tagEdit.setText(null);
                tagPopup.dismiss();
            }
        });
    }
    //tagpop似乎不在主线程中弹出
    private void showMakeTagPopup(){
        tagPopup.showAtLocation(pageView,Gravity.CENTER,0,0);
    }

    //结束之前要保存tagList还要判读如果已经收藏的话，需要保存看过的章节。
    private void saveInEnd(){

    }
}
