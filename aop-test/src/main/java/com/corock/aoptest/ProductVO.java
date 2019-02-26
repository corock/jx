package com.corock.aoptest;

public class ProductVO {

	private String name;

	public ProductVO(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ProductVO [name=" + name + "]";
	}

}
