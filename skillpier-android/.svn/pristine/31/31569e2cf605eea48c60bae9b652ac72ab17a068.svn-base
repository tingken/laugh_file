package com.quark.skillopedia.uiview.orders;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.CoachOrdersListRequest;
import com.quark.api.auto.bean.CoachOrdersListResponse;
import com.quark.api.auto.bean.DeletePayOrderRequest;
import com.quark.api.auto.bean.DeletePayOrderResponse;
import com.quark.api.auto.bean.ListOrders;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.AppParam;
import com.quark.skillopedia.R;
import com.quark.skillopedia.adapter.MyOrdersAdapter;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseFragment;
import com.quark.skillopedia.uiview.zhanghu.jiaolian.OrderMgDetailsActivity;
import com.quark.skillopedia.uiview.zhanghu.jiaolian.ScheduleConfirmTimeActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cz.msebera.android.httpclient.Header;
import me.maxwin.view.XListView;

/**
 * Created by Administrator on 2016/5/23 0023.
 *  订单管理 已支付 已完成 退款
 */
public class MyOrdersFragment extends BaseFragment implements XListView.IXListViewListener {
    @InjectView(R.id.xlstv)
    XListView list;
    @InjectView(R.id.nodata)
    TextView nodata;

    private ArrayList<ListOrders> datas;
    String order_type;
    MyOrdersAdapter adapter;
    int current;
    int type = 1;
    int pn = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View pager = inflater.inflate(R.layout.pager_fragment, container, false);
        ButterKnife.inject(this, pager);
        order_type = getArguments().getString("myorder_type");
        myinitlist();
        getData();
        registerBoradcastReceiverJP();

