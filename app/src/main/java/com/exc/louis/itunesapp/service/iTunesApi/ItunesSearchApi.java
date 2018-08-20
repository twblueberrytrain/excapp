package com.exc.louis.itunesapp.service.iTunesApi;

import com.exc.louis.itunesapp.gson.SearchSongList;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ItunesSearchApi {
    @GET("search?")
    Call<SearchSongList> getSongList(@QueryMap Map<String,String> params);
}
