package com.quark.skillopedia.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.quark.api.auto.bean.ListMyCoupon;
import com.quark.skillopedia.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by pan on 2016/7/4 0004.
 * >#
 * >#
 */
public class CouponAdapter extends BaseSwipeAdapter {

    private Context context;
    private List<ListMyCoupon> list;
    Handler handler;

    public CouponAdapter(Context context, List<ListMyCoupon> list, Handler handler) {
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
        View v = LayoutInflater.from(context).inflate(R.layout.conpon_list, null);
        return v;
    }

    @Override
    public void fillValues(final int position, View convertView) {

        TextView couponName = (TextView) convertView.findViewById(R.id.coupon_name);
        TextView couponMoney = (TextView) convertView.findViewById(R.id.coupon_money);
        TextView couponNum = (TextView) convertView.findViewById(R.id.coupon_number);
        TextView couponDate = (TextView) convertView.findViewById(R.id.coupon_date);

        couponName.setText(list.get(position).getCoupon_name());
//        couponDate.setText(list.get(position).getEnd_time());
        couponMoney.setText("$" + list.get(position).getCoupon_money() + "");
        couponNum.setText("x" + list.get(position).getCoupon_num() + "");

        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(list.get(position).getEnd_time());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 将date转化为String
        String s = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH).format(date);
        couponDate.setText(s);

        final SwipeLayout swipeLayout = (SwipeLayout) convertView.findViewById(getSwipeLayoutResourceId(position));
        convertView.findViewById(R.id.edit).setVisibility(View.GONE);
        convertView.findViewById(R.id.remove).setVisibility(View.GONE);


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
