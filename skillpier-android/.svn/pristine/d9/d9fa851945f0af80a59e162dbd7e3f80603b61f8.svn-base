package com.quark.skillopedia.uiview.zhanghu.jingli;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.AddExperienceRequest;
import com.quark.api.auto.bean.AddExperienceResponse;
import com.quark.api.auto.bean.EditExperienceRequest;
import com.quark.api.auto.bean.EditExperienceResponse;
import com.quark.api.auto.bean.ExperienceInfoRequest;
import com.quark.api.auto.bean.ExperienceInfoResponse;
import com.quark.api.auto.bean.FileItem;
import com.quark.api.auto.bean.ListCustomerBanner;
import com.quark.api.auto.bean.ListFileNameBean;
import com.quark.api.auto.bean.UpdatePicRequest;
import com.quark.api.auto.bean.UpdatePicResponse;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.R;
import com.quark.skillopedia.adapter.JinyanPicAdapter;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.ui.dialog.CustomDialog;
import com.quark.skillopedia.util.ImageUtils;
import com.quark.skillopedia.util.ImageUtilsyasuo;
import com.quark.skillopedia.util.TLog;
import com.quark.skillopedia.util.Utils;

import org.kymjs.kjframe.http.HttpCallBack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 编写经历
 * @author pan
 * @time 2016/8/22 0022 下午 5:41
 */
