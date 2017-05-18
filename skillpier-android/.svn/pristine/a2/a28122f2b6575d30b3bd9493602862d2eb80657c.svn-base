package com.quark.skillopedia.uiview.dingdan;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.joooonho.SelectableRoundedImageView;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.AddPayRequest;
import com.quark.api.auto.bean.AddPayResponse;
import com.quark.api.auto.bean.CourseBuyDetail;
import com.quark.api.auto.bean.OrderCourseInfoRequest;
import com.quark.api.auto.bean.OrderCourseInfoResponse;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.AppParam;
import com.quark.skillopedia.Googlemap.SiteActivity;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiHttpClient;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.uiview.NewLoginActivity;
import com.quark.skillopedia.uiview.fenlei.ScheduleBookingActivity;
import com.quark.skillopedia.util.TLog;
import com.quark.skillopedia.util.Utils;

import java.text.DecimalFormat;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * @author pan
 *         created at 2016/5/30 0030
 *         确认下单
 */
public class FillOrderActivity extends BaseActivity {
    @InjectView(R.id.text1)
    TextView text1;
    @InjectView(R.id.text3)
    TextView text3;
    @InjectView(R.id.money)
    TextView money;
    @InjectView(R.id.ratingBar)
    RatingBar ratingBar;
    @InjectView(R.id.grade_tv)
    TextView gradeTv;
    @InjectView(R.id.miles_tv)
    TextView milesTv;
    @InjectView(R.id.football_tv)
    TextView footballTv;
    @InjectView(R.id.number)
    TextView number;
    @InjectView(R.id.discountView)
    LinearLayout discountView;
    @InjectView(R.id.firstserver_text)
    TextView firstserverText;
    @InjectView(R.id.firstserver)
    LinearLayout firstserver;
    @InjectView(R.id.addressview)
    TextView addressview;
    @InjectView(R.id.chooseaddress)
    LinearLayout chooseaddress;
    @InjectView(R.id.cantravel)
    ImageView cantravel;
    @InjectView(R.id.trafficcost)
    TextView trafficcost;
    @InjectView(R.id.trafficView)
    LinearLayout trafficView;
    @InjectView(R.id.addcose)
    TextView addcose;
    @InjectView(R.id.addperson)
    TextView addperson;
    @InjectView(R.id.selecttime)
    TextView selecttime;
    @InjectView(R.id.coupontext)
    TextView coupontext;
    @InjectView(R.id.message)
    EditText message;
    @InjectView(R.id.discountline)
    View discountline;
    @InjectView(R.id.firstserver2_text)
    TextView firstserver2Text;
    @InjectView(R.id.iamge_iv)
    SelectableRoundedImageView iamgeIv;
    @InjectView(R.id.cantrivial_lay)
    LinearLayout cantrivial_lay;
    @InjectView(R.id.address_tip)
    TextView address_tip;
    @InjectView(R.id.price_tv)
    TextView priceTv;
    @InjectView(R.id.price_ago)
    TextView priceAgo;
    @InjectView(R.id.price_after)
    TextView priceAfter;
    @InjectView(R.id.one_fee_tv)
    TextView oneFeeTv;
    @InjectView(R.id.fee_tv)
    TextView feeTv;
    @InjectView(R.id.partner_tv)
    TextView partnerTv;
    @InjectView(R.id.partner_ago)
    TextView partnerAgo;
    @InjectView(R.id.partner_after)
    TextView partnerAfter;
    @InjectView(R.id.total_price_tv)
    TextView totalPriceTv;
    @InjectView(R.id.service_tip)
    TextView service_tip;
    @InjectView(R.id.service_lay)
    LinearLayout service_lay;
    @InjectView(R.id.coustomerName)
    EditText coustomerName;
    @InjectView(R.id.coustomerCellphone)
    EditText coustomerCellphone;

    @InjectView(R.id.coupon_lay)
    LinearLayout couponLay;
    @InjectView(R.id.coupon_tv2)
    TextView couponTv2;
    @InjectView(R.id.travel_lay)
    LinearLayout travelLay;
    @InjectView(R.id.travel_tv2)
    TextView travelTv2;

