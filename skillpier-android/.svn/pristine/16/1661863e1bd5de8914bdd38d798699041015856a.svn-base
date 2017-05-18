package com.quark.skillopedia.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

import com.quark.api.auto.bean.ListScheduleBean;
import com.quark.skillopedia.R;

import java.util.List;

/**
 * Created by Administrator on 2016/5/21 0021.
 */
public class NewcourseThreeScheduleAdapter extends BaseAdapter {

    private Context context;
    private List<ListScheduleBean> datas;
    int choosePosition;

    public NewcourseThreeScheduleAdapter(Context context, List<ListScheduleBean> datas) {
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
        convertView = LayoutInflater.from(context).inflate(R.layout.new_course_schedule_item, null);
        RelativeLayout bgview = (RelativeLayout)convertView.findViewById(R.id.bgview);
        if (datas.get(position).isCheck()){
            bgview.setBackgroundColor(ContextCompat.getColor(context, R.color.chengse));
        }else{
            bgview.setBackgroundColor(ContextCompat.getColor(context,R.color.schedulehui));
        }

        return convertView;
    }


}
