package com.bean.kaowu.sau.www;

public class AdminBean extends PeopleBean{
	
	//用户名和密码
	private String Username;
	private String Password;
	
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		this.Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		this.Password = password;
	}
	public AdminBean(String username, String password) {
		this.Username = username;
		this.Password = password;
	}
	
	//方便反射
	public AdminBean() {}
	
}
