package com.team.dream.runlegwork.navigator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.team.dream.runlegwork.activity.ChoiceNeedActivity;
import com.team.dream.runlegwork.activity.CreateOrderActivity;
import com.team.dream.runlegwork.activity.CreateProductActivity;
import com.team.dream.runlegwork.activity.MainActivity;
import com.team.dream.runlegwork.activity.OpenShopActivity;
import com.team.dream.runlegwork.activity.OrderDetailActivity;
import com.team.dream.runlegwork.activity.PushOrderActivity;
import com.team.dream.runlegwork.activity.PushSkillActivity;
import com.team.dream.runlegwork.activity.SelectOrderOrSkillActvity;
import com.team.dream.runlegwork.activity.SellSkillActivity;
import com.team.dream.runlegwork.activity.ShopDetialActivity;
import com.team.dream.runlegwork.activity.ShopGoodsManagerActivity;
import com.team.dream.runlegwork.activity.UpdateShopActivity;
import com.team.dream.runlegwork.activity.UserLoginActivity;
import com.team.dream.runlegwork.activity.UserRegisterActivity;
import com.team.dream.runlegwork.activity.WebViewActivity;
import com.team.dream.runlegwork.activity.account.AccountProfileActivity;
import com.team.dream.runlegwork.entity.Store;
import com.team.dream.runlegwork.entity.UserOrder;
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

	public static void NavigatorToMainActivity(Context context, int postion) {
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
			String selectNeed) {
		Intent intent = CreateOrderActivity.getCallingIntent(context);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		intent.putExtra(CreateOrderActivity.SELET_NEED, selectNeed);
		context.startActivity(intent);
	}

	public static void NavigatorToCreateSkillActivity(Context context,
			String selectNeed) {
		Intent intent = SellSkillActivity.getCallingIntent(context);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		intent.putExtra(SellSkillActivity.SELET_NEED, selectNeed);
		context.startActivity(intent);
	}

	public static void NavigatorToChoiceNeedActivity(Context context,
			boolean isOrder) {
		Intent intent = ChoiceNeedActivity.getCallingIntent(context);
		intent.putExtra(ChoiceNeedActivity.ORDER_SKILL, isOrder);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		context.startActivity(intent);
	}

	public static void NavigatorToSellSkillActivity(Context context) {
		Intent intent = SellSkillActivity.getCallingIntent(context);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		context.startActivity(intent);
	}

	public static void NavigatorToOrderDetailActivity(Context context,
			UserOrder order) {
		Intent intent = OrderDetailActivity.getCallingIntent(context);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		intent.putExtra(OrderDetailActivity.ORDER_DATA, order);
		context.startActivity(intent);
	}

	public static void NavigatorToPushOrderActivity(Context context) {
		Intent intent = PushOrderActivity.getCallingIntent(context);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		context.startActivity(intent);
	}

	public static void NavigatorToPushSkillActivity(Context context) {
		Intent intent = PushSkillActivity.getCallingIntent(context);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		context.startActivity(intent);
	}

	public static void NavigatorToSelectOrderOrSkillActivity(Activity context) {
		Intent intent = SelectOrderOrSkillActvity.getCallingIntent(context);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		context.startActivityForResult(intent,
				SelectOrderOrSkillActvity.REQUEST_TYPE);
	}

	public static void NavigatorToOpenShopActivity(Activity context) {
		Intent intent = OpenShopActivity.getCallingIntent(context);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		context.startActivityForResult(intent,
				SelectOrderOrSkillActvity.REQUEST_TYPE);
	}

	public static void NavigatorToShopDetailActivity(Activity context) {
		Intent intent = ShopDetialActivity.getCallingIntent(context);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		context.startActivityForResult(intent,
				SelectOrderOrSkillActvity.REQUEST_TYPE);
	}

	public static void NavigatorToShopSettingActivity(Activity context,
			Store mStore) {
		Intent intent = UpdateShopActivity.getCallingIntent(context);
		intent.putExtra(UpdateShopActivity.STORE_KEY, mStore);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		context.startActivityForResult(intent,
				SelectOrderOrSkillActvity.REQUEST_TYPE);
	}

	public static void NavigatorToShopGoodsManagerActivity(Activity context) {
		Intent intent = ShopGoodsManagerActivity.getCallingIntent(context);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		context.startActivity(intent);
	}

	public static void NavigatorToCreateProductActivity(Activity context) {
		Intent intent = CreateProductActivity.getCallingIntent(context);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		context.startActivity(intent);
	}
}
