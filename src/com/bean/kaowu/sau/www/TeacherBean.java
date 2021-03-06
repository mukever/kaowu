package com.bean.kaowu.sau.www;

public class TeacherBean extends AdminBean{
	private String Username;
	private String Password;
	private String Therid; //教职工编号  登陆所用
	private String Age;
	private String Wechat;
	private String College;
	private String Type;
	private String Position; //职位
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
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}

	public String getPosition() {
		return Position;
	}
	public void setPosition(String position) {
		Position = position;
	}

	public TeacherBean(String username, String password, String therid, String age, String wechat, String college,String type,String position) {
		super();
		Username = username;
		Password = password;
		Therid = therid;
		Age = age;
		Wechat = wechat;
		College = college;
		Type = type;
		Position = position;
	}
	
	public TeacherBean(String username, String therid, String age, String wechat, String college,String type) {
		super();
		Username = username;
		Therid = therid;
		Age = age;
		Wechat = wechat;
		College = college;
		Type = type;
	}
	
	
	//保留无参构造器
    public TeacherBean(){
    	
    }
	   
}
