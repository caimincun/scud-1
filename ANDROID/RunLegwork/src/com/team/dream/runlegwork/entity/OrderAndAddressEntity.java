package com.team.dream.runlegwork.entity;

import java.util.List;

public class OrderAndAddressEntity {
	private int receiptId;
	private List<Product> orders;
	private String storeToken;
	/**
	 * @return the receiptId
	 */
	public int getReceiptId() {
		return receiptId;
	}
	/**
	 * @param receiptId the receiptId to set
	 */
	public void setReceiptId(int receiptId) {
		this.receiptId = receiptId;
	}
	/**
	 * @return the orders
	 */
	public List<Product> getOrders() {
		return orders;
	}
	/**
	 * @param orders the orders to set
	 */
	public void setOrders(List<Product> orders) {
		this.orders = orders;
	}
	/**
	 * @return the storeToken
	 */
	public String getStoreToken() {
		return storeToken;
	}
	/**
	 * @param storeToken the storeToken to set
	 */
	public void setStoreToken(String storeToken) {
		this.storeToken = storeToken;
	}
}
