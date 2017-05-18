package com.quark.skillopedia.uiview;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.SendForgetEmailRequest;
import com.quark.api.auto.bean.SendForgetEmailResponse;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.util.Utils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2016/5/20 0020.
 * 忘记密码界面
 */
public class ForgotActivity extends BaseActivity {

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
    @InjectView(R.id.email_et)
    EditText emailEt;
    @InjectView(R.id.resetemail_tv)
    TextView resetemailTv;
    @InjectView(R.id.sendemail_bt)
    Button sendemailBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_forgot);
        ButterKnife.inject(this);
        setTopTitle("Forget password");
        setBackButton();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.sendemail_bt)
    public void sd(View v) {
        String email = emailEt.getText().toString();
        if (Utils.isEmpty(email)){
            showToast(getString(R.string.tip_no_email));
        }else{
            SendForgetEmailRequest rq = new SendForgetEmailRequest();
            rq.setEmail(email);
            showWait(true);
            QuarkApi.HttpRequest(rq, mHandler);
        }
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, ForgotActivity.this, SendForgetEmailResponse.class);
            if (kd != null) {
                SendForgetEmailResponse info = (SendForgetEmailResponse) kd;
                showToast(info.getMessage());
                if (info.getStatus()==1){
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
