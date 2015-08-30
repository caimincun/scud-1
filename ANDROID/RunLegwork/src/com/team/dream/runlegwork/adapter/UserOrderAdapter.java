package com.team.dream.runlegwork.adapter;

import java.util.List;

import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.entity.UserOrder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class UserOrderAdapter extends BaseAdapter {

	private Context context;
	private List<UserOrder> mData;

	public UserOrderAdapter(Context context, List<UserOrder> data) {
		this.context = context;
		this.mData = data;
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int arg0) {
		return mData.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		UserOrder order = mData.get(arg0);
		ViewHoler holer = null;
		if (arg1 == null) {
			holer = new ViewHoler();
			arg1 = LayoutInflater.from(context).inflate(
					R.layout.adapter_user_order, arg2, false);
			holer.tvOrderTitle = (TextView) arg1
					.findViewById(R.id.tv_order_title);
			arg1.setTag(holer);
		} else {
			holer = (ViewHoler) arg1.getTag();
		}
		holer.tvOrderTitle.setText(order.getOrderTitle());

		return arg1;
	}

	class ViewHoler {
		TextView tvOrderTitle;
	}

}
