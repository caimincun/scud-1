package com.team.dream.runlegwork_data.net.response;

import com.team.dream.runlegwork_data.entity.User;

public class UserRegisterResponse extends OpteratorResponse {

	private User data;

	public User getData() {
		return data;
	}

	public void setData(User data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "UserRegisterResponse [data=" + data.toString() + ", respstatus:"+getRespStatus().toString()+"]";
	}
	
	

}
