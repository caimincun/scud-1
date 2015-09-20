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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.entity.ShowTimeLine;
import com.team.dream.runlegwork.utils.StringUtils;

public class PushSkillAdapter extends BaseAdapter {

	private Context mContext;
	private List<ShowTimeLine> mData = new ArrayList<ShowTimeLine>();
	private String[] mCheckData = new String[5];

	private OnSetDataListener onSetDataListener;

	public PushSkillAdapter(Context mContext) {
		this.mContext = mContext;

		mData.add(new ShowTimeLine(mContext.getString(R.string.my_skill_need),
				mContext.getString(R.string.please_fill_your_skill_name)));
		mData.add(new ShowTimeLine(mContext.getString(R.string.my_push_scope)));
		mData.add(new ShowTimeLine(mContext.getString(R.string.my_skill_price),
				mContext.getString(R.string.please_fill_your_skill_price)));
		mData.add(new ShowTimeLine(
				mContext.getString(R.string.my_skill_detail), mContext
						.getString(R.string.please_fill_your_skill_detail)));

	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public int getViewTypeCount() {
		return mData.size();
	}
	
	@Override
	public int getItemViewType(int position) {
		return position;
	}
	@Override
	public Object getItem(int position) {
		return mCheckData[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	int touchedPosition;

	@Override
	public View getView(int postion, View convertView, ViewGroup parent) {
		final int position = (int) getItemId(postion);
		ShowTimeLine data = null;
		data = mData.get(position);
		int type=getItemViewType(position);
		ViewHoler holer = null;
		if (null == convertView) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.adapter_push_skill_item, parent, false);
			holer = new ViewHoler(convertView);
			convertView.setTag(holer);
		} else {
			holer = (ViewHoler) convertView.getTag();
		}
		final ViewHoler vholer = holer;
		String hint = data.getHint();
		holer.tvTitle.setText(data.getTitle());
		TextChange(position, holer.etMsg, vholer);
		TextChange(position, holer.tvSelectMsg, vholer);
		holer.etMsg.setText(mCheckData[position]);
		holer.tvSelectMsg.setText(mCheckData[position]);
		if (!StringUtils.isEmpty(hint)) {
			holer.etMsg.setHint(hint);
		}

		if (type == 1) {
			holer.tvSelectMsg.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (onSetDataListener != null) {
						onSetDataListener.ChoiceNeed(v);
					}
				}
			});
		}
		CheckShowForPosition(type, holer);

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

		holer.etMsg.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				checkForShowTimeLine(vholer);
			}
		});

		checkForShowTimeLine(vholer);
		return convertView;
	}

	private void CheckShowForPosition(final int position, ViewHoler holer) {
		if (position == 1) {
			holer.tvSelectMsg.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (onSetDataListener != null) {
						onSetDataListener.ChoiceNeed(v);
					}
				}
			});
			holer.etMsg.setVisibility(View.GONE);
			holer.tvSelectMsg.setVisibility(View.VISIBLE);

		}

		if (position == 2) {
			holer.rgPriceSelect.setVisibility(View.VISIBLE);
			holer.rgPriceSelect.check(holer.rbHours.getId());
			holer.etMsg.setInputType(InputType.TYPE_CLASS_NUMBER);

		}
		if (position == (mData.size() - 1) && position != 0) {
			holer.llMarkLine.setVisibility(View.GONE);
			holer.etMsg.setSingleLine(false);
			holer.etMsg.setLines(3);
		}
	}

	class ViewHoler {
		@InjectView(R.id.iv_mark_top)
		ImageView ivMarkTop;
		@InjectView(R.id.ll_mark_line)
		LinearLayout llMarkLine;
		@InjectView(R.id.tv_message)
		TextView tvSelectMsg;
		@InjectView(R.id.et_message)
		EditText etMsg;
		@InjectView(R.id.tv_title)
		TextView tvTitle;
		@InjectView(R.id.rg_price_select)
		RadioGroup rgPriceSelect;
		@InjectView(R.id.rb_hours)
		RadioButton rbHours;

		public ViewHoler(View view) {
			ButterKnife.inject(this, view);
		}

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

	private void TextChange(final int position, View v, final ViewHoler holer) {

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
					String ss;
					if (holer.rgPriceSelect.getVisibility() == View.VISIBLE) {
						RadioButton radioButton = (RadioButton) holer.rgPriceSelect
								.findViewById(holer.rgPriceSelect
										.getCheckedRadioButtonId());
						ss = s.toString().trim()
								+ radioButton.getText().toString();
						mCheckData[4] = ss;
					}
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
					tvText.setTag(s.toString().trim());
					mCheckData[position] = s.toString().trim();
				}
			});
		}
	}

	public OnSetDataListener getOnSetDataListener() {
		return onSetDataListener;
	}

	public void setOnSetDataListener(OnSetDataListener onSetDataListener) {
		this.onSetDataListener = onSetDataListener;
	}

	public String[] getCheckData() {
		return mCheckData;
	}

	public interface OnSetDataListener {
		void ChoiceNeed(View v);

	}

}