        return pager;
    }

    public void myinitlist() {
        datas = new ArrayList<>();
        list.setFooterDividersEnabled(false);
        // 设置xlistview可以加载、刷新
        list.setPullLoadEnable(true);//购物车一次加载完 没有加载更多
        list.setPullRefreshEnable(true);
        list.setXListViewListener(this);
        list.setOnItemClickListener(listListener);
        adapter = new MyOrdersAdapter(getActivity(), datas,handler);
        list.setAdapter(adapter);
        Message msg = handler.obtainMessage();
        msg.what = 1;
        msg.arg1 = datas.size();
        handler.sendMessage(msg);
    }

    AdapterView.OnItemClickListener listListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            position = position - 1;
            if (position < datas.size()) {
                Intent intent = new Intent().setClass(getActivity(), OrderMgDetailsActivity.class);
                intent.putExtra("orders_id", datas.get(position).getOrders_id() + "");
                startActivity(intent);
            }
        }
    };

    public void getData() {
        showWait(true);
        CoachOrdersListRequest rq = new CoachOrdersListRequest();
        rq.setOrder_type(order_type+"");
        rq.setLatitude(new AppParam().getLat(getActivity()));
        rq.setLongitude(new AppParam().getLng(getActivity()));
        rq.setPage_size(10);
        rq.setPn(pn);
        QuarkApi.HttpRequest(rq, mHandler);
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, getActivity(), CoachOrdersListResponse.class);
            if (kd != null) {
                CoachOrdersListResponse info = (CoachOrdersListResponse) kd;
                dealData(info);
            }
            showWait(false);
        }

        @Override
        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
            AppContext.showToast("Network error" + arg0);
            showWait(false);
        }
    };

    public void dealData(CoachOrdersListResponse info) {
        if (type == 1) {
            datas.clear();
        }
        List<ListOrders> tin = info.getCoachOrdersListResult().getCoachOrderList().getList();
        Message msg = handler.obtainMessage();
        msg.what = type;
        if (tin != null && tin.size() > 0) {
            datas.addAll(tin);
            msg.arg1 = tin.size();
            switch (order_type){
                case "1":
                    TextView oneTv = (TextView) getActivity().findViewById(R.id.one_text);
                    oneTv.setText("Paid(" + info.getCoachOrdersListResult().getCoachOrderList().getTotalRow() + ")");
                    break;
                case "2":
                    TextView twoTv = (TextView) getActivity().findViewById(R.id.two_text);
                    twoTv.setText("Finished(" + info.getCoachOrdersListResult().getCoachOrderList().getTotalRow() + ")");
                    break;
                case "3":
                    TextView thteeTv = (TextView) getActivity().findViewById(R.id.three_text);
                    thteeTv.setText("Refunded(" + info.getCoachOrdersListResult().getCoachOrderList().getTotalRow() + ")");
                    break;
            }
        } else {
            msg.arg1 = 0;
        }

        handler.sendMessage(msg);
        if (type == 1 && (tin == null || tin.size() < 1)) {
            nodata.setVisibility(View.VISIBLE);
            list.setVisibility(View.GONE);
        } else {
            nodata.setVisibility(View.GONE);
            list.setVisibility(View.VISIBLE);
        }
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int pdatasize = msg.arg1;
            list.isnomore(pdatasize < 10);
            switch (msg.what) {
                case 1:
                    list.stopRefresh();
                    adapter.notifyDataSetChanged();
                    break;
                case 2:
                    list.stopLoadMore();
                    adapter.notifyDataSetChanged();
                    break;
                case 401://取消
                    current = msg.arg1;
                    Bundle bundle4 = new Bundle();
                    bundle4.putString("schedule_type", "1");
                    bundle4.putString("orders_id", datas.get(current).getOrders_id() + "");

                    Intent intent4 = new Intent();
                    intent4.putExtras(bundle4);
                    intent4.setClass(getActivity(), ScheduleConfirmTimeActivity.class);
                    startActivity(intent4);
                    break;
                case 402:  //确认时间
                    current = msg.arg1;
                    Bundle bundle3 = new Bundle();
                    bundle3.putString("schedule_type", "2");
                    bundle3.putString("orders_id", datas.get(current).getOrders_id() + "");
                    Intent intent3 = new Intent();
                    intent3.putExtras(bundle3);
                    intent3.setClass(getActivity(), ScheduleConfirmTimeActivity.class);
                    startActivity(intent3);

                    break;
                case 403:  //完成
                    current = msg.arg1;
                    current = msg.arg1;
                    Bundle bundle5 = new Bundle();
                    bundle5.putString("schedule_type", "3");
                    bundle5.putString("orders_id", datas.get(current).getOrders_id() + "");
                    Intent intent5 = new Intent();
                    intent5.putExtras(bundle5);
                    intent5.setClass(getActivity(), ScheduleConfirmTimeActivity.class);
                    startActivity(intent5);
                    break;
                case 405: //删除
                    current = msg.arg1;
                    deletePayOrderRequest();
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onRefresh() {
        type = 1;
        pn = 1;
        getData();
    }

    @Override
    public void onLoadMore() {
        type = 2;
        pn++;
        getData();
    }

    public void deletePayOrderRequest() {
        DeletePayOrderRequest rq = new DeletePayOrderRequest();
        rq.setOrders_ids(datas.get(current).getOrders_id() + "");
        rq.setDelete_type("2"); //1-用户操作，2-教练操作
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlerdelete);
    }

    private final AsyncHttpResponseHandler mHandlerdelete = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, getActivity(), DeletePayOrderResponse.class);
            if (kd != null) {
                DeletePayOrderResponse info = (DeletePayOrderResponse) kd;
                showToast(info.getMessage());
                if (info.getStatus() == 1) {
                    datas.remove(current);
                    adapter.notifyDataSetChanged();
                    if (datas==null||datas.size()==0){
                        nodata.setVisibility(View.VISIBLE);
                        list.setVisibility(View.GONE);
                    } else {
                        nodata.setVisibility(View.GONE);
                        list.setVisibility(View.VISIBLE);
                    }
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


    /*====广播 其他地方有操作的时候刷新========*/
    private ReceiveBroadCast broadCastOrders_all;
    public void registerBoradcastReceiverJP() {
        broadCastOrders_all = new ReceiveBroadCast();
        IntentFilter filter = new IntentFilter();
        filter.addAction("my_orders_paid"); // 只有持有相同的action的接受者才能接收此广播
        getActivity().registerReceiver(broadCastOrders_all, filter);
    }

    public class ReceiveBroadCast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent data) {
            String opertion = data.getStringExtra("option");
            if (opertion.equals("refresh")){
                onRefresh();
            }
        }
    }

    @Override
    public void onDestroy() {
        try {
            if (broadCastOrders_all!=null) {
                getActivity().unregisterReceiver(broadCastOrders_all); // 注销广播接收器
            }
        }catch (Exception e){
            Log.e("error", "broadCastOrders_all 销毁出错");
        }

        super.onDestroy();
    }
/*====广播 其他地方有操作的时候刷新========*/
}
