package com.team.dream.runlegwork;

import org.litepal.LitePalApplication;

import com.loopj.android.http.PersistentCookieStore;
import com.team.dream.runlegwork.entity.UserInfo;
import com.team.dream.runlegwork.interfaces.RequestApi;
import com.team.dream.runlegwork.net.RequestApiImpl;
import com.team.dream.runlegwork.singleservice.BaiDuApiHandler;

public class DataApplication extends LitePalApplication {
	public static UserInfo mAccount;

	private static DataApplication mApplication;

	@Override
	public void onCreate() {
		super.onCreate();
		SingletonServiceManager.newInstance(this);
		BaiDuApiHandler.initBaiDuSdk(this);

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

}
