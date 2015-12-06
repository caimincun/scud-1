package com.team.dream.runlegwork.adapter.search;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.team.dream.runlegwork.MyBaseAdapter;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.entity.Skill;

public class NearbyPeopleSkillAdapter extends MyBaseAdapter {
	private Context ctx;
	private List<Skill> listdata;
	private LayoutInflater inflater;
	public NearbyPeopleSkillAdapter(Context ctx,List<Skill> listdata){
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
			convertView = inflater.inflate(R.layout.listview_item_neaybypeopleskill, null);
			holder.tvSkillname = (TextView) convertView.findViewById(R.id.item_nearbypeopleskill_tvName);
			holder.tvPrice = (TextView) convertView.findViewById(R.id.item_nearbypeopleskill_tvPrice);
			holder.tvContent = (TextView) convertView.findViewById(R.id.item_nearbypeopleskill_tvIntriduce);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		Skill skill = (Skill) getItem(position);
		holder.tvSkillname.setText(skill.getSkillTitle());
		holder.tvPrice.setText(skill.getSkillMoney()+skill.getSkillUnit());
		holder.tvContent.setText(skill.getSkillContent());
		return convertView;
	}
	static class ViewHolder{
		TextView tvSkillname,tvPrice,tvContent;
	}
	
}
