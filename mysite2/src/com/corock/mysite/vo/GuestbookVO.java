package com.corock.mysite.vo;

import java.util.Date;

public class GuestbookVO {

	private long no;
	private String name;
	private String password;
	private String message;
	private String regDate;

	public GuestbookVO() {
	}

	public GuestbookVO(long no, String password) {
		this.no = no;
		this.password = password;
	}

	public GuestbookVO(String name, String password, String message) {
		this.name = name;
		this.password = password;
		this.message = message;
	}

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "GuestbookVO [no=" + no + ", name=" + name + ", password=" + password + ", message=" + message
				+ ", regDate=" + regDate + "]";
	}

}
