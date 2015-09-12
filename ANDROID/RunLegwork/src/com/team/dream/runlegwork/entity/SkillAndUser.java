package com.team.dream.runlegwork.entity;

public class SkillAndUser extends Skill{
	 //用户头像
    private String userPicture;
    //用户距离
    private Integer distance;
    //用户姓名
    private String userName;
	/**
	 * @return the userPicture
	 */
	public String getUserPicture() {
		return userPicture;
	}
	/**
	 * @param userPicture the userPicture to set
	 */
	public void setUserPicture(String userPicture) {
		this.userPicture = userPicture;
	}
	/**
	 * @return the distance
	 */
	public Integer getDistance() {
		return distance;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
