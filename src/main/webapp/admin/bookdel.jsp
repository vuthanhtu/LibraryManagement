<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="bookstoreonline.dao.BookDAO"%>
<!DOCTYPE html>
<%
	String id = request.getParameter("id");
	String errmsg = "";
	if(id==null||id.isEmpty()){
		errmsg = "Invalid Parameter!!!";
	}else{
		int book_id = Integer.parseInt(id);
		BookDAO bookDAO = new BookDAO();
		if(bookDAO.exists(book_id)){
			bookDAO.deleteBook(book_id);
			response.sendRedirect("book");
		}else{
		 	errmsg = "No Book found!";
		}
	}
	pageContext.setAttribute("errmsg", errmsg);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div>
  		<h3>Delete Product</h3>
  		<h3 style='color:red'>${errmsg}</h3>
	</div>
</body>
</html>