<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Begin of menu -->
		<nav class="navbar navbar-expand-md navbar-dark bg-dark">
			<div class="container">
				<a class="navbar-brand" href="<%= request.getContextPath() %>/home">Book Store</a>
				
                <div class="collapse navbar-collapse justify-content-left" id="navbarsExampleDefault">
                	<form action="search" method="post" class="form-inline my-2 my-lg-0">
                		<div class="input-group input-group-sm">
                			<input value="${txtS}" name="txt" type="text" class="form-control input-group-lg reg_name" placeholder="Search...">
                			<div class="input-group-append">
                                <button type="submit" class="btn btn-secondary btn-number">
                                    <i class="fa fa-search"></i>
                                </button>
                            </div>
                		</div>
                		<a class="btn btn-success btn-sm ml-3" href="<%= request.getContextPath() %>/cart">
                            <i class="fa fa-shopping-cart"></i> Cart<span class="badge badge-danger">${cart_list.size()}</span>  
                        </a>
                	</form>
                	
                	<ul class="navbar-nav m-auto">
                        <c:if test="${sessionScope.acc == null }">
                        <li class="nav-item">
                            <a class="nav-link" href="#">Login</a>
                        </li>
                        </c:if>
                        <c:if test="${sessionScope.acc != null }">
                        <li class="nav-item">
                            <a class="nav-link" href="#">Hello ${sessionScope.acc.username}</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<%= request.getContextPath() %>/order">Order</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<%= request.getContextPath() %>/login">Logout</a>
                        </li>
                        </c:if>
                    </ul>
                </div>
			</div>
		</nav>
		 <section class="jumbotron text-center">
            <div class="container">
                <h1 class="jumbotron-heading">Welcome to Vu Thanh Tu Website</h1>
                <p class="lead text-muted mb-0">This is my book store website</p>
            </div>
        </section>
        <!-- end of menu -->