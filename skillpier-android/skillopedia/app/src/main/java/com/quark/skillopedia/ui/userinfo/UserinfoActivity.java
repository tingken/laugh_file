package com.quark.skillopedia.ui.userinfo;

import android.os.Bundle;
import android.view.Window;

import com.quark.skillopedia.R;
import com.quark.skillopedia.base.BaseActivity;

import butterknife.ButterKnife;

public class UserinfoActivity extends BaseActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.setting_user_info);
        ButterKnife.inject(this);
        setTopTitle("个人信息");
        ButterKnife.inject(this);
        setBackButton();
//        user = (UserInfo) getValue4Intent(UserinfoActivity.USERINFO);

    }


    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}



