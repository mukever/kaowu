package com.CoreServer.wechat.kaowu.sau.www;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.MessageUtil.wechat.kaowu.sau.www.MessageUtil;
import com.resp.message.wechat.kaowu.sau.www.Article;
import com.resp.message.wechat.kaowu.sau.www.BaseMessage;
import com.resp.message.wechat.kaowu.sau.www.MusicMessage;
import com.resp.message.wechat.kaowu.sau.www.NewsMessage;
import com.resp.message.wechat.kaowu.sau.www.TextMessage;


public class CoreMassageResp  {
	
	
	MusicMessage musicMessage;
	NewsMessage newsMessage;
	TextMessage textMessage;
	
	
	/**
	 * 音乐消息
	 * @param requestMap
	 * @return
	 */
	public static String getMusicMessageXml(Map<String, String>requestMap) {
		String respMessage="";
		MusicMessage musicMessage = new MusicMessage();
		SetType(musicMessage, requestMap,MessageUtil.RESP_MESSAGE_TYPE_MUSIC);
		respMessage= MessageUtil.musicMessageToXml(musicMessage);
		return respMessage;
	}
	
	/**
	 * 图文消息    根据用户的输入返回相应的图文信息
	 * @param requestMap
	 * @return
	 */
	public  static String getNewsMessageXml(Map<String, String>requestMap ,List<Article> articleList ) {
		String respMessage="";
		// 创建图文消息
		NewsMessage newsMessage = new NewsMessage();
		SetType(newsMessage, requestMap,MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		// 图文消息   
        // 设置图文消息个数  
        newsMessage.setArticleCount(articleList.size());  
        // 设置图文消息包含的图文集合  
        newsMessage.setArticles(articleList);  
        // 将图文消息对象转换成xml字符串  
        respMessage = MessageUtil.newsMessageToXml(newsMessage);  
  
		return respMessage;
	}
	
	/**
	 * 文本消息
	 * @param requestMap
	 * @return
	 */
	public static String getTextMessageXml(Map<String, String>requestMap,String respContent) {
		String respMessage="";
		// 设置回复文本消息
		TextMessage textMessage = new TextMessage();
		SetType(textMessage, requestMap,MessageUtil.REQ_MESSAGE_TYPE_TEXT);
		//设置消息内容
		textMessage.setContent(respContent);
		
		respMessage = MessageUtil.textMessageToXml(textMessage);
		
		return respMessage;
	}
	/**
	 * 设置消息类型
	 * @param massage
	 * @param requestMap
	 */
	  private static void SetType(BaseMessage massage,Map<String, String>requestMap,String Type) {
		  
		//将消息发送回去需要将发送方与接收方调换
		massage.setToUserName(requestMap.get("FromUserName"));
		massage.setFromUserName(requestMap.get("ToUserName"));
		//设置消息的时间
		massage.setCreateTime(new Date().getTime());
		//设置消息的类型
		massage.setMsgType(Type);
		//不为标记消息
		massage.setFuncFlag(0);
	}
	
}
