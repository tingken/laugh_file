package com.quark.skillopedia.uiview.zhanghu.jiaolian;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.OrderInfoRequest;
import com.quark.api.auto.bean.OrderInfoResponse;
import com.quark.api.auto.bean.OrdersSchedules;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.AppParam;
import com.quark.skillopedia.Googlemap.ShowPositionActivity;
import com.quark.skillopedia.R;
import com.quark.skillopedia.adapter.ScheduleListAdapter;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.ui.widget.ListViewForScrollView;
import com.quark.skillopedia.ui.widget.ShowChooseDialog;
import com.quark.skillopedia.uiview.zhanghu.zhuye.PersonHomeActivity;
import com.quark.skillopedia.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cz.msebera.android.httpclient.Header;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Administrator on 2016/5/25 0025.
 * 管理订单详情
 */
public class DetailsorderActivity extends BaseActivity implements PlatformActionListener, Handler.Callback,EasyPermissions.PermissionCallbacks {

    @InjectView(R.id.main_left)
    LinearLayout mainLeft;
    @InjectView(R.id.yang_ly)
    LinearLayout yangLy;
    String orders_id;
    @InjectView(R.id.price_tv)
    TextView priceTv;
    @InjectView(R.id.cost_tv)
    TextView costTv;
    @InjectView(R.id.amount_tv)
    TextView amountTv;
    @InjectView(R.id.discount_tv)
    TextView discountTv;
    @InjectView(R.id.total_tv)
    TextView totalTv;
    @InjectView(R.id.fee_tv)
    TextView feeTv;
    @InjectView(R.id.fee_ly)
    LinearLayout feeLy;
    @InjectView(R.id.coupon_tv)
    TextView couponTv;
    @InjectView(R.id.coupon_ly)
    LinearLayout couponLy;
//    @InjectView(R.id.booking_bt)
//    Button bookingBt;
    @InjectView(R.id.addpartner_tv)
    TextView addpartnerTv;
//    @InjectView(R.id.partner_tv)
//    TextView partnerTv;
//    @InjectView(R.id.iamge_iv)
//    SelectableRoundedImageView iamgeIv;
//    @InjectView(R.id.text1)
//    TextView text1;
//    @InjectView(R.id.text3)
//    TextView text3;
//    @InjectView(R.id.ratingBar)
//    RatingBar ratingBar;
//    @InjectView(R.id.grade_tv)
//    TextView gradeTv;
//    @InjectView(R.id.text4)
//    TextView text4;
//    @InjectView(R.id.miles_tv)
//    TextView milesTv;
//    @InjectView(R.id.football_tv)
//    TextView footballTv;
//    @InjectView(R.id.money_tv)
//    TextView moneyTv;

    @InjectView(R.id.id_number)
    TextView idNumber;
    @InjectView(R.id.order_number)
    TextView orderNumber;
    @InjectView(R.id.date_tv)
    TextView dateTv;
    @InjectView(R.id.nochoosecourse)
    TextView nochoosecourse;
    @InjectView(R.id.scrolistview)
    ListViewForScrollView scrolistview;

