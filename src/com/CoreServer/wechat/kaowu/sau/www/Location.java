package com.CoreServer.wechat.kaowu.sau.www;

import java.util.Map;

public class Location {
	
	public static String getLocation(Map<String, String>requestMap) {
		//纬度
		String latitude = requestMap.get("Location_X");
		//经度
		String longitude = requestMap.get("Location_Y");
		
		String result = latitude+","+longitude;
	
		return result;
	}

}
