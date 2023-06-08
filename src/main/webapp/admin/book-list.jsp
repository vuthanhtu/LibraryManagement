<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
 href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
 integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
 crossorigin="anonymous">
 <style>
 	td{
 	text-align:center;
 	}
 	th{
 	text-align:center;
 	}
 </style>
</head>
<body>
	<header>
 	 <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
  	  <div>
   		<a class="navbar-brand">Library Online Management</a>
  	  </div>
      <ul class="navbar-nav navbar-collapse justify-content-end">
       <li><a href="<%= request.getContextPath() %>/admin" class="nav-link">Back</a></li>
      </ul>
     </nav>
	</header>
	<div class="row">
		<div class="container">
			<h3 class="text-center">Book Management</h3>
			<hr>
			<div class="container text-left">
				<a href="<%=request.getContextPath()%>/admin/book/new" class="btn btn-success">Add Book</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Book ID</th>
						<th>Title</th>
						<th>Author</th>
						<th>Day of Release</th>
						<th>Publisher</th>
						<th>Page</th>
						<th>Price</th>
						<th>Amount</th>
						<th>Category</th>
						<th>Action</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach var="book" items="${listBook}">
						<tr>
							<td><c:out value="${book.book_id}" /></td>
							<td><c:out value="${book.title}" /></td>
							<td><c:out value="${book.author}" /></td>
							<td><c:out value="${book.dor}" /></td>
							<td><c:out value="${book.publisher}" /></td>
							<td><c:out value="${book.page}" /></td>
							<td><c:out value="${book.price}" /></td>
							<td><c:out value="${book.amount}" /></td>
							<td><c:out value="${book.category.name}" /></td>
							<td style="display:flex"><a href="book/edit?book_id=<c:out value='${book.book_id}' />" class="btn btn-success">View</a>
							&nbsp;<a href="bookdel.jsp?id=${book.book_id}" class="btn btn-danger" onclick="return confirm('Are you sure to delete this book')" class="btn btn-danger">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>