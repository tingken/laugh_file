package com.quark.skillopedia.uiview.fenlei.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quark.skillopedia.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/6/23 0023.
 * 课程详情 对应的overview片段
 */
public class OverviewFrament extends Fragment {


    @InjectView(R.id.overview_tv)
    TextView overviewTv;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View overView = inflater.inflate(R.layout.overview_fragment_layout, container, false);
        ButterKnife.inject(this, overView);
        overviewTv.setText(getArguments().getString("Overview"));



        return overView;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
