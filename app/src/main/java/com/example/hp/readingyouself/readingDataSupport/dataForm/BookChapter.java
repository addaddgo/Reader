package com.example.hp.readingyouself.readingDataSupport.dataForm;

public class BookChapter {
    private String bookName;
    private String link;
    private String title;

    public BookChapter(String link, String title,String bookName){
        this.bookName = bookName;
        this.link = link;
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
