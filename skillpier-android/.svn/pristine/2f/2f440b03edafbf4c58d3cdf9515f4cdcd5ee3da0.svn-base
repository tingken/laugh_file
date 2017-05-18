package com.quark.skillopedia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quark.api.auto.bean.ListCourse;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiHttpClient;

import java.util.List;

/**
 * Created by Administrator on 2016/5/21 0021.
 *
 */
public class SecondCategoryTwoAdapter extends BaseAdapter {
    private Context context;
    private List<ListCourse> datas;
    int pich;
    public SecondCategoryTwoAdapter(Context context, List<ListCourse> datas, int pich) {
        this.context = context;
        this.datas = datas;
        this.pich = pich;
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
        convertView = LayoutInflater.from(context).inflate(R.layout.second_category_two_item, null);
        RelativeLayout ctgview = (RelativeLayout) convertView.findViewById(R.id.ctgview);
        ImageView ctgpic = (ImageView) convertView.findViewById(R.id.ctgpic);
        TextView des = (TextView) convertView.findViewById(R.id.des);
        TextView distance = (TextView) convertView.findViewById(R.id.distance);
        TextView category = (TextView) convertView.findViewById(R.id.category);
        TextView price = (TextView) convertView.findViewById(R.id.price);
        TextView teacher = (TextView) convertView.findViewById(R.id.teacher);
        ViewGroup.LayoutParams params2 = ctgview.getLayoutParams();
        params2.height = pich;
        ctgview.setLayoutParams(params2);
        ApiHttpClient.loadImage(datas.get(position).getCoach_image(), ctgpic, R.drawable.example_6);
        des.setText(datas.get(position).getTitle());
        price.setText("$" + datas.get(position).getSession_rate());
        teacher.setText("BY  " + datas.get(position).getCoach_name());
        distance.setText(datas.get(position).getDistance());
        category.setText(datas.get(position).getCatetory_name());
        return convertView;
    }

}
