package com.team.dream.runlegwork.net.response;

import java.util.List;

import com.team.dream.runlegwork.entity.Address;

public class AddressResponse  extends OpteratorResponse{
	private List<Address> data;

	/**
	 * @return the data
	 */
	public List<Address> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<Address> data) {
		this.data = data;
	}
	
}
