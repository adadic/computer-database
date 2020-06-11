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

function sortTable(str, direction, lines, search){
	if (typeof search === "undefined") {
		window.location.replace("?order=" + str + "&direction=" + direction + "&lines=" + lines + "&search=");
	}
	else{
		window.location.replace("?order=" + str + "&direction=" + direction + "&lines=" + lines + "&search=" + search);
	}
}