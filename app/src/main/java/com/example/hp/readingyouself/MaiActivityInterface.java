package com.example.hp.readingyouself;

public interface MaiActivityInterface {

    int BookShelfFragment = 1;
    int RankingFragment = 2;
    int CategoryFragment = 3;
    //启动阅读活动

    void startReadActivity(String bookID);
    void startListInCategoryActivity(String major ,String mins);
}
