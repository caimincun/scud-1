package com.team.dream.runlegwork.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.pulltorefresh.library.PullToRefreshListView;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.adapter.ShopAdapter;
import com.team.dream.runlegwork.fragment.ShopTypeFragment;
import com.team.dream.runlegwork.widget.MainTitileBar;
import com.team.dream.slidingmenu.lib.SlidingMenu;
import com.team.dream.slidingmenu.lib.app.SlidingFragmentActivity;

public class ShopActivity extends SlidingFragmentActivity {
	@InjectView(R.id.shop_ptListv)
	PullToRefreshListView plListv;
	@InjectView(R.id.shop_topbar)
	MainTitileBar mtb;
	FragmentTransaction ft;
	SlidingMenu sm;
	FragmentManager fragmentManager;
	
	private ShopAdapter shopAdapter;
	private List<String> listdata;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop);
		ButterKnife.inject(this);
		mtb.finishLeft(this);
		initData();
		loadSlidingmenu();
	}
	
	private void initData() {
		listdata = new ArrayList<String>();
		listdata.add("aa");
		listdata.add("aa");
		listdata.add("aa");
		listdata.add("aa");
		listdata.add("aa");listdata.add("aa");
		dataChanged();
	}

	private void loadSlidingmenu() {
				fragmentManager = getSupportFragmentManager();
				ft = fragmentManager.beginTransaction();
				ShopTypeFragment sma = new ShopTypeFragment();
				ft.replace(R.id.flSlidingmenu, sma);
				ft.commit();
				
				sm = getSlidingMenu();
				sm.setMode(SlidingMenu.RIGHT_OF);
				sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
				setBehindContentView(R.layout.activity_slidingmenu);

				sm.setBehindWidthRes(R.dimen.slidingmenuwidth);

//				sm.setShadowWidth(0);
				sm.setShadowWidth(100);
//				sm.setBehindOffset(80);
				sm.setBehindOffsetRes(R.dimen.slidingmenuwidth);
				sm.setFadeDegree(0.3f);
//				sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

	}
	
	public void dataChanged(){
		if(shopAdapter == null){
			shopAdapter = new ShopAdapter(this, listdata);
			plListv.setAdapter(shopAdapter);
		}
		else{
			shopAdapter.notifyDataSetChanged();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(this);
	}
}
