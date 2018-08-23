package com.exc.louis.itunesapp.service;

import android.content.Context;
import android.util.Log;

import com.exc.louis.itunesapp.gson.SearchSongList;
import com.exc.louis.itunesapp.service.iTunesApi.ItunesSearchApi;
import com.exc.louis.itunesapp.util.EbSongList;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.exc.louis.itunesapp.util.Constant.EB_RESPONSE_SONG_LIST;

public class HttpConnector {
    private static final String TAG = "HttpConnector";

    private static String ITUNES_URL = "https://itunes.apple.com/";
    private static volatile HttpConnector sInstance;
    private Context mContext;
    private OkHttpClient mOkHttpClient;
    private Retrofit mRetrofit;
    private ItunesSearchApi mSearchApi;
    private final static HttpLoggingInterceptor HTTP_LOGGING_INTERCEPTOR =
            new HttpLoggingInterceptor(message -> Log.d("Http:", message))
                    .setLevel(HttpLoggingInterceptor.Level.BODY);

    private HttpConnector() {
        mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(HTTP_LOGGING_INTERCEPTOR)
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(ITUNES_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mSearchApi = mRetrofit.create(ItunesSearchApi.class);
    }

    public static HttpConnector getInstance() {
        if (sInstance == null) {
            synchronized (HttpConnector.class) {
                if (sInstance == null) {
                    sInstance = new HttpConnector();
                }
            }
        }
        return sInstance;
    }

    public void init(Context context) {
        mContext = context;
    }

    public void searchItunes(Map<String, String> paramsMap) {
        Call<SearchSongList> call = mSearchApi.getSongList(paramsMap);
        call.enqueue(new MyCallback<SearchSongList>() {
            @Override
            public void onSuccessful(Call<SearchSongList> call, Response<SearchSongList> response) {
                Log.d(TAG, "onSuccessful");
                EbSongList event = new EbSongList(EB_RESPONSE_SONG_LIST);
                event.setSearchSongList(response.body());
                EventBus.getDefault().post(event);
            }
        });
    }
}
