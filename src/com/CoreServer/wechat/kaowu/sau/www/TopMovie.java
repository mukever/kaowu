package com.CoreServer.wechat.kaowu.sau.www;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.resp.message.wechat.kaowu.sau.www.Article;


/**
 * 电影
 * @author Administrator
 *
 */
public class TopMovie extends HttpRequest {
	
	/**
	 * 从html中抽取电影
	 * 
	 * @param html
	 * @return
	 */
	private static ArrayList<Article> extract(String html) {
		ArrayList<Article> articleList = new ArrayList<>();
		StringBuffer buffer = new StringBuffer();
		String moive_url = "http://www.1905.com/search/index-p-type-all-q-MOIVE.html";
		//详情标题
		String[] details = {"本周名次","上周名次","片名","本周票房(万元)","累计票房(万元)","上映天数"};
		//正则提取详情
		Pattern p = Pattern.compile("(<table width=\"600\" cellspacing=\"1\" cellpadding=\"1\" border=\"1\" class=\"rtecenter\">)(.*?)(</table>)");
		//得到正则对象
		Matcher m = p.matcher(html);
		//标记是否为第一行
		boolean falg = true;
		//提取日期
		Pattern date = Pattern.compile("(<h3>)(.*?)(</h3>)");
		Matcher m_date = date.matcher(html);
		Pattern detail_url = Pattern.compile("《<a href=\"([^>]*)>(.*?)》");
		String url = "";
		if(m_date.find()){
			String title = m_date.group(2);
			buffer.append(title).append("\n\n");
		}
		if (m.find()) {	
		 //得到table
		 int i=1;
		 
		 for(String info : m.group(2).split("</tr>")){
			 //一共为10个电影
			 String moiveinfo = "";
			 //得到每一行
			 //第一行
			 if(falg){
				 falg = false;
			     continue;
			 }else {
				 String movie = "";
				 Article article = new Article();
				 //前3个数据
				 if(i<4){
					 int j=0;
					 for(String li :info.split("</td>")){
						 
						 String td = li.replaceAll("<[^>]*>", "");
						 
						 if(j==0){
							 
							 moiveinfo+="本周排名："+i+"\n";
							 j++;
						 }else{
							 if(j>=6)break;
							 if(j==2){
							
								 Matcher detail = detail_url.matcher(li);
								 //System.out.println(detail.find());
								 if(detail.find()){
									 movie = detail.group(2);
									 movie = movie.substring(0, movie.length()-4);
									 url = moive_url.replace("MOIVE", movie);
									 article.setTitle(movie);
									 article.setUrl(url);
									 String pic_url = getpicUrl(detail.group(1).replace("\"", ""));
									 article.setPicUrl(pic_url);
									 
								 }
								 
								 movie = td.replaceAll("[a-zA-Z]", "").replaceAll(" ", "").replace(":", "");
								 
							 }
							 movie = td.replaceAll("[a-zA-Z]", "").replaceAll(" ", "");
							 moiveinfo+=details[j++]+":"+movie+"\n";
							 
						 }
						 
					 }
					 i++;
					 articleList.add(article);
					 buffer.append(moiveinfo).append("\n");
					
				 }
				 else if(i<11){
					 //后面会多一列数据
					
					 int j=0;
					 for(String li :info.split("</td>")){
						
						 //得到每一列
						 //只抽取有用的数据
						 if(j>=6)break;
						 String td = li.replaceAll("<[^>]*>", "");
						 if(j==2){
							 Matcher detail = detail_url.matcher(li);
							 //System.out.println(detail.find());
							 if(detail.find()){
								 movie = detail.group(2);
								 movie = movie.substring(0, movie.length()-4);
								 url = moive_url.replace("MOIVE", movie);
								 article.setTitle(movie);
								 article.setUrl(url);
								 String pic_url = getpicUrl(detail.group(1).replace("\"", ""));
								 article.setPicUrl(pic_url);
							 }
							 
							 movie = td.replaceAll("[a-zA-Z]", "").replaceAll(" ", "");
							 
						  }
						  movie = td.replaceAll("[a-zA-Z]", "").replaceAll(" ", "");
						  moiveinfo+=details[j++]+":"+movie+"\n";
					 }
					 i++;
					 articleList.add(article);
					 //System.out.println(moiveinfo);
					 buffer.append(moiveinfo).append("\n");
				 }
				 moiveinfo="";
			}
		
			 
		 }
			
		}
	   
		return articleList;
	}

	
	/**
	 * 封装查询方法，供外部调用
	 * 
	 * @return
	 */
	public static ArrayList<Article> getMovie() {
		String url = getUrl();
		// 获取网页源代码
		String html = httpGetRequest(url);
		// 从网页中抽取信息
		ArrayList<Article> articleList = extract(html);
		String string = articleList.get(0).getTitle();
		string = emoji(0x1F3C6)+string;
		articleList.get(0).setTitle(string);
		return articleList;
	}
	
	private static String getUrl() {
		String url = "";
		
		String html = httpGetRequest("http://www.mask9.com/taxonomy/term/903928");
		
		//正则提取详情
		Pattern p = Pattern.compile("(<a href=\"(.*?)\">中国内地电影票房排行榜 (.*?))(</a>)  ");
		//得到正则对象
		Matcher m = p.matcher(html);
		if(m.find()){
			String end_url = m.group(2);
			url = "http://www.mask9.com"+end_url.substring(end_url.length()-12, end_url.length());
			
		}
	
		return url;
	}
	
	private static String getpicUrl(String url) {
		String pic_url = "";
		String html = httpGetRequest(url);
		Pattern pattern = Pattern.compile("<p class=\"rtecenter\">(.*?)</p>");
		Pattern pattern_src = Pattern.compile("src=\"(.*?)\"");
		Matcher matcher = pattern.matcher(html);
		if(matcher.find()){
			String result = matcher.group(1);
			Matcher matcher_src = pattern_src.matcher(result);
			if(matcher_src.find()){
				 pic_url = matcher_src.group(1); 
			}
			
		}
		 return pic_url;
		
	}
	
	public static String emoji(int hexEmoji) {  
        return String.valueOf(Character.toChars(hexEmoji));  
    } 


}