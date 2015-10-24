package com.team.dream.runlegwork.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.team.dream.pulltorefresh.library.PullToRefreshListView;
import com.team.dream.runlegwork.DataApplication;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.adapter.ShopAdapter;
import com.team.dream.runlegwork.entity.LocationInfo;
import com.team.dream.runlegwork.entity.Store;
import com.team.dream.runlegwork.entity.LocationInfo.Location;
import com.team.dream.runlegwork.fragment.ShopTypeFragment;
import com.team.dream.runlegwork.interfaces.IAddressSetting;
import com.team.dream.runlegwork.interfaces.IPositioningOperation;
import com.team.dream.runlegwork.interfaces.OnRightClickListener;
import com.team.dream.runlegwork.interfaces.RequestApi;
import com.team.dream.runlegwork.net.JsonObjectResponseHandler;
import com.team.dream.runlegwork.net.response.ShopListResponse;
import com.team.dream.runlegwork.singleservice.LocationCache;
import com.team.dream.runlegwork.tool.Tool;
import com.team.dream.runlegwork.utils.StringUtils;
import com.team.dream.runlegwork.widget.MainTitileBar;
import com.team.dream.slidingmenu.lib.SlidingMenu;
import com.team.dream.slidingmenu.lib.app.SlidingFragmentActivity;

public class ShopActivity extends SlidingFragmentActivity implements IPositioningOperation, BDLocationListener{
	@InjectView(R.id.shop_ptListv)
	PullToRefreshListView plListv;
	@InjectView(R.id.shop_topbar)
	MainTitileBar mtb;
	FragmentTransaction ft;
	SlidingMenu sm;
	FragmentManager fragmentManager;

	private ShopAdapter shopAdapter;
	private List<Store> listdata = new ArrayList<Store>();
	
	protected RequestApi api = DataApplication.getInstance().getReQuestApi();
	
	private static final int UPDATE_TIME = 500;
	private LocationClient mLocClient;
	private IAddressSetting mAddressSetting;
	private boolean isLocationSuccess;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop);
		ButterKnife.inject(this);
		mtb.finishLeft(this);
		mtb.setTitle(R.string.shop);
		mtb.showRight();
		startPosition();
		loadSlidingmenu();
		initListener();
	}

	private void loadSlidingmenu() {
		fragmentManager = getSupportFragmentManager();
		ft = fragmentManager.beginTransaction();
		ShopTypeFragment sma = new ShopTypeFragment();
		ft.replace(R.id.flSlidingmenu, sma);
		ft.commit();

		sm = getSlidingMenu();
		sm.setMode(SlidingMenu.RIGHT_OF);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		setBehindContentView(R.layout.activity_slidingmenu);

		sm.setBehindWidthRes(R.dimen.slidingmenuwidth);

		// sm.setShadowWidth(0);
		sm.setShadowWidth(100);
		// sm.setBehindOffset(80);
		sm.setBehindOffsetRes(R.dimen.slidingmenuwidth);
		sm.setFadeDegree(0.3f);
		// sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

	}
	private void initListener() {
		plListv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(ShopActivity.this, GoodsShowActivity.class);
				Bundle b = new Bundle();
				b.putSerializable("store", listdata.get(arg2-1));
				intent.putExtras(b);
				startActivity(intent);
			}
		});
		
		mtb.setOnRightClickListener(new OnRightClickListener() {
			
			@Override
			public void onRightClick() {
				sm.showMenu();
			}
		});
		
	}
	
	private void requestdata(int page) {
		api.getShopList("全部", page, new JsonObjectResponseHandler<ShopListResponse>() {
			
			@Override
			public void onSuccess(ShopListResponse response) {
				listdata.clear();
				if(response.getData()!=null){
					listdata.addAll(response.getData());
				}
				dataChanged();
			}
			
			@Override
			public void onFailure(String errMsg) {
				Tool.showToast(ShopActivity.this, errMsg);
			}
		});
	}



	public void dataChanged() {
		if (shopAdapter == null) {
			shopAdapter = new ShopAdapter(this, listdata);
			plListv.setAdapter(shopAdapter);
		} else {
			shopAdapter.notifyDataSetChanged();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(this);
	}
	
	
	@Override
	public void startPosition() {
		// 定位初始化
		InitLocation();
		mLocClient.start();

	}

	private void InitLocation() {
		mLocClient = new LocationClient(this);
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
		requestdata(0);
	}


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

}
