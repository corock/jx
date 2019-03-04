package com.corock.springcontainer;

public class User {

	private Long no;
	private String name = "CoRock";
	private Friend friend;

	public User() {
	}

	public User(String name) {
		this.name = name;
	}

	public User(Long no, String name) {
		this.no = no;
		this.name = name;
	}

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

	public Friend getFriend() {
		return friend;
	}

	public void setFriend(Friend friend) {
		this.friend = friend;
	}

	@Override
	public String toString() {
		return "User [no=" + no + ", name=" + name + ", friend=" + friend + "]";
	}

}
