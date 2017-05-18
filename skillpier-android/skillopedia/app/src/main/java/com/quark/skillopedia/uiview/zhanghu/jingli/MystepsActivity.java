package com.quark.skillopedia.uiview.zhanghu.jingli;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.DeleteExperienceRequest;
import com.quark.api.auto.bean.DeleteExperienceResponse;
import com.quark.api.auto.bean.ExperienceListRequest;
import com.quark.api.auto.bean.ExperienceListResponse;
import com.quark.api.auto.bean.ListExperience;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.R;
import com.quark.skillopedia.adapter.ExperienceEditAdapter;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import me.maxwin.view.XListView;

/**
 * 我的经历
 *
 * @author pan
 * @time 2016/8/22 0022 下午 5:41
 */
public class MystepsActivity extends BaseActivity implements XListView.IXListViewListener{
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
    @InjectView(R.id.xlstv)
    XListView list;
    @InjectView(R.id.kong_ly)
    LinearLayout kongLy;
//    @InjectView(R.id.pager_tabs)
//    PagerSlidingTabStrip pagerTabs;
//    @InjectView(R.id.pager)
//    NoScrollViewPager pager;

    //    private PagerSlidingTabStrip pagerTab;
//    public final int NUM_PAGES = 2;
//
//    PagerAdapter pageadapter;

    private ArrayList<ListExperience> datas;
    ExperienceEditAdapter adapter;
    int current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysteps);
        ButterKnife.inject(this);
        left.setVisibility(View.VISIBLE);

        title.setText("My steps");
        right.setVisibility(View.VISIBLE);
        sign.setTextColor(Color.GREEN);
        sign.setText("Create");
        rightrig.setImageDrawable(getResources().getDrawable(R.drawable.write));
