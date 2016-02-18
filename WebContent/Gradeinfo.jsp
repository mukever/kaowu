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
<title>Insert title here</title>
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
	<br />
	<a href="Greateadd.jsp">添加考试班级</a>
	<table width="300" border="1" cellspacing="0">
		<tr>
			<td>班级名称</td>
			<td>班级人数</td>
		</tr>

		<%	
	List<GrateBean> list = GreateDAO.getList();
	System.out.print(request.getSession().getAttribute("id")+"查看年级信息");
	if(list!= null){
	for(GrateBean g:list){%>
		<tr>
			<td><%=g.getGreatename() %></td>
			<td><%=Integer.parseInt(g.getGreatenum()) %></td>
			<td><a href="Greate_update.action?id=<%=g.getGreatename()%>" />修改
			</td>
			<td><a href="Greate_delete.action?id=<%=g.getGreatename()%>" />删除
			</td>
		</tr>
		<%}
	}
	%>

	</table>

	<a href="index.jsp">返回首页</a>
</body>
</html>