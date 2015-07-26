package com.team.dream.runlegwork.net;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.protocol.HttpContext;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;
import com.team.dream.runlegwork.DataApplication;
import com.team.dream.runlegwork.entity.UserInfo;
import com.team.dream.runlegwork.interfaces.RequestApi;
import com.team.dream.runlegwork.net.request.UserRegisterRequest;
import com.team.dream.runlegwork.net.response.NearUserResponse;
import com.team.dream.runlegwork.net.response.UserInfoResponse;
import com.team.dream.runlegwork.singleservice.LocationCache;
import com.team.dream.runlegwork_data.R;

public class RequestApiImpl implements RequestApi {
	private final String tag = RequestApiImpl.class.getSimpleName();
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
	public void register(String loginAccount, String loginPwd, JsonBooleanResponseHandler responseHandler) {
		UserRegisterRequest request = new UserRegisterRequest();
		request.setPhoneNumber(loginAccount);
		request.setPassword(loginPwd);
		String url = getHttpUrl(R.string.url_reigister);
		Log.d("url", url);
		DataApplication.getInstance().getPersistentCookieStore().clear();
		getHttpClient().post(url, request, responseHandler);

		HttpContext context = getHttpClient().getHttpContext();
		CookieStore cookieStore = (CookieStore) context.getAttribute(ClientContext.COOKIE_STORE);
		PersistentCookieStore cookieStore2 = DataApplication.getInstance().getPersistentCookieStore();
		for (Cookie cookie : cookieStore.getCookies()) {
			cookieStore2.addCookie(cookie);
		}
	}

	@Override
	public void login(String loginAccount, String loginPwd, String checkCode, JsonBooleanResponseHandler responseHandler) {
		String url = getHttpUrl(R.string.url_login);
		UserRegisterRequest request = new UserRegisterRequest();
		request.setPhoneNumber(loginAccount);
		request.setPassword(loginPwd);
		Log.d(tag, url);
		DataApplication.getInstance().getPersistentCookieStore().clear();
		getHttpClient().post(url, request, responseHandler);

		HttpContext context = getHttpClient().getHttpContext();
		CookieStore cookieStore = (CookieStore) context.getAttribute(ClientContext.COOKIE_STORE);
		PersistentCookieStore cookieStore2 = DataApplication.getInstance().getPersistentCookieStore();
		for (Cookie cookie : cookieStore.getCookies()) {
			Log.d("TAG", "key:" + cookie.getName() + "  value:" + cookie.getValue());
			cookieStore2.addCookie(cookie);
		}
	}

	@Override
	public void getUserinfoByToken(JsonObjectResponseHandler<UserInfoResponse> responseHandler) {
		String url = getHttpUrl(R.string.url_getuserinfo);
		// UserInfoRequest infoRequest=new UserInfoRequest();
		// infoRequest.setUserToken(token);
		// RequestParams params = new RequestParams();
		// params.put("userToken", token);
		// Log.d("url", url+"userToken"+token);
		getHttpClient().get(url, responseHandler);
	}

	@Override
	public void updateUserInfo(UserInfo userInfo, JsonBooleanResponseHandler responseHandler) {
		String url = getHttpUrl(R.string.url_updateUserinfo);

		getHttpClient().post(url, userInfo, responseHandler);
	}

	@Override
	public void uploadUserhead(Bitmap bitmap, JsonBooleanResponseHandler responseHandler) {

		byte[] buffer = Bitmap2Bytes(bitmap);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer);
		RequestParams params = new RequestParams();
		// params.put("userToken", userId);
		params.put("userImage", inputStream, "user_head_img.png");

		String url = getHttpUrl(R.string.url_uploadHead);
		asyncClient.post(url, params, responseHandler);

	}

	public static byte[] Bitmap2Bytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}

	@Override
	public void uploadUserLocation(JsonBooleanResponseHandler responseHandler) {
		if (LocationCache.getIntance().isHasLocationData()) {
			String url = getHttpUrl(R.string.url_update_location);
			RequestParams params = new RequestParams();
			params.put("lat", LocationCache.getIntance().getCurrentCityLocation().getLatitude());
			params.put("lng", LocationCache.getIntance().getCurrentCityLocation().getLongitude());
			getHttpClient().get(url, params, responseHandler);
		}

	}

	@Override
	public void getNserUser(int pageIndex, JsonObjectResponseHandler<NearUserResponse> responseHandler) {
		if (LocationCache.getIntance().isHasLocationData()) {
			String url = getHttpUrl(R.string.url_get_near_by);
			RequestParams params = new RequestParams();
			params.put("lat", LocationCache.getIntance().getCurrentCityLocation().getLatitude());
			params.put("lng", LocationCache.getIntance().getCurrentCityLocation().getLongitude());
			params.put("page_index", pageIndex);
			getHttpClient().get(url, params, responseHandler);
		}

	}

	@Override
	public void checkUserState(JsonBooleanResponseHandler responseHandler) {
		String url = getHttpUrl(R.string.url_check_login);
		getHttpClient().get(url, responseHandler);
	}

}
