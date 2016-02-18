package com.manager.wechat.kaowu.sau.www;

import java.util.HashMap;
import java.util.Map;

import com.CoreServer.wechat.kaowu.sau.www.HttpRequest;
import com.CoreServer.wechat.kaowu.sau.www.UserInfo;
import com.dao.kaowu.sau.www.OpenidDAO;
import com.pojo.wechat.kaowu.sau.www.AccessToken;
import com.units.wechat.kaowu.sau.edu.www.GetExistAccessToken;
import com.units.wechat.kaowu.sau.edu.www.WeixinUtil;
import net.sf.json.JSONObject;

public class UserInfoManager {
	
	//HttpSession session = ServletActionContext.getRequest().getSession();
	public static String getOpenid(Map<String, String>requestMap) {
		String openid = null ;
		openid = requestMap.get("FromUserName");
		return openid;
		
	}
	public static UserInfo  getInfo(String open_id) {
		Map<Integer, String> sexmap = new HashMap<Integer, String>();
		
		UserInfo userinfo  = new UserInfo();
		
		// 调用接口获取access_token
		
		AccessToken at = GetExistAccessToken.getToken();
		if (null != at) {
		
			sexmap_init(sexmap);
			// 调用接口
			JSONObject jsonObject = WeixinUtil.getUserInfo(at.getToken(), open_id);
            //jsonObject   数据示例
			//{"country":"中国","province":"辽宁","subscribe":1,"city":"沈阳",
			//"openid":"onld5wGXOEDsjrX3TwJQf-usQ9Yg","sex":1,"groupid":0,
			//"nickname":"mukever","headimgurl":"图标的url链接",
			//"language":"zh_CN","remark":"","subscribe_time":1453737508}
			
			//拉出数据
			try{
			//用户所在国家
			String country = (String)jsonObject.get("country");
			//用户所在省份
			String province = (String)jsonObject.get("province");
			//用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
			int subscribe = (int)jsonObject.get("subscribe");
			//用户所在城市
            String city = (String)jsonObject.get("city");
            //用户的标识，对当前公众号唯一
            String openid = (String)jsonObject.get("openid");
            //用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
            String sex = sexmap.get((int)jsonObject.get("sex"));
            //用户所在的分组ID
            int groupid = (int)jsonObject.get("groupid");
            //用户的昵称
			String nickname = (String)jsonObject.get("nickname");
			//用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
            String headimgurl = (String)jsonObject.get("headimgurl");
            //用户的语言，简体中文为zh_CN
            String language = (String)jsonObject.get("language");
            //公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
            String remark = (String)jsonObject.get("remark");
            //用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
            int subscribe_time = (int)jsonObject.get("subscribe_time");
            
            
            userinfo.setCity(city);
            userinfo.setCountry(country);
            userinfo.setGroupid(groupid);
            userinfo.setHeadimgurl(headimgurl);
            userinfo.setLanguage(language);
            userinfo.setNickname(nickname);
            userinfo.setOpenid(openid);
            userinfo.setProvince(province);
            userinfo.setRemark(remark);
            userinfo.setSex(sex);
            userinfo.setSubscribe(subscribe);
            userinfo.setSubscribe_time(subscribe_time);
			}catch(Exception e){
				e.printStackTrace();
			}
            
//            userinfo+="nickname:"+nickname+"\n";
//            userinfo+="sex:"+sex+"\n";
//            userinfo+="country:"+country+"\n";
//            userinfo+="province:"+province+"\n";
//            userinfo+="subscribe:"+subscribe+"\n";
//            userinfo+="city:"+city+"\n";
//            userinfo+="openid:"+openid+"\n";
//            userinfo+="groupid:"+groupid+"\n";
//            userinfo+="language:"+language+"\n";
//            userinfo+="remark:"+remark+"\n";
//            userinfo+="subscribe_time:"+subscribe_time+"\n";
            
            
            
            
		}
		System.out.println(userinfo);
		return userinfo;
	}
	
	//定义性别
	private static void sexmap_init(Map<Integer, String> sexmap) {
		sexmap.put(0, "未知");
		sexmap.put(1, "男");
		sexmap.put(2, "女");
	}
	
	public static void addTodatabase(Map<String, String>requestMap) {
		String openid = getOpenid(requestMap);
		UserInfo user =   getInfo(openid);
		OpenidDAO.add(user);
	}
	
	public static void deletefromdatabase(Map<String, String>requestMap) {
		String openid = getOpenid(requestMap);
		
		UserInfo user = new UserInfo();
		user.setOpenid(openid);
		OpenidDAO.delete(user);
	}
	
}
