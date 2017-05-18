package com.quark.skillopedia.uiview.zhanghu.jiaolian;

import android.annotation.SuppressLint;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.CoachOrdersScheduleListARequest;
import com.quark.api.auto.bean.CoachOrdersScheduleListAResponse;
import com.quark.api.auto.bean.ListScheduleBean;
import com.quark.api.auto.bean.ScheduleDatas;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.AppParam;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseFragementActivity;
import com.quark.skillopedia.util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cz.msebera.android.httpclient.Header;

/**
 * 教练 确认时间
 * @author leon
 * @time 2016/7/7 0007 下午 3:54
 */
public class ScheduleConfirmTimeActivity extends BaseFragementActivity implements RadioGroup.OnCheckedChangeListener {

    String timestr;
    List<ListScheduleBean> datas;

    String orders_id;
    String schedule_type;   //1-cancel，2-confirm，3-finish
    List<ScheduleDatas> scheduleDatas;

    WindowManager wm;
    MyPagerAdapter adapter;

    int type = 1;
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
    @InjectView(R.id.radioGroup)
    RadioGroup radioGroup;
    @InjectView(R.id.horizontalScrollView)
    HorizontalScrollView horizontalScrollView;
    @InjectView(R.id.pager)
    ViewPager mViewPager;
    @InjectView(R.id.dataview)
    LinearLayout dataview;
    @InjectView(R.id.img1)
    ImageView mImageView;

    private float mCurrentCheckedRadioLeft;
    private HorizontalScrollView mHorizontalScrollView;
    private ArrayList<View> mViews;
    LocalActivityManager manager = null;
    private RadioGroup mRadioGroup;
    ArrayList<RadioButton> menus;

