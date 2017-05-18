package com.quark.skillopedia.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quark.skillopedia.R;

/**
 * Created by Administrator on 2016/5/23 0023.
 * 自定义对话框
 */
public class AlertDialog   {


    Context context;
    android.app.AlertDialog ad;
    TextView titleView;
    TextView messageView;
    LinearLayout buttonLayout;
    public AlertDialog(Context context) {
        // TODO Auto-generated constructor stub
        this.context=context;
        ad=new android.app.AlertDialog.Builder(context).create();
        ad.show();
        //关键在下面的两行,使用window.setContentView,替换整个对话框窗口的布局
        Window window = ad.getWindow();
        window.setContentView(R.layout.dialog);

        titleView=(TextView)window.findViewById(R.id.title);
        messageView=(TextView)window.findViewById(R.id.message);
        buttonLayout=(LinearLayout)window.findViewById(R.id.buttonLayout);
    }
    public void setTitle(int resId)
    {
        titleView.setText(resId);
    }
    public void setTitle(String title) {
        titleView.setText(title);
    }
    public void setMessage(int resId) {
        messageView.setText(resId);
    } 	public void setMessage(String message)
    {
        messageView.setText(message);
    }
    /**
     * 设置按钮
     * @param text
     * @param listener
     */
    public void setPositiveButton(String text,final View.OnClickListener listener)
    {
        Button button=new Button(context);
       // LinearLayout.LayoutParams params=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams params  = new LinearLayout.LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        button.setLayoutParams(params);
        button.setBackgroundResource(R.drawable.button1_steps_style);
        button.setText(text);
        button.setTextColor(Color.GRAY);
        button.setTextSize(14);
        button.setOnClickListener(listener);
        buttonLayout.addView(button);
    } 	/**
     * 设置按钮
     * @param text
     * @param listener
     */
    public void setNegativeButton(String text,final View.OnClickListener listener)
    {
        Button button=new Button(context);
        //LinearLayout.LayoutParams params=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams params  = new LinearLayout.LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(params);
        button.setBackgroundResource(R.drawable.button2_steps_style);
        button.setText(text);
        button.setTextColor(Color.WHITE);
        button.setTextSize(14);
        button.setOnClickListener(listener);
        if(buttonLayout.getChildCount()>0)
        {
            params.setMargins(20, 0, 0, 0);
            button.setLayoutParams(params);
            buttonLayout.addView(button, 1);
        }else{
            button.setLayoutParams(params);
            buttonLayout.addView(button);
        }
    }
    /**
     * 关闭对话框
     */
    public void dismiss() {
        ad.dismiss();
    }

}


