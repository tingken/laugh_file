package com.quark.skillopedia.uiview.dingdan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.CourseCouponList2Request;
import com.quark.api.auto.bean.CourseCouponListResponse;
import com.quark.api.auto.bean.ListMyCoupon;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.R;
import com.quark.skillopedia.adapter.ChooseCouponAdapter;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cz.msebera.android.httpclient.Header;

/**
 * @author pan
 *         created at 2016/5/30 0030
 * @选择优惠券
 */
public class ChooseCouponActivity extends BaseActivity {
    ChooseCouponAdapter adapter;

    List<ListMyCoupon> coupons;
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
    @InjectView(R.id.listview)
    ListView listview;
    @InjectView(R.id.nodata)
    TextView nodata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosecoupon);
        ButterKnife.inject(this);
        setTopTitle("Coupon");
        setBackButton();

        category_01_id = (String) getValue4Intent("category_01_id");
        category_02_id = (String) getValue4Intent("category_02_id");
        user_id = (String) getValue4Intent("user_id");
        course_id = (String) getValue4Intent("course_id");
        courseCouponList2Request();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
              if (position>0){
                  intent.putExtra("my_coupon_id", coupons.get(position).getMy_coupon_id() + "");
                  intent.putExtra("my_coupon_money", coupons.get(position).getCoupon_money() + "");
                  intent.putExtra("my_coupon_name", coupons.get(position).getCoupon_name() + "");
              }else{
                  intent.putExtra("my_coupon_id",  "");
                  intent.putExtra("my_coupon_money",  "");
                  intent.putExtra("my_coupon_name", "");
              }
                setResult(304, intent);
                finish();
            }
        });
    }

    private String category_01_id;//第一大类Id
    private String category_02_id;//第二大类Id
    private String user_id;//商家ID：发布者
    private String course_id;//课程ID：指定的课程

    public void courseCouponList2Request() {
        CourseCouponList2Request rq = new CourseCouponList2Request();
        rq.setCourse_id(course_id);
        rq.setCategory_01_id(category_01_id);
        rq.setCategory_02_id(category_02_id);
        rq.setUser_id(user_id);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandler);
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, ChooseCouponActivity.this, CourseCouponListResponse.class);
            if (kd != null) {
                CourseCouponListResponse info = (CourseCouponListResponse) kd;
                if (info.getStatus() == 1) {
                    coupons = info.getCourseCouponList2Result().getCourseCoupons();
//                    if (coupons==null||coupons.size()==0){
//                        nodata.setVisibility(View.VISIBLE);
//                        listview.setVisibility(View.GONE);
//                    }else{
                    ListMyCoupon noc = new ListMyCoupon();
                    noc.setCoupon_name("Without Coupon");
                    coupons.add(0, noc);
                    nodata.setVisibility(View.GONE);
                    listview.setVisibility(View.VISIBLE);
                    adapter = new ChooseCouponAdapter(ChooseCouponActivity.this, coupons);
                    listview.setAdapter(adapter);
//                    }
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

}
