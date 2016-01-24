package com.team.dream.runlegwork.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.runlegwork.BaseFragment;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.activity.ChatActivity;
import com.team.dream.runlegwork.activity.GoodsDetailActivity;
import com.team.dream.runlegwork.adapter.AnserOrderPersonAdapter;
import com.team.dream.runlegwork.adapter.AnserOrderPersonAdapter.onConfirmChangeListener;
import com.team.dream.runlegwork.dialog.AsyncOpteratorView;
import com.team.dream.runlegwork.entity.NearUserInfo;
import com.team.dream.runlegwork.entity.UserOrder;
import com.team.dream.runlegwork.net.JsonBooleanResponseHandler;
import com.team.dream.runlegwork.net.JsonObjectResponseHandler;
import com.team.dream.runlegwork.net.response.AcptsPersonResponse;
import com.team.dream.runlegwork.utils.ToastUtils;
import com.team.dream.runlegwork.widget.MainTitileBar;

public class OrderDetailFragment extends BaseFragment implements
		onConfirmChangeListener {
	private static final String KEY = "key";

	@InjectView(R.id.lv_order_detail)
	ListView lvOrderDetail;
	@InjectView(R.id.topbar)
	MainTitileBar topbar;

	private ViewHear hear;

	private UserOrder mOrder;
	private List<NearUserInfo> mData = new ArrayList<NearUserInfo>();
	private AnserOrderPersonAdapter adapter;

	public static OrderDetailFragment newInstance(UserOrder order) {
		OrderDetailFragment fragment = new OrderDetailFragment();
		Bundle bundle = new Bundle();
		bundle.putSerializable(KEY, order);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(
				R.layout.fragment_order_detail_and_order_person, container,
				false);
		ButterKnife.inject(this, view);
		View hearView = inflater.inflate(
				R.layout.listview_hear_item_order_detail, lvOrderDetail, false);
		hear = new ViewHear(hearView);
		lvOrderDetail.addHeaderView(hearView);
		adapter = new AnserOrderPersonAdapter(mData, getActivity(),mOrder,
				mOrder.getOrderComplteFlag() == 2);
		lvOrderDetail.setAdapter(adapter);

		adapter.setOnConfirmChangeListener(this);

		topbar.setTitle(getString(R.string.order_detail));
		topbar.hideTitleRight();

		hear.tvScope.setText(mOrder.getOrderCallScope());
		hear.tvDetail.setText(mOrder.getOrderContent());
		hear.tvAddress.setText(mOrder.getOrderServiceAddress());
		hear.tvMoney.setText(String.valueOf(mOrder.getOrderMoney()));
		hear.tvTime.setText(mOrder.getOrderLimitTime());
		return view;
	}

	@Override
	protected void initializePresenter() {
		asyncTipView = new AsyncOpteratorView(getActivity());
		asyncTipView.start(R.string.loading_data);
		mOrder = (UserOrder) getArguments().getSerializable(KEY);
		refeshData();

	}

	private void refeshData() {
		api.getAcptsPerson(mOrder.getOrderToken(),
				new JsonObjectResponseHandler<AcptsPersonResponse>() {

					@Override
					public void onSuccess(AcptsPersonResponse response) {
						mData.clear();
						mData.addAll(response.getData());
						if (adapter != null) {
							adapter.notifyDataSetChanged();
						}
						asyncTipView.finish();
					}

					@Override
					public void onFailure(String errMsg) {
						ToastUtils.show(getActivity(), "获取订单详情失败");
						asyncTipView.finish(R.string.loading_data_failed);
					}
				});
	}

	class ViewHear {
		@InjectView(R.id.tv_scope)
		TextView tvScope;
		@InjectView(R.id.tv_address)
		TextView tvAddress;
		@InjectView(R.id.tv_detial)
		TextView tvDetail;
		@InjectView(R.id.tv_money)
		TextView tvMoney;
		@InjectView(R.id.tv_time)
		TextView tvTime;

		public ViewHear(View view) {
			ButterKnife.inject(this, view);
		}

	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);
	}

	@Override
	public void onComfirmOrder(NearUserInfo userInfo) {
		asyncTipView.start(R.string.loading_data);
		api.SaveAcpt(userInfo.getUserToken(), mOrder.getOrderToken(),
				new JsonBooleanResponseHandler() {

					@Override
					public void onSuccess() {
						ToastUtils.show(getActivity(), "确认订单成功");
						refeshData();
					}

					@Override
					public void onFailure(String errMsg) {
						asyncTipView.finish(R.string.loading_data_failed);
					}
				});

	}

	@Override
	public void onSelectTalk(NearUserInfo userInfo) {
//		ToastUtils.show(getActivity(), "谈话");
		Intent intent = new Intent(getActivity(), ChatActivity.class);
		intent.putExtra("userId", userInfo.getPhoneNumber());
		startActivity(intent);

	}

	@Override
	public void onSelectCallPhone(NearUserInfo userInfo) {
		// TODO Auto-generated method stub
//		ToastUtils.show(getActivity(), "将电话");
		Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+userInfo.getPhoneNumber()));
		startActivity(intent);
	}

	@Override
	public void onConfirm() {
		api.confrimOrder(mOrder.getOrderToken(),
				new JsonBooleanResponseHandler() {

					@Override
					public void onSuccess() {
						ToastUtils.show(getActivity(), "确认成功");
					}

					@Override
					public void onFailure(String errMsg) {
						ToastUtils.show(getActivity(), "确认失败");
					}
				});
	}
}
