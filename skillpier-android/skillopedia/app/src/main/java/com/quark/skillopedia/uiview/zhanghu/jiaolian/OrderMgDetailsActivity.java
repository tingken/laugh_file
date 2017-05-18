package com.quark.skillopedia.uiview.zhanghu.jiaolian;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.joooonho.SelectableRoundedImageView;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.CoachOrderInfoRequest;
import com.quark.api.auto.bean.CoachOrderInfoResponse;
import com.quark.api.auto.bean.OrdersSchedules;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.AppParam;
import com.quark.skillopedia.Googlemap.ShowPositionActivity;
import com.quark.skillopedia.R;
import com.quark.skillopedia.adapter.ScheduleListAdapter;
import com.quark.skillopedia.api.ApiHttpClient;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.ui.widget.ListViewForScrollView;
import com.quark.skillopedia.uiview.zhanghu.zhuye.PersonHomeActivity;
import com.quark.skillopedia.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by pan on 2016/7/11 0011.
 * >#
 * >#管理订单详情 教练用
 */
public class OrderMgDetailsActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    @InjectView(R.id.left)
    LinearLayout left;
    @InjectView(R.id.head_iv)
    SelectableRoundedImageView headIv;
    @InjectView(R.id.yang_ly)
    LinearLayout yangLy;
    @InjectView(R.id.price_tv)
    TextView priceTv;
    @InjectView(R.id.addpartner_tv)
    TextView addpartnerTv;
    @InjectView(R.id.partner_tv)
    TextView partnerTv;
    @InjectView(R.id.cost_tv)
    TextView costTv;
    @InjectView(R.id.amount_tv)
    TextView amountTv;
    @InjectView(R.id.discount_tv)
    TextView discountTv;
    @InjectView(R.id.fee_tv)
    TextView feeTv;
    @InjectView(R.id.fee_ly)
    LinearLayout feeLy;
    @InjectView(R.id.coupon_tv)
    TextView couponTv;
    @InjectView(R.id.coupon_ly)
    LinearLayout couponLy;
    @InjectView(R.id.total_tv)
    TextView totalTv;
    @InjectView(R.id.scrolistview)
    ListViewForScrollView scrolistview;
    @InjectView(R.id.id_number)
    TextView idNumber;
    @InjectView(R.id.date_tv)
    TextView dateTv;
    @InjectView(R.id.order_number)
    TextView orderNumber;
//    @InjectView(R.id.iamge_iv)
//    SelectableRoundedImageView iamgeIv;
//    @InjectView(R.id.text1)
//    TextView text1;
//    @InjectView(R.id.text2)
//    TextView text2;
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

    @InjectView(R.id.name_tv)
    TextView nameTv;

    @InjectView(R.id.cancel_bt)
    Button cancelBt;
    @InjectView(R.id.confirm_bt)
    Button confirmBt;
    @InjectView(R.id.finish_bt)
    Button finishBt;
    @InjectView(R.id.delete_bt)
    Button deleteBt;

    @InjectView(R.id.teaching_bt)
    Button teachingBt;
    @InjectView(R.id.get_bt)
    Button getBt;
    ArrayList<OrdersSchedules> datas;
    ScheduleListAdapter adapter;
    @InjectView(R.id.msg_tv)
    TextView msgTv;

    CoachOrderInfoResponse info;
    String orders_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordermgdetails);
        ButterKnife.inject(this);
        left.setVisibility(View.VISIBLE);
        setBackButton();
        setTopTitle("Details of order");
        orders_id = (String) getValue4Intent("orders_id");
        datas = new ArrayList<>();
        coachOrderInfoRequest();

        getBt.setText("Contact Student");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    String phoneStr;

    @OnClick({R.id.yang_ly, R.id.teaching_bt, R.id.get_bt, R.id.cancel_bt, R.id.confirm_bt, R.id.finish_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.yang_ly:
                Intent intent = new Intent();
                intent.putExtra("userid", info.getOrders().getUser_id() + "");
                Bundle bundle3 = new Bundle();
                bundle3.putBoolean("isCoach",false);
                intent.putExtras(bundle3);
                intent.setClass(this, PersonHomeActivity.class);
                startActivity(intent);
                break;
            case R.id.teaching_bt:
                Bundle bundle = new Bundle();
                bundle.putString("longitude", info.getOrders().getCourse().getLongitude());
                bundle.putString("latitude", info.getOrders().getCourse().getLatitude());
                bundle.putString("address",info.getOrders().getCourse().getAddress());
                startActivityByClass(ShowPositionActivity.class, bundle);

                break;
            case R.id.get_bt:
                if (!Utils.isEmpty(info.getOrders().getCourse().getBuy_email())) {
//                    sendEmail();
//                    Intent data=new Intent(Intent.ACTION_SENDTO);
//                    data.setData(Uri.parse("mailto:"+info.getOrders().getCourse().getBuy_email()));
//                    data.putExtra(Intent.EXTRA_SUBJECT, info.getOrders().getCourse().getTitle());
//                    data.putExtra(Intent.EXTRA_TEXT, "");
//                    startActivity(data);
                    phoneStr = info.getOrders().getPhone_number();
                    startByPermissions();
                } else {
                    showToast("There is no contact phone");
                }
                break;
            case R.id.cancel_bt:
//                startActivityByClass();

                break;
            case R.id.confirm_bt:

                break;
            case R.id.finish_bt:

                break;
        }
    }


