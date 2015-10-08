package com.team.dream.runlegwork.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.SingletonServiceManager;
import com.team.dream.runlegwork.entity.Store;
import com.team.dream.runlegwork.navigator.Navigator;
import com.team.dream.runlegwork.net.JsonObjectResponseHandler;
import com.team.dream.runlegwork.net.response.StoreResponse;
import com.team.dream.runlegwork.utils.PathUtil;
import com.team.dream.runlegwork.view.RoundImageView;
import com.team.dream.runlegwork.widget.MainTitileBar;

public class ShopDetialActivity extends BaseActivity {

	@InjectView(R.id.mt_bar)
	MainTitileBar mtBar;
	@InjectView(R.id.iv_shop_icon)
	RoundImageView ivShopIcon;
	@InjectView(R.id.tv_shop_name)
	TextView tvShopName;
	@InjectView(R.id.tv_shop_detail)
	TextView tvShopDetail;
	@InjectView(R.id.gv_func)
	GridView gvFunc;
	private ShopFucAdapter mAdapter;
	// TODO:其他功能实现

	private String[] mData;
	private int[] resId = { R.drawable.shop_fuc_order, R.drawable.shop_fuc_bao,
			R.drawable.shop_fuc_push, R.drawable.shop_fuc_setting };

	private static Store mStore;

	@Override
	protected void onCreate(Bundle onSaveInstate) {
		super.onCreate(onSaveInstate);
		setContentView(R.layout.activity_shop_detail);
		ButterKnife.inject(this);
		mtBar.setTitle("我的店铺");
		initData();
		mData = getResources().getStringArray(R.array.shop_fuc);
		mAdapter = new ShopFucAdapter();
		gvFunc.setAdapter(mAdapter);
	}

	private void initData() {
		api.getStore(new JsonObjectResponseHandler<StoreResponse>() {

			@Override
			public void onSuccess(StoreResponse response) {
				mStore = response.getData();
				fillData(response);
			}

			@Override
			public void onFailure(String errMsg) {

			}
		});

	}

	private void fillData(StoreResponse response) {
		tvShopName.setText(response.getData().getStoreName());
		tvShopDetail.setText(response.getData().getSlogan());
		SingletonServiceManager.getInstance().display(
				PathUtil.getShopStorePicUrl(response.getData()
						.getStorePicture()), ivShopIcon,
				R.drawable.shop_df_pic, null);
	}

	@OnItemClick(R.id.gv_func)
	public void onSelctItem(int postion) {
		// TODO:具体设置
		switch (postion) {
		case 0:

			break;
		case 1:
			Navigator.NavigatorToShopGoodsManagerActivity(this);
			break;
		case 2:

			break;
		case 3:
			Navigator.NavigatorToShopSettingActivity(this, mStore);
			break;

		}
	}

	public class ShopFucAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mData.length;
		}

		@Override
		public Object getItem(int position) {
			return mData[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHoler holer = null;
			if (null == convertView) {
				convertView = LayoutInflater.from(ShopDetialActivity.this)
						.inflate(R.layout.adapter_shop_fuc, parent, false);
				holer = new ViewHoler(convertView);

				convertView.setTag(holer);
			} else {
				holer = (ViewHoler) convertView.getTag();
			}
			holer.ivFucImg.setImageResource(resId[position]);
			holer.tvFucName.setText(mData[position]);
			return convertView;
		}

		public class ViewHoler {
			@InjectView(R.id.tv_fuc_name)
			TextView tvFucName;
			@InjectView(R.id.iv_shop_fuc)
			ImageView ivFucImg;

			public ViewHoler(View view) {
				ButterKnife.inject(this, view);
			}
		}

	}

	public static Intent getCallingIntent(Context context) {
		return new Intent(context, ShopDetialActivity.class);
	}

}
