package com.action.kaowu.sau.www;

import com.bean.kaowu.sau.www.TeacherBean;
import com.dao.kaowu.sau.www.RegisterDAO;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class Register extends ActionSupport {
	
	
	private static final long serialVersionUID = 1L;
	
	
	private String Username;
	private String Password;
	private String Therid;
	private String Age;
	private String Wechat;
	private String College;
	private String result;
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
	public String getID() {
		return Therid;
	}
	public void setID(String iD) {
		Therid = iD;
	}
	public String getAge() {
		return Age;
	}
	public void setAge(String age) {
		Age = age;
	}
	public String getWechat() {
		return Wechat;
	}
	public void setWechat(String wechat) {
		Wechat = wechat;
	}
	public String getCollege() {
		return College;
	}
	public void setCollege(String college) {
		College = college;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	
	public String execute() {
		//添加
		TeacherBean teacher = new TeacherBean(Username, Password, Therid, Age, Wechat, College);
		String type = RegisterDAO.add(teacher);
		 //自己手动构造json类型数据
		JSONObject json = new JSONObject();
	    json.put("type", type);
	    
        System.out.println(json.toString());
        
        setResult(json.toString());//给result赋值，传递给页面
		return SUCCESS;
		
	}
	

	
	

}
