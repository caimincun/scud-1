package com.team.dream.runlegwork.adapter;

import java.util.List;

import javax.crypto.spec.IvParameterSpec;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.runlegwork.DataApplication;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.entity.Address;
import com.team.dream.runlegwork.net.JsonBooleanResponseHandler;
import com.team.dream.runlegwork.tool.Tool;

import android.view.LayoutInflater;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAddressAdapter extends BaseAdapter{
	private Context ctx;
	private LayoutInflater inflater;
	private List<Address> list;
	
	public MyAddressAdapter(Context ctx,List<Address> list) {
		super();
		this.ctx = ctx;
		this.inflater = LayoutInflater.from(ctx);
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.listview_item_myaddress, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		if(list.get(position).getFlag()==1){
			holder.cb.setChecked(true);
		}
		else{
			holder.cb.setChecked(false);
		}
		
		
		holder.tvAddress.setText(list.get(position).getReceiptAddress()+"");
		holder.ivDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				int id = list.get(position).getId();
				DataApplication.getInstance().getReQuestApi().deleteAddress(id, new JsonBooleanResponseHandler() {
					
					@Override
					public void onSuccess() {
						Tool.showToast(ctx, "地址删除成功");
						list.clear();
						notifyDataSetChanged();
					}
					
					@Override
					public void onFailure(String errMsg) {
						Tool.showToast(ctx, errMsg);
					}
				});
			}
		});
		return convertView;
	}
	
	static class ViewHolder{
		@InjectView(R.id.item_myaddress_cb)
		CheckBox cb;
		@InjectView(R.id.item_myaddress_tvAddress)
		TextView tvAddress;
		@InjectView(R.id.item_myaddress_ivDelete)
		ImageView ivDelete;
		public ViewHolder(View view){
			ButterKnife.inject(this, view);
		}
	}

}
