package com.team.dream.runlegwork.adapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.entity.Product;
import com.team.dream.runlegwork.interfaces.OnShopPopChangedListener;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PopuWindowPayAdapter extends BaseAdapter {
	private Context ctx;
	private List<Product> listdata = new ArrayList<Product>();
	private LayoutInflater inflater;
	
	private OnShopPopChangedListener shopPopChangedListener;
	
	
	/**
	 * @return the shopPopChangedListener
	 */
	public OnShopPopChangedListener getShopPopChangedListener() {
		return shopPopChangedListener;
	}

	/**
	 * @param shopPopChangedListener the shopPopChangedListener to set
	 */
	public void setShopPopChangedListener(
			OnShopPopChangedListener shopPopChangedListener) {
		this.shopPopChangedListener = shopPopChangedListener;
	}

	public PopuWindowPayAdapter(Context ctx,List<Product> list) {
		this.ctx = ctx;
		this.listdata = list;
		this.inflater = LayoutInflater.from(ctx);
	}
	
	public void setList(List<Product> list){
		this.listdata = list;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return listdata.size();
	}

	@Override
	public Object getItem(int arg0) {
		return listdata.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(final int position, View converView, ViewGroup arg2) {
		ViewHolder holder = null;
		if(converView == null){
			converView = inflater.inflate(R.layout.listview_item_popwindow_pay, null);
			holder = new ViewHolder(converView);
			
			converView.setTag(holder);
		}
		else{
			holder = (ViewHolder) converView.getTag();
		}
		final ViewHolder holder1 = holder;
		holder.ivAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Product product = (Product) getItem(position);
				int count = Integer.parseInt(holder1.tvCount.getText().toString());
				if(count<product.getSurplusNum()){
				holder1.tvCount.setText(count+1+"");
				holder1.tvPrice.setText((count+1)*product.getProductMoney()+"");
				listdata.get(position).setCount(count+1);
				shopPopChangedListener.changed(product.getProductToken(),count+1);
				}
			}
		});
		
		holder.ivJian.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Product product = (Product) getItem(position);
				int count = Integer.parseInt(holder1.tvCount.getText().toString());
				if(count>0){
					holder1.tvCount.setText(count-1+"");
					holder1.tvPrice.setText((count-1)*product.getProductMoney()+"");
					listdata.get(position).setCount(count-1);
					shopPopChangedListener.changed(product.getProductToken(),count+1);
				}
			}
		});
		
		Product product = (Product) getItem(position);
		holder.tvName.setText(product.getPrductName());
		holder.tvCount.setText(product.getCount()+"");
		holder.tvPrice.setText(product.getCount()*product.getProductMoney()+"");
		
		return converView;
	}
	
	static class ViewHolder{
		@InjectView(R.id.item_poppay_tvName)
		TextView tvName;
		@InjectView(R.id.item_poppay_ivAdd)
		ImageView ivAdd;
		@InjectView(R.id.item_poppay_ivJian)
		ImageView ivJian;
		@InjectView(R.id.item_poppay_tvCount)
		TextView tvCount;
		@InjectView(R.id.item_poppay_tvPrice)
		TextView tvPrice;
		
		
		public ViewHolder(View view) {
			ButterKnife.inject(this,view);
		}
		
	}

}
