package com.douzon.bookmall.vo;

public class BookVo 
{
	private int no;
	private String title;
	private int bookPrice;
	private String categoryName;
	private long price;
	private long categoryNo;
	
	public BookVo(int no, String title, String categoryName, int bookPrice) {
		// TODO Auto-generated constructor stub
		
		this.no = no;
		this.title = title;
		this.categoryName = categoryName;
		this.bookPrice = bookPrice;
		
	}
	
	public BookVo(int no, String title, int price, int categoryNo) {
		// TODO Auto-generated constructor stub
		
		this.no = no;
		this.title = title;
		this.price = price;
		this.categoryNo = categoryNo;
		
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public long getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(long categoryNo) {
		this.categoryNo = categoryNo;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public int getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


}
