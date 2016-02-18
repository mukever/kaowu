package com.tuling.wechat.kaowu.sau.www;

import java.util.ArrayList;


public class NewsMessage extends BaseMessage{
	

	private ArrayList<New> newsList = new ArrayList<>();
	
	public ArrayList<New> getNewsList() {
		return newsList;
	}
	public void setNewsList(ArrayList<New> news) {
		 this.newsList =  news;
		
	}
	

}
