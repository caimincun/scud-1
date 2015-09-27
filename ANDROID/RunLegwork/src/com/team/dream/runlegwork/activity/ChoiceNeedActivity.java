package com.team.dream.runlegwork.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.fragment.ChoiceNeedFragment;
import com.team.dream.runlegwork.singleservice.ActivityProcessHandler;

public class ChoiceNeedActivity extends BaseActivity {
	public static final String ORDER_SKILL = "order_or_skill";
	boolean isOrder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		isOrder = getIntent().getBooleanExtra(ORDER_SKILL, true);
		if (isOrder) {
			ActivityProcessHandler.getInstance().putActivity(
					ActivityProcessHandler.CREATE_ORDRER_HANDLER, this);
		} else {
			ActivityProcessHandler.getInstance().putActivity(
					ActivityProcessHandler.CREATE_SKILL_HANDLER, this);
		}

		if (savedInstanceState == null) {
			initializeActivity(savedInstanceState);
		}

	}

	private void initializeActivity(Bundle savedInstanceState) {
		addFragment(R.id.container, ChoiceNeedFragment.newInstance(isOrder));
	}

	public static Intent getCallingIntent(Context context) {
		return new Intent(context, ChoiceNeedActivity.class);
	}
}
