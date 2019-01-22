package com.douzon.bookmall.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.douzon.bookmall.dao.BookDao;
import com.douzon.bookmall.dao.CategoryDao;
import com.douzon.bookmall.vo.BookVo;

public class BookDaoTest {
	
	public void testBookDaoInsert(BookVo vo)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try 
		{
			conn = CategoryDao.getConnection();
			
			System.out.println("연결 성공");
			
			String sql = "insert into book values (null, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setLong(2, vo.getPrice());
			pstmt.setLong(3, vo.getCategoryNo());
			
			pstmt.executeUpdate();
		} 
		catch (ClassNotFoundException e) 
		{
			System.out.println("드라이버 에러 : " + e);
		} 
		catch (SQLException e) 
		{
			System.out.println("연결 에러 : " + e);
		}
		finally 
		{
			try 
			{
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BookDaoTest test = new BookDaoTest();
		
		//test.testBookDaoInsert();
		BookDao bd = new BookDao();
		bd.printList(bd.select());
	}

}
