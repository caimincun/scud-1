package com.team.dream.runlegwork.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.R.bool;
import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
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
import com.team.dream.runlegwork.entity.ShowTimeLine;
import com.team.dream.runlegwork.utils.StringUtils;

public class PushOrderAdapter extends BaseAdapter {

	private static final int TYPE_EDIT_ONE = 0;
	private static final int TYPE_SELECT = 1;
	private static final int TYPE_EDIT_TWO = 2;
	private static final int TYPE_EDIT_END = 3;

	private Context mContext;
	private List<ShowTimeLine> mData = new ArrayList<ShowTimeLine>();
	private HashMap<String, ShowTimeLine> dataMap = new HashMap<String, ShowTimeLine>();
	private OnSetDataListener onSetDataListener;
	private String[] mCheckData = new String[6];

	public PushOrderAdapter(Context mContext) {
		this.mContext = mContext;

		mData.add(new ShowTimeLine(mContext.getString(R.string.my_push_need),
				mContext.getString(R.string.please_enter_your_need),
				TYPE_EDIT_ONE));
		mData.add(new ShowTimeLine(mContext.getString(R.string.my_push_scope),
				null, TYPE_SELECT));
		mData.add(new ShowTimeLine(mContext.getString(R.string.my_push_detail),
				mContext.getString(R.string.please_enter_your_detail),
				TYPE_EDIT_TWO));
		mData.add(new ShowTimeLine(mContext.getString(R.string.my_push_time),
				null, TYPE_SELECT));
		mData.add(new ShowTimeLine(
				mContext.getString(R.string.my_push_address), mContext
						.getString(R.string.please_enter_your_address),
				TYPE_EDIT_TWO));
		mData.add(new ShowTimeLine(mContext.getString(R.string.my_push_money),
				null, TYPE_EDIT_END));
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public int getViewTypeCount() {
		return 4;
	}

	@Override
	public int getItemViewType(int position) {
		int type = mData.get(position).getType();
		return type;
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
	public View getView(int postion, View convertView, ViewGroup parent) {
		final int position = (int) getItemId(postion);
		ShowTimeLine data = mData.get(position);
		ShowTimeLine item;
		int type = getItemViewType(position);
		// ViewHoler holer = null;
		ImageView ivMarkTop = null;
		EditText etMsg = null;
		LinearLayout llMarkLine = null;
		TextView tvTipMsg;
		TextView tvTitle = null;
		TextView tvSelectMsg = null;

		String value;
		switch (type) {
		case TYPE_EDIT_ONE:
			if (null == convertView) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.adapter_push_order_item, parent, false);
			}
			tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
			etMsg = (EditText) convertView.findViewById(R.id.et_message);
			llMarkLine = (LinearLayout) convertView
					.findViewById(R.id.ll_mark_line);
			ivMarkTop = (ImageView) convertView.findViewById(R.id.iv_mark_top);
			findmsg(etMsg, data, postion);
			tvTitle.setText(data.getTitle());
			item = dataMap.get(data.getTitle());
			if (item != null) {
				value = item.getInputData();
				if (!TextUtils.isEmpty(value)) {
					etMsg.setText(value);
				}
			}
			etMsg.setHint(data.getHint());
			break;
		case TYPE_EDIT_TWO:
			if (null == convertView) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.adapter_push_order_item_two, parent, false);
			}
			tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
			etMsg = (EditText) convertView.findViewById(R.id.et_message);
			llMarkLine = (LinearLayout) convertView
					.findViewById(R.id.ll_mark_line);
			ivMarkTop = (ImageView) convertView.findViewById(R.id.iv_mark_top);
			findmsg(etMsg, data, postion);
			tvTitle.setText(data.getTitle());
			etMsg.setHint(data.getHint());
			break;
		case TYPE_SELECT:
			if (null == convertView) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.adapter_push_order_item_select, parent, false);
			}
			tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
			tvSelectMsg = (TextView) convertView.findViewById(R.id.tv_message);
			llMarkLine = (LinearLayout) convertView
					.findViewById(R.id.ll_mark_line);
			ivMarkTop = (ImageView) convertView.findViewById(R.id.iv_mark_top);
			// findmsg(convertView, data, postion);
			tvTitle.setText(data.getTitle());

			break;
		case TYPE_EDIT_END:

			if (null == convertView) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.adapter_push_order_item_end, parent, false);
			}
			tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
			etMsg = (EditText) convertView.findViewById(R.id.et_message);
			llMarkLine = (LinearLayout) convertView
					.findViewById(R.id.ll_mark_line);
			ivMarkTop = (ImageView) convertView.findViewById(R.id.iv_mark_top);
			findmsg(etMsg, data, postion);
			tvTitle.setText(data.getTitle());
			etMsg.setHint(data.getHint());

			break;
		}

		// etMsg.setOnFocusChangeListener(new OnFocusChangeListener() {
		//
		// @Override
		// public void onFocusChange(View v, boolean hasFocus) {
		// // checkForShowTimeLine(etMsg, tvSelectMsg, ivMarkTop, llMarkLine);
		// }
		// });
		checkForShowTimeLine(etMsg, tvSelectMsg, ivMarkTop, llMarkLine,
				position);
		// if (null == convertView) {
		// if (type == 5) {
		// convertView = LayoutInflater.from(mContext).inflate(
		// R.layout.adapter_push_order_item_end, parent, false);
		// } else {
		//
		// convertView = LayoutInflater.from(mContext).inflate(
		// R.layout.adapter_push_order_item, parent, false);
		// }
		// holer = new ViewHoler(convertView);
		// convertView.setTag(holer);
		// } else {
		// holer = (ViewHoler) convertView.getTag();
		// }
		// final ViewHoler vholer = holer;
		// String hint = data.getHint();
		// holer.tvTitle.setText(data.getTitle());
		// TextChange(position, holer.etMsg, vholer);
		//
		// holer.etMsg.setText(mCheckData[position]);
		// holer.tvSelectMsg.setText(mCheckData[position]);
		//
		// switch (type) {
		// case 0:
		//
		// break;
		// case 1:
		// tvSelectMsgState(position, vholer);
		// holer.tvSelectMsg.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// if (onSetDataListener != null) {
		// onSetDataListener.ChoiceNeed(v);
		// }
		// }
		// });
		// break;
		// case 2:
		// holer.etMsg.setLines(3);
		// break;
		// case 3:
		// tvSelectMsgState(position, vholer);
		// holer.tvSelectMsg.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// if (onSetDataListener != null) {
		// onSetDataListener.SetDate(v);
		// }
		// }
		// });
		// break;
		// case 4:
		// holer.etMsg.setLines(2);
		// break;
		// case 5:
		// typeMoney(holer);
		// break;
		//
		// }
		// if (!StringUtils.isEmpty(hint)) {
		// holer.etMsg.setHint(hint);
		// }
		//
		// holer.etMsg.setOnTouchListener(new OnTouchListener() {
		//
		// @SuppressLint("ClickableViewAccessibility")
		// @Override
		// public boolean onTouch(View v, MotionEvent event) {
		// if (event.getAction() == MotionEvent.ACTION_UP) {
		// touchedPosition = position;
		// }
		// return false;
		// }
		// });
		//
		// if (touchedPosition == position) {
		// // 如果当前的行下标和点击事件中保存的index一致，手动为EditText设置焦点。
		// holer.etMsg.requestFocus();
		//
		// } else {
		// holer.etMsg.clearFocus();
		// }
		//
		// holer.etMsg.setOnFocusChangeListener(new OnFocusChangeListener() {
		//
		// @Override
		// public void onFocusChange(View v, boolean hasFocus) {
		// checkForShowTimeLine(vholer);
		// }
		// });
		//
		// checkForShowTimeLine(holer);
		return convertView;
	}

	private void findmsg(EditText et, final ShowTimeLine item,
			final int position) {
		et.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View view, MotionEvent event) {
				// 在TOUCH的UP事件中，要保存当前的行下标，因为弹出软键盘后，整个画面会被重画
				// 在getView方法的最后，要根据index和当前的行下标手动为EditText设置焦点
				if (event.getAction() == MotionEvent.ACTION_UP) {
					touchedPosition = position;
				}
				return false;
			}
		});

		et.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable editable) {
			}

			public void beforeTextChanged(CharSequence text, int start,
					int count, int after) {
			}

			public void onTextChanged(CharSequence text, int start, int before,
					int count) {
				// 在这个地方添加你的保存文本内容的代码，如果不保存，你就等着重新输入吧
				// 而且不管你输入多少次，也不会有用的，因为getView全清了～～
				item.setInputData(text.toString());
				dataMap.put(item.getTitle(), item);
			}
		});

		// 这个地方可以添加将保存的文本内容设置到EditText上的代码，会有用的～～
		et.clearFocus();
		if (touchedPosition != -1 && touchedPosition == position) {
			// 如果当前的行下标和点击事件中保存的index一致，手动为EditText设置焦点。
			et.requestFocus();
		}

	}

	// private void typeMoney(ViewHoler holer) {
	// holer.llMarkLine.setVisibility(View.GONE);
	// // holer.etMsg.setInputType(InputType.TYPE_CLASS_NUMBER);
	// // holer.tvTipMsg.setVisibility(View.VISIBLE);
	// }
	//
	// private void tvSelectMsgState(final int position, final ViewHoler vholer)
	// {
	// vholer.etMsg.setVisibility(View.GONE);
	// vholer.tvSelectMsg.setVisibility(View.VISIBLE);
	//
	// TextChange(position, vholer.tvSelectMsg, vholer);
	// }

	private void checkForShowTimeLine(EditText etMsg, TextView tvSelectMsg,
			ImageView ivMarkTop, LinearLayout llMarkLine, int position) {
		boolean isFous = false;
		boolean isFillet = false;
		boolean isFilltv = false;
		boolean isFill = false;
		if (etMsg != null) {
			isFous = etMsg.isFocused();
			isFillet = !StringUtils.isEmpty(etMsg.getText().toString().trim());
		}
		if (tvSelectMsg != null) {
			isFilltv = !StringUtils.isEmpty(tvSelectMsg.getText().toString());
		}
		isFill = isFillet || isFilltv;
		if (isFous || isFill) {
			ivMarkTop.setImageResource(R.drawable.time_line_mark);
		} else {
			ivMarkTop.setImageResource(R.drawable.time_line_mark_unselect);
		}
		if (isFill) {
			llMarkLine.setBackgroundResource(R.drawable.sp_time_line_select);
		} else {
			llMarkLine.setBackgroundResource(R.drawable.sp_time_line);
		}
		llMarkLine.setVisibility(View.VISIBLE);
		if (getItemViewType(position) == TYPE_EDIT_END) {
			llMarkLine.setVisibility(View.GONE);
		}
	}

	//
	// private void checkForShowTimeLine(ViewHoler holer) {
	// boolean isFous = holer.etMsg.isFocused();
	// boolean isFill = !StringUtils.isEmpty(holer.etMsg.getText().toString()
	// .trim())
	// || !StringUtils.isEmpty(holer.tvSelectMsg.getText().toString());
	// if (isFous || isFill) {
	// holer.ivMarkTop.setImageResource(R.drawable.time_line_mark);
	// } else {
	// holer.ivMarkTop
	// .setImageResource(R.drawable.time_line_mark_unselect);
	// }
	// if (isFill) {
	// holer.llMarkLine
	// .setBackgroundResource(R.drawable.sp_time_line_select);
	// } else {
	// holer.llMarkLine.setBackgroundResource(R.drawable.sp_time_line);
	// }
	// }
	//
	// // class ViewHoler {
	// @InjectView(R.id.iv_mark_top)
	// ImageView ivMarkTop;
	// @InjectView(R.id.ll_mark_line)
	// LinearLayout llMarkLine;
	// @InjectView(R.id.et_message)
	// EditText etMsg;
	// @InjectView(R.id.tv_message)
	// TextView tvSelectMsg;
	// @InjectView(R.id.tv_tip_msg)
	// TextView tvTipMsg;
	// @InjectView(R.id.tv_title)
	// TextView tvTitle;
	//
	// public ViewHoler(View view) {
	// ButterKnife.inject(this, view);
	// }
	//
	// }

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

	// private void TextChange(final int position, View v, final ViewHoler
	// holer) {
	//
	// if (v instanceof EditText) {
	// final EditText etText = (EditText) v;
	// ((EditText) v).addTextChangedListener(new TextWatcher() {
	//
	// @Override
	// public void onTextChanged(CharSequence s, int start,
	// int before, int count) {
	// }
	//
	// @Override
	// public void beforeTextChanged(CharSequence s, int start,
	// int count, int after) {
	// }
	//
	// @Override
	// public void afterTextChanged(Editable s) {
	// etText.setTag(s.toString().trim());
	// mCheckData[position] = s.toString().trim();
	//
	// }
	// });
	// }
	// if (v instanceof TextView) {
	// final TextView tvText = (TextView) v;
	// ((TextView) v).addTextChangedListener(new TextWatcher() {
	//
	// @Override
	// public void onTextChanged(CharSequence s, int start,
	// int before, int count) {
	//
	// }
	//
	// @Override
	// public void beforeTextChanged(CharSequence s, int start,
	// int count, int after) {
	//
	// }
	//
	// @Override
	// public void afterTextChanged(Editable s) {
	// if (!StringUtils.isEmpty(s.toString().trim())) {
	// tvText.setTag(s.toString().trim());
	// checkForShowTimeLine(holer);
	// }
	//
	// mCheckData[position] = s.toString().trim();
	// }
	// });
	// }
	// }

	public String[] getData() {
		return mCheckData;
	}

	public HashMap<String, ShowTimeLine> getDataMap() {
		return dataMap;
	}

}
