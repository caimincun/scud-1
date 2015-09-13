package com.team.dream.runlegwork.adapter.search;

import java.util.List;

import com.team.dream.runlegwork.MyBaseAdapter;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.SingletonServiceManager;
import com.team.dream.runlegwork.entity.SkillAndUser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SkillPeopleAdapter extends MyBaseAdapter {
	private Context ctx;
	private List<SkillAndUser> listdata;
	private LayoutInflater inflater;
	public SkillPeopleAdapter(Context ctx,List<SkillAndUser> listdata){
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
			holder.iv1 = (ImageView) convertView.findViewById(R.id.item_skillpeople_iv1);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		SingletonServiceManager.getInstance().display("http://scud-skills.bj.bcebos.com/upload/20150904163656089klva", holder.iv1, R.drawable.user_default_head, null);
		return convertView;
	}
	static class ViewHolder{
		ImageView iv1,iv2;
		
	}
}
