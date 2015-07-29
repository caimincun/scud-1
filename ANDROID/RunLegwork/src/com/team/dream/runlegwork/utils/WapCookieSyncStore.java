package com.team.dream.runlegwork.utils;

import org.apache.http.cookie.Cookie;

import com.loopj.android.http.PersistentCookieStore;
import com.team.dream.runlegwork.DataApplication;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

public class WapCookieSyncStore {
	private CookieSyncManager mSyncManager;
	private CookieManager mCookieManager;
	private PersistentCookieStore mPersistentCookieStore;

	public WapCookieSyncStore(Context context) {
		mSyncManager = CookieSyncManager.createInstance(context);
		mCookieManager = CookieManager.getInstance();
		mPersistentCookieStore = DataApplication.getInstance().getPersistentCookieStore();
	}

	public void syncCookies() {
		mCookieManager.setAcceptCookie(true);
		mCookieManager.removeSessionCookie();

		for (Cookie cookie : mPersistentCookieStore.getCookies()) {
			String url = cookie.getDomain();
			mCookieManager.setCookie(url, cookie.getDomain() + "=" + cookie.getValue());
		}
		mSyncManager.sync();
	}

}
