package com.team.dream.runlegwork.net;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;
import com.team.dream.runlegwork.interfaces.RequestApi;
import com.team.dream.runlegwork.net.request.UserRegisterRequest;
import com.team.dream.runlegwork.net.response.UserRegisterResponse;
import com.team.dream.runlegwork_data.R;

public class RequestApiImpl implements RequestApi {

	// 异步
	private AsyncHttpClientEx asyncClient;
	// 同步调用
	private SyncHttpClient syncClient;

	private Context context;

	public RequestApiImpl(Context context) {
		asyncClient = new AsyncHttpClientEx();
		syncClient = new SyncHttpClient();
		this.context = context;
	}

	// 添加特定的header
	private AsyncHttpClientEx getHttpClient() {
		asyncClient.setHeader();
		return asyncClient;
	}

	private String getUrl(int protocolResId, int pathResId) {
		String protocolPath = context.getString(protocolResId);
		String host = context.getString(R.string.url_main);
		String path = context.getString(pathResId);
		return String.format(protocolPath, host, path);
	}

	private String getHttpUrl(int urlResId) {
		return getUrl(R.string.url_protocol_format, urlResId);
	}

	private String getHttpsUrl(int urlResId) {
		return getUrl(R.string.url_protocol_secure_format, urlResId);
	}

	@Override
	public void register(String loginAccount, String loginPwd, JsonObjectResponseHandler<UserRegisterResponse> responseHandler) {
		UserRegisterRequest request = new UserRegisterRequest();
		request.setPhoneNumber(loginAccount);
		request.setPassword(loginPwd);
		String url = getHttpUrl(R.string.url_reigister);
		Log.d("url", url);
		getHttpClient().post(url, request, responseHandler);
	}

	@Override
	public void login(String loginAccount, String loginPwd, String checkCode, JsonHttpResponseHandler responseHandler) {
		// TODO Auto-generated method stub

	}
}
