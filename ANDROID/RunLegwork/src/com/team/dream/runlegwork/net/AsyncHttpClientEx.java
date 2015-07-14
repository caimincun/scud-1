package com.team.dream.runlegwork.net;

import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.ResponseHandlerInterface;
import com.team.dream.runlegwork.singleservice.AccountManager;
import com.team.dream.runlegwork.utils.JsonSerializer;
import com.team.dream.runlegwork.utils.StringUtils;

public class AsyncHttpClientEx extends AsyncHttpClient {

	private JsonSerializer jsonSerializer = new JsonSerializer();

	public AsyncHttpClientEx() {
		// true is omitting ssl verification
		super(true, 80, 443);

		if (!StringUtils.isEmpty(AccountManager.sessionid)) {
			addHeader("Cookie", "JSESSIONID=" + AccountManager.sessionid);
			Log.d("AsyncHttpClientEx", AccountManager.sessionid);
		}
	}


	public void setHeader() {
		addHeader("Content-Type", "application/json; charset=UTF-8");
		addHeader("Accept", "application/json");
		addHeader("Accept-Charset", "UTF-8");
	}

	public void addCookie(String seesionId) {
		addHeader("Set-Cookie", "JSESSIONID=" + seesionId);
	}

	public void post(String url, Object request,
			ResponseHandlerInterface responseHandler) {
		String json = jsonSerializer.serialize(request);
		post(null, url, getHttpEntity(json), null, responseHandler);
	}

	public void put(String url, Object request,
			ResponseHandlerInterface responseHandler) {
		String json = jsonSerializer.serialize(request);
		put(null, url, getHttpEntity(json), null, responseHandler);
	}

	private HttpEntity getHttpEntity(String json) {
		StringEntity entity = null;
		try {
			entity = new StringEntity(json, "UTF-8");
			entity.setContentType("application/json");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return entity;
	}

}
