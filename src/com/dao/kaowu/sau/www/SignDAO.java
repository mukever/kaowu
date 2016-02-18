package com.dao.kaowu.sau.www;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.bean.kaowu.sau.www.SignBean;
import com.db.kaowu.sau.www.DBUnit;


public class SignDAO {
	
	//向数据库中添加登录信息
    public static void add(SignBean sBean) {
    	
        Connection connection = null;
    	int time;
    	connection = DBUnit.getConn();
    	String ck = "select * from db_sign where username='"+sBean.getUsername()+"'";
    	String sql_first = "insert into db_sign(username,date,frequency) values('"+sBean.getUsername()+"','"+sBean.getDate()+"','1')";
    	String sql_second = "update db_sign set date='"+sBean.getDate()+"' where username='"+sBean.getUsername()+"'";
    	try {
			Statement statement = connection.createStatement();
			ResultSet resultSet= statement.executeQuery(ck);
			if(!resultSet.next()){
				//System.out.println(sql_first);
				statement.executeUpdate(sql_first);
			}else{
				//System.out.println(sql_second);
				time = resultSet.getInt(4);
				time++;
				statement.execute(sql_second);
				statement.executeUpdate("update db_sign set frequency='"+time+"' where username='"+sBean.getUsername()+"'");
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
	}
    
    //获取全部登录信息
	public static List<SignBean> getList() {
    	
    	List<SignBean>  list = new ArrayList<SignBean>();
    	Connection connection = null;
    	
    	connection = DBUnit.getConn();
    	
    	String sql = "select * from db_sign";
    	
    	try {
    		Statement statement = connection.createStatement();
    		ResultSet resultSet = statement.executeQuery(sql);
    		while(resultSet.next()){
    			String username = resultSet.getString(2);
    			String date = resultSet.getString(3);
    			int frequency = resultSet.getInt(4);
    			list.add(new SignBean(username,date,frequency));
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
	//返回知道用户名的最后访问时间
	public static String query(String username) {
		  String da = null;
		 Connection connection = null;
	    	connection = DBUnit.getConn();
	    	String ck = "select * from db_sign where username='"+username+"'";
	    	try {
				Statement statement = connection.createStatement();
				ResultSet resultSet= statement.executeQuery(ck);
				//System.out.println(sql_first);
				if(resultSet.next()){
					da = resultSet.getString(3);
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
		return da;
	}
    // 关闭数据库连接
    public static void closeConnection(Connection connection) throws SQLException {
    	System.out.println();
    	System.out.println("数据库连接关闭");
		connection.close();
	}

}
