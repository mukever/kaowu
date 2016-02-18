package com.units.wechat.kaowu.sau.edu.www;


import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.CoreServer.wechat.kaowu.sau.www.HttpRequest;
import com.pojo.wechat.kaowu.sau.www.AccessToken;
import com.pojo.wechat.kaowu.sau.www.Menu;

/**
 * 公众平台通用接口工具类
 * 
 */
public class WeixinUtil extends HttpRequest{
	
	private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);
	
	// 获取access_token的接口地址（GET） 限200（次/天）
	
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	
	// 菜单创建（POST） 限100（次/天）
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	public static String menu_delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	
	public static String userinfo_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";	

	public static String message_to_all__url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";	
	
	
	
	
	

	/**
	 * 获取access_token
	 * 
	 * @param appid 凭证
	 * @param appsecret 密钥
	 * @return
	 */
	public static AccessToken getAccessToken(String appid, String appsecret) {
		AccessToken accessToken = null;
	
		String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				accessToken = null;
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return accessToken;
	}
	
	
	/**
	 * 创建菜单
	 * 
	 * @param menu 菜单实例
	 * @param accessToken 有效的access_token
	 * @return 0表示成功，其他值表示失败
	 */
	public static int createMenu(Menu menu, String accessToken) {
		int result = 0;

		// 拼装创建菜单的url
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(menu).toString();
		System.out.println(jsonMenu);
		// 调用接口创建菜单
		JSONObject jsonObject = httpsRequest(url, "POST", jsonMenu);

		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}

		return result;
	}
	
	
	/**
	 * 删除菜单
	 * 
	 * @param menu 菜单实例
	 * @param accessToken 有效的access_token
	 * @return 0表示成功，其他值表示失败
	 */
	public static int deleteMenu(Menu menu, String accessToken) {
		int result = 0;

		// 拼装创建菜单的url
		String url = menu_delete_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(menu).toString();
		// 调用接口创建菜单
		JSONObject jsonObject = httpsRequest(url, "POST", jsonMenu);

		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				log.error("删除菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}

		return result;
	}
	

	/**
	 * 
	 * @param accessToken
	 * @param open_id
	 * @return  JSON 数据包
	 */
	public static JSONObject getUserInfo(String accessToken,String open_id) {
		//int result = 0;
		// 拼装请求的url
		String url = userinfo_url.replace("ACCESS_TOKEN", accessToken).replace("OPENID", open_id);
		// 发送https请求 返回JSON数据包
		JSONObject jsonObject = httpsRequest(url, "POST");

		if (null == jsonObject) {
			System.out.println("请求用户信息失败");
			
		}
		return jsonObject;
	}
	
	
	public static int MessageToAll(String messageContent, String accessToken) {
		
		int result = 0;

		// 拼装群发的url
		String url = message_to_all__url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		//String messageCont = JSONObject.fromObject(messageContent).toString();
		// 调用接口
		JSONObject jsonObject = httpsRequest(url, "POST");

		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				log.error("群发信息失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}

		return result;
	}
	
}