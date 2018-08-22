package com.exc.louis.itunesapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.exc.louis.itunesapp.adapter.SongsAdapter;
import com.exc.louis.itunesapp.gson.SongInformation;
import com.exc.louis.itunesapp.service.MyService;
import com.exc.louis.itunesapp.util.EbClickCallback;
import com.exc.louis.itunesapp.util.EbSongList;
import com.exc.louis.itunesapp.util.QuerySetting;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.exc.louis.itunesapp.util.Constant.EB_RESPONSE_SONG_LIST;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    private MyService myService;
    private boolean mBound = false;
    private EditText etKeyWord;
    private RecyclerView rvSongList;
    private SongsAdapter songsAdapter;
    private ProgressDialog progressDialog = null;
    private String theKeyWord = "";

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected");
            myService = ((MyService.MyServiceBinder) service).getService();
            mBound = true;
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
        etKeyWord = (EditText) findViewById(R.id.ed_key_word_input);
    }

    private void init() {
        songsAdapter = new SongsAdapter(this);
        rvSongList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvSongList.setAdapter(songsAdapter);


        etKeyWord.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    theKeyWord = textView.getText().toString();
                    if (!theKeyWord.isEmpty()) {
                        showProgressDialog();
                        sendKeyWordToQuery(theKeyWord);
                    }
                    return true;
                }
                return false;
            }
        });
    }

    private void doBind() {
        bindService(new Intent(this, MyService.class), mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private void doUnBind() {
        if (mBound) {
            unbindService(mServiceConnection);
        }
    }

    private void sendKeyWordToQuery(String keyWord) {
        if (!mBound) return;
        QuerySetting querySetting = new QuerySetting(keyWord);
        myService.getItunesSongs(querySetting);
    }

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Please wait.");
        progressDialog.setMessage("Search songs : \"" + theKeyWord + "\".");
        progressDialog.show();
    }

    private void closeProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private void showSongDetail(View view) {
        int position = rvSongList.getChildAdapterPosition(view);
        if (position < 0) return;
        final SongInformation songInformation = songsAdapter.getDataList().get(position);
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(R.layout.dialog_song_detail)
                .setPositiveButton("Complete", null)
                .setCancelable(true)
                .create();
        final MediaPlayer mediaPlayer = new MediaPlayer();
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if (mediaPlayer.isPlaying() || mediaPlayer.isLooping()) {
                    mediaPlayer.stop();
                }
            }
        });
        alertDialog.show();
        TextView detailSongName = alertDialog.findViewById(R.id.txt_detail_song_name);
        TextView detailArtistName = alertDialog.findViewById(R.id.txt_detail_artist_name);
        ImageView ivCoverInDialog = alertDialog.findViewById(R.id.iv_detail_cover);
        ImageView ivPlayOrStop = alertDialog.findViewById(R.id.iv_play_or_stop);
        detailSongName.setText(songInformation.getTrackName());
        detailArtistName.setText(songInformation.getArtistName());

        ImageView ivCoverInList = view.findViewById(R.id.iv_song_image);
        BitmapDrawable drawable = (BitmapDrawable) ivCoverInList.getDrawable();
        Bitmap cover = drawable.getBitmap();
        ivCoverInDialog.setImageBitmap(cover);

        ivPlayOrStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (!mediaPlayer.isPlaying()) {
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        mediaPlayer.setDataSource(songInformation.getPreviewUrl());
                        mediaPlayer.prepare(); // might take long! (for buffering, etc)
                        mediaPlayer.start();
                    } else if (mediaPlayer.isPlaying()){
                        mediaPlayer.stop();
                    } else if (mediaPlayer.isLooping()) {
                        mediaPlayer.stop();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void onEvent(EbSongList event) {
        switch (event.getMessage()) {
            default:
                break;
            case EB_RESPONSE_SONG_LIST:
                closeProgressDialog();
                songsAdapter.setDataList(event.getSearchSongList().getResults());
                songsAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void onEvent(EbClickCallback event){
        showSongDetail(event.getView());
    }
}
