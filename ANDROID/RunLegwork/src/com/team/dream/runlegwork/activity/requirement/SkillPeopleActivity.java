package com.team.dream.runlegwork.activity.requirement;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.adapter.search.SkillPeopleAdapter;
import com.team.dream.runlegwork.entity.LocationInfo;
import com.team.dream.runlegwork.entity.SkillAndUser;
import com.team.dream.runlegwork.entity.LocationInfo.Location;
import com.team.dream.runlegwork.interfaces.IAddressSetting;
import com.team.dream.runlegwork.interfaces.IPositioningOperation;
import com.team.dream.runlegwork.interfaces.RequestApi;
import com.team.dream.runlegwork.net.JsonObjectResponseHandler;
import com.team.dream.runlegwork.net.response.SkillpeopleDetailResponse;
import com.team.dream.runlegwork.singleservice.LocationCache;
import com.team.dream.runlegwork.tool.Tool;
import com.team.dream.runlegwork.utils.StringUtils;
import com.team.dream.runlegwork.widget.MainTitileBar;

public class SkillPeopleActivity extends BaseActivity implements IPositioningOperation, BDLocationListener{
	private Context ctx;
	@InjectView(R.id.skillpeople_ptListv)
	PullToRefreshListView plListv;
	@InjectView(R.id.skillpeople_topbar)
	MainTitileBar mtb;
	
	private SkillPeopleAdapter skillPeopleAdapter;
	private List<String> list;
	List<SkillAndUser> listdata = new ArrayList<SkillAndUser>();
	
	
	private static final int UPDATE_TIME = 500;
	private LocationClient mLocClient;
	private IAddressSetting mAddressSetting;
	private boolean isLocationSuccess;
	
	private String condition;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_skillpeople);
		ButterKnife.inject(this);
		ctx = this;
		initExtras();
		startPosition();
		initListener();
		
	}
	
	private void initExtras() {
		condition = getIntent().getExtras().getString("condition");
		mtb.setTitle(condition);
	}

	private void initData() {
		showProgressDialog();
		listdata.clear();
		api.getSkillpeopleDetail(0,condition, new JsonObjectResponseHandler<SkillpeopleDetailResponse>() {
			
			@Override
			public void onSuccess(SkillpeopleDetailResponse response) {
				removeProgressDialog();
				listdata.addAll(response.getData());
				dataChanged();
			}
			
			@Override
			public void onFailure(String errMsg) {
				Tool.showToast(ctx, errMsg);
				removeProgressDialog();
			}
		});
		
		
		dataChanged();
	}
	private void initListener() {
//		plListv.setOnRefreshListener(this);
		plListv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(ctx, SkillPeopleDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("skillpeople", listdata.get(arg2-1));
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}
	
	public void dataChanged(){
		if(skillPeopleAdapter == null){
			skillPeopleAdapter = new SkillPeopleAdapter(ctx,getWidth(), listdata);
			plListv.setAdapter(skillPeopleAdapter);
		}
		else{
			skillPeopleAdapter.notifyDataSetChanged();
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
		initData();
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
