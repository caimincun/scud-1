package com.team.dream.runlegwork.utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class KeybordUtils {

	/**
	 * 关闭键盘 hideInputMethod
	 * 
	 * @param mActivity
	 * @author Administrator
	 * @since JDK 1.7
	 */
	public static void hideKeybord(Activity mActivity) {

		InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
		// //得到InputMethodManager的实例
		if (imm != null) {

			if (imm.isActive()) {

				// 当前窗口接收文本编辑时返回true
				boolean b = imm.isAcceptingText();
				if (b) {
					imm.hideSoftInputFromWindow(mActivity.getCurrentFocus().getWindowToken(), 0); // (mActivity是当前的Activity)
				}

			}
		}

	}

	/**
	 * 打开软键盘
	 * 
	 * @param mEditText
	 *            输入框
	 * @param mContext
	 *            上下文
	 */
	public static void openKeybord(EditText mEditText, Context mContext) {

		InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

		imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

	}
}
