package com.bean.kaowu.sau.www;

public class TeacherBean {
	private String Username;
	private String Password;
	private String Therid;
	private String Age;
	private String Wechat;
	private String College;

	
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
	public String getTherid() {
		return Therid;
	}
	public void setTherid(String therid) {
		Therid = therid;
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
	
    public TeacherBean() {
    
    }
	public TeacherBean(String username, String password, String therid, String age, String wechat, String college) {
		super();
		Username = username;
		Password = password;
		Therid = therid;
		Age = age;
		Wechat = wechat;
		College = college;
	}
    
   
}
