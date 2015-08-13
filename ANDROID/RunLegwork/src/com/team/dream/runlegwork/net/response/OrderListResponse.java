package com.team.dream.runlegwork.net.response;

import java.util.List;

import com.team.dream.runlegwork.entity.UserOrder;

public class OrderListResponse extends OpteratorResponse {
	
	private List<UserOrder> data;

	public List<UserOrder> getData() {
		return data;
	}

	public void setData(List<UserOrder> data) {
		this.data = data;
	}
	

}
