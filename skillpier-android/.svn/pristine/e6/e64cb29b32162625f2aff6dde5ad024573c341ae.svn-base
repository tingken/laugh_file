package com.quark.fragment;

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
import com.quark.skillopedia.ui.widget.AutoListForScrollView;
import com.quark.skillopedia.uiview.zhanghu.jingli.MyStepsDetailActivity;
import com.quark.skillopedia.uiview.zhanghu.jingli.StepswritingActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cz.msebera.android.httpclient.Header;

/**
 * Created by pan on 2016/7/6 0006.
 * >#
 * >#
 */
public class StepsFtagment extends BaseFragment implements AutoListForScrollView.OnRefreshListener, AutoListForScrollView.OnLoadListener {


//    @InjectView(R.id.xlstv)
//    XListView xlstv;

    int pn = 1;
    int type = 1;
//    ArrayList<ListExperience> datas;
//    StepsAdapter adapter;

    private ArrayList<ListExperience> datas;
    ExperienceEditAdapter adapter;

    @InjectView(R.id.forlist)
    AutoListForScrollView forlist;
    int current;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_steps, container, false);
        ButterKnife.inject(this, v);

         getActivity().findViewById(R.id.right).setOnClickListener(clickListener);

        myinitlist();
        getData();
        return v;
    }

    //创建按钮点击事件
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), StepswritingActivity.class);
            startActivityForResult(intent, 108);
        }
    };

    public void myinitlist() {
        datas = new ArrayList<>();
        forlist.setFooterDividersEnabled(false);
        // 设置xlistview可以加载、刷新
//        forlist.setPullLoadEnable(true);//购物车一次加载完 没有加载更多
//        forlist.setPullRefreshEnable(true);
//        forlist.setXListViewListener(this);
        forlist.setLoadEnable(true);
        forlist.setOnItemClickListener(listListener);
        adapter = new ExperienceEditAdapter(getActivity(), datas, handler);
        forlist.setAdapter(adapter);

        Message msg = handler.obtainMessage();
        msg.what = 1;
        msg.arg1 = datas.size();
        handler.sendMessage(msg);
    }

    AdapterView.OnItemClickListener listListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //position = position - 1;
            if (position < datas.size()) {

                Intent intent = new Intent().setClass(getActivity(), MyStepsDetailActivity.class);
                intent.putExtra("experience_id", datas.get(position).getExperience_id() + "");
                intent.putExtra("position", position);
                startActivity(intent);
            }
        }
    };

    /*public void getData() {
        TaExperienceListRequest rq = new TaExperienceListRequest();
        rq.setUser_id(new AppParam().getUser_id(getActivity()));
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandler);
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, getActivity(), TaExperienceListResponse.class);
            if (kd != null) {
                TaExperienceListResponse info = (TaExperienceListResponse) kd;
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

    public void dealdatas(TaExperienceListResponse info) {
        if (type == 1) {
            datas.clear();
        }
        List<ListExperience> data = info.getTaExperienceListResult().getTaExperiences();
        Message msg = handler.obtainMessage();
        msg.what = type;
        if (data != null && data.size() > 0) {
            datas.addAll(data);
            msg.arg1 = data.size();
        } else {
            msg.arg1 = 0;
        }
        handler.sendMessage(msg);
    }*/

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
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int pdatasize = msg.arg1;
            switch (msg.what) {
                case 1:
                    //xlstv.stopRefresh();
                    adapter.notifyDataSetChanged();

                    break;
                case 2:
                    //xlstv.stopLoadMore();
                    adapter.notifyDataSetChanged();
                    break;
                case 201:
                    current = msg.arg1;
                    deleteExperienceRequest();
                    break;
                case 202:
                    current = msg.arg1;
                    Intent intent = new Intent(getActivity(), StepswritingActivity.class);
                    intent.putExtra("experience_id", datas.get(current).getExperience_id() + "");
                    startActivityForResult(intent,108);
//                    startActivity(intent);
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
    public void onLoad() {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != 0) {
            if (resultCode == 108) {
                getData();
                adapter.notifyDataSetChanged();
            }
        }
    }

    //    @Override
//    public void onRefresh() {
//        type = 1;
//        pn = 1;
//        getData();
//    }
//
//    @Override
//    public void onLoadMore() {
//        type = 1;
//        pn = 1;
//        getData();
//    }
    /**
     * 删除 steps
     * @author pan
     * @time 2016/11/21 0021 下午 2:10
     */
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
