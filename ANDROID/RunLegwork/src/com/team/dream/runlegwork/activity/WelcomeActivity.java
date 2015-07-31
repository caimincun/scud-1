package com.team.dream.runlegwork.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.loopj.android.http.PersistentCookieStore;
import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.DataApplication;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.ZoomOutPageTransformer;
import com.team.dream.runlegwork.jpush.Constant;
import com.team.dream.runlegwork.jpush.ExampleUtil;
import com.team.dream.runlegwork.navigator.Navigator;
import com.team.dream.runlegwork.net.JsonBooleanResponseHandler;
import com.team.dream.runlegwork.singleservice.Syseting;
import com.team.dream.runlegwork.utils.ToastUtils;

public class WelcomeActivity extends BaseActivity implements OnPageChangeListener {

	private int[] imgs = { R.drawable.guide_image1, R.drawable.guide_image2, R.drawable.guide_image3 };

	private List<View> imgViews = new ArrayList<View>();
	private int[] imgTips = { R.drawable.smallbule, R.drawable.middlebule };
	private ImageView[] mTips;
	private ImageView imageView;
	private Syseting mSysSetting;
	@InjectView(R.id.view_pager)
	ViewPager viewPager;
	@InjectView(R.id.iv_flash)
	ImageView ivFlash;
	@InjectView(R.id.ll_position_to)
	ViewGroup llPostionTo;
	
	private MessageReceiver mMessageReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.activity_welcome);

		ButterKnife.inject(this);

		mSysSetting = new Syseting(WelcomeActivity.this);
		boolean isFirstUse = mSysSetting.isFirstUse();
		if (isFirstUse) {
			ivFlash.setVisibility(View.GONE);
			viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
			viewPager.setAdapter(new MyPageadapter());
			viewPager.setOnPageChangeListener(this);
			mTips = new ImageView[imgs.length];
			showTips();
		} else {
			viewPager.setVisibility(View.GONE);
			CheceForUserMessage();
		}
		registerMessageReceiver();
	}

	protected void CheceForUserMessage() {
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				final PersistentCookieStore cookieStore = DataApplication.getInstance().getPersistentCookieStore();
				if (null == cookieStore.getCookies() || cookieStore.getCookies().isEmpty() || cookieStore.getCookies().size() == 0) {
					Navigator.NavigatorToLogin(WelcomeActivity.this);
				} else {
					api.checkUserState(new JsonBooleanResponseHandler() {

						@Override
						public void onSuccess() {
							Navigator.NavigatorToMainActivity(WelcomeActivity.this);

						}

						@Override
						public void onFailure(String errMsg) {
							ToastUtils.show(WelcomeActivity.this, errMsg);
							cookieStore.clear();
							Navigator.NavigatorToLogin(WelcomeActivity.this);
						}
					});

				}
			}
		}, 1000);
	}

	private void showTips() {
		for (int i = 0; i < mTips.length; i++) {
			imageView = new ImageView(this);
			imageView.setScaleType(ScaleType.CENTER_CROP);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			lp.setMargins(15, 0, 0, 0);
			mTips[i] = imageView;

			if (i == 0) {
				mTips[i].setImageResource(imgTips[1]);
			} else {
				mTips[i].setImageResource(imgTips[0]);
			}

			llPostionTo.addView(mTips[i], lp);
		}
	}

	private class MyPageadapter extends PagerAdapter implements OnClickListener {

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView imageView = null;
			View view = null;
			if (position == (imgs.length - 1)) {
				view = LayoutInflater.from(WelcomeActivity.this).inflate(R.layout.loading_last_page, null);
				ImageView tempImgView = (ImageView) view.findViewById(R.id.iv_flash_last_page);
				tempImgView.setImageResource(imgs[position]);
				TextView tempTextView = (TextView) view.findViewById(R.id.tv_taste);
				tempTextView.setOnClickListener(this);
				container.addView(view);
				imgViews.add(view);
			} else {
				imageView = new ImageView(WelcomeActivity.this);
				imageView.setImageResource(imgs[position]);
				imageView.setScaleType(ScaleType.CENTER_CROP);
				container.addView(imageView);
				imgViews.add(imageView);
			}
			if (imageView != null) {
				return imageView;
			}
			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(imgViews.get(position));
		}

		@Override
		public int getCount() {
			return imgs.length;
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view == obj;
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.tv_taste:
				mSysSetting.writeSetting(false);
				PersistentCookieStore cookieStore = DataApplication.getInstance().getPersistentCookieStore();
				if (null == cookieStore.getCookies() || cookieStore.getCookies().isEmpty() || cookieStore.getCookies().size() == 0) {
					Navigator.NavigatorToLogin(WelcomeActivity.this);
				} else {
					Navigator.NavigatorToMainActivity(WelcomeActivity.this);
				}

				break;
			}
		}

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int postion) {

		// 遍历数组让当前选中图片下的小圆点设置颜色
		for (int i = 0; i < mTips.length; i++) {
			mTips[postion].setImageResource(imgTips[1]);

			if (postion != i) {
				mTips[i].setImageResource(imgTips[0]);
			}
		}

	}
	
	public void registerMessageReceiver() {
		mMessageReceiver = new MessageReceiver();
		IntentFilter filter = new IntentFilter();
		filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
		filter.addAction(Constant.MESSAGE_RECEIVED_ACTION);
		registerReceiver(mMessageReceiver, filter);
	}
	
	public class MessageReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (Constant.MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
              String messge = intent.getStringExtra(Constant.KEY_MESSAGE);
              String extras = intent.getStringExtra(Constant.KEY_EXTRAS);
              StringBuilder showMsg = new StringBuilder();
              showMsg.append(Constant.KEY_MESSAGE + " : " + messge + "\n");
              if (!ExampleUtil.isEmpty(extras)) {
            	  showMsg.append(Constant.KEY_EXTRAS + " : " + extras + "\n");
              }
			}
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mMessageReceiver);
	}

}
