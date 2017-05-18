package com.quark.skillopedia.uiview.zhanghu.youhui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.DeteleMyCouponRequest;
import com.quark.api.auto.bean.DeteleMyCouponResponse;
import com.quark.api.auto.bean.ListMyCoupon;
import com.quark.api.auto.bean.MyCouponListRequest;
import com.quark.api.auto.bean.MyCouponListResponse;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.R;
import com.quark.skillopedia.adapter.CouponExpAdapter;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseFragment;
import java.util.ArrayList;
import java.util.List;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cz.msebera.android.httpclient.Header;
import me.maxwin.view.XListView;
/**
 * Created by pan on 2016/7/5 0005.
 * >#显示过期的优惠券
 * >#
 */
public class TwoFragment extends BaseFragment implements XListView.IXListViewListener {

    @InjectView(R.id.xlstv_exp)
    XListView xlstvExp;
    ArrayList<ListMyCoupon> coupondatas;
    CouponExpAdapter expAdapter;
    int pn = 1;
    int type = 1;
    int delete_position;
    TextView twoTv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View twoView = inflater.inflate(R.layout.two_fragment, null);
        ButterKnife.inject(this, twoView);
        twoTv = (TextView) getActivity().findViewById(R.id.two_tv);
        myCouponListRequest();
        myinitlist();
        return twoView;
    }

    public void myinitlist() {
        coupondatas = new ArrayList<>();
        xlstvExp.setFooterDividersEnabled(false);
        // 设置xlistview可以加载、刷新
        xlstvExp.setPullLoadEnable(true);//购物车一次加载完 没有加载更多
        xlstvExp.setPullRefreshEnable(true);
        xlstvExp.setXListViewListener(this);
        xlstvExp.setOnItemClickListener(listListener);
        expAdapter = new CouponExpAdapter(getActivity(), coupondatas,handler);
        xlstvExp.setAdapter(expAdapter);

        Message msg = handler.obtainMessage();
        msg.what = 1;
        msg.arg1 = coupondatas.size();
        handler.sendMessage(msg);
    }
    AdapterView.OnItemClickListener listListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            position = position - 1;
            if (position < coupondatas.size()) {
                Intent intent = new Intent().setClass(getActivity(), CoupondetailsActivity.class);
                intent.putExtra("my_coupon_id", coupondatas.get(position).getMy_coupon_id());
                startActivity(intent);
            }
        }
    };

    public void myCouponListRequest() {
        MyCouponListRequest rq = new MyCouponListRequest();
        rq.setType("-1");
        rq.setPage_size(10);
        rq.setPn(1);
        // showWait(true);
        QuarkApi.HttpRequest(rq, mHandlerdata);
    }

    private final AsyncHttpResponseHandler mHandlerdata = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, getActivity(), MyCouponListResponse.class);
            if (kd != null) {
                MyCouponListResponse info = (MyCouponListResponse) kd;
                dealdatas(info);
            }
            //showWait(false);
        }

        @Override
        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
            AppContext.showToast("Network error" + arg0);
            // showWait(false);
        }
    };

    public void deteleMyCouponRequest() {
        DeteleMyCouponRequest rq = new DeteleMyCouponRequest();
        rq.setMy_coupon_id(coupondatas.get(delete_position).getMy_coupon_id());
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlerdel);
    }

    private final AsyncHttpResponseHandler mHandlerdel = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, getActivity(), DeteleMyCouponResponse.class);
            if (kd != null) {
                DeteleMyCouponResponse info = (DeteleMyCouponResponse) kd;
                if (info.getStatus() == 1) {
                    coupondatas.remove(delete_position);
                    expAdapter.notifyDataSetChanged();
                    twoTv.setText("Expired Coupon("+coupondatas.size()+")");

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

    public void dealdatas(MyCouponListResponse info) {
        if (type == 1) {
            coupondatas.clear();
        }
        List<ListMyCoupon> data = info.getMyCouponListResult().getMyCoupons().getList();
        Message msg = handler.obtainMessage();
        msg.what = type;
        if (data != null && data.size() > 0) {
            coupondatas.addAll(data);
            msg.arg1 = data.size();
        } else {
            msg.arg1 = 0;
        }
        handler.sendMessage(msg);
        twoTv.setText("Expired Coupon("+data.size()+")");
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int pdatasize = msg.arg1;
            xlstvExp.isnomore(pdatasize < 10);
            switch (msg.what) {
                case 1:
                    xlstvExp.stopRefresh();
                    expAdapter.notifyDataSetChanged();
                    break;
                case 2:
                    xlstvExp.stopLoadMore();
                    expAdapter.notifyDataSetChanged();
                    break;
                case 206:
                    delete_position = msg.arg1;
                    deteleMyCouponRequest();
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
        myCouponListRequest();
    }

    @Override
    public void onLoadMore() {
        type = 1;
        pn = 1;
        myCouponListRequest();

    }
}
