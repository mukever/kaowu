package com.bean.kaowu.sau.www;

public class AdminBean {
	
	//用户名和密码
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public AdminBean(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	//方便反射
	public AdminBean() {}
	
}
