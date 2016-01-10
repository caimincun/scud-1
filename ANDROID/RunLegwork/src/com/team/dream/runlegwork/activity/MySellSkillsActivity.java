package com.team.dream.runlegwork.activity;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.adapter.MysellSkillsAdapter;
import com.team.dream.runlegwork.entity.Skill;
import com.team.dream.runlegwork.navigator.Navigator;
import com.team.dream.runlegwork.net.JsonObjectResponseHandler;
import com.team.dream.runlegwork.net.response.SkillListResponse;
import com.team.dream.runlegwork.singleservice.AccountManager;
import com.team.dream.runlegwork.tool.Tool;
import com.team.dream.runlegwork.widget.MainTitileBar;

public class MySellSkillsActivity extends BaseActivity {
	private Context ctx;
	@InjectView(R.id.mysellskill_topbar)
	MainTitileBar mtb;
	@InjectView(R.id.mysellskill_listv)
	ListView listv;
	@InjectView(R.id.mysellskill_tvSell)
	TextView tvSell;
	
	
	private List<Skill> listdata;
	
	private MysellSkillsAdapter mysellSkillsAdapter;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_mysellskill);
		ctx = this;
		ButterKnife.inject(this);
		mtb.setTitle("我出售的技能");
		requestData();
	}
	
	private void requestData() {
		showProgressDialog();
		api.getSkillList(AccountManager.getInstance().getUserToken(),new JsonObjectResponseHandler<SkillListResponse>() {
			
			@Override
			public void onSuccess(SkillListResponse response) {
				removeProgressDialog();
				listdata = response.getData();
				dataChanged();
			}
			
			@Override
			public void onFailure(String errMsg) {
				removeProgressDialog();
				Tool.showToast(ctx, errMsg);
			}
		});
	}

	public void dataChanged(){
		if(mysellSkillsAdapter == null){
			mysellSkillsAdapter = new MysellSkillsAdapter(ctx, listdata);
			listv.setAdapter(mysellSkillsAdapter);
		}
		else{
			mysellSkillsAdapter.notifyDataSetChanged();
		}
	}
	@OnClick(R.id.mysellskill_tvSell)
	public void toSell(){
		Navigator.NavigatorToPushSkillActivity(this);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(this);
	}
	
}
