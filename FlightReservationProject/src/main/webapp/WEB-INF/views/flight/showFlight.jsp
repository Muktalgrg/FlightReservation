<style>
#flightTable td:last-child {
		display : none;
	}
#hiddenId{
	display : none;
}

</style>

<div class="container">
	<div id = "displayflights">
		<h1>Available Flights</h1><p>(Displaying flight that will depart 1 hours later than current time)</p>
		<hr/>
		
		<table id="flightTable" class="display" style="width:100%">
	         <thead>
	            <tr>
	                <th>Flight Code</th>
	                <!-- <th>Number Of Seat</th> -->
	                <th>Price</th>
	                <th>Departure City</th>
	                <th>Arrival City</th>
	                <th>Estimated Time Of Departure</th>
	                <th>Operating Airlines</th>
	                <th>Action</th>
	            	<th id="hiddenId">id</th>
	            </tr>
	        </thead>
	
	    </table>
	</div>
	
	<div id="flightReservation">
	</div>

</div>
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
		$("#flightTable").dataTable({
			data : json,
			columns : [
				{'data' : 'flightInformation.flightCode'},
				/* {'data' : 'flightInformation.numberOfSeat'}, */
				{'data' : 'flightInformation.price'},
				{'data' : 'departureCity'},
				{'data' : 'arrivalCity'},
				{'data' : 'dateOfDeparture'},
			 	{'data' : 'operatingAirlines'},
 				{
					'targets': -1,
					'data' : null,
					'defaultContent' : "<button class='btn btn-sm btn-primary'>Register</button>"
				}, 
				{'data' : 'id'}
				/* {'data' : 'id', 'visible' : false} */
			]
		});

	    
	}).fail(function(jqXHR, textStatus){
		console.log(jqXHR);
		console.log('request failed : '+textStatus);
	});
    

	$("#flightTable").on( 'click', 'button', function () {
	    var data = $(this).parents("tr");
	    var id = data.find("td:last").html();
	    console.log(id);
	    
	    $.ajax({
	    	url : '/checkFlightSeat?id='+id+'',
	    	type : 'GET',
	    }).done(function(data){
	    	console.log(data);
	    	if(data == 'success'){
	    		reserveFlight();
	    	}else if( data == 'All seat has been booked'){
				bootbox.alert({
					title : "Message",
					message : data, 
				});
	    	}
	    }).fail(function(data){
	    	console.log(data);
	    	console.log("failure");
	    }); 
	    
	    function reserveFlight(){
	        $.get("/reserveFlight?id="+id+"", function(data){
	        	$("#displayflights").hide();
	        	$("#flightReservation").html(data);
	        });
	    }
	});
});
</script>
