package com.team.dream.runlegwork.adapter.search;

import java.util.ArrayList;
import java.util.List;

import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.entity.UserInfo;
import com.team.dream.runlegwork.view.RoundImageView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NearbyPeoAdapter extends BaseAdapter {
	List<UserInfo> list = new ArrayList<UserInfo>();
	private Context ctx;
	private LayoutInflater inflater;
	public NearbyPeoAdapter(List<UserInfo> list,Context ctx){
		this.list = list;
		this.ctx = ctx;
		this.inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View currentView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if(currentView == null){
			currentView = inflater.inflate(R.layout.plistview_item_nearby, null);
			holder = new ViewHolder();
			holder.tvAge = (TextView) currentView.findViewById(R.id.itemNearby_tvSexandAge);
			holder.tvLabel = (TextView) currentView.findViewById(R.id.itemNearby_tvLabel);
			holder.tvSignin = (TextView) currentView.findViewById(R.id.itemNearby_tvSign);
			holder.tvName = (TextView) currentView.findViewById(R.id.itemNearby_tvName);
			holder.tvDistance = (TextView) currentView.findViewById(R.id.itemNearby_tvDistance);
			holder.rv = (RoundImageView) currentView.findViewById(R.id.itemNearby_ivHead);
			currentView.setTag(holder);
		}
		else{
			holder = (ViewHolder) currentView.getTag();
		}
		return currentView;
	}
	
	static class ViewHolder{
		TextView tvName,tvAge,tvLabel,tvSignin,tvDistance;
		RoundImageView rv;
	}

}
