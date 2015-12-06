package com.team.dream.runlegwork.net.response;

import java.util.List;

import com.team.dream.runlegwork.entity.Skill;

public class SkillListResponse  extends OpteratorResponse{
	private List<Skill> data;

	/**
	 * @return the data
	 */
	public List<Skill> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<Skill> data) {
		this.data = data;
	}
	
}
