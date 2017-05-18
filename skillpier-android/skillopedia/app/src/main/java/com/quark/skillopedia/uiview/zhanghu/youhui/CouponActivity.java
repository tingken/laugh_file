package com.quark.skillopedia.uiview.zhanghu.youhui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.GetCouponRequest;
import com.quark.api.auto.bean.GetCouponResponse;
import com.quark.api.auto.bean.ListMyCoupon;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.R;
import com.quark.skillopedia.adapter.CouponAdapter;
import com.quark.skillopedia.adapter.CouponExpAdapter;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseFragementActivity;
import com.quark.skillopedia.util.Utils;
import java.util.ArrayList;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
/**
 * 我的优惠券界面
 * @author pan
 * @time 2016/8/22 0022 下午 5:41
 */
public class CouponActivity extends BaseFragementActivity {

    @InjectView(R.id.coupon_et)
    EditText couponEt;
    @InjectView(R.id.get_it)
    Button getIt;
    @InjectView(R.id.remove)
    Button remove;
    @InjectView(R.id.one_color)
    TextView oneColor;
    @InjectView(R.id.two_color)
    TextView twoColor;
    @InjectView(R.id.one_tv)
    TextView oneTv;
    @InjectView(R.id.two_tv)
    TextView twoTv;
    ArrayList<ListMyCoupon> coupondatas;
    CouponAdapter couAdapter;
    CouponExpAdapter expAdapter;
    String Number;

    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            oneFragment = (OneFragment) fragmentManager.findFragmentByTag("oneCouFragment");
            twoFragment = (TwoFragment) fragmentManager.findFragmentByTag("twoCouFragment");
        }
        setContentView(R.layout.activity_coupon);
        ButterKnife.inject(this);
        setTopTitle("Coupons");
        setBackButton();
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                couponEt.setText("");
            }
        });
        setTabSelection(1);
        setTabSelection(0);
    }

    @OnClick({R.id.one_tv, R.id.two_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.one_tv:
                clearSelection();
                setTabSelection(0);
                break;
            case R.id.two_tv:
                clearSelection();
                setTabSelection(1);
                break;
        }
    }

    @OnClick(R.id.get_it)
    public void onClick() {
        if (check()) {
            getCouponRequest();
        }
    }

    private boolean check() {
        Number = couponEt.getText().toString();
        if (Utils.isEmpty(Number)) {
            showToast("please enter coupon codes");
            return false;
        }
        return true;
    }

    public void getCouponRequest() {
        GetCouponRequest rq = new GetCouponRequest();
        rq.setNumber(Number);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlerget);
    }

    private final AsyncHttpResponseHandler mHandlerget = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, CouponActivity.this, GetCouponResponse.class);
            if (kd != null) {
                GetCouponResponse info = (GetCouponResponse) kd;
                showToast(info.getMessage());
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

    private void setTabSelection(int index) {
        clearSelection();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case 0:
                oneColor.setVisibility(View.VISIBLE);
                oneTv.setTextColor(getResources().getColor(R.color.chengse));
                if (oneFragment == null) {
                    oneFragment = new OneFragment();
                    transaction.add(R.id.coupon_content, oneFragment, "oneCouFragment");
                } else {
                    transaction.show(oneFragment);
                }

                break;
            case 1:
                twoColor.setVisibility(View.VISIBLE);
                twoTv.setTextColor(getResources().getColor(R.color.chengse));
                if (twoFragment == null) {
                    twoFragment = new TwoFragment();
                    transaction.add(R.id.coupon_content, twoFragment, "twoCouFragment");
                } else {
                    transaction.show(twoFragment);
                }
        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (oneFragment != null) {
            transaction.hide(oneFragment);
        }
        if (twoFragment != null) {
            transaction.hide(twoFragment);
        }
    }

    private void clearSelection() {
        oneColor.setVisibility(View.INVISIBLE);
        twoColor.setVisibility(View.INVISIBLE);
        oneTv.setTextColor(getResources().getColor(R.color.qianhuise));
        twoTv.setTextColor(getResources().getColor(R.color.qianhuise));
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
