package com.team.dream.runlegwork.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.imageloader.utils.DensityUtils;
import com.team.dream.pulltorefresh.library.PullToRefreshGridView;
import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.adapter.GoodsShowAdapter;
import com.team.dream.runlegwork.adapter.PopuWindowPayAdapter;
import com.team.dream.runlegwork.entity.Product;
import com.team.dream.runlegwork.interfaces.OnShopBusCountChanged;
import com.team.dream.runlegwork.interfaces.OnShopPopChangedListener;

public class GoodsShowActivity extends BaseActivity implements
		OnShopPopChangedListener,OnShopBusCountChanged {
	@InjectView(R.id.goodsshow_gvShopDetail)
	PullToRefreshGridView gv;
	@InjectView(R.id.goodsshow_tvCommit)
	TextView tvCommit;
	@InjectView(R.id.goodsshow_tvClassity)
	TextView tvClassity;
	@InjectView(R.id.goodsshow_tvCount)
	TextView tvCount;
	@InjectView(R.id.goodsshow_llShopbus)
	LinearLayout llShopbus;
	@InjectView(R.id.goodsshow_tvAllMoney)
	TextView tvAllMoney;

	private PopupWindow popuWindowClassity;
	private PopupWindow popuWindowPay;
	View view1;
	ListView popPayListv;

	List<Product> list = new ArrayList<Product>();;// 该店所有商品
//	List<Product> listSelected = new ArrayList<Product>();
	private List<Product> listSelector = new ArrayList<Product>();// 要购买的商品

	private GoodsShowAdapter gsAdapter;
	private PopuWindowPayAdapter pwpAda;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_goodsshow);
		ButterKnife.inject(this);
		initData();
		initPopuwindow();
		initListener();
	}

	private void initPopuwindow() {
		// 商品分类的popuwindow
		View view = LayoutInflater.from(this).inflate(
				R.layout.popuwindow_goodsclassity, null);

		popuWindowClassity = new PopupWindow(view, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, true);
		popuWindowClassity.setTouchable(true);
		popuWindowClassity.setOutsideTouchable(true);
		popuWindowClassity.setBackgroundDrawable(new ColorDrawable(0));
		popuWindowClassity.setTouchInterceptor(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				return false;
			}
		});
		// 结算的popuwindow
		view1 = LayoutInflater.from(this)
				.inflate(R.layout.popuwindow_pay, null);
		popuWindowPay = new PopupWindow(view1, LayoutParams.MATCH_PARENT, DensityUtils.dp2px(this, 300),
				true);
		popuWindowPay.setTouchable(true);
		popuWindowPay.setBackgroundDrawable(new ColorDrawable(0));
		view1.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
		view1.getMeasuredHeight();
		popPayListv = (ListView) view1.findViewById(R.id.pop_pay_listv);
		pwpAda = new PopuWindowPayAdapter(this, getSelector());
		popPayListv.setAdapter(pwpAda);
		pwpAda.setShopPopChangedListener(this);

	}
	
	public void initListener(){
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(GoodsShowActivity.this, GoodsDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("product", list.get(arg2));
				bundle.putSerializable("productlist", (Serializable) listSelector);
				intent.putExtras(bundle);
				startActivityForResult(intent,1);
			}
		});
	}

	public void dataChangedPop() {

		getSelector();
//		pwpAda.notifyDataSetChanged();
		pwpAda.setList(listSelector);
	}

	private void initData() {

		list.add(new Product("aa", "a", "", "苹果", "", 20, 20, "好吃", 0, 0));
		list.add(new Product("aa", "b", "", "梨子", "", 10, 20, "好吃", 0, 0));
		list.add(new Product("aa", "c", "", "香蕉", "", 10, 20, "好吃", 0, 0));
		list.add(new Product("aa", "d", "", "红提", "", 15, 20, "好吃", 0, 0));
		list.add(new Product("aa", "e", "", "西瓜", "", 5, 20, "好吃", 0, 0));
		dataChanged();
	}

	public void dataChanged() {
		if (gsAdapter == null) {
			gsAdapter = new GoodsShowAdapter(this, list);
			gv.setAdapter(gsAdapter);
			gsAdapter.setOnShopBusCountChanged(this);
		} else {
			gsAdapter.setList(list);
			// gv.setAdapter(gsAdapter);
		}
		tvCount.setText(listSelector.size()+"");

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(this);
	}

	@OnClick(R.id.goodsshow_tvClassity)
	public void classify() {

		if (popuWindowClassity.isShowing()) {
			popuWindowClassity.dismiss();
		} else {
			popuWindowClassity.showAsDropDown(tvClassity);
		}
	}

	@OnClick(R.id.goodsshow_llShopbus)
	public void commit() {
		if (popuWindowPay.isShowing()) {
			popuWindowPay.dismiss();
		} else {
			dataChangedPop();
			int[] location = new int[2];
			tvCommit.getLocationOnScreen(location);
			popuWindowPay.showAtLocation(tvCommit, Gravity.NO_GRAVITY, 0,
					location[1] - DensityUtils.dp2px(this, 300));
		}
	}

	public List<Product> getSelector() {
		listSelector.clear();
		for (Product p : list) {
			if (p.getCount() > 0) {
				listSelector.add(p);
			}
		}

		return listSelector;
	}

	@Override
	public void changed(String token, int count) {
		gsAdapter.notifyDataSetChanged();
		double money = 0;
		for(Product p : list){
			money += p.getProductMoney() * p.getCount();
		}
		tvAllMoney.setText("￥"+money);
		
	}
	public abstract class aa{}
	@Override
	public void shopBusCountChanged() {
		getSelector();
		double money = 0;
		for(Product p : list){
			money += p.getProductMoney() * p.getCount();
		}
		tvAllMoney.setText("￥"+money);
		
		tvCount.setText(listSelector.size()+"");
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
			listSelector = (List<Product>) data.getExtras().getSerializable("list");
			for(Product p : listSelector){
				for(int i=0;i<list.size();i++){
					if(p.getProductToken().equals(list.get(i).getProductToken())){
						list.get(i).setCount(p.getCount());
						break;
					}
				}
			}
			double money = 0;
			for(Product p : list){
				money += p.getProductMoney() * p.getCount();
			}
			tvAllMoney.setText("￥"+money);
			gsAdapter.notifyDataSetChanged();
			dataChangedPop();
		}
	}
}
