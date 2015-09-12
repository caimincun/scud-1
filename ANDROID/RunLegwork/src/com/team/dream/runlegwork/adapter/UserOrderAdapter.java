package com.team.dream.runlegwork.adapter;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.entity.UserOrder;
import com.team.dream.runlegwork.utils.AppUtils;

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
	public View getView(int position, View view, ViewGroup parent) {
		UserOrder order = mData.get(position);
		ViewHoler holer = null;
		if (view == null) {

			view = LayoutInflater.from(context).inflate(
					R.layout.adapter_user_order, parent, false);
			holer = new ViewHoler(view);

			view.setTag(holer);
		} else {
			holer = (ViewHoler) view.getTag();
		}
		holer.tvOrderTitle.setText(order.getOrderTitle());
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair(String.valueOf(context.getResources()
				.getColor(R.color.order_item_color)), "开始时间："));
		params.add(new BasicNameValuePair(String.valueOf(context.getResources()
				.getColor(R.color.red)), order.getOrderLimitTime()));
		holer.tvOrderTime.setText(AppUtils.setTextSpanColor(params));
		holer.tvOrderPerson.setText(order.getAptUserNum() + "人");

		return view;
	}

	class ViewHoler {
		@InjectView(R.id.tv_order_title)
		TextView tvOrderTitle;
		@InjectView(R.id.tv_order_person)
		TextView tvOrderPerson;
		@InjectView(R.id.tv_order_time)
		TextView tvOrderTime;

		public ViewHoler(View view) {
			ButterKnife.inject(this, view);
		}

	}

}
