<%@include file="../shared/header.jsp"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Display Flights</title>

<c:set var="SITE_URL" value="${pageContext.request.contextPath}" />

</head>
<body>
<style>

.addMargin{
	margin-top: 15px;
	margin-bottom: 15px; 
}
.error{
	color : red;
	font-size : 0.8em;
} 

</style>
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
        <li><a href="${SITE_URL}/">Home</a></li>
        <li class="active"><a href="${SITE_URL}/admin/addFlights">Add Flight</a></li>
        <li><a href="${SITE_URL}/profile" class="showProfileLink">Profile</a></li>
        <li><a href="${SITE_URL}/showFlight" id="showFlightLink">Book Flight</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#"   class="showProfileLink"><span class="glyphicon glyphicon-user"></span> <security:authentication property="principal.username"/></a></li>
        <li><a href="${SITE_URL}/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
<!-- 
<div id="showFlightPage"></div>
 -->
<div class="container">
	<div id="addFlightsPage">
	<h2>Add Flights</h2>
	<hr/>
		<button class="btn btn-primary addFlightCollapse addMargin" data-toggle="collapse" data-target="#addFlights">Add Flights</button>
	
		<div id="addFlights" class="collapse">
			<div class="row">
				<form:form action="${SITE_URL}/addFlight" id="addFlightForm" method="POST">
					<div class="col-md-4">
						<div class="form-group">
						  <label for="flightCodeId" class="control-label">Flight Code</label>
						  <select class="form-control flightCode" name="flightCodeId" id="flightCodeId" required>
						  	<option value="">Select Flight Code</option>
						  </select>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label for="departureCity" class="control-label">Departure City</label>
							<input type="text" id="departureCity" name="departureCity" class="control-label form-control" required/>
						</div>
					</div>
					<div class="col-md-4">	
						<div class="form-group">
							<label for="arrivalCity" class="control-label">Arrival City</label>
							 <input type="text" class="form-control control-label" name="arrivalCity" id="arrivalCity" required/>
						</div>
					</div>
					<div class="clearfix"></div>
					<div class="col-md-4">
						<div class="form-group">
							<label for="operatingAirlines" class="control-label">Operating Airline</label>
							 <input type="text" class="form-control control-label" name="operatingAirlines" id="operatingAirlines" required/>
						</div>
					</div>	
					<div class='col-md-4'>
			            <div class="form-group">
							<label for="datetimepicker1" class="control-label">Pick date and time</label>            
				                <div class='input-group date' name="datetimepicker1" id='datetimepicker1'>
				                    <input type='text' id="dateTimeValue" class="form-control" required/>
				                    <span class="input-group-addon">
				                        <span class="glyphicon glyphicon-calendar"></span>
				                    </span>
				                </div>
			            </div>
		        	</div>
		        <script type="text/javascript">
		            $(function () {
		                $('#datetimepicker1').datetimepicker({
		                    format: 'YYYY-MM-DD HH:mm', /* moment.js format */
		                });
		            });
		        </script>
		        <div class="clearfix"></div>
		        
		        <div class="col-xs-2">
			        <div class="form-group">
			        	<input type="submit" class="btn btn-primary" id="submitAddFlight"/> 
			        </div>	
		        </div>
				</form:form>	
			</div>
		</div>
		<div class="clearfix"></div>
	
		<h1>Available Flights</h1><p>(Displaying flight that will depart 1 hours later than current time)</p>
		<hr/>
		
		<table id="displayFlights" class="display" style="width:100%">
	         <thead>
	            <tr>
	                <th>Flight Code</th>
	                <th>Number Of Seat</th>
	                <th>Price</th>
	                <th>Departure City</th>
	                <th>Arrival City</th>
	                <th>Estimated Time Of Departure</th>
	                <th>Operating Airlines</th>
	            </tr>
	        </thead>
	
	    </table>
					
	</div>
</div>
	<div id="showProfilePage"></div>
	<div id="showFlightPage"></div>

<script type="text/javascript">

