package com.quark.skillopedia.uiview.zhanghu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.CommentListRequest;
import com.quark.api.auto.bean.CommentListResponse;
import com.quark.api.auto.bean.DeleteCommentRequest;
import com.quark.api.auto.bean.DeleteCommentResponse;
import com.quark.api.auto.bean.ListComment;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.AppParam;
import com.quark.skillopedia.R;
import com.quark.skillopedia.adapter.MessageAdapter;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.uiview.zhanghu.zhuye.EditActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cz.msebera.android.httpclient.Header;
import me.maxwin.view.XListView;

/**
 * Created by Administrator on 2016/5/21 0021.
 * 我的消息界面
 */
public class MessageActivity extends BaseActivity implements XListView.IXListViewListener {

    @InjectView(R.id.left)
    LinearLayout left;
    ArrayList<ListComment> datas;
    MessageAdapter adapter;
    @InjectView(R.id.xlstv)
    XListView xlstv;
    int pn = 1;
    int type = 1;
    String agent_level;//1.用户2.教练
    int delete_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.inject(this);
        left.setVisibility(View.VISIBLE);
        setBackButton();
        setTopTitle("Messages");
//        agent_level = (String) getValue4Intent("agent_level");
        agent_level =  new AppParam().getAgent_level(this);
        myinitlist();
        getData();
    }

    public void myinitlist() {
        datas = new ArrayList<>();
        xlstv.setFooterDividersEnabled(false);
        // 设置xlistview可以加载、刷新
        xlstv.setPullLoadEnable(true);//购物车一次加载完 没有加载更多
        xlstv.setPullRefreshEnable(true);
        xlstv.setXListViewListener(this);
        xlstv.setOnItemClickListener(listListener);
        adapter = new MessageAdapter(this, datas, handler);
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
//                Intent intent = new Intent().setClass(FavoritesActivity.this, CourseDetailActivity.class);
//                intent.putExtra("course_id", courseData.get(position).getCourse_id() + "");
//                startActivity(intent);
            }
        }
    };

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

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
                case 207:
                    delete_position = msg.arg1;
                    deleteCommentRequest();
                    break;
                case 208:
                    delete_position = msg.arg1;
                    Intent in = new Intent(MessageActivity.this, EditActivity.class);
                    in.putExtra("comment_id",datas.get(delete_position).getComment_id()+"");
                    startActivity(in);
                    break;
                default:
                    break;
            }
        }
    };


    /**
     * 我的消息列表
     */
    public void getData() {
        CommentListRequest rq = new CommentListRequest();
        rq.setType(agent_level);
        rq.setPn(pn);
        rq.setPage_size(10);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlercom);
    }

    private final AsyncHttpResponseHandler mHandlercom = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, MessageActivity.this, CommentListResponse.class);
            if (kd != null) {
                CommentListResponse info = (CommentListResponse) kd;
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

    public void deleteCommentRequest() {
        DeleteCommentRequest rq = new DeleteCommentRequest();
        rq.setDelete_type(agent_level);
        rq.setComment_id(datas.get(delete_position).getComment_id()+"");
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlerdel);
    }

    private final AsyncHttpResponseHandler mHandlerdel = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, MessageActivity.this, DeleteCommentResponse.class);
            if (kd != null) {
                DeleteCommentResponse info = (DeleteCommentResponse) kd;

                if (info.getStatus() == 1){
                    datas.remove(delete_position);
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

    public void dealdatas(CommentListResponse info) {
        if (type == 1) {
            datas.clear();
        }
        List<ListComment> data = info.getCommentListResult().getComments().getList();

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
