package com.team.dream.runlegwork.interfaces;

import java.util.List;

import android.graphics.Bitmap;

import com.team.dream.runlegwork.entity.Address;
import com.team.dream.runlegwork.entity.OrderAndAddressEntity;
import com.team.dream.runlegwork.entity.Product;
import com.team.dream.runlegwork.entity.Producttype;
import com.team.dream.runlegwork.entity.Skill;
import com.team.dream.runlegwork.entity.Store;
import com.team.dream.runlegwork.entity.UserInfo;
import com.team.dream.runlegwork.entity.UserOrder;
import com.team.dream.runlegwork.fragment.PushSkillFragment.TempPic;
import com.team.dream.runlegwork.net.JsonBooleanResponseHandler;
import com.team.dream.runlegwork.net.JsonObjectResponseHandler;
import com.team.dream.runlegwork.net.request.CreateOrderRequest;
import com.team.dream.runlegwork.net.response.AcptsPersonResponse;
import com.team.dream.runlegwork.net.response.AddressResponse;
import com.team.dream.runlegwork.net.response.ArrayEntityResponse;
import com.team.dream.runlegwork.net.response.CheckNumResponse;
import com.team.dream.runlegwork.net.response.EntityResponse;
import com.team.dream.runlegwork.net.response.ListUserSkillResponse;
import com.team.dream.runlegwork.net.response.NearUserResponse;
import com.team.dream.runlegwork.net.response.OrderListResponse;
import com.team.dream.runlegwork.net.response.RequirementResponse;
import com.team.dream.runlegwork.net.response.ShopListResponse;
import com.team.dream.runlegwork.net.response.SkillListResponse;
import com.team.dream.runlegwork.net.response.SkillpeopleDetailResponse;
import com.team.dream.runlegwork.net.response.StoreResponse;
import com.team.dream.runlegwork.net.response.UserInfoResponse;

public interface RequestApi {

	void register(String loginAccount, String loginPwd,
			JsonBooleanResponseHandler responseHandler);

	void login(String loginAccount, String loginPwd, String checkCode,
			JsonBooleanResponseHandler responseHandler);

	void getUserinfoByToken(
			JsonObjectResponseHandler<UserInfoResponse> responseHandler);

	void updateUserInfo(UserInfo userInfo,
			JsonObjectResponseHandler<UserInfoResponse> responseHandler);

	void uploadUserhead(Bitmap bitmap,
			JsonBooleanResponseHandler responseHandler);

	void uploadUserLocation(JsonBooleanResponseHandler responseHandler);

	void getNserUser(int pageIndex, String condition,
			JsonObjectResponseHandler<NearUserResponse> responseHandler);

	void checkUserState(JsonBooleanResponseHandler responseHandler);

	void getRequirementList(int pageIndex,
			JsonObjectResponseHandler<RequirementResponse> responseHandler);

	void createOrder(CreateOrderRequest request,
			JsonBooleanResponseHandler responseHandler);

	void getOrderList(
			JsonObjectResponseHandler<OrderListResponse> responseHandler);

	void createSkill(Skill request, List<TempPic> list,
			JsonBooleanResponseHandler responseHandler);

	void createSkills(Skill request, List<Bitmap> list,
			JsonBooleanResponseHandler responseHandler);

	void answerOrders(String orderToken,
			JsonBooleanResponseHandler responseHandler);

	void getListUserSkill(
			JsonObjectResponseHandler<ListUserSkillResponse> responseHandler);

	void updateUserSkill(Skill request, List<TempPic> list,
			JsonBooleanResponseHandler responseHandler);

	void getAcptsPerson(String orderToken,
			JsonObjectResponseHandler<AcptsPersonResponse> responseHandler);

	/**
	 * 根据首页按摩等点计获取对应技能的用户
	 * 
	 * @param pageIndex页数
	 * @param Skill
	 *            技能名称
	 * @param responseHandler
	 */
	void getSkillpeopleDetail(int pageIndex, String Skill,
			JsonObjectResponseHandler<SkillpeopleDetailResponse> responseHandler);

	/**
	 * 获取技能列表
	 * 
	 * @param responseHandler
	 */
	void getSkillList(String userToken,
			JsonObjectResponseHandler<SkillListResponse> responseHandler);

	void SaveAcpt(String userToken, String orderToken,
			JsonBooleanResponseHandler responseHandler);

	void confrimOrder(String orderToken,
			JsonBooleanResponseHandler booleanResponseHandler);

	void getComplateOrderList(
			JsonObjectResponseHandler<OrderListResponse> responseHandler);

	void sendOrderWithSkill(UserOrder order,
			JsonBooleanResponseHandler booleanResponseHandler);

	void getShopList(
			String type,
			int page,
			JsonObjectResponseHandler<ShopListResponse> jsonObjectResponseHandler);

	void createShop(String storeName, String storeDetail, Bitmap bitmap,
			JsonBooleanResponseHandler responseHandler);

	void getStore(JsonObjectResponseHandler<StoreResponse> responseHandler);

	void isHavaStore(JsonBooleanResponseHandler responseHandler);

	void updateShop(Store store, Bitmap bitmap,
			JsonBooleanResponseHandler responseHandler);

	void saveGoodsType(String typeName,
			JsonBooleanResponseHandler responseHandler);

	void querylistproductTypes(
			JsonObjectResponseHandler<ArrayEntityResponse<Producttype>> responseHandler);

	void delProductType(String typeToken,
			JsonBooleanResponseHandler responseHandler);

	void createProdcut(Product product, Bitmap bitmap,
			JsonBooleanResponseHandler responseHandler);

	void getProductList(
			JsonObjectResponseHandler<ArrayEntityResponse<Product>> responseHandler);

	void getXiajiaProductList(
			JsonObjectResponseHandler<ArrayEntityResponse<Product>> responseHandler);

	void xiajiaProduct(String productToken,
			JsonBooleanResponseHandler responseHandler);

	/**
	 * 删除订单
	 * 
	 * @param token
	 */
	void deleteOrder(String token,String orderUsertoken, JsonBooleanResponseHandler responseHandler);

	/**
	 * 获取验证码
	 */
	void getChecknum(String phoneNum,
			JsonObjectResponseHandler<CheckNumResponse> jsonResponseHandler);

	void updatePassword(String pwd, JsonBooleanResponseHandler responseHandler);

	void confirmOrder(OrderAndAddressEntity jsonstr,
			JsonBooleanResponseHandler responseHandler);

	void deleteAddress(int id, JsonBooleanResponseHandler responseHandler);

	void getAddressList(
			JsonObjectResponseHandler<AddressResponse> jsonResponseHandler);

	void saveAddress(Address address, JsonBooleanResponseHandler responseHandler);

	void answerOrders(String orderToken, String userToken,
			JsonBooleanResponseHandler responseHandler);

	void getSkilldetail(String skillToken,
			JsonObjectResponseHandler<EntityResponse<Skill>> responseHandler);
	/**
	 * 删除技能
	 * @param skillToken
	 * @param jsonBooleanResponseHandler
	 */
	void deleteSkill(String skillToken,JsonBooleanResponseHandler jsonBooleanResponseHandler);
}
