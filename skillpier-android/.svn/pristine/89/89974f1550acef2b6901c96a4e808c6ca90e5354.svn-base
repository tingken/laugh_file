package com.quark.skillopedia.uiview.zhanghu.jingli;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.ExperienceInfoRequest;
import com.quark.api.auto.bean.ExperienceInfoResponse;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiHttpClient;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cz.msebera.android.httpclient.Header;

/**
 * 我的经历详情
 * @author pan
 * @time 2016/8/22 0022 下午 5:41
 */
public class MyStepsDetailActivity extends BaseActivity {
    String experience_id;
    @InjectView(R.id.title_tv)
    TextView titleTv;
    @InjectView(R.id.date_tv)
    TextView dateTv;
    @InjectView(R.id.details_tv)
    TextView detailsTv;
    @InjectView(R.id.image_iv)
    ImageView imageIv;
    @InjectView(R.id.left)
    LinearLayout left;
    @InjectView(R.id.image_iv_two)
    ImageView imageIvTwo;
    @InjectView(R.id.image_iv_three)
    ImageView imageIvThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stepsdetail);
        ButterKnife.inject(this);
        setBackButton();
        left.setVisibility(View.VISIBLE);
        experience_id = (String) getValue4Intent("experience_id");
        getData();
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
    }


    public void getData() {
        ExperienceInfoRequest rq = new ExperienceInfoRequest();
        rq.setExperience_id(experience_id);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandler);
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, MyStepsDetailActivity.this, ExperienceInfoResponse.class);
            if (kd != null) {
                ExperienceInfoResponse info = (ExperienceInfoResponse) kd;
                titleTv.setText(info.getExperienceInfoResult().getExperienceInfo().getTitle());
//                dateTv.setText(info.getExperienceInfoResult().getExperienceInfo().getPost_time());
                detailsTv.setText(info.getExperienceInfoResult().getExperienceInfo().getContent());

                //指定时间格式转换
                Date date = null;
                try {
                    date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(info.getExperienceInfoResult().getExperienceInfo().getPost_time());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                // 将date转化为String
                String s = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH).format(date);
                dateTv.setText(s);


                String[] imageStr = info.getExperienceInfoResult().getExperienceInfo().getImages().split("#");
                if (imageStr.length == 1) {
                    ApiHttpClient.loadImage(info.getExperienceInfoResult().getExperienceInfo().getImages(), imageIv);
                }else if (imageStr.length == 2){
                    ApiHttpClient.loadImage(imageStr[0], imageIv);
                    ApiHttpClient.loadImage(imageStr[1], imageIvTwo);
                }else if (imageStr.length == 3){
                    ApiHttpClient.loadImage(imageStr[0], imageIv);
                    ApiHttpClient.loadImage(imageStr[1], imageIvTwo);
                    ApiHttpClient.loadImage(imageStr[2], imageIvThree);
                }
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
}
