package com.quark.skillopedia.uiview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.IsThirdLoginRelatedRequest;
import com.quark.api.auto.bean.IsThirdLoginRelatedResponse;
import com.quark.api.auto.bean.LoginRequest;
import com.quark.api.auto.bean.LoginResponse;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.AppParam;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiHttpClient;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.api.remote.RSAsecurity;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.mainview.MainActivity;
import com.quark.skillopedia.util.TLog;
import com.quark.skillopedia.util.Utils;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import io.fabric.sdk.android.Fabric;

/**
 * Created by Administrator on 2016/5/20 0020.
 * 登录界面
 */
public class NewLoginActivity extends BaseActivity {
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
    @InjectView(R.id.dsflogin_tv)
    TextView dsfloginTv;
    @InjectView(R.id.facebook_ly)
    LinearLayout facebookLy;
    @InjectView(R.id.twiiter_ly)
    LinearLayout twiiterLy;
    @InjectView(R.id.fress_tv)
    TextView fressTv;
    @InjectView(R.id.forgot_tv)
    TextView forgotTv;

    CallbackManager callbackManager;

    String utype = "2", name, profile_image_url, uidtemp;//获取到的uid;//

    LoginManager loginManager;
    TwitterLoginButton loginButton;

    String isfromException; //yes 异常情况跳转过来的

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isfromException = (String)getValue4Intent("isfromException");
//twitter
        TwitterAuthConfig authConfig = new TwitterAuthConfig("I33GgFCHbyLqYOyceeYbkUbRG", "Qsk4Ts0InarjEU9r81A7zbz0GJbBWpPN7uvFkq0rhYWIDzjUnT");
        Fabric.with(this, new Twitter(authConfig));

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_newlogin);
        ButterKnife.inject(this);

        String emailstr = new AppParam().getEmail(this);
        if (!Utils.isEmpty(emailstr)){
            emailEt.setText(emailstr);
        }

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        //facebook 登录回调
        loginManager = LoginManager.getInstance();
        loginManager.logOut();//先执行一次退出操作
        loginManager.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        uidtemp = loginResult.getAccessToken().getUserId();
                        TLog.error("Success=" + uidtemp);

                        /* make the API call */
                        new GraphRequest(
                                AccessToken.getCurrentAccessToken(),
                                uidtemp+"?fields=id,name,picture,email",
                                null,
                                HttpMethod.GET,
                                new GraphRequest.Callback() {
                                    public void onCompleted(GraphResponse response) {
            /* handle the result */
                                        TLog.error("Success=" + response.getJSONObject().toString());
                                        JSONObject jsonstr = response.getJSONObject();
                                        try {
                                            String name = jsonstr.getString("name");
                                            JSONObject picture = jsonstr.getJSONObject("picture");
                                            JSONObject data = picture.getJSONObject("data");
                                            String url = data.getString("url");
                                            if (jsonstr.has("email")){
                                                String email = jsonstr.getString("email");
                                                isThirdLoginRelatedRequest(email,name,url);
                                            }else {
                                                showToast("Sorry facebook email address not found. Login failed");
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                        ).executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        TLog.error("onCancel");
//                        showToast("Cancel login");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        TLog.error("onError" + exception.getMessage().toString());
//                        showToast("Login failed");
                    }
                });

    /*======================twistter=======================*/
        loginButton = new TwitterLoginButton(this);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                TwitterSession session = result.data;
                uidtemp = session.getUserId() + "";
                name = session.getUserName();
                String msg = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")";
                TLog.error("Success=" + msg);

                getTwitterinfo();
                TwitterAuthClient authClient = new TwitterAuthClient();
                authClient.requestEmail(session, new Callback<String>() {
                    @Override
                    public void success(Result<String> result) {
                        // Do something with the result, which provides the email address
                        TLog.error("Success=" + result.toString());
                        String email = result.data.toString();
                        String icon_url = "https://twitter.com/"+name+"/profile_image?size=original";

                        isThirdLoginRelatedRequest(email,name,icon_url);
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        // Do something on failure
                        TLog.error("Success=" + exception.getMessage().toString());
                        showToast("Sorry twitter email address not found. Login failed");
                    }
                });
            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
                showToast("Login failed");
            }
        });

     /*======================twistter=======================*/
//清空登录信息
        new AppParam().setSharedPreferencesy(this, "token", "");
        new AppParam().setSharedPreferencesy(this, "user_id", "");
