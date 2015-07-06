package com.team.dream.runlegwork.activity.search;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.pulltorefresh.library.PullToRefreshBase;
import com.team.dream.pulltorefresh.library.PullToRefreshBase.Mode;
import com.team.dream.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.team.dream.pulltorefresh.library.PullToRefreshListView;
import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.adapter.search.NearbyPeoAdapter;
import com.team.dream.runlegwork.entity.UserInfo;
import com.team.dream.runlegwork.widget.MainTitileBar;

public class NearbyPeopleActivity extends BaseActivity implements OnRefreshListener<ListView>, OnItemClickListener {
	private final String tag = NearbyPeopleActivity.class.getSimpleName();
	private Context ctx;
	@InjectView(R.id.nearby_titlebar)
	MainTitileBar mtb;
	@InjectView(R.id.nearby_ptListv)
	PullToRefreshListView plListv;
	
	private List<UserInfo> list = new ArrayList<UserInfo>();
	private NearbyPeoAdapter nearbypeoAda;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_nearby);
		ButterKnife.inject(this);
//		plListv = (PullToRefreshListView) findViewById(R.id.nearby_ptListv);
		ctx = this;
//		mtb.hideTitleRight();
		plListv.setOnItemClickListener(this);
		initListener();
		loadData();
	}
	private void initListener() {
		// TODO Auto-generated method stub
		plListv.setOnRefreshListener(this);
		plListv.setMode(Mode.BOTH);
	}
	private void loadData() {
		// TODO Auto-generated method stub
		list.clear();
		
//		list.add(new Account("张三", "sss", "sss", 1, "程序员", "fuck you!"));
		list.add(new UserInfo());
		list.add(new UserInfo());
		list.add(new UserInfo());
		list.add(new UserInfo());
		list.add(new UserInfo());
		dataChanged();
		
	}
	private void loadData1() {
		// TODO Auto-generated method stub
//		list.add(new Account("张三", "sss", "sss", 1, "程序员", "fuck you!"));
		list.add(new UserInfo());
		list.add(new UserInfo());
		list.add(new UserInfo());
		list.add(new UserInfo());
		list.add(new UserInfo());
		dataChanged();
		
	}
	public void dataChanged(){
		if(nearbypeoAda==null){
			nearbypeoAda = new NearbyPeoAdapter(list, ctx);
			plListv.setAdapter(nearbypeoAda);
		}
		else{
			nearbypeoAda.notifyDataSetChanged();
		}
		plListv.onRefreshComplete();
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ButterKnife.reset(this);
	}
	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		if(plListv.isHeaderShown()){
			loadData();
			Log.d(tag, "下拉刷新");
		}
		else if(plListv.isFooterShown()){
			loadData1();
		}
		plListv.onRefreshComplete();
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		startActivity(new Intent(ctx, NearbyDetail.class));
	}
}
