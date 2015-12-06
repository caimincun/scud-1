package com.team.dream.runlegwork.adapter;

import java.util.List;

import javax.inject.Inject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.runlegwork.MyBaseAdapter;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.SingletonServiceManager;
import com.team.dream.runlegwork.entity.Store;

public class ShopAdapter extends MyBaseAdapter {
	private Context ctx;
	private List<Store> list;
	private LayoutInflater inflater;
	
	public ShopAdapter(Context ctx, List<Store> list) {
		this.ctx = ctx;
		this.list = list;
		this.inflater = LayoutInflater.from(ctx);
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
	public View getView(int position, View currentView, ViewGroup arg2) {
		ViewHolder holder = null;
		if(currentView == null){
			currentView = inflater.inflate(R.layout.item_listv_shop, null);
			holder = new ViewHolder(currentView);
			currentView.setTag(holder);
		}
		else{
			holder = (ViewHolder) currentView.getTag();
		}
		Store store = list.get(position);
		holder.tvAddress.setText(store.getAddress());
		holder.tvContent.setText(store.getSlogan()+"");
		holder.tvName.setText(store.getStoreName());
		holder.tvPhoneandName.setText(store.getStorePhone()+"");
		holder.tvStartPrice.setText("起送价￥"+store.getStartPrice());
		SingletonServiceManager.getInstance().display("http://store-images.bj.bcebos.com"+store.getStorePicture(), holder.ivPic, R.drawable.photo_1, null);
		return currentView;
	}
	
	static class ViewHolder{
		@InjectView(R.id.item_listv_shop_ivPic)
		ImageView ivPic;
		@InjectView(R.id.item_listv_shop_tvShopName)
		TextView tvName;
		@InjectView(R.id.item_listv_shop_tvIntriduce)
		TextView tvContent;
		@InjectView(R.id.item_listv_shop_tvStartPrice)
		TextView tvStartPrice;
		@InjectView(R.id.item_listv_shop_tvTime)
		TextView tvTime;
		@InjectView(R.id.item_listv_shop_tvPhoneandName)
		TextView tvPhoneandName;
		@InjectView(R.id.item_listv_shop_tvAddress)
		TextView tvAddress;
		
		public ViewHolder(View view){
			ButterKnife.inject(this, view);
		}
	}
	

}
