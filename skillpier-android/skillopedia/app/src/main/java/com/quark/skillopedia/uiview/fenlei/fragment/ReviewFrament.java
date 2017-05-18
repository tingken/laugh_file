package com.quark.skillopedia.uiview.fenlei.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.CourseCommentListRequest;
import com.quark.api.auto.bean.CourseCommentListResponse;
import com.quark.api.auto.bean.ListComment;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.R;
import com.quark.skillopedia.adapter.CourseDetailCommentsAdapter;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseFragment;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2016/6/23 0023.
 * 课程详情 对应的 review片段
 */
public class ReviewFrament extends BaseFragment {
    int pn = 1;
    int page_size = 10;
    String course_id;

    @InjectView(R.id.list)
    RecyclerView list;


    CourseDetailCommentsAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View reviewView = inflater.inflate(R.layout.review_fragment_layout, container, false);
        ButterKnife.inject(this, reviewView);
        Bundle bundle = getArguments();
        course_id = bundle.getString("course_id");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        list.setLayoutManager(layoutManager);

        courseCommentListRequest();

        return reviewView;
    }

    /**
     * 课程详情评论列表
     */
    public void courseCommentListRequest() {
        CourseCommentListRequest rq = new CourseCommentListRequest();
        rq.setCourse_id(course_id);
        rq.setPn(pn);
        rq.setPage_size(100);
        showWait(true);
        QuarkApi.HttpRequestNoTS(rq, mHandlercom);
    }

    private final AsyncHttpResponseHandler mHandlercom = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, getActivity(), CourseCommentListResponse.class);
            if (kd != null) {
                CourseCommentListResponse info = (CourseCommentListResponse) kd;

                List<ListComment> datas = info.getCourseCommentListResult().getComments().getList();
                if (datas != null && datas.size() > 0) {
                    adapter = new CourseDetailCommentsAdapter(getActivity(), datas);
                    list.setAdapter(adapter);
                } else {
                    ListComment empty = new ListComment();
                    datas.add(empty);
                    adapter = new CourseDetailCommentsAdapter(getActivity(), datas);
                    list.setAdapter(adapter);
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
