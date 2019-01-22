package com.douzon.bookmall.vo;

public class CartVo {

	private int no;
	private int bookNo;
	private int customerNo;
	private int count;
	private String name;
	private String title;
	private int price;
	
	public CartVo(int no, int bookNo, int customerNo, int count) {
		// TODO Auto-generated constructor stub
		this.no = no;
		this.bookNo = bookNo;
		this.customerNo = customerNo;
		this.count = count;
		
	}
	public CartVo(String title, int count, int price, String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.title = title;
		this.count = count;
		this.price = price;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getNo() {
		return no;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public void setCustomerNo(int customerNo) {
		this.customerNo = customerNo;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public int getBookNo() {
		// TODO Auto-generated method stub
		return bookNo;
	}

	public int getCustomerNo() {
		// TODO Auto-generated method stub
		return customerNo;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}

}
