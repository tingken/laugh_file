package com.quark.skillopedia.uiview.fenlei.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quark.skillopedia.R;
import com.quark.skillopedia.util.Utils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/6/23 0023.
 * 课程详情 对应的overview片段
 */
public class AchievemensFrament extends Fragment {
    @InjectView(R.id.overview_tv)
    TextView overviewTv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View overView = inflater.inflate(R.layout.overview_fragment_layout, container, false);
        ButterKnife.inject(this, overView);
        String achievements = getArguments().getString("achievements");
        if (Utils.isEmpty(achievements)){
            overviewTv.setText("Temporarily no data");
            overviewTv.setGravity(Gravity.CENTER);
        }else{
            overviewTv.setText(achievements);
        }

        return overView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