    int sw;
    int currentPosition = 0;
    int perwidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmtime_schedule);
        ButterKnife.inject(this);
        setTopTitle("Booking");
        setBackButton();
        orders_id = (String) getValue4Intent("orders_id");
        schedule_type = (String) getValue4Intent("schedule_type");

        sw = new AppParam().getScreenWidth(this);
        perwidth = sw/2;

        ViewGroup.LayoutParams params = mImageView.getLayoutParams();
        params.width = sw/2;

        mImageView.setLayoutParams(params);

        manager = new LocalActivityManager(this, true);
        manager.dispatchCreate(savedInstanceState);

        menus = new ArrayList<RadioButton>();
        scheduleDatas = new ArrayList<>();
        mViews = new ArrayList<>();
        iniController();
        coachOrdersScheduleListARequest();
    }

    public void coachOrdersScheduleListARequest() {
        CoachOrdersScheduleListARequest rq = new CoachOrdersScheduleListARequest();
        rq.setOrders_id(orders_id);
        rq.setSchedule_type(schedule_type);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandler);
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, ScheduleConfirmTimeActivity.this, CoachOrdersScheduleListAResponse.class);
            if (kd != null) {
                CoachOrdersScheduleListAResponse info = (CoachOrdersScheduleListAResponse) kd;
                if (info.getStatus() == 1) {
                    scheduleDatas.clear();
                    scheduleDatas.addAll(info.getScheduleDatas());
                    if (scheduleDatas != null && scheduleDatas.size() > 0) {
                        iniVariable();
                    }
                } else {
                    showToast(info.getMessage());
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

    private void iniVariable() {
        for (int i = 0; i < scheduleDatas.size(); i++) {
            RadioButton r = new RadioButton(this);
            r.setTextColor(getResources().getColor(R.color.huise));
            r.setText(scheduleDatas.get(i).getSchedule_data());
            r.setTextSize(16);
            r.setHeight(Utils.dip2px(this, 30));
            r.setWidth(sw / 2);
            r.setGravity(Gravity.CENTER);
            r.setId(i + 10);
            r.setButtonDrawable(android.R.color.transparent);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(Utils.dip2px(this, 10), 0, Utils.dip2px(this, 10), 0);
            r.setLayoutParams(layoutParams);

            menus.add(r);
            mRadioGroup.addView(r);
        }

        mViews.clear();
        mViewPager.removeAllViews();

        for (int i = 0; i < scheduleDatas.size(); i++) {
            Random random = new Random();// 定义随机类
            int result = random.nextInt(100);

            Intent intent2 = new Intent(this, ScheduleOrderActivity.class);
            intent2.putExtra("schedule_data", scheduleDatas.get(i).getSchedule_data() + "");
            intent2.putExtra("orders_id", orders_id);
            intent2.putExtra("schedule_type", schedule_type);

            mViews.add(getView("1" + result, intent2));
        }
        adapter = new MyPagerAdapter();
        mViewPager.setAdapter(adapter); // 设置ViewPager的适配器

        //默认选中第一个
        menus.get(0).performClick();
    }

    private View getView(String id, Intent intent) {
        return manager.startActivity(id, intent).getDecorView();
    }

    private void iniController() {
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        mHorizontalScrollView = (HorizontalScrollView) findViewById(R.id.horizontalScrollView);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mRadioGroup.setOnCheckedChangeListener(this);
        mViewPager.setOnPageChangeListener(new MyPagerOnPageChangeListener());
    }

    private class MyPagerOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        /**
         * 滑动ViewPager的时候,让上方的HorizontalScrollView自动切换
         */
        @Override
        public void onPageSelected(int position) {
            Log.e("onPageSelected", "onPageSelected");
            currentPosition = position;
            for (int i = 0; i < menus.size(); i++) {
                if ((position) == i) {
                    menus.get(position).performClick();
                }
            }
        }
    }

	@SuppressLint("ResourceAsColor")
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		AnimationSet _AnimationSet = new AnimationSet(true);
		TranslateAnimation _TranslateAnimation;
        for (int i = 0; i < menus.size(); i++) {
            if (checkedId == menus.get(i).getId()) {
                currentPosition = i;
                mCurrentCheckedRadioLeft = getCurrentCheckedRadioLeft();

                _TranslateAnimation = new TranslateAnimation(mCurrentCheckedRadioLeft, perwidth*i, 0f, 0f);
                _AnimationSet.addAnimation(_TranslateAnimation);
                _AnimationSet.setFillAfter(true);
                _AnimationSet.setDuration(100);
                setTextColorBtn(menus.get(i).getId());
                mImageView.startAnimation(_AnimationSet);
                mViewPager.setCurrentItem(i);
            }
        }

		mHorizontalScrollView.smoothScrollTo((int)mCurrentCheckedRadioLeft, 0);
	}

    private void setTextColorBtn(int checkedId){
        for (int i = 0; i < menus.size(); i++) {
            if (checkedId == menus.get(i).getId()) {
                menus.get(i).setTextColor(getResources().getColor(R.color.chengse));
            }else{
                menus.get(i).setTextColor(getResources().getColor(R.color.huise));
            }
        }
    }

    /**
     * 获得当前被选中的RadioButton距离左侧的距离
     */
    private float getCurrentCheckedRadioLeft() {

        return  sw/2*currentPosition;
    }

    /**
     * ViewPager的适配器
     *
     */
    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public void destroyItem(View v, int position, Object obj) {
            try {
                ((ViewPager) v).removeView(mViews.get(position));
            } catch (Exception e) {
                Log.e("erroe", e.getMessage() + "销毁报错");
            }
        }

        @Override
        public void finishUpdate(View arg0) {
            // TODO Auto-generated method stub
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            int ub = mViews.size();
            return mViews.size();
        }

        @Override
        public Object instantiateItem(View v, int position) {
            ((ViewPager) v).addView(mViews.get(position));

            return mViews.get(position);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
            // TODO Auto-generated method stub

        }

        @Override
        public Parcelable saveState() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
            // TODO Auto-generated method stub

        }
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
    }

}
