package com.team.dream.runlegwork.net;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.params.CoreProtocolPNames;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;
import com.team.dream.runlegwork.DataApplication;
import com.team.dream.runlegwork.utils.JsonSerializer;



public class AsyncHttpClientEx extends AsyncHttpClient {

	private JsonSerializer jsonSerializer = new JsonSerializer();

	public AsyncHttpClientEx() {
		// true is omitting ssl verification
		super(true, 80, 443);
		PersistentCookieStore cookieStore = DataApplication.getInstance().getPersistentCookieStore();
		setCookieStore(cookieStore);
		this.getHttpClient().getParams().setParameter(CoreProtocolPNames.USER_AGENT, System.getProperty("http.agent"));
	}

	public RequestHandle post(String url, Object request, ResponseHandlerInterface responseHandler) {
		String json = jsonSerializer.serialize(request);
		RequestHandle requestHandle = post(null, url, getHttpEntity(json), null, responseHandler);
		return requestHandle;
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
