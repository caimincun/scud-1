package com.team.dream.runlegwork.net.response;

import com.team.dream.runlegwork.entity.UserInfo;

public class UserInfoResponse extends OpteratorResponse {
	private UserInfo data;

	public UserInfo getData() {
		return data;
	}

	public void setData(UserInfo data) {
		this.data = data;
	}
}
