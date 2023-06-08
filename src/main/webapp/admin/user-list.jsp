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
			<h3 class="text-center">User Management</h3>
			<hr>
			<div class="container text-left">
				<c:if test="${sessionScope.acc!=null}">
				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add Book</a>
				</c:if>
			</div>
			<br>
			<table class="table table-responsive table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Username</th>
						<th>Password</th>
						<th>Name</th>
						<th>Date of birth</th>
						<th>Email</th>
						<th>Phone</th>
						<th>Action</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach var="user" items="${listUser}">
						<tr>
							<td><c:out value="${user.id}" /></td>
							<td><c:out value="${user.username}" /></td>
							<td><c:out value="${user.password}" /></td>
							<td><c:out value="${user.name}" /></td>
							<td><c:out value="${user.dob}" /></td>
							<td><c:out value="${user.email}" /></td>
							<td><c:out value="${user.dt}" /></td>
							<td style="display:flex"><a class="btn btn-success">View</a>&nbsp;<a class="btn btn-danger">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>