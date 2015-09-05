package com.team.dream.runlegwork.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.runlegwork.BaseFragment;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.adapter.SelectOrderOrSkillAdapter;
import com.team.dream.runlegwork.widget.MainTitileBar;

public class SelectOrderOrSkillFragment extends BaseFragment {

	@InjectView(R.id.gv_fuc_hot_commend)
	GridView gvFucHotCommend;
	@InjectView(R.id.gv_fuc_all)
	GridView gvFucAll;
	@InjectView(R.id.mb_topbar)
	MainTitileBar mbTopBar;

	private String[] hotType;
	private String[] allType;
	private SelectOrderOrSkillAdapter hotAdapter;
	private SelectOrderOrSkillAdapter allAdapter;

	public static SelectOrderOrSkillFragment newInstance() {
		SelectOrderOrSkillFragment fragment = new SelectOrderOrSkillFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_select_type_or_skill,
				container, false);
		ButterKnife.inject(this, view);
		gvFucAll.setAdapter(allAdapter);
		gvFucHotCommend.setAdapter(hotAdapter);
		mbTopBar.setTitle(getString(R.string.select_type));
		return view;
	}

	@Override
	protected void initializePresenter() {
		allType = getResources().getStringArray(R.array.choice_need);
		hotType = getResources().getStringArray(R.array.choice_hot);
		hotAdapter = new SelectOrderOrSkillAdapter(getActivity(), hotType);
		allAdapter = new SelectOrderOrSkillAdapter(getActivity(), allType);

	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);
	}
}
