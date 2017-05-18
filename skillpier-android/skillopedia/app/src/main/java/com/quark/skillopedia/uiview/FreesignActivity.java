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
import com.quark.api.auto.bean.CheckRegisterEmailRequest;
import com.quark.api.auto.bean.RegistTelRequest;
import com.quark.api.auto.bean.RegistTelResponse;
import com.quark.api.auto.bean.checkEmailResponse;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.api.remote.RSAsecurity;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.util.Utils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2016/5/20 0020.
 * 注册界面
 */
public class FreesignActivity extends BaseActivity {

    @InjectView(R.id.sign_bt)
    Button signBt;
    @InjectView(R.id.pwd_bt)
    ImageView pwdBt;
    @InjectView(R.id.digitpwd_et)
    EditText digitpwdEt;
    @InjectView(R.id.left_img)
    ImageView leftImg;
    @InjectView(R.id.left)
    LinearLayout left;
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.head)
    RelativeLayout head;
    @InjectView(R.id.name_et)
    EditText nameEt;
    @InjectView(R.id.account_email_et)
    EditText accountEmailEt;
    String emailStr, passwordStr, nicknameStr;

    String uid,utype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_freesign);
        ButterKnife.inject(this);
        setBackButton();

        uid = (String)getValue4Intent("uid");
        utype = (String)getValue4Intent("utype");
    }

    public boolean check() {
        nicknameStr = nameEt.getText().toString();
        emailStr = accountEmailEt.getText().toString();
        passwordStr = digitpwdEt.getText().toString();
        if (Utils.isEmpty(nicknameStr)) {
            showToast(getString(R.string.tip_no_name));
            return false;
        }
        if (Utils.isEmpty(emailStr)) {
            showToast(getString(R.string.tip_no_email));
            return false;
        }
        if (Utils.isEmpty(passwordStr)) {
            showToast(getString(R.string.tip_no_password));
            return false;
        }
        return true;
    }

    public void checkRegisterEmailRequest() {
        CheckRegisterEmailRequest rq = new CheckRegisterEmailRequest();
        rq.setEmail(emailStr);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlercheck);
    }

    private final AsyncHttpResponseHandler mHandlercheck = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, FreesignActivity.this, checkEmailResponse.class);
            if (kd != null) {
                checkEmailResponse info = (checkEmailResponse) kd;
                if (info.getStatus() == 1) {
                    registTelRequest();
                } else {
                    showToast(info.getMessage());
                }
            }
            showWait(false);
        }

        @Override
        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
            AppContext.showToast("Network error" + arg0);
            showWait(false);
        }
    };

    public void registTelRequest() {
        RegistTelRequest rq = new RegistTelRequest();
        rq.setEmail(emailStr);
        rq.setPassword(new RSAsecurity().DESAndRSAEncrypt(passwordStr));
        rq.setNickname(nicknameStr);
        if (!Utils.isEmpty(uid)){
            rq.setUid(uid);
        }
        if (!Utils.isEmpty(utype)){
            rq.setU_type(utype);
        }
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandler);
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, FreesignActivity.this, RegistTelResponse.class);
            if (kd != null) {
                RegistTelResponse info = (RegistTelResponse) kd;
                showToast(info.getMessage());
                if(ThreeLoginOneActivity.instanse!=null){
                    ThreeLoginOneActivity.instanse.finish();
                }
                if (info.getStatus() == 2) {//2是操作成 未审核
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

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    boolean showpssword = false;
    @OnClick({R.id.pwd_bt, R.id.sign_bt})
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.pwd_bt:
//                if (!showpssword) {
//                    // 设置为明文显示
//                    showpssword = true;
//                    digitpwdEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                    pwdBt.setImageDrawable(getResources().getDrawable(R.drawable.eye_2));
//                } else {
//                    // 设置为秘闻显示
//                    showpssword = false;
//                    digitpwdEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                    pwdBt.setImageDrawable(getResources().getDrawable(R.drawable.eye_1));
//                }
//                // 切换后将EditText光标置于末尾
//                CharSequence charSequence = digitpwdEt.getText();
//                if (charSequence instanceof Spannable) {
//                    Spannable spanText = (Spannable) charSequence;
//                    Selection.setSelection(spanText, charSequence.length());
//                }
//                break;
            case R.id.sign_bt:
                if (check()) {
                    if (digitpwdEt.getText().length() < 6){
                        showToast("enter your password, at least 6");
                    }else {
                        checkRegisterEmailRequest();
                    }

                }
                break;
        }
    }


}
