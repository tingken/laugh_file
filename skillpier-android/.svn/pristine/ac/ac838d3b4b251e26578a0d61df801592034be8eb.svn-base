package com.quark.skillopedia.uiview.zhanghu.jiaolian;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.AddCourseRequest;
import com.quark.api.auto.bean.CategoryListRequest;
import com.quark.api.auto.bean.CategoryListResponse;
import com.quark.api.auto.bean.Categorys;
import com.quark.api.auto.bean.CourseInfoRequest;
import com.quark.api.auto.bean.CourseInfoResponse;
import com.quark.api.auto.bean.CourseVedios;
import com.quark.api.auto.bean.DeleteCourseCertificationRequest;
import com.quark.api.auto.bean.DeleteCourseCertificationResponse;
import com.quark.api.auto.bean.EditCourse;
import com.quark.api.auto.bean.FileItem;
import com.quark.api.auto.bean.ListCourseBanner;
import com.quark.api.auto.bean.ListCourseBanners;
import com.quark.api.auto.bean.ListCourseCertification;
import com.quark.api.auto.bean.NewCoursePremissRequest;
import com.quark.api.auto.bean.UpdatePicRequest;
import com.quark.api.auto.bean.UpdatePicResponse;
import com.quark.api.auto.bean.newCoursePremissResponse;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.R;
import com.quark.skillopedia.adapter.RicPicAdapter;
import com.quark.skillopedia.api.ApiHttpClient;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.ui.widget.VideoDialog;
import com.quark.skillopedia.ui.widget.WheelChooseCategoryDialog;
import com.quark.skillopedia.util.ImageUtils;
import com.quark.skillopedia.util.ImageUtilsyasuo;
import com.quark.skillopedia.util.Utils;
import com.quark.swipedelete.SwipeLayout;

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
 * Created by Administrator on 2016/5/25 0025.
 * 新建课程 第一步
 */
public class NewcourseActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {
    public List<Categorys> categorys;
    ArrayList<String> videos = new ArrayList<>();
    public static ArrayList<ListCourseBanners> pics;
    RicPicAdapter adapter;
    int current;
    AddCourseRequest rq;
    public static NewcourseActivity instance;

    @InjectView(R.id.newcourse_one)
    LinearLayout newcourseOne;
    @InjectView(R.id.ccategory_tv)
    TextView ccategory_tv;
    @InjectView(R.id.certifications_view)
    LinearLayout certifications_view;
    @InjectView(R.id.vidioview)
    LinearLayout vidioview;
    @InjectView(R.id.GridView)
    GridView gridview;
    @InjectView(R.id.ctitle_et)
    EditText ctitleEt;
    @InjectView(R.id.overview)
    EditText overviewView;
    @InjectView(R.id.coursepic)
    ImageView coursepic;
    @InjectView(R.id.achievements)
    EditText achievementsView;
    @InjectView(R.id.specialist)
    EditText specialistView;


    EditCourse course;
    String picType;//课程图片=coursePic  轮播图片=bannar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcourse);
        ButterKnife.inject(this);
        setTopTitle("New course");
        setBackButton();
        categoryListRequest();
        instance = this;
        pics = new ArrayList<>();
        rq = new AddCourseRequest();
        course_id = (String) getValue4Intent("course_id");
        ListCourseBanners addpic = new ListCourseBanners();
        addpic.setIsadd(true);
        pics.add(addpic);
        adapter = new RicPicAdapter(NewcourseActivity.this, handler, 1);
        gridview.setAdapter(adapter);

