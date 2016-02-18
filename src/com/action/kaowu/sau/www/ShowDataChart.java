package com.action.kaowu.sau.www;


import com.opensymphony.xwork2.ActionSupport;

public class ShowDataChart  extends ActionSupport{
	
	private String username;
	private String da;
	
	public String getDa() {
		return da;
	}

	public void setDa(String da) {
		this.da = da;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String data() {
	    // dataMap中的数据将会被Struts2转换成JSON字符串，所以这里要先清空其中的数据
       
		da = "成功放入";
        // 放入一个是否操作成功的标识
       
        // 返回结果
        return SUCCESS;
	}

}
