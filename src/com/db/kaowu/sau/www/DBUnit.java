package com.db.kaowu.sau.www;

import java.sql.*;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
/*
 * 
 * 数据库连接对象
 */
public class DBUnit {	

	//返回数据库连接对象
	public static Connection getConn(){

		
		ServletContext context = ServletActionContext.getServletContext();
		//连接数据库
		String url = context.getInitParameter("url");
		String username  = context.getInitParameter("username");
		String psd = context.getInitParameter("password");
		
		String jdbcName= context.getInitParameter("driver");
		
		Connection conn = null;
		try{
			Class.forName(jdbcName);
		//	System.out.println("驱动加载成功！");
		}
		catch(Exception e){}
		try{
			//本地连接
//			conn=DriverManager.getConnection(url);
			
			//服务器连接
			conn=DriverManager.getConnection(url,username,psd);
			
			System.out.println("数据库连接成功!");
		}
		catch(SQLException ex){}
		return conn;		
	}
	
	

}
