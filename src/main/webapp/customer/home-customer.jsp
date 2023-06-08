<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
		<style>
		.bloc_left_price {
    color: #c01508;
    text-align: center;
    font-weight: bold;
    font-size: 150%;
}
.category_block li:hover {
    background-color: #007bff;
}
.category_block li:hover a {
    color: #ffffff;
}
.category_block li a {
    color: #343a40;
}
.add_to_cart_block .price {
    color: #c01508;
    text-align: center;
    font-weight: bold;
    font-size: 200%;
    margin-bottom: 0;
}
.add_to_cart_block .price_discounted {
    color: #343a40;
    text-align: center;
    text-decoration: line-through;
    font-size: 140%;
}
.product_rassurance {
    padding: 10px;
    margin-top: 15px;
    background: #ffffff;
    border: 1px solid #6c757d;
    color: #6c757d;
}
.product_rassurance .list-inline {
    margin-bottom: 0;
    text-transform: uppercase;
    text-align: center;
}
.product_rassurance .list-inline li:hover {
    color: #343a40;
}
.reviews_product .fa-star {
    color: gold;
}
.pagination {
    margin-top: 20px;
}
footer {
    background: #343a40;
    padding: 40px;
    margin-top: 20px;
}
footer a {
    color: #f8f9fa!important
}
.bgc{
    background-image: url(image/Clothes+and+shoes-74_banner.jpg);
    /*background-image: url("https://envato-shoebox-0.imgix.net/a553/ba21-ce80-45ee-82d4-120907cdb414/Clothes+and+shoes-74_banner.jpg?auto=compress%2Cformat&fit=max&mark=https%3A%2F%2Felements-assets.envato.com%2Fstatic%2Fwatermark2.png&markalign=center%2Cmiddle&markalpha=18&w=1600&s=a9cc1545e602fe3d3e6c9faed39f0a84");*/
}
.show_txt{
    display: inline-block;
    width: 100%;
    white-space: nowrap;
    overflow: hidden !important;
    text-overflow: ellipsis;
}
a .active{
    color: white;
}
		</style>
	</head>
	<body>
		<jsp:include page="header.jsp"></jsp:include>
        <div class="container">
        	<div class="row">
        		<div class="col">
        			<nav aria-label="breadcrumb">
        				<ol class="breadcrumb">
        					<li class="breadcrumb-item"><a href="#">Home</a></li>
                            <li class="breadcrumb-item"><a href="#">Category</a></li>
                            <li class="breadcrumb-item active" aria-current="#">Sub-category</li>
        				</ol>
        			</nav>
        		</div>
        	</div>
        </div>
        <div class="container">
        	<div class="row">
        		<div class="col-sm-3">
        			<div class="card bg-light mb-3">
        				<div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
        				<ul class="list-group category_block">
        					<c:forEach items="${listCategory}" var="category">
                                <li class="list-group-item text-white ${tag==category.cate_id ? "active":""}"><a href="category?cate_id=${category.cate_id}">${category.name}</a></li>
                            </c:forEach>
        				</ul>
        			</div>
        			<div class="card bg-light mb-3">
        				<div class="card-header bg-success text-white text-uppercase">Last book</div>
        				<div class="card-body">
        					<img class="img-fluid" src="data:image/*;base64,${p.photo}">
        					<h5 class="card-title">${p.title}</h5>
                            <p class="card-text">${p.author}</p>
                            <p class="bloc_left_price">${p.price}$</p>
        				</div>
        			</div>
        		</div>
        		<div class="col-sm-9">
        			<div class="row">
        				<c:forEach items="${listBook}" var="book">
        					<div class="col-12 col-md-6 col-lg-4">
        						<div class="card h-100">
        							<img src="data:image/*;base64,${book.photo}" class="card-img-top" alt="Card image cap">
        							<div class="card-body">
        								<h4 class="card-title show_txt"><a href="detail?bid=${book.book_id}" title="View Product">${book.title}</a></h4>
        								<p class="card-text show_txt">${book.author}</p>
        								<div class="row">
                                            <div class="col">
                                                <p class="btn btn-danger btn-block">${book.price}$</p>
                                            </div>
                                            <div class="col">
                                                <a href="add-to-cart?bid=${book.book_id}" class="btn btn-success btn-block">Add to cart</a>
                                            </div>
                                        </div>
        							</div>
        						</div>
        					</div>
        				</c:forEach>
        			</div>
        		</div>
        	</div>
        </div>
        
        <jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>