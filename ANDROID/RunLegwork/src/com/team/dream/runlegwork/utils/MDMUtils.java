package com.team.dream.runlegwork.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 全局公共类
 * 
 * @author yb
 * 
 */
public class MDMUtils {


	/**
	 * 子文件目录
	 * @param subFolder
	 * @return
	 */
	public static String getFolderDir(String subFolder) {
		File mFile = null;
		if(isSDCardEnable()) {
		     mFile = new File(getAppSDRootDir(), subFolder);
		} else {
			 mFile = new File(getAppPhoneRootDir(), subFolder);
		}
		
		if(!mFile.exists()) {
			mFile.mkdirs();
		}
		
		return mFile.getAbsolutePath() + File.separator;
	}
	
	public static String getAppSDRootDir() {
		File file = AppContext.mMainContext.getExternalCacheDir();
		if(null != file){
			if(!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			return file.getParent() + File.separator;
		}
		return null;
	}
	
	public static String getAppPhoneRootDir() {
		File file = AppContext.mMainContext.getCacheDir();
		if(!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		return file.getParent() + File.separator;
	}
	
	/**
	 * SD是否可用
	 * 
	 * @return
	 */
	public static boolean isSDCardEnable() {
		try {
			return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 验证url的合法性
	 * 
	 * @param url
	 * @return
	 */
	public static boolean checkUrl(String url) {
		return url.matches("^[a-zA-z]+://[^\\s]*$");
	}

	/**
	 * 验证手机号是否合法
	 * 
	 * @param mobiles
	 * @return boolean
	 */
	public static boolean isMobile(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * 验证邮箱是否合法
	 * 
	 * @param strEmail
	 * @return boolean
	 */
	public static boolean isEmail(String str) {
		if(str == null) {
			return false;
		}
		
		String strPattern = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	public static boolean isNumeric(String str){ 
		if(str == null) {
			return false;
		}
	    Pattern pattern = Pattern.compile("[0-9]+"); 
	    return pattern.matcher(str).matches();    
	} 
	
	public static boolean isAlpha(String str) {
		String strPattern = "[a-zA-Z]+";
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(str);
		return m.matches();
	}
	
	public static boolean isAlphaNumeric(String str) {
		String strPattern = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{2,}$";
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(str);
		return m.matches();
	}
	
	/*
	 * 提取url中的参数
	 */
	public static String getParameter(String url, String name) {
		if (url == null || name == null) {
			return null;
		}

		int start = url.indexOf(name + "=");
		if (start == -1)
			return null;
		int len = start + name.length() + 1;
		int end = url.indexOf("&", len);
		if (end == -1) {
			end = url.length();
		}

		return url.substring(len, end);
	}




	/**
	 * WIFI网络开关
	 * 
	 * @param mContext
	 * @param enabled
	 */
	public static final void toggleWiFi(Context mContext, boolean enabled) {
		WifiManager wm = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
		wm.setWifiEnabled(enabled);
	}

	/**
	 * 移动网络开关
	 * 
	 * @param context
	 * @param enabled
	 */
	public static final void toggleMobileData(Context mContext, boolean enabled) {
		ConnectivityManager conMgr = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

		Class<?> conMgrClass = null; // ConnectivityManager类
		Field iConMgrField = null; // ConnectivityManager类中的字段
		Object iConMgr = null; // IConnectivityManager类的引用
		Class<?> iConMgrClass = null; // IConnectivityManager类
		Method setMobileDataEnabledMethod = null; // setMobileDataEnabled方法

		try {
			// 取得ConnectivityManager类
			conMgrClass = Class.forName(conMgr.getClass().getName());
			// 取得ConnectivityManager类中的对象mService
			iConMgrField = conMgrClass.getDeclaredField("mService");
			// 设置mService可访问
			iConMgrField.setAccessible(true);
			// 取得mService的实例化类IConnectivityManager
			iConMgr = iConMgrField.get(conMgr);
			// 取得IConnectivityManager类
			iConMgrClass = Class.forName(iConMgr.getClass().getName());
			// 取得IConnectivityManager类中的setMobileDataEnabled(boolean)方法
			setMobileDataEnabledMethod = iConMgrClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
			// 设置setMobileDataEnabled方法可访问
			setMobileDataEnabledMethod.setAccessible(true);
			// 调用setMobileDataEnabled方法
			setMobileDataEnabledMethod.invoke(iConMgr, enabled);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭软键盘
	 */
	public static final void hideSoftInput(Context mContext) {
		InputMethodManager imm = (InputMethodManager)AppContext.mMainContext.getSystemService(Context.INPUT_METHOD_SERVICE);
		if(mContext != null && mContext instanceof Activity && ((Activity)mContext).getCurrentFocus() != null) {			
			imm.hideSoftInputFromWindow(((Activity)mContext).getCurrentFocus().getWindowToken(), 0);
		}		
	}
	
	public static final void showSoftInput(Context mContext) {
		InputMethodManager imm = (InputMethodManager)AppContext.mMainContext.getSystemService(Context.INPUT_METHOD_SERVICE);	
		if(mContext != null && mContext instanceof Activity && ((Activity)mContext).getCurrentFocus() != null) {				
			imm.showSoftInput(((Activity)mContext).getCurrentFocus(), InputMethodManager.SHOW_FORCED);
		}		
	}
	
	public static String getPhoneNumber(Context mContext) {
		TelephonyManager tm = (TelephonyManager)mContext.getSystemService(Context.TELEPHONY_SERVICE);		
		return tm.getLine1Number();     
	}
	
	/**
	 * 半角转换为全角 
	 * @param input
	 * @return
	 */
	public static String toDBC(String input) {
	    char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 32) {
                c[i] = (char) 12288;
                continue;
            }
            if (c[i] < 127 && c[i] > 32)
                c[i] = (char) (c[i] + 65248);
        }
        return new String(c);
	}

   /**
    * 获取Applicatin中元数据
    * @param mContext
    * @param key
    * @return
    */
   public static String getMetaData(Context mContext, String key) {
	   String value = "";
		try {
			ApplicationInfo appInfo = mContext.getPackageManager().getApplicationInfo(mContext.getPackageName(), PackageManager.GET_META_DATA);
			value = appInfo.metaData.get(key).toString();			
		} catch (Exception e) {			
		}
		return value;
   }
   
   /**
	 * 安装应用
	 * 
	 * @param context
	 *            上下文
	 * @param apkName
	 *            apk全名
	 */
	public static final void setupApp(Context context, String apkPath) {
		
		try{
			Intent intent = new Intent(Intent.ACTION_VIEW);
			File file = new File(apkPath);
			intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
			context.startActivity(intent);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取安装应用版本号
	 * 
	 * @param mContext
	 * @param packageName
	 * 
	 */
	public static final String getVersion(Context mContext,String packageName){
		String version = "";
		try{
			PackageManager pm = mContext.getPackageManager();
			 PackageInfo info = pm.getPackageInfo(packageName,0);
		        if (info != null) {
		        	version = String.valueOf(info.versionName);
		        }
		}catch (Exception e) {
			//e.printStackTrace();
		}
		
		return version;
	}
	
	public static final String getVersionCode(Context mContext,String packageName){
		String versionCode = "";
		try{
			PackageManager pm = mContext.getPackageManager();
			 PackageInfo info = pm.getPackageInfo(packageName,0);
		        if (info != null) {
		        	versionCode = String.valueOf(info.versionCode);
		        }
		}catch (Exception e) {
			//e.printStackTrace();
		}		
		return versionCode;
	}
	
	public static final PackageInfo getPackageInfoByPath(Context mContext,String mFilePath) {		
		try{
			PackageManager pm = mContext.getPackageManager();
			PackageInfo info = pm.getPackageArchiveInfo(mFilePath, PackageManager.GET_ACTIVITIES); 
			return info;
		}catch (Exception e) {
			//e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * 获取未安装应用版本号
	 * 
	 * @param mContext
	 * @param packageName
	 * 
	 */
	public static final String getVersionByPath(Context mContext,String mFilePath){
		String version = "";
		try{
			PackageManager pm = mContext.getPackageManager();
			PackageInfo info = pm.getPackageArchiveInfo(mFilePath, PackageManager.GET_ACTIVITIES); 
			if (info != null) {
				version = String.valueOf(info.versionName);
			}
		}catch (Exception e) {
			//e.printStackTrace();
		}
		
		return version;
	}
	
	public static final boolean isServiceStart(Class<?> className) {
		try {
			ActivityManager mActivityManager = (ActivityManager) AppContext.mMainContext.getSystemService(Context.ACTIVITY_SERVICE);
			List<ActivityManager.RunningServiceInfo> serviceList = mActivityManager.getRunningServices(30);
			for (int i = 0; i < serviceList.size(); i++) {				
				if (serviceList.get(i) != null && serviceList.get(i).service.getPackageName().equals(AppContext.mMainContext.getPackageName()) && serviceList.get(i).service.getClass().getName().equals(className.getName())) {
					return true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	public final static boolean isScreenLocked() {
		android.app.KeyguardManager mKeyguardManager = (KeyguardManager) AppContext.mMainContext.getSystemService(Context.KEYGUARD_SERVICE);
		return mKeyguardManager.inKeyguardRestrictedInputMode();
	}
	
	public final static boolean isUseApp() {
		 ActivityManager am = (ActivityManager) AppContext.mMainContext.getSystemService(Context.ACTIVITY_SERVICE);
	     ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
	     if(cn.getPackageName().equals(AppContext.mMainContext.getPackageName())&& !isScreenLocked()) {
	    	 return true;
	     } else {
	    	 return false;
	     }
	}
	
	public final static boolean isCurrentActivity(Object obj) {
		try {
			ActivityManager am = (ActivityManager) AppContext.mMainContext.getSystemService(Context.ACTIVITY_SERVICE);
			ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
			
			String className = "";
			if (obj != null && obj instanceof View) {
				className = ((View) obj).getContext().getClass().getName();
			} else if (obj != null && obj instanceof Context) {
				className = obj.getClass().getName();
			}

			return cn.getClassName().equals(className) ? true : false;
		} catch (Exception e) {
			// TODO: handle exception
		}
	    return false;
	}
	
	public static final boolean isAppInFront() {
		try {
			ActivityManager am = (ActivityManager) AppContext.mMainContext.getSystemService(Context.ACTIVITY_SERVICE);
			ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
			String switchPackageName = cn.getPackageName();
			String localPackageName = AppContext.mMainContext.getPackageName();
			return localPackageName.endsWith(switchPackageName);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;		
	}
	
	
	public final static String getRealPathFromURI(Uri contentUri) {
		try {
			String[] proj = { MediaStore.Images.Media.DATA };
			ContentResolver mResolver = AppContext.mMainContext.getContentResolver();
			Cursor cursor = mResolver.query(contentUri, proj, null, null, null);
			int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			return cursor.getString(column_index);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	/**
	 * 获取手机的imei号
	 * 
	 * @return
	 */
	public static final String getIMEI(Context mContext) {
		String android_id = "";
		try {
			TelephonyManager telManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
			android_id = telManager.getDeviceId();
			if (android_id == null) {
				android_id = android.provider.Settings.System.getString(mContext.getContentResolver(), "android_id");
			}
		} catch (Exception e) {
		}
		return android_id;
	}
	
	/**
	 * 获取手机的imsi号
	 * 
	 * @return
	 */
	public static final String getIMSI(Context mContext) {
		String id = "";
		try {
			TelephonyManager telManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
			id = telManager.getSubscriberId();
		} catch (Exception e) {
		}
		return id;
	}
	
	public static CharSequence getHtmlString(String txt) {
		try {
			CharSequence html = Html.fromHtml("<font color='black'>" + txt + "\u3000\u3000</font>"); 			
            return html;
		} catch (Exception e) {
			
		}
		return  null;
	}
	
	/**
	 * 是否是平板
	 * @param context
	 * @return
	 */
	public static boolean isTabletDevice(Context context) {
		int screenLayout = context.getResources().getConfiguration().screenLayout;
		int cur = screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
		if (cur == Configuration.SCREENLAYOUT_SIZE_UNDEFINED)
			return false;
		return cur >= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}	
	
	/**
	 * 获取Unicode编码
	 * @param gbString
	 * @return
	 */
	public static String encodeUnicode(String gbString) {     
        char[] utfBytes = gbString.toCharArray();     
        String unicodeBytes = "";     
        for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {     
            String hexB = Integer.toHexString(utfBytes[byteIndex]);     
            if (hexB.length() <= 2) {     
                hexB = "00" + hexB;     
            }     
            unicodeBytes = unicodeBytes + "\\u" + hexB;     
        }    
        return unicodeBytes;
    }  
	
	/**
	 * 比较系统版本号
	 * 
	 * @param v1
	 * @param v2
	 * @return 0--等；-1--小；1--大
	 */
	public static int compareVersion(String v1, String v2) {
		if (v1 == null) {
			return -1;
		}

		if (v2 == null) {
			return 1;
		}

		if (v1.equals(v2)) {
			return 0;
		}

		String[] arrayStr1 = v1.split("\\.");
		String[] arrayStr2 = v2.split("\\.");

		int leng1 = arrayStr1.length;
		int leng2 = arrayStr2.length;
		int leng = (leng1 > leng2) ? leng2 : leng1;

		int result = 0;
		for (int i = 0; i < leng; i++) {
			result = arrayStr1[i].compareTo(arrayStr2[i]);
			if (result != 0) {
				return result > 0 ? 1 : -1;
			}
		}

		if (result == 0) {
			if (leng1 > leng2) {
				result = 1;
			} else if (leng1 < leng2) {
				result = -1;
			}
		}

		return result;
	}	
	
	public static String fromFenToYuan(int fen) {
		DecimalFormat format = new DecimalFormat("0.##");
		format.setMaximumFractionDigits(2);
		
		return format.format((float)fen / 100);
	}
	
	/**
	 * 应用是否第一次安装
	 * @param context
	 * @return
	 */
	public static boolean isFirstRun() {
		try {
			AppContext.mMainContext.openFileInput(AppContext.mMainContext.getPackageName());
			return false;
		} catch (Exception e) {
		}
		return true;
	}
	
	/**
	 * 设置安装标志位
	 * 
	 * @param context
	 * @return
	 */
	public static void setFirstRun(boolean isFirst) {
		try {
			if (isFirst) {
				AppContext.mMainContext.deleteFile(AppContext.mMainContext.getPackageName());
			} else {
				AppContext.mMainContext.openFileOutput(AppContext.mMainContext.getPackageName(), Context.MODE_WORLD_WRITEABLE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 七牛图片处理 具体参数参考：http://developer.qiniu.com/docs/v6/api/reference/fop/image/imageview2.html
	 * @param url
	 * @param mode
	 * @param w
	 * @param h
	 * @param format
	 * @return
	 */
	public static String dealQiNiuImageUrl(String url, int mode, int w, int h, String format) {
		try {
			if(url != null && (url.contains("clouddn") || url.contains("qiniucdn") || url.contains("qiniudn"))) {
				url += "?" + "imageView2/" + mode + "/w/" + w + "/h/" + h+ "/format/" + format; 
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return url;
	}
}