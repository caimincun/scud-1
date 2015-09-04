package com.team.dream.runlegwork.interfaces;

import java.util.List;

import android.graphics.Bitmap;

import com.team.dream.runlegwork.entity.Skill;
import com.team.dream.runlegwork.entity.UserInfo;
import com.team.dream.runlegwork.net.JsonBooleanResponseHandler;
import com.team.dream.runlegwork.net.JsonObjectResponseHandler;
import com.team.dream.runlegwork.net.request.CreateOrderRequest;
import com.team.dream.runlegwork.net.response.AcptsPersonResponse;
import com.team.dream.runlegwork.net.response.ListUserSkillResponse;
import com.team.dream.runlegwork.net.response.NearUserResponse;
import com.team.dream.runlegwork.net.response.OrderListResponse;
import com.team.dream.runlegwork.net.response.RequirementResponse;
import com.team.dream.runlegwork.net.response.UserInfoResponse;

public interface RequestApi {

	void register(String loginAccount, String loginPwd, JsonBooleanResponseHandler responseHandler);

	void login(String loginAccount, String loginPwd, String checkCode, JsonBooleanResponseHandler responseHandler);

	void getUserinfoByToken(JsonObjectResponseHandler<UserInfoResponse> responseHandler);

	void updateUserInfo(UserInfo userInfo, JsonObjectResponseHandler<UserInfoResponse> responseHandler);

	void uploadUserhead(Bitmap bitmap, JsonBooleanResponseHandler responseHandler);

	void uploadUserLocation(JsonBooleanResponseHandler responseHandler);

	void getNserUser(int pageIndex,String condition, JsonObjectResponseHandler<NearUserResponse> responseHandler);

	void checkUserState(JsonBooleanResponseHandler responseHandler);
	
	void getRequirementList(int pageIndex, JsonObjectResponseHandler<RequirementResponse> responseHandler);

	void createOrder(CreateOrderRequest request,JsonBooleanResponseHandler responseHandler);
	
	void getOrderList(JsonObjectResponseHandler<OrderListResponse> responseHandler);
	
	void createSkill(Skill request,List<Bitmap> list,JsonBooleanResponseHandler responseHandler);
	
	void answerOrders(String orderToken,JsonBooleanResponseHandler responseHandler);
	
	void getListUserSkill(JsonObjectResponseHandler<ListUserSkillResponse> responseHandler);
	
	void updateUserSkill(Skill request,JsonBooleanResponseHandler responseHandler);
	
	void getAcptsPerson(String orderToken,JsonObjectResponseHandler<AcptsPersonResponse> responseHandler);
}
