package com.quark.swipedelete;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class SwipeLayout extends FrameLayout {

	private View contentView;
	private View deleteView;
	private int contentWidth,contentHeight;
	private int deleteWidth;
	private ViewDragHelper viewDragHelper;
	
	enum SwipeState{
		Open,Close
	}
	private SwipeState mState = SwipeState.Close;//默认状态是关闭

	public void closeState(){
        SwipeLayoutManager.getInstance().clearOpenedSwipeLayout();
	}

	public SwipeLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public SwipeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public SwipeLayout(Context context) {
		super(context);
		init();
	}
	
	private void init(){
		viewDragHelper = ViewDragHelper.create(this, callback);
	}
	
	public SwipeState getSwipeState(){
		return mState;
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		contentView = getChildAt(0);
		deleteView = getChildAt(1);
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		contentWidth = contentView.getMeasuredWidth();
		contentHeight = contentView.getMeasuredHeight();
		deleteWidth = deleteView.getMeasuredWidth();
	}
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		contentView.layout(0, 0,contentWidth,contentHeight);
		deleteView.layout(contentView.getRight(),0,contentView.getRight()+deleteWidth,
				contentHeight);
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		boolean result = viewDragHelper.shouldInterceptTouchEvent(ev);
		if(!SwipeLayoutManager.getInstance().isShouldSwipe(SwipeLayout.this)){
			//并且需要关闭已经打开的SwipeLayout
			SwipeLayoutManager.getInstance().closeSwipeLayout();
			result = true;
		}
		return result;
	}
	
	private float downX,downY;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//需要判断当前是否可以滑动
		if(!SwipeLayoutManager.getInstance().isShouldSwipe(SwipeLayout.this)){
			requestDisallowInterceptTouchEvent(true);//请求不让父View拦截事件
			return true;//下面代码都不执行了
		}
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downX = event.getX();
			downY = event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			//1.获取移动的坐标
			float moveX = event.getX();
			float moveY = event.getY();
			//2.获取x和y方向移动的距离
			float deltaX = moveX - downX;
			float deltaY = moveY - downY;
			//3.判断用户滑动的方向是偏向于水平还是垂直
			if(Math.abs(deltaX)> Math.abs(deltaY)){
				//说明偏向于水平方向，就认为用户是想左右滑动的
				requestDisallowInterceptTouchEvent(true);//请求不让父View拦截事件
			}
			break;
		case MotionEvent.ACTION_UP:
			
			break;
		}
		viewDragHelper.processTouchEvent(event);
		return true;
	}
	
	private ViewDragHelper.Callback callback = new Callback() {
		@Override
		public boolean tryCaptureView(View child, int pointerId) {
			return child==contentView || child==deleteView;
		}
		@Override
		public int getViewHorizontalDragRange(View child) {
			return deleteWidth;
		}

		@Override
		public int clampViewPositionHorizontal(View child, int left, int dx) {
			if(child==contentView){
				if(left>0)left = 0;
				if(left<-deleteWidth)left = -deleteWidth;
			}else if (child==deleteView) {
				if(left<(getMeasuredWidth()-deleteWidth))left = getMeasuredWidth()-deleteWidth;
				if(left>contentWidth)left = contentWidth;
			}
			return left;
		}
		@Override
		public void onViewPositionChanged(View changedView, int left, int top,
				int dx, int dy) {
			super.onViewPositionChanged(changedView, left, top, dx, dy);
			if(changedView==contentView){
				//需要让deleteView伴随移动
				int newLeft = deleteView.getLeft()+dx;
				deleteView.layout(newLeft,deleteView.getTop(),newLeft+deleteWidth,deleteView.getBottom());
			}else if (changedView==deleteView) {
				//需要让deleteView伴随移动
				int newLeft = contentView.getLeft()+dx;
				contentView.layout(newLeft,contentView.getTop(),newLeft+contentWidth,contentView.getBottom());
			}
			
			//对mState更改进行逻辑判断
			if(contentView.getLeft()==0 && mState!= SwipeState.Close){
				mState = SwipeState.Close;
				
				//需要让SwipeLayoutManager清除已经记录的
				SwipeLayoutManager.getInstance().clearOpenedSwipeLayout();
			}else if (contentView.getLeft()==-deleteWidth && mState!= SwipeState.Open) {
				mState = SwipeState.Open;
				
				//需要让SwipeLayoutManager记录已经关闭的
				SwipeLayoutManager.getInstance().setOpenedSwipeLayout(SwipeLayout.this);
			}
		}

		@Override
		public void onViewReleased(View releasedChild, float xvel, float yvel) {
			super.onViewReleased(releasedChild, xvel, yvel);
			if(contentView.getLeft()<-deleteWidth/2){
				//应该打开
				open();
			}else {
				//应该关闭
				close();
			}
		}
	};
	@Override
	public void computeScroll() {
		if(viewDragHelper.continueSettling(true)){
			ViewCompat.postInvalidateOnAnimation(SwipeLayout.this);
		}
	}

	public void open() {
		viewDragHelper.smoothSlideViewTo(contentView,-deleteWidth,contentView.getTop());
		ViewCompat.postInvalidateOnAnimation(SwipeLayout.this);
	}

	public void close() {
		viewDragHelper.smoothSlideViewTo(contentView,0,contentView.getTop());
		ViewCompat.postInvalidateOnAnimation(SwipeLayout.this);
	}
	
}
