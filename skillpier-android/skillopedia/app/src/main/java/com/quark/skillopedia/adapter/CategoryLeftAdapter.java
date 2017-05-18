package com.quark.skillopedia.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.quark.api.auto.bean.ListCatetory;
import com.quark.skillopedia.R;

import java.util.List;

/**
 * Created by Administrator on 2016/5/21 0021.
 */
public class CategoryLeftAdapter extends BaseAdapter {

    private Context context;
    private List<ListCatetory> datas;
    int choosePosition;
    public CategoryLeftAdapter(Context context, List<ListCatetory> datas) {
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
        convertView = LayoutInflater.from(context).inflate(R.layout.category_left_item, null);
        TextView category = (TextView) convertView.findViewById(R.id.category);
        ImageView jiao = (ImageView) convertView.findViewById(R.id.jiao);
        if (position==choosePosition){
            jiao.setVisibility(View.VISIBLE);
            category.setTextColor(ContextCompat.getColor(context,R.color.listtext));
        }else{
            jiao.setVisibility(View.GONE);
            category.setTextColor(ContextCompat.getColor(context, R.color.huise));
        }

        category.setText(datas.get(position).getName());

        return convertView;
    }


}
