package com.team.dream.runlegwork.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.team.dream.runlegwork.MyBaseAdapter;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.SingletonServiceManager;

public class SinglePicAdapter extends MyBaseAdapter {
	private List<String> list;
	private Context ctx;
	private LayoutInflater inflater;
	public SinglePicAdapter(List<String> list, Context ctx) {
		super();
		this.list = list;
		this.ctx = ctx;
		this.inflater = LayoutInflater.from(ctx);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return super.getItemId(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.item_singlepic, null);
			holder.iv = (ImageView) convertView.findViewById(R.id.item_singlepci_iv);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		SingletonServiceManager.getInstance().display("file:///mnt"+list.get(position), holder.iv, R.drawable.user_default_head, null);
		return convertView;
	}
	static class ViewHolder{
		ImageView iv;
	}
}
