package com.team.dream.runlegwork.activity;

import org.apache.http.Header;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.entity.UserInfo;
import com.team.dream.runlegwork.navigator.Navigator;
import com.team.dream.runlegwork.net.JsonBooleanResponseHandler;
import com.team.dream.runlegwork.net.JsonObjectResponseHandler;
import com.team.dream.runlegwork.net.response.UserInfoResponse;
import com.team.dream.runlegwork.singleservice.AccountManager;
import com.team.dream.runlegwork.tool.Tool;
import com.team.dream.runlegwork.utils.AppUtils;
import com.team.dream.runlegwork.utils.StringUtils;
import com.team.dream.runlegwork.utils.ToastUtils;
import com.team.dream.runlegwork.widget.TopBar;

public class UserLoginActivity extends BaseActivity {
	private final String tag = UserLoginActivity.class.getSimpleName();
	@InjectView(R.id.user_name)
	EditText edtUsername;
	@InjectView(R.id.user_password)
	EditText edtPassword;
	@InjectView(R.id.login)
	TextView tvLogin;
	@InjectView(R.id.at_once_register)
	TextView tvRegister;
	@InjectView(R.id.forget_password)
	TextView tvForgetPwd;
	@InjectView(R.id.topbar)
	TopBar topbar;
	
	private String username, password;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.fragment_login);
		ButterKnife.inject(this);
		
		topbar.hideBack();
		topbar.initialze(getString(R.string.login));
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(this);
	}

	@OnClick(R.id.login)
	public void login() {
		username = edtUsername.getText().toString();
		password = edtPassword.getText().toString();
		if (StringUtils.isEmpty(username)) {
			ToastUtils.show(getApplicationContext(), "用户名不能为空");
		} else if (StringUtils.isEmpty(password)) {
			ToastUtils.show(getApplicationContext(), "密码不能为空");
		} else {
			api.login(username, password, null, new JsonBooleanResponseHandler() {

				@Override
				public void onSuccess() {
					AccountManager.getInstance().initUser(username);
					getUserinfoByToken();

				}

				@Override
				public void onSuccess(Header[] headers) {
					super.onSuccess(headers);
					AppUtils.setHeader(headers);
				}

				@Override
				public void onFailure(String errMsg) {
					Log.d(tag, "登录失败" + errMsg);
				}
			});
		}

	}

	@OnClick(R.id.at_once_register)
	public void toRegister() {
		startActivity(new Intent(this, UserRegisterActivity.class));
	}

	private void getUserinfoByToken() {
		Log.d(tag, "开始");
		api.getUserinfoByToken(new JsonObjectResponseHandler<UserInfoResponse>() {

			@Override
			public void onFailure(String errMsg) {
				Log.d(tag, "错误" + errMsg);
			}

			@Override
			public void onSuccess(UserInfoResponse response) {
				UserInfo userInfo = response.getData();
				AccountManager.getInstance().setUserinfo(userInfo);
				Log.d(tag, userInfo.toString() + "asdfs");
//				startActivity(new Intent(UserLoginActivity.this, WelcomeActivity.class));
				Navigator.NavigatorToMainActivity(UserLoginActivity.this);
				
				if(!StringUtils.isEmpty(userInfo.getUserInfoPicture())){
					try {
						String path = AccountManager.USER_HEAD_DOWNLOAD_PATH+userInfo.getUserInfoPicture();
						Log.d(tag, path);
						Tool.downloadPics(path,AccountManager.USER_HEAD_NAME, "/sdcard");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

	public static Intent getCallingIntent(Context context) {
		return new Intent(context, UserLoginActivity.class);
	}
}
