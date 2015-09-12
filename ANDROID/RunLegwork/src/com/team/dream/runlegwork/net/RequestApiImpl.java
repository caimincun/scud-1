package com.team.dream.runlegwork.net;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.loopj.android.http.RequestParams;
import com.team.dream.runlegwork.DataApplication;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.entity.Skill;
import com.team.dream.runlegwork.entity.UserInfo;
import com.team.dream.runlegwork.interfaces.RequestApi;
import com.team.dream.runlegwork.net.request.CreateOrderRequest;
import com.team.dream.runlegwork.net.request.UserRegisterRequest;
import com.team.dream.runlegwork.net.response.AcptsPersonResponse;
import com.team.dream.runlegwork.net.response.ListUserSkillResponse;
import com.team.dream.runlegwork.net.response.NearUserResponse;
import com.team.dream.runlegwork.net.response.OrderListResponse;
import com.team.dream.runlegwork.net.response.RequirementResponse;
import com.team.dream.runlegwork.net.response.SkillListResponse;
import com.team.dream.runlegwork.net.response.SkillpeopleDetailResponse;
import com.team.dream.runlegwork.net.response.UserInfoResponse;
import com.team.dream.runlegwork.singleservice.LocationCache;
import com.team.dream.runlegwork.utils.JsonSerializer;

public class RequestApiImpl implements RequestApi {
	private final String tag = RequestApiImpl.class.getSimpleName();
	private JsonSerializer jsonSerializer = new JsonSerializer();
	// 异步
	private AsyncHttpClientEx asyncClient;
	// // 同步调用
	// private SyncHttpClient syncClient;

	private Context context;

	public RequestApiImpl(Context context) {
		asyncClient = new AsyncHttpClientEx();
		// syncClient = new SyncHttpClient();
		this.context = context;
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

	// private String getHttpsUrl(int urlResId) {
	// return getUrl(R.string.url_protocol_secure_format, urlResId);
	// }

	@Override
	public void register(String loginAccount, String loginPwd,
			JsonBooleanResponseHandler responseHandler) {
		UserRegisterRequest request = new UserRegisterRequest();
		request.setPhoneNumber(loginAccount);
		request.setPassword(loginPwd);
		String url = getHttpUrl(R.string.url_reigister);
		Log.d("url", url);
		DataApplication.getInstance().getPersistentCookieStore().clear();
		asyncClient.post(url, request, responseHandler);

	}

	@Override
	public void login(String loginAccount, String loginPwd, String checkCode,
			JsonBooleanResponseHandler responseHandler) {
		String url = getHttpUrl(R.string.url_login);
		UserRegisterRequest request = new UserRegisterRequest();
		request.setPhoneNumber(loginAccount);
		request.setPassword(loginPwd);
		Log.d(tag, url);
		DataApplication.getInstance().getPersistentCookieStore().clear();
		asyncClient.post(url, request, responseHandler);

	}

	@Override
	public void getUserinfoByToken(
			JsonObjectResponseHandler<UserInfoResponse> responseHandler) {
		String url = getHttpUrl(R.string.url_getuserinfo);
		asyncClient.get(url, responseHandler);
	}

	@Override
	public void updateUserInfo(UserInfo userInfo,
			JsonObjectResponseHandler<UserInfoResponse> responseHandler) {
		String url = getHttpUrl(R.string.url_updateUserinfo);

		asyncClient.post(url, userInfo, responseHandler);
	}

	@Override
	public void uploadUserLocation(JsonBooleanResponseHandler responseHandler) {
		if (LocationCache.getIntance().isHasLocationData()) {
			String url = getHttpUrl(R.string.url_update_location);
			RequestParams params = new RequestParams();
			params.put("lat", LocationCache.getIntance()
					.getCurrentCityLocation().getLatitude());
			params.put("lng", LocationCache.getIntance()
					.getCurrentCityLocation().getLongitude());
			asyncClient.get(url, params, responseHandler);
		}

	}

	@Override
	public void getNserUser(int pageIndex, String condition,
			JsonObjectResponseHandler<NearUserResponse> responseHandler) {
		if (LocationCache.getIntance().isHasLocationData()) {
			String url = getHttpUrl(R.string.url_get_near_by);
			RequestParams params = new RequestParams();
			if (LocationCache.getIntance().getCurrentCityLocation() != null) {
				params.put("lat", LocationCache.getIntance()
						.getCurrentCityLocation().getLatitude());
				params.put("lng", LocationCache.getIntance()
						.getCurrentCityLocation().getLongitude());
			}
			params.put("skillName", condition);
			params.put("page_index", pageIndex);

			asyncClient.post(url, params, responseHandler);
			// asyncClient.get(url, params, responseHandler);
		}

	}

	@Override
	public void checkUserState(JsonBooleanResponseHandler responseHandler) {
		String url = getHttpUrl(R.string.url_check_login);
		asyncClient.get(url, responseHandler);
	}

	@Override
	public void uploadUserhead(Bitmap bitmap,
			JsonBooleanResponseHandler responseHandler) {

		byte[] buffer = Bitmap2Bytes(bitmap);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer);
		RequestParams params = new RequestParams();
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
	public void getRequirementList(int pageIndex,
			JsonObjectResponseHandler<RequirementResponse> responseHandler) {
		if (LocationCache.getIntance().isHasLocationData()) {
			String url = getHttpUrl(R.string.url_requirebydistance);
			RequestParams params = new RequestParams();
			params.put("lat", LocationCache.getIntance()
					.getCurrentCityLocation().getLatitude());
			params.put("lng", LocationCache.getIntance()
					.getCurrentCityLocation().getLongitude());
			params.put("page_index", pageIndex);
			asyncClient.get(url, params, responseHandler);
		}
	}

	@Override
	public void createOrder(CreateOrderRequest request,
			JsonBooleanResponseHandler responseHandler) {
		String url = getHttpUrl(R.string.url_create_order);
		asyncClient.post(url, request, responseHandler);
	}

	@Override
	public void getOrderList(
			JsonObjectResponseHandler<OrderListResponse> responseHandler) {
		String url = getHttpUrl(R.string.url_get_order_list);
		asyncClient.get(url, responseHandler);
	}

	@Override
	public void getComplateOrderList(
			JsonObjectResponseHandler<OrderListResponse> responseHandler) {
		String url = getHttpUrl(R.string.url_get_complate_order_list);
		asyncClient.get(url, responseHandler);
	}

	@Override
	public void createSkill(Skill request, List<Bitmap> list,
			JsonBooleanResponseHandler responseHandler) {
		String url = getHttpUrl(R.string.url_create_skill);
		Log.d(tag, url);
		String json = jsonSerializer.serialize(request);
		RequestParams params = new RequestParams();
		params.put("json", json);

		for (int i = 0; i < list.size(); i++) {
			byte[] buffer = Bitmap2Bytes(list.get(i));
			ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer);
			params.put("userImage" + i, inputStream, "user_head_img" + i
					+ ".png");
		}

		asyncClient.post(url, params, responseHandler);
	}

