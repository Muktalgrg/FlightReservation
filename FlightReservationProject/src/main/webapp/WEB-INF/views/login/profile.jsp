<%-- <%@include file="../shared/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>    
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<style>

</style>
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="container">

<div class="form-group">
	<button id="changePassword" class="btn btn-primary">Change Password</button>
</div>
<div class="row">
	<div class="col-sm-6">
		<div id="hidePasswordForm" style="display:none">
			<form:form action="${SITE_URL}/changePassword" id="changePasswordForm" method="POST">
				<div class="form-group">
					<label for="userName" class="control-label">Old Name</label>
					 <input type="text" class="form-control" class="control-label" name="userName" id="userName" required/>
				</div>
				<div class="form-group">
					<label for="password" class="control-label">Old password</label>
					 <input type="password" class="form-control" class="control-label" name="password" id="password" required/>
				</div>
				<div class="form-group">
					<button type="submit" id="submitPasswordCheck" class="btn btn-primary">Submit</button>
				</div>
			</form:form>
		</div>
		<!-- for a new password -->
		<div id="hidePasswordConfirmForm" style="display:none">
			<form:form action="${SITE_URL}/changePasswordConfirm" id="changePasswordFormConfirm" method="POST">
				<div class="form-group">
					<label for="userName" class="control-label">New Password</label>
					 <input type="password" class="form-control" class="control-label" name="password1" id="password1" required/>
				</div>
				<div class="form-group">
					<label for="password" class="control-label">Retype password</label>
					 <input type="password" class="form-control" class="control-label" name="retype_password" id="retype_password" required/>
				</div>
				<div class="form-group">
					<button type="submit" id="submitPasswordConfirmCheck" class="btn btn-primary">Submit</button>
				</div>
			</form:form>
		</div>
	</div>
</div>

<div id="showReservedFlights">
	<h1>Registered Flights</h1>
	<hr/>

	<table id="reservedFlightsTable" class="display" style="width:100%">
		<thead>
			<tr>
				<th>Reservation Token</th>
				<th>CheckedIn</th>
				<th>Operating Airlines</th>
				<th>Departure City</th>
				<th>Arrivial City</th>
				<th>Price</th>
				<th>Date Of Departure</th>
			</tr>
		</thead>
 		<tfoot>
			<tr>
				<th>Reservation Token</th>
				<th>CheckedIn</th>
				<th>Operating Airlines</th>
				<th>Departure City</th>
				<th>Arrivial City</th>
				<th>Price</th>
				<th>Date Of Departure</th>
			</tr>
		</tfoot> 
	</table>
	
</div>
</div>


<script type="text/javascript">
$(function(){
	
	$("#changePassword").on("click", function(e){
		$("#hidePasswordForm").show();
	});

	$("#submitPasswordCheck").on("click", function(e){
		$("form#changePasswordForm").validate({
			rules : {
				userName : {
					required : true
				},
				password1 : {
					required1 : true,
				}
			},
			submitHandler : function(form){
				var formData = {
						"userName" : $("input[name=userName]").val(),
						"password" : $("input[name=password]").val()
					};
				console.log(formData);
					
				$.ajax({
					/* type : "POST", */
				 	contentType : "application/json", 
					url : "/changePassword",
					method : "POST",
					dataType : "json",
					data : JSON.stringify(formData),
 					success : function(data, status){
 						if(data.msg === "User not found"){
							showBootbox(data);	
 						}else if (data.msg === "Match not found"){
							showBootbox(data);	
 						}else if(data.msg == "Match Found"){
 							// everything true
 							bootbox.alert({
								title : "Message",
								message : data.msg,
								callback : function(){
									$("input[name=userName]").val("");
									$("input[name=password]").val("");
									$("#changePassword").hide();
									$("#hidePasswordForm").css("display", "none");
									$("#hidePasswordConfirmForm").show();
								}
							});
 						}
					} 
				})
				return false;
			}
		});
	});
	
	function showBootbox(data){
			bootbox.alert({
				title : "Message",
				message : data.msg,
				callback : function(){
					$("input[name=userName]").val("");
					$("input[name=password]").val("");
				}
			});	
	}
	
		$("form#changePasswordFormConfirm").validate({
			rules : {
				password1 : {
					required : true,
					minlength : 5
				},
				retype_password : {
					required : true,
					equalTo : "[name='password1']",
					minlength : 5
				}
			},
			submitHandler : function(form){
				var password = $("#password1").val();
				$.ajax({
					url : "/changePasswordConfirm?password="+password+"",
					method : "POST",
 					success : function(data, status){
 						console.log(data);
 						console.log(status);
 							if(data == "success"){
 							bootbox.alert({
								title : "Message",
								message : "Password update successful",
								callback : function(){
									window.location.replace("${SITE_URL}/login");
									
									//TODO : LOG ME OUT OF HERE
								}
							});
 						}
					} 
				})
				return false;
			}
	});

/* 	show reserved flight info */
 
 	$.ajax({
 		url : '/reservedFlights',
 		type : 'GET',
 		dataType : 'json',
 		success : function(json){
 			$("#reservedFlightsTable").dataTable({
 				data : json,
 				columns : [
 					{'data' : 'reservationToken'},
  					{'data' : 'checkedIn'},
 					{'data' : 'flight.operatingAirlines'},
 					{'data' : 'flight.departureCity'},
 					{'data' : 'flight.arrivalCity'},
 					{'data' : 'flight.flightInformation.price'},
 					{'data' : 'flight.dateOfDeparture'},
 				]
 			});
 		},
 		error : function(er){
 			bootbox.alert({
 				title : "Message",
 				message : err
 			})
 		}
 	})
	

	

});	
	
</script>

</body>
</html>