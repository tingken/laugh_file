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
import com.quark.api.auto.bean.ListCollection;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiHttpClient;

import java.util.List;

/**
 * Created by Administrator on 2016/5/21 0021.
 */
public class FavoritesAdapter extends BaseSwipeAdapter {
    private Context context;
    private List<ListCollection> list;
    Handler handler;

    public FavoritesAdapter(Context context, List<ListCollection> list, Handler handler) {
        this.context = context;
        this.list = list;
        this.handler = handler;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipeLayout;
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.favorites_list, null);
        return v;
    }

    @Override
    public void fillValues(final int position, View convertView) {

        SelectableRoundedImageView imageIv = (SelectableRoundedImageView) convertView.findViewById(R.id.iamge_iv);
        TextView text1 = (TextView) convertView.findViewById(R.id.text1);
       // TextView text2 = (TextView) convertView.findViewById(R.id.text2);
        TextView text3 = (TextView) convertView.findViewById(R.id.text3);
        RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.ratingBar);
        TextView gradeTv = (TextView) convertView.findViewById(R.id.grade_tv);
        TextView miles = (TextView) convertView.findViewById(R.id.miles_tv);
        TextView football = (TextView) convertView.findViewById(R.id.football_tv);
        TextView moneyTv = (TextView) convertView.findViewById(R.id.money_tv);

        ApiHttpClient.loadImage(list.get(position).getCourse().getCoach_image(), imageIv, R.drawable.example_7);
        text1.setText(list.get(position).getCourse().getTitle());
        text3.setText("BY  "+list.get(position).getCourse().getCoach_name());

        //评分 强转
        try {
            float grade = Float.parseFloat(list.get(position).getCourse().getTotal_score());
            ratingBar.setRating(grade);

            int gradeint = (int)grade;
//            gradeTv.setText(gradeint + " review");
            gradeTv.setText(list.get(position).getCourse().getTotal_coment_num() + " review");
        }catch (Exception e){
            e.printStackTrace();
        }

        miles.setText(list.get(position).getCourse().getDistance());
        moneyTv.setText("¥"+list.get(position).getCourse().getSession_rate());
        football.setText(list.get(position).getCourse().getCategory_02_name());

        final SwipeLayout swipeLayout = (SwipeLayout) convertView.findViewById(getSwipeLayoutResourceId(position));
        convertView.findViewById(R.id.hengxian).setVisibility(View.INVISIBLE);
        convertView.findViewById(R.id.edit).setVisibility(View.GONE);
        convertView.findViewById(R.id.remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Message msg = new Message();
                msg.what = 205;
                msg.arg1 = position;
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
