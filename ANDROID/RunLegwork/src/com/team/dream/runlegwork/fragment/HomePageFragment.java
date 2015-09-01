package com.team.dream.runlegwork.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.runlegwork.BaseFragment;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.adapter.HomePageAdapter;

public class HomePageFragment extends BaseFragment {

	@InjectView(R.id.lv_home_pagse)
	ListView lvHomePage;

	private HomePageAdapter adapter;

	@Override
	protected void initializePresenter() {
		adapter = new HomePageAdapter(getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home_page, container,
				false);
		ButterKnife.inject(this, view);

		lvHomePage.setAdapter(adapter);
		return view;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);
	}
}
