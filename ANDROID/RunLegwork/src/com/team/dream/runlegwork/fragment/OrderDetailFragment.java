package com.team.dream.runlegwork.fragment;

import java.util.ArrayList;
import java.util.List;

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
import com.team.dream.runlegwork.adapter.AnserOrderPersonAdapter;
import com.team.dream.runlegwork.entity.NearUserInfo;
import com.team.dream.runlegwork.entity.UserOrder;
import com.team.dream.runlegwork.net.JsonObjectResponseHandler;
import com.team.dream.runlegwork.net.response.AcptsPersonResponse;
import com.team.dream.runlegwork.utils.AppUtils;
import com.team.dream.runlegwork.widget.TopBar;

public class OrderDetailFragment extends BaseFragment {
	private static final String KEY = "key";

	@InjectView(R.id.lv_answer_person)
	ListView lvAnswerPerson;
	@InjectView(R.id.topbar)
	TopBar topbar;
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
	@InjectView(R.id.tv_have_answer_person)
	TextView tvHaveAnswerPerson;

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
		topbar.initialze(getString(R.string.order_detail));
		tvScope.setText(mOrder.getOrderCallScope());
		tvDetail.setText(mOrder.getOrderContent());
		tvAddress.setText(mOrder.getOrderServiceAddress());
		tvMoney.setText(String.valueOf(mOrder.getOrderMoney()));
		tvTime.setText(mOrder.getOrderLimitTime());
		adapter = new AnserOrderPersonAdapter(mData, getActivity());
		lvAnswerPerson.setAdapter(adapter);
		AppUtils.setListViewHeightBasedOnChildren(lvAnswerPerson);
		return view;
	}

	@Override
	protected void initializePresenter() {
		mOrder = (UserOrder) getArguments().getSerializable(KEY);
		api.getAcptsPerson(mOrder.getOrderToken(),
				new JsonObjectResponseHandler<AcptsPersonResponse>() {

					@Override
					public void onSuccess(AcptsPersonResponse response) {
						mData.clear();
						mData.addAll(response.getData());
						if (adapter != null) {
							adapter.notifyDataSetChanged();
						}
					}

					@Override
					public void onFailure(String errMsg) {

					}
				});
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);
	}
}
