package com.team.dream.runlegwork.singleservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.baidu.mapapi.SDKInitializer;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.utils.ToastUtils;

public class BaiDuApiHandler {

	private SDKReceiver mReceiver;
	private  static BaiDuApiHandler apiHandler;

	private BaiDuApiHandler(Context context) {
		// 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
		SDKInitializer.initialize(context);
		// 注册 SDK 广播监听者
		IntentFilter iFilter = new IntentFilter();
		iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
		iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
		mReceiver = new SDKReceiver();
		context.registerReceiver(mReceiver, iFilter);
	}

	public static void initBaiDuSdk(Context context) {
		if (apiHandler == null) {
			apiHandler = new BaiDuApiHandler(context);
		}
	}

	/**
	 * 构造广播监听类，监听 SDK key 验证以及网络异常广播
	 */
	public class SDKReceiver extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {
			String s = intent.getAction();
			if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
				ToastUtils.show(context, R.string.bai_du_key_error);
			} else if (s.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {
				ToastUtils.show(context, R.string.netword_is_unable);
			}
		}
	}

}
