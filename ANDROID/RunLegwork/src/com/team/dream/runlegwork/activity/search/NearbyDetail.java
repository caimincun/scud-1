package com.team.dream.runlegwork.activity.search;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.pulltorefresh.library.PullToRefreshListView;
import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.SingletonServiceManager;
import com.team.dream.runlegwork.activity.requirement.SkillDetailActivity;
import com.team.dream.runlegwork.adapter.search.NearbyPeopleSkillAdapter;
import com.team.dream.runlegwork.entity.NearUserInfo;
import com.team.dream.runlegwork.entity.Skill;
import com.team.dream.runlegwork.net.JsonObjectResponseHandler;
import com.team.dream.runlegwork.net.response.SkillListResponse;
import com.team.dream.runlegwork.tool.Tool;
import com.team.dream.runlegwork.utils.PathUtil;
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
	@InjectView(R.id.nearby_detail_ivHead)
	ImageView ivHead;

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
		initListener();
	}

	private void requestData() {
		showProgressDialog();
		api.getSkillList(new JsonObjectResponseHandler<SkillListResponse>() {
			
			@Override
			public void onSuccess(SkillListResponse response) {
				removeProgressDialog();
				listdata = response.getData();
				dataChanged();
			}
			
			@Override
			public void onFailure(String errMsg) {
				removeProgressDialog();
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
	
	public void initListener(){
		plListv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(ctx, SkillDetailActivity.class);
				Bundle b = new Bundle();
				b.putSerializable("skill", listdata.get(arg2-1));
				b.putSerializable("userinfo", userInfo);
				intent.putExtras(b);
				startActivity(intent);
			}
		});
	}

	public void getExtrax() {
		userInfo = (NearUserInfo) getIntent().getExtras().getSerializable("userinfo");

	}

	private void initData() {
		tvAge.setText(userInfo.getAge() + "");
		tvDistance.setText(userInfo.getLocation()+ " " + userInfo.getUserDantce());
		tvIntriduce.setText(userInfo.getUserInfoIntroduction());
		tvJob.setText(userInfo.getUserInfoJob());
		tvName.setText(userInfo.getUserRealName());
		tvSign.setText(userInfo.getUserInfoSignature());
		topBar.initialze(userInfo.getUserRealName() + getString(R.string.near_detail));
		if (userInfo.getUserInfoSex() == 1) {
			SingletonServiceManager.getInstance().display("drawable://" + R.drawable.icon_boy, ivSex, R.drawable.icon_boy, null);
		} else {
			SingletonServiceManager.getInstance().display("drawable://" + R.drawable.icon_gril, ivSex, R.drawable.icon_boy, null);
		}
		SingletonServiceManager.getInstance().display(PathUtil.getPicPath(userInfo.getUserInfoPicture()), ivHead, R.drawable.user_default_head, null);
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
