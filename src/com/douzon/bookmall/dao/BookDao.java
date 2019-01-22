package com.douzon.bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.douzon.bookmall.vo.BookVo;
import com.douzon.bookmall.vo.CategoryVo;
import com.douzon.bookmall.vo.MemberVo;

public class BookDao 
{
	public void insert(BookVo vo)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try 
		{
			conn = CategoryDao.getConnection();
						
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
			System.out.println("연결 에러 fdsfds : " + e);
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
	
	public ArrayList<BookVo> select()
	{
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BookVo> list = new ArrayList<>();
		
		try 
		{
			conn = CategoryDao.getConnection();
						
			String sql = "select a.no, a.title, b.name, a.price" +
						 "	from book a, category b" + 
						 "		where a.category_no = b.no";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				int no = rs.getInt(1);
				String title = rs.getString(2);
				String categoryName = rs.getString(3);
				int bookPrice = rs.getInt(4);
				
				list.add(new BookVo(no, title, categoryName, bookPrice));
			}
			
		} 
		catch (ClassNotFoundException e) 
		{
			System.out.println("드라이버 에러 : " + e);
		} 
		catch (SQLException e) 
		{
			System.out.println("연결, sql 에러 : " + e);
		}
		finally 
		{
			try 
			{
				if (rs != null)
					rs.close();
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
		return list;
	}
	
	public void printList(ArrayList<BookVo> list)
	{
		for (BookVo vo : list)
			System.out.println(vo.getNo() + " " + 
							   vo.getTitle() + " " + 
							   vo.getCategoryName() + " " +
							   vo.getBookPrice());
	}
}
