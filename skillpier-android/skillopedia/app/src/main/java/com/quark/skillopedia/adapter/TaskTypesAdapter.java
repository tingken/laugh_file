package com.quark.skillopedia.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.quark.api.auto.bean.FilterBean;
import com.quark.skillopedia.R;
import com.quark.skillopedia.util.Utils;

import java.util.List;


/**
 * @author Administrator
 * 
 */
public class TaskTypesAdapter extends BaseAdapter {
	int choosep;
	private List<FilterBean> list;
	private Context context;

	public TaskTypesAdapter(Context context, List<FilterBean> list) {
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public void setChoose(int position){
		choosep = position;
	}
	
	@Override
	public View getView(int i, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(context).inflate(R.layout.language_item, null);
		TextView name = (TextView) convertView.findViewById(R.id.name);
		ImageView upordown = (ImageView) convertView.findViewById(R.id.upordown);

		name.setText(list.get(i).getName());
		if (choosep==i) {
			name.setTextColor(ContextCompat.getColor(context, R.color.chengse));
		}else{
			name.setTextColor(ContextCompat.getColor(context, R.color.zitihuise));
		}

		if ((!Utils.isEmpty(list.get(i).getType()))&&list.get(i).getType().equals("1")){
			upordown.setVisibility(View.VISIBLE);
			upordown.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.carset_yellow_down));
		}else if((!Utils.isEmpty(list.get(i).getType()))&&list.get(i).getType().equals("2")){
			upordown.setVisibility(View.VISIBLE);
			upordown.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.carset_yellow_up));
		}else {
			upordown.setVisibility(View.GONE);
		}

		return convertView;
	}

}
