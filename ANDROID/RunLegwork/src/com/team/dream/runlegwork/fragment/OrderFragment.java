package com.team.dream.runlegwork.fragment;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;

import com.team.dream.runlegwork.BaseFragment;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.adapter.UserOrderAdapter;
import com.team.dream.runlegwork.dialog.DialogDefault;
import com.team.dream.runlegwork.entity.UserOrder;
import com.team.dream.runlegwork.navigator.Navigator;
import com.team.dream.runlegwork.net.JsonBooleanResponseHandler;
import com.team.dream.runlegwork.net.JsonObjectResponseHandler;
import com.team.dream.runlegwork.net.response.OrderListResponse;
import com.team.dream.runlegwork.tool.Tool;

public class OrderFragment extends BaseFragment {

	@InjectView(R.id.lv_order)
	ListView lvOrder;
	@InjectView(R.id.rg_tab)
	RadioGroup rgTab;
	@InjectView(R.id.rb_ready_on)
	RadioButton rbReadyOn;

	private boolean isFistLoad;
	private boolean isFistLoadSuccess;
	private UserOrderAdapter adapter;

	private List<UserOrder> mData = new ArrayList<UserOrder>();
	private List<UserOrder> mReadyOnData = new ArrayList<UserOrder>();
	private List<UserOrder> mComplateData = new ArrayList<UserOrder>();

	DialogDefault dialogDefault;
	private int mPosition;

	public static OrderFragment newInstance() {
		OrderFragment fragment = new OrderFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_order, container, false);
		ButterKnife.inject(this, view);

		adapter = new UserOrderAdapter(getActivity(), mData);
		lvOrder.setAdapter(adapter);
		rgTab.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb_ready_on:
					mData.clear();
					mData.addAll(mReadyOnData);
					adapter.notifyDataSetChanged();
					break;
				case R.id.rb_complate:
					mData.clear();
					mData.addAll(mComplateData);
					adapter.notifyDataSetChanged();
					break;
				}
			}
		});
		rgTab.check(rbReadyOn.getId());

		initDialog();

		lvOrder.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// dialogDefault.show();
				dialogDefault.show();
				dialogDefault.getWindow().setBackgroundDrawable(
						new ColorDrawable(0));
				mPosition = arg2;
				return true;
			}
		});
		return view;
	}

	private void initDialog() {
		dialogDefault = new DialogDefault(getActivity());
		dialogDefault.setTitle("你确定要删除该需求吗？");
		dialogDefault.setLeftListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				dialogDefault.cancel();
			}
		});
		dialogDefault.setRightListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				dialogDefault.cancel();
				showProgressDialog();
				api.deleteOrder(mData.get(mPosition).getOrderToken(),
						new JsonBooleanResponseHandler() {

							@Override
							public void onSuccess() {
								Tool.showToast(getActivity(), "删除成功");
								getUserOrder();
								removeProgressDialog();
							}

							@Override
							public void onFailure(String errMsg) {
								Tool.showToast(getActivity(), errMsg);
								removeProgressDialog();
							}
						});
			}
		});
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
				mReadyOnData.clear();
				mReadyOnData.addAll(response.getData());
				if (!isFistLoadSuccess && adapter != null) {

				}
				mData.clear();
				mData.addAll(mReadyOnData);
				adapter.notifyDataSetChanged();
				isFistLoadSuccess = true;
				changeData();
			}

			@Override
			public void onFailure(String errMsg) {

			}
		});

		api.getComplateOrderList(new JsonObjectResponseHandler<OrderListResponse>() {

			@Override
			public void onSuccess(OrderListResponse response) {
				mComplateData.clear();
				mComplateData.addAll(response.getData());
				mData.addAll(mComplateData);
				adapter.notifyDataSetChanged();
				isFistLoadSuccess = true;
				changeData();
			}

			@Override
			public void onFailure(String errMsg) {

			}
		});
		
	}

	private void changeData() {
		switch (rgTab.getCheckedRadioButtonId()) {
		case R.id.rb_ready_on:
			mData.clear();
			mData.addAll(mReadyOnData);
			adapter.notifyDataSetChanged();
			break;
		case R.id.rb_complate:
			mData.clear();
			mData.addAll(mComplateData);
			adapter.notifyDataSetChanged();
			break;
		}
	}

	@Override
	protected void initializePresenter() {

	}

	@Override
	public void onResume() {
		super.onResume();
		// if (!isFistLoad)
		// return;
		getUserOrder();
	}

}
