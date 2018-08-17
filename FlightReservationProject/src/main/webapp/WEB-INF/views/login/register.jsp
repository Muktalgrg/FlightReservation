<%@include file="../shared/header.jsp"%>
<%@page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>

<c:set var="SITE_URL" value="${pageContext.request.contextPath}" />

</head>
<body>

 	<div class="container">
		<div class="row main">
			<div class="panel-heading">
				<div class="panel-title text-center">
					<h1 class="title">Register New User</h1>
					<hr />
				</div>
			</div>
				<div class="col-xs-offset-3 col-xs-6">
				<c:if test="${msg != null}">
					<p class="text-danger">Message : ${msg}</p>
				</c:if>
				<div class="main-login main-center">
					<form:form class="form-horizontal" id="registerNewUserForm" method="POST" action="${SITE_URL}/processRegister">
						<div class="form-group">
							<label for="firstName" class="control-label">First Name</label>
							 <input type="text" class="form-control" class="control-label" name="firstName" id="firstName"/>
						</div>
						<div class="form-group">
							<label for="middleName" class="control-label">Middle Name</label>
							 <input type="text" class="form-control" class="control-label" name="middleName" id="middleName"/>
						</div>
						<div class="form-group">
							<label for="lastName" class="control-label">Last Name</label>
							 <input type="text" class="form-control" class="control-label" name="lastName" id="lastName"/>
						</div>
						<div class="form-group">
							<label for="userName" class="control-label">User Name</label>
							 <input type="text" class="form-control" class="control-label" name="userName" id="userName"/>
						</div>			
						<div class="form-group">
							<label for="email" class="control-label">Email</label>
							 <input type="email" class="form-control" class="control-label" name="email" id="email"/>
						</div>		
						<div class="form-group">
							<label for="password" class="control-label">Password</label>
							 <input type="password" class="form-control" class="control-label" name="password" id="password"/>
						</div>		
						<div class="form-group">
							<label for="re-type-password" class="control-label">Enter password again</label>
							 <input type="password" class="form-control" class="control-label" name="re_type_password" id="re_type_password"/>
						</div>			
						<div class="form-group">
							<input type="submit" id="registerUser" value="Submit" class="btn btn-primary" />
						</div>
					</form:form>
				</div>
			</div>
		</div>
		
		<div id="content"></div>
	</div> 


<script type="text/javascript">
$(function(){
	
	$('#registerUser').on('click', function(e){
		
		$("#registerNewUserForm").validate({
			rules : {
				firstName : {
					required : true
				},
				lastName : {
					required : true
				},
				userName : {
					required : true
				},
				email : {
					required : true
				},
				password : {
					required : true,
					minlength : 5
				},
				re_type_password : {
					required : true,
					equalTo : "[name='password']"
				}
			},
			 highlight: function(element, errorClass){
				 $(element).closest(".form-group").addClass("has-error");
			 },
			 unhighlight : function(element, errorClass){
				 $(element).closest(".form-group").removeClass("has-error");
			 },
			 submitHandler : function(form){
				 processRegisterNewUserForm();
				 return false;
			 }
			
		});
	});
	
	function processRegisterNewUserForm(){
		
		var formData = {
			'firstName' : $('input[name=firstName]').val(),
			'middleName' : $('input[name=middleName]').val(),
			'lastName' : $('input[name=lastName]').val(),
			'userName' : $('input[name=userName]').val(),
			'email' : $('input[name=email]').val(),
			'password' : $('input[name=password]').val()
		};
		console.log(formData);
		
		$.ajax({
			type : 'POST',
			url : '/processRegister',
			contentType : 'application/json',
			dataType : 'json',
			data : JSON.stringify(formData),
			success : function(data){
				if(data.msg === "success"){
					bootbox.alert({
						title : "Message",
						message : "Registration successful",
						callback : function(){
							console.log("callback called");
							// TODO :  add loading icon later
							window.location.replace("${SITE_URL}/login");
						}
					});
				}else{
					bootbox.alert({
						title : "Message",
						message : "Username/password already exists. Try with unique one",
 						callback : function(){
							console.log("callback called");
							$('input[name=password]').val("");
							$('input[name=re_type_password]').val("");
						} 
					});
					
				}

			},
			error : function(data, status, msg){
				if(status == error)
				bootbox.alert({
					title : "Message",
					message : "Could not proceed at the moment."
				});
			}
		});
	}
});
	
	
 
	
</script>

</body>
</html>