        if (Utils.isEmpty(course_id)) {
            newCoursePremissRequest();
        } else {
            courseInfoRequest();
        }
    }

    //==============edit=====================
    public void courseInfoRequest() {
        CourseInfoRequest rq = new CourseInfoRequest();
        rq.setCourse_id(course_id);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlerinfo);
    }

    private final AsyncHttpResponseHandler mHandlerinfo = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, NewcourseActivity.this, CourseInfoResponse.class);
            if (kd != null) {
                CourseInfoResponse info = (CourseInfoResponse) kd;
                course = info.getCourseInfoResult().getCourse();

                initcourse();
            }
            showWait(false);
        }

        @Override
        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
            AppContext.showToast("Network error" + arg0);
            showWait(false);
        }
    };

    public void initcourse() {
        ctitleEt.setText(course.getTitle());
        ccategory_tv.setText(course.getCategory_02_name());
        category_01_id = course.getCategory_01_id() + "";
        category_01_name = course.getCategory_01_name() + "";
        category_02_id = course.getCategory_02_id() + "";
        category_02_name = course.getCategory_02_name() + "";
        user_images_01 = course.getUser_images_01() + "";
        //-------
        List<ListCourseCertification> cers = course.getCourseCertifications();
        if (cers != null && cers.size() > 0) {
            for (int i = 0; i < cers.size(); i++) {
                addcertification(cers.get(i).getName(), cers.get(i).getInstitue(), cers.get(i).getCourse_certification_id() + "");
            }
        }
        overviewView.setText(course.getOverview());
        //------
        List<ListCourseBanner> banners = course.getCourseBanners();
        if (banners != null && banners.size() > 0) {
            for (int i = 0; i < banners.size(); i++) {
                ListCourseBanners pic = new ListCourseBanners();
                pic.setImage_01(banners.get(i).getImage_01());
                pics.add(0, pic);
            }
            adapter.notifyDataSetChanged();
        }

        //----------
        List<CourseVedios> vedios = course.getCourseVedios();
        if (vedios != null && vedios.size() > 0) {
            for (int i = 0; i < vedios.size(); i++) {
                saveVedio(vedios.get(i).getVedio_url());
            }
        }

        ApiHttpClient.loadImage(course.getUser_images_01(), coursepic, R.drawable.example_7);
        achievementsView.setText(course.getAchievements());
        specialistView.setText(course.getSpecialist());
    }
    //==============edit=====================

    //获取分类
    public void categoryListRequest() {
        CategoryListRequest rq = new CategoryListRequest();
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandler);
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, NewcourseActivity.this, CategoryListResponse.class);
            if (kd != null) {
                CategoryListResponse info = (CategoryListResponse) kd;
                categorys = info.getCategoryListResult().getCategorys();
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

    @OnClick({R.id.next_bt, R.id.newcourse_one, R.id.addjy, R.id.ccategory_tv, R.id.ccategory_rightpic, R.id.addvidio})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addjy:
                if (!Utils.isEmpty(course_id)) {
                    Intent intent = new Intent();
                    intent.putExtra("course_id", course_id);
                    intent.setClass(this, CertificationsActivity.class);
                    startActivityForResult(intent, 201);
                }
                break;
            case R.id.ccategory_tv:
            case R.id.ccategory_rightpic:
                if (categorys != null && categorys.size() > 0) {
                    WheelChooseCategoryDialog wccd = new WheelChooseCategoryDialog();
                    wccd.showSheetPic(this, handler, 101, categorys);
                }
                break;
            case R.id.addvidio:
                addVideo();
                break;
        }
    }

    public void addVideo() {
        final VideoDialog.Builder builder = new VideoDialog.Builder(this);
        builder.setMessage("paste your youtube link here.");
        builder.setTitle("Vedio");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String url = builder.getconten();
                if (Utils.isEmpty(url)) {
                    showToast("paste your youtube link here");
                } else {
                    saveVedio(url);
                    dialog.dismiss();
                }
            }
        });
        builder.create().show();
    }

    public void saveVedio(final String url) {
        videos.add(url);
        final View swipe_layout = LayoutInflater.from(this).inflate(R.layout.video_swipe_layout_item, null);
        final SwipeLayout swi = (SwipeLayout) swipe_layout.findViewById(R.id.swipeLayout2);
        TextView url_tv = (TextView) swipe_layout.findViewById(R.id.url_tv);
        url_tv.setText(url);
        final LinearLayout remove = (LinearLayout) swipe_layout.findViewById(R.id.message_remove);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swi.closeState();
                videos.remove(url);
                vidioview.removeView(swipe_layout);
            }
        });

        vidioview.addView(swipe_layout);
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 101:  //类型
                    String cctv = (String) msg.obj;
                    int ctgrone = msg.arg1;
                    int ctgrtwo = msg.arg2;
                    category_01_id = categorys.get(ctgrone).getCategory_01_id() + "";
                    category_01_name = categorys.get(ctgrone).getCategory_01_name() + "";
                    category_02_id = categorys.get(ctgrone).getCategory02s().get(ctgrtwo).getCategory_02_id() + "";
                    category_02_name = categorys.get(ctgrone).getCategory02s().get(ctgrtwo).getCategory_02_name() + "";
                    ccategory_tv.setText(cctv);

                    break;
                case 202:
                    current = msg.arg1;
                    picType = "bannar";
                    ImageUtils.showSheetPic(NewcourseActivity.this, handlerphoto);
                    break;
                case 203:
                    current = msg.arg1;
                    pics.remove(current);
                    //动态设置高度
                    int size = pics.size();
                    double dd = size / 4.0;
                    double linedoubel = Math.ceil(dd);
                    int line = (int) linedoubel;
                    ViewGroup.LayoutParams params2 = gridview.getLayoutParams();
                    params2.height = Utils.dip2px(NewcourseActivity.this, 80) * line;
                    gridview.setLayoutParams(params2);

                    adapter.notifyDataSetChanged();

                default:
                    break;
            }
            super.handleMessage(msg);
        }

        ;
    };

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
    ArrayList<View> certificationViews = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != 0) {
            switch (requestCode) {
                case 201: //添加
                    String name = data.getStringExtra("name");
                    final String course_certification_id = data.getStringExtra("course_certification_id");
                    String institue = data.getStringExtra("institue");
                    addcertification(name, institue, course_certification_id);
                    certifications_view.setVisibility(View.VISIBLE);

                    break;
                case 202: //编辑

                    String nameedt = data.getStringExtra("name");
                    final String course_certification_idedt = data.getStringExtra("course_certification_id");
                    String institueedt = data.getStringExtra("institue");
                    int currentCertificationViews = data.getIntExtra("currentCertificationViews", 0);

                    final View swipe_layoutedt = certificationViews.get(currentCertificationViews);
                    final SwipeLayout swiedt = (SwipeLayout) swipe_layoutedt.findViewById(R.id.swipeLayout1);
                    TextView institueViewedt = (TextView) swipe_layoutedt.findViewById(R.id.institue);
                    TextView nameViewdt = (TextView) swipe_layoutedt.findViewById(R.id.name);
                    nameViewdt.setText(nameedt);
                    institueViewedt.setText(institueedt);
                    final LinearLayout removeedt = (LinearLayout) swipe_layoutedt.findViewById(R.id.message_remove);
                    removeedt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            swiedt.closeState();
                            certifications_view.removeView(swipe_layoutedt);
                            deleteCourseCertificationRequest(course_certification_idedt);
                        }
                    });
                    swiedt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.putExtra("course_certification_id", course_certification_idedt);
                            intent.setClass(NewcourseActivity.this, CertificationsEditActivity.class);
                            startActivityForResult(intent, 202);
                        }
                    });
                    break;

                case ImageUtils.IMAGE_REQUEST_CODE:
                    if (picType.equals("coursePic")) {
                        String imagePath1 = ImageUtils.getImageToViewyuantu(data.getData(), this, coursepic);
                        updatePicRequest(imagePath1);
                    } else if (picType.equals("bannar")) {
                        String imagePath1 = ImageUtils.getImageToViewyuantu(data.getData(), this, null);
                        pics.get(current).setBitmap(BitmapFactory.decodeFile(imagePath1));
                        pics.get(current).setSdpath(imagePath1);
                        updatePicRequest("");
                    }

                    break;
                case ImageUtils.CAMERA_REQUEST_CODE:
                    if (Utils.hasSdcard()) {
                        if (picType.equals("coursePic")) {
//                            String imagePath1 = ImageUtils.getImageToViewyuantu(data.getData(), this, coursepic);
                            String imagePath1 = ImageUtils.getImageToViewyuantu2(ImageUtils.cameraUri, this, coursepic);
                            updatePicRequest(imagePath1);
                        } else if (picType.equals("bannar")) {
                            String pa = ImageUtils.getImageToViewyuantu2(ImageUtils.cameraUri, this, null);
                            pics.get(current).setBitmap(BitmapFactory.decodeFile(pa, ImageUtils.getBitmapOption(3)));
                            pics.get(current).setSdpath(pa);
                            updatePicRequest("");
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

    //添加证书view
    public void addcertification(String name, String institue, final String course_certification_id) {
        final View swipe_layout = LayoutInflater.from(this).inflate(R.layout.certification_swipe_layout_item, null);
        certificationViews.add(swipe_layout);
        LinearLayout content = (LinearLayout) swipe_layout.findViewById(R.id.content);
        final SwipeLayout swi = (SwipeLayout) swipe_layout.findViewById(R.id.swipeLayout1);
        TextView institueView = (TextView) swipe_layout.findViewById(R.id.institue);
        TextView nameView = (TextView) swipe_layout.findViewById(R.id.name);
        nameView.setText(name);
        institueView.setText(institue);
        final LinearLayout remove = (LinearLayout) swipe_layout.findViewById(R.id.message_remove);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swi.closeState();
                certifications_view.removeView(swipe_layout);
                deleteCourseCertificationRequest(course_certification_id);
            }
        });
        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("course_certification_id", course_certification_id);
                intent.putExtra("currentCertificationViews", certificationViews.size()); //记录编辑的位置
                intent.setClass(NewcourseActivity.this, CertificationsEditActivity.class);
                startActivityForResult(intent, 202);
            }
        });
        certifications_view.addView(swipe_layout);
    }

    //上傳圖片
    public void updatePicRequest(String path) {
        try {
            if (picType.equals("coursePic")) {
                File file = ImageUtilsyasuo.compressAndGenImage(path, 200, false);
                List<FileItem> ls = new LinkedList<FileItem>();
                FileItem f = new FileItem("image_01", file);
                ls.add(f);
                UpdatePicRequest rq = new UpdatePicRequest();
                String ds = Math.random() + "";
                rq.setPid(ds + "");   //消除缓存
                showWait(true);
                QuarkApi.HttpuploadFile(rq, ls, httpCallBack);
            } else if (picType.equals("bannar")) {
                File file = ImageUtilsyasuo.compressAndGenImage(pics.get(current).getSdpath(), 200, false);
                List<FileItem> ls = new LinkedList<FileItem>();
                FileItem f = new FileItem("image_01", file);
                ls.add(f);
                UpdatePicRequest rq = new UpdatePicRequest();
                String ds = Math.random() + "";
                rq.setPid(ds + "");   //消除缓存
                showWait(true);
                QuarkApi.HttpuploadFile(rq, ls, httpCallBack);
            }
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
                    if (picType.equals("coursePic")) {
                        user_images_01 = info.getFileName();
                    } else if (picType.equals("bannar")) {
                        pics.get(current).setIsmodify(true);
                        pics.get(current).setImage_01(info.getFileName());
                        pics.get(current).setIsadd(false);
                        //增加按钮往后移动
                        ListCourseBanners addpic = new ListCourseBanners();
                        addpic.setIsadd(true);
                        pics.add(addpic);

                        //动态设置高度
                        int size = pics.size();
                        double dd = size / 4.0;
                        double linedoubel = Math.ceil(dd);
                        int line = (int) linedoubel;
                        ViewGroup.LayoutParams params2 = gridview.getLayoutParams();
                        params2.height = Utils.dip2px(NewcourseActivity.this, 80) * line;
                        gridview.setLayoutParams(params2);

                        adapter.notifyDataSetChanged();
                    }
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

    public void deleteCourseCertificationRequest(String course_certification_id) {
        DeleteCourseCertificationRequest rq = new DeleteCourseCertificationRequest();
        rq.setCourse_certification_id(course_certification_id);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlerdelecc);
    }

    private final AsyncHttpResponseHandler mHandlerdelecc = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, NewcourseActivity.this, DeleteCourseCertificationResponse.class);
            if (kd != null) {
                DeleteCourseCertificationResponse info = (DeleteCourseCertificationResponse) kd;
                showToast(info.getMessage());
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

    //===========提交==========================
    private String course_id;//
    private String title;//课程标题
    private String category_01_id;//第一大类Id
    private String category_01_name;//第一类名称
    private String category_02_id;//第二大类Id
    private String category_02_name;//第二类名称
    private String overview;//简介
    private String fileName = "";//拼接如：11.jpg#22.jpg#11.jpg#22.jpg【图片名称#图片名称】
    private String vedioURL = "";//拼接如：http://#http://#http://#http://【url名称#url名称】
    private String user_images_01; //课程头像

    private String achievements; //课程头像
    private String specialist; //课程头像

    @OnClick(R.id.next_bt)
    public void next(View v) {
        title = ctitleEt.getText().toString();
        overview = overviewView.getText().toString();
        achievements = achievementsView.getText().toString();
        specialist = specialistView.getText().toString();
        if (check()) {

            if (overviewView.getText().length() < 200) {
                showToast("at least 200 letter");
            } else {
                rq.setCourse_id(course_id);
                rq.setCategory_01_name(category_01_name);
                rq.setCategory_01_id(category_01_id);
                rq.setCategory_02_id(category_02_id);
                rq.setCategory_02_name(category_02_name);
                rq.setOverview(overview);
                rq.setTitle(title);
                rq.setFileName(fileName);
                rq.setVedioURL(vedioURL);
                rq.setSpecialist(specialist);
                rq.setAchievements(achievements);
                rq.setUser_images_01(user_images_01);
                Bundle bundle = new Bundle();
                bundle.putSerializable("courseRequest", rq);
                if (course != null) {
                    bundle.putSerializable("course", course);
                }
                startActivityByClass(NewcourseTwoActivity.class, bundle);
            }
        }
    }

    public boolean check() {
        if (pics.size() > 1) {
            for (int i = 0; i < pics.size() - 1; i++) {
                fileName += pics.get(i).getImage_01() + "#";
            }
        }
        if (fileName.endsWith("#")) {
            fileName = fileName.substring(0, fileName.length() - 1);
        }

        if (videos.size() > 0) {
            for (int i = 0; i < videos.size(); i++) {
                vedioURL += videos.get(i) + "#";
            }
        }
        if (vedioURL.endsWith("#")) {
            vedioURL = vedioURL.substring(0, vedioURL.length() - 1);
        }

        if (Utils.isEmpty(course_id)) {
            showToast("Access to course ID failed, please re-enter");
            return false;
        }
        if (Utils.isEmpty(title)) {
            showToast(getString(R.string.course_no_title));
            return false;
        }
        if (Utils.isEmpty(category_01_id)) {
            showToast("Please select a category");
            return false;
        }
        if (Utils.isEmpty(achievements)) {
            showToast("Please enter achievements");
            return false;
        }
//        if (Utils.isEmpty(specialist)) {
//            showToast("Please enter specialist");
//            return false;
//        }
        if (Utils.isEmpty(user_images_01)) {
            showToast("Please upload course Avatar");
            return false;
        }

//        if (Utils.isEmpty(fileName)) {
//            showToast("Please upload the curriculum.");
//            return false;
//        }

        return true;
    }

    //获取课程id
    public void newCoursePremissRequest() {
        NewCoursePremissRequest rq = new NewCoursePremissRequest();
        QuarkApi.HttpRequest(rq, mHandlercourseid);
    }

    private final AsyncHttpResponseHandler mHandlercourseid = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, NewcourseActivity.this, newCoursePremissResponse.class);
            if (kd != null) {
                newCoursePremissResponse info = (newCoursePremissResponse) kd;
                course_id = info.getCourse_id();
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

    //课程的头像
    @OnClick(R.id.coursepic)
    public void co(View b) {
        picType = "coursePic";
        ImageUtils.showSheetPic(NewcourseActivity.this, handlerphoto);
    }

}
