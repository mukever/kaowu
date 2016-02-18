package com.action.kaowu.sau.www;


import com.dao.kaowu.sau.www.RegisterDAO;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class Audit extends ActionSupport{

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
		//审核不通过
		if("2".equals(Type)){
			//将数据库中对应信息Type至于“0”
			RegisterDAO.chanegType("0", ID);
			//向前台返回“1”  表示处理成功
			jsonObject.put("type", "1");
		}
		//审核通过
		else if("1".equals(Type)){
			//将数据库中对应信息Type至于“2”
			RegisterDAO.chanegType("2", ID);
			//向前台返回“1”  表示处理成功
			jsonObject.put("type", "1");
		}
		result = jsonObject.toString();
		return SUCCESS;
	}

}
