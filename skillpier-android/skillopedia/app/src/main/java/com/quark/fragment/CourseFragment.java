package com.quark.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.ListCourse;
import com.quark.api.auto.bean.TaCourseListRequest;
import com.quark.api.auto.bean.TaCourseListResponse;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.AppParam;
import com.quark.skillopedia.R;
import com.quark.skillopedia.adapter.CourseListAdapter;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseFragment;
import com.quark.skillopedia.uiview.fenlei.CourseDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cz.msebera.android.httpclient.Header;
import me.maxwin.view.XListView;

/**
 * Created by Administrator on 2016/5/23 0023.
 */
public class CourseFragment extends BaseFragment implements XListView.IXListViewListener {


    @InjectView(R.id.xlstv)
    XListView xlstv;
    int pn = 1;
    int type = 1;
    ArrayList<ListCourse> datas;
    CourseListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View coursepager = inflater.inflate(R.layout.course_fragment, container, false);
        ButterKnife.inject(this, coursepager);
        myinitlist();
        taCourseListRequest();
        return coursepager;
    }

    public void myinitlist() {
        datas = new ArrayList<>();
        xlstv.setFooterDividersEnabled(false);
        // 设置xlistview可以加载、刷新
        xlstv.setPullLoadEnable(true);//购物车一次加载完 没有加载更多
        xlstv.setPullRefreshEnable(true);
        xlstv.setXListViewListener(this);
        xlstv.setOnItemClickListener(listListener);
        adapter = new CourseListAdapter(getActivity(), datas, handler);
        xlstv.setAdapter(adapter);

        Message msg = handler.obtainMessage();
        msg.what = 1;
        msg.arg1 = datas.size();
        handler.sendMessage(msg);
    }

    AdapterView.OnItemClickListener listListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            position = position - 1;
            if (position < datas.size()) {
                Intent intent = new Intent().setClass(getActivity(), CourseDetailActivity.class);
                intent.putExtra("course_id", datas.get(position).getCourse_id()+"");
                startActivity(intent);
            }
        }
    };


    public void taCourseListRequest() {
        TaCourseListRequest rq = new TaCourseListRequest();
        rq.setPn(1);
        rq.setPage_size(10);
        rq.setUser_id(new AppParam().getUser_id(getActivity()));
        rq.setLatitude(new AppParam().getLat(getActivity()));
        rq.setLongitude(new AppParam().getLng(getActivity()));
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandler);
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, getActivity(), TaCourseListResponse.class);
            if (kd != null) {
                TaCourseListResponse info = (TaCourseListResponse) kd;

                dealdatas(info);
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

    public void dealdatas(TaCourseListResponse info) {
        if (type == 1) {
            datas.clear();
        }
        List<ListCourse> data = info.getTaCourseListResult().getCourses().getList();
        Message msg = handler.obtainMessage();
        msg.what = type;
        if (data != null && data.size() > 0) {
            datas.addAll(data);
            msg.arg1 = data.size();
        } else {
            msg.arg1 = 0;
        }
        handler.sendMessage(msg);
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int pdatasize = msg.arg1;
            xlstv.isnomore(pdatasize < 10);
            switch (msg.what) {
                case 1:
                    xlstv.stopRefresh();
                    adapter.notifyDataSetChanged();

                    break;
                case 2:
                    xlstv.stopLoadMore();
                    adapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onRefresh() {
        type = 1;
        pn = 1;
        taCourseListRequest();
    }

    @Override
    public void onLoadMore() {
        type = 1;
        pn = 1;
        taCourseListRequest();
    }
}
