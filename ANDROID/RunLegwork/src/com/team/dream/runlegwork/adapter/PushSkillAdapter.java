package com.team.dream.runlegwork.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.entity.ShowTimeLine;
import com.team.dream.runlegwork.entity.Skill;
import com.team.dream.runlegwork.utils.StringUtils;

public class PushSkillAdapter extends BaseAdapter {

	private static final int TYPE_EDIT_ONE = 0;
	private static final int TYPE_SELECT = 1;
	private static final int TYPE_EDIT_TWO = 2;
	private static final int TYPE_RIDIOGOURP = 3;

	private Context mContext;
	private List<ShowTimeLine> mData = new ArrayList<ShowTimeLine>();
	private String[] mCheckData = new String[5];
	private HashMap<String, ShowTimeLine> dataMap = new HashMap<String, ShowTimeLine>();
	private OnSetDataListener onSetDataListener;

	public PushSkillAdapter(Context mContext) {
		this.mContext = mContext;

		mData.add(new ShowTimeLine(mContext.getString(R.string.my_skill_need),
				mContext.getString(R.string.please_fill_your_skill_name),
				TYPE_EDIT_ONE));
		mCheckData[0] = mContext.getString(R.string.my_skill_need);
		mData.add(new ShowTimeLine(mContext.getString(R.string.my_push_scope),
				null, TYPE_SELECT));
		mCheckData[1] = mContext.getString(R.string.my_push_scope);
		mData.add(new ShowTimeLine(mContext.getString(R.string.my_skill_price),
				mContext.getString(R.string.please_fill_your_skill_price),
				TYPE_RIDIOGOURP));
		mCheckData[2] = mContext.getString(R.string.my_skill_price);
		mData.add(new ShowTimeLine(
				mContext.getString(R.string.my_skill_detail), mContext
						.getString(R.string.please_fill_your_skill_detail),
				TYPE_EDIT_TWO));
		mCheckData[3] = mContext.getString(R.string.my_skill_detail);

		mCheckData[4] = "Unint";
		mData.add(new ShowTimeLine("Unint", mContext
				.getString(R.string.please_fill_your_skill_detail),-1));

	}

	public PushSkillAdapter(Context mContext, Skill skill) {
		this.mContext = mContext;
		mData.add(new ShowTimeLine(mContext.getString(R.string.my_skill_need),
				mContext.getString(R.string.please_fill_your_skill_name),
				TYPE_EDIT_ONE, skill.getSkillTitle()));
		mCheckData[0] = mContext.getString(R.string.my_skill_need);
		dataMap.put(mCheckData[0],new ShowTimeLine(mContext.getString(R.string.my_skill_need),
				mContext.getString(R.string.please_fill_your_skill_name),
				TYPE_EDIT_ONE, skill.getSkillTitle()));
		
		mData.add(new ShowTimeLine(mContext.getString(R.string.my_push_scope),
				null, TYPE_SELECT, skill.getSkillSort()));
		mCheckData[1] = mContext.getString(R.string.my_push_scope);
		dataMap.put(mCheckData[1],new ShowTimeLine(mContext.getString(R.string.my_push_scope),
				null, TYPE_SELECT, skill.getSkillSort()));
		
		mData.add(new ShowTimeLine(mContext.getString(R.string.my_skill_price),
				mContext.getString(R.string.please_fill_your_skill_price),
				TYPE_RIDIOGOURP, skill.getSkillMoney()));
		mCheckData[2] = mContext.getString(R.string.my_skill_price);
		dataMap.put(mCheckData[2],new ShowTimeLine(mContext.getString(R.string.my_skill_price),
				mContext.getString(R.string.please_fill_your_skill_price),
				TYPE_RIDIOGOURP, skill.getSkillMoney()));
		
		mData.add(new ShowTimeLine(
				mContext.getString(R.string.my_skill_detail), mContext
						.getString(R.string.please_fill_your_skill_detail),
				TYPE_EDIT_TWO, skill.getSkillContent()));
		mCheckData[3] = mContext.getString(R.string.my_skill_detail);
		dataMap.put(mCheckData[3],new ShowTimeLine(
				mContext.getString(R.string.my_skill_detail), mContext
				.getString(R.string.please_fill_your_skill_detail),
		TYPE_EDIT_TWO, skill.getSkillContent()));
		
		mCheckData[4] = "Unint";
		mData.add(new ShowTimeLine("Unint", mContext
				.getString(R.string.please_fill_your_skill_detail),
				-1,skill.getSkillUnit().split(skill.getSkillMoney())[0]));
		dataMap.put(mCheckData[4], new ShowTimeLine("Unint", mContext
				.getString(R.string.please_fill_your_skill_detail),
				-1,skill.getSkillUnit().split(skill.getSkillMoney())[0]));
	}

