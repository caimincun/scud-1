package com.team.dream.runlegwork.activity.requirement;

import android.os.Bundle;
import butterknife.ButterKnife;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;

public class SkillPeopleDetailActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_skillpeopledetail);
		ButterKnife.inject(this);
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(this);
	}
}