//清空登录信息

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (utype.equals("1")) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        } else {
            loginButton.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void getTwitterinfo(){
        ApiHttpClient.get("https://api.twitter.com/1.1/users/show.json?user_id="+uidtemp, twittermHandler);
    }


    private final AsyncHttpResponseHandler twittermHandler = new AsyncHttpResponseHandler() {
      @Override
      public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
          try {
              String ds = new String(arg2, "UTF-8");
              TLog.error("返回；"+ds);
          } catch (UnsupportedEncodingException e) {
              e.printStackTrace();
          }
          showWait(false);
      }
      @Override
      public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
          AppContext.showToast("Network error" + arg0);
          showWait(false);
      }
    };

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.login_bt, R.id.dsflogin_tv, R.id.facebook_ly, R.id.fress_tv, R.id.forgot_tv, R.id.left,R.id.twiiter_ly})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left:
                if ("yes".equals(isfromException)){
                    Intent tent2 = new Intent();
                    tent2.setClass(NewLoginActivity.this, MainActivity.class);
                    tent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(tent2);
                    finish();
                }else{
                    finish();
                }
                break;
            case R.id.login_bt:
                if (check()) {
                    loginRequest("", "");
                }
                break;
            case R.id.dsflogin_tv:
                break;
            case R.id.facebook_ly:
                utype = "1";
                //使用button会有记录登录状态
//                LoginButton facebookLoginButton = new LoginButton(this);
//                facebookLoginButton.performClick();

                //请求facebook api在registerCallback中处理
                List<String> permissions = new ArrayList<>();
                permissions.add("email");
                loginManager.logInWithReadPermissions(this, permissions);
                break;
            case R.id.twiiter_ly:
                utype = "2";
                loginButton.performClick();
                break;
            case R.id.fress_tv:
                Intent intent = new Intent(NewLoginActivity.this, FreesignActivity.class);
                startActivity(intent);
                break;
            case R.id.forgot_tv:
                Intent i = new Intent(NewLoginActivity.this, ForgotActivity.class);
                startActivity(i);
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

    String passwordStr, emailStr;

    public void loginRequest(String fuid, String tuid) {
        LoginRequest rq = new LoginRequest();

        if (!Utils.isEmpty(fuid)) {
            rq.setF_uid(fuid);
        } else if (!Utils.isEmpty(tuid)) {
            rq.setT_uid(tuid);
        } else {
            rq.setPassword(new RSAsecurity().DESAndRSAEncrypt(passwordStr));
            rq.setEmail(emailStr);
        }
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandler);
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, NewLoginActivity.this, LoginResponse.class);
            if (kd != null) {
                LoginResponse info = (LoginResponse) kd;
                showToast(info.getMessage());
                String message = info.getMessage();
                if (info.getStatus() == 1) {
                    loginsuccess(info.getToken(), emailStr, info.getImage_01(), info.getAgent_level(), info.getUser_id());
                }
                else {
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

        new AppParam().setSharedPreferencesy(NewLoginActivity.this, "user_id", user_id);
        if ("yes".equals(isfromException)){
            Intent tent2 = new Intent();
            tent2.setClass(NewLoginActivity.this, MainActivity.class);
            tent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(tent2);
            finish();
        }else{
            //刷新基本信息
            Intent intent = new Intent("broadfour");
            intent.putExtra("opertion","refresh");
            sendBroadcast(intent);
            finish();
        }
    }
//======================等三方登录=====================
    /**
     * 判断是否关联
     *
     * @author leon
     * @time 2016/8/8 0008 下午 3:37
     */
    String loginemail;
    public void isThirdLoginRelatedRequest(String email,String name,String icon_url) {
        loginemail = email;
        IsThirdLoginRelatedRequest rq = new IsThirdLoginRelatedRequest();
        rq.setEmail(email); //1-facebook登录，2-twitter登录
        rq.setNickname(name);
        rq.setIcon_url(icon_url);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlerisband);
    }

    private final AsyncHttpResponseHandler mHandlerisband = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, NewLoginActivity.this, IsThirdLoginRelatedResponse.class);
            if (kd != null) {
                IsThirdLoginRelatedResponse info = (IsThirdLoginRelatedResponse) kd;
                showToast(info.getMessage());
                if (info.getStatus() == 1) { //1-已关联，并登陆,2-uid获取失败,3-您的账号因异常，4-未关联
                    loginsuccess(info.getToken(), emailStr, info.getImage_01(), info.getAgent_level(), info.getUser_id());
                } else if (info.getStatus() == 4) {
                    Bundle bundle = new Bundle();
                    bundle.putString("uid", uidtemp);
                    bundle.putString("u_type", utype);
                    bundle.putString("email", loginemail);
//                    bundle.putString("name", name);
//                    bundle.putString("profile_image_url", profile_image_url);

                    startActivityByClass(ThreeLoginBindActivity.class, bundle);
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
    //======================等三方登录end=====================

    //如果是登录异常的话返回回到首页
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) { //监控/拦截/屏蔽返回键
            if ("yes".equals(isfromException)){
                Intent tent2 = new Intent();
                tent2.setClass(this, MainActivity.class);
                tent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(tent2);
                finish();
            }else{
                finish();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

}
