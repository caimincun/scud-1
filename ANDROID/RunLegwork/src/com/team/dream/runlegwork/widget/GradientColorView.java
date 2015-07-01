package com.team.dream.runlegwork.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.team.dream.runlegwork.R;

public class GradientColorView extends View {

	private Canvas mCanvas;
	private Paint mPaint;

	private Bitmap iconBitmap;
	private Paint paint	 = new Paint();
	private int mColor = 0xFF45C01A;
	private float mAlpha = 0f;
	private Bitmap mBitmap;
	private Rect iconDrawRange = new Rect();
	private String text = "home";
	private int textSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics());;
	private Rect bunds = new Rect();

	public GradientColorView(Context context) {
		super(context);
	}

	public GradientColorView(Context context, AttributeSet attrs) {
		super(context, attrs);

		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.GradientView);

		int n = a.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = a.getIndex(i);
			switch (attr) {
			case R.styleable.GradientView_icon:
				BitmapDrawable bitmapDrawable = (BitmapDrawable) a.getDrawable(attr);
				iconBitmap = bitmapDrawable.getBitmap();
				break;
			case R.styleable.GradientView_text:
				text = a.getString(attr);
				break;
			case R.styleable.GradientView_color:
				mColor = a.getColor(attr, 0x45C01A);
				break;
			case R.styleable.GradientView_text_size:
				textSize = (int) a.getDimension(attr, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
				break;
			}
		}
		a.recycle();

		
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		paint.setTextSize(textSize);
		paint.setColor(0xff555555);
		// 获取bunds
		paint.getTextBounds(text, 0, text.length(), bunds);
		// 得到绘制icon的宽
		int bitmapWidth = iconBitmap.getWidth();

		int left = getMeasuredWidth() / 2 - bitmapWidth / 2;
		int top = (getMeasuredHeight() - bunds.height()) / 2 - bitmapWidth / 2;
		// 设置icon的绘制范围

		iconDrawRange.left = left;
		iconDrawRange.top = top;
		iconDrawRange.right = left + bitmapWidth;
		iconDrawRange.bottom = top + bitmapWidth;

	}

	@Override
	protected void onDraw(Canvas canvas) {

		int alpha = (int) Math.ceil(255 * mAlpha);

		canvas.drawBitmap(iconBitmap, null, iconDrawRange, null);
		setupTargetBitmap(alpha);
		drawSourceText(canvas, alpha);
		drawTargetText(canvas, alpha);
		canvas.drawBitmap(mBitmap, 0, 0, null);
	}

	private void drawSourceText(Canvas canvas, int alpha) {
		paint.setTextSize(textSize);
		paint.setColor(0xff333333);
		paint.setAlpha(255 - alpha);
		canvas.drawText(text, iconDrawRange.left + iconDrawRange.width() / 2 - bunds.width() / 2, iconDrawRange.bottom + bunds.height(), paint);

	}

	private void drawTargetText(Canvas canvas, int alpha) {
		paint.setColor(mColor);
		paint.setAlpha(alpha);
		canvas.drawText(text, iconDrawRange.left + iconDrawRange.width() / 2 - bunds.width() / 2, iconDrawRange.bottom + bunds.height(), paint);
	}

	private void setupTargetBitmap(int alpha) {

		int width = getMeasuredWidth();
		int height = getMeasuredHeight();
//		Log.d("TAG", "width:" + width + " height:" + height + " Rect:top" + iconDrawRange.top + " Rect:right" + iconDrawRange.right + " buttom:" + iconDrawRange.bottom + " left:" + iconDrawRange.left);
		mBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		mCanvas = new Canvas(mBitmap);
		mPaint = new Paint();
		mPaint.setColor(mColor);
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setAlpha(alpha);
		mCanvas.drawRect(iconDrawRange, mPaint);
		mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
		mPaint.setAlpha(255);
		mCanvas.drawBitmap(iconBitmap, null, iconDrawRange, mPaint);

	}

	public void setIconAlpha(float alpha) {
		this.mAlpha = alpha;
		invalidateView();
	}

	private void invalidateView() {
		if (Looper.getMainLooper() == Looper.myLooper()) {
			invalidate();
		} else {
			postInvalidate();
		}
	}

	public void setIconColor(int color) {
		this.mColor = color;
	}

	public void initialize(String text, int resId) {
		this.text = text;
		this.iconBitmap = BitmapFactory.decodeResource(getResources(), resId);
		if (iconDrawRange != null)
			invalidateView();
	}

	public void setIcon(int resId) {
		this.iconBitmap = BitmapFactory.decodeResource(getResources(), resId);
		if (iconDrawRange != null)
			invalidateView();
	}

	public void setIcon(Bitmap iconBitmap) {
		this.iconBitmap = iconBitmap;
		if (iconDrawRange != null)
			invalidateView();
	}

	private static final String INSTANCE_STATE = "instance_state";
	private static final String STATE_ALPHA = "state_alpha";

	@Override
	protected Parcelable onSaveInstanceState() {
		Bundle bundle = new Bundle();
		bundle.putParcelable(INSTANCE_STATE, super.onSaveInstanceState());
		bundle.putFloat(STATE_ALPHA, mAlpha);
		return bundle;
	}

	@Override
	protected void onRestoreInstanceState(Parcelable state) {
		if (state instanceof Bundle) {
			Bundle bundle = (Bundle) state;
			mAlpha = bundle.getFloat(STATE_ALPHA);
			super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATE));
		} else {
			super.onRestoreInstanceState(state);
		}

	}

}
