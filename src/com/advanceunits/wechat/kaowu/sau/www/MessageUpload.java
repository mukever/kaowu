package com.advanceunits.wechat.kaowu.sau.www;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.units.wechat.kaowu.sau.edu.www.GetExistAccessToken;

import net.sf.json.JSONObject;

public class MessageUpload {
	
	/**
	 * 模拟form表单的形式 ，上传文件 以输出流的形式把文件写入到url中，然后用输入流来获取url的响应
	 * 
	 * @param url
	 *            请求地址 form表单url地址
	 * @param filePath
	 *            文件在服务器保存路径
	 * @return String url的响应信息返回值
	 * @throws IOException
	 */
	public static String send(String url, JSONObject jsonObject) throws IOException {
		String mediaId = null;
		String result = null;

		/**
		 * 第一部分
		 */
		URL urlObj = new URL(url);
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

		// 第一部分：
		// 获得输出流
		DataOutputStream out = new DataOutputStream(con.getOutputStream());
		
		// 输出表头
		out.writeBytes(jsonObject.toString());
		System.out.println(jsonObject.toString());
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
		try{
			mediaId = jsonObj.getString("media_id");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return mediaId;
	}
	public static String ArticlesUpLoad(JSONObject jsonObject)  {
	
		String sendUrl = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";
		sendUrl = sendUrl.replace("ACCESS_TOKEN", GetExistAccessToken.getToken().getToken());
		String result = null;
		try {
			result = MessageUpload.send(sendUrl, jsonObject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

}
