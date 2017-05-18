package com.quark.skillopedia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.quark.api.auto.bean.ListComment;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiHttpClient;

import java.util.List;

/**
 * Created by pan on 2016/7/6 0006.
 * >#
 * >#
 */
public class CommentsAdapter extends BaseAdapter {

    private Context context;
    private List<ListComment> list;

    public CommentsAdapter(Context context, List<ListComment> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHoler holder;
        if (convertView == null){
            holder = new ViewHoler();
            convertView = LayoutInflater.from(context).inflate(R.layout.commonts_item,null);
            holder.picIv = (ImageView) convertView.findViewById(R.id.pic_iv);
            holder.userTv = (TextView) convertView.findViewById(R.id.user);
            holder.ratingBar = (RatingBar) convertView.findViewById(R.id.ratingBar);
            holder.dateTv = (TextView) convertView.findViewById(R.id.date_tv);
            holder.skillTv = (TextView) convertView.findViewById(R.id.skill_tv);
            holder.conditionTv = (TextView) convertView.findViewById(R.id.condition_tv);
            holder.attitudeTv = (TextView) convertView.findViewById(R.id.attitude_tv);
            holder.commentTv = (TextView) convertView.findViewById(R.id.comment_tv);
            convertView.setTag(holder);
        }else {
            holder = (ViewHoler) convertView.getTag();
        }
        new ApiHttpClient().loadImage(list.get(position).getUser_image(),holder.picIv);
        //评分 强转
        try {
            float grade = Float.parseFloat(list.get(position).getTotal_score());
            holder.ratingBar.setRating(grade);
        }catch (Exception e){
            e.printStackTrace();
        }
        holder.userTv.setText(list.get(position).getUser_name());
        holder.skillTv.setText(list.get(position).getPro_skill()+"");
        holder.conditionTv.setText(list.get(position).getTeaching_environment()+"");
        holder.attitudeTv.setText(list.get(position).getTeaching_attitude()+"");
        holder.commentTv.setText(list.get(position).getNote());
        return convertView;
    }
    static class ViewHoler{
        ImageView picIv;
        TextView userTv;
        RatingBar ratingBar;
        TextView dateTv;
        TextView skillTv;
        TextView conditionTv;
        TextView attitudeTv;
        TextView commentTv;
    }


}
