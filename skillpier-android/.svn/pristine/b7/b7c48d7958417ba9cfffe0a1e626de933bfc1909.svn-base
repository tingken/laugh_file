//package com.quark.skillopedia.ui;
//
//import android.annotation.SuppressLint;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.SharedPreferences.Editor;
//import android.os.Bundle;
//import android.text.Editable;
//import android.text.Selection;
//import android.text.Spannable;
//import android.text.TextWatcher;
//import android.text.method.HideReturnsTransformationMethod;
//import android.text.method.PasswordTransformationMethod;
//import android.view.KeyEvent;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//
//import com.quark.skillopedia.AppContext;
//import com.quark.skillopedia.AppParam;
//import com.quark.skillopedia.R;
//import com.quark.skillopedia.base.BaseActivity;
//import com.quark.skillopedia.mainview.MainActivity;
//
//import butterknife.ButterKnife;
//import butterknife.InjectView;
//import butterknife.OnClick;
//
//@SuppressLint("ResourceAsColor")
//public class LoginActivity extends BaseActivity {
//    public static LoginActivity intance;
//    private String loginType;
//
//    @InjectView(R.id.phone)    //手机号
//            EditText edit_phone;
//    @InjectView(R.id.edit)//密码
//            EditText edit_pwd;
//    @InjectView(R.id.normol_login)//不能登录
//            Button normol_login;
//    @InjectView(R.id.active_login)//能登录
//            Button active_login;
//    String phone_str;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//        setContentView(R.layout.login_layout);
//        ButterKnife.inject(this);
//        setTopTitle("登录");
//        setBackButton();
//
//        intance = this;
//        this.edit_phone.addTextChangedListener(new EditChangedListener());
//        this.edit_pwd.addTextChangedListener(new EditChangedListener());
//
//        SharedPreferences sp = getSharedPreferences(AppParam.SHAREDPREFERENCESKEY, MODE_PRIVATE);
//        if (sp.contains("telephone")) {
//            String tel = sp.getString("telephone", "");
//            edit_phone.setText(tel);
//        }
//        Editor edit = sp.edit();
//        edit.putString("token", "");
//        edit.commit();
////        Intent tent = new Intent("updatefthreBroadCast");
////        sendBroadcast(tent);
//    }
//
//    @OnClick(R.id.active_login)
//    public void getData() {
//
//    }
//
//
//
//    public void dongjie(String message) {
//        final AlertDialog dlg = new AlertDialog.Builder(this).create();
//        dlg.setTitle("提示");
//        dlg.setMessage(message);
//        dlg.setButton(DialogInterface.BUTTON_POSITIVE, "联系客服", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dlg.cancel();
////                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+AppContext.SERVERPHONE));
////                startActivity(intent);
//            }
//        });
//        dlg.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dlg.cancel();
//            }
//        });
//        dlg.show();
//    }
//
//
//    @Override
//    public void initView() {
//
//    }
//
//    @Override
//    public void initData() {
//
//    }
//
//    /**
//     * 时间监听   edit_pwd
//     */
//    class EditChangedListener implements TextWatcher {
//
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//            // TODO Auto-generated method stub
//            String phone_str1 = edit_phone.getText().toString();
//            String pss1 = edit_pwd.getText().toString();
//            if (phone_str1.length() > 0 && pss1.length() > 0) {
//                normol_login.setVisibility(View.GONE);
//                active_login.setVisibility(View.VISIBLE);
//            } else {
//                normol_login.setVisibility(View.VISIBLE);
//                active_login.setVisibility(View.GONE);
//            }
//        }
//    };
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        SharedPreferences sp = getSharedPreferences(AppParam.SHAREDPREFERENCESKEY, MODE_PRIVATE);
//        if (sp.contains("telephone")) {
//            String tel = sp.getString("telephone", "");
//            edit_phone.setText(tel);
//        }
//    }
//
//    @InjectView(R.id.show)
//    ImageView showpic;
//    boolean showpssword = false;
//
//    @OnClick(R.id.show)
//    public void show(View v) {
//        if (!showpssword) {
//            // 设置为明文显示
//            showpssword = true;
//            edit_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//            showpic.setImageDrawable(getResources().getDrawable(R.drawable.on));
//        } else {
//            // 设置为秘闻显示
//            showpssword = false;
//            edit_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
//            showpic.setImageDrawable(getResources().getDrawable(R.drawable.pwd_mark));
//        }
//        // 切换后将EditText光标置于末尾
//        CharSequence charSequence = edit_pwd.getText();
//        if (charSequence instanceof Spannable) {
//            Spannable spanText = (Spannable) charSequence;
//            Selection.setSelection(spanText, charSequence.length());
//        }
//    }
//
////405 到首页
//    public void setBackButton() {
//        LinearLayout back_lay = (LinearLayout) findViewById(R.id.left);
//        back_lay.setVisibility(View.VISIBLE);
//        back_lay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (AppContext.isException){
//                    Intent tent2 = new Intent();
//                    tent2.setClass(LoginActivity.this, MainActivity.class);
//                    tent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(tent2);
//                }
//                finish();
//            }
//        });
//    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (AppContext.isException){
//                Intent tent2 = new Intent();
//                tent2.setClass(LoginActivity.this, MainActivity.class);
//                tent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(tent2);
//            }
//            finish();
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//}
//
//
//
//
//
//
