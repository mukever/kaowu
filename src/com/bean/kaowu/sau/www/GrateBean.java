package com.bean.kaowu.sau.www;


public class GrateBean {
	
	//考试班级的属性
	//班级名称和班级人数
	
	private String Greatename = null;
	private String Greatenum = null;
	
	
	public String getGreatename() {
		return Greatename;
	}
	public void setGreatename(String greatename) {
		Greatename = greatename;
	}
	public String getGreatenum() {
		return Greatenum;
	}
	public void setGreatenum(String greatenum) {
		Greatenum = greatenum;
	}
	
	public GrateBean(String greatename, String i) {
		Greatename = greatename;
		Greatenum = i;
	}
	
	public GrateBean() {
		super();
	}
	
	

}
