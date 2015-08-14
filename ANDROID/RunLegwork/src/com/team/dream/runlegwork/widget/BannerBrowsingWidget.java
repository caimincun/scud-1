package com.team.dream.runlegwork.widget;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.runlegwork.R;

public class BannerBrowsingWidget extends RelativeLayout implements OnPageChangeListener {
	private int[] imgs = new int[] { R.drawable.maintab1, R.drawable.maintab2, R.drawable.maintab3 };
	@InjectView(R.id.vp_banner_browsing)
	ViewPager vpShowPager;
	@InjectView(R.id.vg_index)
	ViewGroup vgIndex;
	private SparseArray<View> pageViews;
	private ImageView[] imageViews;
	private GuidePageAdapter adapter;
	private ImageView imageView;
	private int[] imageRs = { R.drawable.home_banner_switch_fu, R.drawable.home_banner_switch_em };

	public BannerBrowsingWidget(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.widget_top_banner_browsing, this);
		ButterKnife.inject(this);
	}

	public void initView(SparseArray<View> sparseArrayViewData) {
		pageViews = sparseArrayViewData;
		if (null==pageViews||pageViews.size() == 0) {
			pageViews = initDefalutad();
		}
		adapter = new GuidePageAdapter();
		imageViews = new ImageView[pageViews.size()];

		vpShowPager.setAdapter(adapter);

		if (!(pageViews.size() <= 1)) {
			vpShowPager.setOnPageChangeListener(this);
			initIndexView(vgIndex, imageRs);
			startLockWindowTimer();
		}

	}

	private SparseArray<View> initDefalutad() {
		SparseArray<View> pageViews = new SparseArray<View>();
		for (int i = 0; i < imgs.length; i++) {
			ImageView mImageView = new ImageView(getContext());
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
			mImageView.setScaleType(ScaleType.CENTER_CROP);
			mImageView.setLayoutParams(lp);
			mImageView.setImageResource(imgs[i]);
			pageViews.put(i, mImageView);
		}
		return pageViews;
	}

	private void initIndexView(ViewGroup viewGroup, int[] imageRs) {

		for (int i = 0; i < imageViews.length; i++) {
			LinearLayout.LayoutParams margin = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			// 设置每个小圆点距离左边的间距
			margin.setMargins(10, 0, 0, 0);
			imageView = new ImageView(getContext());
			// 设置每个小圆点的宽高
			imageViews[i] = imageView;
			if (i == 0) {
				// 默认选中第一张图片
				imageViews[i].setBackgroundResource(imageRs[0]);
			} else {
				// 其他图片都设置未选中状态
				imageViews[i].setBackgroundResource(imageRs[1]);
			}
			viewGroup.addView(imageViews[i], margin);

		}

	}

	private void startLockWindowTimer() {
		if (timer != null) {
			if (task != null) {
				task.cancel(); // 将原任务从队列中移除
			}
			task = new AdTimerTask();
			timer.schedule(task, 1000, 5000);
		}
	}

	private int adImg = 0;

	private Timer timer = new Timer();
	private AdTimerTask task = null;

	private class AdTimerTask extends TimerTask {

		@Override
		public void run() {
			new Handler(getContext().getMainLooper()).post(new Runnable() {

				@Override
				public void run() {
					adImg++;
					if (adImg == pageViews.size()) {
						adImg = 0;
					}
					vpShowPager.setCurrentItem(adImg);
				}
			});
		}
	};

	public BannerBrowsingWidget(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public BannerBrowsingWidget(Context context) {
		this(context, null);
	}

	private class GuidePageAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return pageViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(pageViews.get(arg1));
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {

			((ViewPager) arg0).addView(pageViews.get(arg1));
			return pageViews.get(arg1);
		}

	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		if (task != null) {
			task.cancel();
			task = null;
		}
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		if (task == null) {
			startLockWindowTimer();
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onPageSelected(int arg0) {
		if (adImg != arg0) {
			adImg = arg0;
		}
		// 遍历数组让当前选中图片下的小圆点设置颜色
		for (int i = 0; i < imageViews.length; i++) {
			imageViews[arg0].setBackgroundResource(imageRs[0]);

			if (arg0 != i) {
				imageViews[i].setBackgroundResource(imageRs[1]);
			}
		}

	}
}
