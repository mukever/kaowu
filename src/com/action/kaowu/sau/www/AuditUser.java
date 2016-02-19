package com.action.kaowu.sau.www;

import java.util.List;

import com.bean.kaowu.sau.www.TeacherBean;
import com.dao.kaowu.sau.www.RegisterDAO;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AuditUser extends ActionSupport{

	
	private static final long serialVersionUID = 1L;
	private String Type;
	private String result;
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
		
		List<TeacherBean> list = RegisterDAO.getUserList(Type);
		JSONArray jsonArray = JSONArray.fromObject(list); 
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userlist", jsonArray);
		result = jsonObject.toString();
		return SUCCESS;
	}

}
