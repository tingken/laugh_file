package com.quark.skillopedia.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.joooonho.SelectableRoundedImageView;
import com.quark.api.auto.bean.CourseOrder;
import com.quark.api.auto.bean.ListOrders;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiHttpClient;

import java.util.List;

/**
 * Created by Administrator on 2016/5/21 0021.
 */
public class MyOrdersAdapter extends BaseAdapter {

    private Context context;
    private List<ListOrders> datas;
    int choosePosition;
    Handler handler;

    public MyOrdersAdapter(Context context, List<ListOrders> datas, Handler handler) {
        this.context = context;
        this.datas = datas;
        this.handler = handler;
    }

    public void setChoosePosition(int choosePosition) {
        this.choosePosition = choosePosition;
    }

    @Override
    public int getCount() {
        return datas.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.myorders_item, null);

        SelectableRoundedImageView iamgeIv = (SelectableRoundedImageView) convertView.findViewById(R.id.iamge_iv);
        TextView coursetitle = (TextView) convertView.findViewById(R.id.coursetitle);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView number = (TextView) convertView.findViewById(R.id.number);
        RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.ratingBar);
        TextView gradeTv = (TextView) convertView.findViewById(R.id.grade_tv);
        TextView milesTv = (TextView) convertView.findViewById(R.id.miles_tv);
        TextView footballTv = (TextView) convertView.findViewById(R.id.football_tv);
        TextView moneyTv = (TextView) convertView.findViewById(R.id.money_tv);

        TextView delete = (TextView) convertView.findViewById(R.id.delete);
        TextView confirm = (TextView) convertView.findViewById(R.id.confirm);
        TextView finish = (TextView) convertView.findViewById(R.id.finish);
        TextView cancel = (TextView) convertView.findViewById(R.id.cancel);

        CourseOrder course = datas.get(position).getCourse();
        ApiHttpClient.loadImage(course.getCoach_image(), iamgeIv, R.drawable.example_7);
        coursetitle.setText(course.getTitle());
        name.setText("BY " + course.getCoach_name());
        number.setText("x" + datas.get(position).getBuy_amount());
        try {
            float rat = Float.valueOf(course.getTotal_score());
            ratingBar.setRating(rat);

            int gradeint = (int)rat;
//            gradeTv.setText(gradeint + " review");
            gradeTv.setText(datas.get(position).getCourse().getTotal_coment_num() + " review");
        }catch (Exception e){
            Log.e("error","orderadapter fload 转换异常");
        }


        milesTv.setText(course.getDistance());
        footballTv.setText(course.getCategory_02_name());
        moneyTv.setText("$"+datas.get(position).getTotal_session_rate());

        if (datas.get(position).getButton_status1().equals("1")) {//取消
            cancel.setVisibility(View.VISIBLE);
            delete.setVisibility(View.GONE);
            finish.setVisibility(View.GONE);
            confirm.setVisibility(View.GONE);
            cancel.setOnClickListener(new View.OnClickListener() {  //delete
                @Override
                public void onClick(View v) {
                    Message msg = new Message();
                    msg.what = 401;
                    msg.arg1 = position;
                    handler.sendMessage(msg);
                }
            });
        }
        if(datas.get(position).getButton_status2().equals("1")){ //删除 付款
            cancel.setVisibility(View.GONE);
            delete.setVisibility(View.GONE);
            finish.setVisibility(View.GONE);
            confirm.setVisibility(View.VISIBLE);
            confirm.setOnClickListener(new View.OnClickListener() {  //delete
                @Override
                public void onClick(View v) {
                    Message msg = new Message();
                    msg.what = 402;
                    msg.arg1 = position;
                    handler.sendMessage(msg);
                }
            });
        }
        if(datas.get(position).getButton_status3().equals("1")){ //可退款
            cancel.setVisibility(View.GONE);
            delete.setVisibility(View.GONE);
            finish.setVisibility(View.VISIBLE);
            confirm.setVisibility(View.GONE);
            finish.setOnClickListener(new View.OnClickListener() {  //delete
                @Override
                public void onClick(View v) {
                    Message msg = new Message();
                    msg.what = 403;
                    msg.arg1 = position;
                    handler.sendMessage(msg);
                }
            });
        }
        if(datas.get(position).getButton_status4().equals("1")){
            cancel.setVisibility(View.GONE);
            delete.setVisibility(View.VISIBLE);
            finish.setVisibility(View.GONE);
            confirm.setVisibility(View.GONE);
            delete.setOnClickListener(new View.OnClickListener() {  //delete
                @Override
                public void onClick(View v) {
                    Message msg = new Message();
                    msg.what = 405;
                    msg.arg1 = position;
                    handler.sendMessage(msg);
                }
            });
        }

        return convertView;
    }

}
