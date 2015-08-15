package com.team.dream.runlegwork.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.fragment.ChoiceNeedFragment;

public class ChoiceNeedActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			initializeActivity(savedInstanceState);
		}

	}

	private void initializeActivity(Bundle savedInstanceState) {
		addFragment(R.id.container, ChoiceNeedFragment.newInstance());
	}

	public static Intent getCallingIntent(Context context) {
		return new Intent(context, ChoiceNeedActivity.class);
	}
}