//        pagerTab = (PagerSlidingTabStrip) findViewById(R.id.pager_tabs);
//        registerBoradcastReceiverJP();
//
//        pageadapter = new PagerAdapter(getSupportFragmentManager());
//        pager.setAdapter(pageadapter);
//        pagerTab.setViewPager(pager);
//        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                currentPosition = position;
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

        myinitlist();
        getData();

    }

    public void myinitlist() {
        datas = new ArrayList<>();
        list.setFooterDividersEnabled(false);
        // 设置xlistview可以加载、刷新
        list.setPullLoadEnable(true);//购物车一次加载完 没有加载更多
        list.setPullRefreshEnable(true);
        list.setXListViewListener(this);
        list.setOnItemClickListener(listListener);
        adapter = new ExperienceEditAdapter(this, datas,handler);
        list.setAdapter(adapter);
        Message msg = handler.obtainMessage();
        msg.what = 1;
        msg.arg1 = datas.size();
        handler.sendMessage(msg);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                position = position-1;
                if (position<datas.size()){
                    Intent intent = new Intent(MystepsActivity.this,MyStepsDetailActivity.class);
                    intent.putExtra("experience_id",datas.get(position).getExperience_id()+"");
                    startActivity(intent);
                }
            }
        });
    }

    AdapterView.OnItemClickListener listListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            position = position - 1;
            if (position < datas.size()) {
//                current = position;
//                Bundle bundle = new Bundle();
//                bundle.putString("from", "mydata");
//                Intent intent = new Intent().setClass(MyDatasActivity.this, ShowPicActivity.class);
//                intent.putExtras(bundle);
//                startActivity(intent);
            }
        }
    };

    public void getData() {
        showWait(true);
        ExperienceListRequest rq = new ExperienceListRequest();
//        rq.setStatus(status);
        QuarkApi.HttpRequest(rq, mHandler);
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, MystepsActivity.this, ExperienceListResponse.class);
            if (kd != null) {
                ExperienceListResponse info = (ExperienceListResponse) kd;
                dealData(info);
            }
            showWait(false);
        }

        @Override
        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
            AppContext.showToast("Network error" + arg0);
            showWait(false);
        }
    };

    public void dealData(ExperienceListResponse info) {
        if (type == 1) {
            datas.clear();
        }
        List<ListExperience> tin = info.getExperienceListResult().getExperiences();
        Message msg = handler.obtainMessage();
        msg.what = type;
        if (tin != null && tin.size() > 0) {
            datas.addAll(tin);
            msg.arg1 = tin.size();
        } else {
            msg.arg1 = 0;
        }
        handler.sendMessage(msg);
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int pdatasize = msg.arg1;
            list.isnomore(pdatasize < 10);
            switch (msg.what) {
                case 1:
                    list.stopRefresh();
                    adapter.notifyDataSetChanged();
                    break;
                case 2:
                    list.stopLoadMore();
                    adapter.notifyDataSetChanged();
                    break;
                case 201:
                    current = msg.arg1;
                    deleteExperienceRequest();
                    break;
                case 202:
                    current = msg.arg1;
                    Intent intent = new Intent(MystepsActivity.this, StepswritingActivity.class);
                    intent.putExtra("experience_id",datas.get(current).getExperience_id()+"");
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    };

    @OnClick({R.id.left, R.id.right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.right:
                Intent intent = new Intent(MystepsActivity.this, StepswritingActivity.class);
                startActivityForResult(intent, 108);
                break;
            case R.id.left:
                finish();
                break;
        }
    }

//    Fragment pagerFragmentOne, pagerFragmentTwo;
//    int currentPosition;
//    private class PagerAdapter extends FragmentPagerAdapter {
//
//        private final String[] TITLES = getResources().getStringArray(R.array.pager_name);
//
//        private PagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return TITLES[position];
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            if (position == 0) {
//                if (pagerFragmentOne == null) {
//                    pagerFragmentOne = new ExperienceFragmentOne();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("expericenceStatus", "1");
//                    pagerFragmentOne.setArguments(bundle);
//                }
//                return pagerFragmentOne;
//            } else {
//                if (pagerFragmentTwo == null) {
//                    pagerFragmentTwo = new ExperienceFragmentOne();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("expericenceStatus", "2");
//                    pagerFragmentTwo.setArguments(bundle);
//                }
//                return pagerFragmentTwo;
//            }
//        }
//
//        @Override
//        public int getCount() {
//            return NUM_PAGES;
//        }
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != 0) {
            if (resultCode == 108) {
                getData();
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiveBroadCastexp != null) {
            unregisterReceiver(receiveBroadCastexp);
        }
    }

    private ReceiveBroadCastexp receiveBroadCastexp;

    public void registerBoradcastReceiverJP() {
        receiveBroadCastexp = new ReceiveBroadCastexp();
        IntentFilter filter = new IntentFilter();
        filter.addAction("broadexprice"); // 只有持有相同的action的接受者才能接收此广播
        registerReceiver(receiveBroadCastexp, filter);
    }

    int type = 1;
    int pn = 1;

    @Override
    public void onRefresh() {
        type = 1;
        pn = 1;
        getData();
    }

    @Override
    public void onLoadMore() {
        type = 1;
        pn++;
        getData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    public class ReceiveBroadCastexp extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent data) {
//            pagerFragmentOne = null;
//            pagerFragmentTwo = null;
//            pageadapter = new PagerAdapter(getSupportFragmentManager());
//            pager.setAdapter(pageadapter);
//            pager.setCurrentItem(currentPosition);
        }
    }

    public void deleteExperienceRequest() {
        DeleteExperienceRequest rq = new DeleteExperienceRequest();
        rq.setExperience_id(datas.get(current).getExperience_id() + "");
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlerdelete);
    }

    private final AsyncHttpResponseHandler mHandlerdelete = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, MystepsActivity.this, DeleteExperienceResponse.class);
            if (kd != null) {
                DeleteExperienceResponse info = (DeleteExperienceResponse) kd;
                showToast(info.getMessage());
                if (info.getStatus() == 1) {
                    datas.remove(current);
                    adapter.notifyDataSetChanged();
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
