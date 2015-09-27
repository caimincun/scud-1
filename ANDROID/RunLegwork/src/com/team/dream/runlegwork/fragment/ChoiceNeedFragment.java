package com.team.dream.runlegwork.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;

import com.team.dream.runlegwork.BaseFragment;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.navigator.Navigator;
import com.team.dream.runlegwork.widget.TopBar;

public class ChoiceNeedFragment extends BaseFragment {
	public static final String IS_ORDER = "is_order";

	@InjectView(R.id.topbar)
	TopBar topbar;
	@InjectView(R.id.lv_choice_need)
	ListView lvChoiceNeed;

	private String[] mChoiceNeed;
	private ChoiceNeedAdapter adapter;
	private boolean isOrder;

	public static ChoiceNeedFragment newInstance(boolean isOrder) {
		ChoiceNeedFragment fragment = new ChoiceNeedFragment();
		Bundle bundle = new Bundle();
		bundle.putBoolean(IS_ORDER, isOrder);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_choice_need, container,
				false);
		ButterKnife.inject(this, view);
		topbar.initialze("选择类型");
		lvChoiceNeed.setAdapter(adapter);
		return view;
	}

	@OnItemClick(R.id.lv_choice_need)
	public void choiceNeed(int postion) {
		if (isOrder) {
			Navigator.NavigatorToCreateOrderActivity(getActivity(),
					mChoiceNeed[postion]);
		} else {
			Navigator.NavigatorToCreateSkillActivity(getActivity(),
					mChoiceNeed[postion]);
		}

	}

	@Override
	protected void initializePresenter() {
		mChoiceNeed = getResources().getStringArray(R.array.choice_need);
		adapter = new ChoiceNeedAdapter();
		isOrder = getArguments().getBoolean(IS_ORDER);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);
	}

	class ChoiceNeedAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mChoiceNeed.length;
		}

		@Override
		public Object getItem(int arg0) {
			return mChoiceNeed[arg0];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHoler holer;
			if (null == convertView) {
				convertView = LayoutInflater.from(getActivity()).inflate(
						R.layout.adapter_choice_need, parent, false);
				holer = new ViewHoler(convertView);
				convertView.setTag(holer);
			} else {
				holer = (ViewHoler) convertView.getTag();
			}
			holer.tvProjectName.setText(mChoiceNeed[position]);
			return convertView;
		}

		class ViewHoler {
			@InjectView(R.id.tv_project_name)
			TextView tvProjectName;

			public ViewHoler(View view) {
				ButterKnife.inject(this, view);
			}
		}
	}

}
