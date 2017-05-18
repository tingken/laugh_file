package com.quark.skillopedia.uiview.zhanghu.jiaolian;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quark.api.auto.bean.AddCourseRequest;
import com.quark.api.auto.bean.EditCourse;
import com.quark.skillopedia.Googlemap.SiteActivity;
import com.quark.skillopedia.R;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.ui.widget.WheelChooseSingleDialog;
import com.quark.skillopedia.util.TLog;
import com.quark.skillopedia.util.Utils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/5/25 0025.
 * 新建课程2
 */
public class NewcourseTwoActivity extends BaseActivity {
    AddCourseRequest rq;

    @InjectView(R.id.left_img)
    ImageView leftImg;
    @InjectView(R.id.left)
    LinearLayout left;
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.timelong)
    TextView timelong;
    @InjectView(R.id.rate)
    EditText rate;
    @InjectView(R.id.teachingage)
    EditText teachingageET;
    @InjectView(R.id.teachingsince)
    TextView teachingsince;
    @InjectView(R.id.travelyes)
    TextView travelyes;
    @InjectView(R.id.travelno)
    TextView travelno;
    @InjectView(R.id.distance)
    TextView distance;
    @InjectView(R.id.tracfic_surcharge)
    EditText tracficSurcharge;
    @InjectView(R.id.travelview)
    LinearLayout travelview;
    @InjectView(R.id.addresstext)
    TextView addresstext;
    @InjectView(R.id.addpartner)
    EditText addpartner;
    @InjectView(R.id.surcharge)
    EditText surcharge;

    @InjectView(R.id.discount_one_text)
    TextView discountOneText;
    @InjectView(R.id.discount_two_text)
    TextView discountTwoText;
    @InjectView(R.id.discount_three_text)
    TextView discountThreeText;

    public static NewcourseTwoActivity  instance;
    EditCourse course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcourse_two);
        ButterKnife.inject(this);
        setTopTitle("New course");
        setBackButton();
        rq = (AddCourseRequest) getValue4Intent("courseRequest");
        instance = this;
        course = (EditCourse)getValue4Intent("course");
        if (course!=null){
            initEditview();
        }
    }

    public void initEditview(){
        timelong.setText(course.getSession_length() + "min");
        rate.setText(course.getSession_rate() + "");
        teachingageET.setText(course.getTeaching_age() + "");
        teachingsince.setText(course.getTeaching_since() + " year");
        String tts = course.getTravel_to_session();
        if (tts.equals("1")){
            settravel(1);
            distance.setText(course.getTravel_to_session_distance() + " (miles)");
            tracficSurcharge.setText(course.getTravel_to_session_trafic_surcharge());
        }else{
            settravel(0);
        }
        //地址
        city = course.getCity();
        street = course.getStreet();
        address = course.getAddress();
        latitude = course.getLatitude();
        longitude = course.getLongitude();
        zipcode = course.getZipcode();
        area = course.getArea();
        addresstext.setText(address);

        addpartner.setText(course.getAdditional_partner());
        surcharge.setText(course.getSurcharge_for_each());
        String discount_type = course.getDiscount_type();

        try {
            discount_onetion_pur_money_01 = course.getDiscount_onetion_pur_money_01();
            discount_price_01 = course.getDiscount_price_01();
            double d1 = Double.valueOf(discount_price_01);
            discount_price_01 = ((int)d1)+"";
            discountOneText.setText(discount_price_01+"");

            discount_onetion_pur_money_02 = course.getDiscount_onetion_pur_money_02();
            discount_price_02 = course.getDiscount_price_02();
            double d2 = Double.valueOf(discount_price_02);
            discount_price_02 = ((int)d2)+"";
            discountTwoText.setText(discount_price_02+"");

            discount_onetion_pur_money_03 = course.getDiscount_onetion_pur_money_03();
            discount_price_03 = course.getDiscount_price_03();
            double d3 = Double.valueOf(discount_price_03);
            discount_price_03 = ((int)d3)+"";
            discountThreeText.setText(discount_price_03+"");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.timelong, R.id.timelongpic, R.id.teachingsince, R.id.teachingsincepic, R.id.travel_to_sessions,
            R.id.travelyes, R.id.travelno, R.id.distance, R.id.discount_one, R.id.discount_two, R.id.discount_none,R.id.teachingage})
    public void ct(View b) {
        switch (b.getId()) {
            case R.id.timelongpic:
            case R.id.timelong:
                String[] timelong = {"60min"};
                WheelChooseSingleDialog wccd = new WheelChooseSingleDialog();
                wccd.showSheetPic(NewcourseTwoActivity.this, handler, 101, timelong, "60min");
                break;

            case R.id.teachingage:
                String[] teachingages = {"4+","10+","15+","18+","30+"};
                WheelChooseSingleDialog wc = new WheelChooseSingleDialog();
                wc.showSheetPic(NewcourseTwoActivity.this, handler, 102, teachingages, "4+");
                break;

            case R.id.teachingsince:
            case R.id.teachingsincepic:
//                String[] teachingsincetime = {"1 year", "2 year", "3 year", "4 year", "5 year", "6 year", "7 year", "8 year", "9 year", "10 year"};
                String[] teachingsincetime = {"1 year","3 year", "5 year","8 year", "10 year", "15 year"};
                WheelChooseSingleDialog wccd2 = new WheelChooseSingleDialog();
                wccd2.showSheetPic(NewcourseTwoActivity.this, handler, 103, teachingsincetime, "1 year");
                break;
            case R.id.travelyes:
                settravel(1);
                break;
            case R.id.travelno:
                settravel(0);
                break;
            case R.id.distance:
                String[] distances = {"1 (miles)", "2 (miles)", "3 (miles)", "4 (miles)", "5 (miles)", "6 (miles)", "7 (miles)", "8 (miles)", "9 (miles)", "10 (miles)",
                        "11 (miles)", "12 (miles)", "13 (miles)", "14 (miles)", "15 (miles)", "16 (miles)", "17 (miles)", "18 (miles)", "19 (miles)", "20 (miles)", "21 (miles)",
                        "22 (miles)", "23 (miles)", "24 (miles)", "25 (miles)", "26 (miles)", "27 (miles)", "28 (miles)", "29 (miles)", "30 (miles)",
                        "31 (miles)", "32 (miles)", "33 (miles)", "34 (miles)", "35 (miles)", "36 (miles)", "37 (miles)", "38 (miles)", "39 (miles)", "40 (miles)",
                        "41 (miles)", "42 (miles)", "43 (miles)", "44 (miles)", "45 (miles)", "46 (miles)", "47 (miles)", "48 (miles)", "49 (miles)", "50 (miles)",};
                WheelChooseSingleDialog wccd3 = new WheelChooseSingleDialog();
                wccd3.showSheetPic(NewcourseTwoActivity.this, handler, 104, distances, "1 (miles)");
                break;

            case R.id.travel_to_sessions:
                if (check()) {
                    rq.setSession_length(session_length);
                    rq.setSession_rate(session_rate);
                    rq.setTeaching_age(teaching_age);
                    rq.setTeaching_since(teaching_since);
                    rq.setTravel_to_session(travel_to_session);
                    rq.setTravel_to_session_distance(travel_to_session_distance);
                    rq.setTravel_to_session_trafic_surcharge(travel_to_session_trafic_surcharge);
                    //====
                    rq.setCity(city);
                    rq.setArea(area);
                    rq.setStreet(street);
                    rq.setAddress(address);
                    rq.setLatitude(latitude);
                    rq.setLongitude(longitude);
                    rq.setZipcode(zipcode);
                    rq.setAdditional_partner(additional_partner);
                    rq.setSurcharge_for_each(surcharge_for_each);
                    rq.setDiscount_type(discount_type);

                    rq.setDiscount_onetion_pur_money_01(discount_onetion_pur_money_01);
                    rq.setDiscount_price_01(discount_price_01);
                    rq.setDiscount_onetion_pur_money_02(discount_onetion_pur_money_02);
                    rq.setDiscount_price_02(discount_price_02);
                    rq.setDiscount_onetion_pur_money_03(discount_onetion_pur_money_03);
                    rq.setDiscount_price_03(discount_price_03);

                    Bundle bundle10 = new Bundle();
                    bundle10.putSerializable("courseRequest", rq);
                    if (course!=null){
                        bundle10.putSerializable("course", course);
                    }
                    startActivityByClass(NewcourseThreeActivity.class, bundle10);
                }
                break;
        }
    }

    @OnClick(R.id.addressview)
    public void chooseaddrss(View v){
        Intent intent = new Intent();
        intent.putExtra("latitude",latitude);
        intent.putExtra("longitude",longitude);
        intent.putExtra("city",city);
        intent.putExtra("street",street);
        intent.putExtra("address",address);
        intent.putExtra("zipcode",zipcode);
        intent.putExtra("area",area);
        intent.putExtra("from","newCourse");
        intent.setClass(this, SiteActivity.class);
        startActivityForResult(intent,1001);
    }

    public void settravel(int type){
        if (type==1){
            travelyes.setBackgroundDrawable(getResources().getDrawable(R.drawable.left_choose));
            travelno.setBackgroundDrawable(getResources().getDrawable(R.drawable.right_nomal));
            travelyes.setTextColor(getResources().getColor(R.color.white));
            travelno.setTextColor(getResources().getColor(R.color.chengse));
            travel_to_session = "1";
            travelview.setVisibility(View.VISIBLE);
        }else{
            travelyes.setBackgroundDrawable(getResources().getDrawable(R.drawable.left_nomal));
            travelno.setBackgroundDrawable(getResources().getDrawable(R.drawable.right_choose));
            travelyes.setTextColor(getResources().getColor(R.color.chengse));
            travelno.setTextColor(getResources().getColor(R.color.white));
            travel_to_session = "0";
            travelview.setVisibility(View.GONE);
        }
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 101:  //类型
                    String cctv = (String) msg.obj;
                    timelong.setText(cctv);

                    break;
                case 102:  //类型
                    String cctv12 = (String) msg.obj;
                    teaching_age = cctv12;
                    teachingageET.setText(cctv12);

                    break;
                case 103:
                    String cctv2 = (String) msg.obj;
                    teaching_since = cctv2;
                    teachingsince.setText(cctv2);

                    break;
                case 104:
                    String cctv4 = (String) msg.obj;
                    travel_to_session_distance = cctv4;
                    distance.setText(travel_to_session_distance);
                    break;

                default:
                    break;
            }
            super.handleMessage(msg);
        };
    };

    private String session_length;//课程时长60min  以60min为单位的
    private String session_rate;//课程费用
    private String teaching_age;//教育年限【age 5 and up】
    private String teaching_since;//开始教育时间【since Jun 2007】
    private String travel_to_session="1";//是否上门服务:1-是，0-否【如果选择no
    private String travel_to_session_distance="";//上门服务距离【miles】【可以接受多大范围内的上门服务】
    private String travel_to_session_trafic_surcharge="";//上门服务交通费
    private String city="";//州【上课地址】
    private String area="";//城市【上课地址】
    private String street="";//街道【上课地址】
    private String address="";//详细地址【上课地址】
    private String latitude="";//维度【上课地址】
    private String longitude="";//经度【上课地址】
    private String zipcode="";//城市编码
    private String additional_partner;//额外的最多人【打折幅度】
    private String surcharge_for_each;//每个人的附加费用【打折幅度】
    private String discount_type="2";//打折类型：1-by_total

    private String discount_onetion_pur_money_01 = "3";//A:选择 By account的意思：一次性购买多少课程，打折多少B:选择 By total的意思：一次性购买多少钱，打折多少
    private String discount_price_01="0";  //打折价格
    private String discount_onetion_pur_money_02="5";//如果选中by total:One-time Purchase $XX
    private String discount_price_02="0"; //打折价格
    private String discount_onetion_pur_money_03="10";//
    private String discount_price_03="0"; //打折价格

    //按总价的类型
