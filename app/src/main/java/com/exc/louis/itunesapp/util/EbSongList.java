package com.exc.louis.itunesapp.util;

import com.exc.louis.itunesapp.gson.SearchSongList;

public class EbSongList {
    private SearchSongList searchSongList;
    private String message;

    public EbSongList(String message) {
        this.message = message;
    }

    public SearchSongList getSearchSongList() {
        return searchSongList;
    }

    public void setSearchSongList(SearchSongList searchSongList) {
        this.searchSongList = searchSongList;
    }

    public String getMessage() {
        return message;
    }
}
