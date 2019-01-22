package com.douzon.bookmall.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.douzon.bookmall.dao.CategoryDao;
import com.douzon.bookmall.vo.CartVo;
import com.douzon.bookmall.vo.MemberVo;
import com.douzon.bookmall.vo.OrderVo;

public class OrderDaoTest 
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
						
			String sql = "select a.no, b.name, b.email, sum(c.price), b.address" +
						 "	from request a, customer b, book c, cart d" + 
						 "		where a.customer_no = b.no and b.no = d.customer_no and c.no = d.book_no" +
						 "			group by b.no";
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
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		OrderDaoTest test = new OrderDaoTest();
		//test.printList(test.select());
		
	}

}
