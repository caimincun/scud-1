package com.team.dream.runlegwork.dialog;

import com.team.dream.runlegwork.R;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 内容编辑Dialog主View
 * @author Administrator
 */
public class DialogTextEdit extends LinearLayout{
	final String tag = "DialogTextEdit";
	Context mContext;
	/**左键显示的内容**/
	CharSequence leftBtnContent="";
	/**右键显示的内容**/
	CharSequence rightBtnContent="";
	/**标题**/
	TextView tvTitle;
	/**输入框**/
	EditText etInput;
	/**左侧按钮**/
	TextView tvLeft;
	/**右侧铵钮**/
	TextView tvRight;
	
	/**
	 * 设置type
	 * @param title
	 * @return
	 */
	public DialogTextEdit setInputType(int type){
		if(null!=tvTitle){
			etInput.setInputType(type);
		}
		return this;
	}
	/**
	 * 设置标题内容
	 * @param title
	 * @return
	 */
	public DialogTextEdit setTitleText(CharSequence title){
		if(null!=tvTitle){
			tvTitle.setText(title);
		}
		return this;
	}
	/**
	 * 设置输入框提示文字
	 * @param etHint
	 * @return
	 */
	public DialogTextEdit setEditTextHint(CharSequence etHint){
		if(null!=etInput){
			etInput.setHint(etHint);
		}
		return this;
	}
	/**
	 * 设置输入显示内容
	 * @param etContent
	 * @return
	 */
	public DialogTextEdit setEditTextContent(CharSequence etContent){
		if(null!=etInput){
			etInput.setText(etContent);
		}
		return this;
	}
	/**
	 * 设置左边按钮文字
	 * @param leftBtnContent
	 * @return
	 */
	public DialogTextEdit setLeftBtnContent(CharSequence leftBtnContent){
		this.leftBtnContent = leftBtnContent;
		if(null!=tvLeft && null!=leftBtnContent){
			tvLeft.setText(leftBtnContent);
		}
		return this;
	}
	/**
	 * 设置右边按钮文字
	 * @param leftBtnContent
	 * @return
	 */
	public DialogTextEdit setRightBtnContent(CharSequence rightBtnContent){
		this.rightBtnContent = rightBtnContent;
		if(null!=tvRight && null!=rightBtnContent){
			tvRight.setText(rightBtnContent);
		}
		return this;
	}
	/**
	 * 设置左侧按钮点击事件
	 * @param I
	 * @return
	 */
	public DialogTextEdit setLeftBtnOnClickListener(OnClickListener I){
		if(null!=tvLeft){
			tvLeft.setOnClickListener(I);
		}
		return this;
	}
	/**
	 * 设置右侧按钮点击事件
	 * @param I
	 * @return
	 */
	public DialogTextEdit setRightBtnOnClickListener(OnClickListener I){
		if(null!=tvRight){
			tvRight.setOnClickListener(I);
		}
		return this;
	}
	/**
	 * 获取输入框内容
	 * @return
	 */
	public String getEditContent(){
		if(null!=etInput){
			return etInput.getText().toString();
		}
		return null;
	}
	/**
	 * 设置输入框是否是单行输入
	 * @param isSingleLine
	 * @return
	 */
	public DialogTextEdit setSingleLine(boolean isSingleLine){
		if(null!=etInput){
			etInput.setSingleLine(isSingleLine);
		}
		return this;
	}
	
	public DialogTextEdit(Context context) {
		super(context);
		initFromAttributes(context, null);
	}

	public DialogTextEdit(Context context, AttributeSet attrs) {
		super(context, attrs);
		initFromAttributes(context, attrs);
	}
	/**
	 * 获取自定义属性值
	 * @param context
	 * @param attrs
	 */
	private void initFromAttributes(Context context, AttributeSet attrs) {
		mContext = context;
		LayoutInflater.from(context).inflate(R.layout.dialog_text_edit, this, true);
		if(null==tvTitle){
			tvTitle = (TextView)findViewById(R.id.tvTitle);
		}
		if(null==etInput){
			etInput = (EditText)findViewById(R.id.etInput);
		}
		if(null==tvLeft){
			tvLeft = (TextView)findViewById(R.id.tvLeft);
		}
		if(null==tvRight){
			tvRight = (TextView)findViewById(R.id.tvRight);
		}
	}
	/**
	 * 返回当前得到焦点的View
	 * @return
	 */
	public View getFocusView(){
		return etInput;
	}
}