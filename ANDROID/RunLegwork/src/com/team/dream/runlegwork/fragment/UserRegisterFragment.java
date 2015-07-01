package com.team.dream.runlegwork.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.runlegwork.BaseFragment;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.net.JsonObjectResponseHandler;
import com.team.dream.runlegwork.net.response.UserRegisterResponse;
import com.team.dream.runlegwork.widget.TopBar;

public class UserRegisterFragment extends BaseFragment {

	@InjectView(R.id.topbar)
	TopBar topBar;
	@InjectView(R.id.login)
	TextView tvLogin;
	@InjectView(R.id.user_name)
	EditText etUserName;
	@InjectView(R.id.user_password)
	EditText etUserPassword;
	@InjectView(R.id.at_once_register)
	TextView tvAtOnceLogin;
	@InjectView(R.id.forget_password)
	TextView tvFrogetPassword;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_user_register, container, false);
		ButterKnife.inject(this, view);
		topBar.initialze("你们好吗？");

		api.register("111aa1", "111111111111111", new JsonObjectResponseHandler<UserRegisterResponse>() {
			
			@Override
			public void onSuccess(UserRegisterResponse response) {
				Log.d("TAG", response.toString());
			}
			
			@Override
			public void onFailure(String errMsg) {
				
			}
		});
		return view;
	}

	@OnClick(R.id.login)
	public void login() {

	}

	@OnClick(R.id.at_once_register)
	public void navigatiorToRegister() {

	}

	@OnClick(R.id.forget_password)
	public void navigatiorToFoundPassword() {

	}

	@Override
	protected void initializePresenter() {

	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);
	}
}
