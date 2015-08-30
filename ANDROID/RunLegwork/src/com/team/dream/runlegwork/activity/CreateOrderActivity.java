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
	public static final String SELET_NEED_POSTION = "select_need_postion";
	private String selectNeed;
	private int positon;

	@Override
	protected void onCreate(Bundle onSaveInstanceState) {
		super.onCreate(onSaveInstanceState);
		setContentView(R.layout.activity_main);
		ActivityProcessHandler.getInstance().putActivity(
				ActivityProcessHandler.CREATE_ORDRER_HANDLER, this);
		selectNeed = getIntent().getStringExtra(SELET_NEED);
		positon = getIntent().getIntExtra(SELET_NEED_POSTION, 0);
		if (onSaveInstanceState == null) {
			initializeActivity();
		} else {
			initializeActivity(onSaveInstanceState);
		}
	}

	private void initializeActivity() {
		addFragment(R.id.container,
				CreateOrderFragment.newInstance(selectNeed, positon));

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(SELET_NEED, selectNeed);
		outState.putInt(SELET_NEED_POSTION, positon);
	}

	private void initializeActivity(Bundle onSaveInstanceState) {
		selectNeed = onSaveInstanceState.getString(SELET_NEED);
		positon = onSaveInstanceState.getInt(SELET_NEED_POSTION);
		addFragment(R.id.container,
				CreateOrderFragment.newInstance(selectNeed, positon));
	}

	public static Intent getCallingIntent(Context context) {
		return new Intent(context, CreateOrderActivity.class);
	}

}
