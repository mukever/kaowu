package com.action.kaowu.sau.www;


import com.dao.kaowu.sau.www.RegisterDAO;
import com.opensymphony.xwork2.ActionSupport;

public class Audit extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ID;
	private String Type;
	
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

	public String execute() {
		if("0".equals(Type)){
			RegisterDAO.chanegType("0", ID);
		}else if("1".equals(Type)){
			RegisterDAO.chanegType("2", ID);
		}
		return NONE;
	}


}
