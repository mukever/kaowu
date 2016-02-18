package com.action.kaowu.sau.www;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.CoreServer.wechat.kaowu.sau.www.CoreService;
import com.manager.wechat.kaowu.sau.www.MenuManager;
import com.opensymphony.xwork2.ActionSupport;

public class WeChat extends ActionSupport {
	
	private static final long serialVersionUID = 1L;

	public String Connect() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		// 获取请求和响应
		String Method = ServletActionContext.getRequest().getMethod();
		if(Method.equals("GET")){
			//调用get方法
		    get();
		 
		}
        if(Method.equals("POST")){
        	//调用post方法
        	post();
        }
        return null;
	}
	
	public String CreateMenu(){
		//创建菜单
		MenuManager.Menu();
		return null;
	}
	
	public String DeleteMenu(){
		//删除菜单
		MenuManager.deleteMenu();
		return null;
	}
	
	private String post() {
	/**
	 * 处理微信服务器发来的消息
	 */
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");

			// 调用核心业务类接收消息、处理消息
			String respMessage = CoreService.processRequest(request);
			
			// 响应消息
			PrintWriter out = response.getWriter();
			out.print(respMessage);
			out.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/*
	 * 
	 * get方法
	 */
	private String get() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        //获取请求参数
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostring = request.getParameter("echostr");
        String token = "weixin";    //你自己填写的token
        //对请求参数和自己的token进行排序，并连接排序后的结果为一个字符串
        String[] strSet = new String[]{token, timestamp, nonce};
        java.util.Arrays.sort(strSet);
        String total = "";
        for (String string : strSet) {
            total = total + string;
        }
        //SHA-1加密实例
        MessageDigest sha1;
		try {
			sha1 = MessageDigest.getInstance("SHA-1");
			sha1.update(total.getBytes());
	        byte[] codedBytes = sha1.digest();
	        String codedString = new BigInteger(1, codedBytes).toString(16);
	        //将加密后的字节数组转换成字符串。
	        if (codedString.equals(signature)) {
	        	//将加密的结果与请求参数中的signature比对，如果相同，原样返回echostr参数内容
	        
	            OutputStream os = response.getOutputStream();
	            BufferedWriter resBr = new BufferedWriter(new OutputStreamWriter(os));
	            resBr.write(echostring);
	            resBr.close();
	        }
	        
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
