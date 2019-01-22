package com.douzon.bookmall.main;

import java.util.ArrayList;

import com.douzon.bookmall.dao.BookDao;
import com.douzon.bookmall.dao.CartDao;
import com.douzon.bookmall.dao.CategoryDao;
import com.douzon.bookmall.dao.MemberDao;
import com.douzon.bookmall.dao.OrderBookDao;
import com.douzon.bookmall.dao.OrderDao;
import com.douzon.bookmall.vo.BookVo;
import com.douzon.bookmall.vo.CartVo;
import com.douzon.bookmall.vo.CategoryVo;
import com.douzon.bookmall.vo.MemberVo;

public class BookMall {

	public static void main(String[] args) {
		
		// insert하는 코드들  이 main을 실행시켰을때 결과가 다나와야함
		// 카테고리 : 번호 이름
		// 상품리스트 : 제목, 가격, 카테고리 이름
		// 카트리스트 : 회원 2명중 1명의 카트에 상품 2개를 담고 보여주면됨	도서제목, 수량, 가격
		// 주문리스트 : 카트에있는애를 주문  주문번호, 주문자, 결제금액, 배송지
		// 주문 도서리스트 : 카트에 2개담고 카트리스트 
		
		BookMall bookMall = new BookMall();
		
		// ========== 사용자 =============
		
		bookMall.callMemberDao();
		
		// ========= 카테고리 ============
		
		bookMall.callCategoryDao();
		
		// ========== 책 ==============
		
		bookMall.callBookDao();
		
		// ========= 카트 ==============
		
		bookMall.callCartDao();
		
		// ========= 주문 =============
		
		bookMall.callOrderDao();
		
		// ======== 주문도서 ===========
		
		bookMall.callOrderBookDao();
	}
	
	public void callCategoryDao()
	{
		System.out.println("카테고리 리스트");
		System.out.println();
		
		CategoryDao categoryDao = new CategoryDao();
		
		categoryDao.insert("소설");
		categoryDao.insert("수필");
		categoryDao.insert("컴퓨터/IT");
		categoryDao.insert("인문");
		categoryDao.insert("경제");
		categoryDao.insert("예술");
		
		categoryDao.printList(categoryDao.select());
		System.out.println("============================================");
	}
	
	public void callMemberDao()
	{
		System.out.println("회원정보");
		System.out.println();
		
		MemberDao memberDao = new MemberDao();
		
		memberDao.insert(new MemberVo(0, "김세준", "010-1234-5678", "sdds@gmail.com", "fdsfds", "부산"));
		memberDao.insert(new MemberVo(0, "최기석", "010-0000-1111", "fdgfdrw@gmail.com", "fet456", "부산"));
		
		memberDao.printList(memberDao.select());
		System.out.println("=============================================");
	}
	
	public void callBookDao()
	{
		System.out.println("상품 리스트");
		System.out.println();
		
		BookDao bookDao = new BookDao();
		
		bookDao.insert(new BookVo(0, "이것이 자바다", 27000, 3));
		bookDao.insert(new BookVo(0, "디디의 우산", 12600, 1));
		bookDao.insert(new BookVo(0, "시처럼 아름다운 수필", 10800, 2));
		bookDao.insert(new BookVo(0, "젊은 예술가의 초상", 8550, 6));
		bookDao.insert(new BookVo(0, "놀이와 예술 그리고 상상력", 13500, 4));
		bookDao.insert(new BookVo(0, "경제 트렌드 2019", 14400, 5));

		bookDao.printList(bookDao.select());
		System.out.println("===============================================");
	}
	
	public void callCartDao()
	{
		System.out.println("카트 리스트");
		System.out.println();
		
		CartDao cartDao = new CartDao();
		
		cartDao.insert(new CartVo(0, 1, 2, 1));
		cartDao.insert(new CartVo(0, 4, 1, 5));
		
		cartDao.printList(cartDao.select());
		System.out.println("================================================");
	}
	
	public void callOrderDao()
	{
		System.out.println("주문 리스트");
		System.out.println();
		
		OrderDao orderDao = new OrderDao();
		
		orderDao.insert(1);
		orderDao.insert(2);
		
		orderDao.printList(orderDao.select());
		System.out.println("==================================================");
	}
	
	public void callOrderBookDao()
	{
		System.out.println("주문 도서 리스트");
		System.out.println();
		
		OrderBookDao orderBookDao = new OrderBookDao();
		
		orderBookDao.insert(1);
		orderBookDao.insert(2);
		orderBookDao.insert(3);
		orderBookDao.insert(4);
		orderBookDao.insert(5);
		orderBookDao.insert(6);
		
		orderBookDao.printList(orderBookDao.select());
	}
}
