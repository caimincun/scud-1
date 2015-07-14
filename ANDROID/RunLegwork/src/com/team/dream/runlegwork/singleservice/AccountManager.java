package com.team.dream.runlegwork.singleservice;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.team.dream.runlegwork.SingletonServiceManager;
import com.team.dream.runlegwork.entity.UserInfo;
import com.team.dream.runlegwork.interfaces.IUser;

public class AccountManager implements IUser {

	private static final String USER_TOKEN = "user_token";
	private static final String LOGIN_ACCOUNT = "login_account";
	private static final String USER_INFO = "user_info";
	public static String sessionid;
	private String loignAccount;
	private String userToken;
	private UserInfo userInfo;

	private SharedPreferences preferences;

	public static AccountManager getInstance() {
		AccountManager accountManager = (AccountManager) SingletonServiceManager
				.getInstance().getAppService(
						SingletonServiceManager.ACCOUNT_MANAGER);
		if (accountManager == null) {
			throw new AssertionError("data not found.");
		}
		return accountManager;
	}

	public AccountManager(Context context) {
		preferences = context.getSharedPreferences(this.getClass()
				.getSimpleName(), Context.MODE_PRIVATE);
	}

	public String getUserToken() {
		return preferences.getString(USER_TOKEN, "");
	}

	public void initUser(String loginAccount) {
		preferences.edit().putString(LOGIN_ACCOUNT, loginAccount).commit();
	}
	
	public void getUserinfo(){
		
	}
	
	public void setUserinfo(UserInfo userinfo){
//		preferences.edit().
	}

	@Override
	public void Login() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getUserInfo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void register() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub

	}

}
