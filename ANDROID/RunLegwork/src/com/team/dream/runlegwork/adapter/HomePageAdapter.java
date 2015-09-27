package com.team.dream.runlegwork.adapter;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.entity.FunctionItem;
import com.team.dream.runlegwork.widget.BannerBrowsingWidget;

@SuppressLint("NewApi")
public class HomePageAdapter extends BaseAdapter {

	private static final int TYPE_BANNDER_BROWSING = 0;
	private static final int TYPE_FUCTION = 1;
	private static final int TYPE_GRID = 2;
	private Context mContext;
	private LayoutInflater inflater;
	private FuctionPushAdapter mFucAdapter;
	private List<FunctionItem> mData;
	private List<FunctionItem> mDataShow;
	private FuctionPushAdapter mFucShowAdapter;
	private OnHomeFucClickListener onFucClickListener;

	public HomePageAdapter(Context context) {
		this.mContext = context;
		inflater = LayoutInflater.from(mContext);

		initData();
	}

	private void initData() {
		mData = new ArrayList<FunctionItem>();
		mData.add(new FunctionItem("需求发布", R.drawable.demand_fuc_push_need));
		mData.add(new FunctionItem("需求大厅", R.drawable.demand_fuc_need_room));
		mData.add(new FunctionItem("技能发布", R.drawable.demand_push_skill));
		mFucAdapter = new FuctionPushAdapter(mData, mContext);
		mDataShow = new ArrayList<FunctionItem>();
		mDataShow.add(new FunctionItem(R.drawable.fuc_chatting));
		mDataShow.add(new FunctionItem(R.drawable.fuc_shop));
		mDataShow.add(new FunctionItem(R.drawable.fuc_housekeeping));
		mDataShow.add(new FunctionItem(R.drawable.fuc_massage));
		mFucShowAdapter = new FuctionPushAdapter(mDataShow, mContext);
	}

	@Override
	public int getCount() {
		return 3;
	}

	@Override
	public int getViewTypeCount() {
		return 3;
	}

	@Override
	public int getItemViewType(int position) {
		int type = -1;
		switch (position) {
		case 0:
			type = TYPE_BANNDER_BROWSING;
			break;
		case 1:
			type = TYPE_FUCTION;
			break;
		case 2:
			type = TYPE_GRID;
			break;
		}

		return type;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHoler holer = null;
		int viewType = getItemViewType(position);
		if (null == convertView) {
			holer = new ViewHoler();
			switch (viewType) {
			case TYPE_BANNDER_BROWSING:
				convertView = inflater.inflate(
						R.layout.adapter_item_banner_browsing, parent, false);
				holer.bbwBanner = (BannerBrowsingWidget) convertView
						.findViewById(R.id.bbw_banner);
		
				break;
			case TYPE_FUCTION:
				convertView = inflater.inflate(R.layout.adapter_fuction_push,
						parent, false);
				holer.gvFuction = (GridView) convertView
						.findViewById(R.id.gv_function);
				break;
			case TYPE_GRID:
				convertView = inflater.inflate(R.layout.adapter_fuc_show_skill,
						parent, false);
				holer.gvFucShow = (GridView) convertView
						.findViewById(R.id.gv_fuc_show);
				break;
			}
			convertView.setTag(holer);
		} else {
			holer = (ViewHoler) convertView.getTag();
		}
		switch (viewType) {
		case TYPE_BANNDER_BROWSING:
			holer.bbwBanner.initView(null);
			break;
		case TYPE_FUCTION:
			holer.gvFuction.setNumColumns(3);
			holer.gvFuction.setAdapter(mFucAdapter);

			holer.gvFuction.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					if (onFucClickListener != null) {
						onFucClickListener.OnSelectFucPostion(position);
					}

				}
			});
			break;
		case TYPE_GRID:
			holer.gvFucShow.setAdapter(mFucShowAdapter);
			setListViewHeightBasedOnChildren(holer.gvFucShow);
			holer.gvFucShow.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					if (onFucClickListener != null) {
						onFucClickListener.OnSelectSkillPostion(position);
					}

				}
			});
			break;
		}
		return convertView;
	}

	class ViewHoler {
		BannerBrowsingWidget bbwBanner;
		GridView gvFuction;
		GridView gvFucShow;
	}

	public static void setListViewHeightBasedOnChildren(GridView listView) {

		ListAdapter listAdapter = listView.getAdapter();

		if (listAdapter == null) {
			return;
		}

		int totalHeight = 0;

		for (int i = 0; i < listAdapter.getCount(); i++) {

			View listItem = listAdapter.getView(i, null, listView);

			listItem.measure(0, 0);

			totalHeight += listItem.getMeasuredHeight();

		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();

		int dividerHeight = listView.getVerticalSpacing();
		totalHeight += (dividerHeight * (listAdapter.getCount() - 1));
		params.height = totalHeight / 2;

		listView.setLayoutParams(params);

	}

	public OnHomeFucClickListener getOnFucClickListener() {
		return onFucClickListener;
	}

	public void setOnFucClickListener(OnHomeFucClickListener onFucClickListener) {
		this.onFucClickListener = onFucClickListener;
	}

	public interface OnHomeFucClickListener {
		void OnSelectFucPostion(int position);

		void OnSelectSkillPostion(int position);
	}

}
