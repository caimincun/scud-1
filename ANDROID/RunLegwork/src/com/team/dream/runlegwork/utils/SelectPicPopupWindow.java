package com.team.dream.runlegwork.utils;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;




import java.util.List;

import com.team.dream.runlegwork.R;

public class SelectPicPopupWindow extends BasePopupWindowForListView<String> {

	private ListView lvPhoneList;
	private IOnItemSelected phoneSelected;

	private ListAdapter  mAdapter;

	public SelectPicPopupWindow(View contentView, int width, int height, List<String> mDatas) {
		super(contentView, width, height, true, mDatas);
	}

	@Override
	protected void initWidget() {
		lvPhoneList = (ListView) findViewById(R.id.lv_phone_list);
		mAdapter = new ArrayAdapter<String>(mContext,
				R.layout.adapter_select_photo, R.id.tv_select_photo_item, mDatas);
		lvPhoneList.setAdapter(mAdapter);

	}

	@Override
	protected void initEvents() {
		lvPhoneList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				if (phoneSelected != null) {
					phoneSelected.onItemSelected((int) id);
				}
			}
		});
//		tvCancel.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				dismiss();
//			}
//		});

	}
	
	

	public void setOnItemSelected(IOnItemSelected phoneSelected) {
		this.phoneSelected = phoneSelected;
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initParams(Object... params) {

	}

	public interface IOnItemSelected {
		void onItemSelected(int position);
	}

}
