package com.action.kaowu.sau.www;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.bean.kaowu.sau.www.GrateBean;
import com.dao.kaowu.sau.www.ClassDAO;
import com.dao.kaowu.sau.www.GreateDAO;
import com.opensymphony.xwork2.ActionSupport;


public class Greate extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	private String Greatename;
	private String Greatenum;

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
	public String add() {
		check();
		HttpSession session = ServletActionContext.getRequest().getSession();
		GrateBean grate = new GrateBean(Greatename,Greatenum);
		//System.out.println(  "班级名称"+getGreatename() + "\n班级人数"+getGreatenum());
		GreateDAO.addGreate(grate);
		return "add";
	}
	
	public String delete() {
		check();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String id = ServletActionContext.getRequest().getParameter("id");
		GreateDAO.delete(id);
		//System.out.println(session.getAttribute("id") +"添加教室信息");	
		return  "delete";
	}
	public String updata() {
	    check();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String id = ServletActionContext.getRequest().getParameter("id");
		//先获取相关信息  返回到页面在页面上显示
		//直接在页面上根据相关信息更改
		GrateBean g = GreateDAO.getGrateinfo(id);
		
		session.setAttribute("Greatename",g.getGreatename());
		session.setAttribute("Greatenum" ,g.getGreatenum());
		
		return  "updata";
	}
	
	public String query() {
	    check();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String name = Greatename;
		String num = Greatenum;
		
		boolean ok = ClassDAO.query(Greatename,Greatenum);
		if(ok){
			session.setAttribute("Msg", "信息添加成功");
		}else{
			session.setAttribute("Msg", "信息添加失败");
		}
		//System.out.println(session.getAttribute("id") +"添加教室信息");
		//System.out.println("数据更行成功");
		
		return "info";
	}
	
}
