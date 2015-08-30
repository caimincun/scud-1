package com.team.dream.runlegwork.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.entity.UserOrder;
import com.team.dream.runlegwork.fragment.OrderDetailFragment;

public class OrderDetailActivity extends BaseActivity {
	public static final String ORDER_DATA="order_data";
	private UserOrder mOrder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mOrder=(UserOrder) getIntent().getSerializableExtra(ORDER_DATA);
		if (savedInstanceState == null) {
			initializeActivity(savedInstanceState);
		}
	}

	private void initializeActivity(Bundle savedInstanceState) {
		addFragment(R.id.container, OrderDetailFragment.newInstance(mOrder));
	}

	public static Intent getCallingIntent(Context context) {
		return new Intent(context, OrderDetailActivity.class);
	}
}
