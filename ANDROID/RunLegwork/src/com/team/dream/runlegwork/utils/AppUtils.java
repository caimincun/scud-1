package com.team.dream.runlegwork.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.team.dream.runlegwork.singleservice.AccountManager;

public class AppUtils {
	public static void setHeader(Header[] headers) {
		for (int i = 0; i < headers.length; i++) {
			Header header = headers[i];
			if (header.getName().contains("Cookie")) {
				String session;
				if (header.getValue().contains(";")) {
					session = header.getValue().split(";")[0].split("=")[1];

				} else {
					session = header.getValue().split(";")[1];
				}
				AccountManager.sessionid = session;
			}
		}
	}

	public static String DoubleDP(double number, String fm) {
		StringBuffer buffer = new StringBuffer();
		DecimalFormat df = new DecimalFormat(fm);
		if (number < 1.0) {
			buffer.append("0");
		}
		buffer.append(df.format(number));
		return buffer.toString();

	}

	/***
	 * 获取Android Linux内核版本信息
	 */
	public void getLinuxKernalInfo() {
		Process process = null;
		String mLinuxKernal = null;
		try {
			process = Runtime.getRuntime().exec("cat /proc/version");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// get the output line
		InputStream outs = process.getInputStream();
		InputStreamReader isrout = new InputStreamReader(outs);
		BufferedReader brout = new BufferedReader(isrout, 8 * 1024);

		String result = "";
		String line;
		// get the whole standard output string
		try {
			while ((line = brout.readLine()) != null) {
				result += line;
				// result += "\n";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (result != "") {
			String Keyword = "version ";
			int index = result.indexOf(Keyword);
			Log.v("TAG", "----" + result);
			line = result.substring(index + Keyword.length());
			index = line.indexOf(" ");
			// tv01.setText(line.substring(0,index));
			mLinuxKernal = line.substring(0, index);

			Log.d("TAG", "----Linux Kernal is : " + mLinuxKernal);
		}
	}

	public String getLinuxKernalInfoEx() {
		String result = "";
		String line;
		String[] cmd = new String[] { "/system/bin/cat", "/proc/version" };
		String workdirectory = "/system/bin/";
		try {
			ProcessBuilder bulider = new ProcessBuilder(cmd);
			bulider.directory(new File(workdirectory));
			bulider.redirectErrorStream(true);
			Process process = bulider.start();
			InputStream in = process.getInputStream();
			InputStreamReader isrout = new InputStreamReader(in);
			BufferedReader brout = new BufferedReader(isrout, 8 * 1024);

			while ((line = brout.readLine()) != null) {
				result += line;
				// result += "\n";
			}
			in.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.i("TAG", "----Linux Kernal is :" + result);
		return result;
	}

	public String getUserAgentSetInfo() {
		StringBuffer sb = new StringBuffer();
		String version = System.getProperty("java.vm.version");
		if (Integer.valueOf(version.substring(0, version.indexOf("."))) >= 2) {
			sb.append("ART");
		} else {
			sb.append("Dalvik");
		}
		sb.append("/");
		sb.append(version);
		sb.append("(");
		sb.append("Android " + android.os.Build.VERSION.RELEASE);
		sb.append(";");
		sb.append(android.os.Build.MODEL);
		sb.append("/");
		sb.append(android.os.Build.VERSION.INCREMENTAL);
		sb.append(")");
		return sb.toString();
	}

	public static String CheckViewEmpty(String[] strings, View... views) {
		String temp = null;
		int tempIndex = -1;
		for (int i = 0; i < views.length; i++) {
			if (views[i] instanceof EditText) {
				temp = ((EditText) views[i]).getText().toString().trim();
			}
			if (views[i] instanceof TextView) {
				temp = ((TextView) views[i]).getText().toString().trim();
			}
			if (StringUtils.isEmpty(temp)) {
				tempIndex = i;
				break;
			}
		}
		if (tempIndex != -1)
			return strings[tempIndex];
		return "success";
	}

	public static void setListViewHeightBasedOnChildren(ListView listView) {

		ListAdapter listAdapter = listView.getAdapter();

		if (listAdapter == null) {
			return;
		}

		int totalHeight = 0;

		for (int i = 0; i < listAdapter.getCount(); i++) {

			View listItem = listAdapter.getView(i, null, listView);

			listItem.measure(0, 0);

			totalHeight += listItem.getMeasuredHeight();

		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();

		int dividerHeight = listView.getDividerHeight();
		totalHeight += (dividerHeight * (listAdapter.getCount() - 1));
		params.height = totalHeight;

		listView.setLayoutParams(params);

	}

	public static int getListViewHeightBasedOnChildren(ListView listView) {

		ListAdapter listAdapter = listView.getAdapter();

		if (listAdapter == null) {
			return 0;
		}

		int totalHeight = 0;

		for (int i = 0; i < listAdapter.getCount(); i++) {

			View listItem = listAdapter.getView(i, null, listView);

			listItem.measure(0, 0);
			int itemHeight = listItem.getMeasuredHeight()
					+ listItem.getPaddingBottom() + listItem.getPaddingTop();

			totalHeight += itemHeight;

		}

		int dividerHeight = listView.getDividerHeight();
		int padding = listView.getPaddingTop() + listView.getPaddingBottom();
		totalHeight += (dividerHeight * (listAdapter.getCount() - 1)) + padding;

		return totalHeight;

	}

	public static String CheckViewEmpty(String[] toastString,
			String[] checkString) {
		int tempIndex = -1;
		for (int i = 0; i < checkString.length; i++) {

			if (StringUtils.isEmpty(checkString[i])) {
				tempIndex = i;
				break;
			}
		}
		if (tempIndex != -1)
			return toastString[tempIndex];
		return "success";
	}

	public static int getStatusBarHeight(Context context) {
		int result = 0;
		int resourceId = context.getResources().getIdentifier(
				"status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = context.getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 设置Text多种颜色组合，再也不用new一排TextView啦>_<
	 * 
	 * @params.setName(#FFFFFF)的颜色
	 * @params.setValue(str) str为对于颜色的字符
	 */
	public static Spanned setTextSpanColor(List<BasicNameValuePair> params) {
		StringBuffer sb = new StringBuffer();
		String pre = "<font color=\"";
		String colorPostfix = "\">";
		String fontPostfix = "</font>";
		for (BasicNameValuePair valuePair : params) {
			sb.append(pre);
			sb.append(valuePair.getName());
			sb.append(colorPostfix);
			sb.append(valuePair.getValue());
			sb.append(fontPostfix);
		}
		return Html.fromHtml(sb.toString());
	}

	/**
	 * 设置textView的res color setTextColor:
	 * 
	 * @author Administrator
	 * @param textView
	 * @param res
	 * @since JDK 1.7
	 */
	public static void setTextColor(TextView textView, int res) {
		Resources resources = textView.getResources();
		ColorStateList colorStateList = resources.getColorStateList(res);
		textView.setTextColor(colorStateList);
	}
}
