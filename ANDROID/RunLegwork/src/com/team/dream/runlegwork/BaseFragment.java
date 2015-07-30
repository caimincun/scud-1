package com.team.dream.runlegwork;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.team.dream.runlegwork.interfaces.RequestApi;

public abstract class BaseFragment extends Fragment {

	protected RequestApi api=DataApplication.getInstance().getReQuestApi();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		
		initializePresenter();
	}

	protected abstract void initializePresenter();
}
