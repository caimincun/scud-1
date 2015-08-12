package com.team.dream.runlegwork.net.response;

import java.util.ArrayList;
import java.util.List;

import com.team.dream.runlegwork.entity.UserOrder;

public class RequirementResponse extends OpteratorResponse {
	private List<UserOrder> data;

	public List<UserOrder> getListSucRes() {
		return data;
	}

	public void setListSucRes(List<UserOrder> listSucRes) {
		if (listSucRes.size() == 0 || listSucRes == null) {
			listSucRes = new ArrayList<UserOrder>();
		}
		this.data = listSucRes;
	}
}
