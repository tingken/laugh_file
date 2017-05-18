package com.quark.skillopedia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quark.api.auto.bean.ListMyCoupon;
import com.quark.skillopedia.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2016/5/21 0021.
 */
public class ChooseCouponAdapter extends BaseAdapter {

    private Context context;
    private List<ListMyCoupon> datas;
    int choosePosition;

    public ChooseCouponAdapter(Context context, List<ListMyCoupon> datas) {
        this.context = context;
        this.datas = datas;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.conpon_item, null);
        RelativeLayout dataView = (RelativeLayout)convertView.findViewById(R.id.dataview);
        TextView coupon_name = (TextView) convertView.findViewById(R.id.coupon_name);
        TextView coupon_money = (TextView) convertView.findViewById(R.id.coupon_money);
        TextView coupon_number = (TextView) convertView.findViewById(R.id.coupon_number);
        TextView coupon_date = (TextView) convertView.findViewById(R.id.coupon_date);
        TextView without = (TextView) convertView.findViewById(R.id.without);

        if (datas.get(position).getCoupon_num()==0){
            dataView.setVisibility(View.GONE);
            without.setVisibility(View.VISIBLE);
        }else{
            dataView.setVisibility(View.VISIBLE);
            without.setVisibility(View.GONE);
            coupon_name.setText(datas.get(position).getCoupon_name());
            coupon_money.setText("$"+datas.get(position).getCoupon_money());
            coupon_number.setText(datas.get(position).getCoupon_num()+"");
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datas.get(position).getEnd_time());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // 将date转化为String
            String s = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH).format(date);
            String hh = new SimpleDateFormat("HH:mm:ss").format(date);
            coupon_date.setText(s+" "+hh);
        }

        return convertView;
    }


}
