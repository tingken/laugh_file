package com.quark.skillopedia.uiview.zhanghu.zhuye;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.CommentReplyRequest;
import com.quark.api.auto.bean.CommentReplyResponse;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.uiview.zhanghu.MessageActivity;
import com.quark.skillopedia.util.Utils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2016/5/25 0025.
 * 编辑界面
 */
public class EditActivity extends BaseActivity {

    @InjectView(R.id.edit_content)
    EditText editContent;
    @InjectView(R.id.ok_bt)
    Button okBt;
    @InjectView(R.id.left)
    LinearLayout left;
    String comment_id;
    String content;//回复内容

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.inject(this);
        left.setVisibility(View.VISIBLE);
        setBackButton();
        setTopTitle("Replay");

        comment_id = (String) getValue4Intent("comment_id");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.ok_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ok_bt:
                content = editContent.getText().toString();
                if (Utils.isEmpty(content)){
                    showToast("Please enter comments");
                }else {
                    commentReplyRequest();
                }
//                startActivityByClass(MessageActivity.class);
                break;
        }
    }

    /**
     * 回复评论
     */
    public void commentReplyRequest() {
        CommentReplyRequest rq = new CommentReplyRequest();
        rq.setComment_id(comment_id);
        rq.setContent(content);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlerrep);
    }

    private final AsyncHttpResponseHandler mHandlerrep = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, EditActivity.this, CommentReplyResponse.class);
            if (kd != null) {
                CommentReplyResponse info = (CommentReplyResponse) kd;

                if(info.getStatus()==1){
                    Intent i = new Intent(EditActivity.this, MessageActivity.class);
                    startActivity(i);
                    finish();
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
