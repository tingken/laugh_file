package com.quark.skillopedia.uiview.zhanghu.jiaolian;

import android.content.Intent;
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
import com.quark.api.auto.bean.DeleteCourseRequest;
import com.quark.api.auto.bean.DeleteCourseResponse;
import com.quark.api.auto.bean.ListCourse;
import com.quark.api.auto.bean.MyCourseListRequest;
import com.quark.api.auto.bean.MyCourseListResponse;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.AppParam;
import com.quark.skillopedia.R;
import com.quark.skillopedia.adapter.MycourseAdapter;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.uiview.fenlei.CourseDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import me.maxwin.view.XListView;

/**
 * Created by Administrator on 2016/5/25 0025.
 * 我的课程管理界面
 */
public class MycourseActivity extends BaseActivity implements XListView.IXListViewListener {
    public ArrayList<ListCourse> datas;
    MycourseAdapter adapter;
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
    @InjectView(R.id.kong_ly)
    LinearLayout kongLy;
    @InjectView(R.id.course_list)
    XListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycourse);
        ButterKnife.inject(this);
        setTopTitle("My course");
        setBackButton();
        right.setVisibility(View.VISIBLE);
        rightrig.setImageDrawable(getResources().getDrawable(R.drawable.cm_1));
        datas = new ArrayList<>();
        myinitlist();

        myCourseListRequest();
    }

    public void myinitlist() {
        datas = new ArrayList<>();
        list.setFooterDividersEnabled(false);
        // 设置xlistview可以加载、刷新
        list.setPullLoadEnable(true);//购物车一次加载完 没有加载更多
        list.setPullRefreshEnable(true);
        list.setXListViewListener(this);
        list.setOnItemClickListener(co);
        adapter = new MycourseAdapter(this,datas,handler);
        list.setAdapter(adapter);

        Message msg = handler.obtainMessage();
        msg.what = 1;
        msg.arg1 = datas.size();
        handler.sendMessage(msg);
    }

    AdapterView.OnItemClickListener co = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            position = position -1;
            if (position<datas.size()){

                Intent intent = new Intent(MycourseActivity.this, CourseDetailActivity.class);
                intent.putExtra("course_id",datas.get(current).getCourse_id()+"");
                startActivity(intent);
            }
        }
    };

    public void myCourseListRequest() {
        MyCourseListRequest rq = new MyCourseListRequest();
        rq.setPage_size(10);
        rq.setPn(pn);
        rq.setLatitude(new AppParam().getLat(this));
        rq.setLongitude(new AppParam().getLng(this));
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlerdata);
    }

    private final AsyncHttpResponseHandler mHandlerdata = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, MycourseActivity.this, MyCourseListResponse.class);
            if (kd != null) {
                MyCourseListResponse info = (MyCourseListResponse) kd;
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

    public void dealData(MyCourseListResponse info) {
        if (type == 1) {
            datas.clear();
        }
        List<ListCourse> tin = info.getMyCourseListResult().getCourses().getList();
        Message msg = handler.obtainMessage();
        msg.what = type;
        if (tin != null && tin.size() > 0) {
            datas.addAll(tin);
            msg.arg1 = tin.size();
        } else {
            msg.arg1 = 0;
        }
        handler.sendMessage(msg);
//        if (type == 1 && (tin == null || tin.size() < 1)) {
//            nodata.setVisibility(View.VISIBLE);
//            list.setVisibility(View.GONE);
//        } else {
//            nodata.setVisibility(View.GONE);
//            list.setVisibility(View.VISIBLE);
//        }
    }
    int current;

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
                    deleteCourseRequest();
                    break;
                case 202:
                    current = msg.arg1;
                    Intent intent = new Intent(MycourseActivity.this, NewcourseActivity.class);
                    intent.putExtra("course_id",datas.get(current).getCourse_id()+"");
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    };


    int type = 1;
    int pn = 1;

    @Override
    public void onRefresh() {
        type = 1;
        pn = 1;
        myCourseListRequest();
    }

    @Override
    public void onLoadMore() {
        type = 2;
        pn++;
        myCourseListRequest();
    }


    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.right:
                Intent intent = new Intent(MycourseActivity.this, NewcourseActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void deleteCourseRequest() {
        DeleteCourseRequest rq = new DeleteCourseRequest();
        rq.setCourse_id(datas.get(current).getCourse_id()+"");
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlerdelete);
    }

    private final AsyncHttpResponseHandler mHandlerdelete = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, MycourseActivity.this, DeleteCourseResponse.class);
            if (kd != null) {
                DeleteCourseResponse info = (DeleteCourseResponse) kd;
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
