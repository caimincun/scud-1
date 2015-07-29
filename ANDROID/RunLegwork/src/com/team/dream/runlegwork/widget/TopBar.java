package com.team.dream.runlegwork.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.utils.KeybordUtils;

@SuppressLint("NewApi")
public class TopBar extends LinearLayout {

	private Context mContext;

	@InjectView(R.id.title)
	TextView tvTitle;
	@InjectView(R.id.back)
	ImageView ivBack;

	public TopBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_title_bar, this);

		this.mContext = context;
		ButterKnife.inject(this);
	}

	@OnClick(R.id.back)
	public void back() {
		Activity activity = (Activity) mContext;
		KeybordUtils.hideKeybord(activity);
		activity.finish();
	}

	public void hideBack() {
		ivBack.setVisibility(View.GONE);
		int paddingBottom = tvTitle.getPaddingBottom();
		int paddingLeft = tvTitle.getPaddingBottom();
		int paddingTop = tvTitle.getPaddingBottom();
		int paddingRight = paddingLeft;
		tvTitle.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
	}

	public void initialze(String title) {
		tvTitle.setText(title);
	}

	public TopBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TopBar(Context context) {
		this(context, null);
	}

}
