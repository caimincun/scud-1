package com.team.dream.runlegwork.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.adapter.MainPagerAdapter;
import com.team.dream.runlegwork.fragment.HomeFragment;
import com.team.dream.runlegwork.fragment.HomePageFragment;
import com.team.dream.runlegwork.fragment.MineFragment;
import com.team.dream.runlegwork.fragment.NearbyPeopleDetailFragment;
import com.team.dream.runlegwork.fragment.OrderFragment;
import com.team.dream.runlegwork.listener.MainPageChangerLister;
import com.team.dream.runlegwork.singleservice.Syseting;
import com.team.dream.runlegwork.utils.ToastUtils;
import com.team.dream.runlegwork.widget.MainTitileBar;
import com.team.dream.runlegwork.widget.TabSelectView;
import com.team.dream.runlegwork.widget.TabSelectView.IMenuItemOnClick;

public class MainActivity extends BaseActivity implements IMenuItemOnClick {

	public static final String KEY_POSTION = "key_postion";
	private List<Fragment> fragments = new ArrayList<Fragment>();
	private int defualtPostion;

	@InjectView(R.id.view_pager)
	ViewPager vp;
	@InjectView(R.id.tab_select_view)
	TabSelectView tsv;
	@InjectView(R.id.topbar)
	MainTitileBar mtb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_tab);
		ButterKnife.inject(this);

//		fragments.add(HomeFragment.newInstance(1));
		fragments.add(new HomePageFragment());
		fragments.add(new NearbyPeopleDetailFragment());
		fragments.add(OrderFragment.newInstance());
		fragments.add(new MineFragment());
		vp.setAdapter(new MainPagerAdapter(getSupportFragmentManager(),
				fragments));
		vp.setOnPageChangeListener(new MainPageChangerLister(tsv));
		vp.setOffscreenPageLimit(4);
		tsv.setOnMenuItemClickListener(this);
		mtb.hideTitleLeft();
		mtb.setTitle(R.string.home);

	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		defualtPostion = intent.getIntExtra(KEY_POSTION, 0);
		Log.d("TAG", "postion:"+defualtPostion);
		vp.setCurrentItem(defualtPostion);
	}

	@Override
	public void menuItemOnClick(int position) {
		vp.setCurrentItem(position, false);
		switch (position) {
		case 0:
			mtb.setTitle(R.string.home);
			break;
		case 1:
			mtb.setTitle(R.string.discover);
			break;
		case 2:
			mtb.setTitle(R.string.order);
			break;
		case 3:
			mtb.setTitle(R.string.mine);
			break;

		default:
			break;
		}
	}

	public static Intent getCallingIntent(Context context) {
		return new Intent(context, MainActivity.class);
	}

	private long exitTime = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				ToastUtils.show(this, R.string.exit_app);
				exitTime = System.currentTimeMillis();
			} else {
				// Intent intent = new Intent();
				// intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				// startActivity(intent);
				Syseting.exitApp();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
