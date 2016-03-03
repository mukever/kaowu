package com.bean.kaowu.sau.www;


public class GradeBean {
	
	//考试班级的属性
	//班级名称和班级人数
	
	private String Gradeid = null;
	private String Gradename = null;
	private int Gradenum  ;
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
	public int getGradenum() {
		return Gradenum;
	}
	public void setGradenum(int gradenum) {
		Gradenum = gradenum;
	}
	public GradeBean(String gradeid, String gradename, int gradenum, String gradecollege) {
		super();
		Gradeid = gradeid;
		Gradename = gradename;
		Gradenum = gradenum;
		Gradecollege = gradecollege;
	}
	public GradeBean(String gradeid, String gradename, int gradenum) {
		super();
		Gradeid = gradeid;
		Gradename = gradename;
		Gradenum = gradenum;
	}
	
	
	
	
	
	
	

}
