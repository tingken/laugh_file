package com.quark.skillopedia.uiview.zhanghu.jiaolian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.CancelOrdersRequest;
import com.quark.api.auto.bean.CancelOrdersResponse;
import com.quark.api.auto.bean.CoachOrdersScheduleListBRequest;
import com.quark.api.auto.bean.CoachOrdersScheduleListBResponse;
import com.quark.api.auto.bean.FinishOrdersRequest;
import com.quark.api.auto.bean.FinishOrdersResponse;
import com.quark.api.auto.bean.ListScheduleBean;
import com.quark.api.auto.bean.RejectConfirmOrdersRequest;
import com.quark.api.auto.bean.RejectConfirmOrdersResponse;
import com.quark.api.auto.bean.ScheduleDatas;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.R;
import com.quark.skillopedia.adapter.ScheduleAdapter;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.ui.widget.MyGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * 教练 确认时间
 *
 * @author leon
 * @time 2016/7/7 0007 下午 3:54
 */
public class ScheduleOrderActivity extends BaseActivity {

    ScheduleAdapter adapter;
    String timestr;
    List<ListScheduleBean> datas;

    String orders_id;
    String schedule_type;   //1-cancel，2-confirm，3-finish
    List<ScheduleDatas> scheduleDatas;
    @InjectView(R.id.gridview)
    MyGridView gridview;
    @InjectView(R.id.reject)
    TextView reject;
    @InjectView(R.id.confirm)
    TextView confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmtime_schedule_item);
        ButterKnife.inject(this);

        orders_id = (String) getValue4Intent("orders_id");
        schedule_type = (String) getValue4Intent("schedule_type");
        timestr = (String) getValue4Intent("schedule_data");

        if (schedule_type.equals("1")) {  //取消
            reject.setText("CANCEL");
            confirm.setVisibility(View.GONE);
        } else if(schedule_type.equals("2")){
            reject.setText("REJECT");
            confirm.setText("CONFIRM");
        }else if (schedule_type.equals("3")) {//完成
            reject.setVisibility(View.GONE);
            confirm.setText("FINISH");
        }

        datas = new ArrayList<>();
        scheduleDatas = new ArrayList<>();
        choosetime = new HashMap<>();
        datacash = new HashMap<>();

        coachOrdersScheduleListBRequest(timestr);
        gridview.setOnItemClickListener(gradonclick);
    }

    HashMap<Integer, String> choosetime;
    HashMap<String, List<ListScheduleBean>> datacash;
    AdapterView.OnItemClickListener gradonclick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //必须连续选择两个 每节课固定时间为60min 只往后取时间 如果后一个时间不空闲则不能选择 提示“不能选择该时间段”
            //取消时 如果取消 则取消 上次选择的两个时间
            //时间段个数不能大于课程数
            // 取消 HashMap<Integer,String>记录if点击的是已选择的 判断是否包含key 如果包含设置key key+1为未选 remove key；如果不在判断key-1是否在map中在 key-1 key为空 remove key-1
            if (datas.get(position).getSchedule_state().equals("3")) {//1-unavaliable，2-busy,3-有空
                if (datas.get(position).isCheck()) {  //取消
                    if (choosetime.containsKey(position)) {
                        datas.get(position).setCheck(false);
                        datas.get(position + 1).setCheck(false);
                        choosetime.remove(position);
                    } else {
                        if (choosetime.containsKey(position - 1)) {
                            datas.get(position).setCheck(false);
                            datas.get(position - 1).setCheck(false);
                            choosetime.remove(position - 1);
                        }
                    }
                } else { //选择
                    if (((position + 1) < 32) && (!datas.get(position + 1).isCheck()) && (datas.get(position + 1).getSchedule_state().equals("3"))) {
                        datas.get(position).setCheck(true);
                        datas.get(position + 1).setCheck(true);
                        choosetime.put(position, datas.get(position).getOrders_schedule_id());
                    } else {
                        showToast("Can not choose this time period");
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }

    };

    public void coachOrdersScheduleListBRequest(String schedule_data) {
        CoachOrdersScheduleListBRequest rq = new CoachOrdersScheduleListBRequest();
        rq.setSchedule_type(schedule_type);
        rq.setOrders_id(orders_id);
        rq.setSchedule_data(schedule_data);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlerorderschedule);
    }

    private final AsyncHttpResponseHandler mHandlerorderschedule = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, ScheduleOrderActivity.this, CoachOrdersScheduleListBResponse.class);
            if (kd != null) {
                CoachOrdersScheduleListBResponse info = (CoachOrdersScheduleListBResponse) kd;
                if (info.getStatus() == 1) {
                    datas.clear();
                    datas.addAll(info.getScheduleBeans());

                    adapter = new ScheduleAdapter(ScheduleOrderActivity.this, datas);
                    gridview.setAdapter(adapter);
                }
            }
            showWait(false);
        }

        @Override
        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
            AppContext.showToast("Network error" + arg0);
            showWait(false);
        }
    };

    @OnClick({R.id.reject, R.id.confirm})
    public void rc(View v) {
        if (choosetime.size() == 0) {
            showToast("Please select the time");
        } else {
            orders_schedule_ids = "";
            Iterator iter = choosetime.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                Object val = entry.getValue();
                orders_schedule_ids += val + "#";
            }
            if (orders_schedule_ids.endsWith("#")) {
                orders_schedule_ids = orders_schedule_ids.substring(0, orders_schedule_ids.length() - 1);
            }

            switch (v.getId()) {
                case R.id.reject:
                    if (schedule_type.equals("1")) {
                        cancelOrdersRequest();   //取消课程
                    } else if (schedule_type.equals("2")) {    //确定时间 --》拒绝
                        rejectConfirmOrdersRequest("1", "");
                    }
                    break;
                case R.id.confirm:
                    if (schedule_type.equals("2")) { //确定时间 --》同意
                        rejectConfirmOrdersRequest("2", "");//确定时间
                    } else if (schedule_type.equals("3")) {  //完成课程
                        finishOrdersRequest();
                    }
                    break;
            }
        }
    }

    String orders_schedule_ids = "";

    /*确认  拒绝时间*/
    public void rejectConfirmOrdersRequest(String schedule_type, String refund_reason) {
        RejectConfirmOrdersRequest rq = new RejectConfirmOrdersRequest();
        rq.setSchedule_type(schedule_type);
        rq.setOrders_schedule_ids(orders_schedule_ids);
        rq.setRefund_reason(refund_reason);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlerconfirm);
    }

    private final AsyncHttpResponseHandler mHandlerconfirm = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, ScheduleOrderActivity.this, RejectConfirmOrdersResponse.class);
            if (kd != null) {
                RejectConfirmOrdersResponse info = (RejectConfirmOrdersResponse) kd;
                showToast(info.getMessage());
                if (info.getStatus()==1){
                    Intent intent = new Intent("my_orders_paid");
                    intent.putExtra("option", "refresh");
                    sendBroadcast(intent);

                    coachOrdersScheduleListBRequest(timestr);  //刷新界面
                }
            }
            showWait(false);
        }

        @Override
        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
            AppContext.showToast("Network error" + arg0);
            showWait(false);
        }
    };

    /*结束课程*/
    public void finishOrdersRequest() {
        FinishOrdersRequest rq = new FinishOrdersRequest();
        rq.setOrders_schedule_ids(orders_schedule_ids);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlerfinish);
    }

    private final AsyncHttpResponseHandler mHandlerfinish = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, ScheduleOrderActivity.this, FinishOrdersResponse.class);
            if (kd != null) {
                FinishOrdersResponse info = (FinishOrdersResponse) kd;
                showToast(info.getMessage());
                if (info.getStatus()==1){
                    Intent intent = new Intent("my_orders_paid");
                    intent.putExtra("option", "refresh");
                    sendBroadcast(intent);
                    finish();
                }
            }
            showWait(false);
        }

        @Override
        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
            AppContext.showToast("Network error" + arg0);
            showWait(false);
        }
    };

    /*取消课程*/
    public void cancelOrdersRequest() {
        CancelOrdersRequest rq = new CancelOrdersRequest();
        rq.setOrders_schedule_ids(orders_schedule_ids);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlercancel);
    }

    private final AsyncHttpResponseHandler mHandlercancel = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, ScheduleOrderActivity.this, CancelOrdersResponse.class);
            if (kd != null) {
                CancelOrdersResponse info = (CancelOrdersResponse) kd;
                showToast(info.getMessage());
                if (info.getStatus()==1){
                    Intent intent = new Intent("my_orders_paid");
                    intent.putExtra("option", "refresh");
                    sendBroadcast(intent);
                    finish();
                }
            }
            showWait(false);
        }

        @Override
        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
            AppContext.showToast("Network error" + arg0);
            showWait(false);
        }
    };


    @Override
    public void initView() {
    }

    @Override
    public void initData() {
    }


}
