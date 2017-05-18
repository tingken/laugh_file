package com.quark.skillopedia.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.WindowManager;

import com.quark.skillopedia.R;
import com.quark.skillopedia.base.BaseActivity;

import butterknife.ButterKnife;

@SuppressLint("ResourceAsColor")
public class ShearActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.search_layout);
        ButterKnife.inject(this);
        setTopTitle("餐廳搜索");
        setBackButton();

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

}






