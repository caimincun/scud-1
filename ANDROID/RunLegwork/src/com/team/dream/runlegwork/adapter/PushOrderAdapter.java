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
	private static final int TYPE_SELECT_TWO = 4;

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
		mCheckData[0]=mContext.getString(R.string.my_push_need);
		mData.add(new ShowTimeLine(mContext.getString(R.string.my_push_scope),
				null, TYPE_SELECT));
		mCheckData[1]=mContext.getString(R.string.my_push_scope);
		mData.add(new ShowTimeLine(mContext.getString(R.string.my_push_detail),
				mContext.getString(R.string.please_enter_your_detail),
				TYPE_EDIT_TWO));
		mCheckData[2]=mContext.getString(R.string.my_push_detail);
		mData.add(new ShowTimeLine(mContext.getString(R.string.my_push_time),
				null, TYPE_SELECT_TWO));
		mCheckData[3]=mContext.getString(R.string.my_push_time);
		mData.add(new ShowTimeLine(
				mContext.getString(R.string.my_push_address), mContext
						.getString(R.string.please_enter_your_address),
				TYPE_EDIT_TWO));
		mCheckData[4]=mContext.getString(R.string.my_push_address);
		mData.add(new ShowTimeLine(mContext.getString(R.string.my_push_money),
				null, TYPE_EDIT_END));
		mCheckData[5]=mContext.getString(R.string.my_push_money);
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public int getViewTypeCount() {
		return 5;
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
		final ShowTimeLine data = mData.get(position);
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
			tvSelectMsg.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (onSetDataListener != null) {
						onSetDataListener.ChoiceNeed(v,data);
					}
				}
			});
			break;
		case TYPE_SELECT_TWO:
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
			tvSelectMsg.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (onSetDataListener != null) {
						onSetDataListener.SetDate(v, data);
					}
				}
			});
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

		checkForShowTimeLine(etMsg, tvSelectMsg, ivMarkTop, llMarkLine,
				position);
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

	public OnSetDataListener getOnSetDataListener() {
		return onSetDataListener;
	}

	public void setOnSetDataListener(OnSetDataListener onSetDataListener) {
		this.onSetDataListener = onSetDataListener;
	}

	public interface OnSetDataListener {
		void ChoiceNeed(View v,ShowTimeLine item);

		void SetDate(View v,ShowTimeLine item);
	}

	public String[] getData() {
		return mCheckData;
	}

	public HashMap<String, ShowTimeLine> getDataMap() {
		return dataMap;
	}

}
