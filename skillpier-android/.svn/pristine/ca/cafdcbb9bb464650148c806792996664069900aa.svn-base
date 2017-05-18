package com.quark.skillopedia.uiview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.AllDeleteOrderRequest;
import com.quark.api.auto.bean.AllDeleteOrderResponse;
import com.quark.api.auto.bean.CarLists;
import com.quark.api.auto.bean.DeletePayOrderRequest;
import com.quark.api.auto.bean.DeletePayOrderResponse;
import com.quark.api.auto.bean.MyCartListRequest;
import com.quark.api.auto.bean.MyCartListResponse;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.R;
import com.quark.skillopedia.adapter.CarAdapter;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.uiview.dingdan.PaymentActivity;
import com.quark.skillopedia.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import me.maxwin.view.XListView;

/**
 * @author pan
 *         created at 2016/5/30 0030
 * @购物车
 */
public class ShoppingCartActivity extends BaseActivity implements XListView.IXListViewListener {
    public ArrayList<CarLists> datas;
    CarAdapter adapter;
    int pn = 1;
    int current;
    int type = 1;
    String ordersid = "";
    double totalMoney = 0;
    @InjectView(R.id.left_img)
    ImageView leftImg;
    @InjectView(R.id.left)
    LinearLayout left;
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.sign)
    TextView sign;
    @InjectView(R.id.rightrig)
    ImageView rightrig;
    @InjectView(R.id.right)
    LinearLayout right;
    @InjectView(R.id.head)
    RelativeLayout head;
    @InjectView(R.id.nodata)
    TextView nodata;
    @InjectView(R.id.course_list)
    XListView list;
    @InjectView(R.id.allchoose)
    LinearLayout allchoose;
    @InjectView(R.id.money)
    TextView money;
    @InjectView(R.id.topay)
    TextView topay;
    @InjectView(R.id.carview)
    LinearLayout carview;
    @InjectView(R.id.allpic)
    ImageView allpic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingcart);
        ButterKnife.inject(this);
        setTopTitle("Shopping cart");
        setBackButton();

        myinitlist();
        right.setVisibility(View.VISIBLE);
//        rightrig.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.clearcar));
        myCartListRequest();
    }

    public void myinitlist() {
        datas = new ArrayList<>();
        list.setFooterDividersEnabled(false);
        // 设置xlistview可以加载、刷新
        list.setPullLoadEnable(true);//购物车一次加载完 没有加载更多
        list.setPullRefreshEnable(true);
        list.setXListViewListener(this);
        adapter = new CarAdapter(this, datas, handler);
        list.setAdapter(adapter);

        Message msg = handler.obtainMessage();
        msg.what = 1;
        msg.arg1 = datas.size();
        handler.sendMessage(msg);
    }

    public void myCartListRequest() {
        MyCartListRequest rq = new MyCartListRequest();
        rq.setPage_size(10);
        rq.setPn(pn);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlerdata);
    }

    private final AsyncHttpResponseHandler mHandlerdata = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, ShoppingCartActivity.this, MyCartListResponse.class);
            if (kd != null) {
                MyCartListResponse info = (MyCartListResponse) kd;
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

    public void dealData(MyCartListResponse info) {
        if (info.getHas_invalid_car_num().equals("0")){
            right.setVisibility(View.GONE);
        }else{
            right.setVisibility(View.VISIBLE);
        }

        if (type == 1) {
            datas.clear();
        }
        List<CarLists> tin = info.getMyCartListResult().getCarts().getList();
        Message msg = new Message();
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
            carview.setVisibility(View.GONE);
        } else {
            nodata.setVisibility(View.GONE);
            carview.setVisibility(View.VISIBLE);
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
                case 201:
                    current = msg.arg1;
                    ordersid = datas.get(current).getOrders_id();
                    deletePayOrderRequest();
                    break;
                case 202:
                    current = msg.arg1;
                    if (datas.get(current).isCheck()) {
                        datas.get(current).setCheck(false);
                        adapter.notifyDataSetChanged();
                    } else {
                        datas.get(current).setCheck(true);
                        adapter.notifyDataSetChanged();
                    }
                    calculationMoney();
                    break;
                case 200:
                    rightrig.setImageDrawable(ContextCompat.getDrawable(ShoppingCartActivity.this, R.drawable.clearcar));
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 计算金额 在勾选 全选 删除的时候需要调用
     *
     * @author leon
     * @time 2016/7/22 0022 下午 3:06
     */
    public void calculationMoney() {
        ordersid = "";
        totalMoney = 0;
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).isCheck()) {
                ordersid += datas.get(i).getOrders_id() + "A";
                Double money = Double.valueOf(datas.get(i).getTotal_session_rate());
                totalMoney += money;
            }
        }
        money.setText("TOTAL:$"+Utils.DoubleFormat(totalMoney));
    }

    @Override
    public void onRefresh() {
        type = 1;
        pn = 1;
        myCartListRequest();
    }

    @Override
    public void onLoadMore() {
        type = 2;
        pn++;
        myCartListRequest();
    }

    public void deletePayOrderRequest() {
        DeletePayOrderRequest rq = new DeletePayOrderRequest();
        rq.setDelete_type("1");
        rq.setOrders_ids(ordersid);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandler);
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, ShoppingCartActivity.this, DeletePayOrderResponse.class);
            if (kd != null) {
                DeletePayOrderResponse info = (DeletePayOrderResponse) kd;
                if (info.getStatus() == 1) {
                    datas.remove(current);
                    adapter.notifyDataSetChanged();
                    if (datas == null || datas.size() == 0) {
                        nodata.setVisibility(View.VISIBLE);
                        carview.setVisibility(View.GONE);
                    }
                    //计算金额
                    calculationMoney();

                    Intent intent = new Intent("fragmentThree");
                    intent.putExtra("option","refreshCar");
                    sendBroadcast(intent);
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


    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    boolean chooseall = false;

    @OnClick(R.id.allchoose)
    public void chooseall(View v) {
        if (chooseall) {
            chooseall = false;
            for (int i = 0; i < datas.size(); i++) {
                datas.get(i).setCheck(false);
            }
            allpic.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.cart_1));
        } else {
            chooseall = true;
            for (int i = 0; i < datas.size(); i++) {
                datas.get(i).setCheck(true);
            }
            allpic.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.cart_2));
        }
        adapter.notifyDataSetChanged();
        //计算金额
        calculationMoney();
    }

    @OnClick(R.id.right)
    public void clear(View b) {
        AllDeleteOrderRequest rq = new AllDeleteOrderRequest();
        rq.setDelete_type("1");
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlerclear);
    }

    private final AsyncHttpResponseHandler mHandlerclear = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, ShoppingCartActivity.this, AllDeleteOrderResponse.class);
            if (kd != null) {
                AllDeleteOrderResponse info = (AllDeleteOrderResponse) kd;
                showToast(info.getMessage());
                if (info.getStatus() == 1) {
                    onRefresh();
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

    @OnClick(R.id.topay)
    public void topay(View v) {
        Bundle bundle = new Bundle();
        bundle.putString("orders_ids", ordersid);
        startActivityByClass(PaymentActivity.class, bundle);
    }
}
