package com.quark.skillopedia.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quark.api.auto.bean.ListCourseCertification;
import com.quark.skillopedia.R;
import com.quark.skillopedia.util.Utils;

import java.util.List;

public class CourseCertificationAdapter extends RecyclerView.Adapter<CourseCertificationAdapter.ViewHolder> {
    private Context context;

    int status;
    List<ListCourseCertification> datas;
    int psw;
    Handler handler;

    public CourseCertificationAdapter(Context context, List<ListCourseCertification> histories) {
        this.context = context;
        this.datas = histories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.course_certification_item,null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (!Utils.isEmpty(datas.get(position).getName())){
            holder.name.setText(datas.get(position).getName());
            holder.content.setText(datas.get(position).getInstitue() + " " + datas.get(position).getPost_time());
        }else{
            holder.nodata.setVisibility(View.VISIBLE);
            holder.line.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView content;
        TextView nodata;
        View line;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            content = (TextView) itemView.findViewById(R.id.content);
            nodata = (TextView) itemView.findViewById(R.id.nodata);
            line = (View) itemView.findViewById(R.id.line);

            content.setOnClickListener(this);
            name.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onClick(itemView, getAdapterPosition());
            }
        }
    }

    private OnItemClickListener clickListener;

    public void setItemClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public static interface OnItemClickListener {
        void onClick(View view, int position);
    }

}