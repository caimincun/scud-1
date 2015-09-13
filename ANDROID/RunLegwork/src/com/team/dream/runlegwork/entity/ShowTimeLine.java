package com.team.dream.runlegwork.entity;

public class ShowTimeLine {
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
