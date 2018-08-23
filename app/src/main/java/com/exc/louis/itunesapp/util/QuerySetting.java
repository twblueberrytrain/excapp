package com.exc.louis.itunesapp.util;

public class QuerySetting {
    private String keyWord;
    private int limit;

    public QuerySetting(String keyWord) {
        this.keyWord = keyWord;
        limit = 20;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
