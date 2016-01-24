package com.team.dream.runlegwork.utils;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.PopupWindow;

import java.util.List;

public abstract class BasePopupWindowForListView<T> extends PopupWindow {

	protected Context mContext;
	protected View mContentView;

	protected List<T> mDatas;

	public BasePopupWindowForListView(View contentView, int width, int height, boolean focusable) {
		this(contentView, width, height, focusable, null);
	}

	public BasePopupWindowForListView(View contentView, int width, int height, boolean focusable, List<T> mDatas) {
		this(contentView, width, height, focusable, mDatas, new Object[0]);

	}

	@SuppressWarnings("deprecation")
	public BasePopupWindowForListView(View contentView, int width, int height, boolean focusable, List<T> mDatas, Object... params) {
		super(contentView, width, height, focusable);
		this.mContentView = contentView;
		this.mContext = contentView.getContext();
		if (mDatas != null) {
			this.mDatas = mDatas;
		}

		if (params != null && params.length > 0) {
			initParams(params);
		}

		setBackgroundDrawable(new BitmapDrawable());
		setFocusable(true);
		setOutsideTouchable(true);
		setTouchInterceptor(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
					dismiss();
					return true;
				}
				return false;
			}
		});

		initWidget();
		initEvents();
		init();
	}

	protected abstract void initWidget();

	protected abstract void initEvents();

	protected abstract void init();

	protected abstract void initParams(Object... params);
	
	public  View findViewById(int id){
		return mContentView.findViewById(id);
	}
	public static int  dpToPx(Context context,int dp){
		return (int) (context.getResources().getDisplayMetrics().density*dp+0.5f);
	}
}
