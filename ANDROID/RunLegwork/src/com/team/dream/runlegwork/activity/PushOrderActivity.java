package com.team.dream.runlegwork.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.fragment.PushOrderFragment;
import com.team.dream.runlegwork.singleservice.ActivityProcessHandler;

public class PushOrderActivity extends BaseActivity {

	private PushOrderFragment fragment;

	@Override
	protected void onCreate(Bundle onSaveInstanceState) {
		super.onCreate(onSaveInstanceState);
		setContentView(R.layout.activity_main);
		ActivityProcessHandler.getInstance().putActivity(
				ActivityProcessHandler.CREATE_ORDRER_HANDLER, this);
		if (onSaveInstanceState == null) {
			fragment = PushOrderFragment.newInstance();
			initializeActivity(onSaveInstanceState);
		}
	}

	private void initializeActivity(Bundle onSaveInstanceState) {
		addFragment(R.id.container, fragment);
	}

	public static Intent getCallingIntent(Context context) {
		return new Intent(context, PushOrderActivity.class);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		fragment.onActivityResult(requestCode, resultCode, data);
	}
}
