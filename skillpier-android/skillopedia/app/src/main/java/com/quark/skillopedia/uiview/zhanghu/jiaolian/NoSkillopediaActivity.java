package com.quark.skillopedia.uiview.zhanghu.jiaolian;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.quark.skillopedia.R;
import com.quark.skillopedia.base.BaseActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 我是教练  重新验证界面
 * @author pan
 * @time 2016/8/22 0022 下午 5:39
 */
public class NoSkillopediaActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {
    String authen_status;
    String authen_num;
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
    @InjectView(R.id.tips)
    TextView tips;
    @InjectView(R.id.new_renzheng_tv)
    TextView new_renzheng_tv;
    @InjectView(R.id.tip2)
    TextView tip2;
    @InjectView(R.id.service_phone)
    TextView service_phone;

    public static NoSkillopediaActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noskillopedia);
        ButterKnife.inject(this);
        instance = this;
        setTopTitle("Teach With Skillpier");
        setBackButton();
        authen_status = (String) getValue4Intent("authen_status");
        authen_num = (String) getValue4Intent("authen_num");

        //0-未认证，1-审核中，2-审核通过，3-审核不通过
        if (authen_status.equals("1")) {
            tips.setText("In the review");
            new_renzheng_tv.setEnabled(false);
            new_renzheng_tv.setBackgroundColor(getResources().getColor(R.color.qianhuise));
            new_renzheng_tv.setTextColor(getResources().getColor(R.color.white));
        }else if (authen_status.equals("3")) {
            tips.setText("We are sorry to inform you that your application is not approved");
            new_renzheng_tv.setBackgroundColor(getResources().getColor(R.color.luse));
            new_renzheng_tv.setTextColor(getResources().getColor(R.color.white));
        }

        //认证次数为0
        if (authen_num.equals("0")) {
            new_renzheng_tv.setEnabled(false);
            new_renzheng_tv.setBackgroundColor(getResources().getColor(R.color.qianhuise));
            new_renzheng_tv.setTextColor(getResources().getColor(R.color.white));
        }
        tip2.setText("You have " + authen_num + " times to reapply, If you have any question, lease contact us");
    }

    @OnClick(R.id.new_renzheng_tv)
    public void nrt(View b) {
        Intent i6 = new Intent(this, AuthenActivity.class);
        startActivity(i6);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.service_phone)
    public void callser(View b) {
        startByPermissions();
    }

    public void call() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + "400300300");
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
