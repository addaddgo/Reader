package com.example.hp.readingyouself.readingDataSupport.dataForm;

public class BookSummary {
    private String ID;
    private String title;
    private String author;
    private String shortIntroduction;

    public BookSummary(String ID, String title, String author, String shortIntroduction) {
        this.ID = ID;
        this.title = title;
        this.author = author;
        this.shortIntroduction = shortIntroduction;
    }

    public String getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getShortIntroduction() {
        return shortIntroduction;
    }
}
