package com.exc.louis.itunesapp.gson;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SearchSongList {
    @SerializedName("resultCount")
    private int resultCount;

    @SerializedName("results")
    List<SongInformation> results = new ArrayList<>();

    SearchSongList(List<SongInformation> list) {
        this.results = list;
    }

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public List<SongInformation> getResults() {
        return results;
    }

    public void setResults(List<SongInformation> results) {
        this.results = results;
    }
}
