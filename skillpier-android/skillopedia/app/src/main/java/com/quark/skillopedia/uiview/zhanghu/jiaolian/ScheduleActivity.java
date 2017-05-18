package com.quark.skillopedia.uiview.zhanghu.jiaolian;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.ListScheduleBean;
import com.quark.api.auto.bean.ScheduleListRequest;
import com.quark.api.auto.bean.ScheduleListResponse;
import com.quark.api.auto.bean.SetAllBusyTimeRequest;
import com.quark.api.auto.bean.SetAllBusyTimeResponse;
import com.quark.api.auto.bean.SetBusyTimeRequest;
import com.quark.api.auto.bean.SetBusyTimeResponse;
import com.quark.api.auto.bean.SetFreeTimeRequest;
import com.quark.api.auto.bean.SetFreeTimeResponse;
import com.quark.api.auto.bean.SetStopCourseRequest;
import com.quark.api.auto.bean.SetStopCourseResponse;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.AppParam;
import com.quark.skillopedia.R;
import com.quark.skillopedia.adapter.ScheduleAdapter;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.ui.widget.MyGridView;
import com.quark.skillopedia.ui.widget.TipsDialog;
import com.quark.skillopedia.ui.widget.VideoDialog;
import com.quark.skillopedia.ui.widget.WheelChooseBorthdayDialog;
import com.quark.skillopedia.util.TLog;
import com.quark.skillopedia.util.Utils;

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
 * 时间管理
 *
 * @author pan
 * @time 2016/8/22 0022 下午 5:40
 */
public class ScheduleActivity extends BaseActivity {

    ScheduleAdapter adapter;
    String timestr;
    String zTimestr;
    List<ListScheduleBean> datas;
    String time_slots = "";
    String remark = "";
    String is_stop_course;
    String is_busy_24;

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
    @InjectView(R.id.courseName)
    TextView courseName;
    @InjectView(R.id.courseTime)
    TextView courseTime;
    @InjectView(R.id.courseAddress)
    TextView courseAddress;
    @InjectView(R.id.save_bt)
    Button saveBt;
    @InjectView(R.id.publish_bt)
    Button publishBt;
    @InjectView(R.id.alldaybusy)
    TextView alldaybusy;
    @InjectView(R.id.stopcourse)
    TextView stopcourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        ButterKnife.inject(this);
        setTopTitle("Appointment time");
        String from= (String)getValue4Intent("from");
        if ("MySchedule".equals(from)){
            setTopTitle("My Schedule");
        }

