package com.team.dream.runlegwork.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.fragment.CreateOrderFragment;

public class CreateOrderActivity extends BaseActivity {

	public static String SELET_NEED="select_need";
	private String selectNeed;
	@Override
	protected void onCreate(Bundle onSaveInstanceState) {
		super.onCreate(onSaveInstanceState);
		setContentView(R.layout.activity_main);
		selectNeed=getIntent().getStringExtra(SELET_NEED);
		if (onSaveInstanceState == null) {
			initializeActivity(onSaveInstanceState);
		}
	}

	private void initializeActivity(Bundle onSaveInstanceState) {
		addFragment(R.id.container, CreateOrderFragment.newInstance(selectNeed));
	}

	public static Intent getCallingIntent(Context context) {
		return new Intent(context, CreateOrderActivity.class);
	}

}
