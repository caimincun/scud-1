package com.team.dream.runlegwork.adapter.requirement;


import java.util.List;

import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.SingletonServiceManager;
import com.team.dream.runlegwork.entity.UserOrder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RequirementAdapter extends BaseAdapter {
	private Context ctx;
	private List<UserOrder> list;
	private LayoutInflater inflater;
	public RequirementAdapter(Context ctx, List<UserOrder> list) {
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
		return 0;
	}

	@Override
	public View getView(int position, View currentView, ViewGroup arg2) {
		ViewHolder holder = null;
		if(currentView == null){
			currentView = inflater.inflate(R.layout.listview_item_requirement, null);
			holder = new ViewHolder();
			holder.tvAge = (TextView) currentView.findViewById(R.id.item_requirement_tvAge);
			holder.tvReqContent = (TextView) currentView.findViewById(R.id.item_requirement_tvReqContent);
			holder.tvSendStatus = (TextView) currentView.findViewById(R.id.item_requirement_tvSendStatus);
			holder.tvMoney = (TextView) currentView.findViewById(R.id.item_requirement_tvMoney);
			holder.tvMoneyAssure = (TextView) currentView.findViewById(R.id.item_requirement_tvMoneyAssure);
			holder.tvReqDetail = (TextView) currentView.findViewById(R.id.item_requirement_tvReqDetail);
			holder.tvDistance = (TextView) currentView.findViewById(R.id.item_requirement_tvDistance);
			holder.tvInviteNum = (TextView) currentView.findViewById(R.id.item_requirement_tvInviteNum);
			holder.ivSex = (ImageView) currentView.findViewById(R.id.item_requirement_ivSex);
			holder.ivHead = (ImageView) currentView.findViewById(R.id.item_requirement_ivHead);
			currentView.setTag(holder);
		}
		else{
			holder = (ViewHolder) currentView.getTag();
		}
		
		holder.tvReqContent.setText(list.get(position).getOrderTitle()+"");
		holder.tvMoney.setText(list.get(position).getOrderMoney()+"");
		holder.tvReqDetail.setText(list.get(position).getOrderContent()+"");
		int sex = list.get(position).getUserSex();
		if(sex==1){
			holder.ivSex.setImageResource(R.drawable.icon_boy);
		}
		else{
			holder.ivSex.setImageResource(R.drawable.icon_gril);
		}
		String url = "http://scud-images.bj.bcebos.com"+list.get(position).getUserPicture();
		SingletonServiceManager.getInstance().display(url, holder.ivHead, R.drawable.user_default_head, null);
		return currentView;
	}
	
	static class ViewHolder{
		TextView tvReqContent,tvSendStatus,tvMoney,tvMoneyAssure,tvReqDetail,tvUsername,tvAge,tvDistance,tvInviteNum;
		ImageView ivHead,ivSex;
	}

}
