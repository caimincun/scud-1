package com.team.dream.runlegwork.dialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.interfaces.OnMyDialogClickListener;
import com.team.dream.runlegwork.utils.StringUtils;

@SuppressLint("NewApi")
public class DataPickDialogFragment extends DialogFragment implements
		DialogInterface.OnClickListener {

	@InjectView(R.id.numberPicker)
	NumberPicker np;
	@InjectView(R.id.numberPicker2)
	NumberPicker np2;
	@InjectView(R.id.numberPicker3)
	NumberPicker np3;

	public static final long DAY_MILLIS = 24 * 60 * 60 * 1000;
	private Calendar startTime;
	private Calendar endTime;
	private OnMyDialogClickListener listener;
	private static final String DATA_KEY = "key";
	private String[] lastTime;
	private String[] dateString;
	private String[] hoursString;
	private String[] minString;

	public static DataPickDialogFragment newInstance(String date) {
		DataPickDialogFragment dataPickDialogFragment = new DataPickDialogFragment();
		Bundle bundle = new Bundle();
		bundle.putString(DATA_KEY, date);
		dataPickDialogFragment.setArguments(bundle);
		return dataPickDialogFragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String date = getArguments().getString(DATA_KEY);
		if (!StringUtils.isEmpty(date)) {
			lastTime = date.split(" ");
		}

	}

	@SuppressLint({ "NewApi", "InflateParams" })
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		View view = LayoutInflater.from(getActivity()).inflate(
				R.layout.fragment_dialog_datapicker, null, false);
		ButterKnife.inject(this, view);

		startTime = new GregorianCalendar();
		startTime.setTime(new Date());
		endTime = new GregorianCalendar();
		endTime.setTime(new Date());
		endTime.add(Calendar.MONTH, 1);
		dateString = getData(startTime, endTime);
		hoursString = getMonth();
		minString = getMin();
		int[] indexs = getDateIndex();

		np.setDisplayedValues(dateString);
		np.setMaxValue(dateString.length - 1);
		np.setMinValue(0);
		np.setValue(indexs[0]);
		np.setFocusable(true);
		np.setFocusableInTouchMode(true);
		// 不能转
		np.setWrapSelectorWheel(false);
		np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

		np2.setDisplayedValues(hoursString);
		np2.setMaxValue(hoursString.length - 1);
		np2.setMinValue(0);
		np2.setValue(indexs[1]);
		np2.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

		np3.setDisplayedValues(minString);
		np3.setMaxValue(minString.length - 1);
		np3.setMinValue(0);
		np3.setValue(indexs[2]);
		np3.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

		AlertDialog.Builder b = new AlertDialog.Builder(getActivity())
				.setView(view).setTitle(getTitle())
				.setPositiveButton("确认", this) // 设置回调函数
				.setNegativeButton("取消", this); // 设置回调函数
		return b.create();

	}

	public OnMyDialogClickListener getListener() {
		return listener;
	}

	public void setListener(OnMyDialogClickListener listener) {
		this.listener = listener;
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		boolean isCancel = false;
		if (which == AlertDialog.BUTTON_NEGATIVE) { // 判断用户所按何键
			isCancel = true;
		}
		if (listener != null) {
			listener.onDialogDone(getTag(), isCancel,
					np.getDisplayedValues()[np.getValue()] + " " + np2.getDisplayedValues()[np2.getValue()] + " " + np3.getDisplayedValues()[np3.getValue()]);
		}

	}

	private String getTitle() {
		return getString(R.string.select_time);
	}

	private String[] getMonth() {
		List<String> temp = new ArrayList<String>();
		for (int i = 0; i < 24; i++) {
			if (i < 10) {
				temp.add("0" + i);
			} else {
				temp.add(i + "");
			}
		}
		return temp.toArray(new String[temp.size()]);
	}

	private String[] getMin() {
		List<String> temp = new ArrayList<String>();
		for (int i = 0; i <= 11; i++) {

			if (i * 5 < 10) {
				temp.add("0" + i * 5);
			} else {
				temp.add(i * 5 + "");
			}
		}
		return temp.toArray(new String[temp.size()]);
	}

	private String[] getData(Calendar startTime, Calendar endTime) {
		List<String> temp = new ArrayList<String>();
		// int startTime.getTimeInMillis();
		int differTime = (int) ((endTime.getTimeInMillis() - startTime
				.getTimeInMillis()) / DAY_MILLIS);

		for (int i = 0; i <= differTime; i++) {
			temp.add(getDateString(startTime));
			startTime.add(Calendar.DATE, 1);
		}

		return temp.toArray(new String[temp.size()]);
	}

	private int[] getDateIndex() {
		int dateIndex = 0;
		int hoursIndex = 0;
		int minIndex = 0;
		if (lastTime != null) {
			for (int i = 0; i < dateString.length; i++) {
				if (lastTime[0].equals(dateString[i])) {
					dateIndex = i;
					break;
				}
			}
			for (int i = 0; i < hoursString.length; i++) {
				if (lastTime[1].equals(dateString[i])) {
					hoursIndex = i;
					break;
				}
			}
			for (int i = 0; i < minString.length; i++) {
				if (lastTime[2].equals(dateString[i])) {
					minIndex = i;
					break;
				}
			}

		}
		return new int[] { dateIndex, hoursIndex, minIndex };

	}

	private String getDateString(Calendar startTime) {

		// String year = startTime.get(Calendar.YEAR) + "年 ";
		String year = startTime.get(Calendar.YEAR) + "-";
		String month = (startTime.get(Calendar.MONTH) + 1) + "-";
		String day = startTime.get(Calendar.DATE) + "";
		System.out.println("The YEAR is: " + startTime.get(Calendar.YEAR));
		System.out.println("The MONTH is: " + startTime.get(Calendar.MONTH));
		System.out.println("The DAY is: " + startTime.get(Calendar.DATE));

		// String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"
		// };

		int w = startTime.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 0;
		}
		// return year + month + day + weekDays[w];
		return year + month + day;
	}

}
