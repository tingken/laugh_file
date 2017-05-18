package com.quark.skillopedia.uiview.zhanghu.zhuye;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.quark.skillopedia.R;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.uiview.ForgotActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by pan on 2016/7/6 0006.
 * >#
 * >#改名和修改密码界面
 */
public class AccountSettingActivity extends BaseActivity {


    @InjectView(R.id.left)
    LinearLayout left;


    String name;
    String type;
    @InjectView(R.id.edit_name_ly)
    LinearLayout editNameLy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountsetting);
        ButterKnife.inject(this);
        setTopTitle("Account Setting");
        setBackButton();
        left.setVisibility(View.VISIBLE);
        name = (String) getValue4Intent("name");
        type = (String) getValue4Intent("type");
        if (type.equals("2")) {
            editNameLy.setVisibility(View.GONE);
        }
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.edit_name_ly, R.id.edit_pwd_ly})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_name_ly:
                Intent in1 = new Intent(this, RenameActivity.class);
                in1.putExtra("name", name);
                startActivityForResult(in1, 100);

                break;
            case R.id.edit_pwd_ly:
                startActivityByClass(ForgotActivity.class);
                break;
        }
    }
}
