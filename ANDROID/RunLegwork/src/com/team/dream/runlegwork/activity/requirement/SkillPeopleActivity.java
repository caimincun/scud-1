package com.team.dream.runlegwork.activity.requirement;

import android.content.Context;
import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.pulltorefresh.library.PullToRefreshListView;
import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;

public class SkillPeopleActivity extends BaseActivity {
	private Context ctx;
	@InjectView(R.id.skillpeople_ptListv)
	PullToRefreshListView plListv;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_skillpeople);
		ButterKnife.inject(this);
		ctx = this;
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(this);
	}
}
