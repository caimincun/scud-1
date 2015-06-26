package com.team.dream.runlegwork.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.adapter.MainPagerAdapter;
import com.team.dream.runlegwork.fragment.HomeFragment;
import com.team.dream.runlegwork.listener.MainPageChangerLister;
import com.team.dream.runlegwork.widget.TabSelectView;
import com.team.dream.runlegwork.widget.TabSelectView.IMenuItemOnClick;

public class WelcomeActivity extends BaseActivity implements IMenuItemOnClick   {

	private List<Fragment> fragments = new ArrayList<Fragment>();

	@InjectView(R.id.view_pager)
	ViewPager vp;
	@InjectView(R.id.tab_select_view)
	TabSelectView tsv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_tab);
		ButterKnife.inject(this);

		fragments.add(HomeFragment.newInstance(1));
		fragments.add(HomeFragment.newInstance(2));
		fragments.add(HomeFragment.newInstance(3));
		fragments.add(HomeFragment.newInstance(4));
		vp.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), fragments));
		vp.setOnPageChangeListener(new MainPageChangerLister(tsv));
		tsv.setOnMenuItemClickListener(this);
		// if (savedInstanceState == null) {
		// initializeActivity(savedInstanceState);
		// }

	}

	@Override
	public void menuItemOnClick(int position) {
		vp.setCurrentItem(position,false);
	}

	// private void initializeActivity(Bundle savedInstanceState) {
	// addFragment(R.id.container, new WelcomeFragment());
	// }

}
