<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>班级信息更新</title>
</head>
<body>
	<form id="form" method="POST" action="ClassRoom_query.action">
		<td>
		<tr>
			教室编号
			<input type="text" name="Classname"
				value="<%=request.getSession().getAttribute("classname")%>" />
		</tr>
		<tr>
			教室人数
			<input type="text" name="Classnum"
				value="<%=request.getSession().getAttribute("classnum")%>" />
		</tr>
		</td>
		<td><input type="submit" value="确认修改" id="submit_btn" /></td>
		<% request.getSession().removeAttribute("classname");
                   request.getSession().removeAttribute("classnum");
                
                %>
	</form>
	<a href="Classinfo.jsp">取消修改</a>
</body>
</html>