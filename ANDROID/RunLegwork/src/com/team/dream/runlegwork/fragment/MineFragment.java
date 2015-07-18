package com.team.dream.runlegwork.fragment;

import javax.annotation.Nullable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.runlegwork.BaseFragment;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.activity.account.AccountProfileActivity;

public class MineFragment extends BaseFragment {
	private View mainView;
	Context ctx;
	@InjectView(R.id.mine_layoutUserinfo)
	LinearLayout layoutUserinfo;
	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container,  Bundle savedInstanceState) {
		
		if(mainView ==null){
			mainView = inflater.inflate(R.layout.fragment_mine, null);
		}
		ViewGroup parent = (ViewGroup)mainView.getParent();
		if(null!=parent){
			parent.removeView(mainView);
		}
		ctx = getActivity();
		ButterKnife.inject(this, mainView);
		return mainView;
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	@OnClick(R.id.mine_layoutUserinfo)
	public void StartTouserinfo(){
		startActivity(new Intent(getActivity(), AccountProfileActivity.class));
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(this);
	}

	@Override
	protected void initializePresenter() {

	}

}
