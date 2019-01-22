package com.douzon.bookmall.vo;

public class OrderBookVo 
{
	private int no;
	private String title;
	private int count;
	
	public OrderBookVo(int no, String title, int count) {
		// TODO Auto-generated constructor stub
		this.no = no;
		this.title = title;
		this.count = count;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
