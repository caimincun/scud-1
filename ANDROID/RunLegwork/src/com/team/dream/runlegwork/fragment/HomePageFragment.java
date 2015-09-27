package com.team.dream.runlegwork.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.runlegwork.BaseFragment;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.activity.ShopActivity;
import com.team.dream.runlegwork.activity.requirement.RequirementHomeActivity;
import com.team.dream.runlegwork.activity.requirement.SkillPeopleActivity;
import com.team.dream.runlegwork.adapter.HomePageAdapter;
import com.team.dream.runlegwork.adapter.HomePageAdapter.OnHomeFucClickListener;
import com.team.dream.runlegwork.navigator.Navigator;

public class HomePageFragment extends BaseFragment implements
		OnHomeFucClickListener {

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
		adapter.setOnFucClickListener(this);
		return view;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);
	}

	@Override
	public void OnSelectFucPostion(int position) {
		switch (position) {
		case 0:
			Navigator.NavigatorToPushOrderActivity(getActivity());
			break;
		case 1:
			startActivity(new Intent(getActivity(),
					RequirementHomeActivity.class));
			break;
		case 2:
			Navigator.NavigatorToPushSkillActivity(getActivity());
			break;

		}

	}

	@Override
	public void OnSelectSkillPostion(int position) {
		switch (position) {
		case 0:
			toSkillpeopleActivity("陪聊");
			break;
		case 1:
			startActivity(new Intent(getActivity(), ShopActivity.class));
			break;
		case 2:
			toSkillpeopleActivity("家政");
			break;
		case 3:
			toSkillpeopleActivity("按摩");
			break;

		default:
			break;
		}
	}

	private void toSkillpeopleActivity(String condition) {
		Intent intent = new Intent(getActivity(), SkillPeopleActivity.class);
		intent.putExtra("condition", condition);
		startActivity(intent);
	}
}
