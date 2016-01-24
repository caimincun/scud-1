package com.team.dream.runlegwork.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.runlegwork.BaseFragment;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.adapter.ShopGoodsAdapter;
import com.team.dream.runlegwork.adapter.ShopTypeAdapter;
import com.team.dream.runlegwork.entity.Product;
import com.team.dream.runlegwork.entity.Producttype;
import com.team.dream.runlegwork.net.JsonObjectResponseHandler;
import com.team.dream.runlegwork.net.response.ArrayEntityResponse;
import com.team.dream.runlegwork.utils.ToastUtils;

public class ShopGoodsFragment extends BaseFragment {

	public static final String POSTION_TYPE = "param1";
	private static final String ARG_PARAM2 = "param2";

	public static final String GOODS_SELL = "goods_sell";
	public static final String GOODS_XIAJIA = "goods_xiajia";
	public static final String GOODS_TYPE = "goods_type";
	private BaseAdapter mAdapter;
	private List<Product> mData = new ArrayList<Product>();
	private List<Producttype> mTypes = new ArrayList<Producttype>();

	
	
	private void initList(){
		String sell = getArguments().getString(POSTION_TYPE);

		if (sell.equals(GOODS_SELL)) {
			mAdapter = new ShopGoodsAdapter(getActivity(), mData);
			api.getProductList(new JsonObjectResponseHandler<ArrayEntityResponse<Product>>() {

				@Override
				public void onSuccess(ArrayEntityResponse<Product> response) {
					mData.clear();
					mData.addAll(response.getData());
					lvShopGoods.setAdapter(mAdapter);
				}

				@Override
				public void onFailure(String errMsg) {
					ToastUtils.show(getActivity(), errMsg);
				}
			});
		} else if (sell.equals(GOODS_XIAJIA)) {
			mAdapter = new ShopGoodsAdapter(getActivity(), mData);
			api.getXiajiaProductList(new JsonObjectResponseHandler<ArrayEntityResponse<Product>>() {

				@Override
				public void onSuccess(ArrayEntityResponse<Product> response) {
					mData.clear();
					mData.addAll(response.getData());
					lvShopGoods.setAdapter(mAdapter);

				}

				@Override
				public void onFailure(String errMsg) {
					ToastUtils.show(getActivity(), errMsg);
				}
			});
		} else if (sell.equals(GOODS_TYPE)) {
			mAdapter = new ShopTypeAdapter(mTypes, getActivity());
			api.querylistproductTypes(new JsonObjectResponseHandler<ArrayEntityResponse<Producttype>>() {

				@Override
				public void onSuccess(ArrayEntityResponse<Producttype> response) {
					mTypes.clear();
					mTypes.addAll(response.getData());
					lvShopGoods.setAdapter(mAdapter);

				}

				@Override
				public void onFailure(String errMsg) {
					ToastUtils.show(getActivity(), errMsg);
				}
			});
		}
	}
	@Override
	protected void initializePresenter() {
		
	}

	@InjectView(R.id.lv_shop_goods)
	ListView lvShopGoods;

	public static ShopGoodsFragment newInstance(String param1, int param2) {
		ShopGoodsFragment fragment = new ShopGoodsFragment();
		Bundle args = new Bundle();
		args.putString(POSTION_TYPE, param1);
		args.putInt(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_shop_goods, container,
				false);
		ButterKnife.inject(this, view);
		initList();
		return view;
	}
	@Override
	public void onResume() {
		super.onResume();
		initList();
	}
}
