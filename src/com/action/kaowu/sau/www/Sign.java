package com.action.kaowu.sau.www;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.bean.kaowu.sau.www.SignBean;
import com.dao.kaowu.sau.www.SignDAO;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;


public class Sign extends ActionSupport implements ServletRequestAware{

	private String username;
	private String result;
	HttpServletRequest request;
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;

	// 用户登录时签到记录数据更行
	public static void signUpdate(String Username) {

		java.util.Date time = new java.util.Date();
		// System.out.println(time);
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String currentTime = sdFormat.format(time);
		// 打印测试
		System.out.println(currentTime);

		SignBean sBean = new SignBean(Username, currentTime);
		SignDAO.add(sBean);
		// 创建session
	}

	public String  getdata() {
		
         try {
 
	             //获取数据
	         String name = request.getParameter("username");
	         String date = SignDAO.query(username);
	
	         //将数据存储在map里，再转换成json类型数据，也可以自己手动构造json类型数据
	         Map<String,Object> map = new HashMap<String,Object>();
	         map.put("date", date);
	
	         JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
	
	         result = json.toString();//给result赋值，传递给页面
	
	         } catch (Exception e) {
	
	             e.printStackTrace();
	
	         }
	 
	         return SUCCESS;

     }

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	

}
