<%@page import="com.dao.kaowu.sau.www.ClassDAO"%>
<%@page import="com.bean.kaowu.sau.www.ClassRoomBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>沈阳航空航天大学考务系统</title>
<link rel="stylesheet" type="text/css" href="css/index.css">
<script src="js/jquery-2.1.4.min.js"></script>
<script src="css/jquery-ui.min.css"></script>
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
	<table width="300" border="1" cellspacing="0">
		<tr>
			<td>教室编号</td>
			<td>教室座位数</td>
		</tr>
		<!-- 添加教室 -->
		<a href="Classroomadd.jsp">添加教室</a>
		<%	
	List<ClassRoomBean> list = ClassDAO.getList();
	//System.out.print(request.getSession().getAttribute("id")+"查看教室信息");
	if(list!= null){
	for(ClassRoomBean g:list){%>
		<tr>
			<td><%=g.getClassRoomname() %></td>
			<td><%=Integer.parseInt(g.getClassRoomNum()) %></td>
			<td><a
				href="ClassRoom_update.action?id=<%=g.getClassRoomname()%>">修改</a>
			</td>
			<td><a
				href="ClassRoom_delete.action?id=<%=g.getClassRoomname()%>">删除</a>
			</td>
		</tr>
		<%}
	}
	%>

	</table>

	<a href="index.jsp">返回首页</a>
</body>
</html>