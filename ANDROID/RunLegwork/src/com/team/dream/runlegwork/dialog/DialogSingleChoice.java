package com.team.dream.runlegwork.dialog;

import com.team.dream.runlegwork.MyBaseAdapter;
import com.team.dream.runlegwork.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 文字单选弹出框
 * @author Administrator
 */
public class DialogSingleChoice extends LinearLayout{
	private static final String tag = "DialogSingleChoice";
	Context mContext;
	/**标题栏**/
	TextView tvTitle;
	/**显示菜单列表的ListView**/
	ListView lvMenuList;
	/**菜单列表适配**/
	MyBaseAdapter adapter;
	/**
	 * 设置标题
	 * @param title
	 * @return
	 */
	public DialogSingleChoice setTitleText(String title){
		if(null!=tvTitle){
			tvTitle.setText(title);
		}
		return this;
	}
	/**
	 * 设置数据源
	 * @param adapter
	 * @return
	 */
	public DialogSingleChoice setAdapter(MyBaseAdapter adapter){
		this.adapter = adapter;
		if(null!=lvMenuList){
			lvMenuList.setAdapter(this.adapter);
		}
		return this;
	}
	/**
	 * 设置菜单项点击事件
	 * @param I
	 */
	public void setOnItemClickListener(OnItemClickListener I){
		lvMenuList.setOnItemClickListener(I);
	}
	
	public DialogSingleChoice(Context context) {
		super(context);
		initFromAttributes(context, null);
	}

	public DialogSingleChoice(Context context, AttributeSet attrs) {
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
		LayoutInflater.from(context).inflate(R.layout.dialog_single_choice, this, true);
		if(null==tvTitle){
			tvTitle = (TextView)findViewById(R.id.tvTitle);
		}
		if(null==lvMenuList){
			lvMenuList = (ListView)findViewById(R.id.lvMenuList);
		}
	}
}
