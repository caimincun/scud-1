package com.team.dream.runlegwork;

import org.litepal.LitePalApplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.loopj.android.http.PersistentCookieStore;
import com.team.dream.runlegwork.entity.UserInfo;
import com.team.dream.runlegwork.utils.ToastUtils;

public class DataApplication extends LitePalApplication {
	public static UserInfo mAccount;

	private Context mContext;
	private static PersistentCookieStore mPersistentCookieStore;
	private static DataApplication mApplication;

	@Override
	public void onCreate() {
		super.onCreate();
		mContext = this;
		SingletonServiceManager.newInstance(this);
		// 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
		SDKInitializer.initialize(this);

		// 注册 SDK 广播监听者
		IntentFilter iFilter = new IntentFilter();
		iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
		iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
		mReceiver = new SDKReceiver();
		registerReceiver(mReceiver, iFilter);
		mPersistentCookieStore = new PersistentCookieStore(mContext);
	}

	public static DataApplication getInstance() {
		if (mApplication == null) {
			mApplication = new DataApplication();
		}
		return mApplication;
	}

	public PersistentCookieStore getPersistentCookieStore() {
		if (mPersistentCookieStore == null) {
			mPersistentCookieStore = new PersistentCookieStore(mContext);
		}
		return mPersistentCookieStore;
	}

	/**
	 * 构造广播监听类，监听 SDK key 验证以及网络异常广播
	 */
	public class SDKReceiver extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {
			String s = intent.getAction();
			Log.d("TAG", "action: " + s);
			if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
				ToastUtils.show(getApplicationContext(), "key 验证出错! 请在 AndroidManifest.xml 文件中检查 key 设置");
			} else if (s.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {
				ToastUtils.show(getApplicationContext(), "网络出错");
			}
		}
	}

	private SDKReceiver mReceiver;
}
