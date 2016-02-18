<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta charset="utf-8">
<title>登录</title>
<script src="js/jquery-2.1.4.min.js"></script>
<script src="css/jquery-ui.min.css"></script>
<script src="js/login.js"></script>
<link type="text/css" href="css/jquery-ui.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
	<div id="body"
		style="position: absolute; width: 100%; height: 100%; z-index: -1">
		<div class="img">
			<img src="bg.jpg" width="100%" height="100%" />
		</div>
	</div>
	<div style="width: 400px; height: 450px; margin: auto;">
		<div id="bg"></div>
		<p>Member Login</p>

		<div id="bg1">
			<form id="form1" method="POST" action="Login.action" name="form1">
				<input type="text" name="Username" placeholder="手机号/用户名"
					onfocus="this.placeholder=''" onblur="this.placeholder='手机号/用户名'"
					id="box1" /> <input type="password" name="Password"
					placeholder="密 码" onfocus="this.placeholder=''"
					onblur="this.placeholder='密 码'" id="box2" /> <input type="submit"
					value="登  录" id="submit_btn" />
			</form>
		</div>
	</div>
	<div id="footing"></div>

</body>


</html>