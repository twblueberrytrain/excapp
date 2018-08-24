package com.exc.louis.itunesapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.exc.louis.itunesapp.util.QuerySetting;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onQueryEvent(QuerySetting querySetting) {
        getItunesSongs(querySetting);
    }
}
