package com.douzon.bookmall.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.douzon.bookmall.vo.BookVo;
import com.douzon.bookmall.vo.CartVo;

public class CartDao 
{
	public void insert(CartVo vo)
	{
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		
		try 
		{
			conn = CategoryDao.getConnection();
						
			String sql = "insert into cart values (null, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, vo.getBookNo());
			pstmt.setInt(2, vo.getCustomerNo());
			pstmt.setInt(3, vo.getCount());
			
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
	
	public ArrayList<CartVo> select()
	{
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CartVo> list = new ArrayList<>();
		
		try 
		{
			conn = CategoryDao.getConnection();
						
			String sql = "SELECT \r\n" + 
					"    c.title, a.합계, b.합계, d.name\r\n" + 
					"FROM\r\n" + 
					"    (SELECT \r\n" + 
					"        a.no, SUM(b.count) '합계'\r\n" + 
					"    FROM\r\n" + 
					"        book a, cart b, customer c\r\n" + 
					"    WHERE\r\n" + 
					"        a.no = b.book_no\r\n" + 
					"            AND b.customer_no = c.no\r\n" + 
					"    GROUP BY a.no) a,\r\n" + 
					"    (SELECT \r\n" + 
					"        a.no, SUM((a.price * b.count)) '합계'\r\n" + 
					"    FROM\r\n" + 
					"        book a, cart b, customer c\r\n" + 
					"    WHERE\r\n" + 
					"        a.no = b.book_no\r\n" + 
					"            AND b.customer_no = c.no\r\n" + 
					"    GROUP BY a.no) b,\r\n" + 
					"    book c,\r\n" + 
					"    customer d,\r\n" + 
					"    cart e\r\n" + 
					"WHERE\r\n" + 
					"    a.no = b.no AND b.no = c.no\r\n" + 
					"        AND c.no = e.book_no\r\n" + 
					"        AND d.no = e.customer_no\r\n" + 
					"GROUP BY a.no";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				String title = rs.getString(1);
				int count = rs.getInt(2);
				int price = rs.getInt(3);
				String name = rs.getString(4);
				
				list.add(new CartVo(title, count, price, name));
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
	
	public void printList(ArrayList<CartVo> list)
	{
		for (CartVo vo : list)
			System.out.println( 
							   vo.getTitle() + " " + 
							   vo.getCount() + " " +
							   vo.getPrice() + " " + 
							   vo.getName());
	}
}
