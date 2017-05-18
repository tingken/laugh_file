package com.quark.skillopedia.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
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

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by Administrator on 2016/5/21 0021.
 * 用户订单
 */
public class OrdersAdapter extends BaseAdapter {

    private Context context;
    private List<ListOrders> datas;
    int choosePosition;
    Handler handler;

    public OrdersAdapter(Context context, List<ListOrders> datas, Handler handler) {
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
        convertView = LayoutInflater.from(context).inflate(R.layout.orders_item, null);

        SelectableRoundedImageView iamgeIv = (SelectableRoundedImageView) convertView.findViewById(R.id.iamge_iv);
        TextView coursetitle = (TextView) convertView.findViewById(R.id.coursetitle);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView number = (TextView) convertView.findViewById(R.id.number);
        RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.ratingBar);
        TextView gradeTv = (TextView) convertView.findViewById(R.id.grade_tv);
        TextView milesTv = (TextView) convertView.findViewById(R.id.miles_tv);
        TextView footballTv = (TextView) convertView.findViewById(R.id.football_tv);
        TextView moneyTv = (TextView) convertView.findViewById(R.id.money_tv);
        TextView option_one = (TextView) convertView.findViewById(R.id.option_one);
        TextView option_two = (TextView) convertView.findViewById(R.id.option_two);

        CourseOrder course = datas.get(position).getCourse();
        ApiHttpClient.loadImage(course.getCoach_image(), iamgeIv, R.drawable.example_7);
        coursetitle.setText(course.getTitle());
        name.setText("BY " + course.getCoach_name());
        number.setText("x" + datas.get(position).getBuy_amount());
        try {
            float rat = Float.valueOf(course.getTotal_score());
            ratingBar.setRating(rat);

            int gradeint = (int)rat;
            gradeTv.setText(datas.get(position).getCourse().getTotal_coment_num() + " review");
        }catch (Exception e){
            Log.e("error","orderadapter fload 转换异常");
        }

