package com.douzon.bookmall.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.douzon.bookmall.vo.OrderVo;

public class OrderDao 
{
	public void insert(int customerNo)
	{
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		
		try 
		{
			conn = CategoryDao.getConnection();
						
			String sql = "insert into request values (null, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, customerNo);
			
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
	
	public ArrayList<OrderVo> select()
	{
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OrderVo> list = new ArrayList<>();
		
		try 
		{
			conn = CategoryDao.getConnection();
						
			String sql = "SELECT \r\n" + 
					"    a.no, b.name, b.email, a.합계, b.address\r\n" + 
					"FROM\r\n" + 
					"    (SELECT \r\n" + 
					"        b.no, SUM((c.price * d.count)) '합계'\r\n" + 
					"    FROM\r\n" + 
					"        request a, customer b, book c, cart d\r\n" + 
					"    WHERE\r\n" + 
					"        a.customer_no = b.no\r\n" + 
					"            AND b.no = d.customer_no\r\n" + 
					"            AND c.no = d.book_no\r\n" + 
					"    GROUP BY b.no) a,\r\n" + 
					"    customer b\r\n" + 
					"WHERE\r\n" + 
					"    a.no = b.no";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				int no = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String sumPrice = rs.getString(4);
				String address = rs.getString(5);
				
				list.add(new OrderVo(no, name, email, sumPrice, address));
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
	
	public void printList(ArrayList<OrderVo> list)
	{
		for (OrderVo vo : list)
			System.out.println(vo.getNo() + " " + 
							   vo.getName() + " " + 
							   vo.getEmail() + " " +
							   vo.getSumPrice() + " " +
							   vo.getAddress());
	}
}
