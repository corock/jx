package com.corock.bookmall.vo;

public class CartVO {

	private long customerNo;
	private long bookNo;
	private int count;

	private String customerName;
	private String bookName;
	private int price;

	public CartVO() {
	}

	public CartVO(long customerNo, long bookNo, int count) {
		this.customerNo = customerNo;
		this.bookNo = bookNo;
		this.count = count;
	}

	public long getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(long customerNo) {
		this.customerNo = customerNo;
	}

	public long getBookNo() {
		return bookNo;
	}

	public void setBookNo(long bookNo) {
		this.bookNo = bookNo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return customerNo + ", " + bookName + ", " + count + ", " + price;
	}

}
