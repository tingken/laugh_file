package com.quark.skillopedia.uiview.zhanghu.zhuye;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.joooonho.SelectableRoundedImageView;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.PagerSlidingTabStrip.PagerSlidingTabStrip;
import com.quark.api.auto.bean.FileItem;
import com.quark.api.auto.bean.NoScrollViewPager;
import com.quark.api.auto.bean.PersonalInfoRequest;
import com.quark.api.auto.bean.PersonalInfoResponse;
import com.quark.api.auto.bean.UpdateAvatarRequest;
import com.quark.api.auto.bean.UpdateAvatarResponse;
import com.quark.fragment.CommentsFragment;
import com.quark.fragment.CourseFragment;
import com.quark.fragment.StepsFtagment;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.AppParam;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiHttpClient;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseFragementActivity;
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
 * Created by Administrator on 2016/5/20 0020.
 * 个人主页
 */
public class PersonHomeActivity extends BaseFragementActivity implements EasyPermissions.PermissionCallbacks {
    @InjectView(R.id.left_img)
    ImageView leftImg;
    @InjectView(R.id.left)
    LinearLayout left;
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.head)
    RelativeLayout head;
    @InjectView(R.id.username_tv)
    TextView usernameTv;
    @InjectView(R.id.email_tv)
    TextView emailTv;
    @InjectView(R.id.head_iv)
    SelectableRoundedImageView headIv;
    @InjectView(R.id.sign)
    TextView sign;
    @InjectView(R.id.rightrig)
    ImageView rightrig;
    @InjectView(R.id.right)
    LinearLayout right;

    private PagerSlidingTabStrip pagerTab;
    private NoScrollViewPager pager;
    //private NoScrollViewPager pager;
//    private final int NUM_PAGES = 3;
    String userId;
    Bundle bundle;
    String name;

    boolean isCoach=false; //true 教练 false用户
    private String[] TITLES;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personhomne);
        ButterKnife.inject(this);

        right.setVisibility(View.VISIBLE);
        sign.setTextColor(ContextCompat.getColor(this,R.color.luse));
        sign.setText("Create");
        rightrig.setImageDrawable(getResources().getDrawable(R.drawable.write));
        isCoach = (Boolean)getValue4Intent("isCoach");

        if (isCoach){
            TITLES = getResources().getStringArray(R.array.homepager_name);
        }else{
            TITLES = getResources().getStringArray(R.array.homepager_name_user);
        }

        pagerTab = (PagerSlidingTabStrip) findViewById(R.id.homepager_tabs);
        pager = (NoScrollViewPager) findViewById(R.id.home_pager);
        pager.setOffscreenPageLimit(2);
        pager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        pagerTab.setViewPager(pager);

        userId = (String) getValue4Intent("userid");
        if (Utils.isEmpty(userId)) {
            userId = new AppParam().getUser_id(this);
        }
        personalInfoRequest();
    }

    @OnClick({R.id.left, R.id.username_tv,R.id.right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left:
                finish();
                break;
            case R.id.right:
//                Intent intent = new Intent(PersonHomeActivity.this, StepswritingActivity.class);
//                startActivityForResult(intent, 108);
//                startActivityByClass(StepswritingActivity.class);
//                finish();
                break;
            case R.id.username_tv:
//                Intent intent = new Intent();
//                String namestr = usernameTv.getText().toString();
//                if (!Utils.isEmpty(namestr)) {
//                    intent.putExtra("name", namestr);
//                }
//                intent.setClass(this, RenameActivity.class);
//                startActivityForResult(intent, 100);
                break;

        }
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    private class PagerAdapter extends FragmentPagerAdapter {
        CourseFragment courseFragment;
        CommentsFragment commentsFragment;
        StepsFtagment stepsFtagment;

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public Fragment getItem(int position) {
            if (isCoach){
                switch (position) {
                    case 0:
                        if (courseFragment == null) {
                            courseFragment = new CourseFragment();
                            courseFragment.setArguments(bundle);
                        }
                        right.setVisibility(View.GONE);
                        return courseFragment;
                    case 1:
                        if (commentsFragment == null) {
                            commentsFragment = new CommentsFragment();
                        }
                        right.setVisibility(View.GONE);
                        return commentsFragment;

                    case 2:
                        if (stepsFtagment == null) {
                            stepsFtagment = new StepsFtagment();
                        }
                        right.setVisibility(View.VISIBLE);
                        return stepsFtagment;
                    default:
                        return null;
                }
            }else{
                switch (position) {
                    case 0:
                        if (commentsFragment == null) {
                            commentsFragment = new CommentsFragment();
                        }
                        right.setVisibility(View.GONE);
                        return commentsFragment;
                    case 1:
                        if (stepsFtagment == null) {
                            stepsFtagment = new StepsFtagment();
                        }
                        right.setVisibility(View.VISIBLE);
                        return stepsFtagment;
                    default:
                        return null;
                }
            }
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }
    }

    public void personalInfoRequest() {
        PersonalInfoRequest rq = new PersonalInfoRequest();
        rq.setUser_id(userId);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandler);
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, PersonHomeActivity.this, PersonalInfoResponse.class);
            if (kd != null) {
                PersonalInfoResponse info = (PersonalInfoResponse) kd;
                ApiHttpClient.loadImage(info.getPersonalInfoResult().getUserInfo().getImage_01(), headIv, R.drawable.example_7);
                usernameTv.setText(info.getPersonalInfoResult().getUserInfo().getNickname());
                emailTv.setText("Email:" + info.getPersonalInfoResult().getUserInfo().getEmail());

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
    @OnClick(R.id.head_iv)
    public void onClick() {
        ImageUtils.showSheetPic(this, handlerphoto);
    }

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != 0) {
            switch (requestCode) {
                case ImageUtils.IMAGE_REQUEST_CODE:
                    ImageUtils.startPhotoZoom(data.getData(), 200, 200, this);
                    break;
                case ImageUtils.CAMERA_REQUEST_CODE:
                    ImageUtils.startPhotoZoom(ImageUtils.cameraUri, 200, 200, this);
                    break;
                case ImageUtils.RESULT_REQUEST_CODE:
                    if (data != null) {
                        String imagePath = ImageUtils.getImageToView(data, headIv);
                        updateAvatarRequest(imagePath);
                    }
                    break;
                case 100:
                    name = data.getStringExtra("name");
                    usernameTv.setText(name);
            }
        }
    }

    public void updateAvatarRequest(String imagePath) {
        List<FileItem> ls = new LinkedList<FileItem>();
        File file = null;
        try {
            file = ImageUtilsyasuo.compressAndGenImage(imagePath, 300, false);
            FileItem f = new FileItem("image_01", file);
            ls.add(f);
            UpdateAvatarRequest rq = new UpdateAvatarRequest();
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
//            Log.e("error", "==" + t);
            try {
                UpdateAvatarResponse info = new UpdateAvatarResponse(t);
                showToast(info.getMessage());
                Intent in = new Intent("broadfour");
                in.putExtra("opertion", "refresh");
                sendBroadcast(in);

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
