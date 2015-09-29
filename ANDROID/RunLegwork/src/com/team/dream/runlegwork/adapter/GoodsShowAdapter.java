package com.team.dream.runlegwork.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.runlegwork.MyBaseAdapter;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.entity.Product;
import com.team.dream.runlegwork.interfaces.OnShopBusCountChanged;

public class GoodsShowAdapter extends MyBaseAdapter {
	private List<Product> list = new ArrayList<Product>();
	private Context ctx;
	private LayoutInflater inflater;
	private OnShopBusCountChanged onShopBusCountChanged;
	public GoodsShowAdapter(Context ctx, List<Product> list) {
		this.ctx = ctx;
		this.inflater = LayoutInflater.from(ctx);
		this.list = list;
	}

	public void setList(List<Product> list) {
		this.list = list;
		notifyDataSetChanged();
	}
	
	/**
	 * @return the onShopBusCountChanged
	 */
	public OnShopBusCountChanged getOnShopBusCountChanged() {
		return onShopBusCountChanged;
	}

	/**
	 * @param onShopBusCountChanged the onShopBusCountChanged to set
	 */
	public void setOnShopBusCountChanged(OnShopBusCountChanged onShopBusCountChanged) {
		this.onShopBusCountChanged = onShopBusCountChanged;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(final int position, View converView, ViewGroup arg2) {
		ViewHolder holder = null;
		Product product = list.get(position);
		if (converView == null) {
			converView = inflater.inflate(R.layout.gridview_item_goodsshow,
					null);
			holder = new ViewHolder(converView);

			converView.setTag(holder);
		} else {
			holder = (ViewHolder) converView.getTag();
		}
		final ViewHolder holder1 = holder;
		holder1.tvNumber.setText(product.getCount() + "");
		holder1.tvName.setText(product.getPrductName()+"");
		holder1.tvPrice.setText(product.getProductMoney()+"");
		holder1.tvNumber.setText(product.getCount()+"");
		// 添加
		holder.ivAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Product product = (Product) getItem(position);
				int goodsNumber = Integer.parseInt(holder1.tvNumber.getText()
						.toString());
				int surplusNum = product.getSurplusNum();
				if (goodsNumber < surplusNum) {
					holder1.tvNumber.setText(goodsNumber + 1 + "");
					list.get(position).setCount(goodsNumber + 1);
					onShopBusCountChanged.shopBusCountChanged();
				}

			}
		});

		holder.ivJian.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				int goodsNumber = Integer.parseInt(holder1.tvNumber.getText()
						.toString());
				if (goodsNumber > 0) {
					holder1.tvNumber.setText(goodsNumber - 1 + "");
					list.get(position).setCount(goodsNumber - 1);
					onShopBusCountChanged.shopBusCountChanged();
				}
			}
		});

		return converView;
	}

	static class ViewHolder {
		@InjectView(R.id.item_goodsshow_tvGoodsContent)
		TextView tvName;
		@InjectView(R.id.item_goodsshow_tvPrice)
		TextView tvPrice;
		@InjectView(R.id.item_goodsshow_tvNumber)
		TextView tvNumber;
		@InjectView(R.id.item_goodsshow_ivAdd)
		ImageView ivAdd;
		@InjectView(R.id.item_goodsshow_ivJian)
		ImageView ivJian;
		@InjectView(R.id.item_goodsshow_ivPic)
		ImageView ivPic;

		public ViewHolder(View view) {
			ButterKnife.inject(this, view);
		}
	}

}
