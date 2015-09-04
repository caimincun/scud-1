package com.team.dream.runlegwork.net.response;

import java.util.List;

import com.team.dream.runlegwork.entity.NearUserInfo;
import com.team.dream.runlegwork.entity.UserInfo;

public class AcptsPersonResponse extends OpteratorResponse {

	List<NearUserInfo> data;

	public List<NearUserInfo> getData() {
		return data;
	}

	public void setData(List<NearUserInfo> data) {
		this.data = data;
	}

}
