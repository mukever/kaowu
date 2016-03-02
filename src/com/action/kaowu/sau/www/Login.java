package com.action.kaowu.sau.www;


import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;
import org.apache.struts2.ServletActionContext;
import com.bean.kaowu.sau.www.AdminBean;
import com.dao.kaowu.sau.www.AdminDAO;
import com.dao.kaowu.sau.www.RegisterDAO;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;

public class Login  extends ActionSupport  {
	
	private static final long serialVersionUID = 1L;
	//获取到用户的相关登陆信息
	//用户名
	private String Username;
	//用户密码
	private String Password;
	//sessionid
	private String Sessionid;
	//结果信息json字符串
	private String result;
	//private HttpServletRequest request;  
	private Map<String, String> college_map = new HashMap<>();
	
	public String getSessionid() {
		return Sessionid;
	}

	public void setSessionid(String sessionid) {
		Sessionid = sessionid;
	}
	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}


	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	//处理用户请求的execute方法
	public String execute() {
	    try {
			ServletActionContext.getRequest().setCharacterEncoding("utf8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initHaspMap();
		//-1 0 1 2
		//审核 错误 管理员 普通
		String type = "0";
		
		//获取用户名密码
		AdminBean user = new AdminBean(Username,Password);
		//System.out.println(Username+"\n"+Password+"\n"+"Sessionid:"+Sessionid);
		//判断当前用户是否有cookie
		//request.getCookies();
		HttpSession session = ServletActionContext.getRequest().getSession();
		//检测数据库库中的用户信息
		String status = AdminDAO.userLegitimate(user);
		String college_num="";
		//当用户为管理员
		if("1".equals(status)){
			//更新用户访问记录
			Sign.signUpdate(Username);
			type = "1";
			//得到用户所属学院
			
		    college_num = AdminDAO.getCollege(user);
		    String college_string = college_map.get(college_num);
		    session.setAttribute("College_num", college_num);
		    session.setAttribute("College",college_string);
	       
		}
		//判断用户是否为 普通用户
		else if("0".equals(status)){
			
			status = RegisterDAO.Legitimate(Username, Password);
			if("-1".equals(status) || "2".equals(status)){
				college_num = RegisterDAO.getUserInfo(Username).getCollege();
				String college_string = college_map.get(college_num);
				session.setAttribute("College", college_string);
			}						
			type = status;
			
		}
		//既不是管理员又不是普通用户
		else{
			type = "0";
		}	
		
		
		//维持回话 保持用户名
		session.setAttribute("Username", Username);
		String sessionid = session.getId();
		//自己手动构造json类型数据
		JSONObject json = new JSONObject();
	    json.put("sessionid", sessionid);
	    json.put("type", type);
	    json.put("college", college_num);
	    json.put("username", getUsername());
        System.out.println(json.toString());
        
        setResult(json.toString());//给result赋值，传递给页面
        
        return SUCCESS;
	}
	
	
	private void initHaspMap(){
		college_map.put("1", "计算机学院");
		college_map.put("2", "电子信息工程学院");
		college_map.put("3", "航空航天工程学部");
		college_map.put("4", "安全工程学院");
		college_map.put("5", "机电工程学院");
		college_map.put("6", "自动化学院");
		college_map.put("7", "经济与管理学院");
		college_map.put("8", "设计艺术学院");
		college_map.put("9", "材料科学与工程学院");
		college_map.put("10", "能源与环境学院");
		college_map.put("11", "理学院");
		college_map.put("12", "外国语学院");
		college_map.put("13", "民用航空学院");
		college_map.put("14", "创新学院");
	}
}
