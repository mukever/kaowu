package com.avatardata.wechat.kaowu.sau.www;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.CoreServer.wechat.kaowu.sau.www.HttpRequest;
import com.resp.message.wechat.kaowu.sau.www.Article;

import net.sf.json.JSONObject;

public class avatardataAPI {
	
	public static ArrayList<Article> getWheather(String Location) {
		ArrayList<Article> articlesList = null ;
		JSONObject jsonObject;
		try {
			Location = URLEncoder.encode(Location,"UTF-8");
			String requestUrl = URL.WEATHER_URL.replace("CITY", Location);
			jsonObject = JSONObject.fromObject(HttpRequest.httpGetRequest(requestUrl));
			System.out.println(jsonObject);
			articlesList = getArticleList(jsonObject);
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		return articlesList;
		
	}

	/**
	 * 0  晴
	 * 1  多云
     * 2 阴
     * 3 阵雨
     * 7 小雨
     * 8 中雨
	 * @param jsonObject
	 * @return
	 */
	public static ArrayList<Article> getArticleList(JSONObject jsonObject) {
		ArrayList<Article> articlesList = new ArrayList<>();
		Article title = new Article();
		String result = jsonObject.getString("result");
		String realtime = JSONObject.fromObject(result).getString("realtime");
		String city_name = JSONObject.fromObject(realtime).getString("city_name");
		title.setTitle(city_name+"天气预报");
		articlesList.add(title);
		String dir = JSONObject.fromObject(JSONObject.fromObject(realtime).getString("weather")).getString("info");
		String temperature = JSONObject.fromObject(JSONObject.fromObject(realtime).getString("weather")).getString("temperature");
		title.setDescription(dir+"  温度:"+temperature+"°C\n");
		return articlesList;
	}
}
