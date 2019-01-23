package com.corock.bookmall.vo;

public class CategoryVO {

	private long no;
	private String subject;

	public CategoryVO() {
	}

	public CategoryVO(String subject) {
		this.subject = subject;
	}

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return no + " " + subject;
	}

}
