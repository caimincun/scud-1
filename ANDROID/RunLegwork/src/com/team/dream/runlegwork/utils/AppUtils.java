package com.team.dream.runlegwork.utils;

import org.apache.http.Header;

import com.team.dream.runlegwork.singleservice.AccountManager;

public  class AppUtils {
	public static void setHeader(Header[] headers) {
		for (int i = 0; i < headers.length; i++) {
			Header header = headers[i];
			if (header.getName().contains("Cookie")) {
				String session;
				if (header.getValue().contains(";")) {
					session = header.getValue().split(";")[0]
							.split("=")[1];
					
				} else {
					session = header.getValue().split(";")[1];
				}
				AccountManager.sessionid = session;
			}
		}
	}
}
