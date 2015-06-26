package com.team.dream.runlegwork_data;

import org.litepal.LitePalApplication;

public class DataApplication extends LitePalApplication {

	@Override
	public void onCreate() {
		super.onCreate();
		SingletonServiceManager.newInstance(this);
	}
}
