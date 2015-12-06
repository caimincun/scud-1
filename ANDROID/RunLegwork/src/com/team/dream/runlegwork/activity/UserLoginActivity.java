package com.team.dream.runlegwork.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroupManager;
import com.easemob.chatuidemo.db.UserDao;
import com.easemob.chatuidemo.domain.User;
import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.chathelper.Constant;
import com.team.dream.runlegwork.chathelper.DemoHXSDKHelper;
import com.team.dream.runlegwork.chathelper.HXSDKHelper;
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
import com.team.dream.runlegwork.widget.MainTitileBar;
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
	MainTitileBar topbar;
	
	private String username, password;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.fragment_mylogin);
		ButterKnife.inject(this);
		topbar.setTitle(getString(R.string.login));
		topbar.hideTitleLeft();
		
//		topbar.hideBack();
//		topbar.initialze(getString(R.string.login));
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
		Tool.hiddenSoftKeyboard(UserLoginActivity.this, edtPassword);
		if (StringUtils.isEmpty(username)) {
			ToastUtils.show(getApplicationContext(), "用户名不能为空");
		} else if (StringUtils.isEmpty(password)) {
			ToastUtils.show(getApplicationContext(), "密码不能为空");
		} else {
			showProgressDialog();
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
					ToastUtils.show(UserLoginActivity.this,  "登录失败" + errMsg);
					removeProgressDialog();
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
				removeProgressDialog();
				Log.d(tag, "错误" + errMsg);
			}

			@Override
			public void onSuccess(UserInfoResponse response) {
				UserInfo userInfo = response.getData();
				AccountManager.getInstance().setUserinfo(userInfo);
				Log.d(tag, userInfo.toString() + "asdfs");
//				startActivity(new Intent(UserLoginActivity.this, WelcomeActivity.class));
				if(!StringUtils.isEmpty(userInfo.getUserInfoPicture())){
						final String path = AccountManager.USER_HEAD_DOWNLOAD_PATH+userInfo.getUserInfoPicture();
						Log.d(tag, path);
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								try {
									Tool.downloadPics(path,AccountManager.USER_HEAD_NAME, "/sdcard");
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}).start();
						
				}
				logintoHx();
			}
		});
	}
	
	public void logintoHx(){
		// 调用sdk登陆方法登陆聊天服务器
				EMChatManager.getInstance().login(edtUsername.getText().toString(), edtPassword.getText().toString(), new EMCallBack() {

					@Override
					public void onSuccess() {
						removeProgressDialog();
//						if (!progressShow) {
//							return;
//						}
						// 登陆成功，保存用户名密码
//						DemoApplication.getInstance().setUserName(currentUsername);
//						DemoApplication.getInstance().setPassword(currentPassword);

						try {
							// ** 第一次登录或者之前logout后再登录，加载所有本地群和回话
							// ** manually load all local groups and
						    EMGroupManager.getInstance().loadAllGroups();
							EMChatManager.getInstance().loadAllConversations();
							// 处理好友和群组
							initializeContacts();
						} catch (Exception e) {
							e.printStackTrace();
							// 取好友或者群聊失败，不让进入主页面
							runOnUiThread(new Runnable() {
								public void run() {
//									pd.dismiss();
									DemoHXSDKHelper.getInstance().logout(true,null);
									Toast.makeText(getApplicationContext(), R.string.login_failure_failed, 1).show();
								}
							});
							return;
						}
						// 更新当前用户的nickname 此方法的作用是在ios离线推送时能够显示用户nick
//						boolean updatenick = EMChatManager.getInstance().updateCurrentUserNick(
//								DemoApplication.currentUserNick.trim());
//						if (!updatenick) {
//							Log.e("LoginActivity", "update current user nick fail");
//						}
//						if (!UserLoginActivity.this.isFinishing() && pd.isShowing()) {
//							pd.dismiss();
//						}
						// 进入主页面
						Navigator.NavigatorToMainActivity(UserLoginActivity.this);
						
						finish();
					}

					@Override
					public void onProgress(int progress, String status) {
					}

					@Override
					public void onError(final int code, final String message) {
						removeProgressDialog();
//						if (!progressShow) {
//							return;
//						}
						runOnUiThread(new Runnable() {
							public void run() {
								Toast.makeText(getApplicationContext(), getString(R.string.Login_failed) + message,
										Toast.LENGTH_SHORT).show();
							}
						});
					}
				});
	}
	
	private void initializeContacts() {
		Map<String, User> userlist = new HashMap<String, User>();
		// 添加user"申请与通知"
		User newFriends = new User();
		newFriends.setUsername(Constant.NEW_FRIENDS_USERNAME);
		String strChat = getResources().getString(
				R.string.Application_and_notify);
		newFriends.setNick(strChat);

		userlist.put(Constant.NEW_FRIENDS_USERNAME, newFriends);
		// 添加"群聊"
		User groupUser = new User();
		String strGroup = getResources().getString(R.string.group_chat);
		groupUser.setUsername(Constant.GROUP_USERNAME);
		groupUser.setNick(strGroup);
		groupUser.setHeader("");
		userlist.put(Constant.GROUP_USERNAME, groupUser);
		
		// 添加"Robot"
		User robotUser = new User();
		String strRobot = getResources().getString(R.string.robot_chat);
		robotUser.setUsername(Constant.CHAT_ROBOT);
		robotUser.setNick(strRobot);
		robotUser.setHeader("");
		userlist.put(Constant.CHAT_ROBOT, robotUser);
		
		// 存入内存
		((DemoHXSDKHelper)HXSDKHelper.getInstance()).setContactList(userlist);
		// 存入db
		UserDao dao = new UserDao(UserLoginActivity.this);
		List<User> users = new ArrayList<User>(userlist.values());
		dao.saveContactList(users);
	}

	public static Intent getCallingIntent(Context context) {
		return new Intent(context, UserLoginActivity.class);
	}
}
