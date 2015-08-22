package com.team.dream.runlegwork.net.response;

import java.util.List;

import com.team.dream.runlegwork.entity.Skill;

public class ListUserSkillResponse extends OpteratorResponse {
	private List<Skill> data;

	public List<Skill> getData() {
		return data;
	}

	public void setData(List<Skill> data) {
		this.data = data;
	}

}
