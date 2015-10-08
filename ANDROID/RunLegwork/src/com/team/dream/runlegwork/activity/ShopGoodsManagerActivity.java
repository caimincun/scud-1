package com.team.dream.runlegwork.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.widget.MainTitileBar;

public class ShopGoodsManagerActivity extends BaseActivity {

	@InjectView(R.id.mt_bar)
	MainTitileBar mtTitle;
	@InjectView(R.id.rg_goods_type)
	RadioGroup rgGoodsTypes;

	@Override
	protected void onCreate(Bundle onSaveInstate) {
		super.onCreate(onSaveInstate);
		setContentView(R.layout.activity_goods_manager);
		ButterKnife.inject(this);

		mtTitle.hideTitleRight();
		mtTitle.setTitle("宝贝管理");
		rgGoodsTypes.check(R.id.rb_goods_type_one);
	}

	public static Intent getCallingIntent(Context context) {
		return new Intent(context, ShopGoodsManagerActivity.class);
	}

}
