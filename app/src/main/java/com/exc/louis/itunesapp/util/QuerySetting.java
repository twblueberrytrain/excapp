package com.exc.louis.itunesapp.util;

public class QuerySetting {
    private String keyWord;
    private int limit;
    private String type;

    public QuerySetting(String keyWord) {
        this.keyWord = keyWord;
        limit = 20;
        type = "all";
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
