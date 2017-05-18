package com.quark.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.ListComment;
import com.quark.api.auto.bean.TaCommentListRequest;
import com.quark.api.auto.bean.TaCommentListResponse;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.AppParam;
import com.quark.skillopedia.R;
import com.quark.skillopedia.adapter.CommentsAdapter;
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
 * Created by pan on 2016/7/6 0006.
 * >#
 * >#
 */
public class CommentsFragment extends BaseFragment implements XListView.IXListViewListener {


    @InjectView(R.id.xlstv)
    XListView xlstv;

    int pn = 1;
    int type = 1;
    ArrayList<ListComment> datas;
    CommentsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_comments, container, false);
        ButterKnife.inject(this, v);
        myinitlist();
        getData();
        return v;
    }

    public void myinitlist() {
        datas = new ArrayList<>();
        xlstv.setFooterDividersEnabled(false);
        // 设置xlistview可以加载、刷新
        xlstv.setPullLoadEnable(true);//购物车一次加载完 没有加载更多
        xlstv.setPullRefreshEnable(true);
        xlstv.setXListViewListener(this);
        xlstv.setOnItemClickListener(listListener);
        adapter = new CommentsAdapter(getActivity(), datas);
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
//                Intent intent = new Intent().setClass(getActivity(), CourseDetailActivity.class);
//                intent.putExtra("course_id", datas.get(position).getCourse_id());
//                startActivity(intent);
            }
        }
    };

    public void getData() {
        TaCommentListRequest rq = new TaCommentListRequest();
        rq.setPn(pn);
        rq.setPage_size(10);
        rq.setUser_id(new AppParam().getUser_id(getActivity()));
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandler);
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, getActivity(), TaCommentListResponse.class);
            if (kd != null) {
                TaCommentListResponse info = (TaCommentListResponse) kd;
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

    public void dealdatas(TaCommentListResponse info) {
        if (type == 1) {
            datas.clear();
        }
        List<ListComment> data = info.getTaCommentListResult().getComments().getList();
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
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
    public void onRefresh() {
        type = 1;
        pn = 1;
        getData();
    }

    @Override
    public void onLoadMore() {
        type = 1;
        pn = 1;
        getData();
    }
}
