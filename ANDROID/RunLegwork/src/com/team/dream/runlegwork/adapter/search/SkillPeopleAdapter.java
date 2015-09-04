package com.team.dream.runlegwork.adapter.search;

import java.util.List;

import com.team.dream.runlegwork.MyBaseAdapter;
import com.team.dream.runlegwork.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SkillPeopleAdapter extends MyBaseAdapter {
	private Context ctx;
	private List<String> listdata;
	private LayoutInflater inflater;
	public SkillPeopleAdapter(Context ctx,List<String> listdata){
		this.ctx = ctx;
		this.listdata = listdata;
		this.inflater = LayoutInflater.from(ctx);
	}
	@Override
	public int getCount() {
		return listdata.size();
	}

	@Override
	public Object getItem(int position) {
		return listdata.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.listview_item_skillpeople, null);
			
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		return convertView;
	}
	static class ViewHolder{
		
	}
}
