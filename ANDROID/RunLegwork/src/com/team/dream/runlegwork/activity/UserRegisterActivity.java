package com.team.dream.runlegwork.activity;

import android.os.Bundle;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.fragment.UserRegisterFragment;

public class UserRegisterActivity extends BaseActivity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			initializeActivity(savedInstanceState);
		}

	}

	private void initializeActivity(Bundle savedInstanceState) {
		addFragment(R.id.container, new UserRegisterFragment());
	}
}
