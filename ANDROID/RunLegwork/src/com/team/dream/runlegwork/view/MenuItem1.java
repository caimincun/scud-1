package com.team.dream.runlegwork.view;

import java.lang.ref.SoftReference;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.team.dream.runlegwork.R;


public class MenuItem1 extends RelativeLayout {
	private SoftReference<Context> mContext = null;
	private TextView tvLeft,tvRight;
	private CharSequence tvLeftText,tvRightText;
	public MenuItem1(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mContext = new SoftReference<Context>(context);
		LayoutInflater.from(context).inflate(R.layout.menu_item1, this, true);
		if (null != attrs) {
			if (null != mContext) {
				TypedArray a = mContext.get().obtainStyledAttributes(attrs, R.styleable.MenuItem1);
				for (int i = 0; i < a.getIndexCount(); i++) {
					int index = a.getIndex(i);
					switch (index) {
					case R.styleable.MenuItem1_tvLeftText:
						tvLeftText = a.getText(index);
						break;
					case R.styleable.MenuItem1_tvRightText:
						tvRightText = a.getText(index);
						break;
					}
				}
				a.recycle();
			}
		}
	}
	
	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
		initView();
		tvLeft.setText(tvLeftText);
		tvRight.setText(tvRightText);
	}
	
	private void initView(){
		tvLeft = (TextView)findViewById(R.id.tvLeft);
		tvRight = (TextView)findViewById(R.id.tvRight);
	}
	/**
	 * 获取右边文本框内容
	 * @return
	 */
	public String getRightText(){
		return tvRight.getText().toString();
	}
	/**
	 * 给右边文本框赋值
	 * @param str
	 */
	public void setRightText(String str){
		tvRight.setText(str);
	}
}
