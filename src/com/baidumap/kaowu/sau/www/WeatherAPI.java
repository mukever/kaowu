package com.baidumap.kaowu.sau.www;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import com.CoreServer.wechat.kaowu.sau.www.HttpRequest;
import com.dao.kaowu.sau.www.WheatherDAO;
import net.sf.json.JSONObject;


public class WeatherAPI  extends HttpRequest{
	
	//百度的ak秘钥
	static String  AK = "Xe94omVLQdsxCDfHtGypCGHF";
	//post的百度的url
	static String  CITY_URL ="http://api.map.baidu.com/geocoder/v2/?ak=SECRETKEY&callback=renderReverse&location=LAT_LON&output=json&pois=1";
	
	/**
	 * 
	 * @param lat_lon
	 * @return
	 */
	public static String getPoint(String lat_lon) {
		JSONObject jsonObject = null;
		String point = null;
		try {
			lat_lon = URLEncoder.encode(lat_lon,"UTF-8");
			String url = CITY_URL.replace("SECRETKEY", AK).replace("LAT_LON", lat_lon);
			
			// 发送https请求 返回JSON数据包
			String io = httpPostRequest(url);
			io = io.replace("renderReverse&&renderReverse(", "");
			String json = io.substring(0, io.length()-1);
			jsonObject = JSONObject.fromObject(json);
			JSONObject addressComponent = JSONObject.fromObject(JSONObject.fromObject(jsonObject.getString("result")).getString("addressComponent"));
			String province = addressComponent.getString("province").replace("省", "").replace("市", "");
			String city = addressComponent.getString("city").replace("市", "");
			String district = addressComponent.getString("district").replace("县", "");
			point = WheatherDAO.getCityID(province, city, district);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return point;
		
	}
    
}
