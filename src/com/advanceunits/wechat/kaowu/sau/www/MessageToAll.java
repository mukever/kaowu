package com.advanceunits.wechat.kaowu.sau.www;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.advanceunits.wechat.*;
import com.units.wechat.kaowu.sau.edu.www.GetExistAccessToken;
import net.sf.json.JSONObject;

public class MessageToAll {
	
	  
	public static String MessageTo(String url, JSONObject jsonObject) throws IOException {
		String result = null;

		/**
		 * 第一部分
		 */
		URL urlObj = null;
		try {
			urlObj = new URL(url);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 连接
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

		/**
		 * 设置关键值
		 */
		con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); // post方式不能使用缓存

		// 设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");

		// 设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

		// 请求正文信息
		// 获得输出流
		DataOutputStream out = new DataOutputStream(con.getOutputStream());
		
		// 输出表头
		out.writeBytes(jsonObject.toString());
		out.flush();
        out.close();

		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		try {
			// 定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				// System.out.println(line);
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
			throw new IOException("数据读取异常");
		} finally {
			if (reader != null) {
				reader.close();
			}

		}

		JSONObject jsonObj = JSONObject.fromObject(result);
		System.out.println("Server：  "+jsonObj.toString());
		
		return null;
	}

      
	public static void sendArticles(String Imageid) throws UnsupportedEncodingException {
		//get media id
		
		//构建图文消息
		List<ArticlesMessage>  articles = new ArrayList<ArticlesMessage>(); 
		ArticlesMessage articlesMessage =  new ArticlesMessage();
		articlesMessage.setAuthor("mukever");
		articlesMessage.setContent("您的考试信息");
		articlesMessage.setThumb_media_id(Imageid);
		articlesMessage.setTitle("新的考试信息");
		articlesMessage.setContent_source_url("http://120.27.118.76/kaowu_version_6/login.html");
		articles.add(articlesMessage);
	

		try {
			//创建json
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("articles", articles);
			//得到图文消息的media_id
			System.out.println(jsonObject);
			String idString= MessageUpload.ArticlesUpLoad(jsonObject);
			
			//准备发送
			JSONObject sendArticles = new JSONObject();
			Filter filter = new Filter();
			filter.setGroup_id("0");
			filter.setIs_to_all(false);
			
			Mpnews mpnews = new Mpnews();
			mpnews.setMedia_id(idString);
			
			
			sendArticles.put("filter", filter);
			sendArticles.put("mpnews", mpnews);
			
	
			sendArticles.put("msgtype", "mpnews");
			System.out.println(sendArticles);
			
			//得到了mediaid后发送
			String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
			System.out.println(sendArticles);
			url = url.replace("ACCESS_TOKEN", GetExistAccessToken.getToken().getToken());
			MessageTo(url,sendArticles);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	} 
	
	public static void sendText() {
		try {
			//创建json
			JSONObject jsonObject = new JSONObject();
			
			Filter filter = new Filter();
			filter.setGroup_id("0");
			filter.setIs_to_all(false);
			jsonObject.put("filter", filter);
			
			
			TextContent content = new TextContent();
			String text_content ="\ue301<a href=\"http://120.27.118.76/kaowu_version_6/login.html\">您近期有新的监考任务</a>\n请点击近期监考查询";
			String resulrt = new String(text_content.getBytes("UTF-8"), "ISO8859_1");
			content.setContent(resulrt);
			jsonObject.put("text", content);
			jsonObject.put("msgtype", "text");
			System.out.println(jsonObject);
			
			//发送
			String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
			System.out.println(jsonObject);
			url = url.replace("ACCESS_TOKEN", GetExistAccessToken.getToken().getToken());
			MessageTo(url,jsonObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String args[]) throws UnsupportedEncodingException {
		sendText();
	}
	
}
