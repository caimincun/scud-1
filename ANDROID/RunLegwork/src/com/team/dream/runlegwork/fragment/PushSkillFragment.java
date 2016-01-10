package com.team.dream.runlegwork.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.runlegwork.BaseFragment;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.activity.SelectOrderOrSkillActvity;
import com.team.dream.runlegwork.adapter.PushSkillAdapter;
import com.team.dream.runlegwork.adapter.PushSkillAdapter.OnSetDataListener;
import com.team.dream.runlegwork.entity.Skill;
import com.team.dream.runlegwork.navigator.Navigator;
import com.team.dream.runlegwork.net.JsonBooleanResponseHandler;
import com.team.dream.runlegwork.utils.AppUtils;
import com.team.dream.runlegwork.utils.ToastUtils;
import com.team.dream.runlegwork.widget.MainTitileBar;

public class PushSkillFragment extends BaseFragment implements
		OnSetDataListener, OnCheckedChangeListener {

	@InjectView(R.id.mb_topbar)
	MainTitileBar mbTopbar;
	@InjectView(R.id.lv_push_order)
	ListView lvHomePage;
	@InjectView(R.id.tv_push_order)
	TextView tvPushOrder;
	@InjectView(R.id.rl_root_view)
	RelativeLayout rlRootView;
	private PushSkillAdapter mAdapter;
	private ViewHoler holer;

	private TextView tvType;
	private boolean isOnline;

	@Override
	protected void initializePresenter() {
		mAdapter = new PushSkillAdapter(getActivity());
	}

	public static PushSkillFragment newInstance() {
		PushSkillFragment fragment = new PushSkillFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_push_order, container,
				false);
		ButterKnife.inject(this, view);
		mbTopbar.setTitle("技能发布 ");
		mbTopbar.hideTitleRight();

		View foot = inflater.inflate(R.layout.adapter_push_skill_select,
				lvHomePage, false);
		lvHomePage.addFooterView(foot);
		holer = new ViewHoler(foot);
		lvHomePage.setAdapter(mAdapter);
		holer.rgOnlineSelect.check(holer.rbOnline.getId());
		mAdapter.setOnSetDataListener(this);
		holer.rgOnlineSelect.setOnCheckedChangeListener(this);
		holer.ivSelectPic.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		rlRootView.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						int screenHeight = rlRootView.getRootView().getHeight();
						int myHeight = rlRootView.getHeight();
						int heightDiff = screenHeight - myHeight;
						Log.e("onGlobalLayout", "screenHeight=" + screenHeight);
						Log.e("onGlobalLayout", "myHeight=" + myHeight);
						int stateBarHeight = AppUtils.dip2px(getActivity(),
								AppUtils.getStatusBarHeight(getActivity()));

						if (heightDiff > stateBarHeight) {
							tvPushOrder.setVisibility(View.GONE);
						} else {
							tvPushOrder.setVisibility(View.VISIBLE);
						}

					}
				});

		return view;
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (group.getId()) {
		case R.id.rg_online_select:
			if (checkedId == R.id.rb_online) {
				isOnline = true;
			} else {
				isOnline = false;
			}
			break;
		}
	}

	class ViewHoler {
		@InjectView(R.id.iv_select_pic)
		ImageView ivSelectPic;
		@InjectView(R.id.gv_select_pic)
		GridView gvSelectPic;
		@InjectView(R.id.rg_online_select)
		RadioGroup rgOnlineSelect;
		@InjectView(R.id.rb_online)
		RadioButton rbOnline;
		@InjectView(R.id.et_skill_remark)
		EditText etSkillRemark;

		public ViewHoler(View view) {
			ButterKnife.inject(this, view);
		}
	}

	@OnClick(R.id.tv_push_order)
	public void pushSkill() {
		String[] mDataString = mAdapter.getCheckData();
		String[] checkString = new String[] { mDataString[0], mDataString[1],
				mDataString[2], mDataString[3], mDataString[4],
				holer.etSkillRemark.getText().toString().trim() };

		String msg = AppUtils
				.CheckViewEmpty(
						getResources().getStringArray(R.array.check_skill),
						checkString);
		if (msg.equals(getString(R.string.success))) {
			pushSkill(checkString);
		} else {
			ToastUtils.show(getActivity(), msg);
		}

	}

	private void pushSkill(String[] checkString) {
		Skill skill = new Skill();
		skill.setSkillTitle(checkString[0]);
		skill.setSkillMoney(checkString[2]);
		skill.setSkillContent(checkString[3]);
		skill.setSkillSort(checkString[1]);
		skill.setSkillRemark(checkString[5]);
		skill.setTradeFlag(isOnline ? 1 : 2);
		skill.setSkillUnit(checkString[4]);
		api.createSkill(skill, null, new JsonBooleanResponseHandler() {

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
	public void ChoiceNeed(View v) {
		tvType = (TextView) v;
		Navigator.NavigatorToSelectOrderOrSkillActivity(getActivity());
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != Activity.RESULT_OK) {
			return;
		}
		switch (requestCode) {
		case SelectOrderOrSkillActvity.REQUEST_TYPE:
			tvType.setText(data.getStringExtra("data"));
			break;

		}
	}

}
