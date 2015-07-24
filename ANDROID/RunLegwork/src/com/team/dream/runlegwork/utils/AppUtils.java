package com.team.dream.runlegwork.utils;

import java.text.DecimalFormat;

import org.apache.http.Header;

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
}
