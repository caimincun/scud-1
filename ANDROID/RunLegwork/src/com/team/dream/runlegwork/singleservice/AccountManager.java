package com.team.dream.runlegwork.singleservice;

import android.content.Context;
import android.content.SharedPreferences;

import com.team.dream.runlegwork.SingletonServiceManager;
import com.team.dream.runlegwork.entity.UserInfo;
import com.team.dream.runlegwork.interfaces.IUser;

public class AccountManager implements IUser {

	private static final String USER_TOKEN = "user_token";
	private static final String LOGIN_ACCOUNT = "login_account";
	private static final String USER_INFO = "user_info";

	private static final String USER_REALNAME = "userRealName";
	private static final String USER_IDCARDNUM = "userIdCardNum";
	private static final String USER_INFOEMAIL = "userInfoEmail";
	private static final String USER_SEX = "userInfoSex";
	private static final String USER_HEADPICTURE = "userInfoPicture";
	private static final String USER_SIGNATURE = "userInfoSignature";
	private static final String USER_JOB = "userInfoJob";
	private static final String USER_LABEL = "userInfoLabel";
	private static final String USER_INTRIDUCE = "userIntriduce";

	public static String sessionid;
	private String loignAccount;
	private String userToken;
	private UserInfo userInfo;

	private SharedPreferences preferences;

	public static AccountManager getInstance() {
		AccountManager accountManager = (AccountManager) SingletonServiceManager.getInstance().getAppService(SingletonServiceManager.ACCOUNT_MANAGER);
		if (accountManager == null) {
			throw new AssertionError("data not found.");
		}
		return accountManager;
	}

	public AccountManager(Context context) {
		preferences = context.getSharedPreferences(this.getClass().getSimpleName(), Context.MODE_PRIVATE);
	}

	public String getUserToken() {
		return preferences.getString(USER_TOKEN, "");
	}

	public void initUser(String loginAccount) {
		preferences.edit().putString(LOGIN_ACCOUNT, loginAccount).commit();
	}

	public UserInfo getUserinfo() {
		UserInfo userinfo = new UserInfo();
		userinfo.setUserIdCardNum(preferences.getString(USER_IDCARDNUM, ""));
		userinfo.setUserInfoEmail(preferences.getString(USER_INFOEMAIL, ""));
		userinfo.setUserInfoJob(preferences.getString(USER_JOB, ""));
		userinfo.setUserInfoLabel(preferences.getString(USER_LABEL, ""));
		userinfo.setUserInfoPicture(preferences.getString(USER_HEADPICTURE, ""));
		userinfo.setUserInfoSex(preferences.getInt(USER_SEX, 0));
		userinfo.setUserInfoSignature(preferences.getString(USER_SIGNATURE, ""));
		String ss = preferences.getString(USER_REALNAME, "");
		userinfo.setUserRealName(ss);
		userinfo.setUserToken(preferences.getString(USER_TOKEN, ""));
		userinfo.setUserInfoIntroduction(preferences.getString(USER_INTRIDUCE, ""));
		return userinfo;
	}

	public void setUserinfo(UserInfo userinfo) {

		preferences.edit().putString(USER_IDCARDNUM, userinfo.getUserIdCardNum()).commit();
		preferences.edit().putString(USER_INFOEMAIL, userinfo.getUserInfoEmail()).commit();
		preferences.edit().putString(USER_JOB, userinfo.getUserInfoJob()).commit();
		preferences.edit().putString(USER_LABEL, userinfo.getUserInfoLabel()).commit();
		preferences.edit().putString(USER_HEADPICTURE, userinfo.getUserInfoPicture()).commit();
		preferences.edit().putString(USER_SIGNATURE, userinfo.getUserInfoSignature()).commit();
		preferences.edit().putString(USER_REALNAME, userinfo.getUserRealName()).commit();
		preferences.edit().putString(USER_TOKEN, userinfo.getUserToken()).commit();
		preferences.edit().putInt(USER_SEX, userinfo.getUserInfoSex()).commit();
		preferences.edit().putString(USER_INTRIDUCE, userinfo.getUserInfoIntroduction()).commit();
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
