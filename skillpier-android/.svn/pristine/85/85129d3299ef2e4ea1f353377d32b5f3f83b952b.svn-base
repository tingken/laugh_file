package com.quark.skillopedia.uiview;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.quark.api.auto.bean.ThirdLoginRequest;
import com.quark.api.auto.bean.ThirdLoginResponse;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.AppParam;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.api.remote.RSAsecurity;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.mainview.MainActivity;
import com.quark.skillopedia.util.Utils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

public class ThreeLoginBindActivity extends BaseActivity {

    @InjectView(R.id.left_img)
    ImageView leftImg;
    @InjectView(R.id.left)
    LinearLayout left;
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.head)
    RelativeLayout head;
    @InjectView(R.id.email_et)
    EditText emailEt;
    @InjectView(R.id.password_et)
    EditText passwordEt;
    @InjectView(R.id.login_bt)
    Button loginBt;

    String passwordStr, emailStr;
    String u_type,uid;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.threelogin);
        ButterKnife.inject(this);
        setTopTitle("");
        setBackButton();
        u_type = (String)getValue4Intent("u_type");
        uid = (String)getValue4Intent("uid");
        email = (String)getValue4Intent("email");
        emailEt.setText(email);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.login_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_bt:
                if (check()) {
                    loginRequest();
                }
                break;
        }
    }

    public boolean check() {
        emailStr = emailEt.getText().toString();
        passwordStr = passwordEt.getText().toString();
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

    public void loginRequest() {
        ThirdLoginRequest rq = new ThirdLoginRequest();
        rq.setPassword(new RSAsecurity().DESAndRSAEncrypt(passwordStr));
        rq.setEmail(emailStr);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandler);
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, ThreeLoginBindActivity.this, ThirdLoginResponse.class);
            if (kd != null) {
                ThirdLoginResponse info = (ThirdLoginResponse) kd;
                showToast(info.getMessage());
                String message = info.getMessage();
                if (info.getStatus().equals("1")) {
                    loginsuccess(info.getToken(), emailStr, info.getImage_01(), info.getAgent_level(), info.getUser_id());
                } else {
                    showToast(message);
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

    public void loginsuccess(String token, String email, String acatar, String agent_level, String user_id) {
        SharedPreferences sp = getSharedPreferences(AppParam.SHAREDPREFERENCESKEY, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("token", token);
        edit.putString("email", emailStr);
        edit.putString("avatar", acatar);
        edit.putString("agent_level", agent_level);

        edit.commit();

        new AppParam().setSharedPreferencesy(ThreeLoginBindActivity.this, "user_id", user_id);

        Intent tent2 = new Intent();
        tent2.setClass(ThreeLoginBindActivity.this, MainActivity.class);
        tent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(tent2);
        finish();
    }

}
