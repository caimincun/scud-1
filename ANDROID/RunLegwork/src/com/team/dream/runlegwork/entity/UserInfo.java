package com.team.dream.runlegwork.entity;

public class UserInfo {

	/**id**/
	private String id;
	/**对User表的引用**/
	private String userId;
	/**用户真实姓名**/
	private String userRealName;
	/**身份证号码**/
	private String userIdCardNum;
	/**邮箱**/
	private String userInfoEmail;
	/**用户性别**/
	private int userInfoSex;
	/**用户头像**/
	private String userInfoPicture;
	/**用户标签（教师啊，美容师什么的）  （ 这些信息在展示附近人的试试需要展示出来）**/
	private String userInfoLabel;
	/**个性签名**/
	private String userInfoSignature;
	/**职业**/
	private String userInfoJob;
	private String userToken;
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	public String getUserInfoJob() {
		return userInfoJob;
	}
	public void setUserInfoJob(String userInfoJob) {
		this.userInfoJob = userInfoJob;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		userId = userId;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		userRealName = userRealName;
	}
	public String getUserIdCardNum() {
		return userIdCardNum;
	}
	public void setUserIdCardNum(String userIdCardNum) {
		userIdCardNum = userIdCardNum;
	}
	public String getUserInfoEmail() {
		return userInfoEmail;
	}
	public void setUserInfoEmail(String userInfoEmail) {
		userInfoEmail = userInfoEmail;
	}
	public int getUserInfoSex() {
		return userInfoSex;
	}
	public void setUserInfoSex(int userInfoSex) {
		userInfoSex = userInfoSex;
	}
	public String getUserInfoPicture() {
		return userInfoPicture;
	}
	public void setUserInfoPicture(String userInfoPicture) {
		userInfoPicture = userInfoPicture;
	}
	public String getUserInfoLabel() {
		return userInfoLabel;
	}
	public void setUserInfoLabel(String userInfoLabel) {
		userInfoLabel = userInfoLabel;
	}
	public String getUserInfoSignature() {
		return userInfoSignature;
	}
	public void setUserInfoSignature(String userInfoSignature) {
		userInfoSignature = userInfoSignature;
	}
	
	public static class Sex {
		public static final int MALE = 0;
		public static final int FEMALE = 1;
		public static final int SECRET = 2;
	}
	
	public UserInfo(){
		
	}
	public UserInfo(String username,String UserIdCardNum,String UserInfoEmail,int UserInfoSex,String UserInfoLabel,String UserInfoSignature,String userInfoJob,String userToken){
		this.userRealName = username;
		this.userIdCardNum = UserIdCardNum;
		this.userInfoEmail = UserInfoEmail;
		this.userInfoSex = UserInfoSex;
		this.userInfoLabel = UserInfoLabel;
		this.userInfoSignature = UserInfoSignature;
		this.userInfoJob = userInfoJob;
		this.userToken = userToken;
	}

	
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", UserId=" + userId + ", UserRealName="
				+ userRealName + ", UserIdCardNum=" + userIdCardNum
				+ ", UserInfoEmail=" + userInfoEmail + ", UserInfoSex="
				+ userInfoSex + ", UserInfoPicture=" + userInfoPicture
				+ ", UserInfoLabel=" + userInfoLabel + ", UserInfoSignature="
				+ userInfoSignature + ", userInfoJob=" + userInfoJob
				+ ", userToken=" + userToken + "]";
	}
	
	
}
