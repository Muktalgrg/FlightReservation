<%@include file="shared/header.jsp" %>
<%@page isELIgnored="false"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<c:set var="SITE_URL" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Fight Reservation</title>

<style type="text/css">
/* table tr td:last-child {
	display : none;
}
 */
#hiddenId{
	display : none;
}
.error{
	color : red;
	font-size : 0.8em;
} 
</style>


</head>
<body>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">Flight Reservation</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="${SITE_URL}/">Home</a></li>
        <li><a href="${SITE_URL}/admin/addFlights">Add Flight</a></li>
        <li><a href="${SITE_URL}/profile" class="showProfileLink">Profile</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#"   class="showProfileLink"><span class="glyphicon glyphicon-user"></span> <security:authentication property="principal.username"/></a></li>
        <li><a href="${SITE_URL}/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav>

<div id="showFlightPage"></div>
<div id="showProfile"></div>

   	    
<script type="text/javascript">
$(function(){
	$.get("${SITE_URL}/showFlight", function(data){
		$("#showFlightPage").html(data);
	});
	
	$(".showProfileLink").on("click", function(e){
		e.preventDefault();
		$("#showFlightPage").hide();
		
		$.get("${SITE_URL}/profile", function(data){
			console.log(data);
			$("#showProfile").html(data);
		});
	});
	
});

</script>    	

</body>
</html>