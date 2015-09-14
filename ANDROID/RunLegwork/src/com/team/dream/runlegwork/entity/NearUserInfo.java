package com.team.dream.runlegwork.entity;

import java.io.Serializable;

import com.team.dream.runlegwork.utils.AppUtils;
import com.team.dream.runlegwork.utils.StringUtils;

public class NearUserInfo extends UserInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String distance;
	  //技能名称
    private String skillTitle;
    // 技能单价
    //价格
    private double skillMoney;
    // 单位
    private String skillUnit;

    private int isAccess;
    //位置
    private String location;
    
    
    
    
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	public int getIsAccess() {
		return isAccess;
	}

	public void setIsAccess(int isAccess) {
		this.isAccess = isAccess;
	}

	/**
	 * @return the skillTitle
	 */
	public String getSkillTitle() {
		return skillTitle;
	}

	/**
	 * @param skillTitle the skillTitle to set
	 */
	public void setSkillTitle(String skillTitle) {
		this.skillTitle = skillTitle;
	}

	/**
	 * @return the skillMoney
	 */
	public double getSkillMoney() {
		return skillMoney;
	}

	/**
	 * @param skillMoney the skillMoney to set
	 */
	public void setSkillMoney(double skillMoney) {
		this.skillMoney = skillMoney;
	}

	/**
	 * @return the skillUnit
	 */
	public String getSkillUnit() {
		return skillUnit;
	}

	/**
	 * @param skillUnit the skillUnit to set
	 */
	public void setSkillUnit(String skillUnit) {
		this.skillUnit = skillUnit;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public boolean hasDantce() {
		if (StringUtils.isEmpty(distance)) {
			return false;
		}
		return true;
	}

	public String getUserDantce() {
		if (hasDantce()) {
			Double distance = Double.parseDouble(getDistance());
			if (distance > 0 && distance < 1000) {
				String temp = AppUtils.DoubleDP(distance, "#.0");
				return temp + "m";
			} else {
				distance = distance / 1000;
				String temp = AppUtils.DoubleDP(distance, "#.0");
				return temp + "km";
			}

		}
		return null;
	}
}
