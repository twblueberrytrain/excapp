package com.exc.louis.itunesapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.exc.louis.itunesapp.util.QuerySetting;

import java.util.HashMap;
import java.util.Map;

public class MyService extends Service {
    private static final String TAG = "MyService";

    private final MyServiceBinder myServiceBinder = new MyServiceBinder();

    public class MyServiceBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }


    @Override
    public IBinder onBind(Intent intent) {
        return myServiceBinder;
    }

    public void getItunesSongs(QuerySetting querySetting) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("term", querySetting.getKeyWord());
        map.put("limit", "50");
        map.put("media", "music");
        HttpConnector.getInstance().searchItunes(map);
    }
}
