package com.action.kaowu.sau.www;

import com.bean.kaowu.sau.www.AdminBean;
import com.bean.kaowu.sau.www.PeopleBean;
import com.bean.kaowu.sau.www.TeacherBean;
import com.dao.kaowu.sau.www.AdminDAO;
import com.dao.kaowu.sau.www.RegisterDAO;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class UserInfo extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ID;
	private String Type;
	private String result;
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public String execute() {
		
		JSONObject jsonObject = new JSONObject();
		PeopleBean people  = new TeacherBean();
		//如果用户为管理员
		if("1".equals(Type)){
			people = AdminDAO.getUserInfo(ID);
			AdminBean admin = (AdminBean)people;
			jsonObject.put("userinfo",admin);
			int count = AdminDAO.getCount(ID);
			//返回需要处理审核的用户信息个数
			jsonObject.put("count", count);
		}
		//如果用户为普通用户
		else if("2".equals(Type)){
			people = RegisterDAO.getUserInfo(ID);
			jsonObject.put("userinfo",people);
		}
		result = jsonObject.toString();
		return SUCCESS;
	
	
	}
}
