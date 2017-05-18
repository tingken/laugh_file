package com.quark.skillopedia.uiview.fenlei;

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
import com.quark.api.auto.bean.Category02ListRequest;
import com.quark.api.auto.bean.Category02ListResponse;
import com.quark.api.auto.bean.ListCategory02;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.AppParam;
import com.quark.skillopedia.R;
import com.quark.skillopedia.adapter.SecondCategoryAdapter;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.ui.widget.AutoListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cz.msebera.android.httpclient.Header;

/**
 * 艺术与运动   界面
 * */
public class SecondeCategoryActivity extends BaseActivity implements AutoListView.OnRefreshListener, AutoListView.OnLoadListener {

    SecondCategoryAdapter adapter;
    String category_01_id;
    String category_02_id;
    ArrayList<ListCategory02> datas = new ArrayList<>();

    @InjectView(R.id.list)
    AutoListView lstv;
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
    @InjectView(R.id.nodata)
    TextView nodata;
    int pich;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_category_layout);
        ButterKnife.inject(this);
        category_01_id = (String) getValue4Intent("category_id");

        String title = (String) getValue4Intent("title");
        if (title.equals("SPORTS")) {
            setTopTitle("Sports");
        }else if (title.equals("ARTS")) {
            setTopTitle("Arts");
        }

        setBackButton();
        getData();
        int sw = new AppParam().getScreenWidth(this);
        pich = (int) ((sw * 282) / 705.0);
        adapter = new SecondCategoryAdapter(this, datas,pich);
        lstv.setOnRefreshListener(this);
        lstv.setOnLoadListener(this);
        lstv.setAdapter(adapter);
        lstv.setFocusable(false);
        lstv.setDivider(null);
        lstv.setOnItemClickListener(listListener);

    }

    AdapterView.OnItemClickListener listListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            position = position - 1;
            if (position < datas.size()) {
                Intent intent = new Intent(SecondeCategoryActivity.this, CourseListActivity.class);
                intent.putExtra("category_02_id", datas.get(position).getCategory_02_id() + "");
                intent.putExtra("datatype", "2");
                startActivity(intent);
            }
        }
    };

    public void getData() {
        Category02ListRequest rq = new Category02ListRequest();
        rq.setCategory_01_id(category_01_id);
        showWait(true);
        QuarkApi.HttpRequestNoTS(rq, mHandler);
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, SecondeCategoryActivity.this, Category02ListResponse.class);
            if (kd != null) {
                Category02ListResponse info = (Category02ListResponse) kd;
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

    public void dealdatas(Category02ListResponse info) {
        if (type == AutoListView.REFRESH) {
            datas.clear();
        }
        List<ListCategory02> tproducts = info.getCategory02ListResult().getCategory02s();
        Message msg = handler.obtainMessage();
        msg.what = type;
        if (tproducts != null) {
            msg.arg1 = tproducts.size();
            datas.addAll(tproducts);
        } else {
            msg.arg1 = 0;
            if (type == AutoListView.REFRESH) {
                nodata.setVisibility(View.VISIBLE);
                lstv.setVisibility(View.GONE);
            }
        }
        handler.sendMessage(msg);
    }

    public Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            int rs = msg.arg1;
            switch (msg.what) {
                case AutoListView.REFRESH:
                    lstv.onRefreshComplete();
                    break;
                case AutoListView.LOAD:
                    lstv.onLoadComplete();
                    break;
            }
            lstv.setResultSize(rs);
            lstv.deferNotifyDataSetChanged();
        }

        ;
    };

    public int pn = 1;
    public int type = AutoListView.REFRESH;

    @Override
    public void onRefresh() {
        pn = 1;
        type = AutoListView.REFRESH;
        getData();
    }

    @Override
    public void onLoad() {
        type = AutoListView.LOAD;
        pn++;
        getData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