//    private void startByPermissions() {
//        String[] perms = {Manifest.permission.CALL_PHONE};
//        if (EasyPermissions.hasPermissions(this, perms)) {
//            call();
//        } else {
//            EasyPermissions.requestPermissions(this, "请求获取拨打电话权限", 10001, perms);
//        }
//    }
//
//    public void call() {
//        Intent intent = new Intent(Intent.ACTION_CALL);
//        Uri data = Uri.parse("tel:" + info.getOrders().getCourse().getBuy_telephone());
//        intent.setData(data);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        startActivity(intent);
//    }
//
//    @Override
//    public void onPermissionsGranted(int requestCode, List<String> perms) {
//        call();
//    }
//
//    @Override
//    public void onPermissionsDenied(int requestCode, List<String> perms) {
//        Toast.makeText(this, "授权不正确,操作无法进行", Toast.LENGTH_LONG).show();
//    }

    public void coachOrderInfoRequest() {
        CoachOrderInfoRequest rq = new CoachOrderInfoRequest();
        rq.setOrders_id(orders_id);
        rq.setLatitude(new AppParam().getLat(this));
        rq.setLongitude(new AppParam().getLng(this));
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlercoa);
    }

    private final AsyncHttpResponseHandler mHandlercoa = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, OrderMgDetailsActivity.this, CoachOrderInfoResponse.class);
            if (kd != null) {
                info = (CoachOrderInfoResponse) kd;
                ApiHttpClient.loadImage(info.getOrders().getCourse().getBuy_image_01(), headIv, R.drawable.example_7);
                nameTv.setText(info.getOrders().getCourse().getBuy_nickname());
                priceTv.setText("$" + info.getOrders().getSession_rate());
                amountTv.setText("x" + info.getOrders().getBuy_amount() + "");
                costTv.setText("$" + info.getOrders().getGo_door_traffic_cost());
                discountTv.setText("-$" + info.getOrders().getDiscount_price());
                totalTv.setText("$" + info.getOrders().getOriginal_total_session_rate());
                addpartnerTv.setText("Additional partners (x" + info.getOrders().getTake_partner_num() + ")");
                idNumber.setText(info.getOrders().getCourse_id()+"");
                dateTv.setText(info.getOrders().getPost_time());

                try {
                    float allAartnerty = Float.parseFloat(info.getOrders().getSurcharge_for_each_cash()) * (info.getOrders().getTake_partner_num());
                    partnerTv.setText("$" + allAartnerty);
                } catch (Exception e) {
                    Log.e("error", "ordermgdetailsactivity 转换异常");
                }

                if (info.getOrders().getFirst_joint_fee() != "0.0") {
                    feeLy.setVisibility(View.VISIBLE);
                    feeTv.setText("$" + info.getOrders().getFirst_joint_fee() + "");
                }
                if (info.getOrders().getMy_coupon_money() != "0.0") {
                    couponLy.setVisibility(View.VISIBLE);
                    couponTv.setText("$" + info.getOrders().getMy_coupon_money() + "");
                }

//                ApiHttpClient.loadImage(info.getOrders().getCourse().getCourse_image_01(), headIv, R.drawable.example_7);
//                text1.setText(info.getOrders().getCourse().getTitle());
//                text3.setText("BY" + info.getOrders().getCourse().getCourse_nickname());

                //评分 强转
//                try {
//                    float grade = Float.parseFloat(info.getOrders().getCourse().getTotal_score());
//                    ratingBar.setRating(grade);
//
//                    int gradeint = (int)grade;
////                    gradeTv.setText(gradeint + " review");
//                    gradeTv.setText(info.getOrders().getCourse().getTotal_coment_num()  + " review");
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
                    adapter = new ScheduleListAdapter(OrderMgDetailsActivity.this, datas);
                    scrolistview.setAdapter(adapter);
                }
                String leave_messageStr = info.getOrders().getLeave_message();
                if (!Utils.isEmpty(leave_messageStr))
                    msgTv.setText(leave_messageStr);

                if (info.getOrders().getButton_status1() == 1) {
                    cancelBt.setVisibility(View.VISIBLE);
                }
                if (info.getOrders().getButton_status2() == 1) {
                    confirmBt.setVisibility(View.VISIBLE);
                }
                if (info.getOrders().getButton_status3() == 1) {
                    finishBt.setVisibility(View.VISIBLE);
                }
                if (info.getOrders().getButton_status4() == 1) {
                    deleteBt.setVisibility(View.VISIBLE);
                }
                orderNumber.setText(info.getOrders().getOrder_number());
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

    public void call() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneStr);
        intent.setData(data);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //   int[] grantResults)
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

}
