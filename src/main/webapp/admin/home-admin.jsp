<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Administrator Home Page</h1>
	<a href="<%= request.getContextPath() %>/admin/user">User Management</a>
	<a href="<%= request.getContextPath() %>/admin/book">Book Management</a>
</body>
</html>