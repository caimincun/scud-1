package com.team.dream.runlegwork.net.response;

import java.util.ArrayList;
import java.util.List;

import com.team.dream.runlegwork.entity.NearUserInfo;

public class NearUserResponse extends OpteratorResponse {

	private List<NearUserInfo> data;

	public List<NearUserInfo> getListSucRes() {
		return data;
	}

	public void setListSucRes(List<NearUserInfo> listSucRes) {
		if (listSucRes.size() == 0 || listSucRes == null) {
			listSucRes = new ArrayList<NearUserInfo>();
		}
		this.data = listSucRes;
	}

}
