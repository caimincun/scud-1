package com.team.dream.runlegwork.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;

import com.team.dream.runlegwork.BaseFragment;
import com.team.dream.runlegwork.R;

public class ShopTypeFragment extends BaseFragment {
	private Context ctx;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View mainView = inflater.inflate(R.layout.fragment_shoptype, container,
				false);
		ButterKnife.inject(this, mainView);
		ctx = getActivity();
		
		return mainView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
			
	}
	@Override
	protected void initializePresenter() {
		// TODO Auto-generated method stub

	}

}
