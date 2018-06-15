package com.leo.domain;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class UserInfo extends BaseEntity<String> implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;
	
	private String info;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
}
