package com.team.dream.runlegwork_data.cache;

import org.litepal.tablemanager.Connector;

import com.team.dream.runlegwork_data.SingletonServiceManager;

public class LitePalManager {

	public LitePalManager from() {
		LitePalManager litePalManager = (LitePalManager) SingletonServiceManager.getInstance().getAppService(SingletonServiceManager.LITEPAL_MANAGER);
		if (litePalManager == null) {
			throw new AssertionError("data not found.");
		}
		return litePalManager;
	}

	public LitePalManager() {
		Connector.getDatabase();
	}

	
	
}
