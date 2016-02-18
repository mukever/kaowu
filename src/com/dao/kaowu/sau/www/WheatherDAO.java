package com.dao.kaowu.sau.www;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.db.kaowu.sau.www.DBUnit;

public class WheatherDAO {
	
	//查找城市id
  	public static String getCityID(String province,String city,String district) {
  	    	
  	    	Connection connection = null;
  	    	String point = "";
  	    	connection = DBUnit.getConn();
  	    	String sql_province = "select * from db_weather  where  cityName='"+province+"'";
  	    	String sql_city = "select * from db_weather  where  cityName='"+city+"'";
  	    	String sql_district = "select * from db_weather  where  cityName='"+district+"'";
  	    	//三级搜索
  	    	try {
  	    		Statement statement = connection.createStatement();
  	    		ResultSet resultSet = statement.executeQuery(sql_district);
  	    		if(resultSet.next()){
  	    			point = district;
  	    		}else {
					resultSet = statement.executeQuery(sql_city);
					if(resultSet.next()){
						point = city;
					}else {
						resultSet = statement.executeQuery(sql_province);
						if(resultSet.next()){
							point = province;
						}
						
					}
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
  			return point ;
  	    }

  	
    // 关闭数据库连接
    public static void closeConnection(Connection connection) throws SQLException {
    	System.out.println("数据库连接关闭");
		connection.close();
	}
}
