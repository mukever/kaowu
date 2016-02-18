package com.manager.wechat.kaowu.sau.www;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pojo.wechat.kaowu.sau.www.AccessToken;
import com.pojo.wechat.kaowu.sau.www.Button;
import com.pojo.wechat.kaowu.sau.www.CommonButton;
import com.pojo.wechat.kaowu.sau.www.ComplexButton;
import com.pojo.wechat.kaowu.sau.www.Menu;
import com.pojo.wechat.kaowu.sau.www.ViewButton;
import com.units.wechat.kaowu.sau.edu.www.GetExistAccessToken;
import com.units.wechat.kaowu.sau.edu.www.WeixinUtil;

/**
 * 菜单管理器类
 * 
 */
public class MenuManager {

	private static Logger log =  LoggerFactory.getLogger(MenuManager.class);
	
	//HttpSession session = ServletActionContext.getRequest().getSession();
	//创建菜单
	public static void  Menu() {
	
		AccessToken at = GetExistAccessToken.getToken();

		if (null != at) {
			// 调用接口创建菜单
			int result = WeixinUtil.createMenu(getMenu(), at.getToken());
            
			// 判断菜单创建结果
			if (0 == result){
				log.info("菜单创建成功！");
				System.out.println("菜单创建成功！");
			}
				
			else{
				log.info("菜单创建失败，错误码：" + result);
				System.out.println("菜单创建失败，错误码：" + result);
			}
				
		}
	}
    //删除菜单
	public static void  deleteMenu() {
		
		AccessToken at = GetExistAccessToken.getToken();

		if (null != at) {
			// 调用接口删除菜单
			int result = WeixinUtil.deleteMenu(getMenu(), at.getToken());
            
			// 判断菜单删除结果
			if (0 == result){
				log.info("菜单删除成功！");
				System.out.println("菜单删除成功！");
			}
				
			else{
				log.info("菜单删除失败，错误码：" + result);
				System.out.println("菜单删除失败，错误码：" + result);
			}
				
		}
	}
	
	
	 
	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		//考务菜单
		CommonButton btn11 = new CommonButton();
		btn11.setName("近期监考");
		btn11.setType("click");
		btn11.setKey("11");

		CommonButton btn12 = new CommonButton();
		btn12.setName("监考次数");
		btn12.setType("click");
		btn12.setKey("12");

		CommonButton btn13 = new CommonButton();
		btn13.setName("监考须知");
		btn13.setType("click");
		btn13.setKey("13");
		CommonButton btn14 = new CommonButton();
		btn14.setName("我的监考");
		btn14.setType("click");
		btn14.setKey("14");
       
		
		ViewButton btn2 = new ViewButton();
		btn2.setName("绑定监考");
		btn2.setType("view");
		btn2.setUrl("http://kaowusau.applinzi.com/register.html");
		
        //更多
		CommonButton btn31 = new CommonButton();
		btn31.setName("电影排行榜");
		btn31.setType("click");
		btn31.setKey("31");


		CommonButton btn32 = new CommonButton();
		btn32.setName("糗事百科");
		btn32.setType("click");
		btn32.setKey("32");

		CommonButton btn33 = new CommonButton();
		btn33.setName("天气预报");
		btn33.setType("location_select");
		btn33.setKey("21");

		CommonButton btn34 = new CommonButton();
		btn34.setName("今日新闻");
		btn34.setType("click");
		btn34.setKey("22");

		CommonButton btn35 = new CommonButton();
		btn35.setName("关于团队");
		btn35.setType("click");
		btn35.setKey("33");
		
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("考务助手");
		mainBtn1.setSub_button(new CommonButton[] { btn11, btn12, btn13, btn14 });

		
		
		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("更多体验");
		mainBtn3.setSub_button(new CommonButton[] { btn31, btn32, btn33,btn34,btn35 });

		/**
		 * 
		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, btn2, mainBtn3 });

		return menu;
	}
}