package com.team.dream.runlegwork.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.SingletonServiceManager;
import com.team.dream.runlegwork.entity.NearUserInfo;
import com.team.dream.runlegwork.utils.StringUtils;
import com.team.dream.runlegwork.view.RoundImageView;

public class AnserOrderPersonAdapter extends BaseAdapter {

	private List<NearUserInfo> mData;
	private Context mConext;

	public AnserOrderPersonAdapter(List<NearUserInfo> mData, Context mContext) {
		this.mConext = mContext;
		this.mData = mData;
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		NearUserInfo userInfo = mData.get(position);
		ViewHoler holer = null;
		if (null == convertView) {
			convertView = LayoutInflater.from(mConext).inflate(
					R.layout.adapter_answer_order_item, parent, false);
			holer = new ViewHoler(convertView);
			convertView.setTag(holer);
		} else {
			holer = (ViewHoler) convertView.getTag();
		}
		holer.tvName.setText(userInfo.getUserRealName());
		holer.tvDance.setText(userInfo.getDistance());
		holer.tvJob.setText(userInfo.getUserInfoJob());
		String sign = userInfo.getUserInfoSignature();
		String intriduce = userInfo.getUserInfoIntroduction();
		if (StringUtils.isEmpty(intriduce)) {
			intriduce = "这个人比较懒，暂无个人简介";
		}
		holer.tvIntroduce.setText(intriduce);

		if (StringUtils.isEmpty(sign)) {
			sign = "暂无";
		}
		holer.tvServerPrice.setText("签名:   " + sign);
		if (userInfo.getUserInfoSex() == 1) {
			SingletonServiceManager.getInstance().display(
					"drawable://" + R.drawable.icon_boy, holer.ivSex,
					R.drawable.home_banner_2, null);
		} else {
			SingletonServiceManager.getInstance().display(
					"drawable://" + R.drawable.icon_gril, holer.ivSex,
					R.drawable.home_banner_2, null);
		}

		return convertView;
	}

	class ViewHoler {
		@InjectView(R.id.tv_name)
		TextView tvName;
		@InjectView(R.id.iv_head_img)
		RoundImageView ivHeadImg;
		@InjectView(R.id.tv_dantce)
		TextView tvDance;
		@InjectView(R.id.iv_sex)
		ImageView ivSex;
		@InjectView(R.id.tv_job)
		TextView tvJob;
		@InjectView(R.id.tv_server_price)
		TextView tvServerPrice;
		@InjectView(R.id.tv_introduce)
		TextView tvIntroduce;

		public ViewHoler(View view) {
			ButterKnife.inject(this, view);
		}
	}

}
