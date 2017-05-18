package com.quark.skillopedia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.quark.api.auto.bean.ListCourse;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiHttpClient;

import java.util.List;

/**
 * Created by Administrator on 2016/5/21 0021.
 */
public class HottypeNewAdapter extends BaseAdapter {

    private Context context;

    private List<ListCourse> datas;
    int typeminhight;
    int typebighight;

    public HottypeNewAdapter(Context context, List<ListCourse> datas, int typeminhight, int typebighight) {
        this.context = context;
        this.datas = datas;
        this.typeminhight = typeminhight;
        this.typebighight = typebighight;

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
        convertView = LayoutInflater.from(context).inflate(R.layout.hottype_item, null);
        ImageView type1 = (ImageView) convertView.findViewById(R.id.pic);
        TextView dis = (TextView) convertView.findViewById(R.id.dis);
        TextView price = (TextView) convertView.findViewById(R.id.price);

        ViewGroup.LayoutParams params = type1.getLayoutParams();
        params.height = typebighight;
        type1.setLayoutParams(params);

        ApiHttpClient.loadImage(datas.get(position).getUser_images_01(), type1, R.drawable.example_4);
        dis.setText(datas.get(position).getTitle());
        price.setText("$"+datas.get(position).getSession_rate());
        return convertView;
    }

}
