package com.team.dream.runlegwork.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class StringUtils {

	public static boolean isEmpty(String str) {
		return ("" == str || null == str || str.length() == 0);
	}

	public static String utf8Encode(String str) {
		if (!isEmpty(str) && str.getBytes().length != str.length()) {
			try {
				return URLEncoder.encode(str, "utf-8");
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException("UnsupportedEncodingException occurred. ", e);
			}
		}
		return str;
	}
}
