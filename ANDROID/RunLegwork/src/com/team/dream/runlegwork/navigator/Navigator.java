package com.team.dream.runlegwork.navigator;

import android.content.Context;
import android.content.Intent;

import com.team.dream.runlegwork.activity.MainActivity;
import com.team.dream.runlegwork.activity.UserLoginActivity;
import com.team.dream.runlegwork.activity.UserRegisterActivity;
import com.team.dream.runlegwork.activity.WebViewActivity;
import com.team.dream.runlegwork.activity.account.AccountProfileActivity;

public class Navigator {

	public static void NavigatorToLogin(Context context) {
		Intent intent = UserLoginActivity.getCallingIntent(context);
		context.startActivity(intent);
	}

	public static void NavigatorToReigister(Context context) {
		Intent intent = UserRegisterActivity.getCallingIntent(context);
		context.startActivity(intent);
	}

	public static void NavigatorToUserDetail(Context context) {
		Intent intent = AccountProfileActivity.getCallingIntent(context);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		context.startActivity(intent);
	}

	public static void NavigatorToMainActivity(Context context) {
		Intent intent = MainActivity.getCallingIntent(context);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		context.startActivity(intent);
	}
	public static void NavigatorToWebViewActivity(Context context) {
		Intent intent = WebViewActivity.getCallingIntent(context);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		context.startActivity(intent);
	}

}
