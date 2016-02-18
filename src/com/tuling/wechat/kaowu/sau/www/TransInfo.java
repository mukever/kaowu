package com.tuling.wechat.kaowu.sau.www;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TransInfo {
	
	public static String ToWechatServer(Map<String, String> requestMap) {
		
		String Content = "";
		//得到map
		Map<String, NewsMessage> toWechat = TuiLingAPI.getRequest(requestMap);
		
		Set typeSet = toWechat.keySet();
		String type = "";
		Iterator it = typeSet.iterator();
		  
		//取出 type
		while(it.hasNext()){
			type = it.next().toString();
		 }
		//得到内容
		Content = getContent(type,toWechat);

		return Content;
	}

	private static String getContent(String type, Map<String, NewsMessage> toWechat) {
	    String Content = "";
	    return Content;
	}
	
	

}
