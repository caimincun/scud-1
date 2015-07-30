package com.team.dream.runlegwork.interfaces;

import android.graphics.Bitmap;

import com.team.dream.runlegwork.entity.UserInfo;
import com.team.dream.runlegwork.net.JsonBooleanResponseHandler;
import com.team.dream.runlegwork.net.JsonObjectResponseHandler;
import com.team.dream.runlegwork.net.response.NearUserResponse;
import com.team.dream.runlegwork.net.response.UserInfoResponse;

public interface RequestApi {

	void register(String loginAccount, String loginPwd, JsonBooleanResponseHandler responseHandler);

	void login(String loginAccount, String loginPwd, String checkCode, JsonBooleanResponseHandler responseHandler);

	void getUserinfoByToken(JsonObjectResponseHandler<UserInfoResponse> responseHandler);

	void updateUserInfo(UserInfo userInfo, JsonObjectResponseHandler<UserInfoResponse> responseHandler);

	void uploadUserhead(Bitmap bitmap, JsonBooleanResponseHandler responseHandler);

	void uploadUserLocation(JsonBooleanResponseHandler responseHandler);

	void getNserUser(int pageIndex, JsonObjectResponseHandler<NearUserResponse> responseHandler);

	void checkUserState(JsonBooleanResponseHandler responseHandler);
}
