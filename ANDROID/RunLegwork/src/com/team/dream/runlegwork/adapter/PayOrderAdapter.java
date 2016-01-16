package com.team.dream.runlegwork.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.runlegwork.MyBaseAdapter;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.entity.Address;
import com.team.dream.runlegwork.entity.Product;
import com.team.dream.runlegwork.utils.StringUtils;

public class PayOrderAdapter extends MyBaseAdapter {
	private Context ctx;
	private List<Product> list = new ArrayList<Product>();
	private LayoutInflater inflater;
	private Address address;
	private String remark="";
	public PayOrderAdapter(Context ctx, List<Product> list,Address address,String remark) {
		super();
		this.ctx = ctx;
//		list.add(new Product());
//		list.add(new Product());
//		list.add(new Product());
//		list.add(new Product());
//		this.list.addAll(list);
		this.list = list;
		this.address = address;
		this.remark = remark;
		this.inflater = LayoutInflater.from(ctx);
	}
	public void setAddress(Address address){
		this.address = address;
		notifyDataSetChanged();
	}
	public void setRemark(String remark){
		this.remark = remark;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return list.size()+4;
	}

	@Override
	public Object getItem(int arg0) {
		if(arg0>3){
			return list.get(arg0);
		}
		else{
			return new Product();
		}
		
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View converView, ViewGroup arg2) {
		ViewHolder holder= null;
		if(converView == null){
			converView = inflater.inflate(R.layout.listview_item_payorder, null);
			holder = new ViewHolder(converView);
			
			converView.setTag(holder);
		}
		else{
			holder = (ViewHolder) converView.getTag();
		}
		if(position == 0){
			holder.llAddress.setVisibility(View.VISIBLE);
			holder.llPayway.setVisibility(View.GONE);
			holder.rlTime.setVisibility(View.GONE);
			holder.rlBeizhu.setVisibility(View.GONE);
			holder.rlGoods.setVisibility(View.GONE);
			if(address!=null){
				holder.tvAddress.setText(StringUtils.isEmpty(address.getReceiptAddress())?"":address.getReceiptAddress());
			}
			else{
				holder.tvAddress.setText("请选择收货地址");
			}
		}
		else if(position == 1){
			holder.llAddress.setVisibility(View.GONE);
			holder.llPayway.setVisibility(View.VISIBLE);
			holder.rlTime.setVisibility(View.GONE);
			holder.rlBeizhu.setVisibility(View.GONE);
			holder.rlGoods.setVisibility(View.GONE);
		}
		else if (position == 2) {
			holder.llAddress.setVisibility(View.GONE);
			holder.llPayway.setVisibility(View.GONE);
			holder.rlTime.setVisibility(View.VISIBLE);
			holder.rlBeizhu.setVisibility(View.GONE);
			holder.rlGoods.setVisibility(View.GONE);
		}
		else if (position == 3) {
			holder.llAddress.setVisibility(View.GONE);
			holder.llPayway.setVisibility(View.GONE);
			holder.rlTime.setVisibility(View.GONE);
			holder.rlBeizhu.setVisibility(View.VISIBLE);
			holder.rlGoods.setVisibility(View.GONE);
			holder.tvRemark.setText(remark);
		}
		else{
			holder.llAddress.setVisibility(View.GONE);
			holder.llPayway.setVisibility(View.GONE);
			holder.rlTime.setVisibility(View.GONE);
			holder.rlBeizhu.setVisibility(View.GONE);
			holder.rlGoods.setVisibility(View.VISIBLE);
			
			Product product = list.get(position-4);
			holder.tvGoodsName.setText(product.getPrductName());
			holder.tvGoodsPrice.setText(product.getProductMoney()+"x" +product.getCount());
		}
		
		return converView;
	}
	
	static class ViewHolder{
		public ViewHolder(View view) {
			ButterKnife.inject(this, view);
		}
		@InjectView(R.id.item_payorder_rlAddress)
		RelativeLayout llAddress;
		@InjectView(R.id.item_payorder_llPayway)
		LinearLayout llPayway;
		@InjectView(R.id.item_payorder_rlTime)
		RelativeLayout rlTime;
		@InjectView(R.id.item_payorder_rlBeizhu)
		RelativeLayout rlBeizhu;
		@InjectView(R.id.item_payorder_rlGoods)
		RelativeLayout rlGoods;
		@InjectView(R.id.item_payorder_tvGoodsname)
		TextView tvGoodsName;
		@InjectView(R.id.item_payorder_tvGoodsPrice)
		TextView tvGoodsPrice;
		@InjectView(R.id.item_payorder_tvAddress)
		TextView tvAddress;
		@InjectView(R.id.item_payorder_tvRemark)
		TextView tvRemark;
	}

}
