package com.team.dream.runlegwork.adapter.search;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.entity.NearUserInfo;
import com.team.dream.runlegwork.view.RoundImageView;

public class NearbyPeoAdapter extends BaseAdapter {
	List<NearUserInfo> list = new ArrayList<NearUserInfo>();
	private Context mContext;

	public NearbyPeoAdapter(List<NearUserInfo> list, Context ctx) {
		this.list = list;
		this.mContext = ctx;
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
		NearUserInfo userInfo = list.get(position);
		ViewHolder holder;
		if (currentView == null) {
			currentView = LayoutInflater.from(mContext).inflate(R.layout.plistview_item_nearby, arg2, false);
			holder = new ViewHolder();
			holder.tvAge = (TextView) currentView.findViewById(R.id.itemNearby_tvSexandAge);
			holder.tvLabel = (TextView) currentView.findViewById(R.id.itemNearby_tvLabel);
			holder.tvSignin = (TextView) currentView.findViewById(R.id.itemNearby_tvSign);
			holder.tvName = (TextView) currentView.findViewById(R.id.itemNearby_tvName);
			holder.tvDistance = (TextView) currentView.findViewById(R.id.itemNearby_tvDistance);
			holder.rv = (RoundImageView) currentView.findViewById(R.id.itemNearby_ivHead);
			currentView.setTag(holder);
		} else {
			holder = (ViewHolder) currentView.getTag();
		}

		holder.tvDistance.setText(userInfo.getUserDantce());
		holder.tvName.setText(userInfo.getUserRealName());
		return currentView;
	}

	static class ViewHolder {
		TextView tvName, tvAge, tvLabel, tvSignin, tvDistance;
		RoundImageView rv;
	}

}
