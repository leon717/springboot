package com.leo.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "t_user")
public class User extends BaseEntity<String> implements Serializable {

	private static final long serialVersionUID = 1L;
	private String userName;
	private String passWord;
	private String email;
	private String nickName;
	private Date regTime;
	@OneToOne(mappedBy="user")
	private Address address;
	
	@Enumerated(EnumType.STRING)	//string而不是索引
	private Gender gender;
	@Transient	//不映射
	private String str;
	
    public enum Gender {
    	MALE("01", "MAN"),  //男
        FEMALE("02", "FEMALE");  //女
    	
    	private String value;

	    private String code;

	    Gender(String code, String value) {
	        this.code = code;
	        this.value = value;
	    }

	    public String getValue() {
	        return value;
	    }

	    public void setValue(String value) {
	        this.value = value;
	    }

	    public String getCode() {
	        return code;
	    }

	    public void setCode(String code) {
	        this.code = code;
	    }
    }

	public User() {
		super();
	}

	public User(String userName, String passWord, String email, String nickName, Date regTime) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.email = email;
		this.nickName = nickName;
		this.regTime = regTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
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

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", gender=" + gender + "]";
	}

}