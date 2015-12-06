package com.team.dream.runlegwork.activity.requirement;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
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
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.adapter.requirement.RequirementAdapter;
import com.team.dream.runlegwork.entity.UserOrder;
import com.team.dream.runlegwork.fragment.LocationFragment;
import com.team.dream.runlegwork.net.JsonObjectResponseHandler;
import com.team.dream.runlegwork.net.response.RequirementResponse;
import com.team.dream.runlegwork.utils.ToastUtils;

public class RequirementByDistFragment extends LocationFragment implements OnRefreshListener<ListView> {
	@InjectView(R.id.reqbydis_ptListv)
	PullToRefreshListView ptr;
	private RequirementAdapter reqAdapter;
	private List<UserOrder> listdata = new ArrayList<UserOrder>();
	
	private int listsize,pageIndex=0;//当前数据总条数,当前访问第几页
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View mainView = inflater.inflate(R.layout.fragment_requirementbydist, container, false);
		
		ButterKnife.inject(this, mainView);
		return mainView;
	}
	 @Override 
	    public void onActivityCreated(Bundle savedInstanceState) { 
	        super.onActivityCreated(savedInstanceState); 
	        startPosition();
	        initListener();
	        
	    } 
	
	 
	 private void initListener() {
			ptr.setOnRefreshListener(this);
			ptr.setMode(Mode.BOTH);
			ptr.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					Intent intent = new Intent(getActivity(), RequirementDetailActivity.class);
					Bundle b = new Bundle();
					UserOrder order=listdata.get((int)arg3);
					b.putSerializable("userorder", order);
					intent.putExtras(b);
					startActivity(intent);
				}
			});
		}
	public void requestData(int pageIndex, final int flag){
		showProgressDialog();
		api.getRequirementList(pageIndex, new JsonObjectResponseHandler<RequirementResponse>() {
			
			@Override
			public void onSuccess(RequirementResponse response) {
				removeProgressDialog();
				if (response.getListSucRes() == null || response.getListSucRes().size() == 0) {
					ToastUtils.show(getActivity(), "没有更多数据了");
				} else {
					// 下拉刷新
					if (flag == 1) {
						listdata.clear();
						listdata.addAll(response.getListSucRes());
					} else {
						listdata.addAll(response.getListSucRes());
					}

				}
				dataChanged();
			}
			
			@Override
			public void onFailure(String errMsg) {
				removeProgressDialog();
				ptr.onRefreshComplete();
			}
		});
	}
	public void dataChanged(){
		if(reqAdapter == null){
			reqAdapter = new RequirementAdapter(getActivity(), listdata);
			ptr.setAdapter(reqAdapter);
		}
		else{
			reqAdapter.notifyDataSetChanged();
		}
		ptr.onRefreshComplete();
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(this);
	}
	
	
	
	@Override
	protected void initializePresenter() {

	}
	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		if (ptr.isHeaderShown()) {
			pageIndex = 0;
			listsize = 0;
			requestData(0, 1);
		} else if (ptr.isFooterShown()) {
			
			if(listdata.size()>listsize){
				pageIndex += 1;
				requestData(pageIndex, 2);
				listsize = listdata.size();
			}
			else{
				requestData(pageIndex, 2);
			}
			
			
//			int listsize = listdata.size();
//			int pageIndex = 1;
//			if (listsize > 0) {
//				pageIndex = listsize / 10 + 1;
//			}
//			requestData(pageIndex, 2);
		}
	}
	@Override
	public void OnCompleteLocation(boolean isLocationSuccess) {
		requestData(0, 1);
	}

}
