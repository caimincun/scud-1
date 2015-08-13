package com.team.dream.runlegwork;

import org.litepal.tablemanager.Connector;

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