        setBackButton();
        datas = new ArrayList<>();

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (position<24){
                if (datas.get(position).getSchedule_state().equals("3") || datas.get(position).getSchedule_state().equals("2")) {//1-unavaliable，2-busy,3-有空
                    courseTime.setVisibility(View.GONE);
                    courseName.setVisibility(View.VISIBLE);
                    courseAddress.setVisibility(View.GONE);
                    courseName.setText(datas.get(position).getRemarks());
                    if (datas.get(position).isCheck()) {
                        datas.get(position).setCheck(false);
                    } else {
                        datas.get(position).setCheck(true);
                    }
                    adapter.notifyDataSetChanged();
                    TLog.error(datas.get(position).isCheck() + "3或者2");

                } else if (datas.get(position).getSchedule_state().equals("1")) {
                    courseTime.setVisibility(View.VISIBLE);
                    courseName.setVisibility(View.VISIBLE);
                    courseAddress.setVisibility(View.VISIBLE);
                    courseName.setText("Course name:" + datas.get(position).getCourse_name());
                    courseTime.setText("Time:" + datas.get(position).getCourse_time());
                    courseAddress.setText("Location:" + datas.get(position).getCourse_location());

                }
//                    else if(datas.get(position).getSchedule_state().equals("2")){
//
//                    }
            }
//            }
        });

        Date date = new Date();
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        timestr = d.format(date);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH);
        zTimestr = dateFormat.format(date);
        time.setText(zTimestr);
        scheduleListRequest();
    }

    public void scheduleListRequest() {
        ScheduleListRequest rq = new ScheduleListRequest();
        rq.setChoice_currentdate(timestr);
        rq.setUser_id(new AppParam().getUser_id(this));
        showWait(true);
        QuarkApi.HttpRequestNoTS(rq, mHandler);
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, ScheduleActivity.this, ScheduleListResponse.class);
            if (kd != null) {
                ScheduleListResponse info = (ScheduleListResponse) kd;
                if (info.getStatus() == 1) {
                    datas.clear();
                    datas.addAll(info.getScheduleBeans());
                    is_stop_course = info.getIs_stop_course();
                    is_busy_24 = info.getIs_busy_24();
                    if (is_stop_course.equals("1")) {
                        Drawable nav_up = getResources().getDrawable(R.drawable.check_2);
                        nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                        stopcourse.setCompoundDrawables(nav_up, null, null, null);
                    }
                    if (is_busy_24.equals("1")) {
                        Drawable nav_up = getResources().getDrawable(R.drawable.check_2);
                        nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                        alldaybusy.setCompoundDrawables(nav_up, null, null, null);
                    }
                    //填充一个补满格子
//                    ListScheduleBean nullad = new ListScheduleBean();
//                    datas.add(nullad);
                    adapter = new ScheduleAdapter(ScheduleActivity.this, datas);
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

    @OnClick({R.id.save_bt, R.id.publish_bt, R.id.alldaybusy, R.id.stopcourse, R.id.choosetimeview})
    public void ss(View b) {
        switch (b.getId()) {
            case R.id.save_bt://free
                if (check()) {
                    setFreeTimeRequest();
                }
                break;
            case R.id.publish_bt:
                if (check()) {
                    setmark();
                }
                break;
            case R.id.alldaybusy:
                if (is_busy_24.equals("1")) {
                    is_busy_24 = "0";
                    Drawable nav_up = ContextCompat.getDrawable(this, R.drawable.check_1);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    alldaybusy.setCompoundDrawables(nav_up, null, null, null);
                    setAllBusyTimeRequest(is_busy_24);
                } else {
                    is_busy_24 = "1";
                    Drawable nav_up = ContextCompat.getDrawable(this, R.drawable.check_2);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    alldaybusy.setCompoundDrawables(nav_up, null, null, null);
                    settips("Mark whole day busy?", 1);
                }
                break;
            case R.id.stopcourse:
                if (is_stop_course.equals("1")) {
                    is_stop_course = "0";
                    Drawable nav_up = getResources().getDrawable(R.drawable.check_1);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    stopcourse.setCompoundDrawables(nav_up, null, null, null);
                    setStopCourseRequest(is_stop_course);
                } else {
                    is_stop_course = "1";
                    Drawable nav_up = getResources().getDrawable(R.drawable.check_2);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    stopcourse.setCompoundDrawables(nav_up, null, null, null);
                    settips("Are you sure to suspend all courses?", 2);
                }
                break;
            case R.id.choosetimeview:
                WheelChooseBorthdayDialog wccd = new WheelChooseBorthdayDialog();
                wccd.showSheetPic(this, handler, 101);
                break;
        }
    }

    public boolean check() {
        time_slots = "";
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).isCheck()) {
                time_slots += datas.get(i).getHour_index() + "#";
            }
        }
        if (time_slots.endsWith("#")) {
            time_slots = time_slots.substring(0, time_slots.length() - 1);
        }
        if (Utils.isEmpty(time_slots)) {
            showToast("Enter your plan(optional)");
            return false;
        }

        return true;
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
                    scheduleListRequest();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }

        ;
    };

    /**
     * 设置为空闲
     *
     * @author leon
     * @time 2016/7/1 0001 下午 2:02
     */
    public void setFreeTimeRequest() {
        SetFreeTimeRequest rq = new SetFreeTimeRequest();
        rq.setChoice_currentdate(timestr);
        rq.setTime_slots(time_slots);
        rq.setRemarks(remark);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlerfree);
    }

    private final AsyncHttpResponseHandler mHandlerfree = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, ScheduleActivity.this, SetFreeTimeResponse.class);
            if (kd != null) {
                SetFreeTimeResponse info = (SetFreeTimeResponse) kd;
                showToast(info.getMessage());
                for (int i = 0; i < datas.size(); i++) {
                    if (datas.get(i).isCheck()) {
                        datas.get(i).setSchedule_state(info.getSchedule_state());
                        datas.get(i).setCheck(false);
                        datas.get(i).setSchedule_state_message(info.getSchedule_state_message());
                    }
                }
                if (is_busy_24.equals("1")) { //设置为有空 取消全天没空
                    is_busy_24 = "0";
                    Drawable nav_up = getResources().getDrawable(R.drawable.check_1);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    alldaybusy.setCompoundDrawables(nav_up, null, null, null);
                }
                adapter.notifyDataSetChanged();
            }
            showWait(false);
        }

        @Override
        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
            AppContext.showToast("Network error" + arg0);
            showWait(false);
        }
    };

    /**
     * 设置为busy
     *
     * @author leon
     * @time 2016/7/1 0001 下午 2:02
     */
    public void setBusyTimeRequest() {
        SetBusyTimeRequest rq = new SetBusyTimeRequest();
        rq.setChoice_currentdate(timestr);
        rq.setTime_slots(time_slots);
        rq.setRemarks(remark);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlerbusy);
    }

    private final AsyncHttpResponseHandler mHandlerbusy = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, ScheduleActivity.this, SetBusyTimeResponse.class);
            if (kd != null) {
                SetBusyTimeResponse info = (SetBusyTimeResponse) kd;
                showToast(info.getMessage());
                for (int i = 0; i < datas.size(); i++) {
                    if (datas.get(i).isCheck()) {
                        datas.get(i).setSchedule_state(info.getSchedule_state());
                        datas.get(i).setRemarks(remark);
                        datas.get(i).setCheck(false);
                        datas.get(i).setSchedule_state_message(info.getSchedule_state_message());
                    }
                }
                adapter.notifyDataSetChanged();
            }
            showWait(false);
        }

        @Override
        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
            AppContext.showToast("Network error" + arg0);
            showWait(false);
        }
    };

    /**
     * 设置为全天没空
     *
     * @author leon
     * @time 2016/7/1 0001 下午 2:02
     */
    public void setAllBusyTimeRequest(String type) {
        SetAllBusyTimeRequest rq = new SetAllBusyTimeRequest();
        rq.setChoice_currentdate(timestr);
        rq.setIs_busy_24(type + "");
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlerallbusy);
    }

    private final AsyncHttpResponseHandler mHandlerallbusy = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, ScheduleActivity.this, SetAllBusyTimeResponse.class);
            if (kd != null) {
                SetAllBusyTimeResponse info = (SetAllBusyTimeResponse) kd;
                showToast(info.getMessage());
//                for (int i=0;i<datas.size();i++){
//                    if (datas.get(i).isCheck()){
//                        datas.get(i).setSchedule_state(info.getSchedule_state());
//                        datas.get(i).setCheck(false);
//                    }
//                }
//                adapter.notifyDataSetChanged();
                scheduleListRequest();
            }
            showWait(false);
        }

        @Override
        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
            AppContext.showToast("Network error" + arg0);
            showWait(false);
        }
    };

    /**
     * 设置为暂停授课
     *
     * @author leon
     * @time 2016/7/1 0001 下午 2:02
     */
    public void setStopCourseRequest(String type) {
        SetStopCourseRequest rq = new SetStopCourseRequest();
        rq.setIs_stop_course(type + ""); // 	今后暂停课程 0-否，1-是
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlerallstop);
    }

    private final AsyncHttpResponseHandler mHandlerallstop = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, ScheduleActivity.this, SetStopCourseResponse.class);
            if (kd != null) {
                SetStopCourseResponse info = (SetStopCourseResponse) kd;
                showToast(info.getMessage());
                for (int i = 0; i < datas.size(); i++) {
                    if (datas.get(i).isCheck()) {
                        datas.get(i).setSchedule_state(info.getSchedule_state());
                        datas.get(i).setCheck(false);
                    }
                }
                adapter.notifyDataSetChanged();
            }
            showWait(false);
        }

        @Override
        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
            AppContext.showToast("Network error" + arg0);
            showWait(false);
        }
    };

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    public void setmark() {
        final VideoDialog.Builder builder = new VideoDialog.Builder(this);
        builder.setMessage("enter you schedule plan");
        builder.setTitle("Schedule");
        builder.setEditHine("mark");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                remark = builder.getconten();
                setBusyTimeRequest();
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    String msgStr;

    public void settips(String msg, final int type) {
        final TipsDialog.Builder builder = new TipsDialog.Builder(this);
        builder.setMessage(msg);
        builder.setTitle("confirm");
        msgStr = msg;
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                TLog.error(msgStr + "***********");//确定全天没有空？确定暂停课程？
                if (msgStr.equals("确定全天没有空？")) {
                    is_busy_24 = "0";
                    Drawable nav_up = getResources().getDrawable(R.drawable.check_1);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    alldaybusy.setCompoundDrawables(nav_up, null, null, null);
                }

                if (msgStr.equals("确定暂停课程？")) {
                    is_stop_course = "0";
                    Drawable na_up = getResources().getDrawable(R.drawable.check_1);
                    na_up.setBounds(0, 0, na_up.getMinimumWidth(), na_up.getMinimumHeight());
                    stopcourse.setCompoundDrawables(na_up, null, null, null);
                }
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (type == 1) {
                    setAllBusyTimeRequest(is_busy_24);
                } else {
                    setStopCourseRequest(is_stop_course);
                }
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

}
