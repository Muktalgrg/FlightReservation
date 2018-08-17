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
<title>Login</title>

<c:set var="SITE_URL" value="${pageContext.request.contextPath}" />

</head>
<body>
 	<div class="container">
		<div class="row main">
			<div class="panel-heading">
				<div class="panel-title text-center">
					<h1 class="title">Log In</h1>
					<hr />
				</div>
			</div> 		
			
			<div class="col-xs-offset-3 col-xs-6">
			<c:if test="${param.error != null}"><p class="text-danger">Email/Password did not match</c:if>			
			<c:if test="${msg}"><p class="text-danger">Email/Password did not match</c:if>
				<form:form   action="${SITE_URL}/login" method="POST">
						<div class="form-group">
							<label for="userName" class="control-label">Username</label>
							 <input type="text" class="form-control control-label" name="username" id="username" required />
						</div>	
					
					<div class="form-group">
						<label for="password" class="control-label">Password</label>
						<input type="password" id="password" name="password" class="form-control control-label" required/>
					</div>
					<input type="submit" class="btn btn-primary"/>
				<a href="${SITE_URL}/register" type="button" class="btn btn-danger"/>Register</a>
				</form:form>
			</div>
 		</div>
 	</div>
 </body>
 </html>
