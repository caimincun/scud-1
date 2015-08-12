package com.team.dream.runlegwork.listener;

import com.team.dream.runlegwork.widget.TabSelectView;
import com.team.dream.runlegwork.widget.TabSelectView.IMenuItemOnClick;

import android.support.v4.view.ViewPager.OnPageChangeListener;

public class MainPageChangerLister implements OnPageChangeListener {
	TabSelectView tsv;

	public MainPageChangerLister(TabSelectView tsv) {
		this.tsv = tsv;
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		if (tsv == null)
			return;
		if (positionOffset > 0) {
			tsv.getGrView().get(position).getGrView().setIconAlpha(1 - positionOffset);
			tsv.getGrView().get(position + 1).getGrView().setIconAlpha(positionOffset);

		}
	}

	@Override
	public void onPageSelected(int arg0) {
		
	}
	

}
