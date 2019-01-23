package com.corock.bookmall.vo;

public class OrderVO {

	private long no;
	private int totalPrice;
	private String address;
	private long customerNo;
	private String customerName;

	private long orderNo;
	private long bookNo;
	private int count;
	private String bookName;

	public OrderVO() {
	}

	public OrderVO(long orderNo, long bookNo, int count) {
		this.orderNo = orderNo;
		this.bookNo = bookNo;
		this.count = count;
	}

	public OrderVO(long no, long customerNo, int totalPrice, String address) {
		this.no = no;
		this.customerNo = customerNo;
		this.totalPrice = totalPrice;
		this.address = address;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(long customerNo) {
		this.customerNo = customerNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
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

	@Override
	public String toString() {
		return no + ", " + customerName + ", " + totalPrice + ", " + address;
	}

}
