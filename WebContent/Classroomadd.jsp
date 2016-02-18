<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	欢迎您：<%=request.getSession().getAttribute("id")%>
	<br>
	<br>
	<form action="ClassRoom_add.action" method="post">
		<table>
			<tr>
				<td>教室名称：<input type="text" name="Classname"></td>
				<td>教室座位数：<input type="text" name="Classnum"></td>

			</tr>
			<input type="submit" name="sumbit">
			<tr>

			</tr>
		</table>
	</form>
</body>
</html>