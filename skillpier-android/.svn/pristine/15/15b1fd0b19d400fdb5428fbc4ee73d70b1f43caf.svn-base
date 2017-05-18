package com.quark.skillopedia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.quark.api.auto.bean.ListExperience;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiHttpClient;
import com.quark.skillopedia.util.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2016/5/23 0023.
 */
public class StepsAdapter extends BaseSwipeAdapter {
    private Context context;
    private List<ListExperience> stepsInfos;

    public StepsAdapter(Context context, List<ListExperience> stepsInfos) {
        this.context = context;
        this.stepsInfos = stepsInfos;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipeLayout;
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.stepsadapter_list, null);
        return v;
    }

    @Override
    public void fillValues(int position, View convertView) {

        ImageView imageIv = (ImageView) convertView.findViewById(R.id.iamge_iv);
        TextView titleTv = (TextView) convertView.findViewById(R.id.title_steps_tv);
        TextView contextTv = (TextView) convertView.findViewById(R.id.context_steps_tv);
        TextView dateTv = (TextView) convertView.findViewById(R.id.date_steps_tv);

        if(Utils.isEmpty(stepsInfos.get(position).getImage_01())){
            imageIv.setVisibility(View.GONE);
        }else{
            new ApiHttpClient().loadImage(stepsInfos.get(position).getImage_01(),imageIv);
        }
        titleTv.setText(stepsInfos.get(position).getTitle());
        contextTv.setText(stepsInfos.get(position).getContent());
//        dateTv.setText(stepsInfos.get(position).getPost_time());

        //指定时间格式转换
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(stepsInfos.get(position).getPost_time());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 将date转化为String
        String s = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH).format(date);
        dateTv.setText(s);

        final SwipeLayout swipeLayout = (SwipeLayout) convertView.findViewById(getSwipeLayoutResourceId(position));
        convertView.findViewById(R.id.edit).setVisibility(View.GONE);
        convertView.findViewById(R.id.remove).setVisibility(View.GONE);

    }

    @Override
    public int getCount() {
        return stepsInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

//    private Context context;
//
//    private List<ListExperience> stepsInfos;
//
//    public StepsAdapter(Context context, List<ListExperience> stepsInfos) {
//        this.context = context;
//        this.stepsInfos = stepsInfos;
//    }
//
//    @Override
//    public int getCount() {
//        return stepsInfos.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return position;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        ViewHolder holder;
//        if (convertView == null) {
//            holder = new ViewHolder();
//            convertView = LayoutInflater.from(context).inflate(R.layout.stepsadapter_list, null);
//            holder.imageIv = (ImageView) convertView.findViewById(R.id.iamge_iv);
//            holder.titleTv = (TextView) convertView.findViewById(R.id.title_steps_tv);
//            holder.contextTv = (TextView) convertView.findViewById(R.id.context_steps_tv);
//            holder.dateTv = (TextView) convertView.findViewById(R.id.date_steps_tv);
//            convertView.setTag(holder);
//        }else {
//           holder = (ViewHolder) convertView.getTag();
//
//        }
//        new ApiHttpClient().loadImage(stepsInfos.get(position).getImage_01(),holder.imageIv);
//        holder.titleTv.setText(stepsInfos.get(position).getTitle());
//        holder.contextTv.setText(stepsInfos.get(position).getContent());
//        holder.dateTv.setText(stepsInfos.get(position).getPost_time());
//
//        convertView.findViewById(R.id.edit_ly).setVisibility(View.GONE);
//        convertView.findViewById(R.id.remove_ly).setVisibility(View.GONE);
//
//        return convertView;
//    }
//
//    static class ViewHolder {
//        ImageView imageIv;
//        public TextView titleTv;
//        public TextView contextTv;
//        public TextView dateTv;
//    }
}
