package com.quark.skillopedia.base;


import android.app.Activity;
import android.app.Fragment;
import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.quark.skillopedia.R;
import com.quark.skillopedia.mainview.MainActivity;
import com.quark.skillopedia.ui.widget.WaitDialog;
import com.quark.skillopedia.util.ToastUtil;
import com.quark.skillopedia.util.ValidateHelper;


/**
 * 除了首页的4个fragemnt 其他Fragment 不要继承一BaseFragmetn.
 * 如果要统一化，非要继承 修改 onAttach 中的， mActivity = (MainActivity) activity;在其他页面也要做相应修改
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragmentAPP extends Fragment {
	
    //ImageWorker 初始化如果不自己传入参数，将使用这个默认参数
    protected static final int DEFAULT_IMAGE_WIDTH = 128;
    protected static final int DEFAULT_IMAGE_HIGHT = 128;
//    protected static final int DEFAULT_IMAGE_RESID = R.drawable.default_avatar;
    protected MainActivity mActivity;
    protected Resources res;
    protected WaitDialog dialog;
//    protected RequestQueue queue = VolleySington.getInstance().getRequestQueue();
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (MainActivity) activity;
        res = mActivity.getResources();
    }

    protected void setTitle(View view, String title) {
        ((TextView) view.findViewById(R.id.title)).setText(title);
    }

    protected void setTitle(View view, int titleId) {
        String title = res.getString(titleId);
        setTitle(view, title);
    }

    protected void toastLong(String text) {
        Toast.makeText(mActivity, text, Toast.LENGTH_LONG).show();
    }

    protected void toastLong(int resId) {
        String text = res.getString(resId);
        toastLong(text);
    }

    protected void toastShort(String text) {
        Toast.makeText(mActivity, text, Toast.LENGTH_SHORT).show();
    }

    protected void toastShort(int resId) {
        String text = res.getString(resId);
        toastShort(text);
    }

    protected void showWait(boolean isShow) {
        if (isShow) {
            if (null == dialog) {
                dialog = new WaitDialog(getActivity());
            }
            dialog.show();
        } else {
            if (null != dialog) {
                dialog.dismiss();
            }
        }
    }
    
//    @SuppressLint("NewApi")
//	protected void setRight(View view, int viewid) {
//    	Button	button= (Button) view.findViewById(R.id.right);
//    	button.setBackground(getResources().getDrawable(viewid));
//    	rightListener(button);
//    }
    
    //显示右边按钮需要重写这个方法
    protected void rightListener(Button button) {

    }
    //初始化ImageWorker 如果有图片下载需要先调用此方法
//    protected void initImageWoker() {
//        initImageWoker(DEFAULT_IMAGE_WIDTH, DEFAULT_IMAGE_HIGHT, DEFAULT_IMAGE_RESID);
//    }
//
//
//    protected boolean isLogin() {
//        return ((MdjApplication) getActivity().getApplication()).isLogin();
//    }
//
//    protected int getUserId() {
//        if (isLogin()) {
//            return ((MdjApplication) getActivity().getApplication()).getUser().getId();
//        }
//        return -1;
//    }
//
//    protected User getLoginUser() {
//        if (isLogin()) {
//            return ((MdjApplication) getActivity().getApplication()).getUser();
//        }
//        return null;
//    }
//
//    protected void clearLoginInfo() {
//        if (isLogin()) {
//            ((MdjApplication) getActivity().getApplication()).clearLoginInfo();
//        }
//    }
    
    @Override
    public void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    }
   
   	/**
   	 * Toast 方法
   	 * 
   	 * @param msg
   	 */
   	public void showToast(String msg) {
   		if (ValidateHelper.isEmptyString(msg)) {
   			return;
   		}

   		ToastUtil.showShortToast(msg);
   	}

   	public void showToast(int resid) {
   		ToastUtil.showShortToast(resid);
   	}
   	
    
}
