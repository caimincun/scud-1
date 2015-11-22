package com.team.dream.runlegwork.adapter.search;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.imageloader.utils.DensityUtils;
import com.team.dream.runlegwork.MyBaseAdapter;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.R.layout;
import com.team.dream.runlegwork.SingletonServiceManager;
import com.team.dream.runlegwork.entity.SkillAndUser;
import com.team.dream.runlegwork.utils.PathUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class SkillPeopleAdapter extends MyBaseAdapter {
	private Context ctx;
	private List<SkillAndUser> listdata;
	private LayoutInflater inflater;
	private int picWidth;
	public SkillPeopleAdapter(Context ctx,int width ,List<SkillAndUser> listdata){
		this.ctx = ctx;
		this.listdata = listdata;
		this.picWidth = width;
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
			
			convertView = inflater.inflate(R.layout.listview_item_skillpeople, null);
//			holder.iv1 = (ImageView) convertView.findViewById(R.id.item_skillpeople_iv1);
			holder = new ViewHolder(convertView);
			LayoutParams params = new LayoutParams((picWidth-DensityUtils.dp2px(ctx, 70))/3, (picWidth-DensityUtils.dp2px(ctx, 70))/3);
			
			holder.iv1.setLayoutParams(params);
			params.leftMargin = DensityUtils.dp2px(ctx, 5);
			holder.iv2.setLayoutParams(params);
			holder.iv3.setLayoutParams(params);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		SkillAndUser sau = (SkillAndUser) getItem(position);
		holder.tvSkillName.setText(sau.getSkillTitle());
		holder.tvDistance.setText(sau.getDistance()+"km");
		holder.tvName.setText(sau.getUserName()+"");
		holder.tvPrice.setText(sau.getSkillMoney()+sau.getSkillUnit()+"");
		holder.tvAge.setText(sau.getAge()+"");
		int sex = sau.getUserInfoSex();
		if(sex == 0){
			holder.llSexBg.setBackgroundResource(R.color.requirement_momen);
			holder.ivSex.setImageResource(R.drawable.icon_grilwhite);
		}
		else{
			holder.llSexBg.setBackgroundResource(R.color.requirement_man);
			holder.ivSex.setBackgroundResource(R.drawable.icon_boywhite);
		}
		SingletonServiceManager.getInstance().display(PathUtil.getPicPath(sau.getUserPicture()), holder.ivHead, R.drawable.user_default_head, null);
		String picString = sau.getSkillPicture();
		String[] pictures = picString.split("\\;");
		switch (pictures.length) {
		case 1:
			SingletonServiceManager.getInstance().display(PathUtil.getPicPath(pictures[0]), holder.iv1, R.drawable.photo_1, null);
			break;
		case 2:
			SingletonServiceManager.getInstance().display(PathUtil.getPicPath(pictures[0]), holder.iv1, R.drawable.photo_1, null);
			SingletonServiceManager.getInstance().display(PathUtil.getPicPath(pictures[1]), holder.iv2, R.drawable.photo_1, null);
			break;
		case 3:
			SingletonServiceManager.getInstance().display(PathUtil.getPicPath(pictures[0]), holder.iv1, R.drawable.photo_1, null);
			SingletonServiceManager.getInstance().display(PathUtil.getPicPath(pictures[1]), holder.iv2, R.drawable.photo_1, null);
			SingletonServiceManager.getInstance().display(PathUtil.getPicPath(pictures[2]), holder.iv3, R.drawable.photo_1, null);
			break;

		default:
			break;
		}
		
		return convertView;
	}
	static class ViewHolder{
		@InjectView(R.id.item_skillpeople_iv1)
		ImageView iv1;
		@InjectView(R.id.item_skillpeople_iv2)
		ImageView iv2;
		@InjectView(R.id.item_skillpeople_iv3)
		ImageView iv3;
		@InjectView(R.id.item_skillpeople_ivHead)
		ImageView ivHead;
		@InjectView(R.id.item_skillpeople_ivSex)
		ImageView ivSex;
		@InjectView(R.id.item_skillpeople_tvSkill1)
		TextView tvSkillName;
		@InjectView(R.id.item_skillpeople_llSexbg)
		LinearLayout llSexBg;
		@InjectView(R.id.item_skillpeople_tvDistance)
		TextView tvDistance;
		@InjectView(R.id.item_skillpeople_tvName)
		TextView tvName;
		@InjectView(R.id.item_skillpeople_tvPrice)
		TextView tvPrice;
		@InjectView(R.id.item_skillpeople_tvSexandAge)
		TextView tvAge;
		public ViewHolder(View view){
			ButterKnife.inject(this,view);
		}
		
	}
}
