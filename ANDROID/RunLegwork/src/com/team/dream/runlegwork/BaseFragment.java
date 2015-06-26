package com.team.dream.runlegwork;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.team.dream.runlegwork_data.Interface.RequestApi;
import com.team.dream.runlegwork_data.net.RequestApiImpl;

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
