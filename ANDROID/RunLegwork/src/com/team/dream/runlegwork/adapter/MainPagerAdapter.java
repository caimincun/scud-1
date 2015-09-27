package com.team.dream.runlegwork.adapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainPagerAdapter extends FragmentPagerAdapter {

	private List<Fragment> mList;

	public MainPagerAdapter(FragmentManager fm, List<Fragment> mList) {
		super(fm);
		if (mList == null) {
			mList = new ArrayList<Fragment>();
		}
		this.mList = mList;
	}

	@Override
	public Fragment getItem(int postion) {

		return mList.get(postion);
	}

	@Override
	public int getCount() {
		return mList.size();
	}

}
