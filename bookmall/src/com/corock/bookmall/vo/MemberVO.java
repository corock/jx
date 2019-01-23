package com.corock.bookmall.vo;

public class MemberVO {

	private long no;
	private String name;
	private String tel;
	private String email;
	private String passwd;
	private long orderNo;

	public MemberVO() {
	}

	public MemberVO(String name, String tel, String email, String passwd) {
		this.name = name;
		this.tel = tel;
		this.email = email;
		this.passwd = passwd;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public String toString() {
		return no + ", " + name + ", " + tel + ", " + email + ", " + passwd;
	}

}
