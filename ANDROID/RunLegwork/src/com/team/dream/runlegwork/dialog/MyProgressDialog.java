package com.team.dream.runlegwork.dialog;


import com.team.dream.runlegwork.R;

import android.app.Dialog;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MyProgressDialog extends Dialog {
	private ImageView	mImageView;
	private Context ctx;
	public MyProgressDialog(Context context) {
		super(context, R.style.iphone_progress_dialog);
		setContentView(R.layout.dialog_myprogress);
		ctx = context;
		setCancelable(true);
		setCanceledOnTouchOutside(false);
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		mImageView = (ImageView) findViewById(R.id.progress_dialog_img);
		Animation anim = AnimationUtils.loadAnimation(ctx, R.anim.progressbar);
		mImageView.startAnimation(anim);
	}

}
