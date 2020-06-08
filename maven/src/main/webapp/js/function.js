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

function sortTable(){
	if(direction == 1){
		$('tr td:nth-child(2)').sortElements(function(a, b){
		    return $(a).text().toUpperCase() > $(b).text().toUpperCase() ? -1 : 1;
		});
		direction = 0;
	}
	else{
		$('tr td:nth-child(2)').sortElements(function(a, b){
		    return $(a).text().toUpperCase() > $(b).text().toUpperCase() ? 1 : -1;
		});
		direction = 1;
	}
}