    String coure_id;
    CourseBuyDetail course;
    int sw;
    //打折类型：1-by_total 2-by_account
    public String discount_type;
    public int discount_onetion_pur_money_01;
    public int discount_price_01;
    public int discount_onetion_pur_money_02;
    public int discount_price_02;
    public int discount_onetion_pur_money_03;
    public int discount_price_03;
    public double first_joint_fee;
    double session_rate;  //课程单价【费用1】
    double go_door_traffic_cost;
    int additional_partner; //最多可带人数
    public static FillOrderActivity instance;
    String coustomerNameStr;
    String coustomerCellphoneStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fillorder);
        ButterKnife.inject(this);
        setBackButton();
        setTopTitle("Fill in order");
        coure_id = (String) getValue4Intent("coure_id");
        chooseaddress.setEnabled(false);
        sw = new AppParam().getScreenWidth(this);

        orderCourseInfoRequest();
        instance = this;
    }

    public void orderCourseInfoRequest() {
        OrderCourseInfoRequest rq = new OrderCourseInfoRequest();
        rq.setCourse_id(coure_id);
        rq.setLatitude(new AppParam().getLat(this));
        rq.setLongitude(new AppParam().getLng(this));
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandler);
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, FillOrderActivity.this, OrderCourseInfoResponse.class);
            if (kd != null) {
                OrderCourseInfoResponse info = (OrderCourseInfoResponse) kd;
                course = info.getCourse();
                dealdata();
            }
            showWait(false);
        }

        @Override
        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
            AppContext.showToast("Network error" + arg0);
            showWait(false);
        }
    };

    public void dealdata() {
        discount_type = course.getDiscount_type();
        additional_partner = course.getAdditional_partner();
        //原本是double类型的 修改为只有int
        double discount_onetion_pur_money_01_d = Double.valueOf(course.getDiscount_onetion_pur_money_01());
        double discount_price_01_d = Double.valueOf(course.getDiscount_price_01());
        double discount_onetion_pur_money_02_d = Double.valueOf(course.getDiscount_onetion_pur_money_02());
        double discount_price_02_d = Double.valueOf(course.getDiscount_price_02());
        double discount_onetion_pur_money_03_d = Double.valueOf(course.getDiscount_onetion_pur_money_03());
        double discount_price_03_d = Double.valueOf(course.getDiscount_price_03());

        discount_onetion_pur_money_01 = (int) discount_onetion_pur_money_01_d;
        discount_price_01 = (int) discount_price_01_d;
        discount_onetion_pur_money_02 = (int) discount_onetion_pur_money_02_d;
        discount_price_02 = (int) discount_price_02_d;
        discount_onetion_pur_money_03 = (int) discount_onetion_pur_money_03_d;
        discount_price_03 = (int) discount_price_03_d;

        session_rate = Double.valueOf(course.getSession_rate());//课程价格

        //返回的服务费是百分比 第一次服务费 = 课程单价*服务费比例
        double first_joint_fee_per = Double.valueOf(course.getFirst_joint_fee());
        first_joint_fee_per = first_joint_fee_per / 100;

        first_joint_fee = first_joint_fee_per * session_rate;//第一次服务费

        go_door_traffic_cost = Double.valueOf(course.getTravel_to_session_trafic_surcharge());//交通费
        surcharge_for_each_cash = Double.valueOf(course.getSurcharge_for_each());//多带人费用
        ApiHttpClient.loadImage(course.getUser_image_01(), iamgeIv, R.drawable.example_7);
        text1.setText(course.getTitle());
        text3.setText("BY " + course.getNickname());
        money.setText("$" + course.getSession_rate());
        String score = course.getTotal_score();
        try {
            float f = Float.valueOf(score);
            ratingBar.setRating(f);

            int scoreint = (int) f;
//            gradeTv.setText(scoreint + " review");
            gradeTv.setText(course.getTotal_coment_num() + " review");
        } catch (Exception e) {
            TLog.error("float转化失败");
        }

        milesTv.setText(course.getDistance());
        footballTv.setText(course.getCategory_02_name());
        //===打折========
        if (discount_price_01 != 0) {
            discountline.setVisibility(View.GONE);
            View discview = LayoutInflater.from(this).inflate(R.layout.discount_item, null);
            TextView discount_text = (TextView) discview.findViewById(R.id.discount_text);
            TextView disnumber = (TextView) discview.findViewById(R.id.number);
            disnumber.setText("3");
            discount_text.setText(discount_price_01 + "%");

            LinearLayout playout = (LinearLayout) discview.findViewById(R.id.playout);
            int psw = (sw - Utils.dip2px(this, 28)) / 3;
            ViewGroup.LayoutParams params2 = playout.getLayoutParams();
            params2.width = psw;
            params2.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            discview.setLayoutParams(params2);

            discountView.addView(discview);
            playout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buy_amount = 3;
                    number.setText(buy_amount + "");
                    calculation();//计算金额
                }
            });
        }

        if (discount_price_02 != 0) {
            discountline.setVisibility(View.GONE);
            View discview = LayoutInflater.from(this).inflate(R.layout.discount_item, null);
            TextView discount_text = (TextView) discview.findViewById(R.id.discount_text);
            TextView disnumber = (TextView) discview.findViewById(R.id.number);
            disnumber.setText("5");
            discount_text.setText(discount_price_02 + "%");

            LinearLayout playout = (LinearLayout) discview.findViewById(R.id.playout);
            int psw = (sw - Utils.dip2px(this, 28)) / 3;
            ViewGroup.LayoutParams params2 = playout.getLayoutParams();
            params2.width = psw;
            params2.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            discview.setLayoutParams(params2);

            discountView.addView(discview);
            playout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buy_amount = 5;
                    number.setText(buy_amount + "");
                    calculation();//计算金额
                }
            });
        }

        if (discount_price_03 != 0) {
            discountline.setVisibility(View.GONE);
            View discview = LayoutInflater.from(this).inflate(R.layout.discount_item, null);
            TextView discount_text = (TextView) discview.findViewById(R.id.discount_text);
            TextView disnumber = (TextView) discview.findViewById(R.id.number);
            disnumber.setText("10");
            discount_text.setText(discount_price_03 + "%");

            LinearLayout playout = (LinearLayout) discview.findViewById(R.id.playout);
            int psw = (sw - Utils.dip2px(this, 28)) / 3;
            ViewGroup.LayoutParams params2 = playout.getLayoutParams();
            params2.width = psw;
            params2.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            discview.setLayoutParams(params2);

            discountView.addView(discview);
            playout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buy_amount = 10;
                    number.setText(buy_amount + "");
                    calculation();//计算金额
                }
            });
        }
        //===打折========
        if (first_joint_fee != 0) {
            firstserver.setVisibility(View.VISIBLE);
            firstserverText.setText("Service cost: ");
            firstserver2Text.setText("$" + Utils.DoubleFormat(first_joint_fee));
        } else {
            service_lay.setVisibility(View.GONE);//如果没有服务费 不显示服务费
        }
        addressview.setText(course.getCity() + course.getStreet());
        if (course.getTravel_to_session().equals("1")) {
            cantrivial_lay.setVisibility(View.VISIBLE);
            trafficcost.setText(course.getTravel_to_session_trafic_surcharge());
        } else {
            cantrivial_lay.setVisibility(View.GONE);
        }
        addcose.setText("$" + course.getSurcharge_for_each() + " extra for each partner. Up to " + course.getAdditional_partner() + " partners allowed.");
        calculation();//计算金额

        go_door_city = course.getCity();
        go_door_area = course.getArea();
        go_door_street = course.getStreet();
        go_door_address = course.getAddress();
        go_door_latitude = course.getLatitude();
        go_door_longitude = course.getLongitude();
        go_door_zipcode = course.getZipcode();
    }

    @OnClick({R.id.minus_tv, R.id.add_tv, R.id.cantravel, R.id.minus_tv2, R.id.add_tv2, R.id.selecttime, R.id.coupontext
            , R.id.chooseaddress})
    public void onc(View v) {
        switch (v.getId()) {
            case R.id.minus_tv:
                if (buy_amount > 1) {
                    buy_amount--;
                    number.setText(buy_amount + "");
                    calculation();//计算金额
                }
                break;
            case R.id.add_tv:
                if (buy_amount < 50) {
                    buy_amount++;
                    number.setText(buy_amount + "");
                    calculation();//计算金额
                }
                break;
            case R.id.cantravel:
                if (go_door_status.equals("0")) {
                    trafficView.setVisibility(View.VISIBLE);
                    cantravel.setImageDrawable(getResources().getDrawable(R.drawable.on));
                    go_door_status = "1";
                    addressview.setText("");
                    chooseaddress.setEnabled(true);
                    calculation();//计算金额
                } else {
                    trafficView.setVisibility(View.GONE);
                    cantravel.setImageDrawable(getResources().getDrawable(R.drawable.off));
                    go_door_status = "0";
                    addressview.setText(course.getCity() + course.getStreet());
                    chooseaddress.setEnabled(false);
                    calculation();//计算金额
                }
                break;
            case R.id.minus_tv2:
                if (take_partner_num > 0) {
                    take_partner_num--;
                    addperson.setText(take_partner_num + "");
                    calculation();//计算金额
                }
                break;
            case R.id.add_tv2:
                if (take_partner_num < additional_partner) {
                    take_partner_num++;
                    addperson.setText(take_partner_num + "");
                    calculation();   //计算金额
                }
                break;
            case R.id.selecttime:
                Intent intent3 = new Intent();
                intent3.putExtra("coure_id", course.getCourse_id() + "");
                intent3.putExtra("buy_amount", buy_amount + "");
                if (!Utils.isEmpty(countNumber_schedule)) {
                    int cs = Integer.valueOf(countNumber_schedule);
                    int ccount = 0;
                    if (buy_amount > cs) {
                        ccount = buy_amount - cs;
                    }
                    intent3.putExtra("countNumber", ccount + "");
                } else {
                    intent3.putExtra("countNumber", buy_amount + "");
                }
                intent3.putExtra("coureTitle", course.getTitle() + "");
                intent3.putExtra("from", "detail");
                intent3.putExtra("schedule_datas", schedule_datas); //进去的时候保留选择项
                intent3.putExtra("freeCourseDay", course.getFreeCourseDay());
                intent3.setClass(this, ScheduleBookingActivity.class);

                startActivityForResult(intent3, 1003);
                break;
            case R.id.coupontext:
                if (new AppParam().isLogin(this)) {
                    Intent intent2 = new Intent();
                    intent2.putExtra("category_01_id", course.getCategory_01_id() + "");
                    intent2.putExtra("category_02_id", course.getCategory_02_id() + "");
                    intent2.putExtra("user_id", course.getUser_id() + "");
                    intent2.putExtra("course_id", course.getCourse_id() + "");
                    intent2.setClass(this, ChooseCouponActivity.class);
                    startActivityForResult(intent2, 1002);
                } else {
                    startActivity(new Intent().setClass(this, NewLoginActivity.class));
                }
                break;
            case R.id.chooseaddress:
                Intent intent = new Intent();
//                intent.putExtra("longitude", new AppParam().getLng(this));
//                intent.putExtra("latitude", new AppParam().getLat(this));
                intent.putExtra("coure_id", coure_id);
                intent.putExtra("from", "fillOrder");
                intent.putExtra("latitude",go_door_latitude);
                intent.putExtra("longitude",go_door_longitude);
                intent.putExtra("city",go_door_city);
                intent.putExtra("street",go_door_street);
                intent.putExtra("address",go_door_address);
                intent.putExtra("zipcode",go_door_zipcode);
                intent.putExtra("area",go_door_area);

                intent.setClass(this, SiteActivity.class);
                startActivityForResult(intent, 1001);
                break;
        }
    }

    @OnClick({R.id.pay_tv, R.id.tocar})
    public void tp(View b) {
        if (new AppParam().isLogin(this)) {
            switch (b.getId()) {
                case R.id.pay_tv:
                    order_type = "11";
                    if ((go_door_status.equals("1") && Utils.isEmpty(go_door_latitude)) || checkTravel) {
//                    showToast("Please choose to go to school address");
                    } else {

                        addPayRequest("11");
                    }
                    break;
                case R.id.tocar:
                    order_type = "1";
                    if ((go_door_status.equals("1") && Utils.isEmpty(go_door_latitude)) || checkTravel) {
//                    showToast("Please choose to go to school address");
                    } else {
                        addPayRequest("1");
                    }
                    break;
            }
        } else {
            startActivity(new Intent().setClass(this, NewLoginActivity.class));
        }
    }

    private String order_type;  //1-加入购物车，11-直接购买
    private String address = "";//city area street address 拼接
    private int buy_amount = 1;//购买数量
    private String go_door_status = "0";//是否上门服务：0-关闭，1-上门【如果选择上门服务，增加交通费，Teaching Location可选择】
    private String go_door_city;//州【travel to session选择no后，不显示地址，表示只提供上门服务，这个时候，用户课程下单的时候必须填写地址才能下单】
    private String go_door_area;//城市
    private String go_door_street;//街道
    private String go_door_address;//详细地址
    private String go_door_latitude;//维度
    private String go_door_longitude;//经度
    private String go_door_zipcode;//城市编码
    private String my_coupon_id = "0";//0-表示没有，优惠券Id
    private Double my_coupon_money = 0.0;//优惠券金额【优惠2】
    private int take_partner_num = 0;//带的人数【费用4】
    private double surcharge_for_each_cash;//每个人的附加费用
    private String schedule_datas = "";//课程日期A日期下标A日期下标如：2015-06-11A1A2#2015-06-12A21A22#2015-06-14A23A24{一节课60分钟，一个数字代表半节课}

    public void addPayRequest(String order_type) {
        if (checkPay()) {
            if (new AppParam().isLogin(this)) {
                String msg = message.getText().toString();
                AddPayRequest rq = new AddPayRequest();

                rq.setOrder_type(order_type);  //1-加入购物车，11-直接购买
                rq.setCourse_id(coure_id);//课程id
                rq.setTitle(course.getTitle());//课程标题

                rq.setCourse_user_id(course.getUser_id());//教练id
                rq.setBuy_amount(buy_amount + "");//购买数量
                rq.setSession_rate(course.getSession_rate());//课程单价【费用1】

                rq.setGo_door_status(go_door_status);//是否上门服务：0-关闭，1-上门【如果选择上门服务，增加交通费，Teaching Location可选择】
                rq.setAddress(course.getAddress());//city area street address 拼接
                if (go_door_status.equals("1")) {
                    rq.setGo_door_city(go_door_city);//州【travel to session选择no后，不显示地址，表示只提供上门服务，这个时候，用户课程下单的时候必须填写地址才能下单】
                    rq.setGo_door_area(go_door_area);//城市
                    rq.setGo_door_street(go_door_street);//街道
                    rq.setGo_door_address(go_door_address);//详细地址
                    rq.setGo_door_latitude(go_door_latitude);//维度
                    rq.setGo_door_longitude(go_door_longitude);//经度
                    rq.setGo_door_zipcode(go_door_zipcode);//城市编码
                    rq.setGo_door_traffic_cost(course.getTravel_to_session_trafic_surcharge());//如果选择上门服务，增加交通费课程单价【费用2】
                    rq.setAddress(address);  //外出上课地点==============city area street address 拼接
                }
                rq.setMy_coupon_id(my_coupon_id);//0-表示没有，优惠券Id
                rq.setMy_coupon_money(my_coupon_money + "");//优惠券金额【优惠2】
                rq.setLeave_message(msg);//给商家留言
                rq.setDiscount_type(course.getDiscount_type());//打折类型：1-by_total
                rq.setDiscount_price(discountMoney + "");//打折价格，0-表示无
                rq.setTake_partner_num(take_partner_num + "");//带的人数【费用4】
                rq.setSurcharge_for_each_cash(course.getSurcharge_for_each());//每个人的附加费用
                rq.setTotal_session_rate(realMoney + "");     //实际支付课程总价格
                rq.setOriginal_total_session_rate(totalMoney + "");//原课程总价格
                rq.setFirst_joint_fee(first_joint_fee + "");//首次对接费用百分比【如果不是首次时，此处为0】【费用3】
                rq.setSchedule_datas(schedule_datas);
                rq.setCoustomer_name(coustomerNameStr);
                rq.setCoustomer_cellphone(coustomerCellphoneStr);

                showWait(true);
                QuarkApi.HttpRequest(rq, mHandleradd);
            } else {
                startActivity(new Intent().setClass(this, NewLoginActivity.class));
            }
        }
    }

    public boolean checkPay() {
        coustomerNameStr = coustomerName.getText().toString();
        coustomerCellphoneStr = coustomerCellphone.getText().toString();
        if (go_door_status.equals("1")) {
            String check = addressview.getText().toString();
            if (Utils.isEmpty(check)){
                showToast("Enter the location of your lesson");
                return false;
            }
        }
        if (Utils.isEmpty(coustomerNameStr)) {
            showToast("Please input coustomer name");
            return false;
        }
        if (Utils.isEmpty(coustomerCellphoneStr)||coustomerCellphoneStr.length()!=10) {
            showToast("Please input the correct coustomer cellphone");
            return false;
        }

        return true;
    }

    private final AsyncHttpResponseHandler mHandleradd = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, FillOrderActivity.this, AddPayResponse.class);
            if (kd != null) {
                AddPayResponse info = (AddPayResponse) kd;
//                showToast(info.getMessage());
                if (info.getStatus() == 1) {
                    if (order_type.equals("1")) {//order_type);  //1-加入购物车，11-直接购买
                        Intent intent = new Intent("fragmentThree");
                        intent.putExtra("option", "refreshCar");
                        sendBroadcast(intent);
                        finish();
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putString("orders_ids", info.getOrders_id() + "");
                        bundle.putString("realMoney", realMoney + "");
                        bundle.putString("totalMoney", totalMoney + "");
                        startActivityByClass(PaymentActivity.class, bundle);
                        //需要刷新的地方：全部订单 未付款
                        Intent allIntent = new Intent("orders_all");
                        allIntent.putExtra("option", "refresh");
                        sendBroadcast(allIntent);
                        //
                        Intent comIntent = new Intent("orders_waitpay");
                        comIntent.putExtra("option", "refresh");
                        sendBroadcast(comIntent);
//                        finish();
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
    String countNumber_schedule;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != 0) {
            switch (requestCode) {
                case 1001:
                    go_door_city = data.getStringExtra("city");
                    go_door_area = data.getStringExtra("area");
                    go_door_street = data.getStringExtra("street");
                    go_door_address = data.getStringExtra("address");
                    go_door_latitude = data.getStringExtra("latitude");
                    go_door_longitude = data.getStringExtra("longitude");
                    go_door_zipcode = data.getStringExtra("zipcode");
                    TLog.error(go_door_city + " =" + go_door_area + " =" + go_door_street + " =" + go_door_address + " =" + go_door_latitude + " =" + go_door_longitude + " =" + go_door_zipcode);
                    address = go_door_city + go_door_area + go_door_street + go_door_address;
                    addressview.setText(address);
//                    isFulfilTravelDistanceRequest();
                    break;
                case 1002:
                    my_coupon_id = data.getStringExtra("my_coupon_id");
                    String my_coupon_name = data.getStringExtra("my_coupon_name");
                    if (!Utils.isEmpty(my_coupon_name)) {
                        my_coupon_money = Double.valueOf(data.getStringExtra("my_coupon_money"));
                        coupontext.setText(my_coupon_name);
                    } else {
                        my_coupon_money = 0.0;
                        coupontext.setText("None");
                    }


                    calculation();//计算金额
                    break;
                case 1003:
                    schedule_datas = data.getStringExtra("schedule_datas");
                    countNumber_schedule = data.getStringExtra("countNumber");
                    selecttime.setText("Change selected time");
                    break;
            }
        }
    }

    //原价 = 课程数*课程价格 + 额外带人*每人费用*课程数 + 第一次服务费 + 交通费*课程数;
    //实付金额 = 课程数*课程价格 - 最大优惠 + 第一次服务费 + 交通费*课程数 + 额外带人*每人费用*课程数 - 使用优惠卷(如果结果<0 则为0)
    //优惠计算方式  最大优惠 =（课程数*课程价格 + 额外带人*每人费用*课程数） 选择满足条件的最优优惠
    //计算打折的金额 = 课程数*课程价格 + 额外带人*每人费用*课程数
    //需要计算的地方 1，第一次进入，2课程数量变化；3、上门服务 4、带人 5、选择优惠卷
    double totalMoney = 0;
    double realMoney = 0;
    double discountMoney = 0; //打折减少的金额

    public void calculation() {
//课程费用  课程数*课程价格 + 额外带人*每人费用*课程数
        double courseMoney_real = buy_amount * session_rate + take_partner_num * surcharge_for_each_cash * buy_amount;
        double courseMoney_price = buy_amount * session_rate;
        double additional_partner = take_partner_num * surcharge_for_each_cash * buy_amount;//额外带人*每人费用*课程数
        double courseMoney = courseMoney_real;
//        double courseMoneyDiscount = courseMoney;
//        if (discount_type.equals("1")) {  //按总金额 满减
        double discountMoney1 = courseMoney, discountMoney2 = courseMoney, discountMoney3 = courseMoney;

        if ((discount_price_01 != 0) && (buy_amount > 2)) {
            double discount_price_01_str = ((double) (100 - discount_price_01)) / 100.0;
            discountMoney1 = courseMoney * discount_price_01_str;
//            additional_partner = additional_partner * discount_price_01_str;
//            courseMoney_price = courseMoney_price * discount_price_01_str;
        }

        if ((discount_price_02 != 0) && (buy_amount > 4)) {
            double discount_price_02_str = ((double) (100 - discount_price_02)) / 100.0;
            discountMoney2 = courseMoney * discount_price_02_str;
//            additional_partner = additional_partner * discount_price_02_str;
//            courseMoney_price = courseMoney_price * discount_price_02_str;
        }
        if ((discount_price_03 != 0) && (buy_amount > 9)) {
            double discount_price_03_str = ((double) (100 - discount_price_03)) / 100.0;
            discountMoney3 = courseMoney * discount_price_03_str;
//            additional_partner = additional_partner * discount_price_03_str;
//            courseMoney_price = courseMoney_price * discount_price_03_str;
        }

        if (discountMoney1 > discountMoney2) {
            if (discountMoney2 > discountMoney3) {
                courseMoney = discountMoney3;
            } else {
                courseMoney = discountMoney2;
            }
        } else {
            if (discountMoney1 > discountMoney3) {
                courseMoney = discountMoney3;
            } else {
                courseMoney = discountMoney1;
            }
        }

        discountMoney = courseMoney_real - courseMoney;

        if (go_door_status.equals("1")) {  //上门服务
            totalMoney = courseMoney_real + first_joint_fee + go_door_traffic_cost * buy_amount;
        } else {
            totalMoney = courseMoney_real + first_joint_fee;
        }

        realMoney = totalMoney - discountMoney - my_coupon_money;
        if (realMoney > 0) {
        } else {
            realMoney = 0.0;
        }


//        realmoneyView.setText("Total:$" + Utils.DoubleFormat(realMoney));//最终价格
//        totalmoneyView.setText("$" + Utils.DoubleFormat(totalMoney));//原价

        if ((discount_price_01 != 0) && (buy_amount > 2 && buy_amount < 5)) {
            double discount_price_01_str = ((double) (100 - discount_price_01)) / 100.0;
            additional_partner = additional_partner * discount_price_01_str;
            courseMoney_price = courseMoney_price * discount_price_01_str;
        }

        if ((discount_price_02 != 0) && (buy_amount > 4 && buy_amount < 10)) {
            double discount_price_02_str = ((double) (100 - discount_price_02)) / 100.0;
            additional_partner = additional_partner * discount_price_02_str;
            courseMoney_price = courseMoney_price * discount_price_02_str;
        }
        if ((discount_price_03 != 0) && (buy_amount > 9)) {
            double discount_price_03_str = ((double) (100 - discount_price_03)) / 100.0;
            additional_partner = additional_partner * discount_price_03_str;
            courseMoney_price = courseMoney_price * discount_price_03_str;

        }


        DecimalFormat df2 = new DecimalFormat("###.00");
        String Money = df2.format(realMoney);//保留小数点后两位


        DecimalFormat df1 = new DecimalFormat("###.00");
        String additional_partnerDf = df1.format(additional_partner);//保留小数点后两位


        DecimalFormat df3 = new DecimalFormat("###.00");
        String courseMoneyDF = df3.format(courseMoney_price);//保留小数点后两位

        //底部的价格列表显示：
        priceTv.setText("Course price" + " * " + buy_amount);

        if (buy_amount >= 3) {
            priceAgo.setText("$" + session_rate * buy_amount + "");
            priceAgo.getPaint().setAntiAlias(true);// 抗锯齿
            priceAgo.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

            partnerAgo.setText("$" + take_partner_num * surcharge_for_each_cash * buy_amount);
            partnerAgo.getPaint().setAntiAlias(true);// 抗锯齿
            partnerAgo.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        } else {
            priceAgo.setText("");
            partnerAgo.setText("");
        }
        priceAfter.setText("$" + courseMoneyDF);

        oneFeeTv.setText("One times service fee ?");

        feeTv.setText("$" + Utils.DoubleFormat(first_joint_fee));

        partnerTv.setText(take_partner_num + " additional partner * " + buy_amount);
        partnerAfter.setText("$" + additional_partnerDf);
        totalPriceTv.setText("$" + Money);
        if (my_coupon_money>0){ //优惠卷
            couponLay.setVisibility(View.VISIBLE);
            couponTv2.setText("-$"+my_coupon_money);
        }else{
            couponLay.setVisibility(View.GONE);
        }
        if ("1".equals(go_door_status)) {  //上门服务
            travelLay.setVisibility(View.VISIBLE);
            travelTv2.setText("$"+(go_door_traffic_cost * buy_amount));
        }else{
            travelLay.setVisibility(View.GONE);
        }

        if (realMoney != totalMoney) {//价格不相等，原价加中划线
//            totalmoneyView.getPaint().setAntiAlias(true);// 抗锯齿
//            totalmoneyView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
//            totalmoneyView.getPaint().setFlags(0); // 取消设置的的中划线
        }
    }

    //是否在上课范围
    boolean checkTravel = false;

//    public void isFulfilTravelDistanceRequest() {
//        checkTravel = true;
//        IsFulfilTravelDistanceRequest rq = new IsFulfilTravelDistanceRequest();
//        rq.setCourse_id(coure_id);
//        rq.setLatitude(go_door_latitude);
//        rq.setLongitude(go_door_longitude);
//        showWait(true);
//        QuarkApi.HttpRequest(rq, mHandlertotra);
//    }
//
//    private final AsyncHttpResponseHandler mHandlertotra = new AsyncHttpResponseHandler() {
//        @Override
//        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
//            Object kd = ApiResponse.get(arg2, FillOrderActivity.this, IsFulfilTravelDistanceResponse.class);
//            if (kd != null) {
//                IsFulfilTravelDistanceResponse info = (IsFulfilTravelDistanceResponse) kd;
//                if (info.getStatus() != 1) {
//                    go_door_latitude = "";
//                    addressview.setText("");
//                    showToast(info.getMessage());
//                }
//                checkTravel = false;
//            }
//            showWait(false);
//        }
//
//        @Override
//        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
//            AppContext.showToast("Network error" + arg0);
//            showWait(false);
//            checkTravel = false;
//        }
//    };

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
    }

    boolean isShowAddressTip = false;

    @OnClick(R.id.show_address_tip)
    public void show_address_tip(View b) {
        if (isShowAddressTip) {
            isShowAddressTip = false;
            address_tip.setVisibility(View.GONE);
        } else {
            isShowAddressTip = true;
            address_tip.setVisibility(View.VISIBLE);
        }
    }

    boolean isShowServiceTip = false;

    @OnClick(R.id.service_tippic)
    public void show_service_tip(View b) {
        if (isShowServiceTip) {
            isShowServiceTip = false;
            service_tip.setVisibility(View.GONE);
        } else {
            isShowServiceTip = true;
            service_tip.setVisibility(View.VISIBLE);
        }
    }


}
