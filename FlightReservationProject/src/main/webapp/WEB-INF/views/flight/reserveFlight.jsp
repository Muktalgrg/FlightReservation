<!-- SPA -->
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h2>Flight Reservation</h2>
<hr/>
<div class="row">
	<div class="col-sm-4">
		<div class="panel panel-default">
			<div class="panel-heading">Flight Information</div>
			<div class="panel-body">Flight code : ${flight.flightInformation.flightCode}</div>
			<div class="panel-body">Departure City : ${flight.departureCity}</div>
			<div class="panel-body">Arrival City : ${flight.arrivalCity}</div>
			<div class="panel-body">Operating Airlines : ${flight.operatingAirlines }</div>
			<div class="panel-body">Date Of Departure : ${flight.dateOfDeparture }</div>
			<div class="panel-body">Price : ${flight.flightInformation.price}</div>
		</div>
	</div>
</div>
	
	<h1>Passenger Details</h1>
	<hr/>
	<div class="row">
		<div class="col-sm-6">
			<form:form action="${SITE_URL}/reserveFlight" id="reserveFlightForm" method="POST">
				<div class="form-group">
					<label class="control-label" for="firstName">First name </label>
					<input type="text" class="control-label form-control" name="firstName" id="firstName" required/>
				</div>
				<div class="form-group">
					<label class="control-label" for="middleName">Middle name </label>
					<input type="text" class="control-label form-control" name="middleName" id="middleName"/>
				</div>
				<div class="form-group">
					<label class="control-label" for="lastName">Last name </label>
					<input type="text" class="control-label form-control" name="lastName" id="lastName" required/>
				</div>
				<div class="form-group">
					<label class="control-label" for="email">Email </label>
					<input type="email" class="control-label form-control" name="email" id="email" required/>
				</div>
				<div class="form-group">
					<label class="control-label" for="phone">Phone </label>
					<input type="text" class="control-label form-control" name="phone" id="phone" required/>
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-primary" id="submitReserveFlight">Submit</button>
				</div>
			 	<input type="hidden" name="flightId" id="flightId" value="${flight.id}"/>
			</form:form>
		</div>
	
	
</div>



<script>

$(function(){
	$("#submitReserveFlight").on("click", function(){
		console.log("button is clicked");
		$("form#reserveFlightForm").validate({
			rules : {
				firstName : {
					required : true,
					maxlength: 30
				},
				middleName : {
					required : false,
					maxlength : 30
				}, 
				lastName : {
					required : true,
					maxlength : 30
				},
				email : {
					required : true,
					maxlength : 30
				},
				phone : {
					required : true,
					maxlength : 40
				}
			},
			 highlight: function(element, errorClass){
				 $(element).closest(".form-group").addClass("has-error");
			 },
			 unhighlight : function(element, errorClass){
				 $(element).closest(".form-group").removeClass("has-error");
			 },
			 submitHandler : function(form){
				 var jsonData = {};
				 
				 jsonData["firstName"] = $("#firstName").val();
				 jsonData["middleName"] = $("#middleName").val();
				 jsonData["lastName"] = $("#lastName").val();
				 jsonData["email"] = $("#email").val();
				 jsonData["phone"] = $("#phone").val();
				 jsonData["flightId"] = $("#flightId").val();
			 /*  console.log(jsonData);  */
				 
/* 				 bootbox.confirm({
					 title : "Message",
					 message : "Confirm registration",
					 callback : function(){
						 confirmRegistration(jsonData);
					 }
				 }); */
				 
				 bootbox.confirm({
					    message: "Confirm Registration ?",
					    buttons: {
					        confirm: {
					            label: 'Yes',
					            className: 'btn-success'
					        },
					        cancel: {
					            label: 'No',
					            className: 'btn-danger'
					        }
					    },
					    callback: function (result) {
					    	if(result === true){
					    		confirmRegistration(jsonData);
					    	}else{
					    		/* console.log("false should do nothing"); */
					    	} 
					    }
					});
				 
			 return false;
			 
			 }
		});	
	})
	
	function confirmRegistration(jsonData){
	    bootbox.dialog({  size: "small",
	    				  message: '<div class="text-center"><i class="fa fa-spin fa-spinner"></i> Loading...</div>' });
		$.ajax({
			url : "/reserveFlight",
			type : "POST",
		 	contentType : "application/json", 
			 dataType : 'json', 
			data : JSON.stringify(jsonData),
			success : function(data){
				bootbox.alert({
					title : "Message",
					message : data.msg+".<br/>Please note the <b>token number</b> sent to your email address",
					callback : function(){
						console.log("callback called");
						// TODO :  add loading icon later
						window.location.replace("${SITE_URL}/");
					}
				});
			},
			error : function(data){
				bootbox.alert({
					message : "Failed to complete registration"
				});
			}
		});
	}
	
})
	
</script>



