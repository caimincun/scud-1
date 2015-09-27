package com.team.dream.runlegwork.widget;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.interfaces.OnLeftClickListener;
import com.team.dream.runlegwork.interfaces.OnRightClickListener;
import com.team.dream.runlegwork.utils.KeybordUtils;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainTitileBar extends LinearLayout {

	@InjectView(R.id.title_left)
	ImageView ivLeft;
	@InjectView(R.id.title_right)
	ImageView ivRight;
	@InjectView(R.id.title)
	TextView tvTitle;

	private Context mContext;
	private Activity act;
	private OnRightClickListener onRightClickListener;
	private OnLeftClickListener onLeftClickListener;

	/**
	 * @return the onLeftClickListener
	 */
	public OnLeftClickListener getOnLeftClickListener() {
		return onLeftClickListener;
	}

	/**
	 * @param onLeftClickListener the onLeftClickListener to set
	 */
	public void setOnLeftClickListener(OnLeftClickListener onLeftClickListener) {
		this.onLeftClickListener = onLeftClickListener;
	}

	/**
	 * @return the onRightClickListener
	 */
	public OnRightClickListener getOnRightClickListener() {
		return onRightClickListener;
	}

	/**
	 * @param onRightClickListener the onRightClickListener to set
	 */
	public void setOnRightClickListener(OnRightClickListener onRightClickListener) {
		this.onRightClickListener = onRightClickListener;
	}

	public MainTitileBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.view_main_title, this);

		mContext = context;
		ButterKnife.inject(this);

	}

	@OnClick(R.id.title_left)
	public void onLeftClick() {
		Activity activity = (Activity) mContext;
		KeybordUtils.hideKeybord(activity);
		activity.finish();
//		onLeftClickListener.onLeftClick();
	}

	@OnClick(R.id.title_right)
	public void onRightClick() {
		onRightClickListener.onRightClick();
	}

	public void finishLeft(Activity act) {
		this.act = act;
	}

	public void setTitle(int resId) {
		tvTitle.setText(resId);
	}

	public void setTitle(String title) {
		tvTitle.setText(title);
	}

	public void hideTitleRight() {
		ivRight.setVisibility(View.INVISIBLE);
	}

	public void hideTitleLeft() {
		ivLeft.setVisibility(View.INVISIBLE);
	}
	
	public void showRight(){
		ivRight.setVisibility(View.VISIBLE);
	}

}
