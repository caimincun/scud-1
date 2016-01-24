package com.team.dream.runlegwork.adapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.entity.Product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ShopGoodsAdapter extends BaseAdapter {

	private List<Product> mData;
	private Context mContext;

	public ShopGoodsAdapter(Context context, List<Product> mData) {
		this.mContext = context;
		this.mData = mData;
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Product product = mData.get(position);
		ViewHoler holer;
		if (null == convertView) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.adapter_shop_goods, parent, false);
			holer = new ViewHoler(convertView);
			convertView.setTag(holer);
		} else {
			holer = (ViewHoler) convertView.getTag();
		}
		holer.tvTitle.setText(product.getPrductName());
		holer.tvPrice.setText("￥" + product.getProductMoney());
		holer.tvTNum.setText("库存:" + product.getSurplusNum());
		holer.tvDetail.setText(product.getDescritpion());
		return convertView;
	}

	public class ViewHoler {
		@InjectView(R.id.tv_title)
		TextView tvTitle;
		@InjectView(R.id.tv_detail)
		TextView tvDetail;
		@InjectView(R.id.tv_price)
		TextView tvPrice;
		@InjectView(R.id.tv_num)
		TextView tvTNum;
		@InjectView(R.id.iv_shop_img)
		ImageView ivShopImg;

		public ViewHoler(View view) {
			ButterKnife.inject(this, view);
		}
	}

}
