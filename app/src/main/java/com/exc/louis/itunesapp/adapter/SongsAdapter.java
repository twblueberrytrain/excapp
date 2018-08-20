package com.exc.louis.itunesapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exc.louis.itunesapp.R;
import com.exc.louis.itunesapp.gson.SongInformation;

import java.util.ArrayList;
import java.util.List;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.ItemRowHolder>{
    private static final String TAG = "SongsAdapter";
    private List<SongInformation> dataList = null;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public SongsAdapter(Context context) {
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public List<SongInformation> getDataList() {
        return dataList;
    }

    public void setDataList(List<SongInformation> dataList) {
        this.dataList = dataList;
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = mLayoutInflater.inflate(R.layout.row_song_information, viewGroup, false);
        ItemRowHolder mh = new ItemRowHolder(v);
        mh.itemView.setOnClickListener(mh);
        return mh;
    }
    @Override
    public void onBindViewHolder(ItemRowHolder itemRowHolder, int i) {
        if (dataList == null || dataList.isEmpty()) return;
        itemRowHolder.songName.setText(dataList.get(i).getTrackName());
        itemRowHolder.artistName.setText(dataList.get(i).getArtistName());
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView songName;
        TextView artistName;

        public ItemRowHolder(View view) {
            super(view);
            songName = (TextView) view.findViewById(R.id.txt_song_name);
            artistName = (TextView) view.findViewById(R.id.txt_artis_name);
        }

        @Override
        public void onClick(View v) {

        }

    }
}
