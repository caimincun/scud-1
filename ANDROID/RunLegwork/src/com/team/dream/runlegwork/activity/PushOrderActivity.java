package com.team.dream.runlegwork.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.fragment.PushOrderFragment;
import com.team.dream.runlegwork.singleservice.ActivityProcessHandler;

public class PushOrderActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle onSaveInstanceState) {
		super.onCreate(onSaveInstanceState);
		setContentView(R.layout.activity_main);
		ActivityProcessHandler.getInstance().putActivity(
				ActivityProcessHandler.CREATE_ORDRER_HANDLER, this);
		if (onSaveInstanceState == null) {
			initializeActivity(onSaveInstanceState);
		}
	}

	private void initializeActivity(Bundle onSaveInstanceState) {
		addFragment(R.id.container, PushOrderFragment.newInstance());
	}

	public static Intent getCallingIntent(Context context) {
		return new Intent(context, PushOrderActivity.class);
	}

}
