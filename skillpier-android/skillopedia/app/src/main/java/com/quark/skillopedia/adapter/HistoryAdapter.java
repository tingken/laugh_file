package com.quark.skillopedia.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.quark.api.auto.bean.HistoryResponse;
import com.quark.skillopedia.R;
import com.quark.skillopedia.util.Utils;

import java.util.List;


/**
 * @author Administrator
 */
public class HistoryAdapter extends BaseAdapter {


    private Context context;

    int status;
    List<HistoryResponse> histories;
    int psw;
    Handler handler;

    public HistoryAdapter(Context context, List<HistoryResponse> histories, Handler handler, int psw) {
        this.context = context;
        this.histories = histories;
        this.handler = handler;
    }

    @Override
    public int getCount() {
        return histories.size();
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
    public View getView(final int i, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.history_item, null);
        TextView keytext = (TextView) convertView.findViewById(R.id.key);
        String hst = histories.get(i).getSearchkey();
        if (!Utils.isEmpty(histories.get(i).getZipcodestr())) {
            hst += "  " + histories.get(i).getZipcodestr();
        }
        keytext.setText(hst);
        ImageView remove = (ImageView) convertView.findViewById(R.id.remove);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = new Message();
                msg.what = 1;
                msg.arg1 = i;
                handler.sendMessage(msg);
            }
        });
        return convertView;
    }
}