$(function(){
	
 	$.ajax({
		url : '/flights',
		type : 'GET',
		dataType : 'json',
		async : false,
        beforeSend : function(xhr) {
            xhr.setRequestHeader("Accept", "application/json; charset=UTF-8");
            xhr.setRequestHeader("Content-Type","application/json; charset=UTF-8");
        }
	}).done(function(json){
			console.log(json);
		$("#displayFlights").dataTable({
			data : json,
			columns : [
				{'data' : 'flightInformation.flightCode'},
				{'data' : 'flightInformation.numberOfSeat'},
				{'data' : 'flightInformation.price'},
				{'data' : 'departureCity'},
				{'data' : 'arrivalCity'},
				{
					'data' : 'dateOfDeparture',
				},
				{'data' : 'operatingAirlines'}
			]
		});
	}).fail(function(jqXHR, textStatus){
		console.log(jqXHR);
		console.log('request failed : '+textStatus);
	}); 
 	
	
	$.ajax({
		url : "/allFlightsInfo",
		method : "GET",
		dataType : "json", 
	}).done(function(data){
		$.each(data,function(i, item){
			$(".flightCode").append("<option value='"+item.id+"'>"+item.flightCode+"</option>");
		})
	})
	
/* 	validation with jquery validation plugin*/
  	$("#submitAddFlight").on("click", function(e){
		 $("form#addFlightForm").validate({
			 rules: {
				 flightCodeId : {
					 required : true
				 },
				 departureCity : {
					 required : true,
					 maxlength : 20
				 },
				 arrivalCity : {
					 required : true,
					 maxlength : 20
				 },
				 operatingAirlines : {
					 required : true,
					 maxlength : 20
				 },
				 dateTimepicker1 : {
					 required : true,
				 }
			 },
			 highlight: function(element, errorClass){
				 $(element).closest(".form-group").addClass("has-error");
			 },
			 unhighlight : function(element, errorClass){
				 $(element).closest(".form-group").removeClass("has-error");
			 },
			 submitHandler : function(form){
				 
		var flightCodeId = $("#flightCodeId :selected").val();
		var departureCity = $("#departureCity").val();
		var arrivalCity = $("#arrivalCity").val();
		var operatingAirlines = $("#operatingAirlines").val();
		var dateTimepicker1 = $("#dateTimeValue").val();
		
		 var jsonData = {};
		 
		 jsonData["flightCodeId"] = flightCodeId;
		 jsonData["departureCity"] = departureCity;
		 jsonData["arrivalCity"] = arrivalCity;
		 jsonData["operatingAirlines"] = operatingAirlines;
		 jsonData["dateOfDeparture"] = dateTimepicker1;
		 
		 console.log(JSON.stringify(jsonData));
		
		
/* 		console.log("serialized data : "+$("#addFlightForm").serialize()); */
		$.ajax({
			url : "/admin/processFlight",
			type : "POST",
		 	contentType : "application/json", 
			dataType : 'json', 
			data : JSON.stringify(jsonData),
			success : function(data){
				bootbox.alert({
						title : "Message",
						message : data.msg, 
						callback : function(){
							$("#flightCodeId").val("");
							$("#departureCity").val("");
							$("#arrivalCity").val("");
							$("#dateTimeValue").val("");
							$("#operatingAirlines").val("");
						}
					});

			},
			error : function(data){
				console.log(data);
			}
		})
		
		return false;
		
			 }
		 });
   	});
	
	$(".showProfileLink").on("click", function(e){
		e.preventDefault();
		console.log("show profile link is clicked");
		$("#addFlightsPage").hide();
		$("#showFlightPage").hide();
		
		$.get("${SITE_URL}/profile", function(data){
			console.log(data);
			$("#showProfilePage").show();
			$("#showProfilePage").html(data);
		});
	});
	
	$("#showFlightLink").on("click", function(e){
		e.preventDefault();
		console.log("showFlightLink book flight tab is clicked");
		$("#addFlightsPage").hide();
		$("#showProfilePage").hide();
		
		$.get("${SITE_URL}/showFlight", function(data){
			console.log(data);
			$("#showFlightPage").show();
			$("#showFlightPage").html(data);
		});
	});
	
	
/* 	$.get("${SITE_URL}/showFlight", function(data){
		$("#showFlightPage").html(data);
	}); */
	
   	
})

</script>
</body>
</html>
