package com.CoreServer.wechat.kaowu.sau.www;


import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 幽默
 * @author Administrator
 *
 */
public class Humor extends HttpRequest {
	
	/**
	 * 从html中抽取笑话
	 * 
	 * @param html
	 * @return
	 */
	private static String extract(String html) {
		
		List<String> hu = new LinkedList<>();
		Pattern p = Pattern.compile("(?<=<div class=\"content\">)(.*?)(?=</div>)");
		Matcher m = p.matcher(html);
		while (m.find()) {
			String re = m.group(1).replaceAll("</br>", "\n").replace("<br/>", "\n").trim();
			hu.add((String) re.subSequence(0, re.length()-17));
				 
		}
			Random random = new Random();
			int k ;
			do{
				k = random.nextInt(20);
			}while(hu.get(k).length()<30);
			return hu.get(k);
	}

	/**
	 * 封装查询方法，供外部调用
	 * 
	 * @return
	 */
	public static String getHumor() {
		// 获取网页源代码
		String html = httpGetRequest("http://www.qiushibaike.com/");
		// 从网页中抽取信息
		String result = extract(html);
		return result;
	}

}