public class StepswritingActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

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
    @InjectView(R.id.text_et)
    EditText textEt;
    @InjectView(R.id.GridView)
    android.widget.GridView GridView;

    @InjectView(R.id.save_bt)
    Button saveBt;
    @InjectView(R.id.publish_bt)
    Button publishBt;

    private String titlestr = "";//标题
    private String content = "";//内容
    private String fileName = "";//拼接如：11.jpg#22.jpg#11.jpg#22.jpg【图片名称#图片名称】
    String experience_id;
    int current;

    public static ArrayList<ListCustomerBanner> pics;
    JinyanPicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strpswriting);
        ButterKnife.inject(this);
        setTopTitle("My steps writing");
        left.setVisibility(View.VISIBLE);

        experience_id = (String) getValue4Intent("experience_id");
        if (!Utils.isEmpty(experience_id)) {
            experienceInfoRequest();
        }
        initPic();
    }

    public void initPic() {
        pics = new ArrayList<>();
        ListCustomerBanner addpic = new ListCustomerBanner();
        addpic.setIsadd(true);
        pics.add(addpic);
        adapter = new JinyanPicAdapter(StepswritingActivity.this, handler, 0);
        GridView.setAdapter(adapter);
    }

    @OnClick({R.id.left, R.id.save_bt, R.id.publish_bt})
    public void savebt(View b) {
        titlestr = titleEt.getText().toString();
        content = textEt.getText().toString();
        switch (b.getId()) {
            case R.id.left:
                exit();
                break;
            case R.id.save_bt:
                if (Utils.isEmpty(experience_id)) {
                    addExperienceRequest(2);
                } else {
                    editExperienceRequest(2);
                }
                break;
            case R.id.publish_bt:
                if (check()) {
                    if (Utils.isEmpty(experience_id)) {
                        addExperienceRequest(1);
                    } else {
                        editExperienceRequest(1);
                    }
                }
                break;
        }
    }

    public boolean check() {
        if (Utils.isEmpty(titlestr)) {
            showToast("Please enter a title");
            return false;
        }
        if (Utils.isEmpty(content)) {
            showToast("Please enter the content ");
            return false;
        }
        for (int i = 0; i < pics.size() - 1; i++) {
            fileName += pics.get(i).getImage_01() + "#";
        }
        if (fileName.endsWith("#")) {
            fileName = fileName.substring(0, fileName.length() - 1);
        }
//        if (Utils.isEmpty(fileName)) {
//            showToast("Please upload the experience pictures");
//            return false;
//        }
        return true;
    }

    public void addExperienceRequest(int status) {
        AddExperienceRequest rq = new AddExperienceRequest();
        rq.setTitle(titlestr);
        rq.setContent(content);
        rq.setStatus(status + "");
        rq.setFileName(fileName);
        TLog.error(fileName+"****************图片名字");

        showWait(true);
        QuarkApi.HttpRequest(rq, mHandler);
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, StepswritingActivity.this, AddExperienceResponse.class);
            if (kd != null) {
                AddExperienceResponse info = (AddExperienceResponse) kd;
                showToast(info.getMessage());
                if (info.getStatus() == 1) {
//                    Intent intent = new Intent("broadexprice");
//                    sendBroadcast(intent);
                    Intent intent = new Intent();
                    setResult(108,intent);
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

    public void editExperienceRequest(int status) {
        EditExperienceRequest rq = new EditExperienceRequest();
        rq.setTitle(titlestr);
        rq.setExperience_id(experience_id);
        rq.setContent(content);
        rq.setStatus(status + "");
        rq.setFileName(fileName);

        showWait(true);
        QuarkApi.HttpRequest(rq, mHandleredit);
    }

    private final AsyncHttpResponseHandler mHandleredit = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, StepswritingActivity.this, EditExperienceResponse.class);
            if (kd != null) {
                EditExperienceResponse info = (EditExperienceResponse) kd;
                showToast(info.getMessage());
                if (info.getStatus() == 1) {
//                    Intent intent = new Intent("broadexprice");
//                    sendBroadcast(intent);
                    Intent intent = new Intent();
                    setResult(108,intent);
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

    public void exit() {
        CustomDialog.Builder builder = new CustomDialog.Builder(this);
        builder.setMessage("You are gone to leave whthout save content to the draft,are you sure?");
        builder.setTitle("WARNING");
        builder.setPositiveButton("Save to draft", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (Utils.isEmpty(experience_id)) {
                    addExperienceRequest(2);
                } else {
                    editExperienceRequest(2);
                }
            }
        });
        builder.setNegativeButton("Just leave",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
        builder.create().show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 202:
                    current = msg.arg1;
                    ImageUtils.showSheetPic(StepswritingActivity.this, handlerphoto);
                    break;
                case 203:
                    current = msg.arg1;
                    pics.remove(current);
                    adapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }

        ;
    };

    //编辑
    public void experienceInfoRequest() {
        ExperienceInfoRequest rq = new ExperienceInfoRequest();
        rq.setExperience_id(experience_id);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlerinfo);
    }

    private final AsyncHttpResponseHandler mHandlerinfo = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, StepswritingActivity.this, ExperienceInfoResponse.class);
            if (kd != null) {
                ExperienceInfoResponse info = (ExperienceInfoResponse) kd;
                if (info.getStatus() == 1) {
                    titleEt.setText(info.getExperienceInfoResult().getExperienceInfo().getTitle());
                    textEt.setText(info.getExperienceInfoResult().getExperienceInfo().getContent());
                    List<ListFileNameBean> ps = info.getExperienceInfoResult().getExperienceInfo().getExBanners();
                    if (ps != null && ps.size() > 0) {
                        for (int i = 0; i < ps.size(); i++) {
                            ListCustomerBanner md = new ListCustomerBanner();
                            md.setImage_01(ps.get(i).getImage_01());
                            pics.add(0,md);
                        }
                    }
                    adapter.notifyDataSetChanged();
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
                    String imagePath1 = ImageUtils.getImageToViewyuantu(data.getData(), this, null);
                    pics.get(current).setBitmap(BitmapFactory.decodeFile(imagePath1, ImageUtils.getBitmapOption(3)));
                    pics.get(current).setImageFilePath(imagePath1);
                    uploadNewPhoto();
                    break;
                case ImageUtils.CAMERA_REQUEST_CODE:
                    if (Utils.hasSdcard()) {
                        String pa = ImageUtils.getImageToViewyuantu2(ImageUtils.cameraUri, this, null);
                        pics.get(current).setBitmap(BitmapFactory.decodeFile(pa, ImageUtils.getBitmapOption(3)));
                        pics.get(current).setImageFilePath(pa);
                        uploadNewPhoto();
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
    private void uploadNewPhoto() {
        List<FileItem> ls = new LinkedList<FileItem>();
        File file = null;
        try {
            file = ImageUtilsyasuo.compressAndGenImage(pics.get(current).getImageFilePath(), 300, false);
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
                    pics.get(current).setIsmodify(true);
                    pics.get(current).setImage_01(info.getFileName());
                    pics.get(current).setIsadd(false);
                    ListCustomerBanner addpic = new ListCustomerBanner();
                    addpic.setIsadd(true);
                    pics.add(addpic);
                    adapter.notifyDataSetChanged();
                }
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
