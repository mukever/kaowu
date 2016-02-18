<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

	欢迎您：<%=request.getSession().getAttribute("id")%>>
	<br>
	<br>
	<form action="Greate_add.action" method="post">
		<table>
			<tr>
				<td>班级名称：<input type="text" name="Greatename"></td>
				<td>班级人数：<input type="text" name="Greatenum"></td>
			<tr>
				<input type="submit" name="sumbit" value="确定">
			</tr>
		</table>
	</form>

</body>
</html>