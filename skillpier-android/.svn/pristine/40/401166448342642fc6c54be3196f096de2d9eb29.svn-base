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
import com.quark.api.auto.bean.DeletePayOrderRequest;
import com.quark.api.auto.bean.DeletePayOrderResponse;
import com.quark.api.auto.bean.ListOrders;
import com.quark.api.auto.bean.OrdersListRequest;
import com.quark.api.auto.bean.OrdersListResponse;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.AppParam;
import com.quark.skillopedia.R;
import com.quark.skillopedia.adapter.OrdersAdapter;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseFragment;
import com.quark.skillopedia.uiview.dingdan.OrderCommentActivity;
import com.quark.skillopedia.uiview.dingdan.PaymentActivity;
import com.quark.skillopedia.uiview.dingdan.RefundApplyActivity;
import com.quark.skillopedia.uiview.fenlei.CourseDetailActivity;
import com.quark.skillopedia.uiview.fenlei.ScheduleBookingActivity;
import com.quark.skillopedia.uiview.zhanghu.jiaolian.DetailsorderActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cz.msebera.android.httpclient.Header;
import me.maxwin.view.XListView;

/**
 * Created by Administrator on 2016/5/23 0023.
 * 订单 已支付
 */
public class OrdersPaidFragment extends BaseFragment implements XListView.IXListViewListener {
    @InjectView(R.id.xlstv)
    XListView list;
    @InjectView(R.id.nodata)
    TextView nodata;

    private ArrayList<ListOrders> datas;
    String order_type;
    OrdersAdapter adapter;
    int current;
    int type = 1;
    int pn = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View pager = inflater.inflate(R.layout.pager_fragment, container, false);
        ButterKnife.inject(this, pager);
        order_type = getArguments().getString("orders_type");
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
        adapter = new OrdersAdapter(getActivity(), datas, handler);
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
                Intent in = new Intent(getActivity(), DetailsorderActivity.class);
                in.putExtra("orders_id", datas.get(position).getOrders_id() + "");
                startActivity(in);
            }
        }
    };

    public void getData() {
        showWait(true);
        OrdersListRequest rq = new OrdersListRequest();
//        rq.setOrder_type(order_type + "");
        rq.setOrder_type("3");
        rq.setLatitude(new AppParam().getLat(getActivity()));
        rq.setLongitude(new AppParam().getLng(getActivity()));
        rq.setPage_size(10);
        rq.setPn(pn);
        QuarkApi.HttpRequest(rq, mHandler);
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, getActivity(), OrdersListResponse.class);
            if (kd != null) {
                OrdersListResponse info = (OrdersListResponse) kd;
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

    public void dealData(OrdersListResponse info) {
        if (type == 1) {
            datas.clear();
        }
        List<ListOrders> tin = info.getOrdersListResult().getOrderList().getList();
        Message msg = handler.obtainMessage();
        msg.what = type;
        if (tin != null && tin.size() > 0) {
            datas.addAll(tin);
            msg.arg1 = tin.size();
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
                case 401://删除
                    current = msg.arg1;
                    deletePayOrderRequest();
                    break;
                case 402:  //付款
                    current = msg.arg1;
                    Bundle bundle = new Bundle();
                    bundle.putString("orders_ids", datas.get(current).getOrders_id() + "");
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    intent.setClass(getActivity(), PaymentActivity.class);
                    startActivity(intent);
                    break;
                case 403:  //退款
                    current = msg.arg1;
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("orders_ids", datas.get(current).getOrders_id() + "");
                    Intent intent2 = new Intent();
                    intent2.putExtras(bundle2);
                    intent2.setClass(getActivity(), RefundApplyActivity.class);
                    startActivity(intent2);
                    break;
                case 405: //booking
                    current = msg.arg1;

                    Bundle bundle3 = new Bundle();
                    bundle3.putString("from", "order");
                    bundle3.putString("coure_id", datas.get(current).getCourse_id() + "");
                    bundle3.putString("coureTitle", datas.get(current).getCourse().getTitle());
                    bundle3.putString("countNumber", datas.get(current).getCourse().getHasnone_booking_course()+"");
                    bundle3.putString("orders_id", datas.get(current).getOrders_id() + "");
                    //课程时间表打开时应该显示第一个可以预定的日期
                    bundle3.putString("freeCourseDay", datas.get(current).getCourse().getFreeCourseDay());

                    Intent intent3 = new Intent();
                    intent3.putExtras(bundle3);
                    intent3.setClass(getActivity(), ScheduleBookingActivity.class);
                    startActivity(intent3);
                    break;
                case 406://评论
                    current = msg.arg1;
                    Bundle bundle4 = new Bundle();
                    bundle4.putString("coure_id", datas.get(current).getCourse_id() + "");
                    bundle4.putString("orders_id", datas.get(current).getOrders_id() + "");
                    bundle4.putString("user_id", datas.get(current).getCourse().getUser_id() + "");
                    Intent intent4 = new Intent();
                    intent4.putExtras(bundle4);
                    intent4.setClass(getActivity(), OrderCommentActivity.class);
                    startActivity(intent4);

                    break;
                case 407://再次购买
                    current = msg.arg1;
                    Bundle bundle5 = new Bundle();
                    bundle5.putString("course_id", datas.get(current).getCourse_id() + "");
                    Intent intent5 = new Intent();
                    intent5.putExtras(bundle5);
                    intent5.setClass(getActivity(), CourseDetailActivity.class);
                    startActivity(intent5);
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
        rq.setDelete_type("1");
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
                    if (datas == null || datas.size() == 0) {
                        nodata.setVisibility(View.VISIBLE);
                        list.setVisibility(View.GONE);
                    } else {
                        nodata.setVisibility(View.GONE);
                        list.setVisibility(View.VISIBLE);
                    }
                    Intent intent = new Intent("orders_waitpay");
                    intent.putExtra("option", "refresh");
                    getActivity().sendBroadcast(intent);

                }
            }
            showWait(false);
        }

        @Override
        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
            AppContext.showToast("Network error" + arg0);
            showWait(false);
        }

        @Override
        public void onFinish() {
            super.onFinish();
            showWait(false);
        }
    };

    /*====广播 其他地方有操作的时候刷新========*/
    private ReceiveBroadCast broadCastOrders_paid;
    public void registerBoradcastReceiverJP() {
        broadCastOrders_paid = new ReceiveBroadCast();
        IntentFilter filter = new IntentFilter();
        filter.addAction("orders_paid");   // 只有持有相同的action的接受者才能接收此广播
        getActivity().registerReceiver(broadCastOrders_paid, filter);
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
            if (broadCastOrders_paid!=null) {
                getActivity().unregisterReceiver(broadCastOrders_paid); // 注销广播接收器
            }
        }catch (Exception e){
            Log.e("error", "broadCastOrders_all 销毁出错");
        }

        super.onDestroy();
    }
/*====广播 其他地方有操作的时候刷新========*/

}
