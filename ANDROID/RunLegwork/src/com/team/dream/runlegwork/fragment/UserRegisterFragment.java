package com.team.dream.runlegwork.fragment;

import org.apache.http.Header;

import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.team.dream.runlegwork.entity.UserInfo;
import com.team.dream.runlegwork.navigator.Navigator;
import com.team.dream.runlegwork.net.JsonBooleanResponseHandler;
import com.team.dream.runlegwork.net.JsonObjectResponseHandler;
import com.team.dream.runlegwork.net.response.CheckNumResponse;
import com.team.dream.runlegwork.net.response.UserInfoResponse;
import com.team.dream.runlegwork.singleservice.AccountManager;
import com.team.dream.runlegwork.tool.RegexUtil;
import com.team.dream.runlegwork.tool.Tool;
import com.team.dream.runlegwork.utils.AppUtils;
import com.team.dream.runlegwork.utils.StringUtils;
import com.team.dream.runlegwork.utils.ToastUtils;
import com.team.dream.runlegwork.widget.MainTitileBar;

public class UserRegisterFragment extends BaseFragment {
	private final String tag = UserRegisterFragment.class.getSimpleName();
	@InjectView(R.id.topbar)
	MainTitileBar topBar;
	@InjectView(R.id.user_register)
	TextView tvRegister;
	@InjectView(R.id.user_name)
	EditText etUserName;
	@InjectView(R.id.user_password)
	EditText etUserPassword;
	@InjectView(R.id.user_confirmpwd)
	EditText etUserConPwd;
	@InjectView(R.id.register_tvGetChecknum)
	TextView tvGetChecknum;
	@InjectView(R.id.user_edtCheckNum)
	EditText edtChecknum;
	
	private String username, password, conPassword,checkNum,checkNumServer;
	private myCountTimer countTimer;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_register, container, false);
		ButterKnife.inject(this, view);
//		topBar.initialze(getResources().getString(R.string.register));
		topBar.setTitle(getResources().getString(R.string.register));
		
		return view;
	}

	@OnClick(R.id.user_register)
	public void register() {
		if (!check()) {
			return;
		}
		showProgressDialog();
		api.register(username, password, new JsonBooleanResponseHandler() {

			@Override
			public void onSuccess() {
				AccountManager.getInstance().initUser(username);
				getUserinfoByToken();
			}

			@Override
			public void onSuccess(Header[] headers) {
				AppUtils.setHeader(headers);
			}

			@Override
			public void onFailure(String errMsg) {
				Log.d(tag, "注册失败" + errMsg);
				Tool.showToast(getActivity(), errMsg);
				showProgressDialog();
			}
		});
	}

	private void getUserinfoByToken() {
		Log.d(tag, "开始");
		api.getUserinfoByToken(new JsonObjectResponseHandler<UserInfoResponse>() {

			@Override
			public void onFailure(String errMsg) {
				Log.d(tag, "错误" + errMsg);
				removeProgressDialog();
			}

			@Override
			public void onSuccess(UserInfoResponse response) {
				removeProgressDialog();
				UserInfo userInfo = response.getData();
				AccountManager.getInstance().setUserinfo(userInfo);
				Log.d(tag, userInfo.getUserInfoEmail() + "asdfs");
//				startActivity(new Intent(getActivity(), AccountProfileActivity.class));
				Navigator.NavigatorToMainActivity(getActivity());
			}
		});
	}
	
    @OnClick(R.id.register_tvGetChecknum)
    public void getChecknum(){
    	
    	username = etUserName.getText().toString().trim();
    	if(!RegexUtil.isMobileNO(username)){
    		Tool.showToast(getActivity(), "手机号码格式不对");
    	}
    	else if(StringUtils.isEmpty(username)){
    		Tool.showToast(getActivity(), "请先输入手机号");
    	}
    	else{
    		showCountTimer(120*1000, 1000);
    		tvGetChecknum.setClickable(false);
    		api.getChecknum(username, new JsonObjectResponseHandler<CheckNumResponse>() {
				
				@Override
				public void onSuccess(CheckNumResponse response) {
					checkNumServer = response.getData();
				}
				
				@Override
				public void onFailure(String errMsg) {
					Tool.showToast(getActivity(), errMsg);
					stopCountTimer();
				}
			});
    	}
    	
    }
    private void showCountTimer(long allTime,long time){
    	stopCountTimer();
    		countTimer = new myCountTimer(allTime, time);
    		countTimer.start();
    }
    private void stopCountTimer(){
    	tvGetChecknum.setClickable(true);
    	tvGetChecknum.setText("重新获取");
    	if(countTimer !=null){
    		countTimer.cancel();
    	}
    }
    
    public class myCountTimer extends CountDownTimer{

		public myCountTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
			tvGetChecknum.setText(millisInFuture / 1000 + "秒");
		}

		@Override
		public void onTick(long millisUntilFinished) {
			tvGetChecknum.setText(millisUntilFinished / 1000 + "秒");
		}

		@Override
		public void onFinish() {
			tvGetChecknum.setClickable(true);
			tvGetChecknum.setText("重新获取");
		}
    	
    }

	private boolean check() {
		username = etUserName.getText().toString().trim();
		password = etUserPassword.getText().toString().trim();
		conPassword = etUserConPwd.getText().toString().trim();
		checkNum = edtChecknum.getText().toString().trim();
		if (StringUtils.isEmpty(username)) {
			ToastUtils.show(getActivity(), "用户名不能为空");
			return false;
		}
		if(!RegexUtil.isMobileNO(username)){
			ToastUtils.show(getActivity(), "手机号码格式不对");
			return false;
		}
		if (StringUtils.isEmpty(password)) {
			ToastUtils.show(getActivity(), "密码不能为空");
			return false;
		}
		if (StringUtils.isEmpty(conPassword)) {
			ToastUtils.show(getActivity(), "确认密码不能为空");
			return false;
		}
		if (StringUtils.isEmpty(checkNum)){
			ToastUtils.show(getActivity(), "验证码不能为空");
			return false;
		}
		
		if (!password.equals(conPassword)) {
			ToastUtils.show(getActivity(), "两次密码输入不一致");
			return false;
		}
		if(!checkNum.equals(checkNumServer)){
			ToastUtils.show(getActivity(), "验证码输入有误");
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
