package com.team.dream.runlegwork;

import org.litepal.LitePalApplication;

import com.team.dream.runlegwork.entity.Account;

public class DataApplication extends LitePalApplication {
	public static Account mAccount;
	@Override
	public void onCreate() {
		super.onCreate();
		SingletonServiceManager.newInstance(this);
	}
}
