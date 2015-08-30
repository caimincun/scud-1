package com.team.dream.runlegwork.adapter.search;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.team.dream.runlegwork.R;

public class SearchconditionAdapter extends BaseAdapter {
	private List<String> listdata;
	private LayoutInflater inflater;
	private Context ctx;
	int flag;//0表示附近页面条件，1表示已订阅，2表示未订阅
	
	public SearchconditionAdapter(List<String> listdata,Context ctx,int flag) {
		super();

		this.listdata = listdata;
		this.ctx = ctx;
		this.inflater = LayoutInflater.from(ctx);
		this.flag = flag;
	}

	@Override
	public int getCount() {
		return listdata.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return listdata.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View currentView, ViewGroup arg2) {
		ViewHolder holder = null;
		if(currentView == null){
			currentView = inflater.inflate(R.layout.listview_item_searchcondition, null);
			holder = new ViewHolder();
			holder.tv = (TextView) currentView.findViewById(R.id.main_item_tv);
			currentView.setTag(holder);
		}
		else{
			holder = (ViewHolder) currentView.getTag();
		}
		if(flag == 0){
			holder.tv.setBackgroundResource(R.drawable.shape_condition1);
		}
		else if(flag == 1){
			holder.tv.setBackgroundResource(R.drawable.shape_condition);
		}
		else if(flag == 2){
			holder.tv.setBackgroundResource(R.drawable.shape_condition2);
		}
		holder.tv.setText(listdata.get(position));
		return currentView;
	}
	
	static class ViewHolder{
		TextView tv;
	}

}
