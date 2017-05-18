package com.quark.skillopedia.uiview.zhanghu.jiaolian;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.AddCourseCertificationResponse;
import com.quark.api.auto.bean.CourseCertification;
import com.quark.api.auto.bean.CourseCertificationInfoRequest;
import com.quark.api.auto.bean.CourseCertificationInfoResponse;
import com.quark.api.auto.bean.EditCourseCertificationRequest;
import com.quark.api.auto.bean.FileItem;
import com.quark.api.auto.bean.UpdatePicRequest;
import com.quark.api.auto.bean.UpdatePicResponse;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiHttpClient;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.ui.widget.WheelChooseBorthdayDialog;
import com.quark.skillopedia.util.ImageUtils;
import com.quark.skillopedia.util.ImageUtilsyasuo;
import com.quark.skillopedia.util.Utils;

import org.kymjs.kjframe.http.HttpCallBack;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Administrator on 2016/5/26 0026.
 * 新增证书
 */
public class CertificationsEditActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    String course_certification_id;
    CourseCertification ccinfo;
    int currentCertificationViews;
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
    @InjectView(R.id.title_et)
    EditText titleEt;
    @InjectView(R.id.name_tv)
    TextView nameTv;
    @InjectView(R.id.choosetime_tv)
    TextView choosetimeTv;
    @InjectView(R.id.choosetimepic)
    ImageView choosetimepic;
    @InjectView(R.id.unic_et)
    EditText unicEt;
    @InjectView(R.id.new_image_ibt)
    ImageView newImageIbt;
    @InjectView(R.id.new_remove_ibt)
    ImageButton new_remove_ibt;
    @InjectView(R.id.add_bt)
    Button addBt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certifications);
        ButterKnife.inject(this);
        setTopTitle("Certifications");
        setBackButton();
        course_certification_id = (String) getValue4Intent("course_certification_id");
        currentCertificationViews = (Integer) getValue4Intent("currentCertificationViews");

        certifiinfo();
    }

    //获取证书详情
    public void certifiinfo() {
        CourseCertificationInfoRequest rq = new CourseCertificationInfoRequest();
        rq.setCourse_certification_id(course_certification_id);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlercertifiinfo);
    }

    private final AsyncHttpResponseHandler mHandlercertifiinfo = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, CertificationsEditActivity.this, CourseCertificationInfoResponse.class);
            if (kd != null) {
                CourseCertificationInfoResponse info = (CourseCertificationInfoResponse) kd;
                ccinfo = info.getCourseCertificationInfoResult().getCourseCertification();
                titleEt.setText(ccinfo.getName());
                choosetimeTv.setText(ccinfo.getGet_time());
                unicEt.setText(ccinfo.getInstitue());
                ApiHttpClient.loadImage(ccinfo.getImage_01(), newImageIbt, R.drawable.example_2);
                new_remove_ibt.setVisibility(View.VISIBLE);
            }
            showWait(false);
        }

        @Override
        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
            AppContext.showToast("Network error" + arg0);
            showWait(false);
        }
    };

    @OnClick({R.id.new_image_ibt, R.id.choosetime_tv, R.id.choosetimepic, R.id.new_remove_ibt})
    public void cd(View b) {
        switch (b.getId()) {
            case R.id.new_image_ibt:
                ImageUtils.showSheetPic(CertificationsEditActivity.this, handlerphoto);
                break;
            case R.id.choosetime_tv:
            case R.id.choosetimepic:
                WheelChooseBorthdayDialog wccd = new WheelChooseBorthdayDialog();
                wccd.showSheetPic(this, handler, 101);
                break;
            case R.id.new_remove_ibt:
                newImageIbt.setImageDrawable(getResources().getDrawable(R.drawable.upload1));
                filename = "";
                break;
        }
    }

    private String name;//证书名称
    private String get_time;//获取时间
    private String institue;//机构名称
    private String filename = "";//Infer

    @OnClick(R.id.add_bt)
    public void ck(View b) {
        name = titleEt.getText().toString();
        institue = unicEt.getText().toString();
        get_time = choosetimeTv.getText().toString();
        if (check()) {
            addCourseCertificationRequest();
        }
    }

    public boolean check() {
        if (Utils.isEmpty(name)) {
            showToast(getString(R.string.course_no_name));
            return false;
        }
        if (Utils.isEmpty(get_time)) {
            showToast(getString(R.string.course_no_time));
            return false;
        }
        if (Utils.isEmpty(institue)) {
            showToast(getString(R.string.course_no_institue));
            return false;
        }
        if (Utils.isEmpty(filename)) {
            showToast("Please upload the picture");
            return false;
        }

        return true;
    }

    public void addCourseCertificationRequest() {
        EditCourseCertificationRequest rq = new EditCourseCertificationRequest();
        rq.setFilename(filename);
        rq.setGet_time(get_time);
        rq.setInstitue(institue);
        rq.setName(name);
        rq.setCourse_certification_id(course_certification_id);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandler);
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, CertificationsEditActivity.this, AddCourseCertificationResponse.class);
            if (kd != null) {
                AddCourseCertificationResponse info = (AddCourseCertificationResponse) kd;
                showToast(info.getMessage());
                if (info.getStatus() == 1) {
                    String course_certification_id = info.getCourse_certification_id();
                    Intent intent = new Intent();
                    intent.putExtra("course_certification_id", course_certification_id);
                    intent.putExtra("name", name);
                    intent.putExtra("institue", institue);
                    intent.putExtra("currentCertificationViews", institue);

                    setResult(202, intent);

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
    };

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 101:
                    get_time = (String) msg.obj;
                    choosetimeTv.setText(get_time);

                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }

        ;
    };

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }


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
                    String imagePath1 = ImageUtils.getImageToViewyuantu(data.getData(), this, newImageIbt);
                    new_remove_ibt.setVisibility(View.VISIBLE);
                    uploadNewPhoto(imagePath1);
                    break;
                case ImageUtils.CAMERA_REQUEST_CODE:
                    if (Utils.hasSdcard()) {
                        String pa = ImageUtils.getImageToViewyuantu2(ImageUtils.cameraUri, this, newImageIbt);
                        new_remove_ibt.setVisibility(View.VISIBLE);
                        uploadNewPhoto(pa);
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
                showToast(info.getMessage());
                if (info.getStatus() == 1) {
                    filename = info.getFileName();
                }
            } catch (Exception e) {
                Log.e("error", "數據解析出錯");
            }
            showWait(false);
        }

        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
            AppContext.showToast("Upload failed" + arg0);
            showWait(false);
        }
    };
    /*=================图片end==========================*/
}
