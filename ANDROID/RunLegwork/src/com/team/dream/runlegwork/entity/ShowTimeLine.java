package com.team.dream.runlegwork.entity;

public class ShowTimeLine {
	private String title;
	private String hint;
	private int type;
	private String inputData;
	
	
	public String getInputData() {
		return inputData;
	}

	public void setInputData(String inputData) {
		this.inputData = inputData;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public String getHint() {
		return hint;
	}

	public ShowTimeLine(String title, String hint, int type) {
		super();
		this.title = title;
		this.hint = hint;
		this.type = type;
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
