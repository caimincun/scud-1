package com.team.dream.runlegwork.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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

		gvFucAll.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent();
				intent.putExtra("data", allType[arg2]);
				getActivity().setResult(Activity.RESULT_OK, intent);
				getActivity().finish();
			}
		});
		gvFucHotCommend.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent();
				intent.putExtra("data", hotType[arg2]);
				getActivity().setResult(Activity.RESULT_OK, intent);
				getActivity().finish();
			}
		});
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
