package com.team.dream.runlegwork.activity.search;

import android.content.Context;
import android.os.Bundle;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;

public class NearbyDetail extends BaseActivity {
	private final String tag = NearbyDetail.class.getSimpleName();
	private Context ctx;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_nearby_detail);
		ctx = this;
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
