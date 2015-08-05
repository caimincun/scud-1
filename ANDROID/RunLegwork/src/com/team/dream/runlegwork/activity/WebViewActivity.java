package com.team.dream.runlegwork.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.widget.TopBar;

@SuppressLint("SetJavaScriptEnabled")
public class WebViewActivity extends BaseActivity {

	@InjectView(R.id.topbar)
	TopBar topBar;
	@InjectView(R.id.web_view)
	WebView wvShowWap;

	@Override
	protected void onCreate(Bundle onSaveInstanceState) {
		super.onCreate(onSaveInstanceState);
		setContentView(R.layout.activity_webview);

		ButterKnife.inject(this);

		WebSettings settings = wvShowWap.getSettings();

		settings.setJavaScriptEnabled(true);
		settings.setJavaScriptCanOpenWindowsAutomatically(true);
		String url = "http://www.baidu.com";
//		new WapCookieSyncStore(this).syncCookies();
		wvShowWap.loadUrl(url);
		wvShowWap.setWebViewClient(wvClient);
	}
	

	private WebViewClient wvClient = new WebViewClient() {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	};

	public static Intent getCallingIntent(Context context) {
		return new Intent(context, WebViewActivity.class);
	}

}
