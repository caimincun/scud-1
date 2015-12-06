package com.team.dream.runlegwork.net.response;

import java.util.List;

import com.team.dream.runlegwork.entity.SkillAndUser;

public class SkillpeopleDetailResponse extends OpteratorResponse {
	private List<SkillAndUser> data;

	/**
	 * @return the data
	 */
	public List<SkillAndUser> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<SkillAndUser> data) {
		this.data = data;
	}
	
}
