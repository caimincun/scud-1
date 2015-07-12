package com.team.dream.runlegwork.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.widget.BannerBrowsingWidget;

public class HomeFragment extends LocationFragment {
	private static final String TAG = HomeFragment.class.getSimpleName();

	@InjectView(R.id.banner_browing)
	BannerBrowsingWidget bannerbrowing;

	public static HomeFragment newInstance(int postion) {
		HomeFragment fragment = new HomeFragment();
		Bundle bundle = new Bundle();
		bundle.putInt(TAG, postion);

		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, container, false);
		ButterKnife.inject(this, view);

		bannerbrowing.initView(null);

		return view;
	}

	@Override
	protected void initializePresenter() {
		startPosition();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);
	}

//	@Override
//	public void OnCompleteLocation(BDLocation location) {
//		Log.d("location", "latitude:"+location.getLatitude()+",longitude:"+location.getLongitude());
//
//	}

	@Override
	public void OnCompleteLocation(boolean isLocationSuccess) {
		// TODO Auto-generated method stub
		
	}

}
