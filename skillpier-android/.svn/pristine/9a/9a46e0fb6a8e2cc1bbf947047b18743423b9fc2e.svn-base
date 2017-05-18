package com.quark.skillopedia.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.quark.api.auto.bean.ListCourseBanner;
import com.quark.skillopedia.AppParam;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiHttpClient;
import com.quark.skillopedia.util.Utils;

import java.util.List;

/**
 * Created by Administrator on 2016/5/21 0021.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private Context context;
    private List<ListCourseBanner> list;
    int choosePosition;

    public ImageAdapter(Context context, List<ListCourseBanner> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.img_item, null));
    }


    //点击接口
    public interface OnItemClickLitener {
        /**
         * 点击事件处理
         *
         * @author pan
         * @time 2016/10/31 0031 上午 11:18
         */
        void onItemClick(View view, int position);

        /**
         * 长按点击事件处理
         *
         * @author pan
         * @time 2016/10/31 0031 上午 11:18
         */
        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        ApiHttpClient.loadImage(list.get(position).getImage_01(), holder.picIv, R.drawable.example_2);

        int sw = new AppParam().getScreenWidth(context);
        int tabswtep = (sw - Utils.dip2px(context, 70)) / 3;
        int tabsw = (int) (160.0 / 140.0) * tabswtep;
        ViewGroup.LayoutParams paramsLy = holder.picIv.getLayoutParams();
        ViewGroup.LayoutParams params = holder.picIv.getLayoutParams();
        params.height = tabsw;
        params.width = tabsw;
        paramsLy.height = tabsw;
        holder.picLy.setLayoutParams(paramsLy);
        holder.picIv.setLayoutParams(params);

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView picIv;
        LinearLayout picLy;

        public ViewHolder(View itemView) {
            super(itemView);
            picIv = (ImageView) itemView.findViewById(R.id.pic);
            picLy = (LinearLayout) itemView.findViewById(R.id.pic_ly);
        }
    }
//
//    public void setChoosePosition(int choosePosition) {
//        this.choosePosition = choosePosition;
//    }
//
//    @Override
//    public int getCount() {
//        return datas.size();
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
//        int sw = new AppParam().getScreenWidth(context);
//        convertView = LayoutInflater.from(context).inflate(R.layout.img_item, null);
//        ImageView iv = (ImageView) convertView.findViewById(R.id.pic);
//        int tabswtep = (sw - Utils.dip2px(context, 70)) / 3;
//        int tabsw = (int) (160.0 / 140.0) * tabswtep;
//        ViewGroup.LayoutParams params = iv.getLayoutParams();
//        params.height = tabsw;
//        params.width = tabsw;
//        iv.setLayoutParams(params);
//        ApiHttpClient.loadImage(datas.get(position).getImage_01(), iv, R.drawable.example_2);
//        TLog.error(sw + "+++试试大小");
//        return convertView;
//    }


}
