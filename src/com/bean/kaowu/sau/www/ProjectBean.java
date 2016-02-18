package com.bean.kaowu.sau.www;

import java.util.Date;

public class ProjectBean {
	
	//设置考试的科目的信息
	//科目名称+时间
	private String Projectname = null;
	private Date date = null;
	
	
	public String getProjectname() {
		return Projectname;
	}
	public void setProjectname(String projectname) {
		Projectname = projectname;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
