package com.team.dream.runlegwork.view;

import android.app.Activity;

public interface DataLoadingView {

	void showLoading();

	void hideLoading();

	void showRetry();

	void hideRetry();

	void showErrorMsg(String error);

	Activity getContext();

}
