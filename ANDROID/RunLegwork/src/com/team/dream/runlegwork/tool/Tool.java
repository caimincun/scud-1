package com.team.dream.runlegwork.tool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.R.style;
/**
 * 工具类
 * @author Administrator
 *
 */
@SuppressLint("NewApi")
public class Tool {
	private static final String tag = Tool.class.getSimpleName();
	/** 是否已出现异常 **/
	public static boolean IS_ERROR = false;
	public static Dialog mDialog;
	/**
	 * 文件是否存在
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean isFileExist(String filePath) {
		boolean isExist = false;
		if (null != filePath && !"".equals(filePath)) {
			File file = new File(filePath);
			if (null != file && file.exists()) {
				isExist = true;
			}
		}
		return isExist;
	}

	/**
	 * 文件是否存在
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean isFileExist(String[] filePath) {
		boolean isExist = true;
		if (null != filePath && !"".equals(filePath) && filePath.length > 0) {
			for (int i = 0; i < filePath.length; i++) {
				File file = new File(filePath[i]);
				if (null == file || !file.exists()) {
					isExist = false;
				}
			}
		} else {
			isExist = false;
		}
		return isExist;
	}

	/**
	 * 向SD卡写入数据
	 * 
	 * @param bytes
	 * @param path
	 * @param isAppend
	 */
	public static void saveBytes2SDcardFile(byte[] bytes, String path, boolean isAppend) {
		try {
			File file = new File(path);
			File dir = new File(file.getParent());
			if (!dir.exists()) {
				dir.mkdirs();
				dir.setWritable(true);
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(path, isAppend);
			fos.write(bytes);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
     * 保存图片到指定路径
     * @param bitmap 图像数据
     * @param path 保存的路径
     * @param isApend 是否追加保存
     * @param quality 图像质量
     */
	@SuppressLint("NewApi")
	public synchronized static void saveBitmap2Folder(Bitmap bitmap,String path,boolean isApend,int quality,String fileType){
		if(null==bitmap || bitmap.isRecycled()){
			Log.d(path, "保存图片_图片已不存在");
			return;
		}
		try {
			File file = new File(path);
			File dir = new File(file.getParent());
			if (!dir.exists()) {
				dir.mkdirs();
				dir.setWritable(true);
			}
			if(!file.exists()){
				file.createNewFile();
			}
			String fileName = file.getName();
			int lastDotIndex = fileName.lastIndexOf('.');
			Bitmap.CompressFormat cf = Bitmap.CompressFormat.JPEG;
			Log.d(tag, "保存图片："+path);
			Log.d(tag, "保存图片_[lastDotIndex,fileName,fileType]=["+lastDotIndex+","+fileName+","+fileType+"]");
			if("jpeg".equalsIgnoreCase(fileType)||"jpg".equalsIgnoreCase(fileType)){
				Log.d(tag, "保存图片:jpeg");
				cf = Bitmap.CompressFormat.JPEG;
			}else if("png".equalsIgnoreCase(fileType)){
				Log.d(tag, "保存图片:png");
				cf = Bitmap.CompressFormat.PNG;
			}else if("img".equalsIgnoreCase(fileType)){
				cf = Bitmap.CompressFormat.PNG;
				Log.d(tag, "保存图片:img");
			}else{
				Log.d(tag, "Else:jpg");
				cf = Bitmap.CompressFormat.JPEG;
			}
			FileOutputStream out = new FileOutputStream(file,isApend);
			Log.d(tag, "保存图片_bitmap.isRecycle = " + bitmap.isRecycled());
			if (bitmap.compress(cf, quality, out)) {
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 强制隐藏软件键盘
	 * @param context
	 * @param focusView 当前获得焦点的View
	 */
	public static void hiddenSoftKeyboard(Context context, View focusView){
	    try {
			((InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(focusView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		} catch (Exception e) {
			Log.w(tag, "hiddenSoftKeyboard exception");
		}
	}
	/**
	 * 显示Dialog弹出框
	 * @param context 必须使用Activity
	 * @param dialogContentView 显示的弹出框View
	 * @param cancelable 是否可取消
	 * @param canceledOnTouchOutside 是否可以通过点击Dialog区域外的地方关闭Dialog
	 */
	public static void showAlertDialog(final Context context,View dialogContentView,boolean cancelable,boolean canceledOnTouchOutside){
		if(null!=mDialog){
			if(mDialog.isShowing()){
				mDialog.cancel();
			}
		}
		mDialog = new Dialog(context,R.style.CustomDialog);
		mDialog.setOnCancelListener(new OnCancelListener() {
			@Override
			public void onCancel(DialogInterface arg0) {
				hiddenSoftKeyboard(context,((Activity)context).getCurrentFocus());
			}
		});
		mDialog.setCancelable(cancelable);
		mDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
		mDialog.show();
		mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
		mDialog.setContentView(dialogContentView);
	}
	
	/**
	 * 关闭Dialog弹出框
	 */
	public static void cancelAlertDialog(){
		if(null!=mDialog && mDialog.isShowing()){
			mDialog.cancel();
		}
	}
	/**
	 * Map转成Json
	 * @param map
	 * @return
	 */
	public static String map2JsonString(Map<String,String> map) {
		StringBuffer jsonStr = new StringBuffer();
		if (!map.isEmpty()) {
			jsonStr.append("{");
			// 遍历map
			for (String key : map.keySet()) {
				if(null != map.get(key)){
					jsonStr.append("\"" + key + "\":");
					jsonStr.append("\"" + map.get(key) + "\",");
				}
			}
			if (jsonStr.length() > 1) {
				jsonStr = new StringBuffer(jsonStr.substring(0, jsonStr.length() - 1));
			}
			jsonStr.append("}");
		}
		return jsonStr.toString();
	}
	
	public static void showToast(Context ctx,String msg){
		Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
	}
	/**
	 * 获取日期差
	 * @param date1 之前日期
	 * @param date2 今天日期
	 * @return
	 */
	 public static int getBetweenDay(Date date1, Date date2) {  
	        Calendar d1 = new GregorianCalendar();  
	        d1.setTime(date1);  
	        Calendar d2 = new GregorianCalendar();  
	        d2.setTime(date2);  
	        int days = d2.get(Calendar.DAY_OF_YEAR)- d1.get(Calendar.DAY_OF_YEAR);  
	        System.out.println("days="+days);  
	        int y2 = d2.get(Calendar.YEAR);  
	        if (d1.get(Calendar.YEAR) != y2) {  
//	          d1 = (Calendar) d1.clone();  
	            do {  
	                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);  
	                d1.add(Calendar.YEAR, 1);  
	            } while (d1.get(Calendar.YEAR) != y2);  
	        }  
	        return days;  
	    }  
	 
	 public static String downloadPics(String urlPath, String fileName, String album_path) throws Exception{
			URL url = new URL(urlPath);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setConnectTimeout(5000);
	        conn.setRequestMethod("GET");
	        conn.setDoInput(true);
	        if (conn.getResponseCode() == 200) {
	            InputStream is = conn.getInputStream();
	            FileOutputStream fos = new FileOutputStream(album_path + "/" + fileName);
	            byte[] buffer = new byte[1024];
	            int len = 0;
	            while ((len = is.read(buffer)) != -1) {
	                fos.write(buffer, 0, len);
	            }
	            is.close();
	            fos.close();
	            // 返回一个URI对象
	            return Uri.fromFile(new File(album_path + "/" + fileName)).toString();
	        }
	        return null;
		}
}
