package com.dao.kaowu.sau.www;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.kaowu.sau.www.TeacherBean;
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
    public static String add(TeacherBean teacher) {
    		String type = "1";
            Connection connection = null;
        	
        	connection = DBUnit.getConn();
        	String query_therid_sql =  "select * from db_register  where  Therid='"+teacher.getTherid()+"'";
        	String sql = "insert into db_register(Username,Password,Age,Wechat,College,"
        				+ "Therid,Type,Position) values("
        				+ "'"+teacher.getUsername()+"', "
        				+ "'"+teacher.getPassword()+"', "
        				+ "'"+teacher.getAge()+"', "
        				+ "'"+teacher.getWechat()+"', "
        				+ "'"+teacher.getCollege()+"', "
        				+ "'"+teacher.getTherid()+"', "
        				+ "'"+teacher.getType()+"' ,"
        				+ "'"+teacher.getPosition()+"' "
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
    	boolean resultSet=false;
    	connection = DBUnit.getConn();
    	String change_sql =  "update db_register  set Type='"+Type+"' where  Therid='"+Therid+"'";
    	try {
    		
			Statement statement = connection.createStatement();
			resultSet = statement.execute(change_sql);	
			
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
    	
    	return resultSet;
	}
    
    
    //得到对应学院的用户列表
    public static List<TeacherBean> getUserList(String  College_) {
    	
    	List<TeacherBean>  list = new ArrayList<TeacherBean>();
    	Connection connection = null;
    	
    	connection = DBUnit.getConn();
    	
    	String sql = "select * from db_register where College='"+College_+"'";
    	System.out.println(sql);
    	try {
    		Statement statement = connection.createStatement();
    		ResultSet resultSet = statement.executeQuery(sql); 
    		while(resultSet.next()){
    			String Username = resultSet.getString("Username");
    		    String Therid = resultSet.getString("Therid");
    			String Age = resultSet.getString("Age");
    			String Wechat = resultSet.getString("Wechat");
    			String College = resultSet.getString("College");
    			String Type = resultSet.getString("Type");
    			list.add(new TeacherBean(Username,Therid,Age,Wechat,College,Type));
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

	//删除注册用户或审核未通过用户
	public static void delete(String Therid) {
	    	
	    	Connection connection = null;
	    	
	    	connection = DBUnit.getConn();
	    	
	    	String sql = "delete from db_register where Therid='"+Therid+"'";
	    	//System.out.println(sql);
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

	//得到对应Therid的教师信息
	public static TeacherBean getUserInfo(String Therid) {
		
		Connection connection = null;
    	TeacherBean teacher = null;
    	connection = DBUnit.getConn();
    	try {
			Statement statement = connection.createStatement();
			String sql = "select * from db_register  where Therid='"+Therid+"'";
			//从数据中得到相应的user的信息
			ResultSet resultSet = statement.executeQuery(sql);
			//调用相应的方法经行比较
			if(resultSet.next()){
				String Username = resultSet.getString("Username");
    		    //String Therid = resultSet.getString("Therid");
    			String Age = resultSet.getString("Age");
    			String Wechat = resultSet.getString("Wechat");
    			String College = resultSet.getString("College");
    			String Type = resultSet.getString("Type");
    			TeacherBean teacher_temp = new TeacherBean(Username, Therid, Age, Wechat, College, Type);
    			teacher = teacher_temp;
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
	
    public static void closeConnection(Connection connection) throws SQLException {
    	System.out.println("数据库连接关闭");
		connection.close();
	}
   
}
