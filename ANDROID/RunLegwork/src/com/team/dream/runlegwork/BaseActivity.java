package com.team.dream.runlegwork;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.NumberPicker;

import com.team.dream.runlegwork.interfaces.RequestApi;
import com.team.dream.runlegwork.singleservice.Syseting;

public abstract class BaseActivity extends FragmentActivity {
	protected RequestApi api = DataApplication.getInstance().getReQuestApi();

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Syseting.addAct(this);
	}

	protected void addFragment(int contentResId, Fragment fragment) {
		getSupportFragmentManager().beginTransaction().add(contentResId, fragment).commit();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		super.onResume();

	}

}
