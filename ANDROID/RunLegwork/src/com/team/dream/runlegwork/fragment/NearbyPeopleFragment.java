package com.team.dream.runlegwork.fragment;

import java.util.ArrayList;
import java.util.List;

import org.litepal.util.LogUtil;

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
import com.team.dream.runlegwork.activity.search.NearbyDetail;
import com.team.dream.runlegwork.adapter.search.NearbyPeoAdapter;
import com.team.dream.runlegwork.entity.NearUserInfo;
import com.team.dream.runlegwork.net.JsonObjectResponseHandler;
import com.team.dream.runlegwork.net.response.NearUserResponse;
import com.team.dream.runlegwork.utils.ToastUtils;

public class NearbyPeopleFragment extends BaseFragment implements OnRefreshListener<ListView>, OnItemClickListener {
	private final String tag = NearbyPeopleFragment.class.getSimpleName();

	private Context ctx;

	@InjectView(R.id.nearby_ptListv)
	PullToRefreshListView plListv;

	private List<NearUserInfo> list = new ArrayList<NearUserInfo>();
	private NearbyPeoAdapter nearbypeoAda;

	public static NearbyPeopleFragment newInstance() {
		NearbyPeopleFragment fragment = new NearbyPeopleFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View mainView = inflater.inflate(R.layout.activity_nearby, container,false);
		ctx = getActivity();
		ButterKnife.inject(this, mainView);
		dataChanged();
		plListv.setOnItemClickListener(this);
		initListener();
		return mainView;
	}

	private void initListener() {
		plListv.setOnRefreshListener(this);
		plListv.setMode(Mode.BOTH);
	}

	public void dataChanged() {
		if (nearbypeoAda == null) {
			nearbypeoAda = new NearbyPeoAdapter(list, ctx);
			plListv.setAdapter(nearbypeoAda);
		} else {
			plListv.setAdapter(nearbypeoAda);
		}
		plListv.onRefreshComplete();
	}
	
	public void requestData(int pageIndex,final int flag){
		api.getNserUser(pageIndex, new JsonObjectResponseHandler<NearUserResponse>() {

			@Override
			public void onSuccess(NearUserResponse response) {
				if(response.getListSucRes()==null || response.getListSucRes().size()==0){
					ToastUtils.show(ctx, "没有更多数据了");
				}
				else{
					//下拉刷新
					if(flag == 1){
						list.clear();
						list.addAll(response.getListSucRes());
					}
					else{
						list.addAll(response.getListSucRes());
					}
					
				}
				plListv.onRefreshComplete();
				dataChanged();
			}

			@Override
			public void onFailure(String errMsg) {
				plListv.onRefreshComplete();
			}
		});
	}
	
	
	@Override
	protected void initializePresenter() {
		api.getNserUser(1, new JsonObjectResponseHandler<NearUserResponse>() {

			@Override
			public void onSuccess(NearUserResponse response) {
				list.addAll(response.getListSucRes());
				if (nearbypeoAda != null) {
					nearbypeoAda.notifyDataSetChanged();
				}
			}

			@Override
			public void onFailure(String errMsg) {
				LogUtil.d(tag, errMsg);
				ToastUtils.show(ctx, errMsg+"");
			}
		});
	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(this);
	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		if (plListv.isHeaderShown()) {
			Log.d(tag, "下拉刷新");
			requestData(1,1);
		} else if (plListv.isFooterShown()) {
			int listsize = list.size();
			int pageIndex = 1;
			if(listsize>0){
				pageIndex = listsize/2+1;
			}
			requestData(pageIndex, 2);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intent = new Intent(ctx, NearbyDetail.class);
		Bundle b = new Bundle();
		b.putSerializable("userinfo", list.get(arg2));
		intent.putExtras(b);
		startActivity(intent);
	}
}
