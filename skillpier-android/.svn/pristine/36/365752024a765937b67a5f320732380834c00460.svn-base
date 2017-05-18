package com.quark.skillopedia.uiview.zhanghu.zhuye;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.UpdateNicknameRequest;
import com.quark.api.auto.bean.UpdateNicknameResponse;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.util.Utils;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * Created by pan on 2016/7/6 0006.
 * >#
 * >#改名
 */
public class RenameActivity extends BaseActivity {

    @InjectView(R.id.name)
    EditText name;
    @InjectView(R.id.left)
    LinearLayout left;
    @InjectView(R.id.ok_bt)
    Button okBt;
    String namestr;
    String nameend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rename);
        ButterKnife.inject(this);
        left.setVisibility(View.VISIBLE);
        setBackButton();

        namestr = (String)getValue4Intent("name");
        if (!Utils.isEmpty(namestr)){
            name.setText(namestr);
        }
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
                           public void run() {
                               InputMethodManager inputManager = (InputMethodManager) name.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                               inputManager.showSoftInput(name, 0);
                           }
                       },
                500);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.ok_bt)
    public void onClick() {

         nameend = name.getText().toString();
        if (Utils.isEmpty(nameend)){//
            showToast("Please enter a name");
        }else{
            getData();
        }

    }

     public void getData() {
         UpdateNicknameRequest rq = new UpdateNicknameRequest();
             rq.setNickname(nameend);
             showWait(true);
             QuarkApi.HttpRequest(rq, mHandler);
         }

      private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
          @Override
          public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
              Object kd = ApiResponse.get(arg2, RenameActivity.this, UpdateNicknameResponse.class);
              if (kd!=null){
                  UpdateNicknameResponse info = (UpdateNicknameResponse)kd;
                  //showToast(info.getMessage());
                  if (info.getStatus() == 1) {
                      Intent intent = new Intent();
                      intent.putExtra("name", nameend);
                      setResult(100, intent);
                      Intent in = new Intent("broadfour");
                      in.putExtra("opertion", "refresh");
                      sendBroadcast(in);
                      finish();
                  }else {

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
