package com.corock.jblog.vo;

public class BlogVO {

	private Long userNo;
	private String title;
	private String logo;

	private String id;

	public Long getUserNo() {
		return userNo;
	}

	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "BlogVO [userNo=" + userNo + ", title=" + title + ", logo=" + logo + ", id=" + id + "]";
	}

}