        milesTv.setText(course.getDistance());
        footballTv.setText(course.getCategory_02_name());
        moneyTv.setText("$"+datas.get(position).getTotal_session_rate());

// 用户订单列表--显示按钮状态
// 【status=10{delete订单失效}
// status=11{delete,pay now}
// status=21,22{可退款，不可退款}
// booking_status=1，2{可booking，不可booking}
// status=30,40{30-已完成，40-已评价}】
        if (datas.get(position).getStatus().equals("10")){  //删除
            option_one.setVisibility(View.VISIBLE);
            option_one.setText(context.getString(R.string.deleteStr));
            option_one.setTextColor(ContextCompat.getColor(context, R.color.white));
            option_one.setBackground(ContextCompat.getDrawable(context, R.drawable.bord_deletelightred));
            option_one.setOnClickListener(new View.OnClickListener() {  //delete
                @Override
                public void onClick(View v) {
                    Message msg = new Message();
                    msg.what = 401;
                    msg.arg1 = position;
                    handler.sendMessage(msg);
                }
            });
            option_two.setVisibility(View.GONE);
        }else if(datas.get(position).getStatus().equals("11")){ //删除 付款
            option_one.setVisibility(View.VISIBLE);
            option_one.setText(context.getString(R.string.deleteStr));
            option_one.setTextColor(ContextCompat.getColor(context, R.color.white));
            option_one.setBackground(ContextCompat.getDrawable(context, R.drawable.bord_red));
            option_one.setOnClickListener(new View.OnClickListener() {  //delete
                @Override
                public void onClick(View v) {
                    Message msg = new Message();
                    msg.what = 401;
                    msg.arg1 = position;
                    handler.sendMessage(msg);
                }
            });

            option_two.setVisibility(View.VISIBLE);
            option_two.setText(context.getString(R.string.paynow));
            option_two.setTextColor(ContextCompat.getColor(context, R.color.white));
            option_two.setBackground(ContextCompat.getDrawable(context, R.drawable.bord_paynow_15));
            option_two.setOnClickListener(new View.OnClickListener() {  //to pay
                @Override
                public void onClick(View v) {
                    Message msg = new Message();
                    msg.what = 402;
                    msg.arg1 = position;
                    handler.sendMessage(msg);
                }
            });
        }else if(datas.get(position).getStatus().equals("21")){ //可退款
            option_one.setVisibility(View.VISIBLE);
            option_one.setText(context.getString(R.string.refundStr));
            option_one.setTextColor(ContextCompat.getColor(context, R.color.white));
            option_one.setBackground(ContextCompat.getDrawable(context, R.drawable.bord_red));
            option_one.setOnClickListener(new View.OnClickListener() {  //退款
                @Override
                public void onClick(View v) {
                    Message msg = new Message();
                    msg.what = 403;
                    msg.arg1 = position;
                    handler.sendMessage(msg);
                }
            });

            if (datas.get(position).getBooking_status()==1){
                option_two.setVisibility(View.VISIBLE);
                option_two.setText(context.getString(R.string.bookingStr));
                option_two.setTextColor(ContextCompat.getColor(context, R.color.white));
                option_two.setBackground(ContextCompat.getDrawable(context, R.drawable.bord_paynow_15));
                option_two.setOnClickListener(new View.OnClickListener() {  //booking
                    @Override
                    public void onClick(View v) {
                        Message msg = new Message();
                        msg.what = 405;
                        msg.arg1 = position;
                        handler.sendMessage(msg);
                    }
                });
            }else{
                option_two.setVisibility(View.VISIBLE);
                option_two.setText(context.getString(R.string.bookingStr));
                option_two.setTextColor(ContextCompat.getColor(context, R.color.white));
                option_two.setBackground(ContextCompat.getDrawable(context, R.drawable.bord_paynow_15));
            }
        }else if(datas.get(position).getStatus().equals("22")){//不可退款
            option_one.setVisibility(View.GONE);
            option_one.setText(context.getString(R.string.refundStr));
            option_one.setTextColor(ContextCompat.getColor(context, R.color.white));
            option_one.setBackground(ContextCompat.getDrawable(context, R.drawable.bord_red));

            if (datas.get(position).getBooking_status()==1){  //可以预定
                option_two.setVisibility(View.VISIBLE);
                option_two.setText(context.getString(R.string.bookingStr));
                option_two.setTextColor(ContextCompat.getColor(context, R.color.white));
                option_two.setBackground(ContextCompat.getDrawable(context, R.drawable.bord_paynow_15));

                option_two.setOnClickListener(new View.OnClickListener() {  //booking
                    @Override
                    public void onClick(View v) {
                        Message msg = new Message();
                        msg.what = 405;
                        msg.arg1 = position;
                        handler.sendMessage(msg);
                    }
                });
            }else{                                  //不可以预定
                option_two.setVisibility(View.VISIBLE);
                option_two.setText(context.getString(R.string.bookingStr));
                option_two.setTextColor(ContextCompat.getColor(context, R.color.white));
                option_two.setBackground(ContextCompat.getDrawable(context, R.drawable.bord_paynow_15));
            }
        }else if(datas.get(position).getStatus().equals("30")){ //已完成
            option_one.setVisibility(View.VISIBLE);
            option_one.setText(context.getString(R.string.commentStr));
            option_one.setTextColor(ContextCompat.getColor(context, R.color.white));
            option_one.setBackground(ContextCompat.getDrawable(context, R.drawable.bord_common));
            option_one.setOnClickListener(new View.OnClickListener() {  //评论
                @Override
                public void onClick(View v) {
                    Message msg = new Message();
                    msg.what = 406;
                    msg.arg1 = position;
                    handler.sendMessage(msg);
                }
            });

            option_two.setVisibility(View.VISIBLE);
            option_two.setText(context.getString(R.string.buyagainStr));
            option_two.setTextColor(ContextCompat.getColor(context, R.color.white));
            option_two.setBackground(ContextCompat.getDrawable(context, R.drawable.bord_bugagin));
            option_two.setOnClickListener(new View.OnClickListener() {  //再次购买
                @Override
                public void onClick(View v) {
                    Message msg = new Message();
                    msg.what = 407;
                    msg.arg1 = position;
                    handler.sendMessage(msg);
                }
            });
        }else if(datas.get(position).getStatus().equals("40")){   //已评价
            option_one.setVisibility(View.GONE);
            option_one.setText(context.getString(R.string.commentStr));
            option_one.setTextColor(ContextCompat.getColor(context, R.color.white));
            option_one.setBackground(ContextCompat.getDrawable(context, R.drawable.bord_common));

            option_two.setVisibility(View.VISIBLE);
            option_two.setText(context.getString(R.string.buyagainStr));
            option_two.setTextColor(ContextCompat.getColor(context, R.color.white));
            option_two.setBackground(ContextCompat.getDrawable(context, R.drawable.bord_bugagin));
            option_two.setOnClickListener(new View.OnClickListener() {  //
                @Override
                public void onClick(View v) {
                    Message msg = new Message();
                    msg.what = 407;
                    msg.arg1 = position;
                    handler.sendMessage(msg);
                }
            });
        }

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iamge_iv)
        SelectableRoundedImageView iamgeIv;
        @InjectView(R.id.coursetitle)
        TextView coursetitle;
        @InjectView(R.id.name)
        TextView name;
        @InjectView(R.id.number)
        TextView number;
        @InjectView(R.id.ratingBar)
        RatingBar ratingBar;
        @InjectView(R.id.grade_tv)
        TextView gradeTv;
        @InjectView(R.id.miles_tv)
        TextView milesTv;
        @InjectView(R.id.football_tv)
        TextView footballTv;
        @InjectView(R.id.money_tv)
        TextView moneyTv;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
