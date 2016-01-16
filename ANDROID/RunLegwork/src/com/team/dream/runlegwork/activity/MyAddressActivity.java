package com.team.dream.runlegwork.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.adapter.MyAddressAdapter;
import com.team.dream.runlegwork.entity.Address;
import com.team.dream.runlegwork.net.JsonObjectResponseHandler;
import com.team.dream.runlegwork.net.response.AddressResponse;
import com.team.dream.runlegwork.tool.Tool;
import com.team.dream.runlegwork.widget.MainTitileBar;

public class MyAddressActivity extends BaseActivity {
	@InjectView(R.id.myaddress_topbar)
	MainTitileBar mtb;
	@InjectView(R.id.myaddress_listv)
	ListView listv;
	@InjectView(R.id.myaddress_btnAdd)
	Button btnAdd;
	
	private MyAddressAdapter myAddressAdapter;
	private List<Address> list = new ArrayList<Address>();
	
	private Address mAddress;//选中的地址
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myaddress);
		ButterKnife.inject(this);
		mtb.setTitle("我的收货地址");
		listv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				for(int i=0;i<list.size();i++){
					if(position==i){
						list.get(i).setFlag(1);
						mAddress = list.get(i);
					}
					else{
						list.get(i).setFlag(0);
					}
				}
				Intent intent = new Intent(MyAddressActivity.this, MyAddressActivity.class);
				Bundle b = new Bundle();
				b.putSerializable("address", mAddress);
				intent.putExtras(b);
				setResult(RESULT_OK, intent);
				finish();
			}
		});
		getExtras();
	}
	private void getExtras() {
		mAddress = (Address) getIntent().getExtras().getSerializable("address");
	}
	public void dataChanged(){
		if(myAddressAdapter == null){
			myAddressAdapter = new MyAddressAdapter(this, list);
			listv.setAdapter(myAddressAdapter);
		}
		else{
			myAddressAdapter.notifyDataSetChanged();
		}
	}
	@Override
	protected void onResume() {
		super.onResume();
		getAddressList();
	}
	
	private void getAddressList() {
		showProgressDialog();
		api.getAddressList(new JsonObjectResponseHandler<AddressResponse>() {
			
			@Override
			public void onSuccess(AddressResponse response) {
				removeProgressDialog();
				list.clear();
				list.addAll(response.getData());
				if(list.size()>0){
					if(mAddress!=null){
						for(int i=0;i<list.size();i++){
							if(mAddress.getId() == list.get(i).getId()){
								list.get(i).setFlag(1);
							}
							else{
								list.get(i).setFlag(0);
							}
						}
					}
					else{
						list.get(0).setFlag(1);
					}
					
				}
				dataChanged();
			}
			
			@Override
			public void onFailure(String errMsg) {
				Tool.showToast(MyAddressActivity.this, errMsg);
				removeProgressDialog();
			}
		});
	}
	@OnClick(R.id.myaddress_btnAdd)
	public void addAddress(){
		startActivity(new Intent(this, NewAddressActivity.class));
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(this);
	}
}
