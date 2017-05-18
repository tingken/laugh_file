package com.quark.skillopedia.uiview.zhanghu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.DeleteCollectionCourseRequest;
import com.quark.api.auto.bean.DeleteCollectionCourseResponse;
import com.quark.api.auto.bean.ListCollection;
import com.quark.api.auto.bean.MyCollectionCourseListRequest;
import com.quark.api.auto.bean.MyCollectionCourseListResponse;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.AppParam;
import com.quark.skillopedia.R;
import com.quark.skillopedia.adapter.FavoritesAdapter;
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
 * Created by Administrator on 2016/5/21 0021.
 * 我的收藏界面
 */
public class FavoritesActivity extends BaseActivity implements XListView.IXListViewListener {

    @InjectView(R.id.nodata)
    TextView nodata;
    @InjectView(R.id.main_left)
    LinearLayout mainLeft;
    @InjectView(R.id.xlstv)
    XListView xlstv;

    ArrayList<ListCollection> courseData;
    FavoritesAdapter adapter;
    int pn = 1;
    int type = 1;
    int delete_position;
    String collection_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        ButterKnife.inject(this);
        setTopTitle("Favorites");
        collection_id = new AppParam().getcollectionId(this);
        myinitlist();
        MyCollectionCourseListRequest();
    }

    public void myinitlist() {
        courseData = new ArrayList<>();
        xlstv.setFooterDividersEnabled(false);
        // 设置xlistview可以加载、刷新
        xlstv.setPullLoadEnable(true);//购物车一次加载完 没有加载更多
        xlstv.setPullRefreshEnable(true);
        xlstv.setXListViewListener(this);
        xlstv.setOnItemClickListener(listListener);
        adapter = new FavoritesAdapter(this, courseData, handler);
        xlstv.setAdapter(adapter);
        Message msg = handler.obtainMessage();
        msg.what = 1;
        msg.arg1 = courseData.size();
        handler.sendMessage(msg);
    }

    AdapterView.OnItemClickListener listListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            position = position - 1;
            if (position < courseData.size()) {
                Intent intent = new Intent().setClass(FavoritesActivity.this, CourseDetailActivity.class);
                intent.putExtra("course_id", courseData.get(position).getCourse_id() + "");
                startActivity(intent);
            }
        }
    };

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.main_left)
    public void onClick() {
        finish();
    }

    /**
     * 我的收藏列表
     **/
    public void MyCollectionCourseListRequest() {
        MyCollectionCourseListRequest rq = new MyCollectionCourseListRequest();
        rq.setPn(pn);
        rq.setPage_size(10);
        rq.setLatitude(new AppParam().getLat(this));
        rq.setLongitude(new AppParam().getLng(this));
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandler);
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, FavoritesActivity.this, MyCollectionCourseListResponse.class);
            if (kd != null) {
                MyCollectionCourseListResponse info = (MyCollectionCourseListResponse) kd;
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

    public void dealdatas(MyCollectionCourseListResponse info) {
        if (type == 1) {
            courseData.clear();
        }
        List<ListCollection> data = info.getMyCollectionCourseListResult().getCollections().getList();

        Message msg = handler.obtainMessage();
        msg.what = type;
        if (data != null && data.size() > 0) {
            courseData.addAll(data);
            msg.arg1 = data.size();
        } else {
            msg.arg1 = 0;
        }
        handler.sendMessage(msg);
    }

    /**
     * 删除收藏
     */
    public void DeleteCollectionCourseRequest() {
        DeleteCollectionCourseRequest rq = new DeleteCollectionCourseRequest();
        rq.setCollection_id(courseData.get(delete_position).getCollection_id() + "");
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlerdel);
    }

    private final AsyncHttpResponseHandler mHandlerdel = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, FavoritesActivity.this, DeleteCollectionCourseResponse.class);
            if (kd != null) {
                DeleteCollectionCourseResponse info = (DeleteCollectionCourseResponse) kd;

                if (info.getStatus() == 1) {
                    courseData.remove(delete_position);
                    adapter.notifyDataSetChanged();
                }
                SharedPreferences sp = getSharedPreferences("collection_id", 0);
                sp.edit().remove("collection_id").commit();

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
                case 205:
                    delete_position = msg.arg1;
                    DeleteCollectionCourseRequest();
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
        MyCollectionCourseListRequest();

    }

    @Override
    public void onLoadMore() {
        type = 2;
        pn++;
        MyCollectionCourseListRequest();

    }

}
