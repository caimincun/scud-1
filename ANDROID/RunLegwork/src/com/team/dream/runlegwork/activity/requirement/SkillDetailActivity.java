package com.team.dream.runlegwork.activity.requirement;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.adapter.PushOrderAdapter.OnSetDataListener;
import com.team.dream.runlegwork.dialog.DataPickDialogFragment;
import com.team.dream.runlegwork.interfaces.OnMyDialogClickListener;
import com.team.dream.runlegwork.widget.MainTitileBar;

public class SkillDetailActivity extends BaseActivity implements
		OnSetDataListener, OnMyDialogClickListener {
	@InjectView(R.id.skilldetail_mtb)
	MainTitileBar mtb;
	@InjectView(R.id.skilldetail_tvSend)
	TextView tvSend;
	@InjectView(R.id.skilldetail_edtSimplePrice)
	EditText edtSimplePrice;
	@InjectView(R.id.skilldetail_edtTitle)
	EditText edtTitle;
	@InjectView(R.id.skilldetail_edtMoney)
	EditText edtMoney;
	@InjectView(R.id.skilldetail_tvTime)
	TextView tvTime;
	@InjectView(R.id.skilldetail_edtAddress)
	EditText edtAddress;
	
	
	private TextView tvDate;
	
	private String selectDate;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_skilldetail);
		ButterKnife.inject(this);
	}
	@OnClick(R.id.skilldetail_tvTime)
	public void getDate(){
		SetDate(tvTime);
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(this);
	}

	@Override
	public void onDialogDone(String tag, boolean cancelled, CharSequence message) {
		if (!cancelled) {
			selectDate = message.toString();
			tvTime.setText(selectDate);
		}
	}

	@Override
	public void ChoiceNeed(View v) {
		// TODO Auto-generated method stub
		
	}
	
	

	@Override
	public void SetDate(View v) {
		tvDate = (TextView) v;
		showDataPickerDialog();
	}
	private void showDataPickerDialog() {
		DataPickDialogFragment dataPickDialogFragment = DataPickDialogFragment
				.newInstance(selectDate);
		dataPickDialogFragment.show(getSupportFragmentManager(), "select time");
		dataPickDialogFragment.setListener(this);
	}
}
