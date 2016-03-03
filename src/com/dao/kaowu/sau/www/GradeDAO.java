package com.dao.kaowu.sau.www;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.kaowu.sau.www.GradeBean;
import com.db.kaowu.sau.www.DBUnit;

public class GradeDAO {
	

   //向数据库中添加班级信息
	
    public static boolean addGrade( GradeBean gradeBean) {
    	
        Connection connection = null;
    	
    	connection = DBUnit.getConn();
    	
    	String sql = "insert into db_grade(gradeid,gradename,gradenum,college) values('"+gradeBean.getGradeid()+"','"+gradeBean.getGradename()+"','"+gradeBean.getGradenum()+"','"+gradeBean.getGradecollege()+"')";
    	
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
			return false;
		}
    	return true;
	}
    //查询所在学院班级的信息
	public static List<GradeBean> getList(String college) {
    	
    	List<GradeBean>  list = new ArrayList<GradeBean>();
    	Connection connection = null;
    	
    	connection = DBUnit.getConn();
    	
    	String sql = "select * from db_grade  where  college='"+college+"'";
    	System.out.println(sql);
    	try {
    		Statement statement = connection.createStatement();
    		ResultSet resultSet = statement.executeQuery(sql);
    		while(resultSet.next()){
    			String gradename = resultSet.getString("gradename");
	    		int gradenum = resultSet.getInt("gradenum");
	    		String gradeid = resultSet.getString("gradeid");
    			list.add(new GradeBean(gradeid, gradename, gradenum));
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
	
	public static boolean delete(String id,String gradecollege) {
	    	
	    	Connection connection = null;
	    	
	    	connection = DBUnit.getConn();
	    	
	    	String sql = "delete from db_grade where  gradeid='"+id+"'"+" and gradecollege='"+gradecollege+"'";
	    	
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
	    		return false;
	    	}
	    	return true;
	    }
    // 关闭数据库连接
    public static void closeConnection(Connection connection) throws SQLException {
    	System.out.println("数据库连接关闭");
		connection.close();
	}

}
