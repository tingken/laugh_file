package com.quark.skillopedia.uiview.dingdan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.RefundListRequest;
import com.quark.api.auto.bean.RefundListResponse;
import com.quark.api.auto.bean.RefundOrdersRequest;
import com.quark.api.auto.bean.RefundOrdersResponse;
import com.quark.api.auto.bean.Refunds;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.R;
import com.quark.skillopedia.adapter.RefundAdapter;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.ui.widget.ListViewForScrollView;
import com.quark.skillopedia.util.Utils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * @author pan
 *         created at 2016/5/30 0030
 * @退款申请
 */
public class RefundApplyActivity extends BaseActivity {

    @InjectView(R.id.canrefundnumber)
    TextView canrefundnumber;
    @InjectView(R.id.list)
    ListViewForScrollView list;
    @InjectView(R.id.submit)
    Button submit;

    String orders_ids;
    List<Refunds> refunds;
    RefundAdapter adapter;
    RefundListResponse info;
    private double refund_success_money = 0;//成功退款金额
    private String orders_schedule_ids = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refundapply);
        ButterKnife.inject(this);
        setTopTitle("Refund apply");
        setBackButton();
        orders_ids = (String) getValue4Intent("orders_ids");
        refundListRequest();
        list.setOnItemClickListener(ic);
    }

    AdapterView.OnItemClickListener ic = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (refunds.get(position).getRefund_status().equals("10")) {
                if (refunds.get(position).isCheck()) {
                    refunds.get(position).setCheck(false);
                } else {
                    refunds.get(position).setCheck(true);
                }
                calculatedMoney();
                adapter.notifyDataSetChanged();
            }
        }
    };

    /**
     * 计算退款：
     * -----------用户退款
     * 总退款金额 = 实付金额-服务费-（未退款课程数*原单价）
     * 未退款课程 = 总课程 - 已经选择要退款的课程
     * 原单价 = 课程单价+外出+additional partner
     * ----------教练退款
     * 支付总额-（支付总额/总课程数 *退款课程数）
     *
     * @author leon
     * @time 2016/7/7 0007 上午 11:16
     */
    public void calculatedMoney() {
        refund_success_money = 0;
        orders_schedule_ids = "";

        String total_session_rate = info.getRefundListResult().getOrders().getTotal_session_rate();
        double totalMoney = Double.valueOf(total_session_rate);
        String first_joint_fee = info.getRefundListResult().getOrders().getFirst_joint_fee();
        double serviceMoney = Double.valueOf(first_joint_fee);

        //交通费
        String traffic_costStr = info.getRefundListResult().getOrders().getGo_door_traffic_cost();
        double traffic_cost = Double.valueOf(traffic_costStr);
        //带人数
        String take_partner_numStr = info.getRefundListResult().getOrders().getTake_partner_num();
        double take_partner_num = Double.valueOf(take_partner_numStr);
        //带的每个人的费用
        String surcharge_for_each_cashStr = info.getRefundListResult().getOrders().getSurcharge_for_each_cash();
        double surcharge_for_each_cash = Double.valueOf(surcharge_for_each_cashStr);
        //课程价格
        String session_rateStr = info.getRefundListResult().getOrders().getSession_rate();
        double session_rate = Double.valueOf(session_rateStr);
        //原单价 = 课程原单价+外出+additional partner
        double sessionPrice = session_rate +  traffic_cost + take_partner_num * surcharge_for_each_cash;

        //选了的课程数
        int numberNoChoosetemp = 0;
        for (int i = 0; i < refunds.size(); i++) {
//            可退款 未退款课程数
//            if ((!refunds.get(i).isCheck())&&refunds.get(i).getRefund_status().equals("10")){
//                numberNoChoose++;
//            }
            //选中的退款课程
            if ((refunds.get(i).isCheck()) && refunds.get(i).getRefund_status().equals("10")) {
                orders_schedule_ids += refunds.get(i).getOrders_schedule_id() + "#";
                numberNoChoosetemp++;
            }
        }

        //总课程数
        String courseNumberStr = info.getRefundListResult().getOrders().getBuy_amount();
        int courseNumber = Integer.valueOf(courseNumberStr);
        //未退款课程数
        int numberNoChoose = courseNumber - numberNoChoosetemp;

        //（未退款课程数*原单价）
        double norefundMoney = numberNoChoose * sessionPrice;

        if (orders_schedule_ids.endsWith("#")) {
            orders_schedule_ids = orders_schedule_ids.substring(0, orders_schedule_ids.length() - 1);
        }

        refund_success_money = totalMoney - serviceMoney - norefundMoney;
        if (refund_success_money < 0) {
            refund_success_money = 0;
        }
        submit.setText("$" + Utils.DoubleFormat(refund_success_money) + ",REFUND");
    }

    public void refundListRequest() {
        RefundListRequest rq = new RefundListRequest();
        rq.setOrders_id(orders_ids);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandler);
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, RefundApplyActivity.this, RefundListResponse.class);
            if (kd != null) {
                info = (RefundListResponse) kd;
                if (info.getStatus() == 1) {
                    canrefundnumber.setText(info.getCan_refund_num() + " Sessons could be refund");
                    refunds = info.getRefundListResult().getRefunds();
                    adapter = new RefundAdapter(RefundApplyActivity.this, refunds);
                    list.setAdapter(adapter);
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

    @OnClick(R.id.submit)
    public void refundOrdersRequest(View b) {
        if (Utils.isEmpty(orders_schedule_ids)) {
            showToast("Please select course");
        } else {
            RefundOrdersRequest rq = new RefundOrdersRequest();
            rq.setOrders_id(orders_ids);
            rq.setOrders_schedule_ids(orders_schedule_ids);
            rq.setRefund_success_money(refund_success_money + "");
            showWait(true);
            QuarkApi.HttpRequest(rq, mHandlerrefun);
        }
    }

    private final AsyncHttpResponseHandler mHandlerrefun = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, RefundApplyActivity.this, RefundOrdersResponse.class);
            if (kd != null) {
                RefundOrdersResponse info = (RefundOrdersResponse) kd;
                showToast(info.getMessage());
                if (info.getStatus() == 1) {
                    //需要刷新的地方：全部订单 已付款
                    Intent allIntent = new Intent("orders_all");
                    allIntent.putExtra("option", "refresh");
                    sendBroadcast(allIntent);
                    //
                    Intent comIntent = new Intent("orders_apaid");
                    comIntent.putExtra("option", "refresh");
                    sendBroadcast(comIntent);
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
