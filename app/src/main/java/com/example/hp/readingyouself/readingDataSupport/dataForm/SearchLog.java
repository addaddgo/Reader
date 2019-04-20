package com.example.hp.readingyouself.readingDataSupport.dataForm;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;

public class SearchLog extends DataSupport {
     private String search;
     private long time;

    public SearchLog(String search, long time) {
        this.search = search;
        this.time = time;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
