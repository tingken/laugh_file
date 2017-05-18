package com.quark.skillopedia.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;


/**
 * @author SunnyCoffee
 * @create 2013-10-24
 * @version 1.0
 * @desc 自定义Listview　下拉刷新,上拉加载更多
 */

public class AutoGridViewForScrollView extends GridView {
	public AutoGridViewForScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AutoGridViewForScrollView(Context context) {
		super(context);
	}

	public AutoGridViewForScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
