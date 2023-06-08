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
		<link href="css/style.css" rel="stylesheet" type="text/css"/>
		<link href="css/starrating.css" rel="stylesheet" type="text/css"/>
		<style>
            .gallery-wrap .img-big-wrap img {
                height: 450px;
                width: auto;
                display: inline-block;
                cursor: zoom-in;
            }


            .gallery-wrap .img-small-wrap .item-gallery {
                width: 60px;
                height: 60px;
                border: 1px solid #ddd;
                margin: 7px 2px;
                display: inline-block;
                overflow: hidden;
            }

            .gallery-wrap .img-small-wrap {
                text-align: center;
            }
            .gallery-wrap .img-small-wrap img {
                max-width: 100%;
                max-height: 100%;
                object-fit: cover;
                border-radius: 4px;
                cursor: zoom-in;
            }
            .img-big-wrap img{
                width: 100% !important;
                height: auto !important;
            }
        </style>
	</head>
	<body>
		<jsp:include page="header.jsp"></jsp:include>
		<div class="container">
			<div class="row">
				<div class="col-sm-3">
					<div class="card bg-light mb-3">
						<div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i>Categories</div>
						
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
					<div class="container">
						<div class="card">
							<div class="row">
								<aside class="col-sm-5 border-right">
									<article class="gallery-wrap">
										<div class="img-big-wrap">
											<div><a href="#"><img src="data:image/*;base64,${detail.photo}"></div>
										</div><!-- slide product -->
										<div class="img-small-wrap">
                                        </div>
									</article>
								</aside>
								<aside class="col-sm-7">
									<article class="card-body p-5">
										<h3 class="title mb-3">${detail.title}</h3>
										<p class="price-detail-wrap">
											<span class="price h3 text-warning"> 
                                                <span class="currency">US $</span><span class="num">${detail.price}</span>
                                            </span> 
										</p>
										<dl class="item-property">
											<dt>Author: ${detail.author}</dt>
										</dl>
										<dl class="item-property">
											<dt>Day of release: ${detail.dor}</dt>
										</dl>
										<dl class="item-property">
											<dt>Publisher: ${detail.publisher}</dt>
										</dl>
										<dl class="item-property">
											<dt>Number of page: ${detail.page}</dt>
										</dl>
										<dl class="item-property">
											<dt>Description</dt>
											<dd><p>${detail.description}</p></dd>
										</dl>
										
										
										<hr>
										<a href="order-now?quantity=1&bid=${detail.book_id}" class="btn btn-lg btn-primary text-uppercase"> Buy now </a>
                                        <a href="add-to-cart?bid=${detail.book_id}" class="btn btn-lg btn-outline-primary text-uppercase"> <i class="fas fa-shopping-cart"></i> Add to cart </a>
                                        <hr>
                                        <dt>Comment and Rating:</dt>
                                        <form action="rate" method="post">
                                        	<span class="star-rating star-5">
                                        		<input type="radio" name="rating" value="1"><i></i>
      											<input type="radio" name="rating" value="2"><i></i>
      											<input type="radio" name="rating" value="3"><i></i>
      											<input type="radio" name="rating" value="4"><i></i>
      											<input type="radio" name="rating" value="5"><i></i>
                                        	</span>
                                        	<br>
                                        	<textarea id="content" name="content" rows="5" cols="50" class="form-control input-group-lg reg_name"></textarea>
                                        	<br>
                                        	<button type="submit" class="btn btn-success">Comment</button>
                                        	<input type="hidden" id="user_id" name="user_id" value="<c:out value="${sessionScope.acc.id}" />">
                                        	<input type="hidden" id="b_id" name="b_id" value="<c:out value="${detail.book_id}" />">
                                        </form>
									</article>
								</aside>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<!-- footer -->
        <jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>

