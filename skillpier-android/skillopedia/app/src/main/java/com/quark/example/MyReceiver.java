package com.quark.example;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.paypal.android.sdk.payments.LoginActivity;
import com.quark.skillopedia.AppParam;
import com.quark.skillopedia.AppStart;
import com.quark.skillopedia.mainview.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;


/**
 * 自定义接收器
 *
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
	private static final String TAG = "JPush";

	@Override
	public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
		Log.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
            //send the Registration Id to your server...

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
        	Log.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
        	processCustomMessage(context, bundle);

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知");
            int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
        	processCustomMessage(context, bundle);
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 用户点击打开了通知");
            openNotification(context, bundle);
//            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        	//打开自定义的Activity
        //  to_page：siji_order 跳转到司机更多订单列表
        	//  to_page:bill 用户我的钱包-账单记录页面
//            if(bundle.getString){
//
//            }
//        	Intent i = new Intent(context, MoreOrderActivity.class);
//        	i.putExtras(bundle);
//        	i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );   //在非activity中打开activity需要加
//        	context.startActivity(i);


//        	Intent pm = new Intent(context, PayMoneyLogActivity.class);
//        	pm.putExtras(bundle);
//        	pm.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );   //在非activity中打开activity需要加
//        	context.startActivity(pm);

        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

        } else if(JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
        	boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
        	Log.w(TAG, "[MyReceiver]" + intent.getAction() +" connected state change to "+connected);
        } else {
        	Log.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
        }
	}

	// 打印所有的 intent extra 数据
	private static String printBundle(Bundle bundle) {
		StringBuilder sb = new StringBuilder();
		for (String key : bundle.keySet()) {
			if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
				sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
			}else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
				sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
			}
			else {
				sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
			}
		}
		return sb.toString();
	}

	//send msg to MainActivity
	private void processCustomMessage(Context context, Bundle bundle) {
		if (MainActivity.isForeground) {
//			String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
			String message = bundle.getString("cn.jpush.android.ALERT");
			String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
			Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
			msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
			if (!ExampleUtil.isEmpty(extras)) {
				try {
					JSONObject extraJson = new JSONObject(extras);
					if (null != extraJson && extraJson.length() > 0) {
						msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
					}
				} catch (JSONException e) {

				}
			}
			context.sendBroadcast(msgIntent);
		}
	}

	private void openNotification(Context context, Bundle bundle){
		String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
		String myValue = "";
		String message_id="";
		try {
			JSONObject extrasJson = new JSONObject(extras);
			myValue = extrasJson.optString("type");
			message_id = extrasJson.optString("message_id");
		} catch (Exception e) {
			System.out.println("Unexpected: extras is not a valid json");
			return;
		}
		//先进到 首页 在打开消息页面
		Intent mIntent2 = new Intent(context, AppStart.class);
		mIntent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(mIntent2);

		if(new AppParam().isLogin(context)){
			if (myValue.equals("1")) {
//				Intent mIntent = new Intent(context, MessageListActivity.class);
//				mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				context.startActivity(mIntent);
			}
//			else{
//				Intent mIntent = new Intent(context, MessageListActivity.class);
//				mIntent.putExtra("message_id", message_id);
//				mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				context.startActivity(mIntent);
//			}

		}else{
			Intent intent = new Intent(context, LoginActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);
		}
    }
}
