package com.douzon.bookmall.test;

import java.sql.Connection;
import java.sql.SQLException;

import com.douzon.bookmall.dao.CartDao;
import com.douzon.bookmall.dao.CategoryDao;
import com.douzon.bookmall.vo.CartVo;

public class CartDaoTest 
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
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		CartDaoTest test = new CartDaoTest();
		//CartVo vo = new CartVo(0, 1, 1, 4);
		//test.insert(new CartVo(0, 4, 2, 5));
		CartDao dao = new CartDao();
		dao.printList(dao.select());
	}

}
