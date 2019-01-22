package com.douzon.bookmall.test;

import java.sql.Connection;
import java.sql.SQLException;

import com.douzon.bookmall.dao.CategoryDao;
import com.douzon.bookmall.dao.MemberDao;
import com.douzon.bookmall.vo.MemberVo;

public class MemberDaoTest 
{
	public void insert(MemberVo vo)
	{
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		
		try 
		{
			conn = CategoryDao.getConnection();
						
			String sql = "insert into customer values (null, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPhoneNumber());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getPassword());
			pstmt.setString(5, vo.getAddress());
			
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
//		MemberDaoTest test = new MemberDaoTest();
//		test.insert(new MemberVo("김세준", "010-1234-5678", "sdds@gmail.com", "fdsfds", "부산"));
		MemberDao md = new MemberDao();
		md.printList(md.select());
	}

}
