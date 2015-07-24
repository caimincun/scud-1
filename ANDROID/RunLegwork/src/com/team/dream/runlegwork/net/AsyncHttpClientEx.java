package com.team.dream.runlegwork.net;

import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.ResponseHandlerInterface;
import com.team.dream.runlegwork.DataApplication;
import com.team.dream.runlegwork.utils.JsonSerializer;

public class AsyncHttpClientEx extends AsyncHttpClient {

	private JsonSerializer jsonSerializer = new JsonSerializer();

	public AsyncHttpClientEx() {
		// true is omitting ssl verification
		super(true, 80, 443);

	}

	public void setHeader() {
		addHeader("Content-Type", "application/json; charset=UTF-8");
		addHeader("Accept", "application/json");
		addHeader("Accept-Charset", "UTF-8");
		PersistentCookieStore cookieStore = DataApplication.getInstance().getPersistentCookieStore();
		setCookieStore(cookieStore);
	}

	public void post(String url, Object request, ResponseHandlerInterface responseHandler) {
		String json = jsonSerializer.serialize(request);
		post(null, url, getHttpEntity(json), null, responseHandler);
	}

	public void put(String url, Object request, ResponseHandlerInterface responseHandler) {
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
