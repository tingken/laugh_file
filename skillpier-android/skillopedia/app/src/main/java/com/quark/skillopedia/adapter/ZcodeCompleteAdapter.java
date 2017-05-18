package com.quark.skillopedia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.quark.skillopedia.R;

import java.util.List;

/**
 * Created by Administrator on 2016/5/21 0021.
 */
public class ZcodeCompleteAdapter extends BaseAdapter implements Filterable {

    private Context context;
    private List<String> datas;
    int choosePosition;
    public ZcodeCompleteAdapter(Context context, List<String> datas) {
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


        category.setText(datas.get(position));

        return convertView;
    }


    @Override
    public Filter getFilter() {
        return null;
    }
}
