package com.team.dream.runlegwork_data.entity;

public class RespStatus {

	public static final int CODE_BASE_SUCCESS = 0;
	
	
	private String msg;
	private int result;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "RespStatus [msg=" + msg + ", result=" + result + "]";
	}

	
	
}
