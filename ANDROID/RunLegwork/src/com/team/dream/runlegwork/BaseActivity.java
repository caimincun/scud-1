package com.team.dream.runlegwork;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.Window;

import com.team.dream.runlegwork.dialog.MyProgressDialog;
import com.team.dream.runlegwork.interfaces.RequestApi;
import com.team.dream.runlegwork.singleservice.Syseting;

public abstract class BaseActivity extends FragmentActivity {
	protected RequestApi api = DataApplication.getInstance().getReQuestApi();
	private DisplayMetrics metrics=new DisplayMetrics();

	private   MyProgressDialog mProgressDialog;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Syseting.addAct(this);
		getWindow().getWindowManager().getDefaultDisplay().getMetrics(metrics);
	}

	protected void addFragment(int contentResId, Fragment fragment) {
		getSupportFragmentManager().beginTransaction()
				.add(contentResId, fragment).commit();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	protected int getWidth() {
		return metrics.widthPixels;
	}
	
	/**
	 * 显示进度对话框
	 */
	public final void showProgressDialog() {
		if (mProgressDialog == null) {
			mProgressDialog = new MyProgressDialog(this);		
		}		
		mProgressDialog.setCanceledOnTouchOutside(false);		
		
		if(!isFinishing())
		mProgressDialog.show();
	}
	/**
	 * 隐藏进度对话框
	 */
	public final void removeProgressDialog() {
		if (mProgressDialog != null) {
			mProgressDialog.dismiss();
		}
	}

}
