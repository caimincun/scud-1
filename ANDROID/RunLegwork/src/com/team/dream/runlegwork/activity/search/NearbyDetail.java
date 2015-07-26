package com.team.dream.runlegwork.activity.search;

import java.nio.Buffer;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.SingletonServiceManager;
import com.team.dream.runlegwork.entity.NearUserInfo;
import com.team.dream.runlegwork.entity.UserInfo;

public class NearbyDetail extends BaseActivity {
	private final String tag = NearbyDetail.class.getSimpleName();
	private Context ctx;
	
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
	
	private NearUserInfo userInfo;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_nearby_detail);
		ctx = this;
		ButterKnife.inject(this);
		getExtrax();
		initData();
	}
	public void getExtrax(){
		userInfo = (NearUserInfo) getIntent().getExtras().getSerializable("userinfo");
	}
	private void initData() {
		tvAge.setText(userInfo.getAge()+"");
		tvDistance.setText(""+userInfo.getUserDantce());
		tvIntriduce.setText(userInfo.getUserInfoIntroduction());
		tvJob.setText(userInfo.getUserInfoJob());
		tvName.setText(userInfo.getUserRealName());
		tvSign.setText(userInfo.getUserInfoSignature());
		if(userInfo.getUserInfoSex()==1){
			SingletonServiceManager.getInstance().display("drawable://"+R.drawable.icon_boy, ivSex, R.drawable.home_banner_2, null);
		}
		else{
			SingletonServiceManager.getInstance().display("drawable://"+R.drawable.icon_gril, ivSex, R.drawable.home_banner_2, null);
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
