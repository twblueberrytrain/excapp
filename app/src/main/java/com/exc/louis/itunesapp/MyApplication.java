package com.exc.louis.itunesapp;

import android.app.Application;
import android.content.Intent;

import com.exc.louis.itunesapp.service.HttpConnector;
import com.exc.louis.itunesapp.service.MyService;

public class MyApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        HttpConnector.getInstance().init(this);
    }
}
