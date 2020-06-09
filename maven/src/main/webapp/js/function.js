function setDate(){
	if($('#introduced').val() != ""){
		if($('#discontinued').val() < $('#introduced').val()){
			$('#discontinued').val("");
		}
		$('#discontinued').prop("disabled", false);
		$('#discontinued').attr("min", $('#introduced').val());
	}
	else{
		$('#discontinued').prop("disabled", true);
	}
}

function toDate(dateStr) {
  var parts = dateStr.split("-")
  return new Date(parts[2], parts[1] - 1, parts[0])
}

function sortTable() {
	if(direction == 1){
		var compare_rows = function (a,b){
		  	var a_val = $(a).text().toLowerCase();
		  	var b_val = $(b).text().toLowerCase();
			if (a_val < b_val){
				return 1;
			}
			if (a_val > b_val){
			    return -1;
			}
			    return 0;
		};
		direction = 0;
	}
	else{
	
		var compare_rows = function (a,b){
		  	var a_val = $(a).text().toLowerCase();
		  	var b_val = $(b).text().toLowerCase();
			if (a_val > b_val){
				return 1;
			}
			if (a_val < b_val){
			    return -1;
			}
			    return 0;
		};
		direction = 1;
	}
	
	$('table tbody tr').sort(compare_rows).appendTo('table tbody');

	
}