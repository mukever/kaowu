package com.CoreServer.wechat.kaowu.sau.www;

import java.util.Map;
import com.MessageUtil.wechat.kaowu.sau.www.MessageUtil;
import com.avatardata.wechat.kaowu.sau.www.News;
import com.avatardata.wechat.kaowu.sau.www.avatardataAPI;
import com.baidumap.kaowu.sau.www.WeatherAPI;
import com.manager.wechat.kaowu.sau.www.UserInfoManager;
import com.tuling.wechat.kaowu.sau.www.TuiLingAPI;
import net.sf.json.JSONObject;


public class MessageResp {
	
	public static String getResp(Map<String, String> requestMap) {
		System.out.println(requestMap.get("FromUserName")+"访问了微信");
		String xml = null;
		// 默认返回的文本消息内容
		String respContent =  "请求处理异常，请稍候尝试！";
		String msgType = requestMap.get("MsgType");
		
		// 文本消息
		if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
			
			//首先将消息过滤根据情况是否需要转到图灵机器人
			
			//如果关键字为  考试或者其他  需要一个过滤器    自己处理
			
			//如果为其他则   转到图灵机器人
			
			//将信息内容传过去   得到响应码+内容  json  
			JSONObject jsonObject  = TuiLingAPI.getRequest(requestMap);
			//构建对应的xml
			xml = MessageFormTuLing.getxml(jsonObject,requestMap);
			
		}
		// 图片消息
		else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
			respContent = "您发送的是图片消息！";
			xml = CoreMassageResp.getTextMessageXml(requestMap, respContent);
		}
		// 地理位置消息 
		else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
			//这个地方要改为天气预报
			respContent = "您发送的是地理位置消息！";
			String lat_lon = Location.getLocation(requestMap);
			
			//xml = CoreMassageResp.getTextMessageXml(requestMap, respContent);
			xml = CoreMassageResp.getNewsMessageXml(requestMap, avatardataAPI.getWheather(WeatherAPI.getPoint(lat_lon)));
			
		}
		// 链接消息
		else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
			respContent = "您发送的是链接消息！";
			xml = CoreMassageResp.getTextMessageXml(requestMap, respContent);
		}
		// 音频消息
		else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
			respContent = "您发送的是音频消息！";
			xml = CoreMassageResp.getTextMessageXml(requestMap, respContent);
		}
		// 事件推送
		else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
			// 事件类型
			String eventType = requestMap.get("Event");
			// 订阅
			if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
				//获取用户信息  并添加到数据库
				UserInfoManager.addTodatabase(requestMap);
				respContent = "谢谢您的关注！";
				xml = CoreMassageResp.getTextMessageXml(requestMap, respContent);
				
			}
			//上报地理位置信息
			else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
				//String lat_lon = Location.getLocation(requestMap);
				//WeatherAPI.POI("美食", lat_lon);
				respContent= "";
				xml = CoreMassageResp.getTextMessageXml(requestMap, respContent);
			}
			// 取消订阅
			else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
				// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				//取消订阅 删除其在数据库中的记录
				UserInfoManager.deletefromdatabase(requestMap);
			}
			// 自定义菜单点击事件
			else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
				// 事件KEY值，与创建自定义菜单时指定的KEY值对应
				String eventKey = requestMap.get("EventKey");
				
			
				if (eventKey.equals("11")) {
					respContent = "近期监考菜单项被点击！";
					xml = CoreMassageResp.getTextMessageXml(requestMap, respContent);
				} else if (eventKey.equals("12")) {
					respContent = "监考次数菜单项被点击！";
					xml = CoreMassageResp.getTextMessageXml(requestMap, respContent);
//					xml = CoreMassageResp.getNewsMessageXml(requestMap, articleList);
				} else if (eventKey.equals("13")) {
					respContent = "监考须知菜单项被点击！";
					xml = CoreMassageResp.getTextMessageXml(requestMap, respContent);
				} else if (eventKey.equals("14")) {
					respContent = "我的监考菜单项被点击！";
					xml = CoreMassageResp.getTextMessageXml(requestMap, respContent);
				} else if (eventKey.equals("21")) {
					respContent = "天气预报菜单项被点击！";
					xml = CoreMassageResp.getTextMessageXml(requestMap, respContent);
				} else if (eventKey.equals("22")) {
					//respContent = "今日新闻菜单项被点击！";
					//xml = CoreMassageResp.getTextMessageXml(requestMap, respContent);
					//构建对应的xml
					xml = CoreMassageResp.getNewsMessageXml(requestMap,News.getNewsArticles());
				} else if (eventKey.equals("23")) {
					//历史上的今天
					//respContent = "历史上的今天菜单项被点击！";
					respContent = TodayInHistoryService.getTodayInHistoryInfo();
					xml = CoreMassageResp.getTextMessageXml(requestMap, respContent);
					
				} else if (eventKey.equals("31")) {
					//respContent = "电影排行榜菜单项被点击！";
					//respContent = TopMovie.getMovie();
					//xml = CoreMassageResp.getTextMessageXml(requestMap, respContent);
					xml = CoreMassageResp.getNewsMessageXml(requestMap, TopMovie.getMovie());
					
				} else if (eventKey.equals("32")) {
					respContent = Humor.getHumor();
					xml = CoreMassageResp.getTextMessageXml(requestMap, respContent);
				} else if (eventKey.equals("33")) {
					respContent = "关于团队菜单项被点击！";
					xml = CoreMassageResp.getTextMessageXml(requestMap, respContent);
				}
			}
		}else{
			xml = CoreMassageResp.getTextMessageXml(requestMap, respContent);
		}
		return xml;
	}

	
}
