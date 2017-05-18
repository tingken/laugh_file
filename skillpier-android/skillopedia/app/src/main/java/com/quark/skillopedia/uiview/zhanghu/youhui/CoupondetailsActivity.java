package com.quark.skillopedia.uiview.zhanghu.youhui;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.MyCouponInfoRequest;
import com.quark.api.auto.bean.MyCouponInfoResponse;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * 我的优惠券详情
 * @author pan
 * @time 2016/8/22 0022 下午 5:41
 */
public class CoupondetailsActivity extends BaseActivity {

    @InjectView(R.id.left)
    LinearLayout left;
    String My_coupon_id;
    @InjectView(R.id.provider)
    TextView provider;
    @InjectView(R.id.money_tv)
    TextView moneyTv;
    @InjectView(R.id.number_tv)
    TextView numberTv;
    @InjectView(R.id.begin_time)
    TextView beginTime;
    @InjectView(R.id.end_time)
    TextView endTime;
    @InjectView(R.id.coupon_rule)
    TextView couponRule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupondetails);
        ButterKnife.inject(this);
        setTopTitle("Coupon");
        My_coupon_id = (String) getValue4Intent("my_coupon_id");
        myCouponInfoRequest();

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.left)
    public void onClick() {
        finish();
    }

    public void myCouponInfoRequest() {
        MyCouponInfoRequest rq = new MyCouponInfoRequest();
        rq.setMy_coupon_id(My_coupon_id);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandler);
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, CoupondetailsActivity.this, MyCouponInfoResponse.class);
            if (kd != null) {
                MyCouponInfoResponse info = (MyCouponInfoResponse) kd;

                if (info.getStatus() == 1){


                    Date date = null;
                    Date date2 = null;
                    try {
                        date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(info.getMyCoupon().getBegin_time());
                        date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(info.getMyCoupon().getEnd_time());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    // 将date转化为String
                    String beginTIME = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH).format(date);
                    String begin = new SimpleDateFormat("HH:mm").format(date);

                    String endTIME = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH).format(date2);
                    String end = new SimpleDateFormat("HH:mm").format(date2);

                    provider.setText("by"+info.getMyCoupon().getProvider()+"provide");
                    numberTv.setText("x"+info.getMyCoupon().getCoupon_num()+"");
                    endTime.setText(endTIME+" "+end);
                    beginTime.setText("Valid for"+beginTIME+""+begin+"to");
                    couponRule.setText(info.getMyCoupon().getCoupon_rule());
                    moneyTv.setText("$"+info.getMyCoupon().getCoupon_money()+"");
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
}
