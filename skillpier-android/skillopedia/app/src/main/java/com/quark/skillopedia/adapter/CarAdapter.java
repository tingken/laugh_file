package com.quark.skillopedia.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.quark.api.auto.bean.CarLists;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiHttpClient;

import java.util.List;

/**
 * @author Administrator
 */
public class CarAdapter extends BaseSwipeAdapter {

    private Context context;
    List<CarLists> list;
    Handler handler;
    public CarAdapter(Context context, List<CarLists> list, Handler handler) {
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
        View v = LayoutInflater.from(context).inflate(R.layout.car_item, null);

        return v;
    }

    @Override
    public void fillValues(final int i, View convertView) {
        LinearLayout view = (LinearLayout) convertView.findViewById(R.id.view);
        ImageView check = (ImageView)convertView.findViewById(R.id.choose);
        ImageView iamge_iv = (ImageView)convertView.findViewById(R.id.iamge_iv);
        TextView coursetitle = (TextView)convertView.findViewById(R.id.coursetitle);
        TextView name = (TextView)convertView.findViewById(R.id.name);

        TextView number = (TextView)convertView.findViewById(R.id.number);
//        TextView price = (TextView)convertView.findViewById(R.id.price);
//        TextView totalmoney = (TextView)convertView.findViewById(R.id.totalmoney);
        TextView gradeTv = (TextView)convertView.findViewById(R.id.grade_tv);

        TextView aftermoney = (TextView)convertView.findViewById(R.id.aftermoney);
        RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.ratingBar);
        TextView milesTv = (TextView) convertView.findViewById(R.id.miles_tv);
        TextView footballTv = (TextView) convertView.findViewById(R.id.football_tv);

        ApiHttpClient.loadImage(list.get(i).getCoach_image(), iamge_iv, R.drawable.example_7);

        coursetitle.setText(list.get(i).getCourse_title());
        name.setText("BY " + list.get(i).getCoach_name());
        number.setText("x"+list.get(i).getBuy_amount());
//        price.setText("$ "+list.get(i).getSession_rate());
        milesTv.setText(list.get(i).getCourse().getDistance());
        footballTv.setText(list.get(i).getCourse().getCategory_02_name());
        try {
            float rat = Float.valueOf(list.get(i).getCourse().getTotal_score());
            ratingBar.setRating(rat);

            int gradeint = (int)rat;
//            gradeTv.setText(gradeint + " review");
            gradeTv.setText(list.get(i).getCourse().getTotal_coment_num() + " review");
        }catch (Exception e){
            Log.e("error", "orderadapter fload 转换异常");
        }

        String aftertotalmoenystr = list.get(i).getTotal_session_rate();//打折后实际支付
        String totalmoenystr = list.get(i).getOriginal_total_session_rate();//原价支付
        aftermoney.setText("$" + aftertotalmoenystr);
//        if (totalmoenystr!=null&&(!totalmoenystr.equals(aftertotalmoenystr))){
//            totalmoney.setText("$"+totalmoenystr);
//            totalmoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
//        }

        if (list.get(i).getStatus().equals("1")){//有效
            if (list.get(i).isCheck()){
                check.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.cart_2));
            }else{
                check.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.cart_1));
            }
            check.setEnabled(true);
        }else{//失效
            check.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.invalidcar));
            check.setEnabled(false);
            Message msg = new Message();
            msg.what = 200;
            msg.arg1 = i;
            handler.sendMessage(msg);
        }

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

//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                Message msg = new Message();
//                msg.what = 202;
//                msg.arg1 = i;
//                handler.sendMessage(msg);
//                swipeLayout.close();
//            }
//        });
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