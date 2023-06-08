<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Insert title here</title>
<style>
	label.required:after{
		content: '*';
		color: red;
	}
	#title_validation, #author_validation, #dor_validation{
    	display: none;
	}
	span.error {
    background-color:#FFDFDF;
    color:red;
	}
	body{
	margin-left: 8px;
	}
</style>
</head>
<body>
	<div>
		<h1 style="text-align:center;">Book</h1>
	</div>
	<hr>
	<div class="form-group">
		<c:if test="${book!=null}">
		<form action="update" method="post" onsubmit="return validationForm();" enctype="multipart/form-data" id="form1" onsubmit="event.preventDefault()">
		</c:if>
		<c:if test="${book==null}">
		<form action="insert" method="post" onsubmit="return validationForm();" enctype="multipart/form-data">
		</c:if>
			<div class="row">
				<div class="col-sm-6">
					<div class="row">
						<c:if test="${book!=null}">
							<input type="hidden" id="book_id" name="book_id" value="<c:out value="${book.book_id}" />" />
						</c:if>
						<div class="col-sm-6">
							<label class="required" for="title">Title:</label><br>
							<input type="text" class="form-control input-group-lg reg_name" id="title" name="title" value="<c:out value="${book.title}" />" disabled><br>
							<span id="title_validation" class="error"></span>
						</div>
						<div class="col-sm-6">
							<label class="required" for="author">Author:</label><br>
							<input type="text" class="form-control input-group-lg reg_name" id="author" name="author" value="<c:out value="${book.author}" />" disabled><br>
							<span id="author_validation" class="error"></span>
						</div>
					</div>
					<div class="form-group">
						<label>Description:</label><br>
						<textarea rows="5" cols="85" class="form-control input-group-lg reg_name" id="description" name="description" disabled><c:out value="${book.description}" /></textarea>
					</div>
					<div class="row">
						<div class="col-sm-6">
							<label class="required" for="dor">Day of Release:</label><br>
							<input type="date" class="form-control input-group-lg reg_name" id="dor" name="dor" value="<c:out value="${book.dor}" />" disabled>
							<span id="dor_validation" class="error"></span>
						</div>
						<div class="col-sm-6">
							<label>Page:</label><br>
							<input type="text" class="form-control input-group-lg reg_name" id="page" name="page" value="<c:out value="${book.page}" />" disabled>
						</div>
					</div>
					<div class="form-group">
						<label>Category:</label><br>
						<select name="category" id="category" class="form-select form-control input-group-lg reg_name" disabled>
							<c:if test="${book==null}">
								<option selected>Select Category</option>
							</c:if>
							<c:forEach items="${listCategory}" var="category">
								<option value="${category.name}" <c:if test="${category.cate_id eq book.categoryid}">selected="selected"</c:if>>
								${category.name}
								</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label>Publisher:</label><br>
						<input type="text" class="form-control input-group-lg reg_name" id="publisher" name="publisher" value="<c:out value="${book.publisher}" />" disabled>
					</div>
					<div class="row">
						<div class="col-sm-6">
							<label>Price:</label><br>
							<input type="text" class="form-control input-group-lg reg_name" id="price" name="price" value="<c:out value="${book.price}" />" disabled>
							
						</div>
						<div class="col-sm-6">
							<label>Amount:</label><br>
							<input type="text" class="form-control input-group-lg reg_name" id="amount" name="amount" value="<c:out value="${book.amount}" />" disabled>
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<input type="file" name="photo" id="photo" accept=".png, .gif, .jpeg, .jpg" onchange="loadFile(event)" disabled><br>
					<img id="imageResult" src="data:image/*;base64,${book.photo}" width="300" height="400" >
				</div>
				
			</div>
			<c:if test="${book==null}">
				<input type="submit" value="Add" id="button1">
			</c:if>
			<c:if test="${book!=null}">
				<input type="button" value="Edit" id="button2" onclick="updateButton()">
			</c:if>
		</form>
	</div>
	<script>
		function validationForm(){
			var valid = 1;
			var title = document.getElementById("title");
	        var title_validation = document.getElementById("title_validation");
	        var author = document.getElementById("author");
	        var author_validation = document.getElementById("author_validation");
	        var dor = document.getElementById("dor");
	        var dor_validation = document.getElementById("dor_validation");
	        if(title.value === ""){
	            valid = 0;
	            title_validation.innerHTML = "Title is Required";
	            title_validation.style.display = "block";
	            title_validation.parentNode.style.backgroundColor = "#FFDFDF";
	          } else{
	            title_validation.style.display = "none";
	            title_validation.parentNode.style.backgroundColor = "transparent";
	         }
	        
	        if(author.value === ""){
	            valid = 0;
	            author_validation.innerHTML = "Author is Required";
	            author_validation.style.display = "block";
	            author_validation.parentNode.style.backgroundColor = "#FFDFDF";
	          } else{
	            author_validation.style.display = "none";
	            author_validation.parentNode.style.backgroundColor = "transparent";
	        }
	        
	        if(dor.value === ""){
	            valid = 0;
	            dor_validation.innerHTML = "Day of Release is Required";
	            dor_validation.style.display = "block";
	            dor_validation.parentNode.style.backgroundColor = "#FFDFDF";
	          } else{
	            dor_validation.style.display = "none";
	            dor_validation.parentNode.style.backgroundColor = "transparent";
	         }
	        
	        if(!valid){
	        	return false;
	        }
		}
		
		function updateButton(){
			if(document.getElementById("button2").getAttribute("value")==="Edit"){
				document.getElementById("button2").value="Save";
				document.querySelector("#title").disabled = false;
				document.querySelector("#author").disabled = false;
				document.querySelector("#description").disabled = false;
				document.querySelector("#page").disabled = false;
				document.querySelector("#category").disabled = false;
				document.querySelector("#dor").disabled = false;
				document.querySelector("#amount").disabled = false;
				document.querySelector("#publisher").disabled = false;
				document.querySelector("#price").disabled = false;
				document.querySelector("#photo").disabled = false;
			}
			else{
				document.getElementById("form1").onsubmit = "";
			    document.getElementById('button2').setAttribute('type', 'submit');
			}
		}
	</script>
	
	<script type="text/javascript">
		var buttonadd = document.getElementById("button1");
		if(buttonadd.value === "Add"){
			document.querySelector("#title").disabled = false;
			document.querySelector("#author").disabled = false;
			document.querySelector("#description").disabled = false;
			document.querySelector("#category").disabled = false;
			document.querySelector("#page").disabled = false;
			document.querySelector("#dor").disabled = false;
			document.querySelector("#amount").disabled = false;
			document.querySelector("#publisher").disabled = false;
			document.querySelector("#price").disabled = false;
			document.querySelector("#photo").disabled = false;
			document.getElementById("form1").onsubmit = "";
		}
	</script>
	<script>
	var loadFile = function(event) {
		var image = document.getElementById('imageResult');
		const reader = new FileReader();

        reader.onload = () => {
        	image.src = reader.result;
        };
        reader.readAsDataURL(event.target.files[0]);
		
	};
	</script>
</body>
</html>