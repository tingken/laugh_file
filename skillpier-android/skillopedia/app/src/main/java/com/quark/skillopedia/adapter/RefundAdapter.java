package com.quark.skillopedia.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.quark.api.auto.bean.Refunds;
import com.quark.skillopedia.R;
import com.quark.skillopedia.util.Utils;

import java.util.List;

/**
 * Created by Administrator on 2016/5/21 0021.
 */
public class RefundAdapter extends BaseAdapter {

    private Context context;
    private List<Refunds> datas;

    public RefundAdapter(Context context, List<Refunds> datas) {
        this.context = context;
        this.datas = datas;
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
        convertView = LayoutInflater.from(context).inflate(R.layout.refund_item, null);
        ImageView check = (ImageView) convertView.findViewById(R.id.check);
        TextView sessionnumber = (TextView) convertView.findViewById(R.id.sessionnumber);
        TextView sesiontime = (TextView) convertView.findViewById(R.id.sesiontime);

        sessionnumber.setText("Session" + (position + 1) + ":");
        if (Utils.isEmpty(datas.get(position).getSchedule_data())) {
            sesiontime.setText("Schedule unconfirmed");
        } else {
            sesiontime.setText(datas.get(position).getSchedule_data() + " " + datas.get(position).getSchedule_hours());
        }
        if (datas.get(position).getRefund_status().equals("10")) {
            check.setVisibility(View.VISIBLE);
            if (datas.get(position).isCheck()) {
                check.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.check_2));
            } else {
                check.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.check_1));
            }
            sessionnumber.setTextColor(ContextCompat.getColor(context, R.color.red));
            sesiontime.setTextColor(ContextCompat.getColor(context, R.color.red));
        } else {
            check.setVisibility(View.INVISIBLE);
        }
        if (datas.get(position).getRefund_status().equals("2") || datas.get(position).getRefund_status().equals("30")) {
            sessionnumber.setTextColor(ContextCompat.getColor(context, R.color.chengse));
            sesiontime.setTextColor(ContextCompat.getColor(context, R.color.chengse));
        } else if (datas.get(position).getRefund_status().equals("31")) {
            sessionnumber.setTextColor(ContextCompat.getColor(context, R.color.luse));
            sesiontime.setTextColor(ContextCompat.getColor(context, R.color.luse));
        }

        return convertView;
    }


}
