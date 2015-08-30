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
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout.LayoutParams;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.pulltorefresh.library.PullToRefreshBase;
import com.team.dream.pulltorefresh.library.PullToRefreshBase.Mode;
import com.team.dream.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.team.dream.pulltorefresh.library.PullToRefreshListView;
import com.team.dream.runlegwork.BaseFragment;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.activity.search.NearbyDetail;
import com.team.dream.runlegwork.activity.search.SearchConditionActivity;
import com.team.dream.runlegwork.adapter.search.NearbyPeoAdapter;
import com.team.dream.runlegwork.adapter.search.SearchconditionAdapter;
import com.team.dream.runlegwork.entity.NearUserInfo;
import com.team.dream.runlegwork.net.JsonObjectResponseHandler;
import com.team.dream.runlegwork.net.response.NearUserResponse;
import com.team.dream.runlegwork.utils.ToastUtils;
import com.team.dream.runlegwork.widget.HorizontialListView;

public class NearbyPeopleDetailFragment extends BaseFragment implements OnRefreshListener<ListView>, OnItemClickListener {
	private final String tag = NearbyPeopleDetailFragment.class.getSimpleName();

	private Context ctx;

	@InjectView(R.id.nearby_ptListv)
	PullToRefreshListView plListv;

	private List<NearUserInfo> list = new ArrayList<NearUserInfo>();
	private NearbyPeoAdapter nearbypeoAda;

	private boolean isFistLoad;
	private List<String> listCondition = new ArrayList<String>();//条件
	private SearchconditionAdapter scAdapter;
	
	private String condition;
	
	public static NearbyPeopleDetailFragment newInstance() {
		NearbyPeopleDetailFragment fragment = new NearbyPeopleDetailFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View mainView = inflater.inflate(R.layout.activity_nearby, container, false);
		ctx = getActivity();
		ButterKnife.inject(this, mainView);
		dataChanged();
		plListv.setOnItemClickListener(this);
		initListener();
		condition = getArguments().getString("arg");
		return mainView;
	}

	private void initListener() {
		plListv.setOnRefreshListener(this);
		plListv.setMode(Mode.BOTH);
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
	


	/**
	 * 当第一次显示的时候才执行 获取数据的方法
	 * @see android.support.v4.app.Fragment#setUserVisibleHint(boolean) 
	 * Date :2015年7月28日
	 */
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (getUserVisibleHint()) {
			onVisible();
		} else {
			onInVisible();
		}
	}

	private void onInVisible() {
	}

	private void onVisible() {
		if (!isFistLoad) {
			getNearByUserData();
		}
		isFistLoad = true;

	}

	private void getNearByUserData() {
		api.getNserUser(0,condition, new JsonObjectResponseHandler<NearUserResponse>() {
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
				ToastUtils.show(ctx, errMsg + "");
			}
		});
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

	public void requestData(int pageIndex,String condition, final int flag) {
		api.getNserUser(pageIndex,condition, new JsonObjectResponseHandler<NearUserResponse>() {

			@Override
			public void onSuccess(NearUserResponse response) {
				if (response.getListSucRes() == null || response.getListSucRes().size() == 0) {
					ToastUtils.show(ctx, "没有更多数据了");
				} else {
					// 下拉刷新
					if (flag == 1) {
						list.clear();
						list.addAll(response.getListSucRes());
					} else {
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
			requestData(0,condition, 1);
		} else if (plListv.isFooterShown()) {
			int listsize = list.size();
			int pageIndex = 1;
			if (listsize > 0) {
				pageIndex = listsize / 2 + 1;
			}
			requestData(pageIndex,condition, 2);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intent = new Intent(ctx, NearbyDetail.class);
		Bundle b = new Bundle();
		b.putSerializable("userinfo", list.get((int)arg3));
		intent.putExtras(b);
		startActivity(intent);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}
}
