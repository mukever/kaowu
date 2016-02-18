package com.bean.kaowu.sau.www;


public class SignBean {
	
	
	//用户登录时间
	private String username = null;
	private String date ;
	private int frequency;
	
	
	//javabean
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	
	
	//三个构造方法
	
	public SignBean(String username, String date, int frequency) {
		super();
		this.username = username;
		this.date = date;
		this.frequency = frequency;
	}
	
	public SignBean(String username, String date) {
		super();
		this.username = username;
		this.date = date;
	}
	
	public SignBean() {
		super();
	}
	
	
	
	
	
}
