package com.exc.louis.itunesapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.exc.louis.itunesapp.adapter.SongsAdapter;
import com.exc.louis.itunesapp.service.MyService;
import com.exc.louis.itunesapp.util.EbSongList;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.exc.louis.itunesapp.util.Constant.EB_RESPONSE_SONG_LIST;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private MyService myService;
    private boolean mBound = false;
    private RecyclerView rvSongList;
    private SongsAdapter songsAdapter;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected");
            myService = ((MyService.MyServiceBinder) service).getService();
            mBound = true;
            myService.getItunesSongs();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected");
            myService = null;
            mBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        doBind();
        findIds();
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        doUnBind();
    }

    private void findIds() {
        rvSongList = (RecyclerView) findViewById(R.id.rv_song_list_view);
    }

    private void init() {
        songsAdapter = new SongsAdapter(this);
        rvSongList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvSongList.setAdapter(songsAdapter);
    }

    private void doBind() {
        bindService(new Intent(this, MyService.class), mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private void doUnBind() {
        if (mBound) {
            unbindService(mServiceConnection);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void onEvent(EbSongList event) {
        switch (event.getMessage()) {
            default:
                break;
            case EB_RESPONSE_SONG_LIST:
                songsAdapter.setDataList(event.getSearchSongList().getResults());
                songsAdapter.notifyDataSetChanged();
                break;
        }
    }
}
