package com.team.dream.runlegwork.entity;

import com.team.dream.runlegwork.utils.AppUtils;
import com.team.dream.runlegwork.utils.StringUtils;

public class NearUserInfo extends UserInfo {
	private String distance;

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public boolean hasDantce() {
		if (StringUtils.isEmpty(distance)) {
			return false;
		}
		return true;
	}

	public String getUserDantce() {
		if (hasDantce()) {
			Double distance = Double.parseDouble(getDistance());
			if (distance > 0 && distance < 1000) {
				String temp = AppUtils.DoubleDP(distance, "#.0");
				return temp + "m";
			} else {
				distance = distance / 1000;
				String temp = AppUtils.DoubleDP(distance, "#.0");
				return temp + "km";
			}

		}
		return null;
	}
}
