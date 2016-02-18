<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta charset="UTF-8">
<title>沈阳航空航天大学考务系统</title>
<link rel="stylesheet" type="text/css" href="css/index.css">
<script src="js/index.js"></script>
<style type="text/css">
a:link {
	text-decoration: none;
}

a:hover {
	text-decoration: none;
}

a:visited {
	text-decoration: none;
}
</style>
</head>

<body>
	<div class="header"
		style="width: 1152px; height: 100px; margin: 10px auto">
		<img src="imgs/logo.gif" class="logo">
		<h1>考 务 系 统</h1>
		<h4
			style="float: right; color: #ffffff; position: relative; top: 50px; right: 2em; cursor: default;">导航页</h4>
	</div>
	<hr width="1152px" color="#ffffff">
	<hr width="1152px" color="#ffffff">
	<div class="container">
		<div class="content_left">
			<div class="nav_1" onclick="show_room()">
				<p class="left_font_style_1">
					<a href="Classinfo.jsp" />教 室管理
				</p>
				<img src="imgs/room.png" class="icon_room">
			</div>
			<div class="nav_2">
			<!-- 暂时先放在这 -->
				<a href="WeChat_CreateMenu.action" />微信添加菜单</a><br>
				<a href="WeChat_DeleteMenu.action" />微信删除菜单</a><br>
			</div>
			<div class="nav_3"></div>
			<div class="nav_4" id="left_nav_4">
				<p class="left_font_style_1">
					<a href="Gradeinfo.jsp" />年纪管理
				</p>
				<img src="imgs/class.png" class="icon_class">
			</div>
			<div class="nav_5">
				<p class="left_font_style_2">
					<a href="index.jsp" />考 试
				</p>
				<img src="imgs/test.png" class="icon_test">
			</div>
			<div class="nav_6">
				<p class="left_font_style_3">
					<a href="Signinfo.jsp" />最近访问
				</p>
			</div>
			<div class="nav_7"></div>
		</div>
		<div class="content_right">
			<div class="nav_1">
				<div id="right-1" style="display: block; width: 100%; height: 100%;"
					onclick="window.open('list1.jsp','_self')">
					<p class="right_font_style_1">班级列表</p>
					<img src="imgs/list.png" class="icon_list">
				</div>
			</div>
			<div class="nav_2">
				<div id="right-2" style="display: block; width: 100%; height: 100%;"
					onclick="window.open('Greate_add.jsp','_self')">
					<p class="right_font_style_2">添加班级</p>
					<img src="imgs/SQUARE_ADD.png" class="icon_add">
				</div>
			</div>
			<div class="nav_3">
				<div id="right-3" style="display: block; width: 100%; height: 100%"
					onclick="window.open('ClassRoom_add.jsp','_self')">
					<p class="right_font_style_2">添加教室</p>
					<img src="imgs/SQUARE_ADD.png" class="icon_add">
				</div>
			</div>
			<div class="nav_4">
				<div id="right-4" style="display: block; width: 100%; height: 100%"
					onclick="window.open('list2.jsp','_self')">
					<p class="right_font_style_1">教室列表</p>
					<img src="imgs/list.png" class="icon_list">
				</div>
				<div id="right-4-test"
					style="display: block; width: 100%; height: 100%;"
					onclick="wondow.open('Delete.action','_self')">
					<p class="right_font_style_3">删除考试</p>
					<img src="imgs/BIN.png" class="icon_bin">
				</div>
			</div>
			<div class="nav_5">
				<div id="right-5-test" style="width: 100%; height: 100%"
					onclick="window.open('CreateNewTest.action','_self')">
					<p class="right_font_style_4">创建新的考试</p>
					<img src="imgs/NOTEPAD_ADD.png" class="icon_add_test">
				</div>
			</div>
		</div>
	</div>
	<footer>
	<div class="footer">
		<div style="width: 1152px; height: 100%; margin: auto">
			<div class="navbar_left">
				<div class="left">
					<ul>
						<li><img src="imgs/Home.png" class="home"> <a
							href="http://www.sau.edu.cn/"><span class="text">学校主页</span></a>
						</li>
						<li><img src="imgs/library.png" class="library"> <a
							href="http://59.73.148.12:8080/reader/login.php"><span
								class="text">图书馆</span></a></li>
						<li><img src="imgs/email.png" class="email"> <a
							href="http://mail.sau.edu.cn/"><span class="text">沈航邮箱</span></a>
						</li>
						<li><img src="imgs/Internet.png" class="internet"> <a
							href="http://nicserver.sau.edu.cn/login.jsp"><span
								class="text">网络自助平台</span></a></li>
					</ul>
				</div>
				<div class="right">
					<ul>
						<li><img src="imgs/teach.png" class="teach"> <a
							href="http://jxgl.sau.edu.cn/default2.aspx"><span
								class="text">教学管理</span></a></li>
						<li><img src="imgs/technology.png" class="technology">
							<a href="http://i.sau.edu.cn/"><span class="text">数字校园</span></a>
						</li>
					</ul>
				</div>
			</div>
			<div class="navbar_right">
				<p style="font-weight: bold; cursor: default;">如果页面内容不能正常显示，您应该升级您的浏览器，我们建议您使用下列浏览器</p>
				<ul class="explorer">
					<li><img src="imgs/IE.png">
						<p>
							<a
								href="http://windows.microsoft.com/zh-cn/internet-explorer/download-ie"
								style="color: darkgray">IE9.0以上</a>
						</p></li>
					<li><img src="imgs/Firefox.png">
						<p>
							<a href="http://www.firefox.com.cn/" style="color: darkgray">Firefox</a>
						</p></li>
					<li><img src="imgs/Chrome.png">
						<p>
							<a
								href="http://www.google.cn/intl/zh-CN/chrome/browser/desktop/index.html"
								style="color: darkgray">Chrome</a>
						</p></li>
					<li><img src="imgs/safari.png">
						<p>
							<a href="http://www.apple.com/cn/safari/" style="color: darkgray">Safari</a>
						</p></li>
				</ul>
			</div>
		</div>
	</div>
	</footer>
</body>
</html>
