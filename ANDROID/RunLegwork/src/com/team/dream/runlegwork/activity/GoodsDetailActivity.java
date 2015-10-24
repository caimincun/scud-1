package com.team.dream.runlegwork.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.SingletonServiceManager;
import com.team.dream.runlegwork.entity.Product;
import com.team.dream.runlegwork.interfaces.OnLeftClickListener;
import com.team.dream.runlegwork.widget.MainTitileBar;

public class GoodsDetailActivity extends BaseActivity {
	@InjectView(R.id.goodsdetail_mtb)
	MainTitileBar mtb;
	@InjectView(R.id.goodsdetail_ivPic)
	ImageView ivPic;
	@InjectView(R.id.goodsdetail_tvTitle)
	TextView tvTitle;
	@InjectView(R.id.goodsdetail_tvContent)
	TextView tvContent;
	@InjectView(R.id.goodsdetail_tvCount)
	TextView tvCount;
	@InjectView(R.id.goodsdetail_tvAllMoney)
	TextView tvMoney;
	@InjectView(R.id.goodsdetail_tvContract)
	TextView tvContract;
	@InjectView(R.id.goodsdetail_tvSimplePrice)
	TextView tvSimplePirce;
	@InjectView(R.id.goodsdetail_tvCommit)
	TextView tvCommit;
	@InjectView(R.id.goodsdetail_ivAdd)
	ImageView ivAdd;
	@InjectView(R.id.goodsdetail_ivJian)
	ImageView ivJian;
	@InjectView(R.id.goodsdetail_tvKuchun)
	TextView tvKucun;
	@InjectView(R.id.goodsdetail_tvNumber)
	TextView tvNumber;
	
	Product product = new Product();
	List<Product> list = new ArrayList<Product>();
	private double allMoney = 0;
	boolean flag = false;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_goodsdetail);
		ButterKnife.inject(this);
		initExtras();
		initData();
		initLintener();
	}
	
	private void initLintener() {
//		mtb.setOnLeftClickListener(new OnLeftClickListener() {
//			
//			@Override
//			public void onLeftClick() {
//				goBack();
//			}
//
//			
//		});
	}
	private void goBack() {
		Intent intent = new Intent();
		Bundle b = new Bundle();
		b.putSerializable("list", (Serializable) list);
		intent.putExtras(b);
		setResult(RESULT_OK, intent);
		finish();
	}
	private void initData() {
		tvSimplePirce.setText("￥"+product.getProductMoney()+"");
		tvNumber.setText(product.getCount()+"");
		tvTitle.setText(product.getPrductName());
		tvContent.setText(product.getDescritpion());
		tvKucun.setText("库存："+product.getSurplusNum());
		SingletonServiceManager.getInstance().display(product.getProductPictures(), ivPic, R.drawable.photo_3, null);
		tvCount.setText(list.size()+"");
		
		if(product.getCount() == 0){
			flag = true;
		}
		
		for(Product p : list){
			allMoney += p.getProductMoney() * p.getCount();
		}
		tvMoney.setText(allMoney+"");
	}

	@SuppressWarnings("unchecked")
	private void initExtras() {
		product = (Product) getIntent().getExtras().getSerializable("product");
		list = (List<Product>) getIntent().getExtras().getSerializable("productlist");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(this);
	}
	
	@OnClick(R.id.goodsdetail_ivAdd)
	public void addCount(){
		int count = Integer.parseInt(tvNumber.getText().toString());
		tvNumber.setText(count + 1+"");
		allMoney += product.getProductMoney();
		tvMoney.setText(allMoney+"");
		
		if(flag){
			product.setCount(count + 1);
			list.add(product);
			flag = false;
		}
		
		for(int i=0;i<list.size();i++){
			Product p1 = list.get(i);
			if(product.getProductToken().equals(p1.getProductToken())){
				list.get(i).setCount(count+1);
				break;
			}
		}
	}
	@OnClick(R.id.goodsdetail_ivJian)
	public void jianCount(){
		int count = Integer.parseInt(tvNumber.getText().toString());
		tvNumber.setText(count - 1+"");
		allMoney -= product.getProductMoney();
		tvMoney.setText(allMoney+"");
		
		for(int i=0;i<list.size();i++){
			Product p1 = list.get(i);
			if(product.getProductToken().equals(p1.getProductToken())){
				list.get(i).setCount(count-1);
				break;
			}
		}
	}
	@OnClick(R.id.goodsdetail_tvCommit)
	public void confirm(){
		goBack();
	}
	@OnClick(R.id.goodsdetail_tvContract)
	public void Content(){
		Intent intent = new Intent(GoodsDetailActivity.this, ChatActivity.class);
		intent.putExtra("userId", "18728120022");
		startActivity(intent);
	}
}
