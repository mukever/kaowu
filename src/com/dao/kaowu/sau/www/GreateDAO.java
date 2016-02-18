package com.dao.kaowu.sau.www;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.kaowu.sau.www.ClassRoomBean;
import com.bean.kaowu.sau.www.GrateBean;
import com.db.kaowu.sau.www.DBUnit;

public class GreateDAO {
	

   //向数据库中添加班级信息
	
    public static void addGreate( GrateBean grate) {
    	
        Connection connection = null;
    	
    	connection = DBUnit.getConn();
    	
    	String sql = "insert into db_greate(greatename,greatenum) values('"+grate.getGreatename()+"','"+grate.getGreatenum()+"')";
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
    
  //查找单个年级信息
  	public static GrateBean getGrateinfo(String id) {
  	    	
  	    	Connection connection = null;
  	    	GrateBean g = null;
  	    	connection = DBUnit.getConn();
  	    	
  	    	String sql = "select * from db_greate  where  greatename='"+id+"'";
  	    	
  	    	try {
  	    		Statement statement = connection.createStatement();
  	    		ResultSet resultSet = statement.executeQuery(sql);
  	    		while(resultSet.next()){
  	    			String greatename = resultSet.getString(2);
  	    			String greatenum = resultSet.getString(3);
  	    		    g = new GrateBean(greatename,greatenum);
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
  			return g ;
  	    }
	public static List<GrateBean> getList() {
    	
    	List<GrateBean>  list = new ArrayList<GrateBean>();
    	Connection connection = null;
    	
    	connection = DBUnit.getConn();
    	
    	String sql = "select * from db_greate";
    	
    	try {
    		Statement statement = connection.createStatement();
    		ResultSet resultSet = statement.executeQuery(sql);
    		while(resultSet.next()){
    			String greatename = resultSet.getString(2);
    			String greatenum = resultSet.getString(3);
    			list.add(new GrateBean(greatename, greatenum));
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
		return list;
    }
	
	public static void delete(String id) {
	    	
	    	Connection connection = null;
	    	
	    	connection = DBUnit.getConn();
	    	
	    	String sql = "delete from db_greate where  greatename='"+id+"'";
	    	
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
	//更新信息
	public static boolean query(String greatename,String greatenum) {
	    	
	    	Connection connection = null;
	    	connection = DBUnit.getConn();
	    	
	    	String sql = "update db_greate  set greatenum='"+greatenum+"' where  greatename='"+greatename+"'";
	    	System.out.println(sql);
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
			return true ;
	    }

    // 关闭数据库连接
    public static void closeConnection(Connection connection) throws SQLException {
    	System.out.println("数据库连接关闭");
		connection.close();
	}

}
