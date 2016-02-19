package com.action.kaowu.sau.www;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.bean.kaowu.sau.www.AdminBean;
import com.dao.kaowu.sau.www.LoginDAO;
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
		
		//-1 0 1 2
		//审核 错误 管理员 普通
		String type = "0";
		
		//获取用户名密码
		AdminBean user = new AdminBean(Username,Password);
		System.out.println(Username+"\n"+Password+"\n"+"Sessionid:"+Sessionid);
		//判断当前用户是否有cookie
		//request.getCookies();
		HttpSession session = ServletActionContext.getRequest().getSession();
		//检测数据库库中的用户信息
		String status = LoginDAO.userLegitimate(user);
		String college="";
		//当用户为管理员
		if(status.equals("1")){
			//更新用户访问记录
			Sign.signUpdate(Username);
			type = "1";
			//得到用户所属学院
		    college = LoginDAO.getCollege(user);
			//Test
//			HttpServletRequest request = ServletActionContext.getRequest();
//			System.out.println(request.getLocalAddr());
//			System.out.println(request.getMethod());
//			System.out.println(request.getRemoteHost());
	       
		}
		//判断用户是否为 普通用户
		else if(status.equals("0")){
			
			status = RegisterDAO.Legitimate(Username, Password);
			type = status;
			
		}
		//既不是管理员又不是普通用户
		else{
			type = "0";
		}	
		
		//维持回话 保持用户名
		String ID = getUsername();
		session.setAttribute("id", ID);
		String sessionid = session.getId();
		 //自己手动构造json类型数据
		JSONObject json = new JSONObject();
	    json.put("sessionid", sessionid);
	    json.put("type", type);
	    json.put("college", college);
	    json.put("username", getUsername());
        System.out.println(json.toString());
        
        setResult(json.toString());//给result赋值，传递给页面
        
        return SUCCESS;
	}
}
