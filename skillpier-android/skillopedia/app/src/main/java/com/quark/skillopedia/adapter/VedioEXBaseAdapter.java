// Copyright 2014 Google Inc. All Rights Reserved.

package com.quark.skillopedia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.quark.skillopedia.R;
import com.quark.skillopedia.uiview.youtubeVideos.VideoEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Static container class for holding a reference to your YouTube Developer Key.
 */
public class VedioEXBaseAdapter extends BaseAdapter {


//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.video_mylist_item, null));
//    }

//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        VideoEntry entry = entries.get(position);
//            YouTubeThumbnailLoader loader = thumbnailViewToLoaderMap.get(holder.thumbnail);
//            if (loader == null) {
//                holder.thumbnail.setTag(entry.getVideoId());
//            } else {
//                holder.thumbnail.setImageResource(R.drawable.loading_thumbnail);
//                loader.setVideo(entry.getVideoId());
//            }
//    }

//    @Override
//    public int getItemCount() {
//        return entries.size();
//    }

//    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        YouTubeThumbnailView thumbnail;
//        TextView nodata;
//        public ViewHolder(View itemView) {
//            super(itemView);
//            thumbnail = (YouTubeThumbnailView) itemView.findViewById(R.id.thumbnail);
//            thumbnail.setOnClickListener(this);
//            nodata = (TextView)itemView.findViewById(R.id.nodata);
//        }
//
//        @Override
//        public void onClick(View v) {
//            if (clickListener != null) {
//                clickListener.onClick(itemView, getAdapterPosition());
//            }
//        }
//    }

//    private OnItemClickListener clickListener;
//
//    public void setItemClickListener(OnItemClickListener clickListener) {
//        this.clickListener = clickListener;
//    }
//
//    public static interface OnItemClickListener {
//        void onClick(View view, int position);
//    }

    private final List<VideoEntry> entries;
    private final List<View> entryViews;
    private final Map<YouTubeThumbnailView, YouTubeThumbnailLoader> thumbnailViewToLoaderMap;
    private final LayoutInflater inflater;
    private final ThumbnailListener thumbnailListener;
    private boolean labelsVisible;
    Context context;

    public VedioEXBaseAdapter(Context context, List<VideoEntry> entries) {
        this.entries = entries;

        entryViews = new ArrayList<View>();
        thumbnailViewToLoaderMap = new HashMap<YouTubeThumbnailView, YouTubeThumbnailLoader>();
        inflater = LayoutInflater.from(context);
        thumbnailListener = new ThumbnailListener();
        this.context = context;
        labelsVisible = true;
    }

    @Override
    public int getCount() {
        return entries.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.video_mylist_item, null);
        YouTubeThumbnailView thumbnail = (YouTubeThumbnailView) convertView.findViewById(R.id.thumbnail);

        VideoEntry entry = entries.get(position);
        YouTubeThumbnailLoader loader = thumbnailViewToLoaderMap.get(thumbnail);
        if (loader == null) {
            thumbnail.setTag(entry.getVideoId());
        } else {
            thumbnail.setImageResource(R.drawable.loading_thumbnail);
            loader.setVideo(entry.getVideoId());
        }
        return convertView;
    }

    private final class ThumbnailListener implements
            YouTubeThumbnailView.OnInitializedListener,
            YouTubeThumbnailLoader.OnThumbnailLoadedListener {

        @Override
        public void onInitializationSuccess(
                YouTubeThumbnailView view, YouTubeThumbnailLoader loader) {
            loader.setOnThumbnailLoadedListener(this);
            thumbnailViewToLoaderMap.put(view, loader);
            view.setImageResource(R.drawable.loading_thumbnail);
            String videoId = (String) view.getTag();
            loader.setVideo(videoId);
        }

        @Override
        public void onInitializationFailure(
                YouTubeThumbnailView view, YouTubeInitializationResult loader) {
            view.setImageResource(R.drawable.no_thumbnail);
        }

        @Override
        public void onThumbnailLoaded(YouTubeThumbnailView view, String videoId) {
        }

        @Override
        public void onThumbnailError(YouTubeThumbnailView view, YouTubeThumbnailLoader.ErrorReason errorReason) {
            view.setImageResource(R.drawable.no_thumbnail);
        }
    }

}
