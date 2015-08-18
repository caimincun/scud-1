package com.team.dream.runlegwork.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.runlegwork.BaseFragment;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.widget.TopBar;

public class SellSkillFragment extends BaseFragment implements
		OnCheckedChangeListener {

	@InjectView(R.id.topbar)
	TopBar topbar;
	@InjectView(R.id.rg_online_select)
	RadioGroup rgOnlineSelect;
	@InjectView(R.id.rg_price_select)
	RadioGroup rgPriceSelect;
	@InjectView(R.id.rb_hours)
	RadioButton rbHours;
	@InjectView(R.id.rb_online)
	RadioButton rbOnline;
	@InjectView(R.id.tv_show_type)
	TextView tvShowType;

	public static SellSkillFragment newInstance() {
		SellSkillFragment fragment = new SellSkillFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_sell_skill, container,
				false);
		ButterKnife.inject(this, view);
		topbar.initialze("发布技能");
		rgOnlineSelect.setOnCheckedChangeListener(this);
		rgPriceSelect.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

			}
		});
		rgPriceSelect.check(rbHours.getId());
		rgOnlineSelect.check(rbOnline.getId());
		return view;
	}

	@Override
	protected void initializePresenter() {
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
		switch (group.getId()) {
		case R.id.rg_online_select:

			break;
		case R.id.rg_price_select:
			tvShowType.setText(radioButton.getText());
			break;

		}
	}
}
