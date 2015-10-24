package com.team.dream.runlegwork.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.google.gson.Gson;
import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.adapter.PayOrderAdapter;
import com.team.dream.runlegwork.dialog.DialogTextEdit;
import com.team.dream.runlegwork.entity.Address;
import com.team.dream.runlegwork.entity.OrderAndAddressEntity;
import com.team.dream.runlegwork.entity.Product;
import com.team.dream.runlegwork.entity.Store;
import com.team.dream.runlegwork.net.JsonBooleanResponseHandler;
import com.team.dream.runlegwork.tool.Tool;
import com.team.dream.runlegwork.widget.MainTitileBar;

public class PayOrderActivity extends BaseActivity {
	private Context ctx;
	@InjectView(R.id.payorder_listv)
	ListView listv;
	@InjectView(R.id.payorder_tvAllmoney)
	TextView tvAllmoney;
	@InjectView(R.id.payorder_tvConfirm)
	TextView tvConfirm;
	@InjectView(R.id.payorder_topbar)
	MainTitileBar mtb;
	
	List<Product> listdata = new ArrayList<Product>();
	private PayOrderAdapter payOrderAdapter;
	
	private Address mAddress;
	private String remark="";
	private Store store;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_payorder);
		ButterKnife.inject(this);
		ctx = this;
		mtb.setTitle("提交订单");
		getExtras();
		dataChanged();
		
		listv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case 0:
					Intent intent = new Intent(ctx, MyAddressActivity.class);
					Bundle b = new Bundle();
					b.putSerializable("address", mAddress);
					intent.putExtras(b);
					startActivityForResult(intent, 100);
					break;
				case 3:
					final DialogTextEdit dig = new DialogTextEdit(ctx);
					dig.setTitleText("请输入备注");
					dig.setRightBtnContent("确定").setRightBtnOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							remark = dig.getEditContent();
							payOrderAdapter.setRemark(remark);
							Tool.cancelAlertDialog();
						}
					});
					dig.setLeftBtnContent("取消").setLeftBtnOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							Tool.cancelAlertDialog();
						}
					});
					Tool.showAlertDialog(ctx, dig, true, true);
					break;

				default:
					break;
				}
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	private void getExtras() {
		store = (Store) getIntent().getExtras().getSerializable("store");
		listdata = (List<Product>) getIntent().getExtras().getSerializable("list");
		double money = 0;
		for(Product p : listdata){
			money += p.getCount()*p.getProductMoney();
		}
		tvAllmoney.setText("总计： "+money);
	}
	
	public void dataChanged(){
		if(payOrderAdapter == null){
			payOrderAdapter = new PayOrderAdapter(ctx, listdata,mAddress,remark);
			listv.setAdapter(payOrderAdapter);
		}
		else{
			payOrderAdapter.notifyDataSetChanged();
		}
	}
	@OnClick(R.id.payorder_tvConfirm)
	public void Confirm(){
		OrderAndAddressEntity aae = new OrderAndAddressEntity();
		aae.setOrders(listdata);
		aae.setReceiptId(mAddress.getId());
		aae.setStoreToken(store.getStoreToken());
		api.confirmOrder(aae, new JsonBooleanResponseHandler() {
			
			@Override
			public void onSuccess() {
				Tool.showToast(ctx, "下单成功");
				finish();
			}
			
			@Override
			public void onFailure(String errMsg) {
				Tool.showToast(ctx, errMsg);
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		mAddress = (Address) data.getExtras().getSerializable("address");
		payOrderAdapter.setAddress(mAddress);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(this);
	}
}
