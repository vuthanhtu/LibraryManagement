<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>
<script src="vendor/jquery/jquery.min.js"></script>
<script src="js/main.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet" href="alert/dist/sweetalert.css">
<style>
	.gradient-custom-3 {
    background: #84fab0;
    background: -webkit-linear-gradient(to right, rgba(132, 250, 176, 0.5), rgba(143, 211, 244, 0.5));
    background: linear-gradient(to right, rgba(132, 250, 176, 0.5), rgba(143, 211, 244, 0.5))
    }
    .gradient-custom-4 {
    background: #84fab0;
    background: -webkit-linear-gradient(to right, rgba(132, 250, 176, 1), rgba(143, 211, 244, 1));
    background: linear-gradient(to right, rgba(132, 250, 176, 1), rgba(143, 211, 244, 1))
    }
</style>
</head>
<body>
	<section class="vh-100 bg-image"
  style="background-image: url('https://mdbcdn.b-cdn.net/img/Photos/new-templates/search-box/img4.webp');">
  <div class="mask d-flex align-items-center h-100 gradient-custom-3">
    <div class="container h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-12 col-md-9 col-lg-7 col-xl-6">
          <div class="card" style="border-radius: 15px;">
            <div class="card-body p-5">
              <h2 class="text-uppercase text-center mb-5">Đăng nhập</h2>
			  <input type="hidden" id="status" value="<%=request.getAttribute("status")%>">
              <form action="<%=request.getContextPath() %>/login" method="post">
                <div class="form-outline mb-4">
                    <label class="form-label required" for="form3Example1cg">Tài khoản</label>
                    <input type="text" id="username" name=username class="form-control form-control-lg" />
                </div>
                
                <div class="form-outline mb-4">
                    <label class="form-label required" for="form3Example4cg">Mật khẩu</label>
                    <input type="password" id="password" name="password" class="form-control form-control-lg" />  
                </div>
                      
                <div class="d-flex justify-content-center">
                  <button type="submit"
                    class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">Đăng nhập</button>
                </div>

                <p class="text-center text-muted mt-5 mb-0">Bạn chưa có tài khoản? <a href="<%= request.getContextPath() %>/register" class="fw-bold text-body"><u>Đăng ký</u></a></p>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
<script type="text/javascript">
	var status = document.getElementById("status").value;
	if(status == "failed"){
		swal("Sorry","Wrong UserName or Password","error");
	}
	else if(status == "invalidUsername"){
		swal("Sorry","Please Enter Username","error");
	}
	else if(status == "invalidPassword"){
		swal("Sorry","Please Enter Password","error");
	}
</script>
</body>
</html>