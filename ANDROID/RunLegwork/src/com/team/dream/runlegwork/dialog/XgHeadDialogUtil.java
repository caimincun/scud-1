package com.team.dream.runlegwork.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.team.dream.runlegwork.R;


/**
 * 修改头像
 *
 * @author yb
 */
public class XgHeadDialogUtil extends Dialog {


	private Activity context;
	private LinearLayout take_pics_layout, select_pics_layout;

	public XgHeadDialogUtil(Activity context, int theme) {
        super(context, theme);
        this.context = context;
    }

	public XgHeadDialogUtil(Activity context){
		super(context, R.style.shakeDialogStyle);
		this.context = context;
		setContentView(R.layout.xiugai_head_dialog_view);
		getWindow().getAttributes().gravity = Gravity.CENTER;
		getWindow().setBackgroundDrawable(new ColorDrawable(0));// 去除窗口透明部分显示的黑色
	    setCanceledOnTouchOutside(false);
		initViews();
	}

	/**
	 * 初始化ui
	 */
	private final void initViews() {
		take_pics_layout = (LinearLayout) this.findViewById(R.id.take_pics_layout);
		select_pics_layout = (LinearLayout) this.findViewById(R.id.select_pics_layout);
	}

	public void setOnClickListeners(android.view.View.OnClickListener mListener) {
		take_pics_layout.setOnClickListener(mListener);
		select_pics_layout.setOnClickListener(mListener);
	}
	
	public void setOnCancelListener(DialogInterface.OnCancelListener mCancel) {
		super.setOnCancelListener(mCancel);
	}
	

	public void show() {
		super.show();
	}

	/**
	 * 取消窗口显示
	 */
	public void dismiss() {
		super.dismiss();
	}

	public boolean isShowing() {
		return super.isShowing();
	}

}