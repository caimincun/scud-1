package com.team.dream.runlegwork.navigator;

import android.content.Context;
import android.content.Intent;

import com.team.dream.runlegwork.activity.ChoiceNeedActivity;
import com.team.dream.runlegwork.activity.CreateOrderActivity;
import com.team.dream.runlegwork.activity.MainActivity;
import com.team.dream.runlegwork.activity.SellSkillActivity;
import com.team.dream.runlegwork.activity.UserLoginActivity;
import com.team.dream.runlegwork.activity.UserRegisterActivity;
import com.team.dream.runlegwork.activity.WebViewActivity;
import com.team.dream.runlegwork.activity.account.AccountProfileActivity;
//import com.team.dream.runlegwork.activity.ChoiceNeedActivity;
//import com.team.dream.runlegwork.activity.SellSkillActivity;

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
		context.startActivity(intent);
	}
	public static void NavigatorToMainActivity(Context context,int postion) {
		Intent intent = MainActivity.getCallingIntent(context);
		intent.putExtra(MainActivity.KEY_POSTION, postion);
		context.startActivity(intent);
	}


	public static void NavigatorToWebViewActivity(Context context) {
		Intent intent = WebViewActivity.getCallingIntent(context);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		context.startActivity(intent);
	}

	public static void NavigatorToCreateOrderActivity(Context context,
			String selectNeed,int postion) {
		Intent intent = CreateOrderActivity.getCallingIntent(context);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		intent.putExtra(CreateOrderActivity.SELET_NEED, selectNeed);
		intent.putExtra(CreateOrderActivity.SELET_NEED_POSTION, postion);
		context.startActivity(intent);
	}
	public static void NavigatorToCreateSkillActivity(Context context,
			String selectNeed,int postion) {
		Intent intent = SellSkillActivity.getCallingIntent(context);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		intent.putExtra(SellSkillActivity.SELET_NEED, selectNeed);
		intent.putExtra(SellSkillActivity.SELET_NEED_POSTION, postion);
		context.startActivity(intent);
	}

	public static void NavigatorToChoiceNeedActivity(Context context,boolean isOrder) {
		Intent intent = ChoiceNeedActivity.getCallingIntent(context);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		intent.putExtra(ChoiceNeedActivity.ORDER_SKILL, isOrder);
		context.startActivity(intent);
	}

	public static void NavigatorToSellSkillActivity(Context context) {
		Intent intent = SellSkillActivity.getCallingIntent(context);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		context.startActivity(intent);
	}

}