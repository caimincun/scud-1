package com.team.dream.runlegwork.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.team.dream.runlegwork.MyBaseAdapter;
import com.team.dream.runlegwork.R;

public class ShopAdapter extends MyBaseAdapter {
	private Context ctx;
	private List<String> list;
	private LayoutInflater inflater;
	
	public ShopAdapter(Context ctx, List<String> list) {
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
			holder = new ViewHolder();
			currentView.setTag(holder);
		}
		else{
			holder = (ViewHolder) currentView.getTag();
		}
		
		return currentView;
	}
	
	static class ViewHolder{
		
	}
	

}
