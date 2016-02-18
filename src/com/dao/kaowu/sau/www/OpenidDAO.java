package com.dao.kaowu.sau.www;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import com.CoreServer.wechat.kaowu.sau.www.UserInfo;
import com.db.kaowu.sau.www.DBUnit;

public class OpenidDAO {
	
	
	//向数据库中添加登录信息
    public static void add(UserInfo user) {
    	
            Connection connection = null;
        	
        	connection = DBUnit.getConn();
        	
        	String sql = "insert into db_openid(subscribe,openid,nickname,sex,city,"
        				+ "country,province,language,headimgurl,subscribe_time,"
        				+ "remark,groupid) values("
        				+ "'"+user.getSubscribe()+"', "
        				+ "'"+user.getOpenid()+"', "
        				+ "'"+user.getNickname()+"', "
        				+ "'"+user.getSex()+"', "
        				+ "'"+user.getCity()+"', "
        				+ "'"+user.getCountry()+"', "
        				+ "'"+user.getProvince()+"', "
        				+ "'"+user.getLanguage()+"', "
        				+ "'"+user.getHeadimgurl()+"', "
        				+ "'"+user.getSubscribe_time()+"', "
        				+ "'"+user.getRemark()+"', "
        				+ "'"+user.getGroupid()+"' "
        				+")";
    	try {
    		
			Statement statement = connection.createStatement();
			
			statement.executeUpdate(sql);
			
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
    	
	}
    
  //删除数据库中的用户信息
    public static void delete(UserInfo user) {
    	
            Connection connection = null;
        	
        	connection = DBUnit.getConn();
        	
        	String sql = "delete from db_openid where  openid='"+user.getOpenid()+"'";
    	try {
			Statement statement = connection.createStatement();
			
			statement.executeUpdate(sql);
			
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
    	
	}
    // 关闭数据库连接
    public static void closeConnection(Connection connection) throws SQLException {
    	System.out.println("数据库连接关闭");
		connection.close();
	}
}
