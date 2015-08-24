package com.team.dream.runlegwork.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.runlegwork.BaseFragment;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.entity.Skill;
import com.team.dream.runlegwork.net.JsonBooleanResponseHandler;
import com.team.dream.runlegwork.utils.AppUtils;
import com.team.dream.runlegwork.utils.ToastUtils;
import com.team.dream.runlegwork.widget.TopBar;

public class SellSkillFragment extends BaseFragment implements
		OnCheckedChangeListener {
	private static final String ORDER_KEY = "order_key";
	private static final String ORDER_POSITION_KEY = "postion_key"; 

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
	@InjectView(R.id.et_skill_titile)
	EditText etSkillTitle;
	@InjectView(R.id.et_skill_price)
	EditText etSkillPrice;
	@InjectView(R.id.et_skill_detail)
	EditText etSkillDetail;
	@InjectView(R.id.et_skill_remark)
	EditText etSkillRemark;
	@InjectView(R.id.tv_skill_confirm)
	TextView tvSkillConfirm;

	private boolean isOnline = true;
	private int postion;

	public static SellSkillFragment newInstance(String selectNeed, int poistion) {
		SellSkillFragment fragment = new SellSkillFragment();
		Bundle bundle = new Bundle();
		bundle.putString(ORDER_KEY, selectNeed);
		bundle.putInt(ORDER_POSITION_KEY, poistion);
		fragment.setArguments(bundle);
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
		rgPriceSelect.setOnCheckedChangeListener(this);
		rgPriceSelect.check(rbHours.getId());
		rgOnlineSelect.check(rbOnline.getId());
		return view;
	}

	@Override
	protected void initializePresenter() {
		postion = getArguments().getInt(ORDER_POSITION_KEY);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);
	}

	@OnClick(R.id.tv_skill_confirm)
	public void pushSkill() {
		String[] checkSkillDetail = getResources().getStringArray(
				R.array.check_skill);
		View[] views = { etSkillTitle, etSkillPrice, etSkillDetail,
				etSkillRemark };
		String showType = tvShowType.getText().toString();
		String msg = AppUtils.CheckViewEmpty(checkSkillDetail, views);
		if (msg.equals(getString(R.string.success))) {
			pushSkill(showType);
		} else {
			ToastUtils.show(getActivity(), msg);
		}
	}

	private void pushSkill(String showType) {
		Skill skill = new Skill();
		skill.setSkillTitle(etSkillTitle.getText().toString().trim());
		skill.setSkillMoney(etSkillPrice.getText().toString().trim());
		skill.setSkillContent(etSkillDetail.getText().toString().trim());
		skill.setSkillSort(postion);
		skill.setSkillRemark(etSkillRemark.getText().toString().trim());
		skill.setTradeFlag(isOnline ? 1 : 2);
		skill.setSkillUnit(showType);
		api.createSkill(skill, new JsonBooleanResponseHandler() {

			@Override
			public void onSuccess() {
				ToastUtils.show(getActivity(), R.string.push_skill_success);
			}

			@Override
			public void onFailure(String errMsg) {
				ToastUtils.show(getActivity(), R.string.push_skill_failed);
			}
		});
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
		switch (group.getId()) {
		case R.id.rg_online_select:
			if (checkedId == R.id.rb_online) {
				isOnline = true;
			} else {
				isOnline = false;
			}
			break;
		case R.id.rg_price_select:
			tvShowType.setText(radioButton.getText());
			break;
		}
	}
}
