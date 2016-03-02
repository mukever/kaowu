package com.bean.kaowu.sau.www;


public class ClassRoomBean {
	
	//设置教室属性   教室名+教室人数
	private String classRoomname = null;
	private String classRoomNum = null;
	private String classRoomwhere = null;
	public String getClassRoomwhere() {
		return classRoomwhere;
	}
	public void setClassRoomwhere(String classRoomwhere) {
		this.classRoomwhere = classRoomwhere;
	}
	//设置相应的getter  和  setter  方法
	public String getClassRoomname() {
		return classRoomname;
	}
	public void setClassRoomname(String classRoomname) {
		this.classRoomname = classRoomname;
	}
	public String getClassRoomNum() {
		return classRoomNum;
	}
	public void setClassRoomNum(String classRoomNum) {
		this.classRoomNum = classRoomNum;
	}
	public ClassRoomBean(String classRoomname, String classRoomNum,String classRoomwhere) {
		super();
		this.classRoomname = classRoomname;
		this.classRoomNum = classRoomNum;
		this.classRoomwhere = classRoomwhere;
	}
	
	
}
