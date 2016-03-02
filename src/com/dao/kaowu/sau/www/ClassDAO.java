package com.dao.kaowu.sau.www;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.bean.kaowu.sau.www.ClassRoomBean;
import com.db.kaowu.sau.www.DBUnit;

public class ClassDAO {
	
    //向数据库中添加班级信息
	
    public static boolean add(ClassRoomBean cRoomBean) {
    	
        Connection connection = null;
    	
    	connection = DBUnit.getConn();
    	String ck = "select * from db_classroom where classname='"+cRoomBean.getClassRoomname()+"'";
    	String sql = "insert into db_classroom(classname,classnum,classwhere) values('"+cRoomBean.getClassRoomname()+"','"+cRoomBean.getClassRoomNum()+"','"+cRoomBean.getClassRoomwhere()+"')";
    	try {
    		
			Statement statement = connection.createStatement();
			ResultSet resultSet= statement.executeQuery(ck);
			if(!resultSet.next()){
			
				statement.executeUpdate(sql);
				return false;
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
    	return true;
	}
    //查找单个教室信息
	public static ClassRoomBean getClassinfo(String id) {
	    	
	    	Connection connection = null;
	    	ClassRoomBean c = null;
	    	connection = DBUnit.getConn();
	    	
	    	String sql = "select * from db_classroom  where  classname='"+id+"'";
	    	
	    	try {
	    		Statement statement = connection.createStatement();
	    		ResultSet resultSet = statement.executeQuery(sql);
	    		while(resultSet.next()){
	    			String classname = resultSet.getString("classname");
	    			String classnum = resultSet.getString("classnum");
	    			String classwhere = resultSet.getString("classwhere");
	    		    c = new ClassRoomBean(classname,classnum, classwhere);
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
			return c ;
	    }
	//查看数据库所有的信息
	public static List<ClassRoomBean> getList() {
    	
    	List<ClassRoomBean>  list = new ArrayList<ClassRoomBean>();
    	Connection connection = null;
    	
    	connection = DBUnit.getConn();
    	
    	String sql = "select * from db_classroom";
    	
    	try {
    		Statement statement = connection.createStatement();
    		ResultSet resultSet = statement.executeQuery(sql);
    		while(resultSet.next()){
    			String classname = resultSet.getString("classname");
    			String classnum = resultSet.getString("classnum");
    			String classwhere = resultSet.getString("classwhere");
    			list.add(new ClassRoomBean(classname,classnum, classwhere));
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
	
	//查看数据库所有的信息
		public static List<ClassRoomBean> getListbywhere(String classwhere) {
	    	
	    	List<ClassRoomBean>  list = new ArrayList<ClassRoomBean>();
	    	Connection connection = null;
	    	
	    	connection = DBUnit.getConn();
	    	
	    	String sql =  "select * from db_classroom  where  classwhere='"+classwhere+"'";
	    	
	    	try {
	    		Statement statement = connection.createStatement();
	    		ResultSet resultSet = statement.executeQuery(sql);
	    		while(resultSet.next()){
	    			String classname = resultSet.getString("classname");
	    			String classnum = resultSet.getString("classnum");
	    			list.add(new ClassRoomBean(classname,classnum, classwhere));
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
	
	public static void delete(String classname) {
	    	
	    	Connection connection = null;
	    	
	    	connection = DBUnit.getConn();
	    	
	    	String sql = "delete from db_classroom where  classname='"+classname+"'";
	 
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
		public static boolean query(String Classname,String Classnum) {
		    	
		    	Connection connection = null;
		    	connection = DBUnit.getConn();
		    	
		    	String sql = "update db_classroom  set classnum='"+Classnum+"' where  classname='"+Classname+"'";
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
    	System.out.println();
    	System.out.println("数据库连接关闭");
		connection.close();
	}
}
