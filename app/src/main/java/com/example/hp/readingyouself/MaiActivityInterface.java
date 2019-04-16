package com.example.hp.readingyouself;

public interface MaiActivityInterface {

    int BOOK_SHELF_FRAGMENT = 1;
    int RANKING_FRAGMENT = 2;
    int COMMUNITY_FRAGMENT = 3;
    //启动阅读活动

    void startReadActivity(String bookID);
    void startListInCategoryActivity(String major ,String mins);
}
