package com.team.dream.runlegwork.fragment;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.runlegwork.BaseFragment;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.activity.search.SearchConditionActivity;
import com.viewpagerindicator.TabPageIndicator;

public class NearbyPeopleFragment extends BaseFragment {
	private Context ctx;
	@InjectView(R.id.nearby_pager)
	ViewPager pager;
	@InjectView(R.id.nearby_indicator)
	TabPageIndicator indicator;
	@InjectView(R.id.neayby_moreChoose)
	ImageView ivMoreChoose;

	private ArrayList<String> listTab = new ArrayList<String>();
	private String[] conditionGroup = new String[] { "全部", "按摩", "陪吃饭", "陪跑步",
		"写程序", "陪唱歌"};
	FragmentPagerAdapter adapter;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View mainView = inflater.inflate(R.layout.fragment_nearby, container,
				false);
		ctx = getActivity();
		ButterKnife.inject(this, mainView);
		adapter = new TabPageIndicatorAdapter(
				getActivity().getSupportFragmentManager());
		
		pager.setAdapter(adapter);

		indicator.setViewPager(pager);
		listTab.add("全部");
		return mainView;
	}

	/**
	 * ViewPager閫傞厤鍣
	 * 
	 * @author len
	 * 
	 */
	class TabPageIndicatorAdapter extends FragmentPagerAdapter {
		public TabPageIndicatorAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = new NearbyPeopleDetailFragment();
			Bundle args = new Bundle();
			args.putString("arg", conditionGroup[position]);
			fragment.setArguments(args);

			return fragment;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			
			return conditionGroup[position % conditionGroup.length];
		}

		@Override
		public int getCount() {
			return conditionGroup.length;
		}
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == getActivity().RESULT_OK){
			ArrayList<String> str = data.getStringArrayListExtra("listCondition");
			listTab.clear();
			listTab.addAll(str);
			conditionGroup = (String[]) listTab.toArray(new String[listTab.size()]);
			adapter.notifyDataSetChanged();
			indicator.notifyDataSetChanged();
		}
	}
	@Override
	public void onResume() {
		super.onResume();
	}
	@OnClick(R.id.neayby_moreChoose)
	public void moreChoose(){
		Intent intent = new Intent(ctx, SearchConditionActivity.class);
		intent.putExtra("condition", conditionGroup);
		startActivityForResult(intent, 1);
	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(getActivity());
	}
	@Override
	protected void initializePresenter() {
		// TODO Auto-generated method stub

	}}
