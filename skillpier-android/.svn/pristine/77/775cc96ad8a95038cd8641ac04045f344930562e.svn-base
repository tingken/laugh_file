package com.quark.skillopedia.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quark.api.auto.bean.ListScheduleBean;
import com.quark.skillopedia.R;
import com.quark.skillopedia.util.TLog;

import java.util.List;

/**
 * Created by Administrator on 2016/5/21 0021.
 */
public class ScheduleAdapter extends BaseAdapter {
    private Context context;
    private List<ListScheduleBean> datas;
    int choosePosition;

    public ScheduleAdapter(Context context, List<ListScheduleBean> datas) {
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
        convertView = LayoutInflater.from(context).inflate(R.layout.schedule_item, null);
        LinearLayout schedule_rl = (LinearLayout) convertView.findViewById(R.id.schedule_rl);
        TextView category = (TextView) convertView.findViewById(R.id.time);
        TextView status = (TextView) convertView.findViewById(R.id.status);
        ImageView check = (ImageView) convertView.findViewById(R.id.check);

        category.setText(datas.get(position).getHour());
        status.setText(datas.get(position).getSchedule_state_message());

        TLog.error(datas.get(position).getSchedule_state() + "");

        if (datas.get(position).getSchedule_state().equals("1")) {   //1-unavaliable，2-busy,3-有空
            category.setTextColor(ContextCompat.getColor(context, R.color.unavailable));
            status.setTextColor(ContextCompat.getColor(context, R.color.unavailable));
            schedule_rl.setBackground(ContextCompat.getDrawable(context, R.color.white));
            check.setVisibility(View.GONE);
        } else if (datas.get(position).getSchedule_state().equals("2")) {
            category.setTextColor(ContextCompat.getColor(context, R.color.chengse));
            status.setTextColor(ContextCompat.getColor(context, R.color.chengse));
            schedule_rl.setBackground(ContextCompat.getDrawable(context, R.color.schedule_busy));
            if (datas.get(position).isCheck()) {
                schedule_rl.setBackground(ContextCompat.getDrawable(context, R.color.schedule_check));
            } else {
                schedule_rl.setBackground(ContextCompat.getDrawable(context, R.color.schedule_busy));
            }
            if (datas.get(position).getSchedule_state().equals("1")) {   //1-unavaliable，2-busy,3-有空
                category.setTextColor(ContextCompat.getColor(context, R.color.huise));
                status.setTextColor(ContextCompat.getColor(context, R.color.huise));
                schedule_rl.setBackground(ContextCompat.getDrawable(context, R.color.white));
                check.setVisibility(View.GONE);
//                schedule_rl.setBackground(ContextCompat.getDrawable(context, R.color.schedule_busy));

                if (datas.get(position).isCheck()) {
                    check.setVisibility(View.VISIBLE);
                    schedule_rl.setBackground(ContextCompat.getDrawable(context, R.color.schedule_check));
                } else {
                    check.setVisibility(View.GONE);
                    schedule_rl.setBackground(ContextCompat.getDrawable(context, R.color.unfocused));
                }
            } else if (datas.get(position).getSchedule_state().equals("3")) {
                category.setTextColor(ContextCompat.getColor(context, R.color.chengse));
                status.setTextColor(ContextCompat.getColor(context, R.color.chengse));


                if (datas.get(position).isCheck()) {
                    check.setVisibility(View.VISIBLE);
                    schedule_rl.setBackground(ContextCompat.getDrawable(context, R.color.picborder));
                } else {
                    check.setVisibility(View.GONE);
                    schedule_rl.setBackground(ContextCompat.getDrawable(context, R.color.white));
                }
                TLog.error(datas.get(position).isCheck() + "adapter 3");
            }
        } else if (datas.get(position).getSchedule_state().equals("3")) {
            category.setTextColor(ContextCompat.getColor(context, R.color.chengse));
            status.setTextColor(ContextCompat.getColor(context, R.color.chengse));
            if (datas.get(position).isCheck()) {
                check.setVisibility(View.VISIBLE);
                schedule_rl.setBackground(ContextCompat.getDrawable(context, R.color.schedule_check));
            } else {
                check.setVisibility(View.GONE);
                schedule_rl.setBackground(ContextCompat.getDrawable(context, R.color.white));
            }
        }

        return convertView;
    }


}
