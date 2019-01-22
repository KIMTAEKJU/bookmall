package com.douzon.bookmall.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.douzon.bookmall.vo.BookVo;
import com.douzon.bookmall.vo.OrderBookVo;
import com.douzon.bookmall.vo.OrderVo;

public class OrderBookDao 
{
	public void insert(int bookNo)
	{
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		
		try 
		{
			conn = CategoryDao.getConnection();
						
			String sql = "insert into order_book values (null, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bookNo);
			
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
				if (conn != null)
					conn.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<OrderBookVo> select()
	{
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OrderBookVo> list = new ArrayList<>();
		
		try 
		{
			conn = CategoryDao.getConnection();
						
			String sql = "SELECT \r\n" + 
					"    b.no, b.title, a.합계\r\n" + 
					"FROM\r\n" + 
					"    (SELECT \r\n" + 
					"        d.no, SUM(c.count) '합계'\r\n" + 
					"    FROM\r\n" + 
					"        request a, customer b, cart c, book d\r\n" + 
					"    WHERE\r\n" + 
					"        a.customer_no = b.no\r\n" + 
					"            AND b.no = c.customer_no\r\n" + 
					"            AND c.book_no = d.no\r\n" + 
					"    GROUP BY d.no) a, book b where a.no = b.no";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				int no = rs.getInt(1);
				String title = rs.getString(2);
				int count = rs.getInt(3);
				
				list.add(new OrderBookVo(no, title, count));
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
	
	public void printList(ArrayList<OrderBookVo> list)
	{
		for (OrderBookVo vo : list)
			System.out.println(vo.getNo() + " " + 
							   vo.getTitle() + " " + 
							   vo.getCount());
	}
}
