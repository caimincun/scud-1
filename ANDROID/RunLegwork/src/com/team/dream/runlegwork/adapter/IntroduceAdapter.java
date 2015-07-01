package com.team.dream.runlegwork.adapter;

import java.util.ArrayList;
import java.util.List;

import com.team.dream.runlegwork.R;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class IntroduceAdapter extends Adapter<IntroduceAdapter.ViewHoler> {

	private LayoutInflater inflater;
	private Context mContext;
	private List<String> mDatas;

	public IntroduceAdapter(Context context, List<String> mDatas) {
		this.mContext = context;
		inflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (null == mDatas || mDatas.isEmpty()) {
			mDatas = new ArrayList<String>();
		}
		this.mDatas = mDatas;
	}

	@Override
	public int getItemCount() {
		return mDatas.size();
	}

	static class ViewHoler extends RecyclerView.ViewHolder {

		public ViewHoler(View itemView) {
			super(itemView);
		}

		ImageView imageView;
		TextView title;
		TextView desc;

	}

	@Override
	public void onBindViewHolder(ViewHoler viewHoler, int position) {

	}

	@Override
	public ViewHoler onCreateViewHolder(ViewGroup arg0, int arg1) {
		View view = inflater.inflate(R.layout.adapter_introduce, arg0, false);
		ViewHoler holer = new ViewHoler(view);
		holer.imageView = (ImageView) view.findViewById(R.id.iv_icon);
		holer.title = (TextView) view.findViewById(R.id.tv_title);
		holer.desc = (TextView) view.findViewById(R.id.tv_desc);
		return holer;
	}
}
