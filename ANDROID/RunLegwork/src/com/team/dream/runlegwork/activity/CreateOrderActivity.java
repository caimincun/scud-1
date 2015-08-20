package com.team.dream.runlegwork.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.fragment.CreateOrderFragment;
import com.team.dream.runlegwork.singleservice.ActivityProcessHandler;

public class CreateOrderActivity extends BaseActivity {

	public static final String SELET_NEED = "select_need";
	private String selectNeed;

	@Override
	protected void onCreate(Bundle onSaveInstanceState) {
		super.onCreate(onSaveInstanceState);
		setContentView(R.layout.activity_main);
		ActivityProcessHandler.getInstance().putActivity(
				ActivityProcessHandler.CREATE_ORDRER_HANDER, this);
		selectNeed = getIntent().getStringExtra(SELET_NEED);
		if (onSaveInstanceState == null) {
			initializeActivity();
		} else {
			initializeActivity(onSaveInstanceState);
		}
	}

	private void initializeActivity() {
		addFragment(R.id.container, CreateOrderFragment.newInstance(selectNeed));

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(SELET_NEED, selectNeed);
	}

	private void initializeActivity(Bundle onSaveInstanceState) {
		selectNeed = onSaveInstanceState.getString(SELET_NEED);
		addFragment(R.id.container, CreateOrderFragment.newInstance(selectNeed));
	}

	public static Intent getCallingIntent(Context context) {
		return new Intent(context, CreateOrderActivity.class);
	}

}
