package com.team.dream.runlegwork.activity.account;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.tool.RegexUtil;
import com.team.dream.runlegwork.tool.Tool;
import com.team.dream.runlegwork.utils.StringUtils;
import com.team.dream.runlegwork.widget.MainTitileBar;

public class UpdatePwdActivity extends BaseActivity {
	private Context ctx;
	@InjectView(R.id.activity_updatepwd_etpwd)
	EditText edtPwd;
	@InjectView(R.id.activity_updatepwd_etnewpwd)
	EditText edtNewpwd;
	@InjectView(R.id.activity_update_topbar)
	MainTitileBar tb;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_update);
		ButterKnife.inject(this);
		ctx = this;
		tb.setTitle("修改密码");
	}
	
	public void updatePwd(){
		String oldPwd = edtPwd.getText().toString();
		String newPwd = edtNewpwd.getText().toString();
		if(StringUtils.isEmpty(oldPwd)){
			Tool.showToast(ctx, "新密码不能为空");
		}
		else if(oldPwd.length()<6){
			Tool.showToast(ctx, "密码长度不能少于6位");
		}
		else if(StringUtils.isEmpty(newPwd)){
			Tool.showToast(ctx, "确认密码不能为空");
		}
		else if(!RegexUtil.isPwdVal(oldPwd, newPwd)){
			Tool.showToast(ctx, "两次密码输入不一致");
		}
		else{
			Tool.showToast(ctx, "修改成功");
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(this);
	}
}
