package com.douzon.bookmall.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.douzon.bookmall.dao.CategoryDao;
import com.douzon.bookmall.vo.CategoryVo;
import com.mysql.jdbc.PreparedStatement;

public class CategoryDaoTest 
{

	public void testCategoryDaoInsert(CategoryVo vo)
	{
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		
		try 
		{
			conn = getConnection();
						
			String sql = "insert into category values (null, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			
			pstmt.executeUpdate();
			
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
	
	public ArrayList<CategoryVo> testCategoryDaoSelect()
	{
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CategoryVo> list = new ArrayList<>();
		
		try 
		{
			conn = getConnection();
						
			String sql = "select * from category";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				int no = rs.getInt(1);
				String name = rs.getString(2);
				
				CategoryVo caVo = new CategoryVo();
				caVo.setNo(no);
				caVo.setName(name);
				
				list.add(caVo);
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
	
	public void printList(ArrayList<CategoryVo> list)
	{
		for (CategoryVo vo : list)
			System.out.println(vo.getNo() + " " + vo.getName());
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		CategoryDaoTest test = new CategoryDaoTest();
		CategoryVo vo = new CategoryVo();
//		vo.setName("소설");
//		vo.setName("컴퓨터/IT");
//		test.testCategoryDaoInsert(vo);
		
		test.printList(test.testCategoryDaoSelect());
	}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		
		String url = "jdbc:mysql://localhost:3306/bookmall";
		return DriverManager.getConnection(url, "bookmall", "bookmall");
	}

}
