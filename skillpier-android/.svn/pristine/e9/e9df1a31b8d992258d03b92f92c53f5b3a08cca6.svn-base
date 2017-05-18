package com.quark.skillopedia.uiview.zhanghu.jingli;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

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
import com.quark.skillopedia.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cz.msebera.android.httpclient.Header;
import me.maxwin.view.XListView;

/**
 * Created by Administrator on 2016/5/23 0023.
 * 已经发表的经历列表 和 经历草稿
 */
public class ExperienceFragmentOne extends BaseFragment implements XListView.IXListViewListener {
    @InjectView(R.id.xlstv)
    XListView list;

    private ArrayList<ListExperience> datas;
    String status;
    ExperienceEditAdapter adapter;
    int current;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View pager = inflater.inflate(R.layout.pager_fragment, container, false);
        ButterKnife.inject(this, pager);
        status = getArguments().getString("expericenceStatus");
        myinitlist();
        getData();

        return pager;
    }

    public void myinitlist() {
        datas = new ArrayList<>();
        list.setFooterDividersEnabled(false);
        // 设置xlistview可以加载、刷新
        list.setPullLoadEnable(true);//购物车一次加载完 没有加载更多
        list.setPullRefreshEnable(true);
        list.setXListViewListener(this);
        list.setOnItemClickListener(listListener);
        adapter = new ExperienceEditAdapter(getActivity(), datas,handler);
        list.setAdapter(adapter);
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
            Object kd = ApiResponse.get(arg2, getActivity(), ExperienceListResponse.class);
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
//        if (type == 1 && (tin == null || tin.size() < 1)) {
//            nodata.setVisibility(View.VISIBLE);
//            list.setVisibility(View.GONE);
//        } else {
//            nodata.setVisibility(View.GONE);
//            list.setVisibility(View.VISIBLE);
//        }
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
                    Intent intent = new Intent(getActivity(), StepswritingActivity.class);
                    intent.putExtra("experience_id",datas.get(current).getExperience_id()+"");
                    startActivity(intent);
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
        type = 2;
        pn++;
        getData();
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
            Object kd = ApiResponse.get(arg2, getActivity(), DeleteExperienceResponse.class);
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
