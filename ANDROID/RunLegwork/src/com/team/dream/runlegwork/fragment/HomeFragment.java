package com.team.dream.runlegwork.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.activity.requirement.RequirementHomeActivity;
import com.team.dream.runlegwork.activity.requirement.SkillPeopleActivity;
import com.team.dream.runlegwork.navigator.Navigator;
import com.team.dream.runlegwork.net.JsonBooleanResponseHandler;
import com.team.dream.runlegwork.singleservice.LocationCache;
import com.team.dream.runlegwork.widget.BannerBrowsingWidget;

public class HomeFragment extends LocationFragment {
	private static final String TAG = HomeFragment.class.getSimpleName();
	@InjectView(R.id.ll_push_order)
	LinearLayout llPushOrder;
	@InjectView(R.id.banner_browing)
	BannerBrowsingWidget bannerbrowing;
	@InjectView(R.id.home_llAcceptTast)
	LinearLayout llAcceptTask;
	@InjectView(R.id.ll_push_skill)
	LinearLayout llPushSkill;
	@InjectView(R.id.home_llMassage)
	LinearLayout llMassage;

	public static HomeFragment newInstance(int postion) {
		HomeFragment fragment = new HomeFragment();
		Bundle bundle = new Bundle();
		bundle.putInt(TAG, postion);

		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, container, false);
		ButterKnife.inject(this, view);
		bannerbrowing.initView(null);
		return view;
	}

	@Override
	protected void initializePresenter() {
		startPosition();
	}

	@OnClick(R.id.ll_push_order)
	public void pushOrder() {
		// Navigator.NavigatorToWebViewActivity(getActivity());
		// Navigator.NavigatorToCreateOrderActivity(getActivity());
		Navigator.NavigatorToChoiceNeedActivity(getActivity(), true);
	}
	@OnClick(R.id.ll_push_skill)
	public void pushSkill() {
		Navigator.NavigatorToChoiceNeedActivity(getActivity(), false);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);
	}

	@Override
	public void OnCompleteLocation(boolean isLocationSuccess) {

		if (isLocationSuccess) {
			Log.d("TAG", LocationCache.getIntance().getCurrentCityLocation()
					.toString());
			api.uploadUserLocation(new JsonBooleanResponseHandler() {
				@Override
				public void onSuccess() {
					Log.d("TAG", "success");
				}

				@Override
				public void onFailure(String errMsg) {
				}
			});
		}

	}

	@OnClick(R.id.home_llAcceptTast)
	public void acceptTask() {
		startActivity(new Intent(getActivity(), RequirementHomeActivity.class));
	}
	@OnClick(R.id.home_llMassage)
	public void Massage(){
		startActivity(new Intent(getActivity(), SkillPeopleActivity.class));
	}

}
