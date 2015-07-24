package com.team.dream.runlegwork.singleservice;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Syseting {

	private static final String SETTING = "setting";
	private static final String IS_FIRST_USE = "is_fist_use";
	private Context cxt;

	public Syseting(Context cxt) {
		this.cxt = cxt;
	}

	public void writeSetting(String key, boolean value) {
		SharedPreferences mSharedPreferences = cxt.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
		Editor mEditor = mSharedPreferences.edit();
		mEditor.putBoolean(key, value);
		mEditor.commit();
	}

	public void writeSetting(boolean value) {
		SharedPreferences mSharedPreferences = cxt.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
		Editor mEditor = mSharedPreferences.edit();
		mEditor.putBoolean(IS_FIRST_USE, value);
		mEditor.commit();
	}

	public boolean isFirstUse() {
		SharedPreferences mSharedPreferences = cxt.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
		return mSharedPreferences.getBoolean(IS_FIRST_USE, true);

	}

	// ****************************************************************
	// 判断应用是否初次加载，读取SharedPreferences中的guide_activity字段
	// ****************************************************************
	// private static final String SHAREDPREFERENCES_NAME = "my_pref";
	// private static final String KEY_GUIDE_ACTIVITY = "guide_activity";
	//
	// @SuppressLint("WorldReadableFiles")
	// @SuppressWarnings("deprecation")
	// public boolean isFirstEnter(String className) {
	// if (cxt == null || className == null || "".equalsIgnoreCase(className))
	// return false;
	// String mResultStr = cxt.getSharedPreferences(SHAREDPREFERENCES_NAME,
	// Context.MODE_WORLD_READABLE).getString(KEY_GUIDE_ACTIVITY, "");// 取得所有类名
	// // 如
	// // com.my.MainActivity
	// if (mResultStr.equalsIgnoreCase("false"))
	// return false;
	// else
	// return true;
	// }

}