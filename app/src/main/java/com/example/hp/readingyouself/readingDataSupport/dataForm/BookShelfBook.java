package com.example.hp.readingyouself.readingDataSupport.dataForm;

import org.litepal.crud.DataSupport;

public class BookShelfBook extends DataSupport {

    private String bookName;
    private String bookIdentify;
    private byte[] coverBitmap;
    private int currentChapterPosition;
    private boolean download;
    private boolean collect;

    public BookShelfBook(String bookName,String bookIdentify, byte[] coverBitmap, int currentChapterPosition, boolean download, boolean collect) {
        this.bookName = bookName;
        this.bookIdentify = bookIdentify;
        this.coverBitmap = coverBitmap;
        this.currentChapterPosition = currentChapterPosition;
        this.download = download;
        this.collect = collect;
    }

    public String getbookIdentify() {
        return bookIdentify;
    }

    public void setbookIdentify(String bookIdentify) {
        this.bookIdentify = bookIdentify;
    }

    public byte[] getCoverBitmap() {
        return coverBitmap;
    }

    public void setCoverBitmap(byte[] coverBitmap) {
        this.coverBitmap = coverBitmap;
    }

    public int getCurrentChapterPosition() {
        return currentChapterPosition;
    }

    public void setCurrentChapterPosition(int currentChapterPosition) {
        this.currentChapterPosition = currentChapterPosition;
    }

    public boolean isDownload() {
        return download;
    }

    public void setDownload(boolean download) {
        this.download = download;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
