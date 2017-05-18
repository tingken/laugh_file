package com.quark.skillopedia.uiview.zhanghu.jiaolian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.quark.skillopedia.R;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.uiview.orders.OrdermanagenentActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 我是教练界面
 *
 * @author pan
 * @time 2016/8/22 0022 下午 5:40
 */
public class SkillopediaActivity extends BaseActivity {
    @InjectView(R.id.left_img)
    ImageView leftImg;
//    @InjectView(R.id.title)
//    TextView title;
//    @InjectView(R.id.one_ly)
//    LinearLayout oneLy;
//    @InjectView(R.id.two_ly)
//    LinearLayout twoLy;
//    @InjectView(R.id.fress_ly)
//    LinearLayout fressLy;

    @InjectView(R.id.red_notice)
    TextView red_notice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skillopedia);
        ButterKnife.inject(this);
        setTopTitle("Coach Management");
        setBackButton();
        String showRed = (String) getValue4Intent("showRed");
        if ("1".equals(showRed)) {
            red_notice.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
    }

    @OnClick({R.id.one_ly, R.id.two_ly, R.id.fress_ly, R.id.four_ly})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.one_ly:       //NEW COURSE
                Intent i1 = new Intent(SkillopediaActivity.this, NewcourseActivity.class);
                startActivity(i1);
                break;
            case R.id.two_ly:
                Intent i2 = new Intent(SkillopediaActivity.this, MycourseActivity.class);
                startActivity(i2);
                break;
            case R.id.fress_ly:
                Intent i3 = new Intent(SkillopediaActivity.this, OrdermanagenentActivity.class);
                startActivity(i3);
                break;
            case R.id.four_ly:
                Intent i4 = new Intent(SkillopediaActivity.this, ScheduleActivity.class);
                i4.putExtra("from", "MySchedule");
                startActivity(i4);
                break;
        }
    }
}
