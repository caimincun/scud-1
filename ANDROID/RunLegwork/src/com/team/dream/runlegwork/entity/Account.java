package com.team.dream.runlegwork.entity;

public class Account {

	/**id**/
	private String id;
	/**对User表的引用**/
	private String UserId;
	/**用户真实姓名**/
	private String UserRealName;
	/**身份证号码**/
	private String UserIdCardNum;
	/**邮箱**/
	private String UserInfoEmail;
	/**用户性别**/
	private int UserInfoSex;
	/**用户头像**/
	private String UserInfoPicture;
	/**用户标签（教师啊，美容师什么的）  （ 这些信息在展示附近人的试试需要展示出来）**/
	private String UserInfoLabel;
	/**个性签名**/
	private String UserInfoSignature;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getUserRealName() {
		return UserRealName;
	}
	public void setUserRealName(String userRealName) {
		UserRealName = userRealName;
	}
	public String getUserIdCardNum() {
		return UserIdCardNum;
	}
	public void setUserIdCardNum(String userIdCardNum) {
		UserIdCardNum = userIdCardNum;
	}
	public String getUserInfoEmail() {
		return UserInfoEmail;
	}
	public void setUserInfoEmail(String userInfoEmail) {
		UserInfoEmail = userInfoEmail;
	}
	public int getUserInfoSex() {
		return UserInfoSex;
	}
	public void setUserInfoSex(int userInfoSex) {
		UserInfoSex = userInfoSex;
	}
	public String getUserInfoPicture() {
		return UserInfoPicture;
	}
	public void setUserInfoPicture(String userInfoPicture) {
		UserInfoPicture = userInfoPicture;
	}
	public String getUserInfoLabel() {
		return UserInfoLabel;
	}
	public void setUserInfoLabel(String userInfoLabel) {
		UserInfoLabel = userInfoLabel;
	}
	public String getUserInfoSignature() {
		return UserInfoSignature;
	}
	public void setUserInfoSignature(String userInfoSignature) {
		UserInfoSignature = userInfoSignature;
	}
	
	public static class Sex {
		public static final int MALE = 0;
		public static final int FEMALE = 1;
		public static final int SECRET = 2;
	}
	
	public Account(){
		
	}
	public Account(String username,String UserIdCardNum,String UserInfoEmail,int UserInfoSex,String UserInfoLabel,String UserInfoSignature){
		this.UserRealName = username;
		this.UserIdCardNum = UserIdCardNum;
		this.UserInfoEmail = UserInfoEmail;
		this.UserInfoSex = UserInfoSex;
		this.UserInfoLabel = UserInfoLabel;
		this.UserInfoSignature = UserInfoSignature;
		
	}
}
