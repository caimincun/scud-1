package com.team.dream.runlegwork.net.response;

import com.team.dream.runlegwork.entity.RespStatus;

public class OpteratorResponse {

	private RespStatus respStatus;

	public RespStatus getRespStatus() {
		return respStatus;
	}

	public void setRespStatus(RespStatus respStatus) {
		this.respStatus = respStatus;
	}

	@Override
	public String toString() {
		return "OpteratorResponse [respStatus=" + respStatus.toString() + "]";
	}


}
