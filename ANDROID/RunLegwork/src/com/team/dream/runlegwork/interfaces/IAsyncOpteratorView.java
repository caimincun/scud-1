package com.team.dream.runlegwork.interfaces;

public interface IAsyncOpteratorView {
	/**
	 * 开始进行异步操作
	 * 
	 * @param alertMessage
	 *            异步操作的提示信息
	 */
	void start(String alertMessage);

	void start(int resStringId);

	/**
	 * 操作完成
	 */
	void finish();

	/*
	 * 操作结束并提供操作结果信息 一段时间内界面自动关闭
	 */
	void finish(String alertMessage);

	void finish(int resStringId);
}
