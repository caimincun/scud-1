package com.team.dream.runlegwork;

import org.litepal.LitePalApplication;

import com.team.dream.runlegwork.entity.UserInfo;

public class DataApplication extends LitePalApplication {
	public static UserInfo mAccount;
	@Override
	public void onCreate() {
		super.onCreate();
		SingletonServiceManager.newInstance(this);
	}
}
