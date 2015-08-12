package com.team.dream.runlegwork.widget;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.team.dream.runlegwork.R;

@SuppressLint("NewApi")
public class TabSelectView extends LinearLayout {

	private Context mContext;
	private List<MenuItem> mTitle;

	private LayoutInflater inflater;
	private List<ViewHoler> holers = new ArrayList<TabSelectView.ViewHoler>();

	private int[] resTitle = { R.drawable.tab_bt_home, R.drawable.tab_bt_nearby, R.drawable.tab_bt_my, R.drawable.tab_bt_more };

	public TabSelectView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.mContext = context;
		inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.setOrientation(LinearLayout.HORIZONTAL);
		List<MenuItem> mData = new ArrayList<TabSelectView.MenuItem>();
		mData.add(new TabSelectView.MenuItem(0, getResources().getString(R.string.home), resTitle[0]));
		mData.add(new TabSelectView.MenuItem(1, getResources().getString(R.string.discover), resTitle[1]));
		mData.add(new TabSelectView.MenuItem(2, getResources().getString(R.string.order), resTitle[3]));
		mData.add(new TabSelectView.MenuItem(3, getResources().getString(R.string.my), resTitle[2]));
		initialize(mData);
	}

	public TabSelectView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TabSelectView(Context context) {
		this(context, null);
	}

	public void initialize(List<MenuItem> mTitleData) {
		if (mTitleData.isEmpty() || mTitleData == null) {
			return;
		}
		this.mTitle = mTitleData;

		cretateTab();
	}

	private void cretateTab() {

		for (int i = 0; i < mTitle.size(); i++) {
			MenuItem item = mTitle.get(i);
			ViewHoler holer = new ViewHoler();
			GradientColorView grView = (GradientColorView) inflater.inflate(R.layout.view_tab_select, this, false);
			grView.initialize(item.title, item.resId);
			grView.setTag(item.postion);
			holer.grView = grView;
			holer.postion = i;
			if (i == 0) {
				holer.isSelect = true;
			} else {
				holer.isSelect = false;
			}
			holer.changeMenuStatus(i);
			holers.add(holer);
			grView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Object obj = v.getTag();
					int postion = 0;
					try {
						postion = Integer.valueOf(obj.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}

					menuChoice(postion);
					if (onMenuItemClickListener != null)
						onMenuItemClickListener.menuItemOnClick(postion);
				}
			});

			this.addView(grView);
		}

	}

	public List<ViewHoler> getGrView() {
		return holers;
	}

	public void menuChoice(int choicePosition) {
		for (int i = 0; i < holers.size(); i++) {
			ViewHoler hold = holers.get(i);
			if (choicePosition == i) {
				hold.isSelect = true;
			} else {
				hold.isSelect = false;
			}
			hold.changeMenuStatus(i);
		}
	}

	public class ViewHoler {
		GradientColorView grView;
		int postion;
		boolean isSelect;

		public GradientColorView getGrView() {
			return grView;
		}

		public void changeMenuStatus(int position) {
			if (isSelect) {
				grView.setIconAlpha(1.0f);
			} else {
				grView.setIconAlpha(0.0f);
			}
		}

	}

	class MenuItem {
		int postion;
		String title;
		int resId;

		public MenuItem(int postion, String title, int resId) {
			super();
			this.postion = postion;
			this.title = title;
			this.resId = resId;
		}

	}

	private IMenuItemOnClick onMenuItemClickListener;

	public void setOnMenuItemClickListener(IMenuItemOnClick onMenuItemClickListener) {
		this.onMenuItemClickListener = onMenuItemClickListener;
	}

	public interface IMenuItemOnClick {

		void menuItemOnClick(int position);

	}
}
