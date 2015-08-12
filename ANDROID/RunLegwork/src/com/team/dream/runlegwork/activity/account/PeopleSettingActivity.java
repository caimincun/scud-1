package com.team.dream.runlegwork.activity.account;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.activity.UserLoginActivity;
import com.team.dream.runlegwork.singleservice.AccountManager;
import com.team.dream.runlegwork.singleservice.Syseting;
import com.team.dream.runlegwork.utils.ToastUtils;
import com.team.dream.runlegwork.widget.MainTitileBar;

public class PeopleSettingActivity extends BaseActivity {
	private Context ctx;
	@InjectView(R.id.usersetting_rlUserinfo)
	RelativeLayout rlUserInfo;
	@InjectView(R.id.usersetting_rlUpdatepwd)
	RelativeLayout rlUpdatePwd;
	@InjectView(R.id.usersetting_rlAdvice)
	RelativeLayout rlAdvice;
	@InjectView(R.id.usersetting_checkUpdate)
	RelativeLayout rlCheckUpdate;
	@InjectView(R.id.usersetting_loginout)
	LinearLayout llLoginout;
	@InjectView(R.id.activity_setting_topbar)
	MainTitileBar tb;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_usersetting);
		ctx = this;
		ButterKnife.inject(this);
		loadTitle();
	}

	private void loadTitle() {
		tb.setTitle("设置");
		tb.hideTitleRight();
		tb.finishLeft(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(ctx);
	}

	@OnClick(R.id.usersetting_loginout)
	public void loginout() {
		AlertDialog dig = new AlertDialog.Builder(ctx)
				.setTitle("你确定要退出吗？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						AccountManager.getInstance().clearSharep();
						Syseting.exitApp();
						startActivity(new Intent(ctx, UserLoginActivity.class));
						arg0.cancel();
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						arg0.cancel();
					}
				}).create();
		dig.show();
	}

	@OnClick(R.id.usersetting_rlUpdatepwd)
	public void updatePwd() {
		startActivity(new Intent(ctx, UpdatePwdActivity.class));
	}

	@OnClick(R.id.usersetting_checkUpdate)
	public void CheckUpdate() {
		ToastUtils.show(ctx, "当期已经是最新版本");
	}

	@OnClick(R.id.usersetting_rlAdvice)
	public void advice() {
		startActivity(new Intent(ctx, AdviceActivity.class));
	}

	@OnClick(R.id.usersetting_rlUserinfo)
	public void ToUserinfo() {
		startActivity(new Intent(ctx, AccountProfileActivity.class));
	}
}
