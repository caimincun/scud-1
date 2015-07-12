package com.team.dream.runlegwork.interfaces;

import android.graphics.Bitmap;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.team.dream.runlegwork.entity.UserInfo;
import com.team.dream.runlegwork.net.JsonBooleanResponseHandler;
import com.team.dream.runlegwork.net.JsonObjectResponseHandler;
import com.team.dream.runlegwork.net.response.UserInfoResponse;
import com.team.dream.runlegwork.net.response.UserRegisterResponse;

public interface RequestApi {

	void register(String loginAccount, String loginPwd, JsonObjectResponseHandler<UserRegisterResponse> responseHandler);

	void login(String loginAccount, String loginPwd, String checkCode, JsonObjectResponseHandler<UserRegisterResponse> responseHandler);
	
	void getUserinfoByToken(JsonObjectResponseHandler<UserInfoResponse> responseHandler);
	
	void updateUserInfo(UserInfo userInfo,JsonBooleanResponseHandler responseHandler);
	
	void uploadUserhead(Bitmap bitmap,JsonBooleanResponseHandler responseHandler);
}
