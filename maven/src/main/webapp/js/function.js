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