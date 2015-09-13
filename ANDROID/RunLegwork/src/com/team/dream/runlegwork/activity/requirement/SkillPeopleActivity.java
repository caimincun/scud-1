package com.team.dream.runlegwork.activity.requirement;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.pulltorefresh.library.PullToRefreshListView;
import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.adapter.search.SkillPeopleAdapter;
import com.team.dream.runlegwork.entity.SkillAndUser;
import com.team.dream.runlegwork.interfaces.RequestApi;
import com.team.dream.runlegwork.net.JsonObjectResponseHandler;
import com.team.dream.runlegwork.net.response.SkillpeopleDetailResponse;
import com.team.dream.runlegwork.tool.Tool;

public class SkillPeopleActivity extends BaseActivity {
	private Context ctx;
	@InjectView(R.id.skillpeople_ptListv)
	PullToRefreshListView plListv;
	
	private SkillPeopleAdapter skillPeopleAdapter;
	private List<String> list;
	List<SkillAndUser> listdata = new ArrayList<SkillAndUser>();
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_skillpeople);
		ButterKnife.inject(this);
		ctx = this;
		initListener();
		initData();
	}
	
	private void initData() {
		listdata.clear();
		api.getSkillpeopleDetail(0, "按摩", new JsonObjectResponseHandler<SkillpeopleDetailResponse>() {
			
			@Override
			public void onSuccess(SkillpeopleDetailResponse response) {
				listdata.addAll(response.getData());
				Tool.showToast(ctx, listdata.size()+"");
				dataChanged();
			}
			
			@Override
			public void onFailure(String errMsg) {
				Tool.showToast(ctx, errMsg);
			}
		});
		
		
		dataChanged();
	}
	private void initListener() {
//		plListv.setOnRefreshListener(this);
		plListv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(ctx, SkillPeopleDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("skillpeople", listdata.get(arg2-1));
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}
	
	public void dataChanged(){
		if(skillPeopleAdapter == null){
			skillPeopleAdapter = new SkillPeopleAdapter(ctx, listdata);
			plListv.setAdapter(skillPeopleAdapter);
		}
		else{
			skillPeopleAdapter.notifyDataSetChanged();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(this);
	}
}
