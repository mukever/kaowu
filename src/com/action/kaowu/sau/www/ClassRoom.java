package com.action.kaowu.sau.www;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.bean.kaowu.sau.www.ClassRoomBean;
import com.dao.kaowu.sau.www.ClassDAO;
import com.opensymphony.xwork2.ActionSupport;

public class ClassRoom extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private String Classname;
	private String Classnum;
	
	public String getClassname() {
		return Classname;
	}
	public void setClassname(String classname) {
		Classname = classname;
	}
	public String getClassnum() {
		return Classnum;
	}
	public void setClassnum(String classnum) {
		Classnum = classnum;
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
		ClassRoomBean cRoomBean = new ClassRoomBean(Classname, Classnum);
		boolean ok = ClassDAO.add(cRoomBean);
		if(ok){
			session.setAttribute("Msg", "信息添加成功");
		}else{
			session.setAttribute("Msg", "信息添加失败");
		}
		//System.out.println(session.getAttribute("id") +"添加教室信息");
		//System.out.println("数据更行成功");
		
		return "classinfo";
	}
	public String delete() {
	    check();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String id = ServletActionContext.getRequest().getParameter("id");
		ClassDAO.delete(id);
		//System.out.println(session.getAttribute("id") +"添加教室信息");
		System.out.println("数据删除成功");
		
		return  "delete";
	}
	public String update() {
		check();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String id = ServletActionContext.getRequest().getParameter("id");
		//先获取相关信息  返回到页面在页面上显示
		//直接在页面上根据相关信息更改
		ClassRoomBean c = ClassDAO.getClassinfo(id);
		session.setAttribute("classname",c.getClassRoomname());
		session.setAttribute("classnum" ,c.getClassRoomNum());
		
		return  "updata";
	}
	public String query() {
		check();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String name = Classname;
		String num = Classnum;
		
		boolean ok = ClassDAO.query(Classname,Classnum);
		if(ok){
			session.setAttribute("Msg", "信息添加成功");
		}else{
			session.setAttribute("Msg", "信息添加失败");
		}
		//System.out.println(session.getAttribute("id") +"添加教室信息");
		//System.out.println("数据更行成功");
		
		return "classinfo";
	}
}
