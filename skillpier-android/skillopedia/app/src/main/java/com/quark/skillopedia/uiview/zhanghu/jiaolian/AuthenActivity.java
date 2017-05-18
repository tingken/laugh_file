package com.quark.skillopedia.uiview.zhanghu.jiaolian;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.AuthenRequest;
import com.quark.api.auto.bean.AuthenResponse;
import com.quark.api.auto.bean.FileItem;
import com.quark.api.auto.bean.UpdatePicRequest;
import com.quark.api.auto.bean.UpdatePicResponse;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.AppParam;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.ui.widget.WheelChooseBorthdayDialog;
import com.quark.skillopedia.ui.widget.WheelChooseSingleDialog;
import com.quark.skillopedia.util.ImageUtils;
import com.quark.skillopedia.util.ImageUtilsyasuo;
import com.quark.skillopedia.util.Utils;

import org.kymjs.kjframe.http.HttpCallBack;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Administrator on 2016/5/25 0025.
 * 认证界面
 */
public class AuthenActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    String imagePath;
    int choosepic;

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
    @InjectView(R.id.cname)
    EditText cname;
    @InjectView(R.id.csex)
    ImageView csex;
    @InjectView(R.id.cborthday)
    TextView cborthday;
    @InjectView(R.id.cphone)
    EditText cphone;
    @InjectView(R.id.pic1)
    ImageView pic1;
    @InjectView(R.id.pic2)
    ImageView pic2;
    @InjectView(R.id.authen_bt)
    Button authenBt;
    @InjectView(R.id.category_tv)
    TextView categoryTv;
    @InjectView(R.id.experience_tv)
    TextView experienceTv;

    String category;
    String experience;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authen);
        ButterKnife.inject(this);
        setTopTitle("Authent ication");
        setBackButton();

        //336*223
        int sw = new AppParam().getScreenWidth(this);
        ViewGroup.LayoutParams params = pic1.getLayoutParams();
        params.height = ((sw - Utils.dip2px(this, 31)) / 2) * 223 / 336;
        pic1.setLayoutParams(params);
        ViewGroup.LayoutParams params2 = pic2.getLayoutParams();
        params2.height = ((sw - Utils.dip2px(this, 31)) / 2) * 223 / 336;
        pic2.setLayoutParams(params2);

        //美国固定电话格式
        cphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String contents = s.toString();
                int length = contents.length();
                // 1代表美国
                if (length == 4) {
                    if (contents.substring(3).equals(" ")) { // -
                        contents = contents.substring(0, 3);
                        cphone.setText(contents);
                        cphone.setSelection(contents.length());
                    } else { // +
                        contents = contents.substring(0, 3) + " " + contents.substring(3);
                        cphone.setText(contents);
                        cphone.setSelection(contents.length());
                    }
                } else if (length ==8) {
                    if (contents.substring(7).equals(" ")) { // -
                        contents = contents.substring(0, 7);
                        cphone.setText(contents);
                        cphone.setSelection(contents.length());
                    } else {// +
                        contents = contents.substring(0, 7) + " " + contents.substring(7);
                        cphone.setText(contents);
                        cphone.setSelection(contents.length());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

    }

    @OnClick(R.id.authen_bt)
    public void abt(View v) {
        if (check()) {
            authenRequest();
        }
    }

    public boolean check() {
        nickname = cname.getText().toString();
        birthday = cborthday.getText().toString();
        telephone = cphone.getText().toString();
        if (Utils.isEmpty(nickname)) {
            showToast("Please enter a name");
            return false;
        }
        if (Utils.isEmpty(birthday)) {
            showToast("Please enter your birthday");
            return false;
        }
        if (Utils.isEmpty(telephone)) {
            showToast("Please enter the phone");
            return false;
        }
        if (Utils.isEmpty(cover_ID_01)) {
            showToast("Please upload your ID photo");
            return false;
        }
        if (Utils.isEmpty(cover_ID_02)) {
            showToast("Please upload your ID photo");
            return false;
        }
        return true;
    }

    private String nickname;//昵称
    private String sex = "1";//性别：0-女，1-男，2-保密
    private String birthday;//
    private String telephone;//电话号码
    private String cover_ID_01;//身份证正面
    private String cover_ID_02;//身份证反面

    public void authenRequest() {
        AuthenRequest rq = new AuthenRequest();
        rq.setTelephone(telephone);
        rq.setBirthday(birthday);
        rq.setCover_ID_01(cover_ID_01);
        rq.setCover_ID_02(cover_ID_02);
        rq.setNickname(nickname);
        rq.setSex(sex);
        rq.setCategory_name(category);
        rq.setExperiences(experience);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandler);
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, AuthenActivity.this, AuthenResponse.class);
            if (kd != null) {
                AuthenResponse info = (AuthenResponse) kd;
                showToast(info.getMessage());
                if (info.getStatus() == 1) {
                    Intent intent = new Intent("broadfour");
                    intent.putExtra("opertion", "refresh");
                    sendBroadcast(intent);
                    if (NoSkillopediaActivity.instance != null) {
                        NoSkillopediaActivity.instance.finish();
                    }
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

    @OnClick({R.id.csex, R.id.cborthday, R.id.pic1, R.id.pic2, R.id.authen_bt, R.id.category_tv, R.id.experience_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.csex:
                if (sex.equals("0")) {
                    sex = "1";
                    csex.setImageDrawable(getResources().getDrawable(R.drawable.female));
                } else {
                    sex = "0";
                    csex.setImageDrawable(getResources().getDrawable(R.drawable.male));
                }
                break;
            case R.id.cborthday:
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(AuthenActivity.this.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                WheelChooseBorthdayDialog df = new WheelChooseBorthdayDialog();
                df.showSheetPic(AuthenActivity.this, handler, 100);
                break;
            case R.id.pic1:
                choosepic = 1;
                ImageUtils.showSheetPic(this, handlerphoto);
                break;
            case R.id.pic2:
                choosepic = 2;
                ImageUtils.showSheetPic(this, handlerphoto);
                break;
            case R.id.authen_bt:

                break;
            case R.id.category_tv:
                String[] categorys = {"sports",};
                WheelChooseSingleDialog wccd3 = new WheelChooseSingleDialog();
                wccd3.showSheetPic(AuthenActivity.this, handler, 105, categorys, "sports");
                break;
            case R.id.experience_tv:
                String[] experiences = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",};
                WheelChooseSingleDialog wccd = new WheelChooseSingleDialog();
                wccd.showSheetPic(AuthenActivity.this, handler, 106, experiences, "1");
                break;
        }
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100:
                    birthday = (String) msg.obj;
                    Date date = null;
                    try {
                        date = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    // 将date转化为String
                    String s = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH).format(date);
                    cborthday.setText(s);
                    break;
                case 105:
                    String c1 = (String) msg.obj;
                    category = c1;
                    categoryTv.setText(c1);
                    break;
                case 106:
                    String c2 = (String) msg.obj;
                    experience = c2;
                    experienceTv.setText(experience);
                    break;

                default:
                    break;
            }
            super.handleMessage(msg);
        }

        ;
    };

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode != 0) {
//            switch (requestCode) {
//                case ImageUtils.IMAGE_REQUEST_CODE:
//                    ImageUtils.startPhotoZoom(data.getData(), 375, 164, this);
//                    break;
//                case ImageUtils.CAMERA_REQUEST_CODE:
//                    if (Utils.hasSdcard()) {
//                        File tempFile = new File(Environment.getExternalStorageDirectory() + "/" + ImageUtils.IMAGE_FILE_NAME);
//                        Log.e("erros", "应用路径为;" + tempFile.getPath());
//                        ImageUtils.startPhotoZoom(Uri.fromFile(tempFile), 375, 164, this);
//                    } else {
//                        Toast mToast = Toast.makeText(this, "未找到存儲卡，無法存儲圖片！", Toast.LENGTH_LONG);
//                        mToast.setGravity(Gravity.CENTER, 0, 0);
//                        mToast.show();
//                    }
//                    break;
//                case ImageUtils.RESULT_REQUEST_CODE:
//                    if (data != null) {
//                        if (choosepic == 1) {
//                            imagePath = ImageUtils.getImageToView(data, this, pic1);
//                        } else {
//                            imagePath = ImageUtils.getImageToView(data, this, pic2);
//                        }
//                        updatePicRequest();
//                    }
//                    break;
//            }
//        }
//    }


    /*=========================拍照===========================*/
    //=================通过判断权限启动===============
    private Handler handlerphoto = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    startTakePhotoByPermissions(ImageUtils.CAMERA_REQUEST_CODE);
                    break;
                case 2:
                    startTakePhotoByPermissions(ImageUtils.IMAGE_REQUEST_CODE);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }

        ;
    };

    private void startTakePhotoByPermissions(int type) {
        if (type == ImageUtils.CAMERA_REQUEST_CODE) {
            String[] perms = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if (EasyPermissions.hasPermissions(this, perms)) {
                try {
                    ImageUtils.startTakePhoto(this);
                } catch (Exception e) {
                    Toast.makeText(this, "相机无法完成初始化,请正确授权", Toast.LENGTH_LONG).show();
                }
            } else {
                EasyPermissions.requestPermissions(this, "请求获取相机权限", ImageUtils.CAMERA_REQUEST_CODE, perms);
            }
        } else {
            String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE};
            if (EasyPermissions.hasPermissions(this, perms)) {
                try {
                    ImageUtils.startImagePick(this);
                } catch (Exception e) {
                    Toast.makeText(this, "无法读取图片,请正确授权", Toast.LENGTH_LONG).show();
                }
            } else {
                EasyPermissions.requestPermissions(this, "请求读取图片的权限", ImageUtils.IMAGE_REQUEST_CODE, perms);
            }
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        try {
            if (requestCode == ImageUtils.CAMERA_REQUEST_CODE) {
                ImageUtils.startTakePhoto(this);
            } else {
                ImageUtils.startImagePick(this);
            }
        } catch (Exception e) {
            Toast.makeText(this, "授权不正确,操作无法进行", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Toast.makeText(this, "授权不正确,操作无法进行", Toast.LENGTH_LONG).show();
    }
//=========判断权限启动======================

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != 0) {
            switch (requestCode) {
                case ImageUtils.IMAGE_REQUEST_CODE:
                    if (choosepic == 1) {
                        String imagePath1 = ImageUtils.getImageToViewyuantu(data.getData(), this, pic1);
                        uploadNewPhoto(imagePath1);
                    } else {
                        String imagePath1 = ImageUtils.getImageToViewyuantu(data.getData(), this, pic2);
                        uploadNewPhoto(imagePath1);
                    }

                    break;
                case ImageUtils.CAMERA_REQUEST_CODE:
                    if (Utils.hasSdcard()) {
                        if (choosepic == 1) {
                            String pa = ImageUtils.getImageToViewyuantu2(ImageUtils.cameraUri, this, pic1);
                            uploadNewPhoto(pa);
                        } else {
                            String pa = ImageUtils.getImageToViewyuantu2(ImageUtils.cameraUri, this, pic2);
                            uploadNewPhoto(pa);
                        }
                    } else {
                        Toast mToast = Toast.makeText(this, "未找到存储卡，无法存储照片！", Toast.LENGTH_LONG);
                        mToast.setGravity(Gravity.CENTER, 0, 0);
                        mToast.show();
                    }
                    break;
            }
        }
    }

    /**
     * 上传新照片
     */
    //上傳圖片
    public void uploadNewPhoto(String imagePath1) {
        File file = null;
        try {
            file = ImageUtilsyasuo.compressAndGenImage(imagePath1, 200, false);
            List<FileItem> ls = new LinkedList<FileItem>();
            FileItem f = new FileItem("image_01", file);
            ls.add(f);
            UpdatePicRequest rq = new UpdatePicRequest();
            String ds = Math.random() + "";
            rq.setPid(ds + "");   //消除缓存
            showWait(true);
            QuarkApi.HttpuploadFile(rq, ls, httpCallBack);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    HttpCallBack httpCallBack = new HttpCallBack() {
        @Override
        public void onSuccess(String t) {
            super.onSuccess(t);
            showWait(false);
            Log.e("error", "==" + t);
            try {
                UpdatePicResponse info = new UpdatePicResponse(t);
                if (choosepic == 1) {
                    cover_ID_01 = info.getFileName();
                } else {
                    cover_ID_02 = info.getFileName();
                }
                showToast(info.getMessage());
            } catch (Exception e) {
                Log.e("error", "數據解析出錯");
            }
            showWait(false);
        }

        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
            AppContext.showToast("Network error" + arg0);

            showWait(false);
        }
    };
    /*=================图片end==========================*/
}
