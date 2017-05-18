package com.quark.skillopedia.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.joooonho.SelectableRoundedImageView;
import com.quark.api.auto.bean.ListCourse;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiHttpClient;

import java.util.List;

/**
 * Created by pan on 2016/7/2 0002.
 * >#课程列表适配器
 * >#
 */
public class CourseListAdapter extends BaseAdapter {
    private Context context;
    private List<ListCourse> list;
    Handler handler;

    public CourseListAdapter(Context context, List<ListCourse> list, Handler handler) {
        this.context = context;
        this.list = list;
        this.handler = handler;
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
        convertView = LayoutInflater.from(context).inflate(R.layout.favorites_item, null);
        SelectableRoundedImageView imageIv = (SelectableRoundedImageView) convertView.findViewById(R.id.iamge_iv);
        TextView text1 = (TextView) convertView.findViewById(R.id.text1);
        // TextView text2 = (TextView) convertView.findViewById(R.id.text2);
        TextView text3 = (TextView) convertView.findViewById(R.id.text3);
        RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.ratingBar);
        TextView gradeTv = (TextView) convertView.findViewById(R.id.grade_tv);
        TextView miles = (TextView) convertView.findViewById(R.id.miles_tv);
        TextView football = (TextView) convertView.findViewById(R.id.football_tv);
        TextView moneyTv = (TextView) convertView.findViewById(R.id.money_tv);

        ApiHttpClient.loadImage(list.get(position).getCoach_image(), imageIv, R.drawable.example_7);
        text1.setText(list.get(position).getTitle());
        text3.setText(list.get(position).getCoach_name());

        //评分 强转
        float grade = Float.parseFloat(list.get(position).getTotal_score());
        ratingBar.setRating(grade);
        int gradeint = (int)grade;
        gradeTv.setText(list.get(position).getTotal_coment_num() + " review");

        miles.setText(list.get(position).getDistance());
        moneyTv.setText("¥" + list.get(position).getSession_rate());
        football.setText(list.get(position).getCategory_02_name());


        return convertView;
    }
}
