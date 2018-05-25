package com.leo.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Address extends BaseEntity<String> implements Serializable {

	private static final long serialVersionUID = 1L;
	@OneToOne
	private User user;
	private String code;
	private String msg;
	
	public Address(){
		super();
	}
	
	public Address(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
