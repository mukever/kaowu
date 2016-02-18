package com.avatardata.wechat.kaowu.sau.www;


import java.util.ArrayList;
import com.CoreServer.wechat.kaowu.sau.www.HttpRequest;
import com.resp.message.wechat.kaowu.sau.www.Article;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class News {
	
	static ArrayList<Article> articlesList = new ArrayList<>();
	
	public static synchronized ArrayList<Article> getNewsArticles() {
		getArticles();
		return articlesList;
		
	}
	
	public static void getArticles() {
		JSONObject jsonObject;
		//国内新闻
		String requestUrl = URL.GuoNeiNews_URL;
		jsonObject = JSONObject.fromObject(HttpRequest.httpGetRequest(requestUrl));
		getArticleListfromJson(jsonObject);
		//科技新闻
		requestUrl = URL.TechNews_URL;
		jsonObject = JSONObject.fromObject(HttpRequest.httpGetRequest(requestUrl));
		getArticleListfromJson(jsonObject);
		//国际新闻
		requestUrl = URL.WorldNews_URL;
		jsonObject = JSONObject.fromObject(HttpRequest.httpGetRequest(requestUrl));
		getArticleListfromJson(jsonObject);
		
	}

	private static void getArticleListfromJson(JSONObject jsonObject) {
		
		String result = jsonObject.getString("result");
		JSONArray array = JSONArray.fromObject(result);
		for (int i = 0; i < array.size(); i++) {
		   JSONObject o = array.getJSONObject(i);
		   Article article = new Article();
		   article.setTitle(o.getString("title"));
		   article.setDescription(o.getString("description"));
		   article.setPicUrl(o.getString("picUrl"));
		   article.setUrl(o.getString("url"));
		   articlesList.add(article);
		}
	
	}

}
