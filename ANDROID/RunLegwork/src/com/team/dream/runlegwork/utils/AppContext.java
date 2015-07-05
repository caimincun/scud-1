package com.team.dream.runlegwork.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

/**
 * 记录应用全局信息
 * 
 * @author yb
 * 
 */
public final class AppContext {

	public static final Handler	mMainHandler	= new Handler(Looper.getMainLooper());	// 公共Handler
		
	/*
	 * 初始化上下问
	 */
	public static Context		mMainContext	= null;	
	public static Context		mCurrentContext	= null;//当前Activity的上下文	
	
	public static void init(Context MainContext) {
		mMainContext = MainContext.getApplicationContext();			
	}

	/**
	 * 销毁全局变量
	 */
	public static void destory() {
		
	}

}
