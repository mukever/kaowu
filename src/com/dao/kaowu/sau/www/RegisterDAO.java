package com.dao.kaowu.sau.www;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.db.kaowu.sau.www.DBUnit;

public class RegisterDAO {
	
	 /*
     * 判断用户是否合法
     */
    public static String  Legitimate(String Therid,String Password) {
    	
        Connection connection = null;
        String status = "0";
    	connection = DBUnit.getConn();
    	
    	try {
			Statement statement = connection.createStatement();
			String sql = "select * from  db_register  where Therid='"+Therid+"' and Password='"+Password+"'";
			System.out.println(sql);
			ResultSet res = statement.executeQuery(sql);
			//调用相应的方法经行比较
			status =  isok(res);
			closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
			if(connection!=null){
				try {
					connection.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
    	return status;
	}

    
    /*
     * 判断用户名和密码是否合法
     */
    private  static String  isok(ResultSet resultSet) throws SQLException {
    
    	if(resultSet.next()){
        	if("-1".equals(resultSet.getString("Type"))){
        		//没有通过审核
        		return "-1";
        	}else {
        		//通过审核的普通用户
				return "2";
			}
    	}else{
    		//用户名  密码错误
    		return "0";
    	}
	}
    
  //向数据库中添加注册信息
    public static String add(String Username, String Password, String Therid,String Age,String Wechat, String College) {
    		String type = "1";
            Connection connection = null;
        	
        	connection = DBUnit.getConn();
        	String query_therid_sql =  "select * from db_register  where  Therid='"+Therid+"'";
        	String sql = "insert into db_register(Username,Password,Age,Wechat,College,"
        				+ "Therid,Type) values("
        				+ "'"+Username+"', "
        				+ "'"+Password+"', "
        				+ "'"+Age+"', "
        				+ "'"+Wechat+"', "
        				+ "'"+College+"', "
        				+ "'"+Therid+"',"
        				+ "'-1'"        				
        				+")";
    	try {
    		
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query_therid_sql);
			if(!resultSet.next()){
				System.out.println(sql);
				statement.executeUpdate(sql);
			}else {
				//用户存在
				type = "0";
			}
			
			closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
			if(connection!=null){
				try {
					connection.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return type;
    	
	}
    
    
  //审核通过信息处理
    public static boolean chanegType(String Type,String Therid) {

    	Connection connection = null;
        	
    	connection = DBUnit.getConn();
    	String change_sql =  "update db_register  set Type='"+Type+"' where  Therid='"+Therid+"'";
    	try {
    		
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(change_sql);		
			closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
			if(connection!=null){
				try {
					connection.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
					return false;
				}
			}
		}
    	
    	return true;
	}
    
    
    public static void closeConnection(Connection connection) throws SQLException {
    	System.out.println("数据库连接关闭");
		connection.close();
	}
}
