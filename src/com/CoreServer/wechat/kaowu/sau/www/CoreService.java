package com.CoreServer.wechat.kaowu.sau.www;


import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.MessageUtil.wechat.kaowu.sau.www.MessageUtil;

/**
 * 核心服务类
 */
public class CoreService {
	/**
	 * 处理微信发来的请求
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			
			respMessage = MessageResp.getResp(requestMap);
			 
	
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}
	
	
	
}
