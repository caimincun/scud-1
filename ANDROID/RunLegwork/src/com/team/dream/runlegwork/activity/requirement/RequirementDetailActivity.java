package com.team.dream.runlegwork.activity.requirement;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.SingletonServiceManager;
import com.team.dream.runlegwork.entity.UserOrder;
import com.team.dream.runlegwork.widget.MainTitileBar;

public class RequirementDetailActivity extends BaseActivity {
	@InjectView(R.id.requirementdetail_topbar)
	MainTitileBar mtb;
	@InjectView(R.id.requirementdetail_tvName)
	TextView tvName;
	@InjectView(R.id.requirementdetail_tvDistance)
	TextView tvDistance;
	@InjectView(R.id.requirementdetail_tvRequirement)
	TextView tvRequirement;
	@InjectView(R.id.requirementdetail_tvReqdetail)
	TextView tvReqdetail;
	@InjectView(R.id.requirementdetail_tvAddress)
	TextView tvAddress;
	@InjectView(R.id.requirementdetail_tvTime)
	TextView tvTime;
	@InjectView(R.id.requirementdetail_ivSex)
	ImageView ivSex;
	@InjectView(R.id.requirementdetail_ivHead)
	ImageView ivHead;
	
	private UserOrder userOrder;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_requirementdetail);
		ButterKnife.inject(this);
		mtb.setTitle("需求详情");
		mtb.finishLeft(this);
		getExtras();
		
		initData();
	}
	
	private void initData() {
		int sex = userOrder.getUserSex();
		if (sex==1) {
			ivSex.setImageResource(R.drawable.icon_boy);
		}
		else{
			ivSex.setImageResource(R.drawable.icon_gril);
		}
		tvDistance.setText(userOrder.getDistance()+"");
		tvRequirement.setText(userOrder.getOrderTitle()+"");
		tvReqdetail.setText(userOrder.getOrderContent()+"");
		SingletonServiceManager.getInstance().display("http://scud-images.bj.bcebos.com"+userOrder.getUserPicture(), ivHead, R.drawable.user_default_head, null);
	}

	public void getExtras(){
		userOrder = (UserOrder) getIntent().getExtras().getSerializable("userorder");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(this);
	}
}
