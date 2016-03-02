package com.bean.kaowu.sau.www;


public class GradeBean {
	
	//考试班级的属性
	//班级名称和班级人数
	
	private String Gradeid = null;
	private String Gradename = null;
	private String Gradenum = null;
	private String Gradecollege = null;
	
	public String getGradecollege() {
		return Gradecollege;
	}
	public void setGradecollege(String gradecollege) {
		Gradecollege = gradecollege;
	}
	public String getGradeid() {
		return Gradeid;
	}
	public void setGradeid(String gradeid) {
		Gradeid = gradeid;
	}
	public String getGradename() {
		return Gradename;
	}
	public void setGradename(String gradename) {
		Gradename = gradename;
	}
	public String getGradenum() {
		return Gradenum;
	}
	public void setGradenum(String gradenum) {
		Gradenum = gradenum;
	}
	public GradeBean(String gradeid, String gradename, String gradenum, String gradecollege) {
		super();
		Gradeid = gradeid;
		Gradename = gradename;
		Gradenum = gradenum;
		Gradecollege = gradecollege;
	}
	public GradeBean(String gradeid, String gradename, String gradenum) {
		super();
		Gradeid = gradeid;
		Gradename = gradename;
		Gradenum = gradenum;
	}
	
	
	
	
	
	
	

}
