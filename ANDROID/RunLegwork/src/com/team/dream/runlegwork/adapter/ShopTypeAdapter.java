package com.team.dream.runlegwork.adapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.runlegwork.DataApplication;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.entity.Producttype;
import com.team.dream.runlegwork.interfaces.RequestApi;
import com.team.dream.runlegwork.net.JsonBooleanResponseHandler;
import com.team.dream.runlegwork.utils.ToastUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ShopTypeAdapter extends BaseAdapter {

	private List<Producttype> mData;
	private Context mContext;
	protected RequestApi api = DataApplication.getInstance().getReQuestApi();

	public ShopTypeAdapter(List<Producttype> mData, Context mConext) {
		this.mData = mData;
		this.mContext = mConext;
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int arg0) {
		return mData.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(final int arg0, View arg1, ViewGroup arg2) {
		final Producttype item = mData.get(arg0);
		ViewHolder holder;
		if (null == arg1) {
			arg1 = LayoutInflater.from(mContext).inflate(
					R.layout.adapter_type_item, arg2, false);
			holder = new ViewHolder(arg1);
			arg1.setTag(holder);
		} else {
			holder = (ViewHolder) arg1.getTag();
		}

		holder.tvType.setText(item.getTypeName());
		holder.ivDel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				api.delProductType(item.getTypeToken(), new JsonBooleanResponseHandler() {
					
					@Override
					public void onSuccess() {
						ToastUtils.show(mContext, "删除成功");
						mData.remove(arg0);
						notifyDataSetChanged();
					}
					
					@Override
					public void onFailure(String errMsg) {
						ToastUtils.show(mContext, errMsg);
					}
				});
			}
		});

		return arg1;
	}

	class ViewHolder {
		@InjectView(R.id.tv_type)
		TextView tvType;
		@InjectView(R.id.iv_del)
		ImageView ivDel;

		public ViewHolder(View view) {
			ButterKnife.inject(this, view);
		}
	}

}
