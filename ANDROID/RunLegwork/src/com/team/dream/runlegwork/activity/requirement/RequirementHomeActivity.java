package com.team.dream.runlegwork.activity.requirement;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.widget.MainTitileBar;

public class RequirementHomeActivity extends BaseActivity{
	@InjectView(R.id.reqhome_topbar)
	MainTitileBar mtb;
	@InjectView(R.id.reqhome_vp)
	ViewPager vp;
	@InjectView(R.id.reqhome_tvDistance)
	TextView tvDistance;
	@InjectView(R.id.reqhome_tvTime)
	TextView tvTime;
	
	private List<Fragment> listFragment;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_requirementhome);
		ButterKnife.inject(this);
		loadViewPager();
	}
	
	private void loadViewPager() {
		listFragment = new ArrayList<Fragment>();
		listFragment.add(new RequirementByDistFragment());
		listFragment.add(new RequirementByDistFragment()); 
		vp.setAdapter(fpa);
	}
	
	FragmentPagerAdapter fpa = new FragmentPagerAdapter(getSupportFragmentManager()) {
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listFragment.size();
		}
		
		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return listFragment.get(arg0);
		}
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(this);
	}
	
}
