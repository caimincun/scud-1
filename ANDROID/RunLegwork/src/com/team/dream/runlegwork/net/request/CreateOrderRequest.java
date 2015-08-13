package com.team.dream.runlegwork.net.request;

public class CreateOrderRequest {

	// 订单名称
	private String orderTitle;
	// 订单内容
	private String orderContent;
	// 邀约时间
	private String orderStartTime;
	// 订单限时（订单完成有效时间）
	private String orderLimitTime;
	// 邀约地址
	private String orderServiceAddress;
	// 消息推送范围
	private String orderCallScope;
	// 定金
	private String orderMoney;

	public String getOrderTitle() {
		return orderTitle;
	}

	public void setOrderTitle(String orderTitle) {
		this.orderTitle = orderTitle;
	}

	public String getOrderContent() {
		return orderContent;
	}

	public void setOrderContent(String orderContent) {
		this.orderContent = orderContent;
	}

	public String getOrderStartTime() {
		return orderStartTime;
	}

	public void setOrderStartTime(String orderStartTime) {
		this.orderStartTime = orderStartTime;
	}

	public String getOrderLimitTime() {
		return orderLimitTime;
	}

	public void setOrderLimitTime(String orderLimitTime) {
		this.orderLimitTime = orderLimitTime;
	}

	public String getOrderServiceAddress() {
		return orderServiceAddress;
	}

	public void setOrderServiceAddress(String orderServiceAddress) {
		this.orderServiceAddress = orderServiceAddress;
	}

	public String getOrderCallScope() {
		return orderCallScope;
	}

	public void setOrderCallScope(String orderCallScope) {
		this.orderCallScope = orderCallScope;
	}

	public String getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(String orderMoney) {
		this.orderMoney = orderMoney;
	}

}
