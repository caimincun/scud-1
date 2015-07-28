package com.team.dream.runlegwork.fragment;

import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.team.dream.runlegwork.BaseFragment;
import com.team.dream.runlegwork.entity.LocationInfo;
import com.team.dream.runlegwork.entity.LocationInfo.Location;
import com.team.dream.runlegwork.interfaces.IAddressSetting;
import com.team.dream.runlegwork.interfaces.IPositioningOperation;
import com.team.dream.runlegwork.singleservice.LocationCache;
import com.team.dream.runlegwork.utils.StringUtils;

public abstract class LocationFragment extends BaseFragment implements IPositioningOperation, BDLocationListener {

	private static final int UPDATE_TIME = 500;
	private LocationClient mLocClient;
	private IAddressSetting mAddressSetting;
	private boolean isLocationSuccess;

	@Override
	public void startPosition() {
		// 定位初始化
		InitLocation();
		mLocClient.start();

	}

	private void InitLocation() {
		mLocClient = new LocationClient(getActivity());
		mLocClient.registerLocationListener(this);
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);// 设置定位模式
		option.setCoorType("bd0911");// 返回的定位结果是百度经纬度，默认值gcj02
		option.setScanSpan(UPDATE_TIME);// 设置发起定位请求的间隔时间为5000ms
		option.setIsNeedAddress(true);
		mLocClient.setLocOption(option);
	}

	@Override
	public void OnCompleteLocation(BDLocation location) {
		Log.d("TAG", "location_start");
		if (location == null || StringUtils.isEmpty(location.getCity())) {
			isLocationSuccess = false;
		} else {
			isLocationSuccess = true;
			mAddressSetting = LocationCache.getIntance();
			LocationInfo locationInfo = new LocationInfo();
			locationInfo.setCityName(location.getCity());
			locationInfo.setLocation(new Location(location.getLatitude(), location.getLongitude()));
			mAddressSetting.savaCurrentLocationInfo(locationInfo);
		}
		OnCompleteLocation(isLocationSuccess);
	}

	public abstract void OnCompleteLocation(boolean isLocationSuccess);

	@Override
	public void onReceiveLocation(BDLocation location) {
		OnCompleteLocation(location);
		if (mLocClient != null) {
			mLocClient.stop();
			mLocClient = null;
		}
	}

	public void onReceivePoi(BDLocation poiLocation) {
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mLocClient != null) {
			mLocClient.stop();
		}
	}

	@Override
	public void onStop() {
		super.onStop();
		if (mLocClient != null) {
			mLocClient.stop();
		}
	}



}
