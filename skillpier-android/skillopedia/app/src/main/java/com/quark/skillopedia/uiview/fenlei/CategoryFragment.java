package com.quark.skillopedia.uiview.fenlei;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.CatetoryCourseListRequest;
import com.quark.api.auto.bean.CatetoryCourseListResponse;
import com.quark.api.auto.bean.ListCourse;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.AppParam;
import com.quark.skillopedia.R;
import com.quark.skillopedia.adapter.SecondCategoryTwoAdapter;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.ui.widget.AutoListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cz.msebera.android.httpclient.Header;
/**
 * category页面的右侧片段显示
 * @author pan
 * @time 2016/8/22 0022 下午 5:31
 */
public class CategoryFragment extends Fragment implements AutoListView.OnRefreshListener, AutoListView.OnLoadListener {
    SecondCategoryTwoAdapter adapter;
    String category_id;
    ArrayList<ListCourse> datas = new ArrayList<>();
    View twoLayout;
    int pich;

    @InjectView(R.id.list)
    AutoListView lstv;
    @InjectView(R.id.nodata)
    TextView nodata;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        twoLayout = inflater.inflate(R.layout.category_fragment, container, false);
        ButterKnife.inject(this, twoLayout);
        category_id = getArguments().getString("category_id");
        int sw = new AppParam().getScreenWidth(getActivity());
//         1.3:3 4.3
        double rsl = sw;
        int realsw = (int)(rsl*(3/4.3));

        pich = (int) ((realsw * 247) / 501.0);
        getData();
        adapter = new SecondCategoryTwoAdapter(getActivity(), datas,pich);
        lstv.setOnRefreshListener(this);
        lstv.setOnLoadListener(this);
        lstv.setAdapter(adapter);
        lstv.setFocusable(false);
        lstv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                position = position - 1;
                if (position<datas.size()){
                    Intent intent = new Intent(getActivity(), CourseDetailActivity.class);
                    intent.putExtra("course_id",datas.get(position).getCourse_id()+"");
                    startActivity(intent);
                }
            }
        });
        return twoLayout;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
    public void getData() {
        CatetoryCourseListRequest rq = new CatetoryCourseListRequest();
        rq.setCatetory_id(category_id);
        rq.setPage_size(10);
        rq.setPn(pn);
        rq.setLatitude(new AppParam().getLat(getActivity()));
        rq.setLongitude(new AppParam().getLng(getActivity()));
//        showWait(true);
        QuarkApi.HttpRequestNoTS(rq, mHandler);
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, getActivity(), CatetoryCourseListResponse.class);
            if (kd != null) {
                CatetoryCourseListResponse info = (CatetoryCourseListResponse) kd;
                dealdatas(info);
            }
//            showWait(false);
        }

        @Override
        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
            AppContext.showToast("Network error" + arg0);
//            showWait(false);
        }
    };

    public void dealdatas(CatetoryCourseListResponse info) {
        if (type == AutoListView.REFRESH) {
            datas.clear();
        }
        List<ListCourse> tproducts = info.getCatetoryCourseListResult().getCourses().getList();


        Message msg = new Message();
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

}