	@Override
	public int getCount() {
		return mData.size()-1;
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
		final ShowTimeLine data = mData.get(position);
		int type = getItemViewType(position);
		ImageView ivMarkTop = null;
		LinearLayout llMarkLine;
		TextView tvSelectMsg = null;
		EditText etMsg = null;
		TextView tvTitle;
		RadioGroup rgPriceSelect;
		RadioButton rbHours;
		RadioButton rbTwo;
		RadioButton rbThree;
		RadioButton rbFour;
		switch (type) {
		case TYPE_EDIT_ONE:
			if (null == convertView) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.adapter_push_skill_item, parent, false);
			}
			etMsg = (EditText) convertView.findViewById(R.id.et_message);
			etMsg.setHint(data.getHint());
			etMsg.setText(data.getInputData());
			findmsg(etMsg, data, position);
			break;
		case TYPE_EDIT_TWO:
			if (null == convertView) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.adapter_push_skill_edit_two, parent, false);
			}
			etMsg = (EditText) convertView.findViewById(R.id.et_message);
			etMsg.setHint(data.getHint());
			etMsg.setText(data.getInputData());
			findmsg(etMsg, data, position);
			break;
		case TYPE_SELECT:
			if (null == convertView) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.adapter_push_type, parent, false);
			}
			tvSelectMsg = (TextView) convertView.findViewById(R.id.tv_message);
			tvSelectMsg.setText(data.getInputData());
			tvSelectMsg.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (onSetDataListener != null) {
						onSetDataListener.ChoiceNeed(v, data);
					}
				}
			});
			break;
		case TYPE_RIDIOGOURP:
			if (null == convertView) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.adapter_push_skill_price, parent, false);
			}
			rgPriceSelect = (RadioGroup) convertView
					.findViewById(R.id.rg_price_select);
			rbHours = (RadioButton) convertView.findViewById(R.id.rb_hours);
			rbTwo = (RadioButton) convertView.findViewById(R.id.rb_two);
			rbThree = (RadioButton) convertView.findViewById(R.id.rb_three);
			rbFour = (RadioButton) convertView.findViewById(R.id.rb_four);
			etMsg = (EditText) convertView.findViewById(R.id.et_message);
			etMsg.setText(data.getInputData());
			rgPriceSelect
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(RadioGroup group,
								int checkedId) {
							RadioButton rb = (RadioButton) group
									.findViewById(checkedId);

							ShowTimeLine line = new ShowTimeLine(data
									.getTitle(), data.getHint(), data.getType());
							line.setInputData(rb.getText().toString());
							dataMap.put(mCheckData[4], line);

						}
					});
			findmsg(etMsg, data, position);
			if (dataMap.get(mCheckData[4]) == null) {
				rgPriceSelect.check(rbHours.getId());
			} else {
				if (dataMap.get(mCheckData[4]).getInputData()
						.equals(rbHours.getText().toString().trim())) {
					rgPriceSelect.check(rbHours.getId());
				} else if (dataMap.get(mCheckData[4]).getInputData()
						.equals(rbTwo.getText().toString().trim())) {
					rgPriceSelect.check(rbTwo.getId());
				} else if (dataMap.get(mCheckData[4]).getInputData()
						.equals(rbThree.getText().toString().trim())) {
					rgPriceSelect.check(rbThree.getId());
				} else if (dataMap.get(mCheckData[4]).getInputData()
						.equals(rbFour.getText().toString().trim())) {
					rgPriceSelect.check(rbFour.getId());
				}
			}

			break;
		}

		tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
		llMarkLine = (LinearLayout) convertView.findViewById(R.id.ll_mark_line);
		ivMarkTop = (ImageView) convertView.findViewById(R.id.iv_mark_top);
		tvTitle.setText(data.getTitle());
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
		if (getItemViewType(position) == TYPE_EDIT_TWO) {
			llMarkLine.setVisibility(View.GONE);
		}
	}

	public HashMap<String, ShowTimeLine> getDataMap() {
		return dataMap;
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
		void ChoiceNeed(View v, ShowTimeLine item);

	}

}
