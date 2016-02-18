package com.CoreServer.wechat.kaowu.sau.www;


import java.util.ArrayList;
import java.util.Map;
import com.resp.message.wechat.kaowu.sau.www.Article;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class MessageFormTuLing {
	
	private static //图文消息
	ArrayList<Article> articleList = new ArrayList<>();
	private static //纯文本消息
	String respContent = "";
	
	public static String getxml(JSONObject jsonObject,Map<String, String> requestMap) {
		String xml = "";
		int code = jsonObject.getInt("code");
		getContent(jsonObject);
		
		if(isText(code)){
			//是文本的话直接将respContent 传进去
			xml = CoreMassageResp.getTextMessageXml(requestMap, respContent);
			
		}else {
			
			xml = CoreMassageResp.getNewsMessageXml(requestMap, articleList);
		}
		System.out.println(xml);
		return xml;
	}

	
	private static boolean isText(int code) {
		if(code==100000 || code==200000)
			return true;
		return false;
		
	}


	private static void getContent(JSONObject jsonObject) {
		
		int code = jsonObject.getInt("code");
		//文本类
		if(code == 100000){
			respContent = jsonObject.getString("text");
			
		}
		//url类
		else if (code == 200000) {
			String url = jsonObject.getString("url");
			String text = jsonObject.getString("text");
			respContent = "<a href=\""+url+"\">"+text+"</a>";
		}
		//新闻类
		else if (code == 302000) {
			JSONArray result = JSONArray.fromObject(jsonObject.get("list"));
	    	for (int i = 0; i < result.size(); i++) {
	    	
	    		 Article article = new Article();
	    		 JSONObject o = result.getJSONObject(i);
	    		
	    		 article.setTitle(o.getString("article"));
	    		 article.setDescription(o.getString("source"));
	    		 article.setUrl(o.getString("detailurl"));
	    		 if(!o.getString("icon").equals("")){
	    			 article.setPicUrl(o.getString("icon"));
	    		 }
	    		 articleList.add(article);
	    		 articleList.get(0).setPicUrl("http://46048f95.nat123.net/kaowu_version_3/weChatimgs/wangyiNews.jpg");
	    		
	    	}
		}
		//菜谱类
		else if (code == 308000) {
			JSONArray result = JSONArray.fromObject(jsonObject.get("list"));
	    	for (int i = 0; i < result.size(); i++) {
	    		 Article article = new Article();
	    		 JSONObject o = result.getJSONObject(i);
	    		 article.setTitle(o.getString("name"));
	    		 article.setDescription(o.getString("info"));
	    		 article.setUrl(o.getString("detailurl"));
	    		 if(!o.getString("icon").equals("")){
	    			 article.setPicUrl(o.getString("icon"));
	    		 }
	    		 articleList.add(article);
	    		
	    	}
			
		}else {
			System.out.println("特殊情况");
		}
		
	}
}
