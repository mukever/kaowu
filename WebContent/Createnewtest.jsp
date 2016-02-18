<%@page import="com.dao.kaowu.sau.www.SignDAO"%>
<%@page import="com.action.kaowu.sau.www.Sign"%>
<%@page import="com.bean.kaowu.sau.www.SignBean"%>
<%@page import="com.dao.kaowu.sau.www.ClassDAO"%>
<%@page import="com.bean.kaowu.sau.www.ClassRoomBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>


	<form name="teacher" method="post" action="*****">
		<h4>教师列表</h4>
		<% List<SignBean>  list = SignDAO.getList();
	 
	 	for(SignBean signBean:list){
	  %>
		<br> <input type="checkbox" name="teacher"
			value="<%=signBean.getUsername() %>" /><%=signBean.getUsername() %>
		<%
	 	}
	 %>
		<h4>教室信息</h4>
		<%	
		List<ClassRoomBean> list2 = ClassDAO.getList();
	//System.out.print(request.getSession().getAttribute("id")+"查看教室信息");
	if(list!= null){
	for(ClassRoomBean g:list2){%>
		<br> <input type="checkbox" name="classroom"
			value="<%=g.getClassRoomname()%>" /><%=g.getClassRoomname()%>
		<%
		}
	}
	 %>
		<br> <input type="submit" value="确定信息">
	</form>

</body>
</html>