package com.team.dream.runlegwork.entity;

public class Skill {

	// 技能分类标志
	private int skillSort; // 不同的数字代表不同的技能，需要写一个技能类
	// 技能标志
	private String skillToken;

	// 技能名称
	private String skillTitle;
	// 价格
	private double skillMoney;
	// 技能详情
	private String skillContent;
	// 做平展示
	private String skillPicture;
	// 交易方式
	private int tradeFlag; // 1： 线上交易 2： 线下交易
	// 备注
	private String skillRemark;

	public int getSkillSort() {
		return skillSort;
	}

	public void setSkillSort(int skillSort) {
		this.skillSort = skillSort;
	}

	public String getSkillToken() {
		return skillToken;
	}

	public void setSkillToken(String skillToken) {
		this.skillToken = skillToken;
	}

	public String getSkillTitle() {
		return skillTitle;
	}

	public void setSkillTitle(String skillTitle) {
		this.skillTitle = skillTitle;
	}

	public double getSkillMoney() {
		return skillMoney;
	}

	public void setSkillMoney(double skillMoney) {
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
