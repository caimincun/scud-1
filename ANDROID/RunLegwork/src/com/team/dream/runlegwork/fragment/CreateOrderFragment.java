package com.team.dream.runlegwork.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.runlegwork.BaseFragment;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.dialog.DataPickDialogFragment;
import com.team.dream.runlegwork.interfaces.OnMyDialogClickListener;
import com.team.dream.runlegwork.navigator.Navigator;
import com.team.dream.runlegwork.net.JsonBooleanResponseHandler;
import com.team.dream.runlegwork.net.request.CreateOrderRequest;
import com.team.dream.runlegwork.singleservice.ActivityProcessHandler;
import com.team.dream.runlegwork.utils.AppUtils;
import com.team.dream.runlegwork.utils.ToastUtils;
import com.team.dream.runlegwork.widget.TopBar;

public class CreateOrderFragment extends BaseFragment implements
		OnMyDialogClickListener {
	private static final String ORDER_KEY = "order_key";
	
	@InjectView(R.id.topbar)
	TopBar topbar;
	@InjectView(R.id.et_titile)
	EditText etTitle;
	@InjectView(R.id.tv_type)
	TextView tvType;
	@InjectView(R.id.et_detail)
	EditText etDetail;
	@InjectView(R.id.et_address)
	EditText etAddress;
	@InjectView(R.id.tv_select_time)
	TextView tvSelectTime;
	@InjectView(R.id.tv_push_confirm)
	TextView tvPushConfirm;
	@InjectView(R.id.et_money)
	EditText etMoney;

	private String selectDate;
	private String orderType;
	private int postion;

	public static CreateOrderFragment newInstance(String selectNeed) {
		CreateOrderFragment fragment = new CreateOrderFragment();
		Bundle bundle = new Bundle();
		bundle.putString(ORDER_KEY, selectNeed);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_create_order, container,
				false);
		ButterKnife.inject(this, view);
		topbar.initialze("发布需求");
		tvType.setText(orderType);
		return view;
	}

	@OnClick(R.id.tv_push_confirm)
	public void createOrder() {
		String title = etTitle.getText().toString().trim();
		String type = tvType.getText().toString().trim();
		String address = etAddress.getText().toString().trim();
		String selectTime = tvSelectTime.getText().toString().trim();
		String money = etMoney.getText().toString().trim();
		String detail = etDetail.getText().toString().trim();
		String msg = AppUtils.CheckViewEmpty(
				getResources().getStringArray(R.array.check_order),
				new String[] { title, type, address, selectTime, money });
		if (msg.equals(getString(R.string.success))) {
			pushOrder(title, type, address, selectTime, money, detail);
		} else {
			ToastUtils.show(getActivity(), msg);
		}

	}

	private void pushOrder(String title, String type, String address,
			String selectTime, String money, String detail) {
		CreateOrderRequest request = new CreateOrderRequest();
		request.setOrderCallScope(String.valueOf(postion));
		request.setOrderContent(detail);
		request.setOrderLimitTime(selectTime);
		request.setOrderTitle(title);
		request.setOrderMoney(money);
		request.setOrderServiceAddress(address);

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

	@OnClick(R.id.tv_select_time)
	public void showSelectTime() {
		showDataPickerDialog();
	}

	@Override
	protected void initializePresenter() {
		orderType = getArguments().getString(ORDER_KEY);
	}

	private void showDataPickerDialog() {
		DataPickDialogFragment dataPickDialogFragment = DataPickDialogFragment
				.newInstance(selectDate);
		dataPickDialogFragment.show(getFragmentManager(), "select time");
		dataPickDialogFragment.setListener(this);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);
	}

	@Override
	public void onDialogDone(String tag, boolean cancelled, CharSequence message) {
		if (!cancelled) {
			selectDate = message.toString();
			tvSelectTime.setText(selectDate);
		}
	}
}
