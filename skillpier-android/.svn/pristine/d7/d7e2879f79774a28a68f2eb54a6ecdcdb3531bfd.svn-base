package com.quark.skillopedia.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.quark.api.auto.bean.ListComment;
import com.quark.skillopedia.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2016/5/21 0021.
 */
public class MessageAdapter extends BaseSwipeAdapter {
    private Context context;
    private List<ListComment> list;
    Handler handler;

    public MessageAdapter(Context context, List<ListComment> list, Handler handler) {
        this.context = context;
        this.list = list;
        this.handler = handler;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipeLayout;
    }

    @Override
    public View generateView(final int position, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_list, null);
        return v;
    }

    @Override
    public void fillValues(final int position, View convertView) {
        TextView conmentTv = (TextView) convertView.findViewById(R.id.conment_tv);
        TextView contentTv = (TextView) convertView.findViewById(R.id.content_tv);
        TextView userTv = (TextView) convertView.findViewById(R.id.user_tv);
        TextView dateTv = (TextView) convertView.findViewById(R.id.date_tv);

        if (list.get(position).getType() == 1) {
            conmentTv.setText("[comment]");
        } else if (list.get(position).getType() == 2) {
            conmentTv.setText("[System message]");
        }
        contentTv.setText(list.get(position).getNote());
        userTv.setText(list.get(position).getComment_name());
//        dateTv.setText(list.get(position).getPost_time());
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(list.get(position).getPost_time());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 将date转化为String
        String s = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH).format(date);
        dateTv.setText(s);
        final SwipeLayout swipeLayout = (SwipeLayout) convertView.findViewById(getSwipeLayoutResourceId(position));
        convertView.findViewById(R.id.edit).setVisibility(View.GONE);
        convertView.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = new Message();
                msg.what = 207;
                msg.arg1 = position;
                handler.sendMessage(msg);
                swipeLayout.close();
            }
        });
        //回复评论
        convertView.findViewById(R.id.replay_ly).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = new Message();
                msg.what = 208;
                msg.arg1 = position;
                handler.sendMessage(msg);
                swipeLayout.close();
            }
        });
        //如果有回复评论
        if (list.get(position).getContent().length() > 0) {
            TextView replyTv = (TextView) convertView.findViewById(R.id.reply_tv);
            //回复评论隐藏
            convertView.findViewById(R.id.replay_ly).setVisibility(View.INVISIBLE);
            replyTv.setVisibility(View.VISIBLE);
            replyTv.setText(list.get(position).getContent());
            contentTv.setBackgroundResource(R.drawable.button_huise_reply);
            contentTv.setPadding(20, 8, 20, 8);
            contentTv.setTextSize(12);
        }

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


//    private Context context;
//
//    private List<MessageInfo> messageInfos;
//
//    public MessageAdapter(){
//
//    }
//
//
//    public MessageAdapter(Context context, List<MessageInfo> messageInfos) {
//        this.context = context;
//        this.messageInfos = messageInfos;
//    }
//
//    @Override
//    public int getCount() {
//        return 20;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        ViewHolder holder;
//        if(convertView == null)
//        {
//            holder = new ViewHolder();
//            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_list, null);
//            holder.conmentTv = (TextView) convertView.findViewById(R.id.conment_tv);
//            holder.contentTv = (TextView) convertView.findViewById(R.id.content_tv);
//            holder.userTv = (TextView) convertView.findViewById(R.id.user_tv);
//            holder.dateTv = (TextView) convertView.findViewById(R.id.date_tv);
//
//            convertView.setTag(holder);
//        }else
//        {
//            holder = (ViewHolder)convertView.getTag();
//
//
//        }
//
//        /*holder.conmentTv.setText("[评论]");
//        holder.contentTv.setText("内容");
//        holder.userTv.setText("Dribehance");
//        holder.dateTv.setText("2015-06-05 15:03");*/
//
//        return convertView;
//
//
//
//    }
//
//
//    static class ViewHolder
//    {
//        public TextView conmentTv;
//        public TextView contentTv;
//        public TextView userTv;
//        public TextView dateTv;
//
//    }
}
