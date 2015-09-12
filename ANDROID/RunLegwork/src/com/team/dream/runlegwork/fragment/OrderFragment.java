package com.team.dream.runlegwork.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;

import com.team.dream.runlegwork.BaseFragment;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.adapter.UserOrderAdapter;
import com.team.dream.runlegwork.entity.UserOrder;
import com.team.dream.runlegwork.navigator.Navigator;
import com.team.dream.runlegwork.net.JsonObjectResponseHandler;
import com.team.dream.runlegwork.net.response.OrderListResponse;

public class OrderFragment extends BaseFragment {

	@InjectView(R.id.lv_order)
	ListView lvOrder;
	@InjectView(R.id.rg_tab)
	RadioGroup rgTab;
	@InjectView(R.id.rb_ready_on)
	RadioButton rbReadyOn;

	private boolean isFistLoad;
	private UserOrderAdapter adapter;

	private List<UserOrder> mData = new ArrayList<UserOrder>();

	public static OrderFragment newInstance() {
		OrderFragment fragment = new OrderFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_order, container, false);
		ButterKnife.inject(this, view);
		rgTab.check(rbReadyOn.getId());
		adapter = new UserOrderAdapter(getActivity(), mData);
		lvOrder.setAdapter(adapter);
		return view;
	}

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
		// TODO Auto-generated method stub
	}

	private void onVisible() {
		if (!isFistLoad) {
			getUserOrder();
		}
		isFistLoad = true;

	}

	@OnItemClick(R.id.lv_order)
	public void orderSelect(int postion) {
		UserOrder order = mData.get(postion);
		Navigator.NavigatorToOrderDetailActivity(getActivity(), order);
	}

	private void getUserOrder() {
		api.getOrderList(new JsonObjectResponseHandler<OrderListResponse>() {

			@Override
			public void onSuccess(OrderListResponse response) {
				mData.clear();
				mData.addAll(response.getData());
				adapter.notifyDataSetChanged();
			}

			@Override
			public void onFailure(String errMsg) {

			}
		});
	}

	@Override
	protected void initializePresenter() {

	}

	@Override
	public void onResume() {
		super.onResume();
		if (!isFistLoad)
			return;
		getUserOrder();
	}

}
