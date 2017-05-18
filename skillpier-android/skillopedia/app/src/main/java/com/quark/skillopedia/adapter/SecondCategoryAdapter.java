package com.quark.skillopedia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.quark.api.auto.bean.ListCategory02;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiHttpClient;

import java.util.List;

/**
 * Created by Administrator on 2016/5/21 0021.
 */
public class SecondCategoryAdapter extends BaseAdapter {


    private Context context;
    private List<ListCategory02> datas;
    int pich;
    public SecondCategoryAdapter(Context context, List<ListCategory02> datas,int pich) {
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
        convertView = LayoutInflater.from(context).inflate(R.layout.second_category_item, null);
        ImageView ctgpic = (ImageView) convertView.findViewById(R.id.ctgpic);
//        TextView ctgtitle = (TextView) convertView.findViewById(R.id.ctgtitle);
//        TextView ctgdes = (TextView) convertView.findViewById(R.id.ctgdes);
//        ImageView  ctgpicTwo = (ImageView) convertView.findViewById(R.id.ctgpic_two);

        ViewGroup.LayoutParams params2 = ctgpic.getLayoutParams();
        params2.height = pich;
        ctgpic.setLayoutParams(params2);
//        ctgpicTwo.setLayoutParams(params2);

        ApiHttpClient.loadImage(datas.get(position).getImage_01(), ctgpic, R.drawable.example_6);
//        ctgtitle.setText(datas.get(position).getCategory_02_name());
//        ctgdes.setText(datas.get(position).getCategory_describe());

        return convertView;
    }

}
