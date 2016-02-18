<%@page import="com.dao.kaowu.sau.www.GreateDAO"%>
<%@page import="com.bean.kaowu.sau.www.GrateBean"%>
<%@page import="java.util.List"%>
<%@page import="com.action.kaowu.sau.www.Greate"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>沈阳航空航天大学考务系统</title>
<link rel="stylesheet" type="text/css" href="css/index.css">
<script src="js/jquery-2.1.4.min.js"></script>
<script src="css/jquery-ui.min.css"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>年纪信息更新</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
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
<body>
	<div class="header"
		style="width: 1152px; height: 100px; margin: 10px auto">
		<img src="imgs/logo.gif" class="logo">
		<h1>考 务 系 统</h1>
	</div>
	<%=request.getSession().getAttribute("id") %>Hello
	<br>
	<form id="form" method="POST" action="Greate_updata.action">
		<td>班级编号<input type="text" name="Greatename"
			value="<%=request.getSession().getAttribute("Greatename")%>" />
		</td>
		<td>班级人数<input type="text" name="Greatenum"
			value="<%=request.getSession().getAttribute("Greatenum")%>" />
		</td> <input type="submit" value="确认修改" id="submit_btn" />
	</form>
	<a href="Gradeinfo.jsp">取消修改</a>
	<% request.getSession().removeAttribute("Greatename");
         request.getSession().removeAttribute("Greatenum");
               
       %>
</body>
</html>