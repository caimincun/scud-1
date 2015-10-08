package com.team.dream.runlegwork.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.runlegwork.BaseFragment;
import com.team.dream.runlegwork.R;

public class ShopGoodsFragment extends BaseFragment {

	private static final String POSTION_TYPE = "param1";
	private static final String ARG_PARAM2 = "param2";

	@Override
	protected void initializePresenter() {

	}

	@InjectView(R.id.lv_shop_goods)
	ListView lvShopGoods;

	public static HomeFragment newInstance(int param1, int param2) {
		HomeFragment fragment = new HomeFragment();
		Bundle args = new Bundle();
		args.putInt(POSTION_TYPE, param1);
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
		return view;
	}
}
