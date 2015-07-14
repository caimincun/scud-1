package com.team.dream.runlegwork.interfaces;

import com.team.dream.runlegwork.entity.LocationInfo;
import com.team.dream.runlegwork.entity.LocationInfo.Location;

public interface IAddressSetting {

	void saveCityName(String cityName);

	String getCityName();

	String getCityCode();

	void savaCurrentLocationInfo(LocationInfo locationInfo);

	String getCurrentCityCode();

	Location getCurrentCityLocation();

	void setLastUpdateTime();

	Long getLastUpdateTime();

}
