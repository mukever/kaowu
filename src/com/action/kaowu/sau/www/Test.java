package com.action.kaowu.sau.www;

import org.aspectj.apache.bcel.generic.ReturnaddressType;

import com.mysql.jdbc.jdbc2.optional.SuspendableXAConnection;
import com.opensymphony.xwork2.ActionSupport;

public class Test extends ActionSupport{
	
	private String Time ;
	private String Project ;
	
	public String getProject() {
		return Project;
	}
	public void setProject(String project) {
		Project = project;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
	
	//创建新的考试信息  并获取Time和  Project
	public String createnewtest(){
		return SUCCESS;
	}

	//得到获取的年级
	public String  getselectgrades() {
		return SUCCESS;
	}
	
	//得到选择的教室
	public String getselectclassrooms(){
		return SUCCESS;
	}
}
