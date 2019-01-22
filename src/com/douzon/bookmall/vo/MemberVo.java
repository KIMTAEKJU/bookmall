package com.douzon.bookmall.vo;

public class MemberVo 
{
	private int no;
	private String name;
	private String phoneNumber;
	private String email;
	private String password;
	private String address;
	
	public MemberVo(int no, String name, String phoneNumber, String email, String password, String address) {
		// TODO Auto-generated constructor stub
		this.no = no;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
		this.address = address;
	}
	
	public int getNo() {
		return no;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public String getPhoneNumber() {
		// TODO Auto-generated method stub
		return phoneNumber;
	}

	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	public String getAddress() {
		// TODO Auto-generated method stub
		return address;
	}

}
