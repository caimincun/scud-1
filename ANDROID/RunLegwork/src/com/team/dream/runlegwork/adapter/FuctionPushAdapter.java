package com.team.dream.runlegwork.adapter;

import java.util.List;

import android.annotation.SuppressLint;
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
import com.team.dream.runlegwork.entity.FunctionItem;
import com.team.dream.runlegwork.utils.StringUtils;

@SuppressLint("NewApi")
public class FuctionPushAdapter extends BaseAdapter {

	private List<FunctionItem> mData;
	private Context mContext;

	public FuctionPushAdapter(List<FunctionItem> mData, Context mContext) {
		this.mContext = mContext;
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
		FunctionItem item = mData.get(position);
		ViewHoler holer = null;
		if (null == convertView) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.adapter_push_fuction_item, parent, false);
			holer = new ViewHoler(convertView);
			convertView.setTag(holer);

		} else {
			holer = (ViewHoler) convertView.getTag();
		}
		holer.ivFucImag.setImageResource(item.getFuctionRes());
		String fucName = item.getFuctionName();
		if (StringUtils.isEmpty(fucName)) {
			holer.tvFucName.setVisibility(View.GONE);
		} else {
			holer.tvFucName.setText(item.getFuctionName());
		}
		return convertView;
	}

	class ViewHoler {
		@InjectView(R.id.iv_fuc_img)
		ImageView ivFucImag;
		@InjectView(R.id.tv_fuc_name)
		TextView tvFucName;

		public ViewHoler(View view) {
			ButterKnife.inject(this, view);
		}
	}

}