//    private String discount_onetion_pur_money_01_total = "";//A:选择 By account的意思：一次性购买多少课程，打折多少B:选择 By total的意思：一次性购买多少钱，打折多少
//    private String discount_price_01_total="";  //打折价格
//    private String discount_onetion_pur_money_02_total="";//如果选中by total:One-time Purchase $XX
//    private String discount_price_02_total=""; //打折价格
//    private String discount_onetion_pur_money_03_total="";//
//    private String discount_price_03_total=""; //打折价格

    public boolean check() {
        session_length = timelong.getText().toString();
        session_rate = rate.getText().toString();
        teaching_age = teachingageET.getText().toString();
        teaching_since = teachingsince.getText().toString();
        additional_partner = addpartner.getText().toString();
        surcharge_for_each = surcharge.getText().toString();

        discount_price_01 = discountOneText.getText().toString();
        discount_price_02 = discountTwoText.getText().toString();
        discount_price_03 = discountThreeText.getText().toString();

        if (Utils.isEmpty(session_length)) {
            showToast("Please enter the length of the course");
            return false;
        } else {
            session_length = session_length.substring(0, session_length.length() - 3); //去除min
        }

        if (Utils.isEmpty(session_rate)) {
            showToast("Course fee");
            return false;
        }

        if (Utils.isEmpty(teaching_age)) {
            showToast("Please enter the education period");
            return false;
        }
        if (Utils.isEmpty(teaching_since)) {
            showToast("Please choose to start time");
            return false;
        } else {
            teaching_since = teaching_since.substring(0, teaching_since.length() - 5); //去除“ year”
        }
        //上门服务
        if (travel_to_session.equals("1")) {
            travel_to_session_distance = distance.getText().toString();

            if (Utils.isEmpty(travel_to_session_distance)) {
                showToast("Please select the distance from the home service");
                return false;
            } else {
                travel_to_session_distance = travel_to_session_distance.substring(0, travel_to_session_distance.length() - 8); //去除“ （mile）”
            }

            travel_to_session_trafic_surcharge = tracficSurcharge.getText().toString();
            if (Utils.isEmpty(travel_to_session_trafic_surcharge)) {
                showToast("Please select a door-to-door transportation services");
                return false;
            }
        }

        //地址
        if (Utils.isEmpty(additional_partner)) {
            showToast("Please enter the maximum number of additional");
            return false;
        }
        if (Utils.isEmpty(surcharge_for_each)) {
            showToast("Each person's additional cost");
            return false;
        }

//        if (Utils.isEmpty(discount_price_01)){
//            discount_price_01 = 0;
//        }
//
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode!=0){
            if (requestCode==1001){
                city = data.getStringExtra("city");
                area = data.getStringExtra("area");
                street = data.getStringExtra("street");
                address = data.getStringExtra("address");
                latitude = data.getStringExtra("latitude");
                longitude = data.getStringExtra("longitude");
                zipcode = data.getStringExtra("zipcode");
                TLog.error(city+" ="+area+" ="+street+" ="+address+" ="+latitude+" ="+longitude+" ="+zipcode);
                addresstext.setText(address);
            }
        }
    }
}
