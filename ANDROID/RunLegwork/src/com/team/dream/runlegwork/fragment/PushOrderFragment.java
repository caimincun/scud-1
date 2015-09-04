package com.team.dream.runlegwork.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.runlegwork.BaseFragment;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.adapter.PushOrderAdapter;
import com.team.dream.runlegwork.adapter.PushOrderAdapter.OnSetDataListener;
import com.team.dream.runlegwork.navigator.Navigator;
import com.team.dream.runlegwork.net.JsonBooleanResponseHandler;
import com.team.dream.runlegwork.net.request.CreateOrderRequest;
import com.team.dream.runlegwork.singleservice.ActivityProcessHandler;
import com.team.dream.runlegwork.utils.AppUtils;
import com.team.dream.runlegwork.utils.ToastUtils;
import com.team.dream.runlegwork.widget.MainTitileBar;

public class PushOrderFragment extends BaseFragment implements
		OnSetDataListener {

	@InjectView(R.id.mb_topbar)
	MainTitileBar mbTopbar;
	@InjectView(R.id.lv_push_order)
	ListView lvHomePage;
	@InjectView(R.id.tv_push_order)
	TextView tvPushOrder;

	private PushOrderAdapter mAdapter;
	private TextView tvType;
	private TextView tvDate;

	@Override
	protected void initializePresenter() {
		mAdapter = new PushOrderAdapter(getActivity());
	}

	public static PushOrderFragment newInstance() {
		PushOrderFragment fragment = new PushOrderFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_push_order, container,
				false);
		ButterKnife.inject(this, view);
		mbTopbar.setTitle("需求发布 ");
		mbTopbar.hideTitleRight();
		lvHomePage.setAdapter(mAdapter);
		mAdapter.setOnSetDataListener(this);
		return view;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);
	}

	@OnClick(R.id.tv_push_order)
	public void pushOrder() {
		String[] checkString = mAdapter.getData();
		String msg = AppUtils
				.CheckViewEmpty(
						getResources().getStringArray(R.array.check_order),
						checkString);
		if (msg.equals(getString(R.string.success))) {
			pushOrder(checkString);
		} else {
			ToastUtils.show(getActivity(), msg);
		}

	}

	private void pushOrder(String[] checkString) {
		CreateOrderRequest request = new CreateOrderRequest();
		request.setOrderCallScope(checkString[1]);
		request.setOrderContent(checkString[2]);
		request.setOrderLimitTime(checkString[3]);
		request.setOrderTitle(checkString[0]);
		request.setOrderMoney(checkString[5]);
		request.setOrderServiceAddress(checkString[4]);

		api.createOrder(request, new JsonBooleanResponseHandler() {

			@Override
			public void onSuccess() {
				ToastUtils.show(getActivity(), "创建订单成功");
				ActivityProcessHandler.getInstance().exit(
						ActivityProcessHandler.CREATE_ORDRER_HANDLER);
				Navigator.NavigatorToMainActivity(getActivity(), 2);
			}

			@Override
			public void onFailure(String errMsg) {
				ToastUtils.show(getActivity(), "创建订单失败");
			}
		});
	}

	@Override
	public void ChoiceNeed(View v) {
		// TODO Auto-generated method stub
		tvType = (TextView) v;
		Navigator.NavigatorToSelectOrderOrSkillActivity(getActivity());
	}

	@Override
	public void SetDate(View v) {
		// TODO Auto-generated method stub
		((TextView) v).setText("111");
		tvDate = (TextView) v;
	}
}
