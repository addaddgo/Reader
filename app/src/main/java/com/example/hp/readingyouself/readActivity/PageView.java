package com.example.hp.readingyouself.readActivity;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.hp.readingyouself.R;

import java.util.ArrayList;
import java.util.Collections;

public class PageView extends AppCompatTextView {

    public PageView(Context context) {
        super(context);
        setCustomSelectionActionModeCallback(new MyActionModeCallBack());
        initTag(context);
    }

    public PageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomSelectionActionModeCallback(new MyActionModeCallBack());
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs,R.styleable.PageView);
        tagColor = typedArray.getColor(R.styleable.PageView_tagColor,0x0);
        originalString = getText();
        if(getBackground() instanceof ColorDrawable){
           backgroundColor =  ((ColorDrawable)getBackground()).getColor();
        }
        searchColor = typedArray.getColor(R.styleable.PageView_searchColor,0x0);
        builder = new SpannableStringBuilder(getText());
        typedArray.recycle();
        initTag(context);
    }

    private CharSequence originalString;

    private void initTag(Context context){
        if(context instanceof PageViewInterface){
            this.Tags = ((PageViewInterface)context).setTag();
            if(this.Tags == null){
                this.Tags = new ArrayList<>();
            }
        }else{
            this.Tags = new ArrayList<>();
        }
        makeTagByList();
    }

    private int tagColor;
    private int backgroundColor;
    private int searchColor;

    SpannableStringBuilder builder;

    class MyActionModeCallBack extends ActionMode.Callback2{

        Menu menu;

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.page_text_select,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            this.menu = menu;
            return true;
        }
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            String txt = getText().toString();
            int start = getSelectionStart();
            int end = getSelectionEnd();
            if(callBack != null){
                callBack.onSelected(txt.substring(start,end));
            }
            switch (item.getItemId()){
                //已经自带复制功能，删除复制功能
//                case R.id.page_text_copy:
//                    //剪切板管理者
//                    ClipboardManager clipboardManager = (ClipboardManager)getContext().getSystemService(Context.CLIPBOARD_SERVICE);
//                    int start = getSelectionStart();
//                    int end = getSelectionEnd();
//                    String txt = getText().toString();
//                    String subString = txt.substring(start,end);
//                    //复制到剪切板
//                    clipboardManager.setPrimaryClip(ClipData.newPlainText(null,subString));
//                    break;
                case R.id.page_text_make_tag:
                    //直接使用close是无效的必须，改变textView
                    for (Tag list:Tags) {
                        if((list.start <= end && list.end >= end) || (list.start <= start && list.end >= start)){
                            Toast.makeText(getContext(),getContext().getString(R.string.warn_exist_tag),Toast.LENGTH_SHORT).show();
                            return true;
                        }
                    }
                    String suString = txt.substring(start,end);

                   //需要callback返回一个tag如果没有返回，不进行设置。
                    if(callBack != null){
                        callBack.onMakeNewTag(suString,start,end);
                    }
                    setText(builder);
                    menu.close();
                    return true;
                case R.id.page_text_search:
                    if(callBack != null)
                        callBack.onSearch();
                    return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
        }
    }

    private ArrayList<Tag> Tags;

    private void makeTagByList(){
        if(Tags != null){
            for (Tag list:Tags) {
                builder.setSpan(new BackgroundColorSpan(tagColor),list.start,list.end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            setText(builder);
        }
    }

    //当标签删除之后，需要从新设定Tag
    public void resetTag(ArrayList<Tag> Tags){
        this.Tags = Tags;
        builder = new SpannableStringBuilder(originalString);
        makeTagByList();
    }


    public void addNewTag(Tag tag){
        if(tag != null){
            Tags.add(tag);
            builder.setSpan(new BackgroundColorSpan(tagColor),tag.start,tag.end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            if(callBack != null)callBack.onMakeNewTagSuccessful();
            setText(builder);
        }

    }

    public void setOriginalString(CharSequence originalString) {
        this.originalString = originalString;
    }

    public CharSequence getOriginalString() {
        return originalString;
    }

    static public class Tag{
        String string;
        String myWord;
        int start;
        int end;
        public Tag(String string,String myWord,int start, int end) {
            this.string = string;
            this.start = start;
            this.end = end;
            this.myWord = myWord;
        }
    }

    public ArrayList<Tag> getTags() {
        return Tags;
    }

    //在开始创建时，如果需要设置标签，就必须继承此接口，并返回一个初始的ArrayList<Tag>
    interface PageViewInterface{
        ArrayList<Tag> setTag();

    }


    public interface CallBack{
        //做标记时的一个回调,需要返回一个Tag,如果返回null则不设置
         void onMakeNewTag(String tag,int start,int end);
        //搜索
        void onSearch();
        //返回选中的文字
         void onSelected(String string);

         void onMakeNewTagSuccessful();
    }

    private CallBack callBack;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    private ArrayList<Integer> integerArrayList = new ArrayList<>();
    private int lastSearchLength;

    public void setSearchColorBound(ArrayList<Integer> integers,int length){
        integerArrayList.clear();
        if(integers != null){
            for (int i :integers) {
                builder.setSpan(new BackgroundColorSpan(searchColor),i,i + length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            for (int i:integers) {
                Collections.addAll(integerArrayList,i);
            }
        }
        setText(builder);
        lastSearchLength = length;
    }

    public void wipeSearchColorBound(){
        if(integerArrayList != null){
            for (int i:integerArrayList) {
                builder.setSpan(new BackgroundColorSpan(backgroundColor),i,i+lastSearchLength,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        setText(builder);
    }
}
