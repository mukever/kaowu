package com.action.kaowu.sau.www;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.bean.kaowu.sau.www.ClassRoomBean;
import com.dao.kaowu.sau.www.ClassDAO;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ClassRoom extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	//前段发送的添加教室字段
	private String Classname;
	private String Classnum;
	
	//既是教室的属性也是 前段查询教学楼教室列表的字段
	private String Classwhere;
	
	//向前段返回的json数据
	private String result;
	
	public String getClassname() {
		return Classname;
	}
	public void setClassname(String classname) {
		this.Classname = classname;
	}
	public String getClassnum() {
		return Classnum;
	}
	public void setClassnum(String classnum) {
		this.Classnum = classnum;
	}
	public String getClasswhere() {
		return Classwhere;
	}
	public void setClasswhere(String classwhere) {
		this.Classwhere = classwhere;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	//用户检测用户是否登录
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
	
	//添加教室信息
	public String add() {
		//暂时先不管用户的session
		//check();
		ClassRoomBean cRoomBean = new ClassRoomBean(Classname, Classnum, Classname);
		//调用底层DAO 向数据库中添加信息
		boolean ok = ClassDAO.add(cRoomBean);
		JSONObject jsonObject = new JSONObject();
		//向前段返回处理结果
		jsonObject.put("result", ok);
		result = jsonObject.toString();
		return "classinfo";
	}
	
	//删除教室信息
	public String delete() {
		
		boolean ok = ClassDAO.delete(Classname);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("type", ok);
		result = jsonObject.toString();
		return  "delete";
	}
	
	//前台查询教学楼
	public String  getList() {
		
		
		List<ClassRoomBean> list = ClassDAO.getListbywhere(Classwhere);
		JSONArray jsonArray = JSONArray.fromObject(list); 
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("roomlist", jsonArray);
		result = jsonObject.toString();
		return "getList";
	}
	
}
