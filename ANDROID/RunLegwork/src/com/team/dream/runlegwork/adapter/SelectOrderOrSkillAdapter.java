package com.team.dream.runlegwork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.runlegwork.R;

public class SelectOrderOrSkillAdapter extends BaseAdapter {

	private Context mContext;
	private String[] mData;

	public SelectOrderOrSkillAdapter(Context mContex, String[] mData) {
		this.mContext = mContex;
		this.mData = mData;
	}

	@Override
	public int getCount() {
		return mData.length;
	}

	@Override
	public Object getItem(int position) {
		return mData[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHoler holer = null;
		if (null == convertView) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.adapter_select_order_or_skill, parent, false);
			holer = new ViewHoler(convertView);
			convertView.setTag(holer);
		} else {
			holer = (ViewHoler) convertView.getTag();
		}
		holer.tvTypeSkill.setText(mData[position]);
		return convertView;
	}

	class ViewHoler {
		@InjectView(R.id.tv_type_or_skill)
		TextView tvTypeSkill;

		public ViewHoler(View view) {
			ButterKnife.inject(this, view);
		}
	}
}
