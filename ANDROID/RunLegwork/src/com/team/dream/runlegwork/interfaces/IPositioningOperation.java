package com.team.dream.runlegwork.interfaces;

import com.baidu.location.BDLocation;

public interface IPositioningOperation {
	
	 void startPosition();

	 void OnCompleteLocation(BDLocation location);
	 
}
