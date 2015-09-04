package com.team.dream.runlegwork.adapter;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.utils.StringUtils;

public class PushOrderAdapter extends BaseAdapter {

	private Context mContext;
	private List<ShowTimeLine> mData = new ArrayList<ShowTimeLine>();

	private OnSetDataListener onSetDataListener;
	private String[] mDatas = new String[6];

	public PushOrderAdapter(Context mContext) {
		this.mContext = mContext;

		mData.add(new ShowTimeLine(mContext.getString(R.string.my_push_need),
				mContext.getString(R.string.please_enter_your_need)));
		mData.add(new ShowTimeLine(mContext.getString(R.string.my_push_scope)));
		mData.add(new ShowTimeLine(mContext.getString(R.string.my_push_detail),
				mContext.getString(R.string.please_enter_your_detail)));
		mData.add(new ShowTimeLine(mContext.getString(R.string.my_push_time)));
		mData.add(new ShowTimeLine(
				mContext.getString(R.string.my_push_address), mContext
						.getString(R.string.please_enter_your_address)));
		mData.add(new ShowTimeLine(mContext.getString(R.string.my_push_money)));
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
	public long getItemId(int position) {
		return position;
	}

	int touchedPosition;

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ShowTimeLine data = mData.get(position);
		ViewHoler holer = null;
		if (null == convertView) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.adapter_push_order_item, parent, false);
			holer = new ViewHoler(convertView);
			convertView.setTag(holer);
		} else {
			holer = (ViewHoler) convertView.getTag();
		}
		String hint = data.getHint();
		holer.tvTitle.setText(data.getTitle());
		TextChange(position, holer.etMsg);
		TextChange(position, holer.tvSelectMsg);
		String tag = (String) holer.etMsg.getTag();
		if (tag != null) {
			holer.etMsg.setText(tag);
		}

		if (!StringUtils.isEmpty(hint)) {
			holer.etMsg.setHint(hint);
		}

		if (position == 1) {
			holer.tvSelectMsg.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (onSetDataListener != null) {
						onSetDataListener.ChoiceNeed(v);
					}
				}
			});
		}
		if (position == 3) {
			holer.tvSelectMsg.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (onSetDataListener != null) {
						onSetDataListener.SetDate(v);
					}
				}
			});
		}
		if (position == 2) {
			holer.etMsg.setLines(3);
		}
		if (position == 1 || position == 3) {
			holer.etMsg.setVisibility(View.GONE);
			holer.tvSelectMsg.setVisibility(View.VISIBLE);
			String tags = (String) holer.tvSelectMsg.getTag();
			if (tags != null) {
				holer.tvSelectMsg.setText(tag);
			}
		}
		if (position == (mData.size() - 1)) {
			holer.etMsg.setInputType(InputType.TYPE_CLASS_NUMBER);
			holer.tvTipMsg.setVisibility(View.VISIBLE);
			holer.llMarkLine.setVisibility(View.GONE);
		}

		holer.etMsg.setOnTouchListener(new OnTouchListener() {

			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					touchedPosition = position;
				}
				return false;
			}
		});

		if (touchedPosition == position) {
			// 如果当前的行下标和点击事件中保存的index一致，手动为EditText设置焦点。
			holer.etMsg.requestFocus();

		} else {
			holer.etMsg.clearFocus();
		}
		final ViewHoler vholer = holer;
		holer.etMsg.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				checkForShowTimeLine(vholer);
			}
		});

		checkForShowTimeLine(holer);
		return convertView;
	}

	private void checkForShowTimeLine(ViewHoler holer) {
		boolean isFous = holer.etMsg.isFocused();
		boolean isFill = !StringUtils.isEmpty(holer.etMsg.getText().toString()
				.trim())
				|| !StringUtils.isEmpty(holer.tvSelectMsg.getText().toString());
		if (isFous || isFill) {
			holer.ivMarkTop.setImageResource(R.drawable.time_line_mark);
		} else {
			holer.ivMarkTop
					.setImageResource(R.drawable.time_line_mark_unselect);
		}
		if (isFill) {
			holer.llMarkLine
					.setBackgroundResource(R.drawable.sp_time_line_select);
		} else {
			holer.llMarkLine.setBackgroundResource(R.drawable.sp_time_line);
		}
	}

	class ViewHoler {
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

		public ViewHoler(View view) {
			ButterKnife.inject(this, view);
		}

	}

	public OnSetDataListener getOnSetDataListener() {
		return onSetDataListener;
	}

	public void setOnSetDataListener(OnSetDataListener onSetDataListener) {
		this.onSetDataListener = onSetDataListener;
	}

	public interface OnSetDataListener {
		void ChoiceNeed(View v);

		void SetDate(View v);
	}

	private void TextChange(final int position, View v) {

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
					mDatas[position] = s.toString().trim();

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
					tvText.setTag(s.toString().trim());
					mDatas[position] = s.toString().trim();
				}
			});
		}
	}

	public String[] getData() {
		return mDatas;
	}

	private class ShowTimeLine {
		private String title;
		private String hint;

		public String getTitle() {
			return title;
		}

		public String getHint() {
			return hint;
		}

		public ShowTimeLine(String title, String hint) {
			super();
			this.title = title;
			this.hint = hint;
		}

		public ShowTimeLine(String title) {
			super();
			this.title = title;
		}

	}

}
