package com.quark.skillopedia.ui.widget;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quark.skillopedia.R;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;


@SuppressLint("NewApi")
public class WheelChooseHourMiniDialog implements OnClickListener, OnWheelChangedListener{
	public String city;
	private static WheelView mViewProvince;
	private static WheelView mViewCity;
//	private WheelView mViewDistrict;
	private static TextView mBtnConfirm;
	public Context context;
//	int tyear;
	int tm ;
	int tmonth;
	boolean isc = false;
	protected String[] mYears = {"09","10","11","12","13","14","15","16","17","18","19","20","21"};
//	ArrayList<String> temptimes = new ArrayList<String>();
//	protected String[] days;
	String[] months = {"00","30"};

	protected String year ="09";
	protected String month = "00";
//	protected String day ="01";
	protected String mCurrentZipCode ="";
    int pCurrentMin = 0;
    int pCurrentHours=0;
	 //=========================show city=================
	 @Override
		public void onChanged(WheelView wheel, int oldValue, int newValue) {
			if (wheel == mViewProvince) {
				updateCities();
			} else if (wheel == mViewCity) {
				updateAreas();
			}
//			else if (wheel == mViewDistrict) {
//				day = days[newValue];
//			}
		}

		/**
		 * 根据当前的市，更新区WheelView的信息
		 */
		private void updateAreas() {
            pCurrentMin = mViewCity.getCurrentItem();
			month = months[pCurrentMin];
//			days = getperDay();
//			mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(context, days));
		}

		private void updateCities() {
            pCurrentHours = mViewProvince.getCurrentItem();
			year = mYears[pCurrentHours];
			mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(context, months));
			updateAreas();
		}


		@Override
		public void onClick(View v) {
//				showSelectedResult();
		}

		//显示底部 层
		public Dialog showSheetPic(final Context context,final Handler handler,final int code) {
			this.context = context;
			final Dialog dlg = new Dialog(context, R.style.ActionSheet);
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.pick_hour_minie, null);
			final int cFullFillWidth = 10000;
			layout.setMinimumWidth(cFullFillWidth);

			mViewProvince = (WheelView) layout.findViewById(R.id.id_province);
			mViewCity = (WheelView) layout.findViewById(R.id.id_city);
//			mViewDistrict = (WheelView) layout.findViewById(R.id.id_district);

			mBtnConfirm = (TextView) layout.findViewById(R.id.btn_confirm);

	    	mViewProvince.addChangingListener(this);
	    	mViewCity.addChangingListener(this);
//	    	mViewDistrict.addChangingListener(this);
	    	mBtnConfirm.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
                    int ht = (pCurrentHours+1)*2;
                    if (pCurrentMin<1){
                        ht = ht-1;
                    }

					Message msg = new Message();
					msg.what = code;
                    msg.arg1 = ht;   //转换接口要的 1到24
					msg.obj = year + ":" + month;
					handler.sendMessage(msg);
					dlg.dismiss();
				}
			});

//	    	initProvinceDatas(context);

			mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(context, mYears));
			// 设置可见条目数量
			mViewProvince.setVisibleItems(7);
			mViewCity.setVisibleItems(7);
//			mViewDistrict.setVisibleItems(7);

//			Calendar c = Calendar.getInstance();
//			tyear = c.get(Calendar.YEAR);
//			tmonth = c.get(Calendar.MONTH);
//			tm = tyear - 2014;

//			mViewProvince.setCurrentItem(100);
//			mViewCity.setCurrentItem(tmonth);

			updateCities();
//			updateAreas();

			Window w = dlg.getWindow();
			WindowManager.LayoutParams lp = w.getAttributes();
			lp.x = 0;
			final int cMakeBottom = -1000;
			lp.y = cMakeBottom;
			lp.gravity = Gravity.BOTTOM;
			dlg.onWindowAttributesChanged(lp);
			dlg.setCanceledOnTouchOutside(false);
			dlg.setContentView(layout);
			dlg.show();

			return dlg;
		}

		//获取 下一千天日期
		///////////////////////////end
}
