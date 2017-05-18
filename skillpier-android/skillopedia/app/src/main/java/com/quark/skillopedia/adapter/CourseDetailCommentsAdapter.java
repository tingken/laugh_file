package com.quark.skillopedia.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.quark.api.auto.bean.ListComment;
import com.quark.skillopedia.AppParam;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiHttpClient;
import com.quark.skillopedia.util.Utils;

import java.util.List;

/**
 * Created by pan on 2016/7/6 0006.
 * >#
 * >#
 */
public class CourseDetailCommentsAdapter extends RecyclerView.Adapter<CourseDetailCommentsAdapter.ViewHolder> {

    private Context context;
    private List<ListComment> list;

    public CourseDetailCommentsAdapter(Context context, List<ListComment> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.course_detail_commonts_item, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int sw = new AppParam().getScreenWidth(context);
            ViewGroup.LayoutParams params2 = holder.layout.getLayoutParams();
            params2.width = sw;
            holder.layout.setLayoutParams(params2);

        if (!Utils.isEmpty(list.get(position).getComment_name())){
            holder.nodata.setVisibility(View.GONE);
            holder.layout.setVisibility(View.VISIBLE);

            new ApiHttpClient().loadImage(list.get(position).getComment_image(), holder.picIv, R.drawable.tab_8);

            try {
                float grade = Float.parseFloat(list.get(position).getTotal_score());
                holder.ratingBar.setRating(grade);
            } catch (Exception e) {
                e.printStackTrace();
            }
            holder.userTv.setText(list.get(position).getComment_name());
            holder.skillTv.setText(list.get(position).getPro_skill() + "");
            holder.conditionTv.setText(list.get(position).getTeaching_environment() + "");
            holder.attitudeTv.setText(list.get(position).getTeaching_attitude() + "");
            holder.commentTv.setText(list.get(position).getNote());
            holder.dateTv.setText(list.get(position).getFormat_post_time());
            if (Utils.isEmpty(list.get(position).getCommentReply_content())){
                holder.replayview.setVisibility(View.GONE);
            }else{
                holder.replayview.setVisibility(View.VISIBLE);
                holder.replay.setText(list.get(position).getCommentReply_content());
            }
        }else{
            holder.layout.setVisibility(View.GONE);
            holder.nodata.setVisibility(View.VISIBLE);

            ViewGroup.LayoutParams params3 = holder.nodata.getLayoutParams();
            params3.width = sw;
            holder.nodata.setLayoutParams(params3);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView picIv;
        TextView userTv;
        RatingBar ratingBar;
        TextView dateTv;
        TextView skillTv;
        TextView conditionTv;
        TextView attitudeTv;
        TextView commentTv;
        LinearLayout replayview;
        TextView replay;
        LinearLayout layout;

        TextView nodata;
        public ViewHolder(View itemView) {
            super(itemView);
            picIv = (ImageView) itemView.findViewById(R.id.pic_iv);
            userTv = (TextView) itemView.findViewById(R.id.user);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
            dateTv = (TextView) itemView.findViewById(R.id.date_tv);
            skillTv = (TextView) itemView.findViewById(R.id.skill_tv);
            conditionTv = (TextView) itemView.findViewById(R.id.condition_tv);
            attitudeTv = (TextView) itemView.findViewById(R.id.attitude_tv);
            commentTv = (TextView) itemView.findViewById(R.id.comment_tv);
            replayview = (LinearLayout) itemView.findViewById(R.id.replayview);
            replay = (TextView) itemView.findViewById(R.id.replay);
            layout = (LinearLayout) itemView.findViewById(R.id.layout);
            nodata = (TextView) itemView.findViewById(R.id.nodata);
        }
    }
}
