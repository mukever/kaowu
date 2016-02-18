package com.tuling.wechat.kaowu.sau.www;

import java.util.ArrayList;

public class CookBookMessage extends BaseMessage{
	
	private ArrayList<CookBook> lists;//来源

	
	public ArrayList<CookBook> getLists() {
		return lists;
	}
	public void setLists(CookBook lists) {
		this.lists.add(lists);
	}
	
	

}
