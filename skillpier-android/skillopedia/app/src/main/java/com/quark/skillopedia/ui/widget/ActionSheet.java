/*
package com.quark.skillopedia.ui.widget;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import AppParam;
import com.quark.skillopedia.R;

import java.util.ArrayList;


@SuppressLint("NewApi")
public class ActionSheet {
	static String sexstr;
	static String xueliStr;
	static LinearLayout layout;
	static Context context;
	static String user_id;
	
	public interface OnActionSheetSelected {
		void onClick(int whichButton);
	}

	private ActionSheet() {
	}
	
	static ArrayList<TextView> d = new ArrayList<TextView>();;
	
	//=======sex start======
	//性别
	public static Dialog showSheetSex(final Context context, final OnActionSheetSelected actionSheetSelected,
			OnCancelListener cancelListener,final TextView view,final Handler handler) {
		SharedPreferences sp = context.getSharedPreferences(AppParam.SHAREDPREFERENCESKEY, context.MODE_PRIVATE);
		user_id = sp.getString("user_id", "");
		
		final Dialog dlg = new Dialog(context, R.style.ActionSheet);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.actionsheetsex, null);
		final int cFullFillWidth = 10000;
		layout.setMinimumWidth(cFullFillWidth);
		final TextView sex_man = (TextView)layout.findViewById(R.id.sex_man);
		final TextView sex_lady = (TextView)layout.findViewById(R.id.sex_lady);
		final TextView cancel = (TextView)layout.findViewById(R.id.cancel);

		sex_man.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String sexStr = sex_man.getText().toString();
				view.setText(sexStr);

//				submintSext("男");
				
				Message msg = new Message();
				msg.obj = "男";
				msg.what=1;
				handler.sendMessage(msg);
				dlg.dismiss();
			}
		});
		sex_lady.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String sexStr = sex_lady.getText().toString();
				view.setText(sexStr);

//				submintSext("女");
				Message msg = new Message();
				msg.obj = "女";
				msg.what=1;
				handler.sendMessage(msg);
				dlg.dismiss();
			}
		});
		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dlg.dismiss();
			}
		});

		Window w = dlg.getWindow();
		WindowManager.LayoutParams lp = w.getAttributes();
		lp.x = 0;
		final int cMakeBottom = -1000;
		lp.y = cMakeBottom;
		lp.gravity = Gravity.BOTTOM;
		dlg.onWindowAttributesChanged(lp);
		dlg.setCanceledOnTouchOutside(true);
		if (cancelListener != null)
			dlg.setOnCancelListener(cancelListener);

		dlg.setContentView(layout);
		dlg.show();

		return dlg;
	}
	
	 //===========sex end==========
	 
				*/
/**
				  * 获取系统SDK版本
				  * @return
				  *//*

				 public static int getSDKVersionNumber() {
				  int sdkVersion;
				  try {
				   sdkVersion = Integer.valueOf(android.os.Build.VERSION.SDK);
				  } catch (NumberFormatException e) {
				   sdkVersion = 0;
				  }
				  return sdkVersion;
				 }
				 
				  */
/**
			      * 从当前Dialog中查找DatePicker子控件
			      * 
			      * @param group
			      * @return
			      *//*

			     private DatePicker findDatePicker(ViewGroup group) {
			         if (group != null) {
			             for (int i = 0, j = group.getChildCount(); i < j; i++) {
			                 View child = group.getChildAt(i);
			                 if (child instanceof DatePicker) {
			                     return (DatePicker) child;
			                 } else if (child instanceof ViewGroup) {
			                     DatePicker result = findDatePicker((ViewGroup) child);
			                     if (result != null)
			                         return result;
			                 }
			             }
			         }
			         return null;
			     }
		
}
*/
