package com.team.dream.runlegwork.widget;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.runlegwork.R;

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
	
	private Activity act;

	public MainTitileBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.view_main_title, this);

		ButterKnife.inject(this);

	}

	@OnClick(R.id.title_left)
	public void onLeftClick() {
		if(act!=null){
			act.finish();
		}
	}

	@OnClick(R.id.title_right)
	public void onRightClick() {
		
	}
	
	public void finishLeft(Activity act){
		this.act = act;
	}

	public void setTitle(int resId) {
		tvTitle.setText(resId);
	}
	public void setTitle(String title) {
		tvTitle.setText(title);
	}
	public void hideTitleRight(){
		ivRight.setVisibility(View.INVISIBLE);
	}
	public void hideTitleLeft(){
		ivLeft.setVisibility(View.INVISIBLE);
	}


}
