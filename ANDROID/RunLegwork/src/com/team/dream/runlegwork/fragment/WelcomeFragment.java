package com.team.dream.runlegwork.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.team.dream.runlegwork.BaseFragment;
import com.team.dream.runlegwork.R;

public class WelcomeFragment extends BaseFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.view_tab_select, container, false);
		return view;
	}

	@Override
	protected void initializePresenter() {

	}

}
