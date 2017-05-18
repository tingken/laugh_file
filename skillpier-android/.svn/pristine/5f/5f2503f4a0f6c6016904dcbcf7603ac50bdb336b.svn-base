package com.quark.skillopedia.ui.widget;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quark.skillopedia.R;

import java.util.ArrayList;
import java.util.List;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;

@SuppressLint("NewApi")
public class WheelChooseCategoryDialog implements View.OnClickListener, OnWheelChangedListener {
    List<com.quark.api.auto.bean.Categorys> Categorys;
	private static WheelView mViewProvince;
	private static WheelView mViewCity;
	private static TextView mBtnConfirm;
	public Context context;
	protected String[] mYears;
//	String[][] months;
    ArrayList<String[]> months = new ArrayList<>();
	protected String year;
	protected String month;
    int pOneCurrent,pTwoCurrent;

	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		if (wheel == mViewProvince) {
			updateCities();
		} else if (wheel == mViewCity) {
			updateAreas();
		}
	}

	private void updateAreas() {
		pTwoCurrent = mViewCity.getCurrentItem();
		month = months.get(pOneCurrent)[pTwoCurrent];
	}


	private void updateCities() {
		pOneCurrent = mViewProvince.getCurrentItem();
		year = mYears[pOneCurrent];
		mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(context, months.get(pOneCurrent)));
		updateAreas();
	}


	@Override
	public void onClick(View v) {

	}

	public Dialog showSheetPic(final Context context, final Handler handler, final int code,List<com.quark.api.auto.bean.Categorys> Categorys) {
		this.context = context;
        this.Categorys = Categorys;
        year = Categorys.get(0).getCategory_01_name();
        if (Categorys.get(0).getCategory02s().size()>0){
            month = Categorys.get(0).getCategory02s().get(0).getCategory_02_name();
        }

        mYears = new String[Categorys.size()];

        for (int i=0;i<Categorys.size();i++){
            mYears[i] = Categorys.get(i).getCategory_01_name();
            String[] month = new String[Categorys.get(i).getCategory02s().size()];
            for (int j=0;j<Categorys.get(i).getCategory02s().size();j++){
				try {
					String dsf =  Categorys.get(i).getCategory02s().get(j).getCategory_02_name();
                    month[j] = dsf;
				}catch (Exception e){
					Log.e("eror",e.getMessage());
				}
            }
            months.add(month);
        }

		final Dialog dlg = new Dialog(context, R.style.ActionSheet);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.pick_category, null);
		final int cFullFillWidth = 10000;
		layout.setMinimumWidth(cFullFillWidth);

		mViewProvince = (WheelView) layout.findViewById(R.id.id_province);
		mViewCity = (WheelView) layout.findViewById(R.id.id_city);
		mBtnConfirm = (TextView) layout.findViewById(R.id.btn_confirm);
		mViewProvince.addChangingListener(this);
		mViewCity.addChangingListener(this);

		mBtnConfirm.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Message msg = new Message();
				msg.what = code;
                msg.arg1 = pOneCurrent;
                msg.arg2 = pTwoCurrent;
				msg.obj = year + " " + month;
				handler.sendMessage(msg);
				dlg.dismiss();
			}
		});

		mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(context, mYears));
		mViewProvince.setVisibleItems(7);
		mViewCity.setVisibleItems(7);
		updateCities();

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

}