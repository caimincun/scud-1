package com.team.dream.runlegwork_data.entity;

import org.litepal.crud.DataSupport;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//继承DataSupport实现Litepal数据库
public class UserEntity extends DataSupport {

	@SerializedName("id")
	private int userId;

	@SerializedName("cover_url")
	private String coverUrl;

	@SerializedName("full_name")
	private String fullname;

	@SerializedName("email")
	private String email;

	@Expose(serialize = false)
	private String nickName;

	public UserEntity() {
		// empty
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	

	public UserEntity(int userId, String coverUrl, String fullname, String email, String nickName) {
		super();
		this.userId = userId;
		this.coverUrl = coverUrl;
		this.fullname = fullname;
		this.email = email;
		this.nickName = nickName;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("***** User Entity Details *****\n");
		stringBuilder.append("id=" + this.getUserId() + "\n");
		stringBuilder.append("cover url=" + this.getCoverUrl() + "\n");
		stringBuilder.append("fullname=" + this.getFullname() + "\n");
		stringBuilder.append("email=" + this.getEmail() + "\n");
		stringBuilder.append("*******************************");

		
		return stringBuilder.toString();
	}


}
