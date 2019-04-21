package com.example.hp.readingyouself.readingDataSupport.dataForm;

import org.litepal.crud.DataSupport;

public class ChapterBodySaver extends DataSupport{
    private String link;
    private int position;
    private String title;
    private String bookIdentify;
    private ChapterBodyBean chapterBodyBean;
    public ChapterBodySaver(String bookIdentify, ChapterBodyBean chapterBodyBean,String title,int position,String link) {
        this.link = link;
        this.bookIdentify = bookIdentify;
        this.chapterBodyBean = chapterBodyBean;
        this.position = position;
        this.title = title;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBookIdentify() {
        return bookIdentify;
    }

    public void setBookIdentify(String bookIdentify) {
        this.bookIdentify = bookIdentify;
    }

    public ChapterBodyBean getChapterBodyBean() {
        return chapterBodyBean;
    }

    public void setChapterBodyBean(ChapterBodyBean chapterBodyBean) {
        this.chapterBodyBean = chapterBodyBean;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
