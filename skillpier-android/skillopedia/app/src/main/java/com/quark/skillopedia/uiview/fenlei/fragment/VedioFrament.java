package com.quark.skillopedia.uiview.fenlei.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quark.api.auto.bean.CourseVedios;
import com.quark.skillopedia.R;
import com.quark.skillopedia.adapter.VedioAdapter;
import com.quark.skillopedia.uiview.youtubeVideos.FullscreenDemoActivity;
import com.quark.skillopedia.uiview.youtubeVideos.VideoEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
/**
 * 视频
 * 课程详情 对应的视频片段
 * @author pan
 * @time 2016/8/22 0022 下午 5:29
 */
public class VedioFrament extends Fragment {

    @InjectView(R.id.list)
    RecyclerView videoslist;
    VedioAdapter adapter;

    List<CourseVedios> courseVedios;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vedioView = inflater.inflate(R.layout.vedio_fragment_layout, container, false);
        ButterKnife.inject(this, vedioView);

        courseVedios = (List<CourseVedios>) getArguments().getSerializable("courseVedios");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        videoslist.setLayoutManager(layoutManager);

        if (courseVedios!=null&&courseVedios.size()>0){
            final List<VideoEntry> VIDEO_LIST;
            List<VideoEntry> list = new ArrayList<VideoEntry>();
            for (int i=0;i<courseVedios.size();i++){
                String url = courseVedios.get(i).getVedio_url();
                String[] videoidstr = url.split("watch\\?v=");  //youtube 固定格式
                if (videoidstr.length>1){
                    list.add(new VideoEntry("", videoidstr[videoidstr.length-1]));
                }
            }

            VIDEO_LIST = Collections.unmodifiableList(list);
            adapter = new VedioAdapter(getActivity(), VIDEO_LIST);
            videoslist.setAdapter(adapter);
            adapter.setItemClickListener(new VedioAdapter.OnItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                    startActivity(new Intent().setClass(getActivity(), FullscreenDemoActivity.class).putExtra("videoid", VIDEO_LIST.get(position).getVideoId()));
                }
            });
        }else{
            final List<VideoEntry> VIDEO_LIST = new ArrayList<>();
            VideoEntry empty = new VideoEntry("","");
            VIDEO_LIST.add(empty);
            adapter = new VedioAdapter(getActivity(), VIDEO_LIST);
            videoslist.setAdapter(adapter);
        }

        return vedioView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
