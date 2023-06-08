<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="bookstoreonline.model.Cart" %>
<%@page import="bookstoreonline.dao.CartDAO" %>
<%
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
List<Cart> cartProduct = null;
if(cart_list!=null){
	CartDAO cartDAO = new CartDAO();
	cartProduct = cartDAO.getCartProduct(cart_list);
	float total = cartDAO.getTotalCartPrice(cart_list);
	request.setAttribute("cart_list",cart_list);
	request.setAttribute("total",total);
	
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
		<div class="d-flex py-3"><h3>Total Price:${total}$</h3><a href="cart-check-out" class="mx-3 btn btn-primary">Check Out</a></div>
		<table class="table table-light">
			<thead>
			<tr>
				<th scope="col">Title</th>
				<th scope="col">Author</th>
				<th scope="col">Price</th>
				<th scope="col">Buy Now</th>
				<th scope="col">Cancel</th>
			<tr>
			</thead>
			<tbody>
				<%
				if (cart_list != null) {
					for (Cart c : cartProduct) {
				%>
				<tr>
					<td><a href="detail?bid=<%=c.getBook_id()%>"><%=c.getTitle()%></a></td>
					<td><%=c.getAuthor()%></td>
					<td><%=c.getPrice()%>$</td>
					<td>
						<form action="order-now" method="post" class="form-inline">
							<input type="hidden" name="bid" value="<%=c.getBook_id()%>" class="form-input">
							<div class="form-group d-flex justify-content-between w-50">
								<a class="btn bnt-sm btn-decre" href="quantity-inc-dec?action=dec&bid=<%=c.getBook_id()%>"><i class="fas fa-minus-square"></i></a>
								<input type="text" name="quantity" class="form-control w-50"  value="<%=c.getQuantity() %>" readonly>
								<a class="btn btn-sm btn-incre" href="quantity-inc-dec?action=inc&bid=<%=c.getBook_id()%>"><i class="fas fa-plus-square"></i></a>
							</div>
							<button type="submit" class="btn btn-primary btn-sm">Buy Now</button>
						</form>
					</td>
					<td><a href="remove-from-cart?bid=<%=c.getBook_id()%>" class="btn btn-sm btn-danger">Remove</a></td>
				</tr>
				<%
				}}%>
			</tbody>
		</table>
	</div>
</body>
</html>

<style type="text/css">
.table tbody td{
vertical-align: middle;
}
.btn-incre, .btn-decre{
box-shadow: none;
font-size: 25px;
}
</style>