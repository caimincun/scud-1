package com.team.dream.runlegwork.fragment;

import java.io.File;

import android.content.Context;
import android.content.Intent;
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
import com.team.dream.runlegwork.SingletonServiceManager;
import com.team.dream.runlegwork.activity.account.PeopleSettingActivity;
import com.team.dream.runlegwork.navigator.Navigator;
import com.team.dream.runlegwork.singleservice.AccountManager;
import com.team.dream.runlegwork.view.RoundImageView;

public class MineFragment extends BaseFragment {

	private Context ctx;

	@InjectView(R.id.tv_nick_name)
	TextView tvNickName;
	@InjectView(R.id.rl_come_to_detail)
	RelativeLayout rlComeTodetail;
	@InjectView(R.id.mine_setting)
	RelativeLayout llSetting;
	@InjectView(R.id.iv_hear_icon)
	RoundImageView ivHead;

	private String userName;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View mainView = inflater.inflate(R.layout.fragment_mine, container,
				false);
		ButterKnife.inject(this, mainView);
		ctx = getActivity();
		tvNickName.setText(userName);

		return mainView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
			
	}
	
	@Override
	public void onResume() {
		super.onResume();
		loadHead();
			
	}

	private void loadHead() {
		//加载头像
				File filehead = new File("/sdcard/headimg.png");
				SingletonServiceManager.getInstance().imageLoader.clearMemoryCache();
				SingletonServiceManager.getInstance().imageLoader.clearDiskCache();
				SingletonServiceManager.getInstance().display("file://" + filehead.getAbsolutePath(), ivHead, R.drawable.user_default_head, null);
	}

	@OnClick(R.id.rl_come_to_detail)
	public void ComeToUserDetail() {
		Navigator.NavigatorToUserDetail(ctx);
	}

	@OnClick(R.id.mine_setting)
	public void setting() {
		startActivity(new Intent(ctx, PeopleSettingActivity.class));
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
