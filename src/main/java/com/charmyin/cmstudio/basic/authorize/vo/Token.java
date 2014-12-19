package com.charmyin.cmstudio.basic.authorize.vo;

import java.util.Date;

public class Token {
	
	public Token(String id, String userId, Date lastActiveTime){
		this.id = id;
		this.userId = userId;
		this.lastActiveTime = lastActiveTime;
	}
	private String id;
	
	private String userId;
	
	public String getUserId() {
		return userId;
	} 
	public void setUserId(String userId) {
		this.userId = userId;
	}
	private Date lastActiveTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getLastActiveTime() {
		return lastActiveTime;
	}
	public void setLastActiveTime(Date lastActiveTime) {
		this.lastActiveTime = lastActiveTime;
	}
	
}
