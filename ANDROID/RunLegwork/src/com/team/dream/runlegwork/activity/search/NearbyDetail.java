package com.team.dream.runlegwork.activity.search;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.pulltorefresh.library.PullToRefreshListView;
import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.SingletonServiceManager;
import com.team.dream.runlegwork.adapter.search.NearbyPeopleSkillAdapter;
import com.team.dream.runlegwork.entity.NearUserInfo;
import com.team.dream.runlegwork.entity.Skill;
import com.team.dream.runlegwork.net.JsonObjectResponseHandler;
import com.team.dream.runlegwork.net.response.SkillListResponse;
import com.team.dream.runlegwork.tool.Tool;
import com.team.dream.runlegwork.widget.TopBar;

public class NearbyDetail extends BaseActivity {
	private final String tag = NearbyDetail.class.getSimpleName();
	private Context ctx;

	@InjectView(R.id.activity_nearbydetail_topbar)
	TopBar topBar;
	@InjectView(R.id.nearby_detail_tvName)
	TextView tvName;
	@InjectView(R.id.nearby_detail_tvAge)
	TextView tvAge;
	@InjectView(R.id.nearby_detail_tvDistance)
	TextView tvDistance;
	@InjectView(R.id.nearby_detail_tvJob)
	TextView tvJob;
	@InjectView(R.id.nearby_detail_tvIntriduce)
	TextView tvIntriduce;
	@InjectView(R.id.nearby_detail_tvSign)
	TextView tvSign;
	@InjectView(R.id.nearby_detail_ivSex)
	ImageView ivSex;
	@InjectView(R.id.nearbydetail_ptListv)
	PullToRefreshListView plListv;

	private NearUserInfo userInfo;
	private NearbyPeopleSkillAdapter npsAdapter;
	private List<Skill> listdata;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_nearby_detail);
		ctx = this;
		ButterKnife.inject(this);
		getExtrax();
		initData();
		requestData();
	}

	private void requestData() {
		api.getSkillList(new JsonObjectResponseHandler<SkillListResponse>() {
			
			@Override
			public void onSuccess(SkillListResponse response) {
				listdata = response.getData();
				dataChanged();
			}
			
			@Override
			public void onFailure(String errMsg) {
				Tool.showToast(ctx, errMsg);
			}
		});
	}

	public void dataChanged(){
		if(npsAdapter == null){
			npsAdapter = new NearbyPeopleSkillAdapter(ctx, listdata);
			plListv.setAdapter(npsAdapter);
		}
		else{
			npsAdapter.notifyDataSetChanged();
		}
	}

	public void getExtrax() {
		userInfo = (NearUserInfo) getIntent().getExtras().getSerializable("userinfo");

	}

	private void initData() {
		tvAge.setText(userInfo.getAge() + "");
		tvDistance.setText("" + userInfo.getUserDantce());
		tvIntriduce.setText(userInfo.getUserInfoIntroduction());
		tvJob.setText(userInfo.getUserInfoJob());
		tvName.setText(userInfo.getUserRealName());
		tvSign.setText(userInfo.getUserInfoSignature());
		topBar.initialze(userInfo.getUserRealName() + getString(R.string.near_detail));
		if (userInfo.getUserInfoSex() == 1) {
			SingletonServiceManager.getInstance().display("drawable://" + R.drawable.icon_boy, ivSex, R.drawable.home_banner_2, null);
		} else {
			SingletonServiceManager.getInstance().display("drawable://" + R.drawable.icon_gril, ivSex, R.drawable.home_banner_2, null);
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(this);
	}
}
