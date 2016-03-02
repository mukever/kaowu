package com.action.kaowu.sau.www;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.bean.kaowu.sau.www.GradeBean;
import com.dao.kaowu.sau.www.GradeDAO;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class Grade extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	
	//班级编号
	private String Gradeid;
	//班级名称
	private String Gradename;
	//班级人数
	private String Gradenum;
	//班级所属学院
	private String Gradecollege;
	//向前段页面返回json数据
	private String  result;
	
	
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

	public String getGradecollege() {
		return Gradecollege;
	}

	public void setGradecollege(String gradecollege) {
		Gradecollege = gradecollege;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	public void check() {
	//解决乱码，用于页面输出
		HttpServletResponse response=null;
		response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			//创建session对象
			HttpSession session = ServletActionContext.getRequest().getSession();
			//验证是否正常登录
			if(session.getAttribute("id")==null){
				out.print("<script language='javascript'>alert('请重新登录！');window.location='login.jsp';</script>");
				out.flush();out.close();	
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    
    //添加信息
	public String add() {
//		check();
		HttpSession session = ServletActionContext.getRequest().getSession();
		Gradecollege = (String) session.getAttribute("College_num");
		GradeBean grate = new GradeBean(Gradename,Gradenum, Gradeid,Gradecollege);
		//System.out.println(  "班级名称"+getGreatename() + "\n班级人数"+getGreatenum());
		boolean ok = GradeDAO.addGrade(grate);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("type", ok);
		setResult(jsonObject.toString());
		return "add";
	}
	
	
	//删除班级信息
	public String delete() {
		check();
		HttpSession session = ServletActionContext.getRequest().getSession();
		Gradecollege = (String) session.getAttribute("College_num");
		boolean ok = GradeDAO.delete(Gradeid,Gradecollege);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("type", ok);
		setResult(jsonObject.toString());
		return  "delete";
	}
	
	public String getList() {
		//创建session对象
		HttpSession session = ServletActionContext.getRequest().getSession();
		String gradecollege = (String) session.getAttribute("gradecollege");
		ArrayList<GradeBean> list = (ArrayList<GradeBean>) GradeDAO.getList(gradecollege);
		JSONArray jsonArray = JSONArray.fromObject(list); 
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("gradelist", jsonArray);
		setResult(jsonObject.toString());
		return "getList";
	}

	
	
}