    ArrayList<OrdersSchedules> datas;
    ScheduleListAdapter adapter;
    OrderInfoResponse info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsorder);
        ButterKnife.inject(this);
        setTopTitle("Details of order");
        orders_id = (String) getValue4Intent("orders_id");
        datas = new ArrayList<>();
        orderInfoRequest();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.main_left, R.id.yang_ly})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_left:
                finish();
                break;
            case R.id.yang_ly:
                Intent intent = new Intent(DetailsorderActivity.this, PersonHomeActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void orderInfoRequest() {
        OrderInfoRequest rq = new OrderInfoRequest();
        rq.setOrders_id(orders_id);
        rq.setLatitude(new AppParam().getLat(this));
        rq.setLongitude(new AppParam().getLng(this));
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlerinfo);
    }

    private final AsyncHttpResponseHandler mHandlerinfo = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, DetailsorderActivity.this, OrderInfoResponse.class);
            if (kd != null) {
                info = (OrderInfoResponse) kd;
                priceTv.setText("$" + info.getOrders().getSession_rate());
                amountTv.setText("x" + info.getOrders().getBuy_amount() + "");
                costTv.setText("$" + info.getOrders().getGo_door_traffic_cost());
                discountTv.setText("-$" + info.getOrders().getDiscount_price());
                totalTv.setText("$" + info.getOrders().getTotal_session_rate());
                addpartnerTv.setText("Additional partners (x" + info.getOrders().getTake_partner_num() + ")");
                idNumber.setText(info.getOrders().getCourse_id()+"");

//                Date date = null;
//                try {
//                    date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(info.getOrders().getPost_time());
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
                // 将date转化为String
//                String s = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH).format(date);
//                String hh = new SimpleDateFormat("HH:mm:ss").format(date);
                dateTv.setText(info.getOrders().getPost_time());
                orderNumber.setText(info.getOrders().getOrder_number());
//                try {
//                    float allAartnerty = Float.parseFloat(info.getOrders().getSurcharge_for_each_cash()) * (info.getOrders().getTake_partner_num());
//                    partnerTv.setText("$" + allAartnerty);
//                } catch (Exception e) {
//                    Log.e("error", "detailsorderactivity 转换异常");
//                }
//
//                if (info.getOrders().getBooking_status() == 1) {
//                    bookingBt.setVisibility(View.VISIBLE);
//                } else if (info.getOrders().getBooking_status() == 2) {
//                    bookingBt.setVisibility(View.GONE);
//                }
                if (info.getOrders().getFirst_joint_fee() != "0.0") {
                    feeLy.setVisibility(View.VISIBLE);
                    feeTv.setText("$" + info.getOrders().getFirst_joint_fee() + "");
                }
                if (info.getOrders().getMy_coupon_money() != "0.0") {
                    couponLy.setVisibility(View.VISIBLE);
                    couponTv.setText("$" + info.getOrders().getMy_coupon_money() + "");
                }

//                ApiHttpClient.loadImage(info.getOrders().getCourse().getCourse_image_01(), iamgeIv, R.drawable.example_7);
//                text1.setText(info.getOrders().getCourse().getTitle());
//                text3.setText("BY " + info.getOrders().getCourse().getCourse_nickname());
////                gradeTv.setText(info.getOrders().getCourse().getTotal_score());
//                //评分 强转
//                try {
//                    float grade = Float.parseFloat(info.getOrders().getCourse().getTotal_score());
//                    ratingBar.setRating(grade);
//
//                    int gradeint = (int)grade;
//                    gradeTv.setText(gradeint + " review");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                milesTv.setText(info.getOrders().getCourse().getDistance() + "");
//                moneyTv.setText("¥" + info.getOrders().getTotal_session_rate());
//                text4.setVisibility(View.VISIBLE);
//                text4.setText("x" + info.getOrders().getBuy_amount());
//                footballTv.setText(info.getOrders().getCourse().getCategory_02_name());

                List<OrdersSchedules> data = info.getOrders().getOrdersSchedules();

                if (data != null && data.size() > 0) {
                    datas.addAll(data);
                    adapter = new ScheduleListAdapter(DetailsorderActivity.this, datas);
                    scrolistview.setAdapter(adapter);
                }

//                nochoosecourse.setText("You still have "+info.getOrders().getCourse().getHasnone_booking_course() + " sessions to book!");

//                String one = "You still have ";
                String two = info.getOrders().getCourse().getHasnone_booking_course();
//                String three = " sessions to book!";
//                String str = one+two+three;
//                final SpannableStringBuilder sp = new  SpannableStringBuilder(str);
//                sp.setSpan(new ForegroundColorSpan(ContextCompat.getColor(DetailsorderActivity.this, R.color.text)),one.length(),(one.length()+two.length()), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                sp.setSpan(new AbsoluteSizeSpan(16, true),one.length(),(one.length()+two.length()), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                nochoosecourse.setText(two+" Sessions to confirm");
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

    @OnClick({R.id.tolocation, R.id.tocall})
    public void cll(View b) {
        switch (b.getId()) {
            case R.id.tolocation:
                Bundle bundle = new Bundle();
                bundle.putString("longitude", info.getOrders().getCourse().getLongitude());
                bundle.putString("latitude", info.getOrders().getCourse().getLatitude());
                bundle.putString("address",info.getOrders().getCourse().getAddress());
                startActivityByClass(ShowPositionActivity.class, bundle);
                break;
            case R.id.tocall:
                if (!Utils.isEmpty(info.getOrders().getCourse().getCourse_email())&&!Utils.isEmpty(info.getOrders().getCourse().getCourse_telephone())){
                    showEmailOrPhone();
                }else{
                    if (!Utils.isEmpty(info.getOrders().getCourse().getCourse_email())) {
                        sendEmail();
                    } else if(!Utils.isEmpty(info.getOrders().getCourse().getCourse_telephone())) {
                        startByPermissions();
                    }else{
                        showToast("No contact phone or Email");
                    }
                }
                break;
//            case R.id.booking_bt:
//                Bundle bundle2 = new Bundle();
//                bundle2.putString("from", "order");
//                bundle2.putString("coure_id", info.getOrders().getCourse().getCourse_id() + "");
//                bundle2.putString("orders_id", info.getOrders().getOrders_id() + "");
//                bundle2.putString("countNumber", info.getOrders().getCourse().getHasnone_booking_course() + "");
//                bundle2.putString("coureTitle", info.getOrders().getCourse().getTitle() + "");
//                //课程时间表打开时应该显示第一个可以预定的日期
//                bundle2.putString("freeCourseDay", info.getOrders().getCourse().getFreeCourseDay());
////                startActivityByClass(ScheduleBookingActivity.class, bundle2);
//                Intent intent = new Intent(this, ScheduleBookingActivity.class);
//                intent.putExtras(bundle2);
//                startActivityForResult(intent, 122);
//                break;
        }
    }

    public void sendEmail(){
        Intent data=new Intent(Intent.ACTION_SENDTO);
        data.setData(Uri.parse("mailto:"+info.getOrders().getCourse().getCourse_email()));
        data.putExtra(Intent.EXTRA_SUBJECT, info.getOrders().getCourse().getTitle());
        data.putExtra(Intent.EXTRA_TEXT, "");
        startActivity(data);
    }

    public void call() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + info.getOrders().getCourse().getCourse_telephone());
        intent.setData(data);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }

    private void startByPermissions() {
        String[] perms = {Manifest.permission.CALL_PHONE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            call();
        } else {
            EasyPermissions.requestPermissions(this, "请求获取拨打电话权限", 10001, perms);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        call();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Toast.makeText(this, "授权不正确,操作无法进行", Toast.LENGTH_LONG).show();
    }


    public void showEmailOrPhone(){
        ShowChooseDialog.showSheetPic(this, handlerphoto);
    }

    private Handler handlerphoto = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    sendEmail();
                    break;
                case 2:
                    startByPermissions();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        Message msg = new Message();
        msg.what = 0;
        handlerfx.sendMessage(msg);
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        Message msg = new Message();
        msg.what = 1;
        handlerfx.sendMessage(msg);
    }

    @Override
    public void onCancel(Platform platform, int i) {
        Message msg = new Message();
        msg.what = 2;
    }

    private Handler handlerfx = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
//                    Toast.makeText(XingquedianPicActivity.this, "分享成功", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
//                    Toast.makeText(XingquedianPicActivity.this, "分享失败", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
//                    Toast.makeText(XingquedianPicActivity.this, "取消分享", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }

        ;
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != 0) {
            String nochooseCourseNumber = data.getStringExtra("countNumber");
            info.getOrders().getCourse().setHasnone_booking_course(nochooseCourseNumber);
            nochoosecourse.setText(nochooseCourseNumber + " Sessons to comfirm");
            if (nochooseCourseNumber.equals("0")) {    //选满了
//                bookingBt.setVisibility(View.GONE);
            }
        }
    }
}
