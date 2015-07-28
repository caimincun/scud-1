package com.team.dream.runlegwork.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

import org.apache.http.Header;

import android.util.Log;

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

}
