package com.team.dream.runlegwork;

import org.litepal.LitePalApplication;

public class DataApplication extends LitePalApplication {

	@Override
	public void onCreate() {
		super.onCreate();
		SingletonServiceManager.newInstance(this);
	}
}
