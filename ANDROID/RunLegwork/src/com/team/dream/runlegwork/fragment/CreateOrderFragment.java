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
import com.team.dream.runlegwork.utils.ToastUtils;
import com.team.dream.runlegwork.widget.TopBar;

public class CreateOrderFragment extends BaseFragment implements OnMyDialogClickListener {

	@InjectView(R.id.topbar)
	TopBar topbar;
	@InjectView(R.id.et_titile)
	EditText etTitle;
	@InjectView(R.id.et_type)
	EditText etType;
	@InjectView(R.id.et_detail)
	EditText etDetail;
	@InjectView(R.id.et_address)
	EditText etAddress;
	@InjectView(R.id.tv_select_time)
	TextView tvSelectTime;
	@InjectView(R.id.tv_push_confirm)
	TextView tvPushConfirm;
	
	private String selectDate;

	public static CreateOrderFragment newInstance() {
		CreateOrderFragment fragment = new CreateOrderFragment();
		return fragment;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_create_order, container,
				false);
		ButterKnife.inject(this, view);
		topbar.initialze("发布需求");
		return view;
	}

	@OnClick(R.id.tv_push_confirm)
	public void createOrder() {
	

	}

	@OnClick(R.id.tv_select_time)
	public void showSelectTime() {
		showDataPickerDialog();
	}

	@Override
	protected void initializePresenter() {

	}
	private void showDataPickerDialog(){
		DataPickDialogFragment dataPickDialogFragment=DataPickDialogFragment.newInstance(selectDate);
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
			selectDate= message.toString();
		}
		ToastUtils.show(getActivity(), message);
	}
}
