package com.team.dream.runlegwork.net.response;

import java.util.List;

import com.team.dream.runlegwork.entity.Store;

public class ShopListResponse extends OpteratorResponse{
	private List<Store> data;

	/**
	 * @return the data
	 */
	public List<Store> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<Store> data) {
		this.data = data;
	}
	
}
