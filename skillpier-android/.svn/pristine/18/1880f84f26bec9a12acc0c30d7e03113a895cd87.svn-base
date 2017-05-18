package com.quark.skillopedia.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
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
 * @author Administrator
 */
public class ExperienceEditAdapter extends BaseSwipeAdapter {

    private Context context;
    List<ListExperience> list;
    Handler handler;

    public ExperienceEditAdapter(Context context, List<ListExperience> list, Handler handler) {
        this.list = list;
        this.context = context;
        this.handler = handler;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    @Override
    public View generateView(final int position, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.experience_edit_item, null);

        return v;
    }

    @Override
    public void fillValues(final int i, View convertView) {
        TextView title_steps_tv = (TextView) convertView.findViewById(R.id.title_steps_tv);
        TextView context_steps_tv = (TextView) convertView.findViewById(R.id.context_steps_tv);
        TextView date_steps_tv = (TextView) convertView.findViewById(R.id.date_steps_tv);
        TextView titleStatus = (TextView) convertView.findViewById(R.id.title_status);
        ImageView imageIv = (ImageView) convertView.findViewById(R.id.image_iv);

        if (Utils.isEmpty(list.get(i).getImages())) {
            imageIv.setVisibility(View.GONE);
        } else {
            ApiHttpClient.loadImage(list.get(i).getImages(), imageIv);
//            String[] imageStr = list.get(i).getImages().split("#");
//
//            if (imageStr.length == 1) {
//
//            }else {
//                ApiHttpClient.loadImage(imageStr[0], imageIv);
//            }
        }

        if (list.get(i).getStatus() == 2) {
            titleStatus.setVisibility(View.VISIBLE);
        } else if (list.get(i).getStatus() == 1) {
            titleStatus.setVisibility(View.GONE);
        }
        title_steps_tv.setText(list.get(i).getTitle());
        context_steps_tv.setText(list.get(i).getContent());

        //指定时间格式转换
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(list.get(i).getPost_time());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 将date转化为String
        String s = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH).format(date);
        date_steps_tv.setText(s);


        final SwipeLayout swipeLayout = (SwipeLayout) convertView.findViewById(getSwipeLayoutResourceId(i));
        convertView.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Message msg = new Message();
                msg.what = 201;
                msg.arg1 = i;
                handler.sendMessage(msg);
                swipeLayout.close();
            }
        });

        convertView.findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Message msg = new Message();
                msg.what = 202;
                msg.arg1 = i;
                handler.sendMessage(msg);
                swipeLayout.close();
            }
        });
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}