<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="bookstoreonline.model.Cart" %>
<%@page import="bookstoreonline.model.User" %>
<%@page import="bookstoreonline.model.Order" %>
<%@page import="bookstoreonline.dao.CartDAO" %>
<%@page import="bookstoreonline.dao.OrderDAO" %>
<%
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
User acc = (User) request.getSession().getAttribute("acc");
List<Order> orders = null;
if(acc!=null){
	request.setAttribute("acc",acc);
	OrderDAO orderDAO = new OrderDAO();
	orders = orderDAO.userOrders(acc.getId());
}else{
	response.sendRedirect("login");
}
if(cart_list!=null){
	request.setAttribute("cart-list",cart_list);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
		<title>Insert title here</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<link href="css/style.css" rel="stylesheet" type="text/css"/>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
		<div class="card-header my-3">All Orders</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Date</th>
					<th scope="col">Title</th>
					<th scope="col">Author</th>
					<th scope="col">Quantity</th>
					<th scope="col">Price</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
				<%
				if(orders!=null){
					for(Order o:orders){%>
						<tr>
						<td><%=o.getDate() %></td>
						<td><a href="detail?bid=<%=o.getBook_id()%>"><%=o.getTitle()%></a></td>
						<td><%=o.getAuthor() %></td>
						<td><%=o.getQuantity() %></td>
						<td><%=o.getPrice() %></td>
						<td><a class="btn btn-sm btn-danger" href="cancel-order?o_id=<%=o.getOrder_id()%>">Cancel Order</a></td>
						</tr>
					<%}
				}
				%>
			</tbody>
		</table>
	</div>
</body>
</html>