package com.team.dream.runlegwork.fragment;

import android.content.Intent;
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

import com.loopj.android.http.JsonHttpResponseHandler;
import com.team.dream.runlegwork.BaseFragment;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.activity.account.AccountProfileActivity;
import com.team.dream.runlegwork.entity.UserInfo;
import com.team.dream.runlegwork.net.JsonObjectResponseHandler;
import com.team.dream.runlegwork.net.response.UserInfoResponse;
import com.team.dream.runlegwork.net.response.UserRegisterResponse;
import com.team.dream.runlegwork.singleservice.AccountManager;
import com.team.dream.runlegwork.utils.StringUtils;
import com.team.dream.runlegwork.utils.ToastUtils;
import com.team.dream.runlegwork.widget.TopBar;

public class UserRegisterFragment extends BaseFragment {
	private final String tag = UserRegisterFragment.class.getSimpleName();
	@InjectView(R.id.topbar)
	TopBar topBar;
	@InjectView(R.id.user_register)
	TextView tvRegister;
	@InjectView(R.id.user_name)
	EditText etUserName;
	@InjectView(R.id.user_password)
	EditText etUserPassword;
	@InjectView(R.id.user_confirmpwd)
	EditText etUserConPwd;
	private String username,password,conPassword;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_user_register, container, false);
		ButterKnife.inject(this, view);
		topBar.initialze(getResources().getString(R.string.register));
		
		return view;
	}

	@OnClick(R.id.user_register)
	public void register() {
			if(!check()){
				return;
			}
			api.register(username, password, new JsonObjectResponseHandler<UserRegisterResponse>() {
			
			@Override
			public void onSuccess(UserRegisterResponse response) {
				AccountManager.getInstance().initUser(response.getData(), username);
				getUserinfoByToken(response.getData());
			}
			
			@Override
			public void onFailure(String errMsg) {
				Log.d(tag, errMsg);
			}
		});
	}
	
	private void getUserinfoByToken(String token) {
		Log.d(tag, "开始");
		api.getUserinfoByToken(token, new JsonObjectResponseHandler<UserInfoResponse>() {

			@Override
			public void onFailure(String errMsg) {
				// TODO Auto-generated method stub
				Log.d(tag,"错误"+errMsg);
			}

			@Override
			public void onSuccess(UserInfoResponse response) {
				// TODO Auto-generated method stub
				UserInfo userInfo = response.getData();
				Log.d(tag, userInfo.getUserInfoEmail()+"asdfs");
				startActivity(new Intent(getActivity(), AccountProfileActivity.class));
			}
		});
	}

	private boolean check() {
		username = etUserName.getText().toString().trim();
		password = etUserPassword.getText().toString().trim();
		conPassword = etUserConPwd.getText().toString().trim();
		if(StringUtils.isEmpty(username)){
			ToastUtils.show(getActivity(), "用户名不能为空");
			return false;
		}
		if(StringUtils.isEmpty(password)){
			ToastUtils.show(getActivity(), "密码不能为空");
			return false;
		}
		if(StringUtils.isEmpty(conPassword)){
			ToastUtils.show(getActivity(), "确认密码不能为空");
			return false;
		}
		if(!password.equals(conPassword)){
			ToastUtils.show(getActivity(), "两次密码输入不一致");
			return false;
		}
		return true;
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
