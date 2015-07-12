package com.team.dream.runlegwork;

import org.litepal.LitePalApplication;

import com.baidu.mapapi.SDKInitializer;
import com.team.dream.runlegwork.entity.UserInfo;

public class DataApplication extends LitePalApplication {
	public static UserInfo mAccount;

	@Override
	public void onCreate() {
		super.onCreate();
		SingletonServiceManager.newInstance(this);
		// 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
		SDKInitializer.initialize(this);
	}
}
