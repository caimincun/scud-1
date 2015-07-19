package com.team.dream.runlegwork.activity.search;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.pulltorefresh.library.PullToRefreshBase;
import com.team.dream.pulltorefresh.library.PullToRefreshBase.Mode;
import com.team.dream.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.team.dream.pulltorefresh.library.PullToRefreshListView;
import com.team.dream.runlegwork.BaseFragment;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.adapter.search.NearbyPeoAdapter;
import com.team.dream.runlegwork.entity.UserInfo;
import com.team.dream.runlegwork.widget.MainTitileBar;

public class NearbyPeopleFragment extends BaseFragment implements OnRefreshListener<ListView>, OnItemClickListener {
	private final String tag = NearbyPeopleFragment.class.getSimpleName();
	private View mainView;
	private Context ctx;
//	@InjectView(R.id.nearby_titlebar)
//	MainTitileBar mtb;
	@InjectView(R.id.nearby_ptListv)
	PullToRefreshListView plListv;
	
	
	private List<UserInfo> list = new ArrayList<UserInfo>();
	private NearbyPeoAdapter nearbypeoAda;
//	@Override
//	public void onCreate(Bundle arg0) {
//		super.onCreate(arg0);
//		setContentView(R.layout.activity_nearby);
//		ButterKnife.inject(this);
////		plListv = (PullToRefreshListView) findViewById(R.id.nearby_ptListv);
//		ctx = this;
////		mtb.hideTitleRight();
//		plListv.setOnItemClickListener(this);
//		initListener();
//		loadData();
//	}
//	private void initListener() {
//		plListv.setOnRefreshListener(this);
//		plListv.setMode(Mode.BOTH);
//	}
	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container,  Bundle savedInstanceState) {
		
		if(mainView ==null){
			mainView = inflater.inflate(R.layout.activity_nearby, null);
		}
		ViewGroup parent = (ViewGroup)mainView.getParent();
		if(null!=parent){
			parent.removeView(mainView);
		}
		ctx = getActivity();
		ButterKnife.inject(this, mainView);
		return mainView;
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
//	mtb.hideTitleRight();
	plListv.setOnItemClickListener(this);
	initListener();
	loadData();
	}
	private void initListener() {
	plListv.setOnRefreshListener(this);
	plListv.setMode(Mode.BOTH);
}
	private void loadData() {
		
//		list.add(new Account("张三", "sss", "sss", 1, "程序员", "fuck you!"));
		list.add(new UserInfo());
		list.add(new UserInfo());
		list.add(new UserInfo());
		list.add(new UserInfo());
		list.add(new UserInfo());
		dataChanged();
		
	}
	private void loadData1() {
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
	public void onStart() {
		super.onStart();
	}
	@Override
	public void onResume() {
		super.onResume();
	}
	
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(this);
	}
	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
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
		startActivity(new Intent(ctx, NearbyDetail.class));
	}
	@Override
	protected void initializePresenter() {
		
	}
}
