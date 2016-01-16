package com.team.dream.runlegwork;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.team.dream.runlegwork.dialog.AsyncOpteratorView;
import com.team.dream.runlegwork.dialog.MyProgressDialog;
import com.team.dream.runlegwork.interfaces.RequestApi;

public abstract class BaseFragment extends Fragment {

	protected RequestApi api=DataApplication.getInstance().getReQuestApi();
	protected AsyncOpteratorView asyncTipView;
	private   MyProgressDialog mProgressDialog;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		
		initializePresenter();
	}

	/**
	 * 显示进度对话框
	 */
	public final void showProgressDialog() {
		if (mProgressDialog == null) {
			mProgressDialog = new MyProgressDialog(getActivity());		
		}		
		mProgressDialog.setCanceledOnTouchOutside(false);		
		
		if(null != getActivity() && !getActivity().isFinishing()){
			mProgressDialog.show();
		}
	}
	/**
	 * 隐藏进度对话框
	 */
	public final void removeProgressDialog() {
		if (mProgressDialog != null) {
			mProgressDialog.dismiss();
		}
	}

	protected abstract void initializePresenter();
}
