package com.team.dream.runlegwork;

import org.litepal.LitePalApplication;

import cn.jpush.android.api.JPushInterface;

import com.easemob.EMCallBack;
import com.loopj.android.http.PersistentCookieStore;
import com.team.dream.runlegwork.chathelper.DemoHXSDKHelper;
import com.team.dream.runlegwork.entity.UserInfo;
import com.team.dream.runlegwork.interfaces.RequestApi;
import com.team.dream.runlegwork.net.RequestApiImpl;

public class DataApplication extends LitePalApplication {
	public static UserInfo mAccount;

	private static DataApplication mApplication;
	public static DemoHXSDKHelper hxSDKHelper = new DemoHXSDKHelper();
	@Override
	public void onCreate() {
		super.onCreate();
		SingletonServiceManager.newInstance(this);
//		BaiDuApiHandler.initBaiDuSdk(this);
		JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
        hxSDKHelper.onInit(this);//初始化环信
	}

	public static DataApplication getInstance() {
		if (mApplication == null) {
			mApplication = new DataApplication();
		}
		return mApplication;
	}

	public PersistentCookieStore getPersistentCookieStore() {
		PersistentCookieStore mPersistentCookieStore = (PersistentCookieStore) SingletonServiceManager.getInstance().getAppService(SingletonServiceManager.PERSISTENT_COOKIE_STORE);
		if (mPersistentCookieStore == null) {
			throw new AssertionError("PersistentCookieStore is not found");
		}
		return mPersistentCookieStore;
	}

	public RequestApi getReQuestApi() {
		RequestApi api = (RequestApiImpl) SingletonServiceManager.getInstance().getAppService(SingletonServiceManager.REQUEST_API);
		if (api == null) {
			throw new AssertionError("RequestApi is not found");
		}
		return api;
	}
	
	/**
	 * 获取当前登陆用户名
	 *
	 * @return
	 */
	public String getUserName() {
	    return hxSDKHelper.getHXId();
	}

	/**
	 * 获取密码
	 *
	 * @return
	 */
	public String getPassword() {
		return hxSDKHelper.getPassword();
	}

	/**
	 * 设置用户名
	 *
	 * @param user
	 */
	public void setUserName(String username) {
	    hxSDKHelper.setHXId(username);
	}

	/**
	 * 设置密码 下面的实例代码 只是demo，实际的应用中需要加password 加密后存入 preference 环信sdk
	 * 内部的自动登录需要的密码，已经加密存储了
	 *
	 * @param pwd
	 */
	public void setPassword(String pwd) {
	    hxSDKHelper.setPassword(pwd);
	}

	/**
	 * 退出登录,清空数据
	 */
	public void logout(final boolean isGCM,final EMCallBack emCallBack) {
		// 先调用sdk logout，在清理app中自己的数据
	    hxSDKHelper.logout(isGCM,emCallBack);
	}

}
