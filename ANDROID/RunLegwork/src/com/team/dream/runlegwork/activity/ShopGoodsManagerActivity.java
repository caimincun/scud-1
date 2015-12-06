package com.team.dream.runlegwork.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.adapter.MainPagerAdapter;
import com.team.dream.runlegwork.fragment.ShopGoodsFragment;
import com.team.dream.runlegwork.navigator.Navigator;
import com.team.dream.runlegwork.widget.MainTitileBar;

public class ShopGoodsManagerActivity extends BaseActivity {

	@InjectView(R.id.mt_bar)
	MainTitileBar mtTitle;
	@InjectView(R.id.rg_goods_type)
	RadioGroup rgGoodsTypes;

	@InjectView(R.id.vp_goods)
	ViewPager vpGoods;
	@InjectView(R.id.tv_push_goods)
	TextView tvPushGoods;
	@InjectView(R.id.ll_push_goods)
	LinearLayout llPushGoods;
	private MainPagerAdapter mAdapter;
	private List<Fragment> mList = new ArrayList<Fragment>();

	private boolean isPush;

	@Override
	protected void onCreate(Bundle onSaveInstate) {
		super.onCreate(onSaveInstate);
		setContentView(R.layout.activity_goods_manager);
		ButterKnife.inject(this);

		mtTitle.hideTitleRight();
		mtTitle.setTitle("宝贝管理");
		rgGoodsTypes.check(R.id.rb_goods_type_one);
		mList.add(ShopGoodsFragment
				.newInstance(ShopGoodsFragment.GOODS_SELL, 0));
		mList.add(ShopGoodsFragment.newInstance(ShopGoodsFragment.GOODS_XIAJIA,
				0));
		mList.add(ShopGoodsFragment
				.newInstance(ShopGoodsFragment.GOODS_TYPE, 0));
		mAdapter = new MainPagerAdapter(getSupportFragmentManager(), mList);
		vpGoods.setAdapter(mAdapter);
		rgGoodsTypes.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				switch (checkedId) {
				case R.id.rb_goods_type_one:
					vpGoods.setCurrentItem(0);
					isPush = false;
					changerSubmit();
					break;
				case R.id.rb_xiajia:
					vpGoods.setCurrentItem(1);
					isPush = false;
					changerSubmit();
					break;
				case R.id.rb_type:
					vpGoods.setCurrentItem(2);
					isPush = true;
					changerSubmit();
					break;
				}

			}
		});

		vpGoods.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				switch (arg0) {
				case 0:
					rgGoodsTypes.check(R.id.rb_goods_type_one);
					isPush = false;
					changerSubmit();
					break;
				case 1:
					isPush = false;
					rgGoodsTypes.check(R.id.rb_xiajia);
					changerSubmit();
					break;
				case 2:
					isPush = true;
					rgGoodsTypes.check(R.id.rb_type);
					changerSubmit();
					break;

				}

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}

	public static Intent getCallingIntent(Context context) {
		return new Intent(context, ShopGoodsManagerActivity.class);
	}

	@OnClick(R.id.ll_push_goods)
	public void push() {
		if (!isPush) {
			Navigator.NavigatorToCreateProductActivity(this);
		} else {

		}
	}

	private void changerSubmit() {
		if (!isPush) {
			tvPushGoods.setCompoundDrawables(null, null, null, null);
			tvPushGoods.setText(R.string.push_baobei);
		} else {
			Drawable icon = getResources().getDrawable(R.drawable.push_editor);
			Bitmap bitmap = drawableToBitmap(icon);
			// setIconColor(icon);
			if (icon != null) {
				icon.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
				tvPushGoods.setCompoundDrawables(icon, null, null, null);
			}
			tvPushGoods.setText(R.string.add_type);
		}

	}

	public Bitmap drawableToBitmap(Drawable drawable) {
		if (drawable instanceof BitmapDrawable) {
			BitmapDrawable bd = (BitmapDrawable) drawable;
			return bd.getBitmap();
		}
		int w = drawable.getIntrinsicWidth();
		int h = drawable.getIntrinsicHeight();
		Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, w, h);
		drawable.draw(canvas);
		return bitmap;
	}

}
