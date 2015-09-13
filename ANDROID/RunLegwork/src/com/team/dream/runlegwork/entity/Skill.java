package com.team.dream.runlegwork.entity;

import java.io.Serializable;

public class Skill implements Serializable{

	// 技能分类标志
	private String skillSort; // 不同的数字代表不同的技能，需要写一个技能类
	// 技能名称
	private String skillTitle;
	// 价格
	private String skillMoney;
	// 技能详情
	private String skillContent;
	// 作品展示
	private String skillPicture;
	// 交易方式
	private int tradeFlag; // 1： 线上交易 2： 线下交易
	// 备注
	private String skillRemark;

	private String skillUnit;

	public String getSkillUnit() {
		return skillUnit;
	}

	public void setSkillUnit(String skillUnit) {
		this.skillUnit = skillUnit;
	}

	public String getSkillSort() {
		return skillSort;
	}

	public void setSkillSort(String skillSort) {
		this.skillSort = skillSort;
	}

	public String getSkillTitle() {
		return skillTitle;
	}

	public void setSkillTitle(String skillTitle) {
		this.skillTitle = skillTitle;
	}

	public String getSkillMoney() {
		return skillMoney;
	}

	public void setSkillMoney(String skillMoney) {
		this.skillMoney = skillMoney;
	}

	public String getSkillContent() {
		return skillContent;
	}

	public void setSkillContent(String skillContent) {
		this.skillContent = skillContent;
	}

	public String getSkillPicture() {
		return skillPicture;
	}

	public void setSkillPicture(String skillPicture) {
		this.skillPicture = skillPicture;
	}

	public int getTradeFlag() {
		return tradeFlag;
	}

	public void setTradeFlag(int tradeFlag) {
		this.tradeFlag = tradeFlag;
	}

	public String getSkillRemark() {
		return skillRemark;
	}

	public void setSkillRemark(String skillRemark) {
		this.skillRemark = skillRemark;
	}

}
