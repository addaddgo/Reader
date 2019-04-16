package com.example.hp.readingyouself.readingDataSupport.dataForm;

public class Rank {
    private String url;
    private BookSummary[] strings;

    public Rank(String name, BookSummary[] strings) {
        this.url = name;
        this.strings = strings;
    }

    public void setBooks(BookSummary[] strings) {
        this.strings = strings;
    }

    public String getName() {
        return url;
    }

    public BookSummary[] getBooks() {
        return strings;
    }

}
