package com.example.hp.readingyouself;

public interface MaiActivityInterface {

    int BOOK_SHELF_FRAGMENT = 0;
    int SEARCH_FRAGMENT = 3;
    int CATEGORY_FRAGMENT = 1;
    int RANKING_FRAGMENT = 2;

    void startReadActivity(String bookID);
    void startListInCategoryActivity(String major ,String mins);
}
