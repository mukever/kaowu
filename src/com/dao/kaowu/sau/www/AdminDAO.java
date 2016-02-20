package com.dao.kaowu.sau.www;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.bean.kaowu.sau.www.AdminBean;
import com.bean.kaowu.sau.www.TeacherBean;
import com.db.kaowu.sau.www.DBUnit;

public class AdminDAO {
	
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
	
	public static TeacherBean getUserInfo(String username) {
			
			Connection connection = null;
	    	TeacherBean teacher = new TeacherBean();
	    	connection = DBUnit.getConn();
	    	try {
				Statement statement = connection.createStatement();
				String sql = "select * from db_admin  where username='"+username+"'";
				//从数据中得到相应的user的信息
				ResultSet resultSet = statement.executeQuery(sql);
				//调用相应的方法经行比较
				if(resultSet.next()){
					String College = resultSet.getString("College");
					teacher.setCollege(College);
					teacher.setUsername(username);
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
	    	
			return teacher;
		}
	//获取普通用户注册后没有审核处理的数量
	public static int getCount(String iD) {
		TeacherBean teacher = getUserInfo(iD);
		String College = teacher.getCollege();
		int count = 0;
		Connection connection = null;
    	connection = DBUnit.getConn();
    	try {
			Statement statement = connection.createStatement();
			//String sql = "select * from db_register  where College='"+College+"'";
			String sql = "select * from db_register where College = '"+College+"' and Type='-1'";
			//从数据中得到相应的user的信息
			System.out.println(sql);
			ResultSet resultSet = statement.executeQuery(sql);
			//调用相应的方法经行比较
			if(resultSet.next()){
			 resultSet.last(); //移到最后一行  
			 count = resultSet.getRow(); //得到当前行号，也就是记录数  
			 resultSet.beforeFirst(); //如果还要用结果集，就把指针再移到初始化的位置     
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
    	
		return count;
	}
	
	public static void closeConnection(Connection connection) throws SQLException {
    	System.out.println("数据库连接关闭");
		connection.close();
	}

}
