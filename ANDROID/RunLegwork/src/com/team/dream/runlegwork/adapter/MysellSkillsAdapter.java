package com.team.dream.runlegwork.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.runlegwork.MyBaseAdapter;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.entity.Skill;
import com.team.dream.runlegwork.navigator.Navigator;

public class MysellSkillsAdapter extends MyBaseAdapter {

	private Context context;
	private List<Skill> mData;

	public MysellSkillsAdapter(Context context, List<Skill> data) {
		this.context = context;
		if (data==null) {
			data=new ArrayList<Skill>();
		}
		this.mData = data;
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int arg0) {
		return mData.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		final Skill skill = mData.get(position);
		ViewHoler holer = null;
		if (view == null) {

			view = LayoutInflater.from(context).inflate(
					R.layout.listview_item_mysellskill, parent, false);
			holer = new ViewHoler(view);

			view.setTag(holer);
		} else {
			holer = (ViewHoler) view.getTag();
		}
		
		holer.tvTitle.setText(skill.getSkillTitle());
		holer.tvMoney.setText(skill.getSkillMoney()+skill.getSkillUnit());
		holer.tvContent.setText(skill.getSkillContent());
		holer.tvUpdate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Navigator.NavigatorToPushSkillActivity(context,skill.getSkillToken());
			}
		});

		return view;
	}

	class ViewHoler {
		@InjectView(R.id.item_mysellskill_tvTitle)
		TextView tvTitle;
		@InjectView(R.id.item_mysellskill_tvMoney)
		TextView tvMoney;
		@InjectView(R.id.item_mysellskill_tvContent)
		TextView tvContent;
		@InjectView(R.id.item_mysellskill_tvUpdate)
		TextView tvUpdate;

		public ViewHoler(View view) {
			ButterKnife.inject(this, view);
		}

	}

}
