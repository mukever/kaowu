package com.dao.kaowu.sau.www;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.bean.kaowu.sau.www.AdminBean;
import com.db.kaowu.sau.www.DBUnit;

public class LoginDAO {
	
    /*
     * 判断用户是否合法
     */
    public static String userLegitimate(AdminBean user) {
    	
        Connection connection = null;
    	
    	connection = DBUnit.getConn();
    	
    	try {
			Statement statement = connection.createStatement();
			//从数据中得到相应的user和pwd  
			String name = user.getUsername();
			String pwd = user.getPassword();
			ResultSet res = statement.executeQuery("select * from db_admin  where username='"+name+"' and password='"+pwd+"'");
			//调用相应的方法经行比较
			if(isok(res)){
				return "1";
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
    	
		return "0";
	}
    /*
     * 判断用户名和密码是否合法
     */
    private  static boolean isok(ResultSet resultSet) throws SQLException {
    
    	if(resultSet.next() ){
//    		String username = resultSet.getString("username");
//        	String pwd = resultSet.getString("password");
//        	System.out.println(username);
//        	System.out.println(pwd);
        	return true;
    	}else
    		return false;
	}
    public static void closeConnection(Connection connection) throws SQLException {
    	System.out.println("数据库连接关闭");
		connection.close();
	}
	public static String getCollege(AdminBean user) {
Connection connection = null;
    	
    	connection = DBUnit.getConn();
    	String College = "";
    	try {
			Statement statement = connection.createStatement();
			//从数据中得到相应的user的College
			String name = user.getUsername();
			ResultSet res = statement.executeQuery("select * from db_admin  where username='"+name+"'");
			//调用相应的方法经行比较
			if(res.next()){
				College = res.getString("College");	
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
    	
		return College;
	}

}
