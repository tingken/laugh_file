package com.quark.skillopedia.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.joooonho.SelectableRoundedImageView;
import com.quark.api.auto.bean.ListCourse;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiHttpClient;
import com.quark.skillopedia.util.TLog;

import java.util.List;

/**
 * @author Administrator
 */
public class MycourseAdapter extends BaseSwipeAdapter {

    private Context context;
    List<ListCourse> list;
    Handler handler;
    public MycourseAdapter(Context context, List<ListCourse> list, Handler handler) {
        this.list = list;
        this.context = context;
        this.handler = handler;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    @Override
    public View generateView(final int position, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.mycourse_item, null);

        return v;
    }

    @Override
    public void fillValues(final int i, View convertView) {
        SelectableRoundedImageView iamge_iv = (SelectableRoundedImageView) convertView.findViewById(R.id.iamge_iv);
        TextView text1 = (TextView)convertView.findViewById(R.id.text1);
        TextView text3 = (TextView)convertView.findViewById(R.id.text3);
        RatingBar ratingBar = (RatingBar)convertView.findViewById(R.id.ratingBar);
        TextView miles_tv = (TextView)convertView.findViewById(R.id.miles_tv);
        TextView grade_tv = (TextView)convertView.findViewById(R.id.grade_tv);
        TextView football_tv = (TextView)convertView.findViewById(R.id.football_tv);
        TextView money_tv = (TextView)convertView.findViewById(R.id.money_tv);

        ApiHttpClient.loadImage(list.get(i).getCoach_image(), iamge_iv, R.drawable.example_7);
        text1.setText(list.get(i).getTitle());
        text3.setText("BY " + list.get(i).getCoach_name());

        try {
            String ts = list.get(i).getTotal_score();
            double st = Double.valueOf(ts);
            ratingBar.setNumStars((int)st);
//            grade_tv.setText(st + "");

            int gradeint = (int)st;
//            grade_tv.setText(gradeint + " review");
            grade_tv.setText(list.get(i).getTotal_coment_num()  + " review");
        } catch (Exception e){
            TLog.error("转换int error");
        }
        miles_tv.setText(list.get(i).getDistance());
        football_tv.setText(list.get(i).getCategory_02_name());
        money_tv.setText(list.get(i).getSession_rate());

        final SwipeLayout swipeLayout = (SwipeLayout) convertView.findViewById(getSwipeLayoutResourceId(i));
        convertView.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Message msg = new Message();
                msg.what = 201;
                msg.arg1 = i;
                handler.sendMessage(msg);
                swipeLayout.close();
            }
        });

        convertView.findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Message msg = new Message();
                msg.what = 202;
                msg.arg1 = i;
                handler.sendMessage(msg);
                swipeLayout.close();
            }
        });
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

}