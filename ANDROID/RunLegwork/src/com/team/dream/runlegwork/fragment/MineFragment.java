package com.team.dream.runlegwork.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.runlegwork.BaseFragment;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.navigator.Navigator;
import com.team.dream.runlegwork.singleservice.AccountManager;

public class MineFragment extends BaseFragment {

	private Context ctx;

	@InjectView(R.id.tv_nick_name)
	TextView tvNickName;
	@InjectView(R.id.rl_come_to_detail)
	RelativeLayout rlComeTodetail;

	private String userName;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View mainView = inflater.inflate(R.layout.fragment_mine, container, false);
		ButterKnife.inject(this, mainView);
		ctx = getActivity();
		tvNickName.setText(userName);

		return mainView;
	}


	@OnClick(R.id.rl_come_to_detail)
	public void ComeToUserDetail() {
		Navigator.NavigatorToUserDetail(ctx);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(this);
	}

	@Override
	protected void initializePresenter() {

		userName = AccountManager.getInstance().getUserinfo().getUserRealName();

	}

}
