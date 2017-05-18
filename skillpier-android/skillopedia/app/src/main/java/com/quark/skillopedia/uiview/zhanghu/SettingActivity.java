package com.quark.skillopedia.uiview.zhanghu;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loveplusplus.update.UpdateChecker;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiHttpClient;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.util.DialogHelp;
import com.quark.skillopedia.util.FileUtils;
import com.quark.skillopedia.util.MethodsCompat;
import com.quark.skillopedia.util.TLog;
import com.quark.skillopedia.util.UIHelper;
import com.quark.skillopedia.util.Utils;

import org.kymjs.kjframe.http.HttpConfig;

import java.io.File;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Administrator on 2016/5/25 0025.
 * 设置
 */
public class SettingActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    @InjectView(R.id.update_ly)
    LinearLayout updateLy;
    @InjectView(R.id.clear_ly)
    LinearLayout clearLy;
    @InjectView(R.id.versonName)
    TextView versonName;
    @InjectView(R.id.left)
    LinearLayout left;
    @InjectView(R.id.hc_size)
    TextView hcSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.inject(this);
        setTopTitle("Settings");
        left.setVisibility(View.VISIBLE);
        setBackButton();
        caculateCacheSize();
        versonName.setText(Utils.getVersion(this));
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
    }

    @OnClick({R.id.update_ly, R.id.clear_ly})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.update_ly:
//                versionUpdateRequest();
                startUpdatebyPermissions();
                break;
            case R.id.clear_ly:
                DialogHelp.getConfirmDialog(this, "Whether to clear the cache?", new DialogInterface.OnClickListener
                        () {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UIHelper.clearAppCache(SettingActivity.this);
                        hcSize.setText("0KB");
                    }
                }).show();
                break;
//            case R.id.access_ly:
//                Toast.makeText(getApplicationContext(), "访问官网", Toast.LENGTH_SHORT).show();
//                break;
        }
    }

    /**
     * 计算缓存的大小
     */
    private void caculateCacheSize() {
        long fileSize = 0;
        String cacheSize = "0KB";
        File filesDir = getFilesDir();
        File cacheDir = getCacheDir();

        fileSize += FileUtils.getDirSize(filesDir);
        fileSize += FileUtils.getDirSize(cacheDir);
        // 2.2版本才有将应用缓存转移到sd卡的功能
        if (AppContext.isMethodsCompat(Build.VERSION_CODES.FROYO)) {
            try {
                File externalCacheDir = MethodsCompat.getExternalCacheDir(this);
                fileSize += FileUtils.getDirSize(externalCacheDir);
                fileSize += FileUtils.getDirSize(new File(org.kymjs.kjframe.utils.FileUtils.getSDCardPath() + File.separator + HttpConfig.CACHEPATH));
            } catch (Exception e) {
                TLog.error("获取缓存大小异常" + e.getMessage());
            }
        }
        if (fileSize > 0) {
            cacheSize = FileUtils.formatFileSize(fileSize);
        }
        hcSize.setText(cacheSize);
    }


//    /**
//     * 版本更新
//     *
//     * @author pan
//     * @time 2016/10/12 0012 下午 6:29
//     */
//    public void versionUpdateRequest() {
//        AndroidAutoUpdateRequest rq = new AndroidAutoUpdateRequest();
//        rq.setType("1");
//        rq.setVersionCode(Utils.getVersionCode(this) + "");
//        showWait(true);
//        QuarkApi.HttpRequestNoTS(rq, mHandlerhot);
//    }
//
//    private final AsyncHttpResponseHandler mHandlerhot = new AsyncHttpResponseHandler() {
//        @Override
//        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
//            Object kd = ApiResponse.get(arg2, SettingActivity.this, AndroidAutoUpdateResponse.class);
//            if (kd != null) {
//                AndroidAutoUpdateResponse info = (AndroidAutoUpdateResponse) kd;
//
//                if (info.getStatus() == 1) {
//                    DialogHelp.getMessageDialog(SettingActivity.this, "No updated version!", new DialogInterface.OnClickListener
//                            () {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//
//                        }
//                    }).show();
//                } else if (info.getStatus() == 2) {
//
//                } else if (info.getStatus() == 3) {
//
//                }
//            }
//            showWait(false);
//        }
//
//        @Override
//        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
//            AppContext.showToast("Network error" + arg0);
//            showWait(false);
//        }
//    };

    private void startUpdatebyPermissions() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            try {
                checkupdate();
            } catch (Exception e) {
                Toast.makeText(this, "缺少运行权限,请正确授权", Toast.LENGTH_LONG).show();
            }
        } else {
            EasyPermissions.requestPermissions(this, "申请运行所需的权限，如果拒绝将影响您的正常使用", 10002, perms);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (requestCode == 10002) {
            checkupdate();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
    }

    public void checkupdate() {
        showWait(true);
        String checkUrl = ApiHttpClient.HOSTURL + "/app/Setting/androidAutoUpdate?method=get&type=1&versionCode=" + Utils.getVersionCode(this);
        TLog.error(checkUrl);
        UpdateChecker.checkForDialog(SettingActivity.this, checkUrl, ApiHttpClient.HOSTURL, handlerupdate);
    }

    private Handler handlerupdate = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    showWait(false);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }

        ;
    };
}
