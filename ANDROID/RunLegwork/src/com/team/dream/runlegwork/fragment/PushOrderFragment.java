package com.team.dream.runlegwork.fragment;

import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.runlegwork.BaseFragment;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.activity.SelectOrderOrSkillActvity;
import com.team.dream.runlegwork.adapter.PushOrderAdapter;
import com.team.dream.runlegwork.adapter.PushOrderAdapter.OnSetDataListener;
import com.team.dream.runlegwork.dialog.DataPickDialogFragment;
import com.team.dream.runlegwork.entity.ShowTimeLine;
import com.team.dream.runlegwork.interfaces.OnMyDialogClickListener;
import com.team.dream.runlegwork.navigator.Navigator;
import com.team.dream.runlegwork.net.JsonBooleanResponseHandler;
import com.team.dream.runlegwork.net.request.CreateOrderRequest;
import com.team.dream.runlegwork.singleservice.ActivityProcessHandler;
import com.team.dream.runlegwork.utils.AppUtils;
import com.team.dream.runlegwork.utils.ToastUtils;
import com.team.dream.runlegwork.widget.MainTitileBar;

public class PushOrderFragment extends BaseFragment implements
		OnSetDataListener, OnMyDialogClickListener {

	@InjectView(R.id.mb_topbar)
	MainTitileBar mbTopbar;
	@InjectView(R.id.lv_push_order)
	ListView lvHomePage;
	@InjectView(R.id.tv_push_order)
	TextView tvPushOrder;
	@InjectView(R.id.rl_root_view)
	RelativeLayout rlRootView;

	private PushOrderAdapter mAdapter;
	private TextView tvType;
	private TextView tvDate;

	private String selectDate;

	@Override
	protected void initializePresenter() {
		mAdapter = new PushOrderAdapter(getActivity());

	}

	public static PushOrderFragment newInstance() {
		PushOrderFragment fragment = new PushOrderFragment();
		return fragment;
	}

	@SuppressLint("NewApi")
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

		rlRootView.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						int screenHeight = rlRootView.getRootView().getHeight();
						int myHeight = rlRootView.getHeight();
						int heightDiff = screenHeight - myHeight;
						Log.e("onGlobalLayout", "screenHeight=" + screenHeight);
						Log.e("onGlobalLayout", "myHeight=" + myHeight);
						int stateBarHeight = AppUtils.dip2px(getActivity(),
								AppUtils.getStatusBarHeight(getActivity()));

						if (heightDiff > stateBarHeight) {
							tvPushOrder.setVisibility(View.GONE);
						} else {
							tvPushOrder.setVisibility(View.VISIBLE);
						}

					}
				});
		return view;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);
	}

	@OnClick(R.id.tv_push_order)
	public void pushOrder() {
		String[] checkString = { getInputData(0), getInputData(1),
				getInputData(2), getInputData(3), getInputData(4),
				getInputData(5) };
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

	private String getInputData(int i) {
		HashMap<String, ShowTimeLine> mItem = mAdapter.getDataMap();
		return mItem.get(mAdapter.getData()[i]).getInputData();
	}

	private void pushOrder(String[] checkString) {
		CreateOrderRequest request = new CreateOrderRequest();
		request.setOrderCallScope(checkString[1]);
		request.setOrderContent(checkString[2]);
		request.setOrderLimitTime(checkString[3]);
		request.setOrderTitle(checkString[0]);
		request.setOrderMoney(checkString[5]);
		request.setOrderServiceAddress(checkString[4]);
		showProgressDialog();
		api.createOrder(request, new JsonBooleanResponseHandler() {

			@Override
			public void onSuccess() {
				removeProgressDialog();
				ToastUtils.show(getActivity(), "创建订单成功");
				ActivityProcessHandler.getInstance().exit(
						ActivityProcessHandler.CREATE_ORDRER_HANDLER);
				Navigator.NavigatorToMainActivity(getActivity(), 2);
			}

			@Override
			public void onFailure(String errMsg) {
				removeProgressDialog();
				ToastUtils.show(getActivity(), "创建订单失败");
			}
		});
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != Activity.RESULT_OK) {
			return;
		}
		switch (requestCode) {
		case SelectOrderOrSkillActvity.REQUEST_TYPE:
			tvType.setText(data.getStringExtra("data"));
			item.setInputData(data.getStringExtra("data"));
			mAdapter.getDataMap().put(item.getTitle(), item);
			break;

		default:
			break;
		}
	}

	private ShowTimeLine item;

	@Override
	public void ChoiceNeed(View v, ShowTimeLine item) {
		tvType = (TextView) v;
		this.item = item;
		Navigator.NavigatorToSelectOrderOrSkillActivity(getActivity());
		// mAdapter.notifyDataSetChanged();
	}

	@Override
	public void SetDate(View v, ShowTimeLine item) {

		tvDate = (TextView) v;
		this.item = item;
		showDataPickerDialog();
		// mAdapter.notifyDataSetChanged();

	}

	private void showDataPickerDialog() {
		DataPickDialogFragment dataPickDialogFragment = DataPickDialogFragment
				.newInstance(selectDate);
		dataPickDialogFragment.show(getFragmentManager(), "select time");
		dataPickDialogFragment.setListener(this);
	}

	@Override
	public void onDialogDone(String tag, boolean cancelled, CharSequence message) {
		if (!cancelled) {
			selectDate = message.toString();
			tvDate.setText(selectDate);
			item.setInputData(selectDate);
			mAdapter.getDataMap().put(item.getTitle(), item);
		}
	}
}
