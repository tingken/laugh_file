package com.quark.skillopedia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joooonho.SelectableRoundedImageView;
import com.quark.api.auto.bean.ListComment;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiHttpClient;
import com.quark.skillopedia.util.Utils;

import java.util.List;

/**
 * Created by pan on 2016/7/6 0006.
 * >#
 * >#
 */
public class CourseDetailCommentsEXBaseAdapter extends BaseAdapter {

    private Context context;
    private List<ListComment> list;

    public CourseDetailCommentsEXBaseAdapter(Context context, List<ListComment> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
        convertView = LayoutInflater.from(context).inflate(R.layout.course_detail_commonts_item, null);
        SelectableRoundedImageView picIv = (SelectableRoundedImageView) convertView.findViewById(R.id.pic_iv);
        TextView userTv = (TextView) convertView.findViewById(R.id.user);
        TextView dateTv = (TextView) convertView.findViewById(R.id.date_tv);
        TextView commentTv = (TextView) convertView.findViewById(R.id.comment_tv);
        LinearLayout replayview = (LinearLayout) convertView.findViewById(R.id.replayview);
        TextView replay = (TextView) convertView.findViewById(R.id.replay);
        new ApiHttpClient().loadImage(list.get(position).getComment_image(), picIv, R.drawable.tab_8);
        userTv.setText(list.get(position).getComment_name());
        commentTv.setText(list.get(position).getNote());
        dateTv.setText(list.get(position).getFormat_post_time());
        if (Utils.isEmpty(list.get(position).getCommentReply_content())) {
            replayview.setVisibility(View.GONE);
        } else {
            replayview.setVisibility(View.VISIBLE);
            replay.setText(list.get(position).getCommentReply_content());
        }

        return convertView;
    }

}
