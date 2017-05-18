package com.quark.skillopedia.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.quark.api.auto.bean.OrdersSchedules;
import com.quark.skillopedia.R;
import com.quark.skillopedia.util.Utils;

import java.util.List;

/**
 * Created by pan on 2016/7/8 0008.
 * >#
 * >#
 */
public class ScheduleListAdapter extends BaseAdapter {

    private Context context;
    private List<OrdersSchedules> list;

    public ScheduleListAdapter(Context context, List<OrdersSchedules> list) {
        this.context = context;
        this.list = list;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.schedule_list, null);
            holder.sessionTv = (TextView) convertView.findViewById(R.id.session_tv);
            holder.monthTv = (TextView) convertView.findViewById(R.id.month_tv);
//            holder.dayTv = (TextView) convertView.findViewById(R.id.day_tv);
//            holder.yearTv = (TextView) convertView.findViewById(R.id.year_tv);
            holder.statuTv = (TextView) convertView.findViewById(R.id.time_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }



//        if (!Utils.isEmpty(list.get(position).getSchedule_data())) {
//            // 需要解析的日期字符串
//            String dateStr = list.get(position).getSchedule_data();
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            try {
//                Date date = format.parse(dateStr);
//                Calendar calendar = Calendar.getInstance();
//                calendar.setTime(date);
//                int year = calendar.get(Calendar.YEAR);
//                int month = calendar.get(Calendar.MONTH) + 1;
//                int day = calendar.get(Calendar.DAY_OF_MONTH);
//                holder.yearTv.setText(year + "");
//                if (day < 10) {
//                    String daystr = "0" + day;
//                    holder.dayTv.setText(daystr + ",");
//                } else {
//                    holder.dayTv.setText(day + ",");
//                }
//                holder.timeTv.setText(list.get(position).getSchedule_hours());
//                switch (month) {
//                    case 1:
//                        holder.monthTv.setText("January");
//                        break;
//                    case 2:
//                        holder.monthTv.setText("February");
//                        break;
//                    case 3:
//                        holder.monthTv.setText("March");
//                        break;
//                    case 4:
//                        holder.monthTv.setText("April");
//                        break;
//                    case 5:
//                        holder.monthTv.setText("May");
//                        break;
//                    case 6:
//                        holder.monthTv.setText("June");
//                        break;
//                    case 7:
//                        holder.monthTv.setText("July");
//                        break;
//                    case 8:
//                        holder.monthTv.setText("August");
//                        break;
//                    case 9:
//                        holder.monthTv.setText("September");
//                        break;
//                    case 10:
//                        holder.monthTv.setText("October");
//                        break;
//                    case 11:
//                        holder.monthTv.setText("November");
//                        break;
//                    case 12:
//                        holder.monthTv.setText("December");
//                        break;
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        holder.sessionTv.setText("Session" + (position + 1));
        if (Utils.isEmpty(list.get(position).getSchedule_data())) {
            holder.monthTv.setVisibility(View.GONE);
            holder.statuTv.setText("Unselected");
            holder.statuTv.setTextColor(context.getResources().getColor(R.color.qianhuise));
            holder.sessionTv.setTextColor(ContextCompat.getColor(context, R.color.qianhuise));
        } else {
            holder.monthTv.setVisibility(View.VISIBLE);
            holder.monthTv.setText(list.get(position).getSchedule_data() + " " + list.get(position).getSchedule_hours());
        }
// status:1-完成课程(finished)，20-教练确认时间(confirmed),21-教练拒绝用户时间(未选择时间),
// 22-教练取消订单(cancel),3-教练未确认时间(unconfirmed),4-用户未选择时间(unchoice)
        if (list.get(position).getStatus().equals("1")) {    //1-完成课程(finished) -20-教练确认时间(confirmed) 	21-教练拒绝用户时间(refusetime)
            holder.sessionTv.setTextColor(ContextCompat.getColor(context, R.color.luse));
            holder.monthTv.setTextColor(ContextCompat.getColor(context, R.color.luse));
            holder.statuTv.setTextColor(ContextCompat.getColor(context, R.color.luse));
            holder.statuTv.setText("finish");
        } else if (list.get(position).getStatus().equals("20")) {
            holder.sessionTv.setTextColor(ContextCompat.getColor(context, R.color.chengse));
            holder.monthTv.setTextColor(ContextCompat.getColor(context, R.color.chengse));
            holder.statuTv.setTextColor(ContextCompat.getColor(context, R.color.chengse));
            holder.statuTv.setText("Confirmed");
        } else if (list.get(position).getStatus().equals("3")) {
            holder.sessionTv.setTextColor(ContextCompat.getColor(context, R.color.red));
            holder.monthTv.setTextColor(ContextCompat.getColor(context, R.color.red));
            holder.statuTv.setTextColor(ContextCompat.getColor(context, R.color.red));
            holder.statuTv.setText("UnConfirmed");
        }

        return convertView;
    }

    static class ViewHolder {
        TextView sessionTv;
        TextView monthTv;
        //        TextView dayTv;
//        TextView yearTv;
        TextView statuTv;
    }

}
