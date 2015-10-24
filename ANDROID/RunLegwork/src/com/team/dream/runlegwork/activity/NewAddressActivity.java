package com.team.dream.runlegwork.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.entity.Address;
import com.team.dream.runlegwork.net.JsonBooleanResponseHandler;
import com.team.dream.runlegwork.tool.Tool;
import com.team.dream.runlegwork.utils.StringUtils;
import com.team.dream.runlegwork.widget.MainTitileBar;

public class NewAddressActivity extends BaseActivity {
	private Context ctx;
	@InjectView(R.id.newaddress_topbar)
	MainTitileBar mtb;
	@InjectView(R.id.newaddress_edtAddress)
	EditText edtAddress;
	@InjectView(R.id.newaddress_edtName)
	EditText edtName;
	@InjectView(R.id.newaddress_edtPhone)
	EditText edtPhone;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_newaddress);
		ButterKnife.inject(this);
		ctx = this;
		mtb.setTitle("新增收货地址");
	}
	@OnClick(R.id.newaddress_btnAdd)
	public void confirm(){
		String address = edtAddress.getText().toString();
		String name = edtName.getText().toString();
		String phone = edtPhone.getText().toString();
		if(StringUtils.isEmpty(address)){
			Tool.showToast(this, "地址不能为空");
		}
		else if(StringUtils.isEmpty(name)){
			Tool.showToast(this, "收货人姓名不能为空");
		}
		else if(StringUtils.isEmpty(phone)){
			Tool.showToast(this, "收货人电话不能为空");
		}
		else{
			Address addressEnt = new Address(address, name, phone);
			api.saveAddress(addressEnt, new JsonBooleanResponseHandler() {
				
				@Override
				public void onSuccess() {
					Tool.showToast(ctx, "地址添加成功");
					finish();
				}
				
				@Override
				public void onFailure(String errMsg) {
					Tool.showToast(ctx, errMsg);
				}
			});
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(this);
	}
}
