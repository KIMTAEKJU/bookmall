package com.douzon.bookmall.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.douzon.bookmall.vo.CategoryVo;
import com.douzon.bookmall.vo.MemberVo;

public class MemberDao 
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
	
	public ArrayList<MemberVo> select()
	{
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberVo> list = new ArrayList<>();
		
		try 
		{
			conn = CategoryDao.getConnection();
						
			String sql = "select * from customer";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				int no = rs.getInt(1);
				String name = rs.getString(2);
				String phoneNumber = rs.getString(3);
				String email = rs.getString(4);
				String password = rs.getString(5);
				String address = rs.getString(6);
				
				list.add(new MemberVo(no, name, phoneNumber, email, password, address));
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
	
	public void printList(ArrayList<MemberVo> list)
	{
		for (MemberVo vo : list)
			System.out.println(vo.getNo() + " " + 
							   vo.getName() + " " + 
							   vo.getPhoneNumber() + " " + 
							   vo.getEmail() + " " +
							   vo.getPassword() + " " + 
							   vo.getAddress());
	}
}
