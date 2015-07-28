package com.team.dream.runlegwork.singleservice;

import java.util.Date;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.team.dream.runlegwork.SingletonServiceManager;
import com.team.dream.runlegwork.entity.LocationInfo;
import com.team.dream.runlegwork.entity.LocationInfo.Location;
import com.team.dream.runlegwork.interfaces.IAddressSetting;
import com.team.dream.runlegwork.utils.CityData;
import com.team.dream.runlegwork.utils.StringUtils;

public class LocationCache implements IAddressSetting {

	private SharedPreferences mLocationUtil = null;

	public static LocationCache getIntance() {
		LocationCache loc = (LocationCache) SingletonServiceManager.getInstance().getAppService(SingletonServiceManager.Location_Cache_Util);
		if (loc == null) {
			throw new AssertionError("LocationCacheUtil not found.");
		}
		return loc;
	}

	public LocationCache(Context cxt) {
		if (mLocationUtil == null) {
			mLocationUtil = cxt.getSharedPreferences(ILocationCache.LOCATION_NAME, Context.MODE_PRIVATE);
		}
	}

	interface ILocationCache {
		public static final String LOCATION_NAME = "location";
		public static final String CITY_NAME = "city_name";
		public static final String CURRENT_CITY_NAME = "current_city_name";
		public static final String LATITUDE = "latitude";
		public static final String LONGITUDE = "longitude";
		public static final String LAST_UAPDATE_TIME = "last_uapdate_time";
		public static final String LAST_CHANGE_SELELCT_TIME = "last_change_select_time";
	}

	@Override
	public void saveCityName(String cityName) {
		if (!StringUtils.isEmpty(cityName)) {
			Editor mEditor = mLocationUtil.edit();
			mEditor.putString(ILocationCache.CITY_NAME, cityName);
			mEditor.commit();
		}
	}

	public void savaChangeSelectState(boolean isChange) {
		Editor mEditor = mLocationUtil.edit();
		mEditor.putBoolean(ILocationCache.LAST_CHANGE_SELELCT_TIME, isChange);
		mEditor.commit();
	}

	public boolean getChangeState() {
		return this.mLocationUtil.getBoolean(ILocationCache.LAST_CHANGE_SELELCT_TIME, true);
	}

	@Override
	public String getCityName() {
		return this.mLocationUtil.getString(ILocationCache.CITY_NAME, null);
	}

	@Override
	public String getCityCode() {
		String cityName = getCityName();
		if (cityName == null) {
			return null;
		}
		return CityData.getCityCode(cityName);
	}

	@Override
	public void savaCurrentLocationInfo(LocationInfo locationInfo) {
		if (locationInfo != null) {
			if (!hasLocationCache()) {
				saveCityName(locationInfo.getCityName());
			}
			Editor mEditor = mLocationUtil.edit();
			mEditor.putString(ILocationCache.CURRENT_CITY_NAME, locationInfo.getCityName());
			String latitude = Double.toString(locationInfo.getLocation().getLatitude());
			String longitude = Double.toString(locationInfo.getLocation().getLongitude());
			mEditor.putString(ILocationCache.LATITUDE, latitude);
			mEditor.putString(ILocationCache.LONGITUDE, longitude);
			mEditor.putLong(ILocationCache.LAST_UAPDATE_TIME, System.currentTimeMillis());
			mEditor.commit();
		}
	}

	@Override
	public String getCurrentCityCode() {

		String currentName = mLocationUtil.getString(ILocationCache.CURRENT_CITY_NAME, null);
		return CityData.getCityCode(currentName);
	}

	@Override
	public Location getCurrentCityLocation() {

		String latitudes = mLocationUtil.getString(ILocationCache.LATITUDE, null);
		String longitudes = mLocationUtil.getString(ILocationCache.LONGITUDE, null);
		if (latitudes == null || longitudes == null) {
			return null;
		}
		double latitude = Double.parseDouble(latitudes);
		double longitude = Double.parseDouble(longitudes);
		return new Location(latitude, longitude);
	}

	public boolean hasLocationCache() {
		String currenCityName = mLocationUtil.getString(ILocationCache.CITY_NAME, null);
		return !StringUtils.isEmpty(currenCityName);
	}

	public boolean isCurrentCity() {
		String selectCityCode = getCityCode();
		if (selectCityCode == null) {
			return false;
		}
		String locationCityCode = getCurrentCityCode();
		if (selectCityCode.equals(locationCityCode)) {
			return true;
		}
		return false;

	}

	/**
	 * 是不是当前选择的城市 isCurrentCity:
	 * 
	 * @param cityName
	 *            用户点击切换的城市
	 * @return
	 * @since JDK 1.7
	 */
	public boolean isCurrentSelectCity(String cityName) {
		String selectCityCode = CityData.getCityCode(cityName);
		String locationCityCode = getCityCode();
		if (selectCityCode.equals(locationCityCode)) {
			return true;
		}
		return false;
	}

	@Override
	public void setLastUpdateTime() {
		Editor mEditor = mLocationUtil.edit();
		mEditor.putLong(ILocationCache.LAST_UAPDATE_TIME, System.currentTimeMillis());
		mEditor.commit();
	}

	@Override
	public Long getLastUpdateTime() {
		return mLocationUtil.getLong(ILocationCache.LAST_UAPDATE_TIME, 0);
	}

	public boolean isLocation() {
		Long lastTime = getLastUpdateTime();
		long oneDate = 60 * 60 * 24;

		if (new Date().getTime() - lastTime > oneDate) {
			return true;
		}
		return false;
	}

	public boolean isHasLocationData() {
		Long lastTime = getLastUpdateTime();

		if (System.currentTimeMillis() - lastTime > 0) {
			return true;
		}
		return false;
	}

}
