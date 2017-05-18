package com.quark.swipedelete;

/**
 * 统一管理所有的SwipeLayout，并且只允许一个是打开的
 * @author Administrator
 *
 */
public class SwipeLayoutManager {
	private static SwipeLayoutManager mInstance = new SwipeLayoutManager();
	private SwipeLayoutManager(){}
	
	public static SwipeLayoutManager getInstance(){
		return mInstance;
	}
	
	private SwipeLayout openedLayout;//用来记录已经打开的SwipeLayout
	public void setOpenedSwipeLayout(SwipeLayout openedLayout){
		this.openedLayout = openedLayout;
	}
	
	/**
	 * 清除当前记录的打开的SwipeLayout
	 */
	public void clearOpenedSwipeLayout(){
		openedLayout = null;
	}
	/**
	 * 关闭当前已经打开的SwipeLayout
	 */
	public void closeSwipeLayout(){
		if(openedLayout!=null){
			openedLayout.close();
		}
	}
	/**
	 * 判断当前是否可以继续滑动
	 * swipeLayout: 当前所滑动的SwipeLayout
	 * @return
	 */
	public boolean isShouldSwipe(SwipeLayout swipeLayout){
		if(openedLayout==null){
			return true;
		}else {
			//说明当前已经有打开的SwipeLayout了，
			return openedLayout==swipeLayout;
		}
	}
}
