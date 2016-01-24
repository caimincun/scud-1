package com.team.dream.runlegwork.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.runlegwork.MyBaseAdapter;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.adapter.PushOrderAdapter.OnSetDataListener;
import com.team.dream.runlegwork.entity.ShowTimeLine;
import com.team.dream.runlegwork.utils.StringUtils;

public class SkilldetailAdapter extends MyBaseAdapter {
	private Context ctx;
	private List<ShowTimeLine> listdata = new ArrayList<ShowTimeLine>();
	private LayoutInflater inflater;
	private String[] mCheckData = new String[5];
	private OnSetDataListener onSetDataListener;
	public SkilldetailAdapter(Context ctx, List<ShowTimeLine> listdata) {
		this.ctx = ctx;
		this.listdata = listdata;
		this.inflater = LayoutInflater.from(ctx);
	}

	@Override
	public int getCount() {
		return listdata.size();
	}

	@Override
	public Object getItem(int arg0) {
		return listdata.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (null == convertView) {

			convertView = LayoutInflater.from(ctx).inflate(
					R.layout.adapter_push_order_item, parent, false);

			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final ShowTimeLine showTLine = (ShowTimeLine) getItem(position);
		holder.tvTitle.setText(showTLine.getTitle());
		final ViewHolder holder1 = holder;
		if(position != 1){
			holder.etMsg.setVisibility(View.VISIBLE);
			holder.tvSelectMsg.setVisibility(View.GONE);
			TextChange(position, holder.etMsg, holder1);
			
			holder.tvSelectMsg.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (onSetDataListener != null) {
						onSetDataListener.ChoiceNeed(v, showTLine);
					}
				}
			});
		}
		else{
			holder.etMsg.setVisibility(View.GONE);
			holder.tvSelectMsg.setVisibility(View.VISIBLE);
			TextChange(position, holder.tvSelectMsg, holder1);
		}
		
		checkForShowTimeLine(holder1);
		return convertView;
	}
	
	private void TextChange(final int position, View v,final ViewHolder holder1) {

		if (v instanceof EditText) {
			final EditText etText = (EditText) v;
			((EditText) v).addTextChangedListener(new TextWatcher() {

				@Override
				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
				}

				@Override
				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
				}

				@Override
				public void afterTextChanged(Editable s) {
					etText.setTag(s.toString().trim());
					mCheckData[position] = s.toString().trim();

				}
			});
		}
		if (v instanceof TextView) {
			final TextView tvText = (TextView) v;
			((TextView) v).addTextChangedListener(new TextWatcher() {

				@Override
				public void onTextChanged(CharSequence s, int start,
						int before, int count) {

				}

				@Override
				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {

				}

				@Override
				public void afterTextChanged(Editable s) {
					if (!StringUtils.isEmpty(s.toString().trim())) {
						tvText.setTag(s.toString().trim());
						checkForShowTimeLine(holder1);
					}
					mCheckData[position] = s.toString().trim();
				}
			});
		}
	}
	private void checkForShowTimeLine(ViewHolder holder) {
		boolean isFous = holder.etMsg.isFocused();
		boolean isFill = !StringUtils.isEmpty(holder.etMsg.getText().toString()
				.trim())
				|| !StringUtils.isEmpty(holder.tvSelectMsg.getText().toString());
		if (isFous || isFill) {
			holder.ivMarkTop.setImageResource(R.drawable.time_line_mark);
		} else {
			holder.ivMarkTop
					.setImageResource(R.drawable.time_line_mark_unselect);
		}
		if (isFill) {
			holder.llMarkLine
					.setBackgroundResource(R.drawable.sp_time_line_select);
		} else {
			holder.llMarkLine.setBackgroundResource(R.drawable.sp_time_line);
		}
	}
	public OnSetDataListener getOnSetDataListener() {
		return onSetDataListener;
	}

	public void setOnSetDataListener(OnSetDataListener onSetDataListener) {
		this.onSetDataListener = onSetDataListener;
	}
	
	static class ViewHolder{

		@InjectView(R.id.iv_mark_top)
		ImageView ivMarkTop;
		@InjectView(R.id.ll_mark_line)
		LinearLayout llMarkLine;
		@InjectView(R.id.et_message)
		EditText etMsg;
		@InjectView(R.id.tv_message)
		TextView tvSelectMsg;
		@InjectView(R.id.tv_tip_msg)
		TextView tvTipMsg;
		@InjectView(R.id.tv_title)
		TextView tvTitle;

		public ViewHolder(View view) {
			ButterKnife.inject(this, view);
		}

	
	}

}
