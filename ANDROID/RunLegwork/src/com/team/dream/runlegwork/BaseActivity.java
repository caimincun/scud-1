package com.team.dream.runlegwork;

import com.team.dream.runlegwork.interfaces.RequestApi;
import com.team.dream.runlegwork.net.RequestApiImpl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

public abstract class BaseActivity extends FragmentActivity {
	protected RequestApi api;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		api = new RequestApiImpl(this);
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
