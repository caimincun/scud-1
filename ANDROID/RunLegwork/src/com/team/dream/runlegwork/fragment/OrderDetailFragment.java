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
import com.team.dream.runlegwork.adapter.AnserOrderPersonAdapter.onConfirmChangeListener;
import com.team.dream.runlegwork.entity.NearUserInfo;
import com.team.dream.runlegwork.entity.UserOrder;
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
				R.layout.listview_hear_item_order_detail, lvOrderDetail,
				false);
		hear=new ViewHear(hearView);
		lvOrderDetail.addHeaderView(hearView);
		adapter = new AnserOrderPersonAdapter(mData, getActivity());
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
		mOrder = (UserOrder) getArguments().getSerializable(KEY);
//		api.getAcptsPerson(mOrder.getOrderToken(),
//				new JsonObjectResponseHandler<AcptsPersonResponse>() {
//
//					@Override
//					public void onSuccess(AcptsPersonResponse response) {
//						mData.clear();
//						mData.addAll(response.getData());
//						if (adapter != null) {
//							adapter.notifyDataSetChanged();
//						}
//					}
//
//					@Override
//					public void onFailure(String errMsg) {
//						ToastUtils.show(getActivity(), "获取订单详情失败");
//					}
//				});
		
		NearUserInfo info=new NearUserInfo();
		info.setAge("22");
		info.setDistance("1.1km");
		info.setIsAccess(0);
		info.setSkillMoney(22.3);
		info.setUserInfoSex(1);
		info.setUserRealName("留言");
		NearUserInfo info1=new NearUserInfo();
		info1.setAge("22");
		info1.setDistance("1.1km");
		info1.setIsAccess(0);
		info1.setSkillMoney(22.3);
		info1.setUserInfoSex(1);
		info1.setUserRealName("留言");
		NearUserInfo info2=new NearUserInfo();
		info2.setAge("22");
		info2.setDistance("1.1km");
		info2.setIsAccess(0);
		info2.setSkillMoney(22.3);
		info2.setUserInfoSex(0);
		info2.setUserRealName("留言");
		mData.add(info);
		mData.add(info1);
		mData.add(info2);
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
		// TODO Auto-generated method stub
		ToastUtils.show(getActivity(), "确认订单");
		
	}

	@Override
	public void onSelectTalk(NearUserInfo userInfo) {
		// TODO Auto-generated method stub
		ToastUtils.show(getActivity(), "谈话");
		
	}

	@Override
	public void onSelectCallPhone(NearUserInfo userInfo) {
		// TODO Auto-generated method stub
		ToastUtils.show(getActivity(), "将电话");
	}
}
