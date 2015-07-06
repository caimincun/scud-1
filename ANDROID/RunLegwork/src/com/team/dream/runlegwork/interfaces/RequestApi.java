package com.team.dream.runlegwork.interfaces;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.team.dream.runlegwork.entity.UserInfo;
import com.team.dream.runlegwork.net.JsonBooleanResponseHandler;
import com.team.dream.runlegwork.net.JsonObjectResponseHandler;
import com.team.dream.runlegwork.net.response.UserInfoResponse;
import com.team.dream.runlegwork.net.response.UserRegisterResponse;

public interface RequestApi {

	void register(String loginAccount, String loginPwd, JsonObjectResponseHandler<UserRegisterResponse> responseHandler);

	void login(String loginAccount, String loginPwd, String checkCode, JsonHttpResponseHandler responseHandler);
	
	void getUserinfoByToken(String token,JsonObjectResponseHandler<UserInfoResponse> responseHandler);
	
	void updateUserInfo(UserInfo userInfo,JsonBooleanResponseHandler responseHandler);
}