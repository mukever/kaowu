package com.units.wechat.kaowu.sau.edu.www;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import com.db.kaowu.sau.www.DBUnit;
import com.pojo.wechat.kaowu.sau.www.AccessToken;
/**
 * 保持token长期有效
 *
 */
public class GetExistAccessToken implements AppId{

	//将AccessToken写进数据库
	public static AccessToken getToken() {
	    	
	        Connection connection = null;
	        AccessToken token = new AccessToken();
	    	connection = DBUnit.getConn();
	    	String query_sql = "select * from db_accesstoken";
	    	String delete_sql = "delete from db_accesstoken";
	    	String insert_sql = "";
	    	Date nowdate =new Date();
	    	String dateformdb = "";
	    	try {
	    		
				Statement statement = connection.createStatement();
				ResultSet resultSet= statement.executeQuery(query_sql);
				//当数据库中有数据时
				if(resultSet.next()){
					dateformdb = resultSet.getString(3);
					//判断数据库中的数据是否有效 为了保存数据的有效性   将token的有效期缩短50秒  
					if(nowdate.getTime()-Long.parseLong(dateformdb)<=7150000){
						System.out.println("数据库");
						 token.setToken(resultSet.getString(2));
						 token.setExpiresIn(7200);
					}else{
						//数据库中的token失效了
						// 调用接口获取access_token
						System.out.println("获取");
						AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
						//删除原有token
						statement.executeUpdate(delete_sql);
						//拼接inset_sql
					    insert_sql = "insert into db_accesstoken (token, date) values('"+at.getToken()+"' ,'"+nowdate.getTime()+"')";
						//跟新token
						statement.executeUpdate(insert_sql);
						//返回的token
						token = at;
					}
				}else{
					//数据库没有数据时肯定直接从微信服务器获取 然后存入数据库
					
					// 调用接口获取access_token
					AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
					//拼接inset_sql
					 insert_sql = "insert into db_accesstoken (token, date) values('"+at.getToken()+"' ,'"+nowdate.getTime()+"')";
					//跟新token
					System.out.println("获取");
					statement.executeUpdate(insert_sql);
					//返回的token
					token = at;
					
				}
				//关闭数据库连接
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
	    	return token;
		}
	// 关闭数据库连接
    public static void closeConnection(Connection connection) throws SQLException {
  
		connection.close();
	}
	
}
