package com.tuling.wechat.kaowu.sau.www;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import com.CoreServer.wechat.kaowu.sau.www.HttpRequest;
import net.sf.json.JSONObject;

public class TuiLingAPI {
	/**
	 * 
	 * @param requestMap  微信服务器过来的请求头
	 * @return
	 */
	
	public static JSONObject getRequest(Map<String, String>requestMap) {
		JSONObject jsonObject = null ;
		String Content = requestMap.get("Content");
		
		//构建url
		String url = MessageCode.post_url;
		String key = MessageCode.KEY;
		String userid = requestMap.get("FromUserName").replaceAll("-", "");
		try {
			//将需要去往TuLing的内容编码成utf-8
			Content = URLEncoder.encode(Content,"UTF-8");
			url = url.replace("APIKEY", key).replace("CONTENT", Content).replace("USERID", userid);
			//直接发送post请求
			System.out.println(url);
			String result = HttpRequest.httpPostRequestToTuLing(url);
			//得到post返回的结果  json数据
			jsonObject = JSONObject.fromObject(result);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}
		
}
