package com.team.dream.runlegwork.entity;

public class FunctionItem {
	
	private String fuctionName;
	private int fuctionRes;
	public String getFuctionName() {
		return fuctionName;
	}
	public void setFuctionName(String fuctionName) {
		this.fuctionName = fuctionName;
	}
	public int getFuctionRes() {
		return fuctionRes;
	}
	public void setFuctionRes(int fuctionRes) {
		this.fuctionRes = fuctionRes;
	}
	public FunctionItem(String fuctionName, int fuctionRes) {
		super();
		this.fuctionName = fuctionName;
		this.fuctionRes = fuctionRes;
	}
	public FunctionItem(int fuctionRes) {
		super();
		this.fuctionRes = fuctionRes;
	}
	
	
	

}
