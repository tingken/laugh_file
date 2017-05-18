package com.quark.skillopedia.uiview.zhanghu.jiaolian;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.AddCourseRequest;
import com.quark.api.auto.bean.AddCourseResponse;
import com.quark.api.auto.bean.EditCourse;
import com.quark.api.auto.bean.EditCourseResponse;
import com.quark.api.auto.bean.ListScheduleBean;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.R;
import com.quark.skillopedia.adapter.NewcourseThreeScheduleAdapter;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.ui.widget.MyGridView;
import com.quark.skillopedia.util.TLog;
import com.quark.skillopedia.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2016/5/25 0025.
 * 新建课程3
 */

public class NewcourseThreeActivity extends BaseActivity {
    AddCourseRequest courseRequest;

    String hours = "";//1@1#2@3#3@7
    ArrayList<Integer> edithours;

    @InjectView(R.id.left_img)
    ImageView leftImg;
    @InjectView(R.id.left)
    LinearLayout left;
    @InjectView(R.id.checkall)
    TextView checkall;

    EditCourse course;

    NewcourseThreeScheduleAdapter adapter;
    List<ListScheduleBean> datas;
    @InjectView(R.id.gridview)
    MyGridView gridview;
    @InjectView(R.id.time_l)
    LinearLayout time_l;

    int total = 112;

    String[] tis = {"06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00",
            "19:00", "20:00", "21:00", "22:00"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcourse_three);
        ButterKnife.inject(this);
        setTopTitle("New course");
        setBackButton();

        courseRequest = (AddCourseRequest) getValue4Intent("courseRequest");
        course = (EditCourse)getValue4Intent("course");

        for (int j = 0; j < 17; j++) {
            TextView tv = new TextView(this);
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
            tv.setHeight(Utils.dip2px(this, 40));
            tv.setText(tis[j]);
            time_l.addView(tv);
        }

        initschedule();
        if (course!=null){
            initEditview();
        }
    }
    //===========提交==========================

    public void initschedule() {
        datas = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            ListScheduleBean ll = new ListScheduleBean();
            datas.add(ll);
        }

        adapter = new NewcourseThreeScheduleAdapter(NewcourseThreeActivity.this, datas);
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (datas.get(position).isCheck()) {
                    datas.get(position).setCheck(false);
                } else {
                    datas.get(position).setCheck(true);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void initEditview(){
        edithours = new ArrayList<>();
        String hourse = course.getHours();
//        String hourse = "1@1#2@2#8@1#112@7";
        String[] hoursestr = hourse.split("#");
        for (int i=0;i<hoursestr.length;i++){
            String[] positionstr = hoursestr[i].split("@");
            int position = Integer.valueOf(positionstr[0]);
            datas.get(position-1).setCheck(true);  //从0开始
        }

        adapter.notifyDataSetChanged();
    }

    boolean ischeckall = false;

    @OnClick(R.id.checkall)
    public void checkall(View v) {
        if (ischeckall) {
            for (int i = 0; i < total; i++) {
                datas.get(i).setCheck(false);
            }
            adapter.notifyDataSetChanged();
            ischeckall = false;
            Drawable nav_up = ContextCompat.getDrawable(this, R.drawable.check_1);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
            checkall.setCompoundDrawables(nav_up, null, null, null);
        } else {
            for (int i = 0; i < total; i++) {
                datas.get(i).setCheck(true);
            }
            adapter.notifyDataSetChanged();
            ischeckall = true;
            Drawable nav_up = ContextCompat.getDrawable(this, R.drawable.check_2);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
            checkall.setCompoundDrawables(nav_up, null, null, null);
        }
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
    }

    @OnClick(R.id.confirm)
    public void cn(View v){
        if (check()){
            getData();
        }
    }

    public boolean check() {
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).isCheck()) {
                hours += i+1 + "@" + ((i+1) % 7 + 1) + "#";  //i是从0开始 要加1再使用 当前星期 对7取余加1
            }
        }
        if (hours.endsWith("#")) {
            hours = hours.substring(0, hours.length() - 1);
        }
        if (Utils.isEmpty(hours)) {
            showToast("Please select the time");
            return false;
        }
        TLog.error(hours);

        return true;
    }

    public void getData() {
        if (course != null) {  //编辑 更增加 只是url不同
            courseRequest.setUrl("/app/CourseManage/editCourse");
            courseRequest.setHours(hours);
            showWait(true);
            QuarkApi.HttpRequestAllSign(courseRequest, mHandler2);
        } else {
            courseRequest.setHours(hours);
            showWait(true);
            QuarkApi.HttpRequestAllSign(courseRequest, mHandler);
        }
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, NewcourseThreeActivity.this, AddCourseResponse.class);
            if (kd != null) {
                AddCourseResponse info = (AddCourseResponse) kd;
                showToast(info.getMessage());
                if (info.getStatus() == 1) {
                    if (NewcourseTwoActivity.instance != null) {
                        NewcourseTwoActivity.instance.finish();
                    }
                    if (NewcourseActivity.instance != null) {
                        NewcourseActivity.instance.finish();
                    }
                    finish();
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

    private final AsyncHttpResponseHandler mHandler2 = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, NewcourseThreeActivity.this, EditCourseResponse.class);
            if (kd != null) {
                EditCourseResponse info = (EditCourseResponse) kd;
                showToast(info.getMessage());
                if (info.getStatus() == 1) {
                    if (NewcourseTwoActivity.instance != null) {
                        NewcourseTwoActivity.instance.finish();
                    }
                    if (NewcourseActivity.instance != null) {
                        NewcourseActivity.instance.finish();
                    }
                    finish();
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
}
