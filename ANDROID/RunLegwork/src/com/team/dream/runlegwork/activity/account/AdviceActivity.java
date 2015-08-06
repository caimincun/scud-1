package com.team.dream.runlegwork.activity.account;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.tool.Tool;
import com.team.dream.runlegwork.utils.StringUtils;
import com.team.dream.runlegwork.utils.ToastUtils;
import com.team.dream.runlegwork.widget.MainTitileBar;

/**
 * @author cral 
 * 问题反馈及建议
 */
public class AdviceActivity extends BaseActivity{
	Context ctx;
	
	@InjectView(R.id.activity_advice_edtTitle)
	EditText edtTitle;
	@InjectView(R.id.activity_advice_edtContent)
	EditText edtContent;
	@InjectView(R.id.advice_tvCommit)
	TextView tvCommit;
	@InjectView(R.id.activity_advice_topbar)
	MainTitileBar tb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_advice);
		ButterKnife.inject(this);
		ctx = this;
		
		loadTitle();
	}
	private void loadTitle() {
		tb.setTitle("意见反馈");
		tb.hideTitleRight();
		tb.finishLeft(this);
	}
	@Override
	protected void onStart() {
		super.onStart();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(this);
	}
	
	@OnClick(R.id.advice_tvCommit)
	public void commitAdvice() {
		String title = edtTitle.getText().toString();
		String content = edtContent.getText().toString();
		if(StringUtils.isEmpty(title)){
			Tool.showToast(ctx, "标题不能为空");
		}
		else if(StringUtils.isEmpty(content)){
			Tool.showToast(ctx, "内容不能为空");
		}
		else{
			ToastUtils.show(ctx, "意见已经成功提交");
			finish();
		}
	}
}
