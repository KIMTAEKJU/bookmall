package com.douzon.bookmall.vo;

public class OrderVo 
{
	private int no;
	private String name;
	private String email;
	private String sumPrice;
	private String address;
	
	public OrderVo(int no, String name, String email, String sumPrice, String address) {
		// TODO Auto-generated constructor stub
		this.no = no;
		this.name = name;
		this.email = email;
		this.sumPrice = sumPrice;
		this.address = address;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(String sumPrice) {
		this.sumPrice = sumPrice;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
