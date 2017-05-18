package com.quark.skillopedia.uiview.fenlei;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.BookingScheduleListRequest;
import com.quark.api.auto.bean.BookingScheduleListResponse;
import com.quark.api.auto.bean.ListScheduleBean;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.AppParam;
import com.quark.skillopedia.R;
import com.quark.skillopedia.adapter.ScheduleAdapter;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.ui.widget.MyGridView;
import com.quark.skillopedia.ui.widget.WheelChooseBorthdayDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * 查看教练时间表
 *
 * @author leon
 * @time 2016/7/2 0002 下午 3:55
 */
public class ScheduleShowActivity extends BaseActivity {

    ScheduleAdapter adapter;
    String timestr;
    List<ListScheduleBean> datas;

    String coure_id;
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
    @InjectView(R.id.time)
    TextView time;
    @InjectView(R.id.choosetimeview)
    RelativeLayout choosetimeview;
    @InjectView(R.id.gridview)
    MyGridView gridview;
    @InjectView(R.id.closed_tv)
    TextView closedTv;

    String zTimeStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_schedule);
        ButterKnife.inject(this);
        setTopTitle("Appointment time");
        setBackButton();
        coure_id = (String) getValue4Intent("coure_id");

        datas = new ArrayList<>();

        Date date = new Date();
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH);
        zTimeStr = dateFormat.format(date);
        timestr = d.format(date);
        time.setText(zTimeStr);
        bookingScheduleListRequest();
    }

    public void bookingScheduleListRequest() {
        BookingScheduleListRequest rq = new BookingScheduleListRequest();
        rq.setChoice_currentdate(timestr);
        rq.setUser_id(new AppParam().getUser_id(this));
        rq.setCourse_id(coure_id);
        rq.setOrders_id("0");
        showWait(true);
        QuarkApi.HttpRequestNoTS(rq, mHandler);
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, ScheduleShowActivity.this, BookingScheduleListResponse.class);
            if (kd != null) {
                BookingScheduleListResponse info = (BookingScheduleListResponse) kd;
                if (info.getIs_stop_course() == 1){
                    closedTv.setVisibility(View.VISIBLE);
                }
                if (info.getStatus() == 1) {
                    datas.clear();
                    datas.addAll(info.getScheduleBeans());
                    adapter = new ScheduleAdapter(ScheduleShowActivity.this, datas);
                    gridview.setAdapter(adapter);
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

    @OnClick({R.id.choosetimeview})
    public void ss(View b) {
        switch (b.getId()) {
            case R.id.choosetimeview:
                WheelChooseBorthdayDialog wccd = new WheelChooseBorthdayDialog();
                wccd.showSheetPic(this, handler, 101);
                break;
        }
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 101:
                    timestr = (String) msg.obj;
                    Date date = null;
                    try {
                        date = new SimpleDateFormat("yyyy-MM-dd").parse(timestr);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    // 将date转化为String
                    String s = new SimpleDateFormat("MMM d, yyyy",Locale.ENGLISH).format(date);
                    time.setText(s);
                    bookingScheduleListRequest();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }

        ;
    };


    @Override
    public void initView() {
    }

    @Override
    public void initData() {
    }


}
