package com.team.dream.runlegwork.dialog;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.team.dream.runlegwork.R;

public class DialogDefault extends Dialog {

	private Context ctx;
	private TextView tvTitle,tvLeft,tvRight;

	public DialogDefault(Context context) {
		super(context,R.style.dialog_default);
		setContentView(R.layout.dialog_default);
		setCancelable(true);
		setCanceledOnTouchOutside(true);
		initView();
	}

	private void initView() {
		tvLeft = (TextView) findViewById(R.id.tvLeft);
		tvRight = (TextView) findViewById(R.id.tvRight);
		tvTitle = (TextView) findViewById(R.id.dialog_default_title);
	}
	
	public void setLeftListener(android.view.View.OnClickListener l){
		tvLeft.setOnClickListener(l);
	}
	public void setRightListener(android.view.View.OnClickListener l){
		tvRight.setOnClickListener(l);
	}
	public void setTitle(String title){
		tvTitle.setText(title);
	}
	

}
