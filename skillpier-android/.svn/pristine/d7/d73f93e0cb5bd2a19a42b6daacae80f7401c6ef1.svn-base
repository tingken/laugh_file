package com.quark.skillopedia.uiview.dingdan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.joooonho.SelectableRoundedImageView;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.CommentCourseInfoRequest;
import com.quark.api.auto.bean.CommentCourseInfoResponse;
import com.quark.api.auto.bean.CommentRequest;
import com.quark.api.auto.bean.CommentResponse;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.AppParam;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiHttpClient;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.util.Utils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * @author pan
 *         created at 2016/5/30 0030
 * @评论
 */
public class OrderCommentActivity extends BaseActivity {

    @InjectView(R.id.main_left)
    LinearLayout mainLeft;
    @InjectView(R.id.iamge_iv)
    SelectableRoundedImageView iamgeIv;
    @InjectView(R.id.text1)
    TextView text1;
    @InjectView(R.id.text3)
    TextView text3;
    @InjectView(R.id.ratingBar)
    RatingBar ratingBar;
    @InjectView(R.id.grade_tv)
    TextView gradeTv;
    @InjectView(R.id.text4)
    TextView text4;
    @InjectView(R.id.miles_tv)
    TextView milesTv;
    @InjectView(R.id.football_tv)
    TextView footballTv;
    @InjectView(R.id.money_tv)
    TextView moneyTv;
    @InjectView(R.id.comment_bt)
    Button commentBt;
    @InjectView(R.id.ratingBar1)
    RatingBar ratingBar1;
    @InjectView(R.id.ratingBar2)
    RatingBar ratingBar2;
    @InjectView(R.id.ratingBar3)
    RatingBar ratingBar3;
    @InjectView(R.id.edit_comment)
    EditText editComment;
    String coure_id;
    String orders_id;
    String public_course_user_id;//发布课程用户Id
    String note;//评语，消息
    String pro_skill;//专业技能
    String teaching_environment;//教学环境
    String teaching_attitude;//教学态度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordercomment);
        ButterKnife.inject(this);
        setTopTitle("Order comment");
        coure_id = (String) getValue4Intent("coure_id");
        orders_id = (String) getValue4Intent("orders_id");
        public_course_user_id = (String) getValue4Intent("user_id");
        commentCourseInfoRequest();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.main_left)
    public void Click() {
        finish();
    }

    @OnClick(R.id.comment_bt)
    public void onClick() {
        note = editComment.getText().toString();
        pro_skill = ratingBar1.getRating()+"";
        teaching_environment = ratingBar2.getRating()+"";
        teaching_attitude = ratingBar3.getRating()+"";
        if (Utils.isEmpty(note)) {
            showToast("Please fill in the comments");
        } else {
            commentRequest();
        }
    }

    /**
     * 课程信息
     */
    public void commentCourseInfoRequest() {
        CommentCourseInfoRequest rq = new CommentCourseInfoRequest();
        rq.setOrders_id(orders_id);
        rq.setLatitude(new AppParam().getLat(this));
        rq.setLongitude(new AppParam().getLng(this));
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlercom);
    }

    private final AsyncHttpResponseHandler mHandlercom = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, OrderCommentActivity.this, CommentCourseInfoResponse.class);
            if (kd != null) {
                CommentCourseInfoResponse info = (CommentCourseInfoResponse) kd;

                ApiHttpClient.loadImage(info.getCommentCourseInfo().getCourse().getCourse_image_01(), iamgeIv, R.drawable.example_7);
                text1.setText(info.getCommentCourseInfo().getCourse().getTitle());
                text3.setText("BY " + info.getCommentCourseInfo().getCourse().getCourse_nickname());
//                gradeTv.setText(info.getCommentCourseInfo().getCourse().getTotal_score());
                //评分 强转
                try {
                    float grade = Float.parseFloat(info.getCommentCourseInfo().getCourse().getTotal_score());
                    ratingBar.setRating(grade);

                    int gradeint = (int)grade;
//                    gradeTv.setText(gradeint + " review");
                    gradeTv.setText(info.getCommentCourseInfo().getCourse().getTotal_coment_num() + " review");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                milesTv.setText(info.getCommentCourseInfo().getCourse().getDistance() + "KM");
                moneyTv.setText("¥" + info.getCommentCourseInfo().getTotal_session_rate());
                text4.setVisibility(View.VISIBLE);
                text4.setText("x" + info.getCommentCourseInfo().getBuy_amount());
                footballTv.setText(info.getCommentCourseInfo().getCourse().getCategory_02_name());

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

    /**
     * 评论
     */
    public void commentRequest() {
        CommentRequest rq = new CommentRequest();
        rq.setCourse_id(coure_id);
        rq.setOrders_id(orders_id);
        rq.setPublic_course_user_id(public_course_user_id);
        rq.setNote(note);
        rq.setPro_skill(pro_skill);
        rq.setTeaching_attitude(teaching_attitude);
        rq.setTeaching_environment(teaching_environment);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandler);
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, OrderCommentActivity.this, CommentResponse.class);
            if (kd != null) {
                CommentResponse info = (CommentResponse) kd;
                showToast(info.getMessage());
                if (info.getStatus()==1){
                    //刷新全部订单 已完成订单
                    Intent allIntent = new Intent("orders_all");
                    allIntent.putExtra("option", "refresh");
                    sendBroadcast(allIntent);
                    //
                    Intent comIntent = new Intent("orders_company");
                    comIntent.putExtra("option", "refresh");
                    sendBroadcast(comIntent);
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
