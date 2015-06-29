package com.team.dream.runlegwork;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.team.dream.runlegwork.interfaces.RequestApi;
import com.team.dream.runlegwork.net.RequestApiImpl;

public abstract class BaseFragment extends Fragment {

	protected RequestApi api;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		
		api = new RequestApiImpl(getActivity());
		initializePresenter();
	}

	protected abstract void initializePresenter();
}