	@Override
	public void answerOrders(String orderToken,
			JsonBooleanResponseHandler responseHandler) {
		String url = getHttpUrl(R.string.url_answer_order);
		RequestParams request = new RequestParams();
		request.put("orderToken", orderToken);
		Log.d(tag, url);
		asyncClient.get(url, request, responseHandler);

	}

	@Override
	public void getListUserSkill(
			JsonObjectResponseHandler<ListUserSkillResponse> responseHandler) {
		String url = getHttpUrl(R.string.url_get_skills);
		asyncClient.get(url, responseHandler);
	}

	@Override
	public void updateUserSkill(Skill request,
			JsonBooleanResponseHandler responseHandler) {
		String url = getHttpUrl(R.string.url_update_skills);
		asyncClient.post(url, request, responseHandler);
	}

	@Override
	public void getAcptsPerson(String orderToken,
			JsonObjectResponseHandler<AcptsPersonResponse> responseHandler) {
		if (LocationCache.getIntance().isHasLocationData()) {
			String url = getHttpUrl(R.string.url_get_acpts_person);
			RequestParams params = new RequestParams();
			params.put("lat", LocationCache.getIntance()
					.getCurrentCityLocation().getLatitude());
			params.put("lng", LocationCache.getIntance()
					.getCurrentCityLocation().getLongitude());
			params.put("orderToken", orderToken);
			asyncClient.get(url, params, responseHandler);
		} else {
			responseHandler.onFailure("数据请求失败");
		}
	}

	@Override
	public void getSkillpeopleDetail(int pageIndex, String Skill,
			JsonObjectResponseHandler<SkillpeopleDetailResponse> responseHandler) {

		if (LocationCache.getIntance().isHasLocationData()) {
			String url = getHttpUrl(R.string.url_get_skillanduser);
			RequestParams params = new RequestParams();
			if (LocationCache.getIntance().getCurrentCityLocation() != null) {
				params.put("lat", LocationCache.getIntance()
						.getCurrentCityLocation().getLatitude());
				params.put("lng", LocationCache.getIntance()
						.getCurrentCityLocation().getLongitude());
			}
			params.put("skillName", Skill);
			params.put("page_index", pageIndex);

			asyncClient.post(url, params, responseHandler);
		}

	}

	@Override
	public void getSkillList(
			JsonObjectResponseHandler<SkillListResponse> responseHandler) {
		String url = getHttpUrl(R.string.url_get_skilldetail);
		asyncClient.post(url, responseHandler);
	}

	@Override
	public void SaveAcpt(String userToken, String orderToken,
			JsonBooleanResponseHandler responseHandler) {
		String url = getHttpUrl(R.string.url_save_acpts_person);
		RequestParams params = new RequestParams();
		params.add("userToken", userToken);
		params.add("orderToken", orderToken);
		asyncClient.post(url, params, responseHandler);
	}

	@Override
	public void confrimOrder(String orderToken,
			JsonBooleanResponseHandler booleanResponseHandler) {
		String url = getHttpUrl(R.string.url_confirm_order_person);
		RequestParams params = new RequestParams();
		params.add("orderToken", orderToken);
		asyncClient.post(url, params, booleanResponseHandler);
	}

}
