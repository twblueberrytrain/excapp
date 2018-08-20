package com.exc.louis.itunesapp.service;

import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class MyCallback <T> implements Callback<T> {
    private static final String TAG = "MyCallback";

    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        if (response.code() == 200){
            onSuccessful(call,response);
        }else {
            onFail(call,null,response);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onFail(call,t,null);
    }

    public abstract void onSuccessful(Call<T> call, Response<T> response);

    protected  void  onFail(Call<T> call , Throwable t, Response<T> response){
        if (response == null){
            Log.e(TAG, "null response : " + t.toString());
            return;
        }
        Log.e(TAG,"RESPONSE code is "+response.code()+": "+ response.raw().toString());
    }
}
