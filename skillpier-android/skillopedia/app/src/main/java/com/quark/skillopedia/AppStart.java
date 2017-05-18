package com.quark.skillopedia;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.GuideBannerRequest;
import com.quark.api.auto.bean.GuideBannerResponse;
import com.quark.api.auto.bean.ListGuideBanner;
import com.quark.skillopedia.api.ApiHttpClient;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.mainview.MainActivity;
import com.quark.skillopedia.util.TDevice;
import com.quark.skillopedia.util.TLog;

import org.kymjs.kjframe.Core;
import org.kymjs.kjframe.bitmap.BitmapCallBack;
import org.kymjs.kjframe.utils.PreferenceHelper;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import cn.jpush.android.api.InstrumentedActivity;
import cn.jpush.android.api.JPushInterface;
import cz.msebera.android.httpclient.Header;

/**
 * 应用启动界面
 *
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @created 2014年12月22日 上午11:51:56
 */
public class AppStart extends InstrumentedActivity {

    int waitTime = 1000;
    boolean isfirstIn;
    int finish = 0;  //加载完成的引导页图片数

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //判断是不是首次登录，将登录标志位设置为false，下次登录时不在显示首次登录界面
        SharedPreferences sp1 = getSharedPreferences(AppParam.SHAREDPREFERENCESKEY, MODE_PRIVATE);
        isfirstIn = sp1.getBoolean("firststart", true);
        if (isfirstIn) {
            SharedPreferences.Editor editjb = sp1.edit();
            editjb.putBoolean("firststart", false);
            editjb.commit();
            AppContext.guidepics.clear();//清空上次缓存的数据
            waitTime = 3000;  //需要加载引导页 加长动画时间
            guideBannerRequest();
        }

        // 防止第三方跳转时出现双实例
        Activity aty = AppManager.getActivity(MainActivity.class);
        if (aty != null && !aty.isFinishing()) {
            finish();
        }
        // SystemTool.gc(this); //针对性能好的手机使用，加快应用相应速度

        final View view = View.inflate(this, R.layout.app_start, null);
        setContentView(view);
        // 渐变展示启动屏
        AlphaAnimation aa = new AlphaAnimation(0.5f, 1.0f);
        aa.setDuration(waitTime);
        view.startAnimation(aa);
        aa.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                //广告页加载完成这跳转到广告页 否则跳转到首页
                if (isfirstIn) {
                    if (finish != 0 && (finish == listGuideBanner.size())) {
                        redirectTo();
                    } else {
                        ApiHttpClient.client.cancelAllRequests(true);
                        TLog.error("长时间获取不到 广告页");
                        redirectToMain();
                    }
                } else {
                    redirectToMain();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }
        });
//        透明状态栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        透明导航栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
//        MobclickAgent.onResume(this);
        int cacheVersion = PreferenceHelper.readInt(this, "first_install",
                "first_install", -1);
        int currentVersion = TDevice.getVersionCode();
        if (cacheVersion < currentVersion) {
            PreferenceHelper.write(this, "first_install", "first_install",
                    currentVersion);
        }
    }

    /**
     * 跳转到广告页
     */
    private void redirectTo() {
        Intent intent = new Intent(this, AppYidaoActivity.class);
        startActivity(intent);
        finish();
    }

    private void redirectToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    /*极光推送*/

    @Override
    protected void onPause() {
        JPushInterface.onPause(this);
//        MobclickAgent.onPause(this);

        super.onPause();
    }

    /*极光推送end*/
    //===========获取引导页数据=================
    List<ListGuideBanner> listGuideBanner;

    public void guideBannerRequest() {
        GuideBannerRequest rq = new GuideBannerRequest();
        QuarkApi.HttpRequest(rq, mHandler);
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, AppStart.this, GuideBannerResponse.class);
            if (kd != null) {
                GuideBannerResponse info = (GuideBannerResponse) kd;
                listGuideBanner = info.getGuideBanners();
                if (listGuideBanner != null && listGuideBanner.size() > 0) {
                    for (int i = 0; i < listGuideBanner.size(); i++) {
                        loadImage(listGuideBanner.get(i).getCover(), i);
                    }
                }
            }
        }

        @Override
        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
            AppContext.showToast("Network error" + arg0);

        }
    };
    //============获取引导页数据end=================

    //將圖片保存在本地
    public void loadImage(String cpic, final int current) {
        if ((cpic != null) && (!cpic.equals(""))) {
            try {
                cpic = URLEncoder.encode(cpic, "UTF-8");
                String url = ApiHttpClient.loadImage + cpic;

                new Core.Builder().url(url).view(new View(AppStart.this))
                        .loadBitmap(new ColorDrawable(0x000000))
                        .errorBitmap(new ColorDrawable(0x000000))
                        .size(0, 0)
                        .bitmapCallBack(new BitmapCallBack() {
                                            @Override
                                            public void onFailure(Exception arg0) {
                                            }

                                            @Override
                                            public void onSuccess(Bitmap bitmap) {
                                                super.onSuccess(bitmap);
                                                try {
                                                    String path = Environment.getExternalStorageDirectory() + "/tempImage" + current + ".jpg";
                                                    File file1 = new File(path); //将要保存图片的路径
                                                    try {
                                                        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file1));
                                                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                                                        bos.flush();
                                                        bos.close();
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                    ListYunBanner guid = new ListYunBanner();
                                                    guid.setBitmap(bitmap);
                                                    AppContext.guidepics.add(guid);
                                                    finish++;
                                                } catch (Exception e) {
                                                    TLog.error("加载ad失败" + e.getMessage());
                                                }
                                            }
                                        }
                        ).doTask();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }


//    public void toGuid(){
//       if (finish==listGuideBanner.size()){
//           redirectTo();
//       }
//    }
}
