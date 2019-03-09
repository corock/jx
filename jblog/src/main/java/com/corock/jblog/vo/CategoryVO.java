package com.corock.jblog.vo;

public class CategoryVO {

	private Long no;
	private String name;
	private String description;
	private String regDate;
	private Integer postCount;

	private Long userNo;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public Integer getPostCount() {
		return postCount;
	}

	public void setPostCount(Integer postCount) {
		this.postCount = postCount;
	}

	public Long getUserNo() {
		return userNo;
	}

	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}

	@Override
	public String toString() {
		return "CategoryVO [no=" + no + ", name=" + name + ", description=" + description + ", regDate=" + regDate
				+ ", postCount=" + postCount + ", userNo=" + userNo + ", getNo()=" + getNo() + ", getName()="
				+ getName() + ", getDescription()=" + getDescription() + ", getRegDate()=" + getRegDate()
				+ ", getPostCount()=" + getPostCount() + ", getUserNo()=" + getUserNo() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
