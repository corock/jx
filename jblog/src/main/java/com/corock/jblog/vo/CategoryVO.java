package com.corock.jblog.vo;

public class CategoryVO {

	private Long no;
	private String name;
	private String description;

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

	public Long getUserNo() {
		return userNo;
	}

	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}

	@Override
	public String toString() {
		return "CategoryVO [no=" + no + ", name=" + name + ", description=" + description + ", userNo=" + userNo + "]";
	}

}
