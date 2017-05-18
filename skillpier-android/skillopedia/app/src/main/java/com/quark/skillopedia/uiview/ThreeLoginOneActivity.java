package com.quark.skillopedia.uiview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.quark.skillopedia.R;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.ui.widget.CircularImage;

import butterknife.ButterKnife;
import butterknife.OnClick;


@SuppressLint("ResourceAsColor")
public class ThreeLoginOneActivity extends BaseActivity {
    
	public static ThreeLoginOneActivity instanse;
	String u_type;
	String uid;
//	String name;
//	String profile_image_url;
//	String gender;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		setContentView(R.layout.threelogin_choose);
		ButterKnife.inject(this);

		setTopTitle("联合登录");
		setBackButton();
		instanse = this;
		getViews();
		u_type = (String)getValue4Intent("u_type");
		uid = (String)getValue4Intent("uid");
//		name = (String)getValue4Intent("name");
//		profile_image_url = (String)getValue4Intent("profile_image_url");
//		gender = (String)getValue4Intent("gender");
		
//		ApiHttpClient.loadImage(profile_image_url, mImageView_pic);
		String typetip = null;
		if (u_type.equals("1")) {
			typetip="亲爱的Facebook用户：";
		}else if(u_type.equals("2")){
			typetip="亲爱的Twitter用户：";
		}
		mTextView_type.setText(typetip);
//		mTextView_name.setText(name);
		
	}
	
	private CircularImage mImageView_pic;
	private TextView mTextView_type;
	private TextView mTextView_name;
	private TextView mTextView_toregist;
	private TextView mTextView_tobind;
	public void getViews(){
		mImageView_pic = (CircularImage)findViewById(R.id.pic);
		mTextView_type = (TextView)findViewById(R.id.type);
		mTextView_name = (TextView)findViewById(R.id.name);
		mTextView_toregist = (TextView)findViewById(R.id.toregist);
		mTextView_tobind = (TextView)findViewById(R.id.tobind);
	}
	
	@OnClick(R.id.toregist)
	public void torg(View v){
		Bundle bundle = new Bundle();
		bundle.putString("u_type", u_type);
		bundle.putString("uid", uid);
		startActivityByClass(FreesignActivity.class, bundle);
//		finish();
	}
	
	@OnClick(R.id.tobind)
	public void tobind(View v){
		Bundle bundle = new Bundle();
		bundle.putString("u_type", u_type);
		bundle.putString("uid", uid);
//		bundle.putString("nickname", name);
//		bundle.putString("icon_url", profile_image_url);
//		bundle.putString("birthday", "");
//		bundle.putString("gender", gender);

		startActivityByClass(ThreeLoginBindActivity.class, bundle);
//		finish();
	}

	@Override
	public void initView() {

	}

	@Override
	public void initData() {

	}
}






